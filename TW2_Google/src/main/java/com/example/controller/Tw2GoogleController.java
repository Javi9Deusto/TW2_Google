package com.example.controller;

import com.example.model.Tw2Google;
import com.example.service.Tw2GoogleService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public class Tw2GoogleController {
	private static final Logger log= LoggerFactory.getLogger(Tw2GoogleController.class);


    private Tw2GoogleService Tw2GoogleService;
    
    // Example of constructor injection
    public Tw2GoogleController(Tw2GoogleService Tw2GoogleService) {
        this.Tw2GoogleService = Tw2GoogleService;
    }
    
    @GetMapping("/Tw2Google/all")
    public List<Tw2Google> getTw2Googles() {
        return Tw2GoogleService.getAllTw2Googles();
    }

    @GetMapping("/Tw2Google/details/{id}")
    public Tw2Google getTw2Google(@PathVariable Long id) {
        return Tw2GoogleService.getTw2GoogleById(id);
    }
    
    @GetMapping("/Tw2Google/email/{email}")
    public Tw2Google getTw2GoogleByEmail(@PathVariable String email) {
        return Tw2GoogleService.getTw2GoogleByEmail(email);
    }
        
    @GetMapping("/Tw2Google/{id}/sendEmail")
    public ResponseEntity<Object> sendEmail(@PathVariable Long id) {
    	log.info("Sending an email to a Tw2Google ...");
       	switch (Tw2GoogleService.sendEmail(id)) {
       	    case "FAIL":
    	    	return ResponseEntity.unprocessableEntity().body("Tw2Google does not exist. Op. aborted, check Tw2Google and try again");
    	    
   	   	    default:
      	    	return ResponseEntity.ok("Default - Email successfully sent");	
    	}
   }
       
    @PostMapping("/Tw2Google/create")
    public ResponseEntity<Object> createTw2Google(@RequestBody Tw2Google Tw2Google) {
    	log.info("Creating a new Tw2Google ...");
       	switch (Tw2GoogleService.createTw2Google(Tw2Google)) {
       		case FAIL:
    	    	return ResponseEntity.unprocessableEntity().body("Tw2Google Creation Failed; Tw2Google exists and/or Operation aborted");

      	    default:
      	    	return ResponseEntity.ok("Successfully created Tw2Google");	
    	}
    }
    
    @PutMapping("/Tw2Google/update/{id}")
    public ResponseEntity<Object> updateTw2Google(@PathVariable Long id, @RequestBody Tw2Google Tw2Google) {
    	log.info("Updating Tw2Google info ...");
    	switch (Tw2GoogleService.updateTw2Google(Tw2Google, id)) {
 	    	case FAIL:
 	    		return ResponseEntity.unprocessableEntity().body("Cannot find the specified Tw2Google. Operation aborted, check Tw2Google and try again");
 	    	
 	    	default:
 	    		return ResponseEntity.ok("Tw2Google successfully updated");	
    	}
    }
   
    @DeleteMapping("Tw2Google/delete/{id}")
    public ResponseEntity<Object> deleteTw2Google(@PathVariable Long id) {
    	log.info("Deleting one Tw2Google: " + id);
    	
    	switch (Tw2GoogleService.deleteTw2Google(id)) {
    		case FAIL:
    			return ResponseEntity.unprocessableEntity().body("Failed to Delete the specified Tw2Google");
  	        
    		default:
    			return ResponseEntity.ok("Successfully deleted the specified Tw2Google");
    	}
    }
    
    @DeleteMapping("/Tw2Google/delete/all")
    public ResponseEntity<Object> deleteTw2Googles() {
    	log.info("Deleting ALL Tw2Googles");
    	
    	switch (Tw2GoogleService.deleteAllTw2Googles()) {
			case FAIL:
				return ResponseEntity.unprocessableEntity().body("Deletion of all Tw2Googles fails");
			default:
				return ResponseEntity.ok("All Tw2Googles has been deleted.");
    	}   
   
    }
}
