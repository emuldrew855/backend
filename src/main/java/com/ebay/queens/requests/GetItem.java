package com.ebay.queens.requests;
import java.io.IOException;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

public class GetItem {
	// Build your request object
	public GetItem() throws ClientProtocolException, IOException {
		CloseableHttpClient httpClient = HttpClients.createDefault();
	 	String requestBody = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\r\n" + 
	 			"<GetItemRequest xmlns=\"urn:ebay:apis:eBLBaseComponents\">\r\n" + 
	 			"    <requesterCredentials>\r\n" + 
	 			"        <eBayAuthToken>AgAAAA**AQAAAA**aAAAAA**9enaXQ**nY+sHZ2PrBmdj6wVnY+sEZ2PrA2dj6AGmICpDZmDpwWdj6x9nY+seQ**OSoGAA**AAMAAA**mc4q0e94Onut08iyCeBQDC6u1QQokVs4kukRMxe2PSdQ4CsKUIxSGhgFHFraUV0VJ1fLgYmKg6kXDQh0idHTgKjcHFPcuPDmtwT5TSH+SAKHw6TaZkAgVLgOMBprmwgLAJNiTESHarSfuuJxUdILw3lB/rrGC3tQ3sFz8l3SxpCx6NXvuOQ0++3aZ2gB/EPeiT70bR/z87bVkZYoK/OAG/vRCBVch5xo2PiJWJl5xLYpG3iz6aauTiJur3TKC3cPriMLWDkLAOqhXZol9jp2cknPesRfDypOBzDnvECNP81F18t0/3u/Lgn9BWxYdefCm95Rkw3XjZwQTG1GVLqBHoWBpG3s8eLuQhChlbH52ecF7sFb3aSXdvTcOCSwVHMA0GRjPhcoNt91WOfI22tUaJJ7/H72IqosJw235lvgvqQ5UQSzh5BE/Wp0u9bGzpUHgODeRtfO45miC+5itGBs0r1KKjN0CEzQ8WsDzWK2eGmmnznW8f2osEa83C9sa41/dEC5U1Cy8vpMgp/nz+qjKf+wQ3OUsSEgOKrjvC3tZcWUivhyu/GPEhAHEF6XBOTOyMnspoZKWNL4RMxGxfpeG3ANoer4vmdizPK7C6h3eLyTTYfL0jcML9Ld+rFKMD7hVx8ATu32nQVt3GmXa9m8cp2rSNPdgRV36LMWRxW2aXMq+MksRZkNhhm4WxSGUykR3N8K8jFiD9LPrz0pEux8UXEfF8ZWEKlxn2jCn9Tjy1WLMQ5ljotGHt+eMsfkaWoC</eBayAuthToken>\r\n" + 
	 			"    </requesterCredentials>\r\n" + 
	 			"    <ItemID>333460893922</ItemID>\r\n" + 
	 			"    <DetailLevel>ReturnAll</DetailLevel>\r\n" + 
	 			"</GetItemRequest>";
		
	 	// Create an object mapper and use it to write your object as a JSON string
		final ObjectMapper mapper = new ObjectMapper();
		StringEntity body = new StringEntity(mapper.writeValueAsString(requestBody));
		// Send the request
		HttpPost request = new HttpPost("https://api.ebay.com/ws/api.dll");
		request.setEntity(body);
		request.addHeader("Content-Type", "application/xml");
		request.addHeader("X-EBAY-SOA-SECURITY-APPNAME", "EdwardMu-CharityP-PRD-538907625-4999b865");
		request.addHeader("X-EBAY-API-SITEID", "3");
		request.addHeader("X-EBAY-API-CALL-NAME", "GetItem");
		request.addHeader("X-EBAY-API-COMPATIBILITY-LEVEL", "1107");
		request.addHeader("X-EBAY-API-APP-NAME", "EdwardMu-CharityP-PRD-538907625-4999b865"); 
		request.addHeader("X-EBAY-API-DEV-NAME", "f21ad267-e241-4ed0-8943-721fa90bcf3a");
		request.addHeader("X-EBAY-API-CERT-NAME", "PRD-38907625e4e8-b6b8-4c83-b069-68ac");
		// Get the response
		HttpResponse response = httpClient.execute(request);
		String result = EntityUtils.toString(response.getEntity());
		System.out.println("Response: " + result.toString());
	}

}
