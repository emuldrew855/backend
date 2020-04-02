package com.ebay.queens.demo;

import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.logging.Logger;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.ebay.queens.responses.PaypalTokenResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Represents a class to manage all HTTP Get and post requests    
 */
@Component
public class Http implements CommandLineRunner {
	private ExternalConfig externalConfig;

	private Logger logger;
	@Autowired
	private Utilities utilityClass = new Utilities("","","","","","");
	
	@Autowired
	Http(ExternalConfig externalConfig) {
		logger = Utilities.LOGGER;
		logger.info("HTTP Class");
		this.externalConfig = externalConfig;
	}
	@Override
	public void run(String... args) throws Exception {
		utilityClass.setDevName(externalConfig.getDeveloperName());
		utilityClass.setSecurityAppName(externalConfig.getSecurityAppName());
		utilityClass.setCertName(externalConfig.getCertName());
		utilityClass.setGlobalId(externalConfig.getGlobalId());
		utilityClass.setPaypalAppId(externalConfig.getPaypalAppId());
	}
	
	/**
	 * Method used to get the refresh token to gain access to ebay's api again
	 * @param url - uses url to reach specific api point
	 * @param typeOfCall  - typeOfCall is used to add specific headers to call
	 */
	public void ebayRefreshTokenPost(String url,String typeOfCall)
			throws IOException {
		CloseableHttpClient client = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost(url);
		ArrayList<BasicNameValuePair> nvps = new ArrayList<BasicNameValuePair>();
		nvps.add(new BasicNameValuePair("grant_type", Login.activeUser.getUserRefreshToken()));
		nvps.add(new BasicNameValuePair("code", Login.activeUser.getUserRefreshToken()));
		nvps.add(new BasicNameValuePair("redirect_uri", Login.activeUser.getUserRefreshToken()));
		httpPost.setEntity(new UrlEncodedFormEntity(nvps, "UTF-8"));
		httpPost = selectHeader(httpPost, typeOfCall);
		CloseableHttpResponse response = client.execute(httpPost);
		String result = EntityUtils.toString(response.getEntity());
		logger.info("Ebay Refresh Token result: " + result);
		client.close();
	}
	
	/**
	 * Represents a method make a generic http post request to reach the api's
	 * @param url - uses url to reach specific api point
	 * @param requestBody- requestBody makes up the request that will be sent.
	 * @param typeOfCall  - typeOfCall is used to add specific headers to call
	 */
	public <T> String genericJSONSendPOST(String url, T request, String typeOfCall) throws IOException {
		// Object to JSON
		final ObjectMapper mapper = new ObjectMapper();
		String requestJSONString = mapper.writeValueAsString(request);
		logger.info(requestJSONString);

		CloseableHttpClient client = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost(url);
		httpPost.setEntity(new StringEntity(requestJSONString));
		httpPost = selectHeader(httpPost, typeOfCall);
		CloseableHttpResponse response = client.execute(httpPost);
		String result = EntityUtils.toString(response.getEntity());
		client.close();

		return result.toString();
	}
	
	/**
	 * Represents a method to make a generic http post requests to reach the api's
	 * 
	 * @param url - uses url to reach specific api point
	 * @param typeOfCall - used to add specific headers based of type of cvall
	 *            -
	 * @return- returns response from post method
	 */
	public String sendPOST(String url, String request, String typeOfCall) throws IOException {
		CloseableHttpClient client = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost(url);
		httpPost.setEntity(new StringEntity(request));
		httpPost = selectHeader(httpPost, typeOfCall);
		CloseableHttpResponse response = client.execute(httpPost);
		String result = EntityUtils.toString(response.getEntity());
		client.close();

		return result.toString();
	}

	/**
	 * Represents a method to make a generic http get requests to reach the api's
	 * 
	 * @param url - uses url to reach specific api point
	 * @param typeOfCall
	 *            -
	 */
	public String genericSendGET(String url, String typeOfCall) throws IOException {
		CloseableHttpClient client = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet(url);
		httpGet = selectHttpGet(httpGet, typeOfCall);
		CloseableHttpResponse response = client.execute(httpGet);
		String result = EntityUtils.toString(response.getEntity());
		if(result.contains("errors")) {
			logger.severe("Error in making request due to expired authentication token");
		}
		client.close();

		return result.toString();
	}
	
