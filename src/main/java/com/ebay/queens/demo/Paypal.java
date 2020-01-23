package com.ebay.queens.demo;

import java.io.IOException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Represents a class to access and hit all of the Paypal api's
 */
@Component
@Path("/Paypal")
public class Paypal {

	private static final org.slf4j.Logger logger = LoggerFactory.getLogger(Paypal.class);

	@Autowired
	private Http httpClass;

	public Paypal() throws IOException {
		logger.info("Paypal Constructor");
	}

	/**
	 * Gets list of charities based off charity cause
	 * 
	 * @param missionArea
	 *            is passed in to filter the charities returned
	 * @return string of list of charities
	 */
	@GET
	@Path("/getCharity")
	@Produces(MediaType.APPLICATION_JSON)
	public String charitySearch(@QueryParam("missionArea") String missionArea) throws IOException {
		logger.info("Get Charity");
		String charitySearchResponse = this.advancedCharitySearch(missionArea);
		String queryId = charitySearchResponse.substring(13, 49);
		String url = "https://api.paypal.com/v1/customer/charities?query_id=" + queryId;
		String response = httpClass.genericSendGET(url, "Paypal");
		logger.info(response);
		return response;
	}

	@GET
	@Path("/searchcharityType")
	@Produces(MediaType.APPLICATION_JSON)
	public String advancedCharitySearch(@QueryParam("missionArea") String missionArea) throws IOException {
		logger.info("Advanced Charity Search");
		String url = "https://api.paypal.com/v1/customer/charity-search-queries";
		String requestBody = "{\r\n" + "    \"charity\": {\r\n" + "        \"mission_area\": \"" + missionArea
				+ "\"\r\n" + "    }\r\n" + "}";
		logger.info(requestBody);
		String response = httpClass.genericSendPOST(url, requestBody, "Paypal");
		return response;
	}

	@GET
	@Path("/getcharityType")
	@Produces(MediaType.APPLICATION_JSON)
	public String getCharityType() throws IOException {
		logger.info("Get Charity Type");
		String url = "https://api.sandbox.paypal.com/v1/customer/charities";
		String response = httpClass.genericSendGET(url, "Paypal");
		return response;
	}

}