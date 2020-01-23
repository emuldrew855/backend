package com.ebay.queens.requests.findnonprofit;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ebay.queens.demo.Version1Api;
import com.ebay.queens.requests.getitem.GetItemRequest;
import com.ebay.queens.responses.findnonprofitresponse.FindNonProfitResponse;
import com.ebay.queens.responses.getitemresponse.GetItemResponse;

public class FindNonProfit {
	private static final Logger LOGGER = LoggerFactory.getLogger(FindNonProfit.class);

	public FindNonProfit() {

	}

	public FindNonProfitResponse sendMessage(FindNonProfitRequest findNonProfitRequest)
			throws ClientProtocolException, IOException, JAXBException {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		// Object to XML
		String getItemRequestXmlString = "";
		FindNonProfitResponse deserializedReqFromXml = null;
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(GetItemRequest.class);
			Marshaller marshaller = jaxbContext.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			System.out.println("Serialized Object --> XML String ");
			marshaller.marshal(findNonProfitRequest, System.out);
			StringWriter sw = new StringWriter();
			marshaller.marshal(findNonProfitRequest, sw);
			getItemRequestXmlString = sw.toString();
			System.out.println("---------------------------------");
		} catch (JAXBException e) {
			LOGGER.error("Failed to serialize XML.", e);
		}

		// Send the request
		HttpPost request = new HttpPost("http://svcs.ebay.com/services/fundraising/FundRaisingFindingService/v1");
		request.setEntity(new StringEntity(getItemRequestXmlString));
		request.addHeader("Content-Type", "application/xml");
		request.addHeader("X-EBAY-SOA-OPERATION-NAME", "findNonprofit");
		request.addHeader("X-EBAY-SOA-GLOBAL-ID", "EBAY-US");
		request.addHeader("X-EBAY-SOA-SECURITY-APPNAME", "EdwardMu-CharityP-PRD-538907625-4999b865");
		// Get the response
		HttpResponse response = httpClient.execute(request);
		String result = EntityUtils.toString(response.getEntity());
		System.out.println("Result: " + result);
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(FindNonProfitResponse.class);
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
			deserializedReqFromXml = (FindNonProfitResponse) unmarshaller.unmarshal(new StringReader(result));
			System.out.println("Deserialized XML String --> Object");
			System.out.println(deserializedReqFromXml.getAck());
			System.out.println(deserializedReqFromXml.getTimestamp());
			System.out.println(deserializedReqFromXml.getVersion());
			System.out.println(deserializedReqFromXml.getNonProfit());
			System.out.println("---------------------------------");
		} catch (JAXBException e) {
			LOGGER.error("Failed to deserialize XML.", e);
		}
		return deserializedReqFromXml;
	}

}
