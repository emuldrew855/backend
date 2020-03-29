package com.ebay.queens.demo;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.StringReader;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.slf4j.LoggerFactory;
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
import com.ebay.queens.responses.findnonprofitresponse.FindNonProfitResponse;

/**
 * Represents an object to hold all the authorization tokens needed to access
 * the eBay & Paypal api's
 */
@Component
@Order(1)
@RestController
@RequestMapping("/token")
public class TokenUtilityClass implements CommandLineRunner {
	private OAuth2Api oauth2API = new OAuth2Api();
	private static final List<String> authorizationScopesList = Arrays.asList("https://api.ebay.com/oauth/api_scope", "https://api.ebay.com/oauth/api_scope/sell.marketing.readonly");
    private static final Environment EXECUTION_ENV = Environment.PRODUCTION;
    private static final String EBAY_CONFIG = "C:\\Users\\user\\Documents\\Beng Software Engineering\\CSC3032-Software Engineering Project\\backend\\src\\main\\resources\\ebay-config-sample.yaml";

	private Logger logger;
	public boolean validToken = false;	

	public TokenUtilityClass()  {
		logger = Utilities.LOGGER;
		logger.info("Token Utility Class");
		logger.addHandler(Utilities.fileHandler);
	}
	
	  @PostConstruct
	  public void postConstruct() throws IOException {
		  tokenTimer();
	  }

	@Autowired
	private Http httpClass;
	
	@Autowired
	private Utilities utilityClass;

	@Override
	public void run(String... args) throws Exception {
		
	}
	
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
	
	@GET
	@GetMapping("/accessToken")
	public String getAccessToken(@QueryParam("code") String code) throws IOException {
		logger.info("Access Token");
		OAuthResponse response = oauth2API.exchangeCodeForAccessToken(EXECUTION_ENV, code);
		Login.activeUser.setEbayAuthToken((response.getAccessToken().get().getToken()));
		logger.info("Utility class ebay auth: " + utilityClass.getEbayAuth());
		return response.toString();
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
		logger.info("Authentication token");
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
