package com.example.service;

import com.example.dao.Tw2GoogleRepository;
import com.example.external.EmailService;
import com.example.model.Tw2Google;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public class Tw2GoogleService {
	private Tw2GoogleRepository Tw2GoogleRepository;
    public enum Tw2GoogleServiceResult {
		SUCCESS,
		FAIL;
	}
 
    @Autowired
    private EmailService emailService;
        
    public Tw2GoogleService(Tw2GoogleRepository Tw2GoogleRepository) {
        this.Tw2GoogleRepository = Tw2GoogleRepository;
    }
    /**  Returning Tw2Google information */
    public Tw2Google getTw2GoogleById(Long id) {
    	Optional<Tw2Google> result = Tw2GoogleRepository.findById(id);
    	return result.orElse(null);
    }
    
    public Tw2Google getTw2GoogleByEmail(String email) {
    	Optional<Tw2Google> result = Tw2GoogleRepository.findByEmail(email);
    	return result.orElse(null);
    }
    
    public List<Tw2Google> getAllTw2Googles() {
        return Tw2GoogleRepository.findAll();
    }
    
    /** Sending an Email to a Tw2Google */
    public String sendEmail(Long id) {
        Optional<Tw2Google> result = Tw2GoogleRepository.findById(id);

        return result.map(theTw2Google -> {
            emailService.sendSimpleMessage(theTw2Google.getEmail(), "This is a Spring Boot Message");
            return "Email successfully sent";
        }).orElse("Tw2Google does not exist. Operation aborted, check the Tw2Google and try again");
    }
     
    /** Creating a New Tw2Google */
	public Tw2GoogleServiceResult createTw2Google(Tw2Google Tw2Google) {
		Optional<Tw2Google> result = Tw2GoogleRepository.findByEmail(Tw2Google.getEmail());
		
		if (result.isEmpty()) {
			Tw2Google savedTw2Google = Tw2GoogleRepository.save(Tw2Google);
			
			if (savedTw2Google != null) {
				return Tw2GoogleServiceResult.SUCCESS;
			}
		}
		
		return Tw2GoogleServiceResult.FAIL;
	}    
   
    /** Update an Existing Tw2Google */
    public Tw2GoogleServiceResult updateTw2Google(Tw2Google Tw2Google, Long id) {
    	Optional<Tw2Google> result = Tw2GoogleRepository.findById(id);
		
		if (!result.isEmpty()) {
			Tw2Google updatedTw2Google = result.get();
			
			updatedTw2Google.setFirstName(Tw2Google.getFirstName());
			updatedTw2Google.setLastName(Tw2Google.getLastName());
			updatedTw2Google.setEmail(Tw2Google.getEmail());

			Tw2GoogleRepository.save(updatedTw2Google);
			
			if (!Tw2GoogleRepository.findById(id).isEmpty()) {
				return Tw2GoogleServiceResult.SUCCESS;
			}
		}
		
		return Tw2GoogleServiceResult.FAIL;
    }
  
    /** Delete one Tw2Google*/
    public Tw2GoogleServiceResult deleteTw2Google(Long id) {
    	Optional<Tw2Google> result = Tw2GoogleRepository.findById(id);
		
		if (!result.isEmpty()) {
			Tw2GoogleRepository.delete(result.get());

			if (Tw2GoogleRepository.findById(id).isEmpty()) {
				return Tw2GoogleServiceResult.SUCCESS;
			}
		}
		
		return Tw2GoogleServiceResult.FAIL;
    }
    
    /** Delete all Tw2Googles in the database  */
    public Tw2GoogleServiceResult deleteAllTw2Googles() {
    	Tw2GoogleServiceResult result = Tw2GoogleServiceResult.SUCCESS;
		
		for (Tw2Google u : Tw2GoogleRepository.findAll()) {
			Tw2GoogleRepository.deleteById(u.getId());

			if (!Tw2GoogleRepository.findById(u.getId()).isEmpty()) {
				result = Tw2GoogleServiceResult.FAIL;
			}
		}

		return result;
    }
}
