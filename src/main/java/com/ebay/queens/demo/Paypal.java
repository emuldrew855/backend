package com.ebay.queens.demo;

import java.io.IOException;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Logger;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ebay.queens.requests.paypalcharitysearch.Charity;
import com.ebay.queens.requests.paypalcharitysearch.PaypalCharity;
import com.ebay.queens.requests.paypalcharitysearch.PaypalCharitySearchRequest;
import com.ebay.queens.responses.CharityCache;
import com.ebay.queens.responses.paypalcharitysearchresponse.PaypalCharitySearchResponse;
import com.ebay.queens.responses.paypalgetcharityresponse.CauseArea;
import com.ebay.queens.responses.paypalgetcharityresponse.GetCharityResult;
import com.ebay.queens.responses.paypalgetcharityresponse.Links;
import com.ebay.queens.responses.paypalgetcharityresponse.PaypalGetCharityResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Represents a class to access and hit all of the Paypal api's
 */
@Order(3)
@Lazy(true)
@Component
@RestController
@RequestMapping("/Paypal")
public class Paypal implements CommandLineRunner {
	private Logger logger;
	final ObjectMapper mapper = new ObjectMapper();
	@Autowired
	private Http httpClass;

	@Autowired
	TokenUtilityClass tokenUtilityClass = new TokenUtilityClass();

	Paypal() {
		logger = Utilities.LOGGER;
		logger.info("Paypal Class");
		TimerTask repeatedTask = new TimerTask() {
			@Override
			public void run() {
				if(tokenUtilityClass.validToken) {
					//tokenTimer();
				}
			}
		};
		Timer timer = new Timer("Timer");

		long delay = 1L;
		long period = 20000L; // Task repeats every hour
		timer.scheduleAtFixedRate(repeatedTask, delay, period);
	}

	@Override
	public void run(String... args) throws Exception {
		// this.advancedCharitySearch("animals");
		// test.authenticationToken();
	}

	/**
	 * API which fetches a list of charities based off their charity cause area
	 * 
	 * @param missionArea
	 *            - String containing the name of the mission area the user wishes
	 *            to search for
	 * 
	 * @returns a PaypalGetCharityResponse object which contains JSON of a list of
	 *          charities related to the mission area input
	 * 
	 * @throws IOException
	 */
	@GET
	@GetMapping("/GetCharity")
	@Produces(MediaType.APPLICATION_JSON)
	public PaypalGetCharityResponse charitySearch(@QueryParam("missionArea") String missionArea) throws IOException {
		logger.info("Get Charity");
		PaypalCharitySearchResponse charitySearchResponse = this.advancedCharitySearch(missionArea);
		String queryId = charitySearchResponse.getQuery_id();
		String url = "https://api.paypal.com/v1/customer/charities?query_id=" + queryId;
		String response = httpClass.genericSendGET(url, "Paypal");
		logger.info("Response: " + response);
		final PaypalGetCharityResponse paypalGetCharityResponse = mapper.readValue(response,
				PaypalGetCharityResponse.class);
		return paypalGetCharityResponse;
	}

	/**
	 * API which fetches a query id to facilitate the next api call of finding a
	 * list of charities
	 * 
	 * @param missionArea
	 *            - String containing the name of the mission area the user wishes
	 *            to search for
	 * 
	 * @return a PaypalCharitySearchResponse - object of JSON containing a query id
	 * 
	 * @throws IOException
	 */
	@GET
	@GetMapping("/SearchCharityType")
	@Produces(MediaType.APPLICATION_JSON)
	public PaypalCharitySearchResponse advancedCharitySearch(@QueryParam("missionArea") String missionArea)
			throws IOException {
		logger.info("Searching for mission area: " + missionArea);
		String url = "https://api.paypal.com/v1/customer/charity-search-queries";
		PaypalCharitySearchRequest paypalCharitySearchRequest = new PaypalCharitySearchRequest();
		PaypalCharity paypalCharity = new PaypalCharity();
		paypalCharity.setMissionArea(missionArea);
		Charity charity = new Charity();
		charity.setPaypalCharity(paypalCharity);
		paypalCharitySearchRequest.setCharity(charity);
		String response = httpClass.genericJSONSendPOST(url, charity, "Paypal");
		final PaypalCharitySearchResponse charityItemResponse = mapper.readValue(response,
				PaypalCharitySearchResponse.class);
		return charityItemResponse;
	}

