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

import com.ebay.queens.requests.PaginationInput;
import com.ebay.queens.requests.charityitems.CharityItemRequest;
import com.ebay.queens.requests.charityitems.Constraints;
import com.ebay.queens.requests.charityitems.GlobalAspect;
import com.ebay.queens.requests.charityitems.SearchRequest;
import com.ebay.queens.requests.findnonprofit.*;
import com.ebay.queens.requests.findnonprofit.FindNonProfitRequest;
import com.ebay.queens.requests.getitem.*;
import com.ebay.queens.responses.charityitemresponse.CharityItemResponse;
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
		this.getItem("333460893922");
		//this.findNonProfit("10484");
		this.findCharityItems("10484");
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
		PaginationInput paginationInput = new PaginationInput("1", "1", "25", "", "");
		SearchRequest searchRequest = new SearchRequest("StartTimeNewest", paginationInput); 
		GlobalAspect globalAspect1 = new GlobalAspect("CharityIds", "19790");
		GlobalAspect globalAspect2 = new GlobalAspect("CharityOnly", "true");
		GlobalAspect globalAspectList[] = new GlobalAspect[2]; 
		globalAspectList[0] = globalAspect1;
		globalAspectList[1] = globalAspect2;
		Constraints constraints = new Constraints(globalAspectList);
		CharityItemRequest charityItemRequest = new CharityItemRequest(searchRequest, "phone", constraints);
		String response = httpClass.genericJSONSendPOST("https://api.ebay.com/buying/search/v2", charityItemRequest, "charityItem");
		final ObjectMapper mapper = new ObjectMapper();
		final CharityItemResponse deserializedReqFromJson = mapper.readValue(response, CharityItemResponse.class);
		System.out.println("Deserialized JSON String --> Object");
		System.out.println( deserializedReqFromJson.getCharityItems());
		System.out.println("---------------------------------");
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
		GetItemResponse getItemResponse = null;
		RequestCredentials reqCred = new RequestCredentials(
				"v^1.1#i^1#p^3#I^3#f^0#r^0#t^H4sIAAAAAAAAAOVYa2wUVRTubh8EajUEIgRRl6n4KJndO7Ozrym7ybJd6ALbrrttUYjWuzN32qGzM+vcO20XYlIb5RmEEoMBQmyCMRoepkYSRDTqDzViTECRRBHUmMAPMEFFCUR0ZvtgWxX6IKaJ+2cz557X951z7ty5oKtsatW62nW/V9im2Hu7QJfdZmPKwdSy0gV3FtvnlBaBAgVbb9cDXSXdxecXYphRsnwS4aymYuTozCgq5vPCIGXoKq9BLGNehRmEeSLwqXB8Oc86AZ/VNaIJmkI5YjVBCnp9rD/N+STEiCLHek2pOuizQQtSkih6A9Dv4dJiWnIDwVzH2EAxFROokiDFAhbQgKFZXwPj51mWZzgn43evpBxNSMeyppoqTkCF8unyeVu9INebpwoxRjoxnVChWHhxqj4cq4nWNSx0FfgKDfCQIpAYePhTRBORowkqBrp5GJzX5lOGICCMKVeoP8Jwp3x4MJlxpN9PNSdJEhfwA4lL+zh4W5hcrOkZSG6ehiWRRVrKq/JIJTLJ3YpQk4z0aiSQgac600WsxmH9PWpARZZkpAep6KLw442paJJypBIJXWuXRSRaQBnWz/o9AcYboEIZQxH1ZqONBQw3EKff2QDJIwJFNFWULcqwo04ji5CZNBpJDVNAjalUr9brYYlYCRXosWCQQp9vpVXS/hoapFW1qooyJg+O/OOtCzDYEDda4Ha1BCsiToKQkSQfiwKBf2oJa9bH2hYhqzLhRMJlpYLSMEdnoN6GSFaBAqIFk10jg3RZ5N0eiXX7JUSbQy7RXECS6LRH9NKMhBBAKJ0WAv7/UXcQostpg6ChDhm5kMcYpCxKeRlKPNHakNqQyyJqpGZ+2xloi04cpFoJyfIuV0dHh7PD7dT0FhcLAON6LL48JbSijLkbDOrKt1am5XyHCMi0wjJPzASCVKfZf2ZwtYUKJaOLk9FUbXND/bJo3WDvDsssNFL6L0hTSNARmVzoGJJwNboYBiTD2tIUl8gt1aLyssQa0NSwKN4OIxE3K0TWLFkg1XPBiYEXtCxKaIos5P4bBqxZHy0Lbl1MQJ3kUkhRTMGEgGIL6OQqsmWPTQcwKzutcXMKWsalQXPDtkTN+Ywdo1FyYZMgZ//+Z3p26giKmqrkxmM8BhtZbTf3D03PjSfgkPEYbKAgaIZKxhNuwHQMFpKhSLKiWFvkeAIWmI8lTRUqOSILeFwhZdXqNjwGkyzM5QGKMs5aszIqS1NmvloF5DRfd/mT1lCyI2bRmvWxTWk4m41lMgaBaQXFxMk1rhzgAOuZ0CZkwZtkqKJiB9TFuEFHWqFu1jJBJ5I1tMftDwCfl/WYB6ZAIO33Tgx3vEWeZLCZgJcNeHxePweAe0LYalD7ZKupxDJQZL0+GrEcQ3NIBLQ/wLlpH8tIMADSguSGE8IcUWRz8ocdCkue/WlSYK/VMEHiaNGNEBQciv/2NeQafhkRKsr/mG7bIdBt67PbbMAF5jOVYF5ZcWNJ8R1zsEzMHRJKTiy3qOY3to6cbSiXhbJuL7PJ20+sP1lw/dH7BJg9dAEytZgpL7gNAXNvrJQyd82qsAhhfYyfZRluJai8sVrC3F0ys448v+d6SK0+8dCUI+9Vbz3a/KL6BqgYUrLZSotKum1F926d1/1aw+GdZ1c/OP20d3dw1bT7v/jlfNx2da3j9P5mZhd95oJSvre8fUnk452vdD/jXX9gy5UvT02f2bht2sYdy/dde7208lg7Y4/bL884v+nrP5tmGIevnu3r2Tvz2Nqi1T+3X3ppftmKs1Xff3Th4A8Vje92vXDkeN2UtW+h9K40V+OOtR1u2tYXv7jj2pO55tilyjePPL15/4HrG9euwmgTER2frEt+erxqVfXOPz74jQO932y4OOPDg1uO95R9tqK541flAl116O05ds87m6trN5zc45Sfu3rpkZ57dr/v1789+l33q30P37dg+8ufP3VGrdwwd9Op2XhhZH/rxiuzSo2ec+e+Snbu67v8Y3/5/gIk2DTXmBIAAA==");
		GetItemRequest getItemRequest = new GetItemRequest(reqCred, "333460893922", "ReturnAll");
		String result = httpClass.genericXMLSendPOST("https://api.ebay.com/ws/api.dll", getItemRequest, "getItem");
		System.out.println("Result: " + result);
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(GetItemResponse.class);
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
			getItemResponse = (GetItemResponse) unmarshaller.unmarshal(new StringReader(result));
			System.out.println("Deserialized XML String --> Object");
			System.out.println(getItemResponse.getAck());
			System.out.println(getItemResponse.getBuild());
			System.out.println(getItemResponse.getTimestamp());
			System.out.println(getItemResponse.getItem());
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
	public String findNonProfit(@QueryParam("nonProfitInput") String nonProfitInput)
			throws IOException, JAXBException {
		LOGGER.info("Find Non Profit Method");
		SearchFilter searchFilter = new SearchFilter(nonProfitInput);
		PaginationInput paginationInput = new PaginationInput("1", "25","1","1","1");
		FindNonProfitRequest findNonProfitRequest = new FindNonProfitRequest(searchFilter, paginationInput);
		String response = httpClass.genericXMLSendPOST("http://svcs.ebay.com/services/fundraising/FundRaisingFindingService/v1", findNonProfitRequest, "nonProfit");
		LOGGER.info(response.toString());
		return response;
	}
}