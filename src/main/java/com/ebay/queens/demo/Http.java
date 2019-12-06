package com.ebay.queens.demo;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.Header;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class Http {
	Http() {
		
	}
	
	public static Utilities utilityClass = new Utilities("f21ad267-e241-4ed0-8943-721fa90bcf3a","EdwardMu-CharityP-PRD-538907625-4999b865", "EBAY-GB","EBAY-UK",  "PRD-38907625e4e8-b6b8-4c83-b069-68ac");
	
	public static void main(String[] args) throws IOException, URISyntaxException {
		System.out.println("HTTP Class");
		String requestBody = "<findNonprofitRequest xmlns=\"http://www.ebay.com/marketplace/fundraising/v1/services\">\r\n" + 
    			"    <searchFilter>\r\n" + 
    			"        <externalId>"+"10484"+"</externalId>\r\n" + 
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
	//	System.out.println(sendPOST("http://svcs.ebay.com/services/fundraising/FundRaisingFindingService/v1",requestBody));
		System.out.println( "RESPONSE: " + genericSendPOST("http://svcs.ebay.com/services/fundraising/FundRaisingFindingService/v1",requestBody,"nonProfit"));
	}
	
	
	public static ResponseEntity<String> sendPOST(String url, String requestBody) throws IOException, URISyntaxException {	
		 RestTemplate restTemplate = new RestTemplate();
		    final String baseUrl = url;
		    URI uri = new URI(baseUrl);
		    String request = requestBody;  
		 
		    HttpEntity<String> request2 = new HttpEntity<String>(request, utilityClass.nonProfitHeaders); 
		    ResponseEntity<String> result = restTemplate.exchange(uri, HttpMethod.POST,request2, String.class);
		    String responseBody = result.getBody();	
        return result;
    }

	public static String genericSendPOST(String url, String requestBody, String typeOfCall) throws IOException {	
		CloseableHttpClient client = HttpClients.createDefault();
	    HttpPost httpPost = new HttpPost(url);
	    httpPost.setEntity(new StringEntity(requestBody));
	    httpPost = selectHeader(httpPost, typeOfCall);
	    CloseableHttpResponse response = client.execute(httpPost);
        String result = EntityUtils.toString(response.getEntity());
	    client.close();
	    
       return result.toString();
   }
	
	public static HttpPost selectHeader(HttpPost httpPost,String typeOfCall) {
	    switch(typeOfCall) {
	    case "nonProfit" : 
	    	httpPost.addHeader("Content-Type", "application/xml");
	    	httpPost.addHeader("X-EBAY-SOA-OPERATION-NAME", "findNonprofit");
	    	httpPost.addHeader("X-EBAY-SOA-GLOBAL-ID", utilityClass.globalId);
	    	httpPost.addHeader("X-EBAY-SOA-SECURITY-APPNAME", utilityClass.securityAppName);
	    	break; 
	    case "getItem" :
	    	httpPost.addHeader("Content-Type", "application/xml");
	    	httpPost.addHeader("X-EBAY-SOA-SECURITY-APPNAME", "EdwardMu-CharityP-PRD-538907625-4999b865");
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
	    	httpPost.addHeader("Authorization", "APP "+ utilityClass.securityAppName);
	    	break;
	    default: 
	    	System.out.println("Default value");
	    	break;
	    }
	    
	    return httpPost;
	}

}

