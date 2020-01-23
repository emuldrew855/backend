package com.ebay.queens.requests;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import com.ebay.queens.demo.Version1Api;
import com.ebay.queens.responses.*;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

public class GetItem {
	// Build your request object
	private static final Logger LOGGER = LoggerFactory.getLogger(Version1Api.class);
	
	public GetItem() throws ClientProtocolException, IOException {

	}
	
	public GetItemResponse sendMessage(GetItemRequest getItemRequest) throws ClientProtocolException, IOException, JAXBException {
		CloseableHttpClient httpClient = HttpClients.createDefault();
	 	// Object to XML
		String getItemRequestXmlString = "";
		GetItemResponse deserializedReqFromXml = null;
		  try {
		        JAXBContext jaxbContext = JAXBContext.newInstance(GetItemRequest.class);
		        Marshaller marshaller = jaxbContext.createMarshaller();
		        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		        System.out.println("Serialized Object --> XML String ");
		        marshaller.marshal(getItemRequest, System.out);
		        StringWriter sw = new StringWriter();
		        marshaller.marshal(getItemRequest, sw);
		        getItemRequestXmlString = sw.toString();
		        System.out.println("---------------------------------");
		      } catch (JAXBException e) {
		        LOGGER.error("Failed to serialize XML.", e);
		      }

		// Send the request
		HttpPost request = new HttpPost("https://api.ebay.com/ws/api.dll");
		request.setEntity(new StringEntity(getItemRequestXmlString));
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
		System.out.println("Result: " + result);
		try {
		      JAXBContext jaxbContext = JAXBContext.newInstance(GetItemResponse.class);
		      Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
		      deserializedReqFromXml = (GetItemResponse) unmarshaller.unmarshal(new StringReader(result));
		      System.out.println("Deserialized XML String --> Object");
		      System.out.println(deserializedReqFromXml.getAck());
		      System.out.println(deserializedReqFromXml.getBuild());
		      System.out.println(deserializedReqFromXml.getTimestamp());
		      System.out.println(deserializedReqFromXml.getItem());
		      System.out.println("---------------------------------");
		    } catch (JAXBException e) {
		      LOGGER.error("Failed to deserialize XML.", e);
		    }
		return deserializedReqFromXml;	
	}

}
