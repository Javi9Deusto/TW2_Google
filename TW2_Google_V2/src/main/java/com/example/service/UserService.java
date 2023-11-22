package com.example.service;


import org.springframework.stereotype.Service;

import com.example.dao.UserRepository;
import com.example.model.User;

import java.util.List;
import java.util.Optional;

//Use @Service annotation for BUSINESS LOGIC and access to the @REPOSITORY
@Service
public class UserService {

    private UserRepository userRepository;
    public enum UserServiceResult {
		SUCCESS,
		FAIL;
	}
     
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    /**  Returning User information */
    public User getUserById(Long id) {
    	Optional<User> result = userRepository.findById(id);
    	return result.orElse(null);
    }
    
    public User getUserByEmailAndPassword(String email,String password) {
    	Optional<User> result = userRepository.findByEmailAndPassword(email,password);
    	return result.orElse(null);
    }
    
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
         
    /** Creating a New User */
	public UserServiceResult createUser(User user) {
		Optional<User> result = userRepository.findByEmailAndPassword(user.getEmail(),user.getPassword());
		
		if (result.isEmpty()) {
			User savedUser = userRepository.save(user);
			
			if (savedUser != null) {
				return UserServiceResult.SUCCESS;
			}
		}
		
		return UserServiceResult.FAIL;
	}    
}
























