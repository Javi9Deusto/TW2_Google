package com.example.model;

import jakarta.persistence.*;

@Table(name="userTable")
@Entity
public class Tw2Google {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    
    @Column(unique = true)
    private String email;
      
    public Tw2Google() {}
    
    public Tw2Google(String firstName, String lastName, String email) {
    	this.firstName=firstName;
    	this.lastName=lastName;
    	this.email=email;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
   
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
}
