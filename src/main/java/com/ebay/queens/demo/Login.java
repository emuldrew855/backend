package com.ebay.queens.demo;

import java.io.IOException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.JAXBException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Path("/auth")
public class Login {
	private static final Logger LOGGER = LoggerFactory.getLogger(Login.class);

	Login() {
		LOGGER.info("Login");
	}
	
	@GET
	@Path("/LogIn")
	@Produces(MediaType.APPLICATION_JSON)
	public String logIn(@QueryParam("username") String username, @QueryParam("password") String password) { 
		LOGGER.info("Log In Method" + username + " " + password);
		String response = "";
		System.out.println(SignUp.users);
		for(User user: SignUp.users) {
			if(username.equals(user.getUsername()) && password.equals(user.getPassword())) {
				response = "GrantAccess";
			}else {
				response = "NoAccess";
			}
		}
		
		return response;
	}
}
