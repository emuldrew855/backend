package com.ebay.queens.demo;

import java.io.IOException;
import java.io.StringReader;
import java.util.logging.Logger;

import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
 * Represents a class to access and manage all of the eBays api's
 */
@Component
@Order(2)
@RestController
@RequestMapping("/ebay")
public class Ebay implements CommandLineRunner {
	final ObjectMapper mapper = new ObjectMapper();
	private Logger LOGGER;

	@Autowired
	private Http httpClass;
	
	
	@Override
	public void run(String... args) throws Exception {
		// Method may be used to test code locally directly from source class
		LOGGER = Utilities.LOGGER;
		LOGGER.info("Ebay API");
	}
	

	/**
	 * Represents an api to search for the nonprofit id which is then used to bring
	 * back all the charity items sold by that nonprofit
	 * 
	 * @param charityId
	 *            - charityId is a String input of numbers using the nonprofitid
	 *            used to get information on the charity to obtain the external id
	 *            to return a list of products
	 * @return CharityItemResponse object which is a JSON object containing a list
	 *         of items which the charity sells
	 * @throws JAXBException, IOException
	 */
	@GET
	@PostMapping("/AdvancedFindCharityItems")
	@Produces(MediaType.APPLICATION_JSON)
	public CharityItemResponse advancedFindCharityItems(@QueryParam("charityId") String charityId)
			throws IOException, JAXBException {
		FindNonProfitResponse findNonProfitResponse = findSingleNonProfit(charityId);
		String nonProfitId = findNonProfitResponse.getNonProfit().getExternalId();
		LOGGER.info("Non Profit Id: " + nonProfitId);
		CharityItemResponse charityItemResponse = findCharityItems(nonProfitId);
		System.out.println("Advanced find charity item: " + charityItemResponse.toString());
		LOGGER.info(charityItemResponse.toString());
		return charityItemResponse;
	}

	/**
	 * Represents an api to return products from multiple charities.
	 * 
	 * @param needs
	 *            to accept an a charity item id
	 * 
	 * @return needs to return a CharityItemResponse which is JSON response of
	 *         products related to that specific charity
	 * 
	 * @throws IOException
	 */
	@GET
	@PostMapping("/findcharityItems")
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
		final CharityItemResponse charityItemResponse = mapper.readValue(response, CharityItemResponse.class);
		return charityItemResponse;
	}

	/**
	 * Represents an api to bring information on non profits [NOT CURRENTLY WORKING OR UNSURE OF FUNCTIONAL PURPOSE YET]
	 * 
	 * @param nonProfitInput
	 *            - uses the externalId to bring up information on nonprofits
	 * 
	 * @returns - a list of non profits.
	 * 
	 * @throws JAXBException
	 */
	@GET
	@PostMapping("/FindNonProfit")
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
			LOGGER.severe("Failed to deserialize XML." + e.toString());
		}
		return findNonProfitResponse;
	}

	/**
	 * Represents an api to return infomation about a given charity/nonprofit
	 * 
	 * @param charityItemId
	 *            - uses a String of numbers which is the nonprofit id to return
	 *            details about the given charity/nonprofit
	 * 
	 * @return - returns a FindNonProfitResponse object containing an XML response
	 *         of charity details
	 * 
	 * @throws IOException
	 */
	@GET
	@PostMapping("/FindSingleNonProfit")
	@Produces(MediaType.APPLICATION_JSON)
	public FindNonProfitResponse findSingleNonProfit(@QueryParam("charityItemId") String charityItemId)
			throws IOException {
		LOGGER.info("FindSingleNonProfit: " + charityItemId);
		String requestBody = "<findNonprofitRequest xmlns=\"http://www.ebay.com/marketplace/fundraising/v1/services\">\r\n"
				+ "    <searchFilter>\r\n" + "        <externalId>" + charityItemId + "</externalId>\r\n"
				+ "    </searchFilter>\r\n" + "    <outputSelector>Mission</outputSelector>\r\n"
				+ "    <outputSelector>Address</outputSelector>\r\n"
				+ "    <outputSelector>LargeLogoURL</outputSelector>\r\n"
				+ "    <outputSelector>PopularityIndex</outputSelector>\r\n"
				+ "    <outputSelector>EIN</outputSelector>\r\n"
				+ "    <outputSelector>Description</outputSelector>\r\n" + "    <paginationInput>\r\n"
				+ "        <pageNumber>1</pageNumber>\r\n" + "        <pageSize>25</pageSize>\r\n"
				+ "    </paginationInput>\r\n" + "</findNonprofitRequest>";
		String url = "http://svcs.ebay.com/services/fundraising/FundRaisingFindingService/v1";
		String response = httpClass.sendPOST(url, requestBody, "nonProfit");
		FindNonProfitResponse findNonProfitResponse = new FindNonProfitResponse();

		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(FindNonProfitResponse.class);
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
			findNonProfitResponse = (FindNonProfitResponse) unmarshaller.unmarshal(new StringReader(response));
		} catch (JAXBException e) {
			LOGGER.severe("Failed to deserialize XML." + e.toString());
		}
		return findNonProfitResponse;
	}

	/**
	 * Represents an api to return specific product information about a specific
	 * product
	 * 
	 * @param input
	 *            - accepts an item id
	 * 
	 * @returns a GetItemResponse object which contains an xml response of details
	 *          about a specific choosen product
	 * 
	 * @throws JAXBException
	 */
	@GET
	@PostMapping("/GetItem")
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
			LOGGER.severe("Failed to deserialize XML."+ e.toString());
		}
		return getItemResponse;
	}
	
	/**
	 * Represents an api to return a list of products matching the search term
	 * 
	 * @param searchTerm
	 *            - uses a string input search term to return a list of products
	 *            matching the search term
	 * 
	 * @return - SearchItemResponse objects which contains a list of items relating
	 *         to the input search terms
	 * 
	 * @throws -
	 *             IOException
	 */
	@GET
	@GetMapping("/SearchItem")
	@Produces(MediaType.APPLICATION_JSON)
	public SearchItemResponse searchItem(@QueryParam("searchTerm") String searchTerm,@QueryParam("limitOffset") String limitOffset) throws IOException {

		if(searchTerm.contains(" ")) {
				searchTerm = searchTerm.replaceAll("\\s", "%20");
				LOGGER.info(searchTerm);
		}
		LOGGER.info("Search Item: " + searchTerm);
		String url = "https://api.ebay.com/buy/browse/v1/item_summary/search?q=" + searchTerm+"&limit="+ limitOffset + "&offset=" + limitOffset;
		String response = httpClass.genericSendGET(url, "searchItem");
		LOGGER.info(response.toString());
		final SearchItemResponse searchItemResponse = mapper.readValue(response, SearchItemResponse.class);
		return searchItemResponse;
	}
}