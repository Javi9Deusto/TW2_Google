package com.example.twgoogle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.dao.UserRepository;
import com.example.model.User;


@SpringBootApplication
public class Tw2GoogleApplication {
	private static final Logger log = LoggerFactory.getLogger(Tw2GoogleApplication.class);
	@Value("${spring.mail.host}")
	private String host;
	@Value ("${spring.mail.port}")
	private int port;
	
	public static void main(String[] args) {
		SpringApplication.run(Tw2GoogleApplication.class, args);
	
	}
	@Bean
	CommandLineRunner demo(UserRepository repository) {
		return (args)->{
			User user1 = new User("1","1");
			User user2 = new User("2","2");
			User user3 = new User("3","3");
			User user4 = new User("4","4");
			
			repository.save(user1);
			repository.save(user2);
			repository.save(user3);
			repository.save(user4);
			
			log.info("Sample users created");
			log.info("USER services available");
		};
	}
}
