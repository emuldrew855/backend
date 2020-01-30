package com.ebay.queens.demo;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.net.URISyntaxException;
import java.util.ArrayList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.apache.http.HttpResponse;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.ebay.queens.requests.getitem.GetItem;
import com.ebay.queens.requests.getitem.GetItemRequest;
import com.ebay.queens.responses.PaypalTokenResponse;
import com.ebay.queens.responses.getitemresponse.GetItemResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Represents a class to manage all HTTP Get and post requests  
 * @param <T>
 */
@Component
public class Http<T> implements CommandLineRunner {
  private static final Logger LOGGER = LoggerFactory.getLogger(GetItem.class);

  private ExternalConfig externalConfig;

  @Autowired
  Http(ExternalConfig externalConfig) {
    System.out.println(externalConfig.getCertName());
    this.externalConfig = externalConfig;
  }

  private static final org.slf4j.Logger logger = LoggerFactory.getLogger(Version1Api.class);

  public Utilities utilityClass = new Utilities(
      "test",
      "test",
      "EBAY-US",
      "EBAY-UK",
      "test",
      "A21AAEU40OMhVHxkTsHNK9SfMUh75C-6KiYycLLogklKFxG_YKYz5pxDAK3H34A3NYT_y_5XMTJjkf0sjzK4jC1HLykcGMiFg",
      "v^1.1#i^1#r^0#p^3#I^3#f^0#t^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");

  @Override
  public void run(String... args) throws Exception {
    // TODO Auto-generated method stub
    utilityClass.setDevName(externalConfig.getDeveloperName());
    utilityClass.setSecurityAppName(externalConfig.getSecurityAppName());
    utilityClass.setCertName(externalConfig.getCertName());
    utilityClass.setEbayAuth(externalConfig.getEbayAuth());
  }

  public static void main(String[] args) throws IOException, URISyntaxException {
    logger.info("HTTP Class");
  }

  public PaypalTokenResponse authenticationPost(String url, String requestBody, String typeOfCall) throws IOException {
    CloseableHttpClient client = HttpClients.createDefault();
    HttpPost httpPost = new HttpPost(url);
    ArrayList<BasicNameValuePair> nvps = new ArrayList<BasicNameValuePair>();
    nvps.add(new BasicNameValuePair("grant_type", "client_credentials"));
    httpPost.setEntity(new UrlEncodedFormEntity(nvps, "UTF-8"));
    httpPost = selectHeader(httpPost, typeOfCall);
    CloseableHttpResponse response = client.execute(httpPost);
    String result = EntityUtils.toString(response.getEntity());
	System.out.println("Result: " + result);
	final ObjectMapper mapper = new ObjectMapper();
	final PaypalTokenResponse paypalTokenResponse = mapper.readValue(result, PaypalTokenResponse.class);
	System.out.println("Deserialized JSON String --> Object");
	System.out.println(paypalTokenResponse.getScope());
	System.out.println(paypalTokenResponse.getAccessToken());
	System.out.println(paypalTokenResponse.getExpiresIn());
    client.close();
    utilityClass.setPaypalAuthorizationToken(paypalTokenResponse.getAccessToken());
    return paypalTokenResponse;
  }

  /**
    * Represents a method make a generic http post request to reach the api's
    * @param url - uses url to reach specific api point
    * @param requestBody - requestBody makes up the request that will be sent. 
    * @param typeOfCall - typeOfCall is used to add specific headers to call
    */
  public <T> String genericXMLSendPOST(String url, T request, String typeOfCall) throws IOException {
	// Object to XML
	String getItemRequestXmlString = "";
	try {
		JAXBContext jaxbContext = JAXBContext.newInstance(request.getClass());
		Marshaller marshaller = jaxbContext.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		System.out.println("Serialized Object --> XML String ");
		marshaller.marshal(request, System.out);
		StringWriter sw = new StringWriter();
		marshaller.marshal(request, sw);
		getItemRequestXmlString = sw.toString();
		System.out.println("---------------------------------");
	} catch (JAXBException e) {
		LOGGER.error("Failed to serialize XML.", e);
	}

	CloseableHttpClient client = HttpClients.createDefault();
	HttpPost httpPost = new HttpPost(url);
    httpPost.setEntity(new StringEntity(getItemRequestXmlString));
    httpPost = selectHeader(httpPost, typeOfCall);
    CloseableHttpResponse response = client.execute(httpPost);
    String result = EntityUtils.toString(response.getEntity());
    client.close();

    return result.toString();
  }
  
