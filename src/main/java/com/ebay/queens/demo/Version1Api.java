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

import com.ebay.queens.requests.GetItem;
import com.ebay.queens.requests.GetItemRequest;
import com.ebay.queens.requests.RequestCredentials;
import com.ebay.queens.requests.SampleRequest;
import com.ebay.queens.requests.SampleSubRequest;
import com.ebay.queens.responses.GetItemResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Represents a class to access and hit 
 * all of the eBays api's 
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
  }

  
  @Autowired
  private Http httpClass;

  public static void main(String[] args) throws IOException {
    GetItem test = new GetItem();
    //advancedFindCharityItems("1726");
    //getItem("333460893922");
    LOGGER.info("Testing");

/*     Sample Object Mapper (Object --> JSON String) Usage 
    final ObjectMapper mapper = new ObjectMapper();
    final SampleRequest req = new SampleRequest();
    final SampleSubRequest subReq = new SampleSubRequest();
    req.setName("Ethan");
    req.setNum(100);
    req.setSubReq(subReq);
    subReq.setFlag(true);
    subReq.setSubName("Rubinson");
    System.out.println("Serialized Object --> JSON String");
    System.out.println(mapper.writeValueAsString(req));
    System.out.println("---------------------------------");

     Sample Object Mapper (JSON String --> Object) Usage 
    final String rawJsonStr = "{\"num\":100,\"name\":\"Ethan\",\"subReq\":{\"subName\":\"Rubinson\",\"flag\":true}}";
    final SampleRequest deserializedReqFromJson = mapper.readValue(rawJsonStr, SampleRequest.class);
    System.out.println("Deserialized JSON String --> Object");
    System.out.println(deserializedReqFromJson.getName());
    System.out.println(deserializedReqFromJson.getNum());
    System.out.println(deserializedReqFromJson.getSubReq().getSubName());
    System.out.println(deserializedReqFromJson.getSubReq().isFlag());
    System.out.println("---------------------------------");

    try {
      JAXBContext jaxbContext = JAXBContext.newInstance(SampleRequest.class);
      Marshaller marshaller = jaxbContext.createMarshaller();
      marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
      System.out.println("Serialized Object --> XML String ");
      marshaller.marshal(deserializedReqFromJson, System.out);
      System.out.println("---------------------------------");
    } catch (JAXBException e) {
      LOGGER.error("Failed to serialize XML.", e);
    }

    try {
      JAXBContext jaxbContext = JAXBContext.newInstance(SampleRequest.class);
      final String rawXmlStr = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><SampleRequest2><name>Ethan</name><num>100</num><subReq><flag>true</flag><subName>Rubinson</subName></subReq></SampleRequest2>";
      Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
      final SampleRequest deserializedReqFromXml = (SampleRequest) unmarshaller.unmarshal(new StringReader(rawXmlStr));
      System.out.println("Deserialized XML String --> Object");
      System.out.println(deserializedReqFromXml.getName());
      System.out.println(deserializedReqFromXml.getNum());
      System.out.println(deserializedReqFromXml.getSubReq().getSubName());
      System.out.println(deserializedReqFromXml.getSubReq().isFlag());
      System.out.println("---------------------------------");
    } catch (JAXBException e) {
      LOGGER.error("Failed to deserialize XML.", e);
    }*/
  }

  /**
    * Represents an api to search for the nonprofit id which is then used to bring back the charity items 
    * @param charityId - charityId is used to get information on the charity to obtain the external id to return a list of products
    */
  @GET
  @Path("/advancedfindcharityItems")
  @Produces(MediaType.APPLICATION_JSON)
  public String advancedFindCharityItems(@QueryParam("charityId") String charityId) throws IOException {
    String response = findNonProfit(charityId);
    String nonProfitId = response.substring(response.indexOf("nonprofitId") + 12, response.indexOf("nonprofitId") + 16);
    LOGGER.info("Non Profit Id: " + nonProfitId);
    String response2 = findCharityItems(nonProfitId);
    LOGGER.info("Response: " + response2);
    return response2;
  }

  /**
    * Represents an api to return a list of products matching the search term 
    * @param searchTerm - uses the search term to return a list of products matching the search term
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
    * Represents an api to return products from a specific charity with a specific listing type
    * @param charityItemId - uses the charity item id to return list of products/inventory for that specific charity
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
        + charityItemId
        + "]\r\n"
        + "                },\r\n"
        + "                {\r\n"
        + "                    \"constraintType\": \"CharityOnly\",\r\n"
        + "                    \"value\": [\"true\"]\r\n"
        + "                },\r\n"
        + "                {\r\n"
        + "                    \"constraintType\": \"ListingType\",\r\n"
        + "                    \"value\": [\""
        + listingType
        + "\"],\r\n"
        + "                    \"paramNameValue\": [\r\n"
        + "                        {\r\n"
        + "                            \"name\": \"operator\",\r\n"
        + "                            \"value\": \"exclusive\"\r\n"
        + "                        }\r\n"
        + "                    ]\r\n"
        + "                }\r\n"
        + "            ]\r\n"
        + "        }\r\n"
        + "    }\r\n"
        + "}";
    LOGGER.info(requestBody);
    String url = "https://api.ebay.com/buying/search/v2";
    String response = httpClass.genericSendPOST(url, requestBody, "charityItem");
    return response;
  }

  /**
   * Represents an api to return products from a specific charity
   * @param charityItemId - uses the charity item id to return list of products/inventory for that specific charity
   */
  @GET
  @Path("/findcharityItems")
  @Produces(MediaType.APPLICATION_JSON)
  public String findCharityItems(@QueryParam("charityItemId") String charityItemId) throws IOException {
    LOGGER.info("Find Charity Items");
    String requestBody = "{\r\n" + "    \"searchRequest\": {\r\n" + "        \"sortOrder\": \"StartTimeNewest\",\r\n"
        + "        \"paginationInput\": {\r\n" + "            \"pageNumber\": 1,\r\n"
        + "            \"entriesPerPage\": 25\r\n" + "        },\r\n" + "        \"constraints\": {\r\n"
        + "            \"globalAspect\": [\r\n" + "                {\r\n"
        + "                    \"constraintType\": \"CharityIds\",\r\n" + "                    \"value\": ["
        + charityItemId + "]\r\n" + "                    \r\n" + "                },\r\n" + "                {\r\n"
        + "                    \"constraintType\": \"CharityOnly\",\r\n"
        + "                    \"value\": [\"true\"]\r\n" + "                }\r\n" + "            ]\r\n"
        + "        }\r\n" + "    }\r\n" + "}";
    String url = "https://api.ebay.com/buying/search/v2";
    LOGGER.info("Request body: " + requestBody);
    String response = httpClass.genericSendPOST(url, requestBody, "charityItem");
    return response.toString();
  }

  /**
   * Represents an api to return specific product information 
   * @param input - uses the input to search for a specific product in the itemId field
 * @throws JAXBException 
   */
  @GET
  @Path("/getItem")
  @Produces(MediaType.APPLICATION_XML)
  public GetItemResponse getItem(@QueryParam("input") String input) throws IOException, JAXBException {
    LOGGER.info("Get Item Method");
	RequestCredentials reqCred = new RequestCredentials("v^1.1#i^1#r^0#f^0#I^3#p^3#t^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");
	GetItemRequest getItemRequest = new GetItemRequest(reqCred,"333460893922","ReturnAll"); 
	GetItem obj = new GetItem();
	GetItemResponse getItemResponse = obj.sendMessage(getItemRequest);
    return getItemResponse;
  }

  /**
   * Represents an api to bring information on non profits 
   * @param nonProfitInput - uses the externalId to bring up information on nonprofit 
   */
  @GET
  @Path("/findnonProfit")
  @Produces(MediaType.APPLICATION_XML)
  public String findNonProfit(@QueryParam("nonProfitInput") String nonProfitInput) throws IOException {
    LOGGER.info("Find Non Profit Method");
    String requestBody = "<findNonprofitRequest xmlns=\"http://www.ebay.com/marketplace/fundraising/v1/services\">\r\n"
        + "    <searchFilter>\r\n" + "        <externalId>" + nonProfitInput + "</externalId>\r\n"
        + "    </searchFilter>\r\n" + "    <outputSelector>Mission</outputSelector>\r\n"
        + "    <outputSelector>Address</outputSelector>\r\n" + "    <outputSelector>LargeLogoURL</outputSelector>\r\n"
        + "    <outputSelector>PopularityIndex</outputSelector>\r\n" + "    <outputSelector>EIN</outputSelector>\r\n"
        + "    <outputSelector>Description</outputSelector>\r\n" + "    <paginationInput>\r\n"
        + "        <pageNumber>1</pageNumber>\r\n" + "        <pageSize>25</pageSize>\r\n"
        + "    </paginationInput>\r\n" + "</findNonprofitRequest>";
    String url = ("http://svcs.ebay.com/services/fundraising/FundRaisingFindingService/v1");
    String response = httpClass.genericSendPOST(url, requestBody, "nonProfit");
    LOGGER.info(response.toString());
    return response.toString();
  }
}