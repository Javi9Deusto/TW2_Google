package com.example;


import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.dao.UserRepository;
import com.example.model.User;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;



@SpringBootApplication
public class Tw2GoogleV2Application {
	
	private static final Logger log= LoggerFactory.getLogger(Tw2GoogleV2Application.class);
	  

    public static void main(String[] args) {
        SpringApplication.run(Tw2GoogleV2Application.class, args);
        
          }
     
        
    @Bean
    CommandLineRunner demo(UserRepository repository) {
      return (args) -> {
          // INIT data ... some Users
    	    	  
    	  User veronica = new User ("veronica@gmail.es", "1234");
    	  User mercedes = new User ("mercedes@gmail.es", "123456");
    	  User macarena = new User ("macarena@gmail.es", "1234567");
    	  User joseMiguel = new User ("josemiguel@gmail.es", "12345678");
    	   
    	  repository.save(veronica);
    	  repository.save(mercedes);
    	  repository.save(macarena);
    	  repository.save(joseMiguel);
    	  
        log.info("Sample users created"); 
    	  
    	log.info("USER Services AVAILABLE ...");
      };
    }
}
    
