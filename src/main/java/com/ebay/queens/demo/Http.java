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
			"v^1.1#i^1#f^0#I^3#r^0#p^3#t^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");
	
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

