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
		// this.advancedCharitySearch("animals");
		// test.authenticationToken();
	}

	/**
	 * Gets list of charities based off charity cause
	 * 
	 * @param missionArea
	 *            is passed in to filter the charities returned
	 * @return string of list of charities
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
		System.out.println("Response" + response);
		final ObjectMapper mapper = new ObjectMapper();
		final PaypalGetCharityResponse paypalGetCharityResponse = mapper.readValue(response,
				PaypalGetCharityResponse.class);
		return paypalGetCharityResponse;
	}

	@GET
	@Path("/SearchCharityType")
	@Produces(MediaType.APPLICATION_JSON)
	public PaypalCharitySearchResponse advancedCharitySearch(@QueryParam("missionArea") String missionArea)
			throws IOException {
		logger.info("Advanced Charity Search");
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
		System.out.println("Deserialized JSON String --> Object");
		System.out.println(charityItemResponse.getQuery_id());
		System.out.println("---------------------------------");
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