package com.example.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.User;
import com.example.service.UserService;



@RestController
public class userController {
	private static final Logger log = LoggerFactory.getLogger(userController.class);
	private UserService userService;
	
	public userController(UserService userService) {
		this.userService = userService;
	}
	
	@GetMapping("/user/all")
	public List<User> getUsers(){
		return userService.getAllUsers();
	}
	@GetMapping("/user/email/{email}/{password}")
	public boolean checkEmail(@PathVariable String email,@PathVariable String password) {
		return userService.checkEmail(email,password).isPresent();
	}
	@PostMapping("/user/register")
    public ResponseEntity<Object> registerUser(@RequestBody User user) {
    	log.info("Creating a new User ...");
       	switch (userService.registerUser(user)) {
       		case FAIL:
    	    	return ResponseEntity.unprocessableEntity().body("User Creation Failed; User exists and/or Operation aborted");

      	    default:
      	    	return ResponseEntity.ok("Successfully created User");	
    	}
    }

}
