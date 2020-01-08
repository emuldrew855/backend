package com.ebay.queens.demo;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import java.io.IOException;
@CrossOrigin
@Component
@Path("/Paypal")
public class Paypal {
	
	Http httpClass = new Http();
	private static final org.slf4j.Logger logger = LoggerFactory.getLogger(Paypal.class);
	
	public Paypal() throws IOException {
		logger.info("Paypal Constructor");
		this.authenticationToken();
	}
	
	@GET
	@Path("/GetCharity")
	@Produces(MediaType.APPLICATION_JSON)
	public String charitySearch(@QueryParam("missionArea") String missionArea) throws IOException {
		logger.info("Get Charity");
		String charitySearchResponse = this.advancedCharitySearch(missionArea);
		String queryId = charitySearchResponse.substring(13,49);
		String url = "https://api.paypal.com/v1/customer/charities?query_id=" + queryId;
		String response = Http.genericSendGET(url, "Paypal");
		logger.info(response);
		return response;
	}
		
	@GET
	@Path("/SearchCharityType")
	@Produces(MediaType.APPLICATION_JSON)
	public String advancedCharitySearch(@QueryParam("missionArea") String missionArea) throws IOException {
		logger.info("Advanced Charity Search");
		String url = "https://api.paypal.com/v1/customer/charity-search-queries";
		String requestBody = "{\r\n" + 
				"    \"charity\": {\r\n" + 
				"        \"mission_area\": \""+missionArea+"\"\r\n" + 
				"    }\r\n" + 
				"}";
		logger.info(requestBody);
		String response = Http.genericSendPOST(url, requestBody, "Paypal");
		return response;
	}
	
    @GET
    @Path("/GetCharityType")
    @Produces(MediaType.APPLICATION_JSON)
    public String getCharityType() throws IOException {
    	logger.info("Get Charity Type");
    	String url = "https://api.sandbox.paypal.com/v1/customer/charities";
    	String response = Http.genericSendGET(url, "Paypal");
    	return response;
    }
    
    @GET
    @Path("/AuthenticationToken")
    @Produces(MediaType.APPLICATION_JSON)
    @RequestMapping(value = "/patientdetails", method = RequestMethod.POST,consumes = MediaType.APPLICATION_FORM_URLENCODED)
    public String authenticationToken() throws IOException {
    	logger.info("Authentication Token");
    	String requestBody = ""; 
    	logger.info(requestBody);
    	String url = "https://api.paypal.com/v1/oauth2/token";
    	String response = Http.authenticationPost(url, requestBody, "PaypalAuth");
    	return response;
    }
    
}