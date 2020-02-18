package com.ebay.queens.demo;

import java.io.IOException;
import java.io.StringReader;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.ebay.queens.requests.charityitems.CharityItemRequest;
import com.ebay.queens.requests.charityitems.Constraints;
import com.ebay.queens.requests.charityitems.GlobalAspect;
import com.ebay.queens.requests.charityitems.SearchRequest;
import com.ebay.queens.requests.findnonprofit.FindNonProfitRequest;
import com.ebay.queens.requests.findnonprofit.PaginationInput;
import com.ebay.queens.requests.findnonprofit.SearchFilter;
import com.ebay.queens.requests.getitem.GetItemRequest;
import com.ebay.queens.requests.getitem.RequesterCredentials;
import com.ebay.queens.responses.charityitemresponse.CharityItemResponse;
import com.ebay.queens.responses.findnonprofitresponse.FindNonProfitResponse;
import com.ebay.queens.responses.getitemresponse.GetItemResponse;
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
		LOGGER.info("Version 1 API");
		// TODO Auto-generated method stub
		//this.getItem("333460893922");
		// this.findSingleNonProfit("10484");
		//this.findCharityItems("10484");
		// this.searchItem("drone");
		//this.advancedFindCharityItems("64163");  
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
	@Path("/AdvancedFindCharityItems")
	@Produces(MediaType.APPLICATION_JSON)
	public CharityItemResponse advancedFindCharityItems(@QueryParam("charityId") String charityId)
			throws IOException, JAXBException {
		FindNonProfitResponse findNonProfitResponse = findNonProfit(charityId);
		String nonProfitId = findNonProfitResponse.getNonProfit().getExternalId();
		LOGGER.info("Non Profit Id: " + nonProfitId);
		CharityItemResponse charityItemResponse = findCharityItems(nonProfitId);
		System.out.println("Advanced find charity item: " + charityItemResponse.toString());
		LOGGER.info(charityItemResponse.toString());
		return charityItemResponse;
	}

	/**
	 * Represents an api to return a list of products matching the search term
	 * 
	 * @param searchTerm
	 *            - uses the search term to return a list of products matching the
	 *            search term
	 */
	@GET
	@Path("/SearchItem")
	@Produces(MediaType.APPLICATION_JSON)
	public SearchItemResponse searchItem(@QueryParam("searchTerm") String searchTerm) throws IOException {
		LOGGER.info("Search Item: " + searchTerm);
		String url = "https://api.ebay.com/buy/browse/v1/item_summary/search?q=" + searchTerm;
		String response = httpClass.genericSendGET(url, "searchItem");
		final ObjectMapper mapper = new ObjectMapper();
		final SearchItemResponse searchItemResponse = mapper.readValue(response, SearchItemResponse.class);
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
	@Path("/FindSingleNonProfit")
	@Produces(MediaType.APPLICATION_JSON)
	public FindNonProfitResponse findSingleNonProfit(@QueryParam("charityItemId") String charityItemId) throws IOException {
		LOGGER.info("FindSingleNonProfit: " + charityItemId);
		String requestBody = "<findNonprofitRequest xmlns=\"http://www.ebay.com/marketplace/fundraising/v1/services\">\r\n" + 
				"    <searchFilter>\r\n" + 
				"        <externalId>"+charityItemId+"</externalId>\r\n" + 
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
		String url = "http://svcs.ebay.com/services/fundraising/FundRaisingFindingService/v1";
		String response = httpClass.sendPOST(url, requestBody, "nonProfit");
		FindNonProfitResponse findNonProfitResponse = new FindNonProfitResponse();
		
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(FindNonProfitResponse.class);
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
			findNonProfitResponse = (FindNonProfitResponse) unmarshaller.unmarshal(new StringReader(response));
		} catch (JAXBException e) {
			LOGGER.error("Failed to deserialize XML.", e);
		}
		return findNonProfitResponse;
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
		String[] valueIds = { "88" };
		String[] charityOnly = { "true" };
		GlobalAspect globalAspect1 = new GlobalAspect("CharityIds", valueIds);
		GlobalAspect globalAspect2 = new GlobalAspect("CharityOnly", charityOnly);
		GlobalAspect globalAspectList[] = new GlobalAspect[2];
		globalAspectList[0] = globalAspect1;
		globalAspectList[1] = globalAspect2;
		Constraints constraints = new Constraints(globalAspectList);
		SearchRequest searchRequest = new SearchRequest("StartTimeNewest", paginationInput, constraints);
		CharityItemRequest charityItemRequest = new CharityItemRequest(searchRequest);
		String response = httpClass.genericJSONSendPOST("https://api.ebay.com/buying/search/v2", charityItemRequest,
				"charityItem");
		LOGGER.info(response);
		final ObjectMapper mapper = new ObjectMapper();
		final CharityItemResponse charityItemResponse = mapper.readValue(response, CharityItemResponse.class);
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
	@Path("/GetItem")
	@Produces(MediaType.APPLICATION_XML)
	public GetItemResponse getItem(@QueryParam("input") String input) throws IOException, JAXBException {
		LOGGER.info("Get Item Method: " + input);
		GetItemResponse getItemResponse = null;
		RequesterCredentials reqCred = new RequesterCredentials(
				"AgAAAA**AQAAAA**aAAAAA**ChvUXQ**nY+sHZ2PrBmdj6wVnY+sEZ2PrA2dj6AEkYOiDpeKoQWdj6x9nY+seQ**AeEFAA**AAMAAA**om8bY77e+hYcxwwMwjnSs/PwlO2OBas/H1XmD9BuaRKJT8AqTUApb1bNnd9cpkcHT+ylWjnmiiHa1sHkBNSX8vICwZrMKdhu94xYz4fwl1r88hIUqXfyTpd0lLeKb9PvBTm/6lml/4MX1c5YENvcyRx9Dyan8WOMbIvM6ZsM8mQ7Sw7URdaZCEZwjee1WztIL9AEOUFKKsO9TYjArRZFN0rqGfgXqNdUd1e4BqK7qtNyRScWtHQ4+cJDEU+iH0d8yEGlg4KgJuPn17M0nOoOKzMrrNuwsojaBTYk3GIdPj0Eb18bJhnVvRm4xI138YDizMXEu8PIbAmp+4233hRE4wiOnAa80eMSgqxd1TZuRSOupGPdDIgVoRT+AuXHaXhEMQDkbxitHg4ZnsF9XYC1tpxo0lngZNf3NAFRu1yPdxWXSShaQpLXGcM/KISYvKoxgkYIBq3hn8LG52qA+ARJlo+Wc+Z74volMIFnCz51I8EkYqn3ntR1fuvkmoJL4HEy3cCse9XPPkIlgUk3aYW+i0/bNnLzJVa9N8es4FHsg6f1fyQ5I4feBC//ScF1qbkLOxs/BLL+cylOUKZBEKXxDzq0ieq5p0g9TwT13UTtmjFGhHzCMCOmbfP4S0jki9jCReyWIiIauAKLDbnGEqfk1AYYoZJvqPGVRDHdzU665zgfFrNYNljo0uGGUIqzxOtepQmL5G04uoOCdTanfSKKaKnriBNR35u2SlY6zevZjZ/gwbsQs1WAFkjujrkOPlbz");
		GetItemRequest getItemRequest = new GetItemRequest(reqCred, input, "ReturnAll");
		System.out.println(getItemRequest);
		String response = httpClass.genericXMLSendPOST("https://api.ebay.com/ws/api.dll", getItemRequest, "getItem");
		System.out.println("Result: " + response);
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(GetItemResponse.class);
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
			getItemResponse = (GetItemResponse) unmarshaller.unmarshal(new StringReader(response));
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
	@Path("/FindNonProfit")
	@Produces(MediaType.APPLICATION_XML)
	public FindNonProfitResponse findNonProfit(@QueryParam("nonProfitInput") String nonProfitInput)
			throws IOException, JAXBException {
		LOGGER.info("Find Non Profit Method");
		SearchFilter searchFilter = new SearchFilter(nonProfitInput);
		PaginationInput paginationInput = new PaginationInput("25", "1");
		FindNonProfitRequest findNonProfitRequest = new FindNonProfitRequest(searchFilter, paginationInput);
		String response = httpClass.genericXMLSendPOST(
				"http://svcs.ebay.com/services/fundraising/FundRaisingFindingService/v1", findNonProfitRequest,
				"nonProfit");
		FindNonProfitResponse findNonProfitResponse = new FindNonProfitResponse();
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(FindNonProfitResponse.class);
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
			findNonProfitResponse = (FindNonProfitResponse) unmarshaller.unmarshal(new StringReader(response));
		} catch (JAXBException e) {
			LOGGER.error("Failed to deserialize XML.", e);
		}
		return findNonProfitResponse;
	}
}