package com.ebay.queens.demo;

import java.awt.List;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.Header;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.apache.http.NameValuePair;

public class Http {
	Http() {
		
	}
	
	public static Utilities utilityClass = new Utilities("f21ad267-e241-4ed0-8943-721fa90bcf3a","EdwardMu-CharityP-PRD-538907625-4999b865",
			"EBAY-US","EBAY-UK",  "PRD-38907625e4e8-b6b8-4c83-b069-68ac", "A21AAEU40OMhVHxkTsHNK9SfMUh75C-6KiYycLLogklKFxG_YKYz5pxDAK3H34A3NYT_y_5XMTJjkf0sjzK4jC1HLykcGMiFg",
			"v^1.1#i^1#r^0#I^3#p^3#f^0#t^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");
	
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
		System.out.println( "RESPONSE: " + genericSendPOST("http://svcs.ebay.com/services/fundraising/FundRaisingFindingService/v1",requestBody,"nonProfit"));
	}
	
		
	public static String authenticationPost(String url, String requestBody, String typeOfCall) throws IOException {
		CloseableHttpClient client = HttpClients.createDefault();
	    HttpPost httpPost = new HttpPost(url);
		ArrayList<BasicNameValuePair> nvps = new ArrayList<BasicNameValuePair>();
	    nvps.add(new BasicNameValuePair("grant_type","client_credentials"));
	    httpPost.setEntity(new UrlEncodedFormEntity(nvps, "UTF-8"));
	    httpPost = selectHeader(httpPost, typeOfCall);
	    CloseableHttpResponse response = client.execute(httpPost);
        String result = EntityUtils.toString(response.getEntity());
	    client.close();
    	String authToken = result.substring(result.indexOf("access_token")+15, result.indexOf("access_token")+ 112);
    	utilityClass.setPaypalAuthorizationToken(authToken);
    	System.out.println("Authtoken: " + authToken);
       return result.toString();
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
	
	public static String genericSendGET(String url, String typeOfCall) throws IOException {	
		CloseableHttpClient client = HttpClients.createDefault();
	    HttpGet httpGet = new HttpGet(url);
	    httpGet = selectHttpGet(httpGet, typeOfCall);
	    CloseableHttpResponse response = client.execute(httpGet);
        String result = EntityUtils.toString(response.getEntity());
	    client.close();
	    
       return result.toString();
   }
	
	public static HttpGet selectHttpGet(HttpGet httpGet,String typeOfCall) {
	    switch(typeOfCall) {
	    case "nonProfit" : 
	    	httpGet.addHeader("Content-Type", "application/xml");
	    	httpGet.addHeader("X-EBAY-SOA-OPERATION-NAME", "findNonprofit");
	    	httpGet.addHeader("X-EBAY-SOA-GLOBAL-ID", utilityClass.globalId);
	    	httpGet.addHeader("X-EBAY-SOA-SECURITY-APPNAME", utilityClass.securityAppName);
	    	break; 
	    case "getItem" :
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
	    	httpGet.addHeader("Authorization", "APP "+ utilityClass.securityAppName);
	    	break;
	    case "searchItem":
	    	httpGet.addHeader("Content-Type", "application/json");
	    	httpGet.addHeader("X-EBAY-C-ENDUSERCTX", "contextualLocation=country=<2CharCountryCode>,zip=<5DigitCode>,affiliateCampaignId=<ePNCampaignId>,affiliateReferenceId=<referenceId>");
	    	httpGet.addHeader("X-EBAY-C-MARKETPLACE", utilityClass.marktplaceId);
	    	httpGet.addHeader("Authorization", "Bearer "+ utilityClass.ebayAuth);
	    	break;
	    case "Paypal":
	    	httpGet.addHeader("Content-Type", "application/json");
	    	httpGet.addHeader("Authorization", ("Bearer "+ utilityClass.paypalAuthorizationToken));
	    	break;
	    case "PaypalAuth":
	    	httpGet.addHeader("Content-Type", "application/x-www-form-urlencoded");
	    	httpGet.addHeader("Authorization", ("Basic QWJwMkQzNjI4X3U0emJPNEVhcVlxSDBHbkNqX0xqdVlwYXlscVQ3ampkZEFFeWRTSFdVYWpPVGJiY1J6X0RfTDZnX2REd2VDQ0t3VE5tQ1o6RU1XdWtTNWpmZlpwa2lKOE54X3NnUDNzRDFqVEstejh6dDVnUlg3czk4VFdnOVZRUFBjMkZWNFJLS0ZMWk82azJJa3FHalRNUmZHZmFyMFk="));
	    	break;
	    default: 
	    	System.out.println("Default value");
	    	break;
	    }
	    
	    return httpGet;
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
	    case "searchItem":
	    	httpPost.addHeader("Content-Type", "application/json");
	    	httpPost.addHeader("X-EBAY-C-ENDUSERCTX", "contextualLocation=country=<2CharCountryCode>,zip=<5DigitCode>,affiliateCampaignId=<ePNCampaignId>,affiliateReferenceId=<referenceId>");
	    	httpPost.addHeader("X-EBAY-C-MARKETPLACE", utilityClass.marktplaceId);
	    	httpPost.addHeader("Authorization", "Bearer "+ utilityClass.ebayAuth);
	    	break;
	    case "Paypal":
	    	httpPost.addHeader("Content-Type", "application/json");
	    	httpPost.addHeader("Authorization", ("Bearer "+ utilityClass.paypalAuthorizationToken));
	    	break;
	    case "PaypalAuth":
	    	httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded");
	    	httpPost.addHeader("Authorization", ("Basic QWJwMkQzNjI4X3U0emJPNEVhcVlxSDBHbkNqX0xqdVlwYXlscVQ3ampkZEFFeWRTSFdVYWpPVGJiY1J6X0RfTDZnX2REd2VDQ0t3VE5tQ1o6RU1XdWtTNWpmZlpwa2lKOE54X3NnUDNzRDFqVEstejh6dDVnUlg3czk4VFdnOVZRUFBjMkZWNFJLS0ZMWk82azJJa3FHalRNUmZHZmFyMFk="));
	    	break;
	    default: 
	    	System.out.println("Default value");
	    	break;
	    }
	    
	    return httpPost;
	}

}

