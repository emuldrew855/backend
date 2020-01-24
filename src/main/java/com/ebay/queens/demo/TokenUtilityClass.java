package com.ebay.queens.demo;

import java.io.IOException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ebay.queens.responses.PaypalTokenResponse;

/**
 * Represents an object to hold all the authorization tokens needed to access
 * the eBay & Paypal api's
 */
public class TokenUtilityClass {

	private static final org.slf4j.Logger logger = LoggerFactory.getLogger(Paypal.class);

	public TokenUtilityClass() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {

	}
	
	private ExternalConfig externalConfig;
	
	@Autowired
	private Http httpClass = new Http(externalConfig);

	@GET
	@Path("/authenticationtoken")
	@Produces(MediaType.APPLICATION_JSON)
	@RequestMapping(value = "/patientdetails", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED)
	public PaypalTokenResponse authenticationToken() throws IOException {
		logger.info("Authentication Token");
		System.out.println(httpClass);
		String requestBody = "";
		String url = "https://api.paypal.com/v1/oauth2/token";
		PaypalTokenResponse response = new PaypalTokenResponse();
		httpClass.authenticationPost(url, requestBody, "PaypalAuth");
		return response;
	}

}
