package com.ebay.queens.demo;

import java.io.IOException;
import java.io.StringWriter;
import java.net.URISyntaxException;
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

import com.ebay.queens.requests.getitem.GetItem;
import com.ebay.queens.responses.PaypalTokenResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Represents a class to manage all HTTP Get and post requests
 * 
 * @param <T>
 */
@Component
public class Http<T> implements CommandLineRunner {
	private ExternalConfig externalConfig;
	private Logger logger;

	@Autowired
	Http(ExternalConfig externalConfig) {
		logger = Utilities.LOGGER;
		logger.info("HTTP Class");
		System.out.println(externalConfig.getCertName());
		this.externalConfig = externalConfig;
	}
	@Autowired
	private Utilities utilityClass = new Utilities("test", "test", "EBAY-US", "EBAY-UK", "test");

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		utilityClass.setDevName(externalConfig.getDeveloperName());
		utilityClass.setSecurityAppName(externalConfig.getSecurityAppName());
		utilityClass.setCertName(externalConfig.getCertName());
		utilityClass.setGlobalId("EBAY-US");
	}

	/**
	 * Represents a method to make a call to Paypal's api to set the authorization
	 * token
	 * 
	 * @param url
	 *            - uses url to reach specific api point
	 * @param requestBody
	 *            - requestBody makes up the request that will be sent.
	 * @param typeOfCall
	 *            - typeOfCall is used to add specific headers to call
	 */
	public PaypalTokenResponse authenticationPost(String url, String requestBody, String typeOfCall)
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
	 * Represents a method make a generic http post request to reach the api's
	 * 
	 * @param url
	 *            - uses url to reach specific api point
	 * @param requestBody
	 *            - requestBody makes up the request that will be sent.
	 * @param typeOfCall
	 *            - typeOfCall is used to add specific headers to call
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
	 * Represents a method make a generic http post request to reach the api's
	 * 
	 * @param url
	 *            - uses url to reach specific api point
	 * @param requestBody
	 *            - requestBody makes up the request that will be sent.
	 * @param typeOfCall
	 *            - typeOfCall is used to add specific headers to call
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
	 * Represents a method to make a generic http get requests to reach the api's
	 * 
	 * @param url
	 *            - uses url to reach specific api point
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
			httpGet.addHeader("X-EBAY-C-ENDUSERCTX",
					"contextualLocation=country=<2CharCountryCode>,zip=<5DigitCode>,affiliateCampaignId=<ePNCampaignId>,affiliateReferenceId=<referenceId>");
			httpGet.addHeader("X-EBAY-C-MARKETPLACE", utilityClass.marktplaceId);
			httpGet.addHeader("Authorization", "Bearer " + Login.activeUser.getEbayAuthToken());
			break;
		case "Paypal":
			httpGet.addHeader("Content-Type", "application/json");
			httpGet.addHeader("Authorization", ("Bearer " + Login.activeUser.getPaypalAuthToken()));
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
			httpPost.addHeader("X-EBAY-API-SITEID", "0");
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
			httpPost.addHeader("X-EBAY-C-ENDUSERCTX",
					"contextualLocation=country=<2CharCountryCode>,zip=<5DigitCode>,affiliateCampaignId=<ePNCampaignId>,affiliateReferenceId=<referenceId>");
			httpPost.addHeader("X-EBAY-C-MARKETPLACE", utilityClass.marktplaceId);
			httpPost.addHeader("Authorization", "Bearer " + Login.activeUser.getEbayAuthToken());
			break;
		case "Paypal":
			httpPost.addHeader("Content-Type", "application/json");
			httpPost.addHeader("Authorization", ("Bearer " + Login.activeUser.getPaypalAuthToken()));
			break;
		default:
			logger.info("Default value");
			break;
		}

		return httpPost;
	}

}