  /**
   * Represents a method make a generic http post request to reach the api's
   * @param url - uses url to reach specific api point
   * @param requestBody - requestBody makes up the request that will be sent. 
   * @param typeOfCall - typeOfCall is used to add specific headers to call
   */
 public <T> String genericJSONSendPOST(String url, T request, String typeOfCall) throws IOException {
   // Object to JSON
   final ObjectMapper mapper = new ObjectMapper();
   System.out.println("Serialized Object --> JSON String");
   String getItemRequestJSONString = mapper.writeValueAsString(request);
   System.out.println(getItemRequestJSONString);
   System.out.println("---------------------------------");

   CloseableHttpClient client = HttpClients.createDefault();
   HttpPost httpPost = new HttpPost(url);
   httpPost.setEntity(new StringEntity(getItemRequestJSONString));
   httpPost = selectHeader(httpPost, typeOfCall);
   CloseableHttpResponse response = client.execute(httpPost);
   String result = EntityUtils.toString(response.getEntity());
   client.close();

   return result.toString();
 }

  /**
     * Represents a method to make a generic http get requests to reach the api's
     * @param url - uses url to reach specific api point
     * @param typeOfCall - 
     */
  public String genericSendGET(String url, String typeOfCall) throws IOException {
    CloseableHttpClient client = HttpClients.createDefault();
    HttpGet httpGet = new HttpGet(url);
    httpGet = selectHttpGet(httpGet, typeOfCall);
    CloseableHttpResponse response = client.execute(httpGet);
    String result = EntityUtils.toString(response.getEntity());
    client.close();

    return result.toString();
  }

  public HttpGet selectHttpGet(HttpGet httpGet, String typeOfCall) {
    switch (typeOfCall) {
    case "nonProfit":
      httpGet.addHeader("Content-Type", "application/xml");
      httpGet.addHeader("X-EBAY-SOA-OPERATION-NAME", "findNonprofit");
      httpGet.addHeader("X-EBAY-SOA-GLOBAL-ID", utilityClass.globalId);
      httpGet.addHeader("X-EBAY-SOA-SECURITY-APPNAME", utilityClass.securityAppName);
      break;
    case "getItem":
      httpGet.addHeader("Content-Type", "application/xml");
      httpGet.addHeader("X-EBAY-SOA-SECURITY-APPNAME", "EdwardMu-CharityP-PRD-538907625-4999b865");
      httpGet.addHeader("X-EBAY-API-SITEID", "3");
      httpGet.addHeader("X-EBAY-API-CALL-NAME", "GetItem");
      httpGet.addHeader("X-EBAY-API-COMPATIBILITY-LEVEL", "1107");
      httpGet.addHeader("X-EBAY-API-APP-NAME", utilityClass.securityAppName);
      httpGet.addHeader("X-EBAY-API-DEV-NAME", utilityClass.devName);
      httpGet.addHeader("X-EBAY-API-CERT-NAME", utilityClass.certName);
      break;
    case "charityItem":
      httpGet.addHeader("Content-Type", "application/json");
      httpGet.addHeader("Accept", "application/json");
      httpGet.addHeader("X-EBAY-C-MARKETPLACE-ID", utilityClass.marktplaceId);
      httpGet.addHeader("Authorization", "APP " + utilityClass.securityAppName);
      break;
    case "searchItem":
      httpGet.addHeader("Content-Type", "application/json");
      httpGet
          .addHeader(
              "X-EBAY-C-ENDUSERCTX",
              "contextualLocation=country=<2CharCountryCode>,zip=<5DigitCode>,affiliateCampaignId=<ePNCampaignId>,affiliateReferenceId=<referenceId>");
      httpGet.addHeader("X-EBAY-C-MARKETPLACE", utilityClass.marktplaceId);
      httpGet.addHeader("Authorization", "Bearer " + utilityClass.ebayAuth);
      break;
    case "Paypal":
      httpGet.addHeader("Content-Type", "application/json");
      httpGet.addHeader("Authorization", ("Bearer " + utilityClass.paypalAuthorizationToken));
      break;
    case "PaypalAuth":
      httpGet.addHeader("Content-Type", "application/x-www-form-urlencoded");
      httpGet
          .addHeader(
              "Authorization",
              ("Basic QWJwMkQzNjI4X3U0emJPNEVhcVlxSDBHbkNqX0xqdVlwYXlscVQ3ampkZEFFeWRTSFdVYWpPVGJiY1J6X0RfTDZnX2REd2VDQ0t3VE5tQ1o6RU1XdWtTNWpmZlpwa2lKOE54X3NnUDNzRDFqVEstejh6dDVnUlg3czk4VFdnOVZRUFBjMkZWNFJLS0ZMWk82azJJa3FHalRNUmZHZmFyMFk="));
      break;
    default:
      logger.info("Default value");
      break;
    }

    return httpGet;
  }

