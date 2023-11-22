package com.example.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.model.User;
import com.example.service.UserService;

import java.util.List;

/*Use @Controllers ONLY for Routing: providing end-points (get requests & provide JSON responses)
* They are STATELESS & SINGLETON
*/
@RestController
public class UserController {
	private static final Logger log= LoggerFactory.getLogger(UserController.class);


    private UserService userService;
    
    // Example of constructor injection
    public UserController(UserService userService) {
        this.userService = userService;
    }
    
    @GetMapping("/user/all")
    public List<User> getUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/user/details/{id}")
    public User getUser(@PathVariable Long id) {
        return userService.getUserById(id);
    }
    
    @GetMapping("/user/email/{email}/{password}")
    public User getUserByEmail(@PathVariable String email,@PathVariable String password) {
        return userService.getUserByEmailAndPassword(email,password);
    }
            
    @PostMapping("/user/create")
    public ResponseEntity<Object> createUser(@RequestBody User user) {
    	log.info("Creating a new User ...");
       	switch (userService.createUser(user)) {
       		case FAIL:
    	    	return ResponseEntity.unprocessableEntity().body("User Creation Failed; User exists and/or Operation aborted");

      	    default:
      	    	return ResponseEntity.ok("Successfully created User");	
    	}
    }
}
