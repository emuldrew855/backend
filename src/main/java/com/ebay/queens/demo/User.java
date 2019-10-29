package com.ebay.queens.demo;

import javax.validation.constraints.NotNull;

public class User {
     
	@NotNull(message = "First Name is mandatory")
    private String firstName;
    private String lastName;
     
    // standard constructors / setters / getters / toString
        User(String firstName, String lastName) {
        	this.setFirstName(firstName);
        	this.setLastName(lastName);
        }
        
        User() {
		}

		public String getLastName() {
			return lastName;
		}

		public String getFirstName() {
			return firstName;
		}
		
		
		public void setLastName(String lastName) {
			this.lastName = lastName;
		}

		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}
}
