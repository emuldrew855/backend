package com.ebay.queens.demo;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ebay.api.client.auth.oauth2.CredentialUtil;
import com.ebay.api.client.auth.oauth2.OAuth2Api;
import com.ebay.api.client.auth.oauth2.model.Environment;
import com.ebay.api.client.auth.oauth2.model.OAuthResponse;
import com.ebay.queens.responses.PaypalTokenResponse;

/**
 * Represents an object to hold all the authorization tokens needed to access
 * the eBay & Paypal api's
 */
@Component
@Order(1)
@RestController
@RequestMapping("/token")
public class TokenUtilityClass implements CommandLineRunner {
	private static final List<String> authorizationScopesList = Arrays.asList("https://api.ebay.com/oauth/api_scope", "https://api.ebay.com/oauth/api_scope/sell.marketing.readonly");
	private static final Environment EXECUTION_ENV = Environment.PRODUCTION;
    private static final String EBAY_CONFIG = "C:\\Users\\user\\Documents\\Beng Software Engineering\\CSC3032-Software Engineering Project\\backend\\src\\main\\resources\\ebay-config-sample.yaml";
    public TokenUtilityClass()  {
		logger = Utilities.LOGGER;
		logger.info("Token Utility Class");
		logger.addHandler(Utilities.fileHandler);
	}

	private OAuth2Api oauth2API = new OAuth2Api();
	private Logger logger;	
	public boolean validToken = false;
	
	 @Autowired
	private Http httpClass;

	@Autowired
	private Utilities utilityClass;
	
	@PostConstruct
	 public void postConstruct() throws IOException {
		  paypalTokenTimer();
	  }

	@Override
	public void run(String... args) throws Exception {
		// Method may be used to test code locally directly from source class
	}
	
	/**
	 * Represents an api to retrieve the ebay authorization url to authenticate a valid url
	 * 
	 * @returns - authUrl - the URL which a user is required to use to sign in
	 * 
	 * @throws IOException
	 */
	@GET
	@GetMapping("/ebayToken")
	public String ebayToken() throws IOException {
		logger.info("ebay token");
		CredentialUtil.load(new FileInputStream(EBAY_CONFIG));
		logger.info(CredentialUtil.getCredentials(EXECUTION_ENV).toString());
		String authUrl = oauth2API.generateUserAuthorizationUrl(EXECUTION_ENV, authorizationScopesList,  Optional.empty());
		logger.info("Authorization URL: " + authUrl);
		return authUrl;
	}
	
	/**
	 * Represents an api to retrieve the access token which allows a user to gain access to ebay's api
	 * 
	 * @returns - the response from eBays api
	 * @throws IOException
	 */
	@GET
	@GetMapping("/accessToken")
	public String getEbayAccessToken(@QueryParam("code") String code) throws IOException {
		logger.info("Access Token");
		OAuthResponse response = oauth2API.exchangeCodeForAccessToken(EXECUTION_ENV, code);
		//Login.activeUser.setUserRefreshToken(response.getRefreshToken().toString());
		Login.activeUser.setEbayAuthToken((response.getAccessToken().get().getToken()));
		logger.info("Active User ebay auth: " + Login.activeUser.getEbayAuthToken());
		return response.toString();
	}
	
	/**
	 * Represents an api to retrieve a refresh access token once an access token has expired
	 * 
	 * @returns - the response from eBays api
	 * @throws IOException
	 */
	@GET
	@GetMapping("/refreshToken")
	@RequestMapping(value = "/somethingElse", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED)
	public String getRefreshToken() throws IOException {
		String url = "https://api.ebay.com/identity/v1/oauth2/token";
		httpClass.ebayRefreshTokenPost(url,  "refreshToken");
		return null;
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
	@Path("/PaypalAuthenticationToken")
	@Produces(MediaType.APPLICATION_JSON)
	@RequestMapping(value = "/patientdetails", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED)
	public boolean paypalAuthenticationToken() throws IOException {
		logger.info("Paypal Authentication token");
		boolean tokenReceived = false;
		String url = "https://api.paypal.com/v1/oauth2/token";
		PaypalTokenResponse response = new PaypalTokenResponse();
		response = httpClass.paypalAuthenticationPost(url, "PaypalAuth");
		logger.info(response.getacccess_token());
		if (response.getacccess_token() != null) {
			tokenReceived = true;
			this.validToken = true;
		}
		Login.activeUser.setPaypalAuthToken(response.getacccess_token());
		return tokenReceived;
	}
	
	public void paypalTokenTimer() {
		TimerTask repeatedTask = new TimerTask() {
			@Override
			public void run() {
				try {
					paypalAuthenticationToken();
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

}
