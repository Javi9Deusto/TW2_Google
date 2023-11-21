package com.example.twgoogle;

import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.SecurityProperties.Tw2Google;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import com.example.dao.Tw2GoogleRepository;

@SpringBootApplication
public class Tw2GoogleApplication {
	private static final Logger log= LoggerFactory.getLogger(Tw2GoogleApplication.class);

	@Value("${spring.mail.host}")		
	private String host;
	@Value("${spring.mail.port}")		
	private int port;
	@Value("${spring.mail.Tw2Googlename}")
    private String sender;
	@Value("${spring.mail.password}")
	private String password;
	
	public static void main(String[] args) {
		SpringApplication.run(Tw2GoogleApplication.class, args);
	}
	
	@Bean
    JavaMailSender getJavaMailSender() {
    	// Configuration programmatically done - automatic Spring mapping does not work
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl(); 
       
        mailSender.setHost(host);
        mailSender.setPort(port);
        mailSender.setTw2Googlename(sender);
        mailSender.setPassword(password);

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");

        return mailSender;
    }
    
    @Bean
    CommandLineRunner demo(Tw2GoogleRepository repository) {
      return (args) -> {
          // INIT data ... some Tw2Googles
    	    	  
    	  Tw2Google rebeca = new Tw2Google ("Rebeca", "Cortazar", "rebeca.cortazar@deusto.es");
    	  Tw2Google roberto = new Tw2Google ("Roberto", "Carballedo", "rcarba@deusto.es");
    	   
    	  repository.save(rebeca);
    	  repository.save(roberto);
    	  
        log.info("Sample Tw2Googles created"); 
    	  
    	log.info("Tw2Google Services AVAILABLE ...");
      };
    }

}
