package com.ebay.queens.demo;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import java.io.IOException;
@CrossOrigin
@Component
@Path("/v1")
public class Version1Api {
	
	Http httpClass = new Http();
	
	public static void main(String [] args) throws IOException {
		advancedFindCharityItems("1726");
	}
	
	@GET
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	public static String advancedFindCharityItems(@QueryParam("charityId") String charityId) throws IOException {
		String response = findNonProfit(charityId);
		System.out.println(response);
		return "";
	}

	@GET
	@Path("/SearchItem")
	@Produces(MediaType.APPLICATION_JSON)
	public static String searchItem(@QueryParam("searchTerm") String searchTerm) throws IOException {
		System.out.println("Search Item");
    	System.out.println("Search Term: " + searchTerm );
    	String url = "https://api.ebay.com/buy/browse/v1/item_summary/search?q=" + searchTerm;
    	String response = Http.genericSendGET(url, "searchItem");
    	return response;
	}
	
    @GET
    @Path("/AdvancedCharityItems")
    @Produces(MediaType.APPLICATION_JSON)
    public static String advancedCharitySearch(@QueryParam("charityItemId") String charityItemId, @QueryParam("listingType") String listingType) throws IOException {
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
    	String url = "https://api.ebay.com/buying/search/v2";
    	String response = Http.genericSendPOST(url, requestBody, "charityItem");
    	return response;
    }
    
    @GET
    @Path("/FindCharityItems")
    @Produces(MediaType.APPLICATION_JSON)
    public static String findCharityItems(@QueryParam("charityItemId") String charityItemId) throws IOException {
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
    	String url = "https://api.ebay.com/buying/search/v2";
    	String response = Http.genericSendPOST(url, requestBody, "charityItem");
    	return response.toString();
    }
    
    
    @GET
    @Path("/GetItem")
    @Produces(MediaType.APPLICATION_XML)
    public static String getItem(@QueryParam("input")String input) throws IOException {
    	System.out.println("Get Item Method");
    	String requestBody = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\r\n" + 
    			"<GetItemRequest xmlns=\"urn:ebay:apis:eBLBaseComponents\">\r\n" + 
    			"  <RequesterCredentials>\r\n" + 
    			"    <eBayAuthToken>AgAAAA**AQAAAA**aAAAAA**9enaXQ**nY+sHZ2PrBmdj6wVnY+sEZ2PrA2dj6AGmICpDZmDpwWdj6x9nY+seQ**OSoGAA**AAMAAA**mc4q0e94Onut08iyCeBQDC6u1QQokVs4kukRMxe2PSdQ4CsKUIxSGhgFHFraUV0VJ1fLgYmKg6kXDQh0idHTgKjcHFPcuPDmtwT5TSH+SAKHw6TaZkAgVLgOMBprmwgLAJNiTESHarSfuuJxUdILw3lB/rrGC3tQ3sFz8l3SxpCx6NXvuOQ0++3aZ2gB/EPeiT70bR/z87bVkZYoK/OAG/vRCBVch5xo2PiJWJl5xLYpG3iz6aauTiJur3TKC3cPriMLWDkLAOqhXZol9jp2cknPesRfDypOBzDnvECNP81F18t0/3u/Lgn9BWxYdefCm95Rkw3XjZwQTG1GVLqBHoWBpG3s8eLuQhChlbH52ecF7sFb3aSXdvTcOCSwVHMA0GRjPhcoNt91WOfI22tUaJJ7/H72IqosJw235lvgvqQ5UQSzh5BE/Wp0u9bGzpUHgODeRtfO45miC+5itGBs0r1KKjN0CEzQ8WsDzWK2eGmmnznW8f2osEa83C9sa41/dEC5U1Cy8vpMgp/nz+qjKf+wQ3OUsSEgOKrjvC3tZcWUivhyu/GPEhAHEF6XBOTOyMnspoZKWNL4RMxGxfpeG3ANoer4vmdizPK7C6h3eLyTTYfL0jcML9Ld+rFKMD7hVx8ATu32nQVt3GmXa9m8cp2rSNPdgRV36LMWRxW2aXMq+MksRZkNhhm4WxSGUykR3N8K8jFiD9LPrz0pEux8UXEfF8ZWEKlxn2jCn9Tjy1WLMQ5ljotGHt+eMsfkaWoC</eBayAuthToken>\r\n" + 
    			"  </RequesterCredentials>\r\n" + 
    			"  <ItemID>"+ input + "</ItemID>\r\n" + 
    			"</GetItemRequest>";
    	String url = "https://api.ebay.com/ws/api.dll";
    	String response = Http.genericSendPOST(url, requestBody, "getItem");
    	return response.toString();
    }
    
    @GET
    @Path("/FindNonProfit")
    @Produces(MediaType.APPLICATION_XML)
    public static String findNonProfit(@QueryParam("nonProfitInput")String nonProfitInput) throws IOException {
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
    	String url = ("http://svcs.ebay.com/services/fundraising/FundRaisingFindingService/v1");
    	String response = Http.genericSendPOST(url,requestBody,"nonProfit");
    	System.out.println(response.toString());
    	return response.toString();
    }
}