	/**
	 * Represents a method make a generic http post request to reach the api's
	 * 
	 * @param url - uses url to reach specific api point
	 * @param request  - request makes up the request that will be sent.
	 * @param typeOfCall  - typeOfCall is used to add specific headers to call
	 */
	public <T> String genericXMLSendPOST(String url, T request, String typeOfCall) throws IOException {
		// Object to XML
		String requestXMLString = "";
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(request.getClass());
			Marshaller marshaller = jaxbContext.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			System.out.println("Serialized Object --> XML String ");
			marshaller.marshal(request, System.out);
			StringWriter sw = new StringWriter();
			marshaller.marshal(request, sw);
			requestXMLString = sw.toString();
			System.out.println("---------------------------------");
		} catch (JAXBException e) {
			logger.severe("Failed to serialize XML." + e.toString());
		}

		CloseableHttpClient client = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost(url);
		httpPost.setEntity(new StringEntity(requestXMLString));
		httpPost = selectHeader(httpPost, typeOfCall);
		CloseableHttpResponse response = client.execute(httpPost);
		String result = EntityUtils.toString(response.getEntity());
		client.close();

		return result.toString();
	}

	/**
	 * Represents a method to make a call to Paypal's api to set the authorization token
	 * @param url - uses url to reach specific api point
	 * @param typeOfCall - typeOfCall is used to add specific headers to call
	 * @return - returns PaypalTokenResponse object
	 */
	public PaypalTokenResponse paypalAuthenticationPost(String url, String typeOfCall)
			throws IOException {
		CloseableHttpClient client = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost(url);
		ArrayList<BasicNameValuePair> nvps = new ArrayList<BasicNameValuePair>();
		nvps.add(new BasicNameValuePair("grant_type", "client_credentials"));
		httpPost.setEntity(new UrlEncodedFormEntity(nvps, "UTF-8"));
		httpPost = selectHeader(httpPost, typeOfCall);
		CloseableHttpResponse response = client.execute(httpPost);
		String result = EntityUtils.toString(response.getEntity());
		final ObjectMapper mapper = new ObjectMapper();
		final PaypalTokenResponse paypalTokenResponse = mapper.readValue(result, PaypalTokenResponse.class);
		client.close();
		return paypalTokenResponse;
	}
	
	/**
	 * Method adds headers to HttpPost object
	 * @param HttpPost -sends in the HttpPost object to add headers to 
	 * @param TypeOfCall - typeOfCall is used to add specific headers to call
	 * @return - returns HttPost Object
	 */
	public HttpPost selectHeader(HttpPost httpPost, String typeOfCall) {
		switch (typeOfCall) {
		case "nonProfit":
			httpPost.addHeader("Content-Type", "application/xml");
			httpPost.addHeader("X-EBAY-SOA-OPERATION-NAME", "findNonprofit");
			httpPost.addHeader("X-EBAY-SOA-GLOBAL-ID", utilityClass.getGlobalId());
			httpPost.addHeader("X-EBAY-SOA-SECURITY-APPNAME", utilityClass.getSecurityAppName());
			break;
		case "getItem":
			httpPost.addHeader("Content-Type", "application/xml");
			httpPost.addHeader("X-EBAY-API-SITEID", "0");
			httpPost.addHeader("X-EBAY-API-CALL-NAME", "GetItem");
			httpPost.addHeader("X-EBAY-API-COMPATIBILITY-LEVEL", "1107");
			httpPost.addHeader("X-EBAY-API-APP-NAME", utilityClass.getSecurityAppName());
			httpPost.addHeader("X-EBAY-API-DEV-NAME", utilityClass.getDevName());
			httpPost.addHeader("X-EBAY-API-CERT-NAME", utilityClass.getCertName());
			break;
		case "charityItem":
			httpPost.addHeader("Content-Type", "application/json");
			httpPost.addHeader("Accept", "application/json");
			httpPost.addHeader("X-EBAY-C-MARKETPLACE-ID", utilityClass.getGlobalId());
			httpPost.addHeader("Authorization", "APP " + utilityClass.getSecurityAppName());
			break;
		case "searchItem":
			httpPost.addHeader("Content-Type", "application/json");
			httpPost.addHeader("X-EBAY-C-ENDUSERCTX",
					"contextualLocation=country=<2CharCountryCode>,zip=<5DigitCode>,affiliateCampaignId=<ePNCampaignId>,affiliateReferenceId=<referenceId>");
			httpPost.addHeader("X-EBAY-C-MARKETPLACE", utilityClass.getMarktplaceId());
			httpPost.addHeader("Authorization", "Bearer " + Login.activeUser.getEbayAuthToken());
			break;
		case "Paypal":
			httpPost.addHeader("Content-Type", "application/json");
			httpPost.addHeader("Authorization", ("Bearer " + Login.activeUser.getPaypalAuthToken()));
			break;
		case "PaypalAuth":
			httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded");
			httpPost.addHeader("Authorization",
					("Basic QWJwMkQzNjI4X3U0emJPNEVhcVlxSDBHbkNqX0xqdVlwYXlscVQ3ampkZEFFeWRTSFdVYWpPVGJiY1J6X0RfTDZnX2REd2VDQ0t3VE5tQ1o6RU1XdWtTNWpmZlpwa2lKOE54X3NnUDNzRDFqVEstejh6dDVnUlg3czk4VFdnOVZRUFBjMkZWNFJLS0ZMWk82azJJa3FHalRNUmZHZmFyMFk="));
		default:
			logger.info("Default value");
			break;
		}
		return httpPost;
	}
	
	/**
	 * Method adds headers to HttpGet object
	 * @param HttpPost -sends in the HttpGetobject to add headers to 
	 * @param TypeOfCall - typeOfCall is used to add specific headers to call
	 * @return - returns HttGetObject
	 */
	public HttpGet selectHttpGet(HttpGet httpGet, String typeOfCall) {
		switch (typeOfCall) {
		case "nonProfit":
			httpGet.addHeader("Content-Type", "application/xml");
			httpGet.addHeader("X-EBAY-SOA-OPERATION-NAME", "findNonprofit");
			httpGet.addHeader("X-EBAY-SOA-GLOBAL-ID", utilityClass.getGlobalId());
			httpGet.addHeader("X-EBAY-SOA-SECURITY-APPNAME", utilityClass.getSecurityAppName());
			break;
		case "getItem":
			httpGet.addHeader("Content-Type", "application/xml");
			httpGet.addHeader("X-EBAY-SOA-SECURITY-APPNAME", "EdwardMu-CharityP-PRD-538907625-4999b865");
			httpGet.addHeader("X-EBAY-API-SITEID", "3");
			httpGet.addHeader("X-EBAY-API-CALL-NAME", "GetItem");
			httpGet.addHeader("X-EBAY-API-COMPATIBILITY-LEVEL", "1107");
			httpGet.addHeader("X-EBAY-API-APP-NAME", utilityClass.getSecurityAppName());
			httpGet.addHeader("X-EBAY-API-DEV-NAME", utilityClass.getDevName());
			httpGet.addHeader("X-EBAY-API-CERT-NAME", utilityClass.getCertName());
			break;
		case "charityItem":
			httpGet.addHeader("Content-Type", "application/json");
			httpGet.addHeader("Accept", "application/json");
			httpGet.addHeader("X-EBAY-C-MARKETPLACE-ID", utilityClass.getGlobalId());
			httpGet.addHeader("Authorization", "APP " + utilityClass.getSecurityAppName());
			break;
		case "searchItem":
			httpGet.addHeader("Content-Type", "application/json");
			httpGet.addHeader("X-EBAY-C-ENDUSERCTX",
					"contextualLocation=country=<2CharCountryCode>,zip=<5DigitCode>,affiliateCampaignId=<ePNCampaignId>,affiliateReferenceId=<referenceId>");
			httpGet.addHeader("X-EBAY-C-MARKETPLACE", utilityClass.getMarktplaceId());
			httpGet.addHeader("Authorization", "Bearer " + Login.activeUser.getEbayAuthToken());
			break;
		case "ebayRefreshToken": 
			httpGet.addHeader("Content-Type","application/x-www-form-urlencoded");
			httpGet.addHeader("Authorization","Baisc " + "<"+ utilityClass.getSecurityAppName()+ ":" + utilityClass.getCertName() +">");
			break;
		case "Paypal":
			httpGet.addHeader("Content-Type", "application/json");
			httpGet.addHeader("Authorization", ("Bearer " + Login.activeUser.getPaypalAuthToken()));
			break;
		case "PaypalAuth":
			httpGet.addHeader("Content-Type", "application/x-www-form-urlencoded");
			httpGet.addHeader("Authorization",
					("Basic QWJwMkQzNjI4X3U0emJPNEVhcVlxSDBHbkNqX0xqdVlwYXlscVQ3ampkZEFFeWRTSFdVYWpPVGJiY1J6X0RfTDZnX2REd2VDQ0t3VE5tQ1o6RU1XdWtTNWpmZlpwa2lKOE54X3NnUDNzRDFqVEstejh6dDVnUlg3czk4VFdnOVZRUFBjMkZWNFJLS0ZMWk82azJJa3FHalRNUmZHZmFyMFk="));
		default:
			logger.info("Default value");
			break;
		}
		return httpGet;
	}

}
