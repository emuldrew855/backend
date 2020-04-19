package com.ebay.queens.demo;

import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Logger;

import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.JAXBException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ebay.queens.demo.mongodb.resource.CharityController;
import com.ebay.queens.requests.paypalcharitysearch.Charity;
import com.ebay.queens.requests.paypalcharitysearch.PaypalCharity;
import com.ebay.queens.requests.paypalcharitysearch.PaypalCharitySearchRequest;
import com.ebay.queens.responses.CharityCache;
import com.ebay.queens.responses.CharityCause;
import com.ebay.queens.responses.charityitemresponse.CharityItemResponse;
import com.ebay.queens.responses.paypalcharitysearchresponse.PaypalCharitySearchResponse;
import com.ebay.queens.responses.paypalgetcharityresponse.CauseArea;
import com.ebay.queens.responses.paypalgetcharityresponse.GetCharityResult;
import com.ebay.queens.responses.paypalgetcharityresponse.Links;
import com.ebay.queens.responses.paypalgetcharityresponse.PaypalGetCharityResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Represents a class to access and manage all of the Paypal api's
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
	private Ebay ebay;

	@Autowired 
	CharityController charityController;

	@Autowired
	TokenUtilityClass tokenUtilityClass = new TokenUtilityClass();
	
	Paypal() {
		logger = Utilities.LOGGER;
		logger.info("Paypal Class");
		TimerTask repeatedTask = new TimerTask() {
			@Override
			public void run() {
				if(tokenUtilityClass.validToken) {
					//gatherCharityDataOnLoop();
				}
			}
		};
		Timer timer = new Timer("Timer");

		long delay = 1L;
		long period = 360000L; // Task repeats every hour
		timer.scheduleAtFixedRate(repeatedTask, delay, period);
	}

	
	@Override
	public void run(String... args) throws Exception {
		// Method may be used to test code locally directly from source class
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
		String url = "https://api.paypal.com/v1/customer/charities";
		String response = httpClass.genericSendGET(url, "Paypal");
		final PaypalGetCharityResponse charityItemResponse = mapper.readValue(response, PaypalGetCharityResponse.class);
		return charityItemResponse;
	}
	
	
	@GET
	@GetMapping("/SearchCharity")
	@Produces(MediaType.APPLICATION_JSON)
	public List<GetCharityResult> searchCharity(@QueryParam("searchCharity") String searchCharity) throws IOException {
		List<GetCharityResult> charitySearchResults = new ArrayList();
		for(GetCharityResult charity: charityController.getAllChartiesInSystem()) {
			if(charity.getName().contains(searchCharity)) {
				charitySearchResults.add(charity);
			}
		}
		return charitySearchResults;
	}

	/**
	 * API which returns a static list of Cause Area's [MAY NOT BE NEEDED]
	 * 
	 * @return Map<Integer, String> - Map object containing a list of all the
	 *         Charity Cause areas available
	 */
	@GET
	@PostMapping("/GetCharityCauses")
	@Produces(MediaType.APPLICATION_JSON)
	public Map<Integer, CharityCause> getAllCharityCause() {
		CharityCache charityCache = new CharityCache();
		return charityCache.getCurrentCauseAreas();
	}

	/**
	 * API which iterates through all charities available from Paypal's api and adds
	 * them to a charity cache object also adds all charity cause areas to an object
	 * 
	 * Shouldn't need to return anything as it's purpose is to run and gather all
	 * charity data.
	 * @throws JAXBException 
	 */
	@GET
	@PostMapping("/GetAllCharityCause")
	@Produces(MediaType.APPLICATION_JSON)
	public String[] getCharityCause() throws IOException, JAXBException {
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
		for (int i = 1000; i <= lastReference; i++) {
			String num = String.valueOf(i);
			newUrl = (url + num);
			String response = httpClass.genericSendGET(newUrl, "Paypal");
			PaypalGetCharityResponse charityResponse = mapper.readValue(response, PaypalGetCharityResponse.class);
			// Add charity objects to cache
			for (GetCharityResult charity : charityResponse.getResults()) {
				//charityCache.addCharity(charity);
				CharityItemResponse charityItemResponse = ebay.advancedFindCharityItems(charity.getNonprofit_id());
				if(charityItemResponse == null) {
					logger.info("Charity not in current global id configuration");
				}else if( charityItemResponse.getItems().getItems() == null){
					logger.info("No Charity Products Available");
				}else {
					charityController.addCharity(charity);
				}
				// Adds new charity cause area if necessary
				if (charity.getCause_area() != null) {
					for (CauseArea causeArea : charity.getCause_area()) {
						charityCache.addCharityCause(new CharityCause(causeArea.getName(),""));
					}
				}
			}
		}
		logger.info("All Charities Method Complete");
	
		return lastLink;

	}

	/**
	 * Runs the GetAllCharityCause method on a timer
	 */
	public void gatherCharityDataOnLoop() {
		TimerTask repeatedTask = new TimerTask() {
			@Override
			public void run() {
				try {
					try {
						getCharityCause();
					} catch (JAXBException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
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
	
	// Code which was previously used to gather charities from paypal api directly
	/**
	 * API which fetches a query id to facilitate the next api call of finding a list of charities 
	 * [CURRENTLY NOT WORKING AFTER CHANGE TO PAYPAL API- MAY BE USED IN FUTURE]
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
	 * API which fetches a list of charities based off their charity cause area 
	 * [CURRENTLY NOT WORKING AFTER CHANGE TO PAYPAL API- MAY BE USED IN FUTURE]
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
}