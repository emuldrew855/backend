package com.ebay.queens.demo;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;

import javax.validation.Valid;
import javax.websocket.server.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import com.ebay.queens.demo.User;
import com.ebay.queens.demo.Message;

@CrossOrigin
@Component
@Path("/v1")
public class Version1Api {


	// Calling this API simply returns the plaintext response "Hello World"
    @GET
    @Path("/{hello}")
    public String getMessage() {
        return "Hello World ";
    }
    
    @POST 
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String returnMessage(@Valid @RequestBody User newUser) {
    	User returnUser = newUser; 
    	returnUser.setFirstName(newUser.getFirstName());
    	returnUser.setLastName(newUser.getLastName());
        return ("Hello " + returnUser.getFirstName() + " " + returnUser.getLastName());
    } 
    
    @GET
    @Path("/AdvancedCharityItems")
    @Produces(MediaType.APPLICATION_JSON)
    public String advancedCharitySearch(@QueryParam("charityItemId") String charityItemId, @QueryParam("listingType") String listingType) throws IOException {
    	System.out.println("Advanced Charity Search");
    	System.out.println("Charity Item Id: " + charityItemId + " Listing Type: " + listingType );
    	String requestBody = "{\r\n" + 
    			"    \"searchRequest\": {\r\n" + 
    			"        \"sortOrder\": \"BestMatch\",\r\n" + 
    			"        \"paginationInput\": {\r\n" + 
    			"            \"pageNumber\": 1,\r\n" + 
    			"            \"entriesPerPage\": 5\r\n" + 
    			"        },\r\n" + 
    			"        \"keyword\": \"phone\",\r\n" + 
    			"        \"constraints\": {\r\n" + 
    			"            \"globalAspect\": [\r\n" + 
    			"                {\r\n" + 
    			"                    \"constraintType\": \"CharityIds\",\r\n" + 
    			"                    \"value\": ["+charityItemId+"]\r\n" + 
    			"                },\r\n" + 
    			"                {\r\n" + 
    			"                    \"constraintType\": \"CharityOnly\",\r\n" + 
    			"                    \"value\": [\"true\"]\r\n" + 
    			"                },\r\n" + 
    			"                {\r\n" + 
    			"                    \"constraintType\": \"ListingType\",\r\n" + 
    			"                    \"value\": [\""+listingType+"\"],\r\n" + 
    			"                    \"paramNameValue\": [\r\n" + 
    			"                        {\r\n" + 
    			"                            \"name\": \"operator\",\r\n" + 
    			"                            \"value\": \"exclusive\"\r\n" + 
    			"                        }\r\n" + 
    			"                    ]\r\n" + 
    			"                }\r\n" + 
    			"            ]\r\n" + 
    			"        }\r\n" + 
    			"    }\r\n" + 
    			"}";
    	System.out.println(requestBody);
    	URL url = new URL("https://api.ebay.com/buying/search/v2");
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();

		// Set timeout as per needs
		connection.setConnectTimeout(20000);
		connection.setReadTimeout(20000);

		// Set DoOutput to true if you want to use URLConnection for output.
		// Default is false
		connection.setDoOutput(true);
		connection.setRequestMethod("POST");

		// Set Headers
		connection.setRequestProperty("Content-Type", "application/json");
		connection.setRequestProperty("Accept", "application/json");
		connection.setRequestProperty("X-EBAY-C-MARKETPLACE-ID", "EBAY-UK");
		connection.setRequestProperty("Authorization", "APP EdwardMu-CharityP-PRD-538907625-4999b865");
		
		// Write XML
		OutputStream outputStream = connection.getOutputStream();
		outputStream.write(requestBody.getBytes("UTF-8"));
		outputStream.flush();
		outputStream.close();

		// Read XML
		InputStream inputStream = connection.getInputStream();
		StringBuilder responseBuilder = new StringBuilder();
		byte[] res = new byte[2048];
		int i;
		while ((i = inputStream.read(res)) != -1) {
			responseBuilder.append(new String(res, 0, i));
		}
		inputStream.close();
		System.out.println(responseBuilder.toString());
    	return responseBuilder.toString();
    }
    
