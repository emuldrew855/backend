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

import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.ebay.queens.requests.findnonprofit.PaginationInput;
import com.ebay.queens.requests.charityitems.CharityItemRequest;
import com.ebay.queens.requests.charityitems.Constraints;
import com.ebay.queens.requests.charityitems.GlobalAspect;
import com.ebay.queens.requests.charityitems.SearchRequest;
import com.ebay.queens.requests.findnonprofit.*;
import com.ebay.queens.requests.findnonprofit.FindNonProfitRequest;
import com.ebay.queens.requests.getitem.*;
import com.ebay.queens.responses.charityitemresponse.CharityItemResponse;
import com.ebay.queens.responses.findnonprofitresponse.FindNonProfitResponse;
import com.ebay.queens.responses.findnonprofitresponse.NonProfit;
import com.ebay.queens.responses.getitemresponse.*;
import com.ebay.queens.responses.searchitemresponse.SearchItemResponse;
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
		this.findNonProfit("10484");
		//this.findCharityItems("10484");
		//this.searchItem("drone");
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
		String nonProfitId = response.getNonProfit();
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
	public SearchItemResponse searchItem(@QueryParam("searchTerm") String searchTerm) throws IOException {
		LOGGER.info("Search Item: " + searchTerm);
		String url = "https://api.ebay.com/buy/browse/v1/item_summary/search?q=" + searchTerm;
		String response = httpClass.genericSendGET(url, "searchItem");
		final ObjectMapper mapper = new ObjectMapper(); 
		final SearchItemResponse searchItemResponse = mapper.readValue(response, SearchItemResponse.class);
		System.out.println("Deserialized JSON String --> Object");
		System.out.println(searchItemResponse.getHref());
		System.out.println(searchItemResponse.getHref());
		System.out.println(searchItemResponse.getTotal());
		return searchItemResponse;
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
		String response = httpClass.genericJSONSendPOST(url, requestBody, "charityItem");
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
	public CharityItemResponse findCharityItems(@QueryParam("charityItemId") String charityItemId) throws IOException {
		LOGGER.info("Find Charity Items");
		PaginationInput paginationInput = new PaginationInput("1", "25");
		String[] valueIds = {"88"};
		String[] charityOnly = {"true"};
		GlobalAspect globalAspect1 = new GlobalAspect("CharityIds", valueIds);
		GlobalAspect globalAspect2 = new GlobalAspect("CharityOnly", charityOnly);
		GlobalAspect globalAspectList[] = new GlobalAspect[2]; 
		globalAspectList[0] = globalAspect1;
		globalAspectList[1] = globalAspect2;
		Constraints constraints = new Constraints(globalAspectList);
		SearchRequest searchRequest = new SearchRequest("StartTimeNewest", paginationInput, constraints); 
		CharityItemRequest charityItemRequest = new CharityItemRequest(searchRequest);
		String response = httpClass.genericJSONSendPOST("https://api.ebay.com/buying/search/v2", charityItemRequest, "charityItem");
		LOGGER.info(response);
		final ObjectMapper mapper = new ObjectMapper();
		final CharityItemResponse charityItemResponse = mapper.readValue(response, CharityItemResponse.class);
		System.out.println("Deserialized JSON String --> Object");
		System.out.println( charityItemResponse.getItems());
		System.out.println("---------------------------------");
		return charityItemResponse;
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
		GetItemResponse getItemResponse = null;
		RequesterCredentials reqCred = new RequesterCredentials(
				"AgAAAA**AQAAAA**aAAAAA**ChvUXQ**nY+sHZ2PrBmdj6wVnY+sEZ2PrA2dj6AEkYOiDpeKoQWdj6x9nY+seQ**AeEFAA**AAMAAA**om8bY77e+hYcxwwMwjnSs/PwlO2OBas/H1XmD9BuaRKJT8AqTUApb1bNnd9cpkcHT+ylWjnmiiHa1sHkBNSX8vICwZrMKdhu94xYz4fwl1r88hIUqXfyTpd0lLeKb9PvBTm/6lml/4MX1c5YENvcyRx9Dyan8WOMbIvM6ZsM8mQ7Sw7URdaZCEZwjee1WztIL9AEOUFKKsO9TYjArRZFN0rqGfgXqNdUd1e4BqK7qtNyRScWtHQ4+cJDEU+iH0d8yEGlg4KgJuPn17M0nOoOKzMrrNuwsojaBTYk3GIdPj0Eb18bJhnVvRm4xI138YDizMXEu8PIbAmp+4233hRE4wiOnAa80eMSgqxd1TZuRSOupGPdDIgVoRT+AuXHaXhEMQDkbxitHg4ZnsF9XYC1tpxo0lngZNf3NAFRu1yPdxWXSShaQpLXGcM/KISYvKoxgkYIBq3hn8LG52qA+ARJlo+Wc+Z74volMIFnCz51I8EkYqn3ntR1fuvkmoJL4HEy3cCse9XPPkIlgUk3aYW+i0/bNnLzJVa9N8es4FHsg6f1fyQ5I4feBC//ScF1qbkLOxs/BLL+cylOUKZBEKXxDzq0ieq5p0g9TwT13UTtmjFGhHzCMCOmbfP4S0jki9jCReyWIiIauAKLDbnGEqfk1AYYoZJvqPGVRDHdzU665zgfFrNYNljo0uGGUIqzxOtepQmL5G04uoOCdTanfSKKaKnriBNR35u2SlY6zevZjZ/gwbsQs1WAFkjujrkOPlbz");
		GetItemRequest getItemRequest = new GetItemRequest(reqCred, "333460893922", "ReturnAll");
		String response = httpClass.genericXMLSendPOST("https://api.ebay.com/ws/api.dll", getItemRequest, "getItem");
		System.out.println("Result: " + response);
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(GetItemResponse.class);
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
			getItemResponse = (GetItemResponse) unmarshaller.unmarshal(new StringReader(response));
			System.out.println("Deserialized XML String --> Object");
			System.out.println(getItemResponse.getAck());
			System.out.println(getItemResponse.getBuild());
			System.out.println(getItemResponse.getTimestamp());
			System.out.println(getItemResponse.getItem().getCharity().getCharityName());
			System.out.println("---------------------------------");
		} catch (JAXBException e) {
			LOGGER.error("Failed to deserialize XML.", e);
		}
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
		PaginationInput paginationInput = new PaginationInput("25","1");
		FindNonProfitRequest findNonProfitRequest = new FindNonProfitRequest(searchFilter, paginationInput);
		String response = httpClass.genericXMLSendPOST("http://svcs.ebay.com/services/fundraising/FundRaisingFindingService/v1", findNonProfitRequest, "nonProfit");
		FindNonProfitResponse findNonProfitResponse = new FindNonProfitResponse();
		NonProfit nonProfit[] = null;
		LOGGER.info(response.toString());
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(FindNonProfitResponse.class);
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
			findNonProfitResponse = (FindNonProfitResponse) unmarshaller.unmarshal(new StringReader(response));
			System.out.println("Deserialized XML String --> Object");
			System.out.println(findNonProfitResponse.getAck());
			System.out.println(findNonProfitResponse.getTimestamp());
			System.out.println(findNonProfitResponse.getVersion());
			System.out.println("---------------------------------");
		} catch (JAXBException e) {
			LOGGER.error("Failed to deserialize XML.", e);
		}
		return findNonProfitResponse;
	}
}