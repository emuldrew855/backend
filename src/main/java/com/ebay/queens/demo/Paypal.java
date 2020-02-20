package com.ebay.queens.demo;

import java.io.IOException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.ebay.queens.requests.paypalcharitysearch.Charity;
import com.ebay.queens.requests.paypalcharitysearch.PaypalCharity;
import com.ebay.queens.requests.paypalcharitysearch.PaypalCharitySearchRequest;
import com.ebay.queens.responses.paypalcharitysearchresponse.PaypalCharitySearchResponse;
import com.ebay.queens.responses.paypalgetcharityresponse.PaypalGetCharityResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Represents a class to access and hit all of the Paypal api's
 */
@Component
@Path("/Paypal")
public class Paypal implements CommandLineRunner {
	private static final org.slf4j.Logger logger = LoggerFactory.getLogger(Paypal.class);

	@Autowired
	private Http httpClass;
	
	@Autowired
	TokenUtilityClass test = new TokenUtilityClass();

	@Override
	public void run(String... args) throws Exception {
		//this.advancedCharitySearch("animals");
		// test.authenticationToken();
	}

	/**
	 * API which fetches a list of charities based off their charity cause area 
	 * 
	 * @param missionArea - String containing the name of the mission area the user wishes to search for 
	 * 
	 * @returns a PaypalGetCharityResponse object which contains JSON of a list of charities related to the mission area input
	 * 
	 * @throws IOException
	 */
	@GET
	@Path("/GetCharity")
	@Produces(MediaType.APPLICATION_JSON)
	public PaypalGetCharityResponse charitySearch(@QueryParam("missionArea") String missionArea) throws IOException {
		logger.info("Get Charity");
		PaypalCharitySearchResponse charitySearchResponse = this.advancedCharitySearch(missionArea);
		String queryId = charitySearchResponse.getQuery_id();
		String url = "https://api.paypal.com/v1/customer/charities?query_id=" + queryId;
		String response = httpClass.genericSendGET(url, "Paypal");
		logger.info("Response: " + response);
		final ObjectMapper mapper = new ObjectMapper();
		final PaypalGetCharityResponse paypalGetCharityResponse = mapper.readValue(response,
				PaypalGetCharityResponse.class);
		return paypalGetCharityResponse;
	}
	
	/**
	 * API which fetches a query id to facilitate the next api call of finding a list of charities
	 * 
	 * @param missionArea - String containing the name of the mission area the user wishes to search for 
	 * 
	 * @return a PaypalCharitySearchResponse - object of JSON containing a query id 
	 * 
	 * @throws IOException
	 */
	@GET
	@Path("/SearchCharityType")
	@Produces(MediaType.APPLICATION_JSON)
	public PaypalCharitySearchResponse advancedCharitySearch(@QueryParam("missionArea") String missionArea)
			throws IOException {
		logger.info("Searching for api queryid");
		String url = "https://api.paypal.com/v1/customer/charity-search-queries";
		PaypalCharitySearchRequest paypalCharitySearchRequest = new PaypalCharitySearchRequest();
		PaypalCharity paypalCharity = new PaypalCharity();
		paypalCharity.setMissionArea(missionArea);
		Charity charity = new Charity();
		charity.setPaypalCharity(paypalCharity);
		paypalCharitySearchRequest.setCharity(charity);
		String response = httpClass.genericJSONSendPOST(url, charity, "Paypal");
		final ObjectMapper mapper = new ObjectMapper();
		final PaypalCharitySearchResponse charityItemResponse = mapper.readValue(response,
				PaypalCharitySearchResponse.class);
		return charityItemResponse;
	}

	/*
	 * @GET
	 * 
	 * @Path("/getcharityType")
	 * 
	 * @Produces(MediaType.APPLICATION_JSON) public String getCharityType() throws
	 * IOException { logger.info("Get Charity Type"); String url =
	 * "https://api.paypal.com/v1/customer/charities"; String response =
	 * httpClass.genericSendGET(url, "Paypal"); return response; }
	 */

}