    @GET
    @Path("/FindCharityItems")
    @Produces(MediaType.APPLICATION_JSON)
    public String findCharityItems(@QueryParam("charityItemId") String charityItemId) throws IOException {
    	System.out.println("Find Charity Items");
    	String requestBody = "{\r\n" + 
    			"    \"searchRequest\": {\r\n" + 
    			"        \"sortOrder\": \"StartTimeNewest\",\r\n" + 
    			"        \"paginationInput\": {\r\n" + 
    			"            \"pageNumber\": 1,\r\n" + 
    			"            \"entriesPerPage\": 25\r\n" + 
    			"        },\r\n" + 
    			"        \"constraints\": {\r\n" + 
    			"            \"globalAspect\": [\r\n" + 
    			"                {\r\n" + 
    			"                    \"constraintType\": \"CharityIds\",\r\n" + 
    			"                    \"value\": ["+charityItemId+"]\r\n" + 
    			"                    \r\n" + 
    			"                },\r\n" + 
    			"                {\r\n" + 
    			"                    \"constraintType\": \"CharityOnly\",\r\n" + 
    			"                    \"value\": [\"true\"]\r\n" + 
    			"                }\r\n" + 
    			"            ]\r\n" + 
    			"        }\r\n" + 
    			"    }\r\n" + 
    			"}";
    	URL url = new URL("https://api.ebay.com/buying/search/v2");
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();

		// Set timeout as per needs
		connection.setConnectTimeout(20000);
		connection.setReadTimeout(20000);

		// Set DoOutput to true if you want to use URLConnection for output.
		// Default is false
		connection.setDoOutput(true);
		connection.setRequestMethod("POST");

		// Set Headers
		connection.setRequestProperty("Content-Type", "application/json");
		connection.setRequestProperty("Accept", "application/json");
		connection.setRequestProperty("X-EBAY-C-MARKETPLACE-ID", "EBAY-UK");
		connection.setRequestProperty("Authorization", "APP EdwardMu-CharityP-PRD-538907625-4999b865");
		
		// Write XML
		OutputStream outputStream = connection.getOutputStream();
		outputStream.write(requestBody.getBytes("UTF-8"));
		outputStream.flush();
		outputStream.close();

		// Read XML
		InputStream inputStream = connection.getInputStream();
		StringBuilder responseBuilder = new StringBuilder();
		byte[] res = new byte[2048];
		int i;
		while ((i = inputStream.read(res)) != -1) {
			responseBuilder.append(new String(res, 0, i));
		}
		inputStream.close();
		System.out.println(responseBuilder.toString());
    	return responseBuilder.toString();
    }
    
    
    @GET
    @Path("/GetItem")
    @Produces(MediaType.APPLICATION_XML)
    public String getItem(@QueryParam("input")String input) throws IOException {
    	System.out.println("Get Item Method");
    	String requestBody = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\r\n" + 
    			"<GetItemRequest xmlns=\"urn:ebay:apis:eBLBaseComponents\">\r\n" + 
    			"  <RequesterCredentials>\r\n" + 
    			"    <eBayAuthToken>AgAAAA**AQAAAA**aAAAAA**9enaXQ**nY+sHZ2PrBmdj6wVnY+sEZ2PrA2dj6AGmICpDZmDpwWdj6x9nY+seQ**OSoGAA**AAMAAA**mc4q0e94Onut08iyCeBQDC6u1QQokVs4kukRMxe2PSdQ4CsKUIxSGhgFHFraUV0VJ1fLgYmKg6kXDQh0idHTgKjcHFPcuPDmtwT5TSH+SAKHw6TaZkAgVLgOMBprmwgLAJNiTESHarSfuuJxUdILw3lB/rrGC3tQ3sFz8l3SxpCx6NXvuOQ0++3aZ2gB/EPeiT70bR/z87bVkZYoK/OAG/vRCBVch5xo2PiJWJl5xLYpG3iz6aauTiJur3TKC3cPriMLWDkLAOqhXZol9jp2cknPesRfDypOBzDnvECNP81F18t0/3u/Lgn9BWxYdefCm95Rkw3XjZwQTG1GVLqBHoWBpG3s8eLuQhChlbH52ecF7sFb3aSXdvTcOCSwVHMA0GRjPhcoNt91WOfI22tUaJJ7/H72IqosJw235lvgvqQ5UQSzh5BE/Wp0u9bGzpUHgODeRtfO45miC+5itGBs0r1KKjN0CEzQ8WsDzWK2eGmmnznW8f2osEa83C9sa41/dEC5U1Cy8vpMgp/nz+qjKf+wQ3OUsSEgOKrjvC3tZcWUivhyu/GPEhAHEF6XBOTOyMnspoZKWNL4RMxGxfpeG3ANoer4vmdizPK7C6h3eLyTTYfL0jcML9Ld+rFKMD7hVx8ATu32nQVt3GmXa9m8cp2rSNPdgRV36LMWRxW2aXMq+MksRZkNhhm4WxSGUykR3N8K8jFiD9LPrz0pEux8UXEfF8ZWEKlxn2jCn9Tjy1WLMQ5ljotGHt+eMsfkaWoC</eBayAuthToken>\r\n" + 
    			"  </RequesterCredentials>\r\n" + 
    			"  <ItemID>"+ input + "</ItemID>\r\n" + 
    			"</GetItemRequest>";
    	URL url = new URL("https://api.ebay.com/ws/api.dll");
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();

		// Set timeout as per needs
		connection.setConnectTimeout(20000);
		connection.setReadTimeout(20000);

		// Set DoOutput to true if you want to use URLConnection for output.
		// Default is false
		connection.setDoOutput(true);
		connection.setRequestMethod("POST");

		// Set Headers
		connection.setRequestProperty("Content-Type", "application/xml");
		connection.setRequestProperty("X-EBAY-SOA-SECURITY-APPNAME", "EdwardMu-CharityP-PRD-538907625-4999b865");
		connection.setRequestProperty("X-EBAY-API-SITEID", "3");
		connection.setRequestProperty("X-EBAY-API-CALL-NAME", "GetItem");
		connection.setRequestProperty("X-EBAY-API-COMPATIBILITY-LEVEL", "1107");
		connection.setRequestProperty("X-EBAY-API-APP-NAME", "EdwardMu-CharityP-PRD-538907625-4999b865");
		connection.setRequestProperty("X-EBAY-API-DEV-NAME", "f21ad267-e241-4ed0-8943-721fa90bcf3a");
		connection.setRequestProperty("X-EBAY-API-CERT-NAME", "PRD-38907625e4e8-b6b8-4c83-b069-68ac");
		
		// Write XML
		OutputStream outputStream = connection.getOutputStream();
		outputStream.write(requestBody.getBytes("UTF-8"));
		outputStream.flush();
		outputStream.close();

		// Read XML
		InputStream inputStream = connection.getInputStream();
		StringBuilder responseBuilder = new StringBuilder();
		byte[] res = new byte[2048];
		int i;
		while ((i = inputStream.read(res)) != -1) {
			responseBuilder.append(new String(res, 0, i));
		}
		inputStream.close();
    	return responseBuilder.toString();
    }
  
    
    @GET
    @Path("/FindNonProfit")
    @Produces(MediaType.APPLICATION_XML)
    public Response findNonProfit(@QueryParam("nonProfitInput")String nonProfitInput) throws IOException {
    	System.out.println("Find Non Profit Method");
    	String requestBody = 
    			"<findNonprofitRequest xmlns=\"http://www.ebay.com/marketplace/fundraising/v1/services\">\r\n" + 
    			"    <searchFilter>\r\n" + 
    			"        <externalId>"+nonProfitInput+"</externalId>\r\n" + 
    			"    </searchFilter>\r\n" + 
    			"    <outputSelector>Mission</outputSelector>\r\n" + 
    			"    <outputSelector>Address</outputSelector>\r\n" + 
    			"    <outputSelector>LargeLogoURL</outputSelector>\r\n" + 
    			"    <outputSelector>PopularityIndex</outputSelector>\r\n" + 
    			"    <outputSelector>EIN</outputSelector>\r\n" + 
    			"    <outputSelector>Description</outputSelector>\r\n" + 
    			"    <paginationInput>\r\n" + 
    			"        <pageNumber>1</pageNumber>\r\n" + 
    			"        <pageSize>25</pageSize>\r\n" + 
    			"    </paginationInput>\r\n" + 
    			"</findNonprofitRequest>";
    	URL url = new URL("http://svcs.ebay.com/services/fundraising/FundRaisingFindingService/v1");
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();

		// Set timeout as per needs
		connection.setConnectTimeout(20000);
		connection.setReadTimeout(20000);

		// Set DoOutput to true if you want to use URLConnection for output.
		// Default is false
		connection.setDoOutput(true);
		connection.setRequestMethod("POST");

		// Set Headers
		connection.setRequestProperty("Content-Type", "application/xml");
		connection.setRequestProperty("X-EBAY-SOA-SECURITY-APPNAME", "EdwardMu-CharityP-PRD-538907625-4999b865");
		connection.setRequestProperty("X-EBAY-SOA-OPERATION-NAME", "findNonprofit");
		connection.setRequestProperty("X-EBAY-SOA-GLOBAL-ID", "EBAY-GB");
		
		// Write XML
		OutputStream outputStream = connection.getOutputStream();
		outputStream.write(requestBody.getBytes("UTF-8"));
		outputStream.flush();
		outputStream.close();

		// Read XML
		InputStream inputStream = connection.getInputStream();
		StringBuilder responseBuilder = new StringBuilder();
		byte[] res = new byte[2048];
		int i;
		while ((i = inputStream.read(res)) != -1) {
			responseBuilder.append(new String(res, 0, i));
		}
		inputStream.close();
		System.out.println(responseBuilder.toString());
    	return Response.ok().entity(responseBuilder.toString()).build();
    }
}