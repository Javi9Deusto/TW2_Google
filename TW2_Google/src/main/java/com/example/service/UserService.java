package com.example.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Service;

import com.example.dao.UserRepository;
import com.example.model.User;

@Service
public class UserService {
	private UserRepository userRepository;
	public enum UserServiceResult{
		SUCCESS,
		FAIL;
	}
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	public List<User> getAllUsers() {
        return userRepository.findAll();
    }
	public UserServiceResult registerUser(User user) {
		Optional<User> result = userRepository.findByEmailAndPassword(user.getEmail(),user.getPassword());
		
		if (result.isEmpty()) {
			User savedUser = userRepository.save(user);
			
			if (savedUser != null) {
				return UserServiceResult.SUCCESS;
			}
		}
		
		return UserServiceResult.FAIL;
	}
	public Optional<User> checkEmail(String Email, String password) {
		return userRepository.findByEmailAndPassword(Email,password);
    }
	
}
