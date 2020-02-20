package com.ebay.queens.demo;

import java.io.IOException;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.JAXBException;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ebay.queens.responses.PaypalTokenResponse;

/**
 * Represents an object to hold all the authorization tokens needed to access
 * the eBay & Paypal api's
 */
@Component
public class TokenUtilityClass implements CommandLineRunner {

	private static final org.slf4j.Logger logger = LoggerFactory.getLogger(TokenUtilityClass.class);

	public TokenUtilityClass() {
		// TODO Auto-generated constructor stub
	}
	@Autowired
	private Http httpClass;


	@Override
	public void run(String... args) throws Exception {
		logger.info("Token Utility Class");
		//PaypalTokenResponse test = this.authenticationToken();
		//int hoursToExpire = Integer.parseInt(test.getexpires_in());
		//logger.info("Expires in: " + test.getexpires_in());
		// this.authenticationToken();
		tokenTimer();
	}

	public static void main(String[] args) {

	}
	
	public void tokenTimer() {
	    TimerTask repeatedTask = new TimerTask() {
	        public void run() {
	            System.out.println("Token Generated at: " + new Date());
	            try {
					authenticationToken();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        }
	    };
	    Timer timer = new Timer("Timer");
	     
	    long delay = 1L;
	    long period = 2000000L;
	    timer.scheduleAtFixedRate(repeatedTask, delay, period);
	}
	
	
	/**
	 * Represents an api to retrieve the paypal authentication token which allows access to Paypals' API
	 *            
	 * @returns - PaypalToken response which is a JSON response which contains an authorization token 
	 * 
	 * @throws IOException
	 */
	@GET
	@Path("/AuthenticationToken")
	@Produces(MediaType.APPLICATION_JSON)
	@RequestMapping(value = "/patientdetails", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED)
	public PaypalTokenResponse authenticationToken() throws IOException {
		logger.info("Authentication Token");
		String requestBody = "";
		String url = "https://api.paypal.com/v1/oauth2/token";
		PaypalTokenResponse response = new PaypalTokenResponse();
		response = httpClass.authenticationPost(url, requestBody, "PaypalAuth");
		return response;
	}

}
