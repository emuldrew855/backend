package com.ebay.queens.demo;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import javax.annotation.PostConstruct;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ebay.queens.responses.PaypalTokenResponse;

/**
 * Represents an object to hold all the authorization tokens needed to access
 * the eBay & Paypal api's
 */
@Component
@Order(1)
public class TokenUtilityClass implements CommandLineRunner {

	private static final org.slf4j.Logger logger = LoggerFactory.getLogger(TokenUtilityClass.class);
	public boolean validToken = false;

	public TokenUtilityClass() {
		logger.info("Token Utility Class");
	}
	
	  @PostConstruct
	  public void postConstruct() {
		  tokenTimer();
	  }

	@Autowired
	private Http httpClass;

	@Override
	public void run(String... args) throws Exception {
		
	}

	public void tokenTimer() {
		TimerTask repeatedTask = new TimerTask() {
			@Override
			public void run() {
				try {
					authenticationToken();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		};
		Timer timer = new Timer("Timer");

		long delay = 1L;
		long period = 3600000L; // Task repeats every hour
		timer.scheduleAtFixedRate(repeatedTask, delay, period);
	}

	/**
	 * Represents an api to retrieve the paypal authentication token which allows
	 * access to Paypals' API
	 * 
	 * @returns - PaypalToken response which is a JSON response which contains an
	 *          authorization token
	 * 
	 * @throws IOException
	 */
	@GET
	@Path("/AuthenticationToken")
	@Produces(MediaType.APPLICATION_JSON)
	@RequestMapping(value = "/patientdetails", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED)
	public boolean authenticationToken() throws IOException {
		logger.info("Authentication Token");
		boolean tokenReceived = false;
		String url = "https://api.paypal.com/v1/oauth2/token";
		PaypalTokenResponse response = new PaypalTokenResponse();
		response = httpClass.authenticationPost(url, "", "PaypalAuth");
		logger.info(response.getacccess_token());
		if (response.getacccess_token() != null) {
			tokenReceived = true;
			this.validToken = true;
		}
		return tokenReceived;
	}

}