  public HttpPost selectHeader(HttpPost httpPost, String typeOfCall) {
    switch (typeOfCall) {
    case "nonProfit":
      httpPost.addHeader("Content-Type", "application/xml");
      httpPost.addHeader("X-EBAY-SOA-OPERATION-NAME", "findNonprofit");
      httpPost.addHeader("X-EBAY-SOA-GLOBAL-ID", utilityClass.globalId);
      httpPost.addHeader("X-EBAY-SOA-SECURITY-APPNAME", utilityClass.securityAppName);
      break;
    case "getItem":
      httpPost.addHeader("Content-Type", "application/xml");
      httpPost.addHeader("X-EBAY-API-SITEID", "3");
      httpPost.addHeader("X-EBAY-API-CALL-NAME", "GetItem");
      httpPost.addHeader("X-EBAY-API-COMPATIBILITY-LEVEL", "1107");
      httpPost.addHeader("X-EBAY-API-APP-NAME", utilityClass.securityAppName);
      httpPost.addHeader("X-EBAY-API-DEV-NAME", utilityClass.devName);
      httpPost.addHeader("X-EBAY-API-CERT-NAME", utilityClass.certName);
      break;
    case "charityItem":
      httpPost.addHeader("Content-Type", "application/json");
      httpPost.addHeader("Accept", "application/json");
      httpPost.addHeader("X-EBAY-C-MARKETPLACE-ID", utilityClass.marktplaceId);
      httpPost.addHeader("Authorization", "APP " + utilityClass.securityAppName);
      break;
    case "searchItem":
      httpPost.addHeader("Content-Type", "application/json");
      httpPost
          .addHeader(
              "X-EBAY-C-ENDUSERCTX",
              "contextualLocation=country=<2CharCountryCode>,zip=<5DigitCode>,affiliateCampaignId=<ePNCampaignId>,affiliateReferenceId=<referenceId>");
      httpPost.addHeader("X-EBAY-C-MARKETPLACE", utilityClass.marktplaceId);
      httpPost.addHeader("Authorization", "Bearer " + utilityClass.ebayAuth);
      break;
    case "Paypal":
      httpPost.addHeader("Content-Type", "application/json");
      httpPost.addHeader("Authorization", ("Bearer " + utilityClass.paypalAuthorizationToken));
      break;
    case "PaypalAuth":
      httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded");
      httpPost
          .addHeader(
              "Authorization",
              ("Basic QWJwMkQzNjI4X3U0emJPNEVhcVlxSDBHbkNqX0xqdVlwYXlscVQ3ampkZEFFeWRTSFdVYWpPVGJiY1J6X0RfTDZnX2REd2VDQ0t3VE5tQ1o6RU1XdWtTNWpmZlpwa2lKOE54X3NnUDNzRDFqVEstejh6dDVnUlg3czk4VFdnOVZRUFBjMkZWNFJLS0ZMWk82azJJa3FHalRNUmZHZmFyMFk="));
      break;
    default:
      logger.info("Default value");
      break;
    }

    return httpPost;
  }

}
