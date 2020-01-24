package com.ebay.queens.requests.charityitems;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.ebay.queens.responses.charityitemresponse.CharityItemResponse;
import com.ebay.queens.responses.findnonprofitresponse.FindNonProfitResponse;
import com.ebay.queens.responses.getitemresponse.GetItemResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class CharityItem {

	CharityItem() {

	}

	public CharityItemResponse sendMessage(CharityItemRequest charityItem) throws ClientProtocolException, IOException {
		// Object to XML
		FindNonProfitResponse deserializedReqFromXml = null;
		// Sample Object Mapper (Object --> JSON String) Usage
		final ObjectMapper mapper = new ObjectMapper();
		System.out.println("Serialized Object --> JSON String");
		String getItemRequestJSONString = mapper.writeValueAsString(charityItem);
		System.out.println(getItemRequestJSONString);
		System.out.println("---------------------------------");
		
		// Send the request
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpPost request = new HttpPost("http://svcs.ebay.com/services/fundraising/FundRaisingFindingService/v1");
		request.setEntity(new StringEntity(getItemRequestJSONString));
		request.addHeader("Accept", "application/json");
		request.addHeader("Content-Type", "application/json");
		request.addHeader("X-EBAY-C-MARKETPLACE-ID", "EBAY_US");
		request.addHeader("Authorization", "APP " + "EdwardMu-CharityP-PRD-538907625-4999b865");
		// Get the response
		HttpResponse response = httpClient.execute(request);
		String result = EntityUtils.toString(response.getEntity());
		// Sample Object Mapper (JSON String --> Object) Usage final String
		final CharityItemResponse deserializedReqFromJson = mapper.readValue(result, CharityItemResponse.class);
		System.out.println("Deserialized JSON String --> Object");
		System.out.println( deserializedReqFromJson.getCharityItems());
		System.out.println("---------------------------------");

		return deserializedReqFromJson;
	}
}