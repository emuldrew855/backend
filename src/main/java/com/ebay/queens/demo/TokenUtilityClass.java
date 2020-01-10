package com.ebay.queens.demo;

import java.io.IOException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Represents an object to hold all the authorization tokens
 * needed to access the eBay & Paypal api's
 */
public class TokenUtilityClass {
	
	private static final org.slf4j.Logger logger = LoggerFactory.getLogger(Paypal.class);
	
	 @GET
	    @Path("/authenticationtoken")
	    @Produces(MediaType.APPLICATION_JSON)
	    @RequestMapping(value = "/patientdetails", method = RequestMethod.POST,consumes = MediaType.APPLICATION_FORM_URLENCODED)
	    public String authenticationToken() throws IOException {
	    	logger.info("Authentication Token");
	    	String requestBody = ""; 
	    	logger.info(requestBody);
	    	String url = "https://api.paypal.com/v1/oauth2/token";
	    	String response = Http.authenticationPost(url, requestBody, "PaypalAuth");
	    	return response;
	    }

}