	/**
	 * API which fetches a list of charities with links to all the charities for the
	 * system to search through
	 * 
	 * @return a PaypalGetCharityResponse - object of JSON containing a list of
	 *         charities and links object
	 * 
	 * @throws IOException
	 */
	@GET
	@PostMapping("/GetAllCharity")
	@Produces(MediaType.APPLICATION_JSON)
	public PaypalGetCharityResponse getAllCharity() throws IOException {
		logger.info("Get Charity");
		String url = "https://api.paypal.com/v1/customer/charities";
		String response = httpClass.genericSendGET(url, "Paypal");
		final PaypalGetCharityResponse charityItemResponse = mapper.readValue(response, PaypalGetCharityResponse.class);
		return charityItemResponse;
	}

	/**
	 * API which returns a static list of Cause Area's
	 * 
	 * @return Map<Integer, String> - Map object containing a list of all the
	 *         Charity Cause areas available
	 */
	@GET
	@PostMapping("/GetCharityCauses")
	@Produces(MediaType.APPLICATION_JSON)
	public Map<Integer, String> getAllCharityCause() {
		CharityCache charityCache = new CharityCache();
		return charityCache.getCurrentCauseAreas();
	}

	/**
	 * Runs the GetAllCharityCause method on a timer
	 */
	public void tokenTimer() {
		TimerTask repeatedTask = new TimerTask() {
			@Override
			public void run() {
				try {
					getCharityCause();
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
	 * API which iterates through all charities available from Paypal's api and adds
	 * them to a charity cache object also adds all charity cause areas to an object
	 * 
	 * Shouldn't need to return anything as it's purpose is to run and gather all
	 * charity data.
	 */
	@GET
	@PostMapping("/GetAllCharityCause")
	@Produces(MediaType.APPLICATION_JSON)
	public String[] getCharityCause() throws IOException {
		logger.info("GetAllCharityMethod");
		PaypalGetCharityResponse getCharityResponse = this.getAllCharity();
		Links[] links = getCharityResponse.getLinks();
		String[] lastLink = null;
		int lastReference = 0;
		// Getting last reference number
		for (Links link : links) {
			if (link.getRel().equals("last")) {
				lastLink = link.getHref().split("page=");
				lastReference = Integer.parseInt(lastLink[1]);
			}
		}
		// Getting all urls for all charities
		CharityCache charityCache = new CharityCache();
		String url = "https://api.paypal.com/v1/customer/charities?page_size=15&page=";
		String newUrl = "";
		for (int i = 1; i <= lastReference; i++) {
			String num = String.valueOf(i);
			newUrl = (url + num);
			String response = httpClass.genericSendGET(newUrl, "Paypal");
			PaypalGetCharityResponse charityResponse = mapper.readValue(response, PaypalGetCharityResponse.class);
			// Add charity objects to cache
			for (GetCharityResult charity : charityResponse.getResults()) {
				charityCache.addCharity(charity);
				if (charity.getCause_area() != null) {
					for (CauseArea causeArea : charity.getCause_area()) {
						charityCache.addCharityCause(causeArea.getName());
					}
				}
			}
		}
		logger.info("All Charities Method Complete");
		// Printing all Charity Causes
		for (Map.Entry<Integer, String> entry : charityCache.getCurrentCauseAreas().entrySet()) {
			System.out.println("Cause Area = " + entry.getValue());
		}
		logger.info("Amount of Charities: " + charityCache.getCurrentCharityResponses().size());
		return lastLink;

	}

}