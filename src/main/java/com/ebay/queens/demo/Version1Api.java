package com.ebay.queens.demo;

import java.io.IOException;
import java.io.StringReader;
import java.util.Arrays;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.ebay.queens.requests.PaginationInput;
import com.ebay.queens.requests.findnonprofit.*;
import com.ebay.queens.requests.findnonprofit.FindNonProfitRequest;
import com.ebay.queens.requests.getitem.*;
import com.ebay.queens.responses.findnonprofitresponse.FindNonProfitResponse;
import com.ebay.queens.responses.getitemresponse.*;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Represents a class to access and hit all of the eBays api's
 */
@Component
@Path("/v1")
public class Version1Api implements CommandLineRunner {

	private static final Logger LOGGER = LoggerFactory.getLogger(Version1Api.class);

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		LOGGER.info("Testing");
		//this.getItem("333460893922");
	}

	@Autowired
	private Http httpClass;

	public static void main(String[] args) throws IOException {
		
	}

	/**
	 * Represents an api to search for the nonprofit id which is then used to bring
	 * back the charity items
	 * 
	 * @param charityId
	 *            - charityId is used to get information on the charity to obtain
	 *            the external id to return a list of products
	 * @throws JAXBException 
	 */
	@GET
	@Path("/advancedfindcharityItems")
	@Produces(MediaType.APPLICATION_JSON)
	public String advancedFindCharityItems(@QueryParam("charityId") String charityId) throws IOException, JAXBException {
		FindNonProfitResponse response = findNonProfit(charityId);
		String nonProfitId = response.getNonProfit().getNonProfitId();
		LOGGER.info("Non Profit Id: " + nonProfitId);
		String response2 = findCharityItems(nonProfitId);
		LOGGER.info("Response: " + response2);
		return response2;
	}

	/**
	 * Represents an api to return a list of products matching the search term
	 * 
	 * @param searchTerm
	 *            - uses the search term to return a list of products matching the
	 *            search term
	 */
	@GET
	@Path("/searchItem")
	@Produces(MediaType.APPLICATION_JSON)
	public String searchItem(@QueryParam("searchTerm") String searchTerm) throws IOException {
		LOGGER.info("Search Item: " + searchTerm);
		String url = "https://api.ebay.com/buy/browse/v1/item_summary/search?q=" + searchTerm;
		String response = httpClass.genericSendGET(url, "searchItem");
		return response;
	}

	/**
	 * Represents an api to return products from a specific charity with a specific
	 * listing type
	 * 
	 * @param charityItemId
	 *            - uses the charity item id to return list of products/inventory
	 *            for that specific charity
	 */
	@GET
	@Path("/advancedcharityItems")
	@Produces(MediaType.APPLICATION_JSON)
	public String advancedCharitySearch(@QueryParam("charityItemId") String charityItemId,
			@QueryParam("listingType") String listingType) throws IOException {
		LOGGER.info("Advanced Charity Search");
		LOGGER.info("Charity Item Id: " + charityItemId + " Listing Type: " + listingType);
		String requestBody = "{\r\n" + "    \"searchRequest\": {\r\n" + "        \"sortOrder\": \"BestMatch\",\r\n"
				+ "        \"paginationInput\": {\r\n" + "            \"pageNumber\": 1,\r\n"
				+ "            \"entriesPerPage\": 5\r\n" + "        },\r\n" + "        \"keyword\": \"phone\",\r\n"
				+ "        \"constraints\": {\r\n" + "            \"globalAspect\": [\r\n" + "                {\r\n"
				+ "                    \"constraintType\": \"CharityIds\",\r\n" + "                    \"value\": ["
				+ charityItemId + "]\r\n" + "                },\r\n" + "                {\r\n"
				+ "                    \"constraintType\": \"CharityOnly\",\r\n"
				+ "                    \"value\": [\"true\"]\r\n" + "                },\r\n" + "                {\r\n"
				+ "                    \"constraintType\": \"ListingType\",\r\n" + "                    \"value\": [\""
				+ listingType + "\"],\r\n" + "                    \"paramNameValue\": [\r\n"
				+ "                        {\r\n" + "                            \"name\": \"operator\",\r\n"
				+ "                            \"value\": \"exclusive\"\r\n" + "                        }\r\n"
				+ "                    ]\r\n" + "                }\r\n" + "            ]\r\n" + "        }\r\n"
				+ "    }\r\n" + "}";
		LOGGER.info(requestBody);
		String url = "https://api.ebay.com/buying/search/v2";
		String response = httpClass.genericSendPOST(url, requestBody, "charityItem");
		return response;
	}

	/**
	 * Represents an api to return products from a specific charity
	 * 
	 * @param charityItemId
	 *            - uses the charity item id to return list of products/inventory
	 *            for that specific charity
	 */
	@GET
	@Path("/findcharityItems")
	@Produces(MediaType.APPLICATION_JSON)
	public String findCharityItems(@QueryParam("charityItemId") String charityItemId) throws IOException {
		LOGGER.info("Find Charity Items");
		String requestBody = "{\r\n" + "    \"searchRequest\": {\r\n"
				+ "        \"sortOrder\": \"StartTimeNewest\",\r\n" + "        \"paginationInput\": {\r\n"
				+ "            \"pageNumber\": 1,\r\n" + "            \"entriesPerPage\": 25\r\n" + "        },\r\n"
				+ "        \"constraints\": {\r\n" + "            \"globalAspect\": [\r\n" + "                {\r\n"
				+ "                    \"constraintType\": \"CharityIds\",\r\n" + "                    \"value\": ["
				+ charityItemId + "]\r\n" + "                    \r\n" + "                },\r\n"
				+ "                {\r\n" + "                    \"constraintType\": \"CharityOnly\",\r\n"
				+ "                    \"value\": [\"true\"]\r\n" + "                }\r\n" + "            ]\r\n"
				+ "        }\r\n" + "    }\r\n" + "}";
		String url = "https://api.ebay.com/buying/search/v2";
		LOGGER.info("Request body: " + requestBody);
		String response = httpClass.genericSendPOST(url, requestBody, "charityItem");
		return response.toString();
	}

	/**
	 * Represents an api to return specific product information
	 * 
	 * @param input
	 *            - uses the input to search for a specific product in the itemId
	 *            field
	 * @throws JAXBException
	 */
	@GET
	@Path("/getItem")
	@Produces(MediaType.APPLICATION_XML)
	public GetItemResponse getItem(@QueryParam("input") String input) throws IOException, JAXBException {
		LOGGER.info("Get Item Method");
		RequestCredentials reqCred = new RequestCredentials(
				"v^1.1#i^1#r^0#f^0#I^3#p^3#t^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");
		GetItemRequest getItemRequest = new GetItemRequest(reqCred, "333460893922", "ReturnAll");
		GetItem obj = new GetItem();
		GetItemResponse getItemResponse = obj.sendMessage(getItemRequest);
		return getItemResponse;
	}

	/**
	 * Represents an api to bring information on non profits
	 * 
	 * @param nonProfitInput
	 *            - uses the externalId to bring up information on nonprofit
	 * @throws JAXBException
	 */
	@GET
	@Path("/findnonProfit")
	@Produces(MediaType.APPLICATION_XML)
	public FindNonProfitResponse findNonProfit(@QueryParam("nonProfitInput") String nonProfitInput)
			throws IOException, JAXBException {
		LOGGER.info("Find Non Profit Method");
		SearchFilter searchFilter = new SearchFilter(nonProfitInput);
		PaginationInput paginationInput = new PaginationInput("1", "25","","","");
		FindNonProfitRequest findNonProfitRequest = new FindNonProfitRequest(searchFilter, paginationInput);
		FindNonProfit findNonProfit = new FindNonProfit();
		FindNonProfitResponse result = findNonProfit.sendMessage(findNonProfitRequest);
		String requestBody = "<findNonprofitRequest xmlns=\"http://www.ebay.com/marketplace/fundraising/v1/services\">\r\n"
				+ "    <searchFilter>\r\n" + "        <externalId>" + nonProfitInput + "</externalId>\r\n"
				+ "    </searchFilter>\r\n" + "    <outputSelector>Mission</outputSelector>\r\n"
				+ "    <outputSelector>Address</outputSelector>\r\n"
				+ "    <outputSelector>LargeLogoURL</outputSelector>\r\n"
				+ "    <outputSelector>PopularityIndex</outputSelector>\r\n"
				+ "    <outputSelector>EIN</outputSelector>\r\n"
				+ "    <outputSelector>Description</outputSelector>\r\n" + "    <paginationInput>\r\n"
				+ "        <pageNumber>1</pageNumber>\r\n" + "        <pageSize>25</pageSize>\r\n"
				+ "    </paginationInput>\r\n" + "</findNonprofitRequest>";
		String url = ("http://svcs.ebay.com/services/fundraising/FundRaisingFindingService/v1");
		String response = httpClass.genericSendPOST(url, requestBody, "nonProfit");
		LOGGER.info(response.toString());
		return result;
	}
}