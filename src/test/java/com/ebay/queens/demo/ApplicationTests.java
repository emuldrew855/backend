package com.ebay.queens.demo;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import junit.framework.Assert;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTests {

	@Test
	public void contextLoads() throws IOException, IOException {
		String url = "http://svcs.ebay.com/services/fundraising/FundRaisingFindingService/v1";
		
		HttpClient client = HttpClientBuilder.create().build(); 
		HttpGet request = new HttpGet(url);
				
		HttpResponse response = client.execute(request);
		int actualResponseCode = response.getStatusLine().getStatusCode();
		
		Assert.assertEquals("200", actualResponseCode);
	}

}
