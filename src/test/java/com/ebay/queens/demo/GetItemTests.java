package com.ebay.queens.demo;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import com.ebay.queens.demo.Http;
import com.ebay.queens.requests.getitem.GetItemRequest;
import com.ebay.queens.requests.getitem.RequesterCredentials;
import com.ebay.queens.responses.getitemresponse.GetItemResponse;

public class GetItemTests {
	@Autowired
	private Http httpClass;
	
	@Test
	public void testGetItemRequestGetters() throws IOException {
		RequesterCredentials reqCred = new RequesterCredentials(
				"AgAAAA**AQAAAA**aAAAAA**ChvUXQ**nY+sHZ2PrBmdj6wVnY+sEZ2PrA2dj6AEkYOiDpeKoQWdj6x9nY+seQ**AeEFAA**AAMAAA**om8bY77e+hYcxwwMwjnSs/PwlO2OBas/H1XmD9BuaRKJT8AqTUApb1bNnd9cpkcHT+ylWjnmiiHa1sHkBNSX8vICwZrMKdhu94xYz4fwl1r88hIUqXfyTpd0lLeKb9PvBTm/6lml/4MX1c5YENvcyRx9Dyan8WOMbIvM6ZsM8mQ7Sw7URdaZCEZwjee1WztIL9AEOUFKKsO9TYjArRZFN0rqGfgXqNdUd1e4BqK7qtNyRScWtHQ4+cJDEU+iH0d8yEGlg4KgJuPn17M0nOoOKzMrrNuwsojaBTYk3GIdPj0Eb18bJhnVvRm4xI138YDizMXEu8PIbAmp+4233hRE4wiOnAa80eMSgqxd1TZuRSOupGPdDIgVoRT+AuXHaXhEMQDkbxitHg4ZnsF9XYC1tpxo0lngZNf3NAFRu1yPdxWXSShaQpLXGcM/KISYvKoxgkYIBq3hn8LG52qA+ARJlo+Wc+Z74volMIFnCz51I8EkYqn3ntR1fuvkmoJL4HEy3cCse9XPPkIlgUk3aYW+i0/bNnLzJVa9N8es4FHsg6f1fyQ5I4feBC//ScF1qbkLOxs/BLL+cylOUKZBEKXxDzq0ieq5p0g9TwT13UTtmjFGhHzCMCOmbfP4S0jki9jCReyWIiIauAKLDbnGEqfk1AYYoZJvqPGVRDHdzU665zgfFrNYNljo0uGGUIqzxOtepQmL5G04uoOCdTanfSKKaKnriBNR35u2SlY6zevZjZ/gwbsQs1WAFkjujrkOPlbz");
		GetItemRequest getItemRequest = new GetItemRequest(reqCred, "333460893922", "ReturnAll");
		assertEquals(getItemRequest.getItemID(),"333460893922");
		assertEquals(getItemRequest.getDetailLevel(),"ReturnAll");
		assertEquals(getItemRequest.getRequesterCredentials().getClass(),RequesterCredentials.class);
	}
	
	
	@Test
	public void testGetItemRequestSetters() throws IOException {
		GetItemResponse getItemResponse = null;
		RequesterCredentials reqCred = new RequesterCredentials(
				"AgAAAA**AQAAAA**aAAAAA**ChvUXQ**nY+sHZ2PrBmdj6wVnY+sEZ2PrA2dj6AEkYOiDpeKoQWdj6x9nY+seQ**AeEFAA**AAMAAA**om8bY77e+hYcxwwMwjnSs/PwlO2OBas/H1XmD9BuaRKJT8AqTUApb1bNnd9cpkcHT+ylWjnmiiHa1sHkBNSX8vICwZrMKdhu94xYz4fwl1r88hIUqXfyTpd0lLeKb9PvBTm/6lml/4MX1c5YENvcyRx9Dyan8WOMbIvM6ZsM8mQ7Sw7URdaZCEZwjee1WztIL9AEOUFKKsO9TYjArRZFN0rqGfgXqNdUd1e4BqK7qtNyRScWtHQ4+cJDEU+iH0d8yEGlg4KgJuPn17M0nOoOKzMrrNuwsojaBTYk3GIdPj0Eb18bJhnVvRm4xI138YDizMXEu8PIbAmp+4233hRE4wiOnAa80eMSgqxd1TZuRSOupGPdDIgVoRT+AuXHaXhEMQDkbxitHg4ZnsF9XYC1tpxo0lngZNf3NAFRu1yPdxWXSShaQpLXGcM/KISYvKoxgkYIBq3hn8LG52qA+ARJlo+Wc+Z74volMIFnCz51I8EkYqn3ntR1fuvkmoJL4HEy3cCse9XPPkIlgUk3aYW+i0/bNnLzJVa9N8es4FHsg6f1fyQ5I4feBC//ScF1qbkLOxs/BLL+cylOUKZBEKXxDzq0ieq5p0g9TwT13UTtmjFGhHzCMCOmbfP4S0jki9jCReyWIiIauAKLDbnGEqfk1AYYoZJvqPGVRDHdzU665zgfFrNYNljo0uGGUIqzxOtepQmL5G04uoOCdTanfSKKaKnriBNR35u2SlY6zevZjZ/gwbsQs1WAFkjujrkOPlbz");
		GetItemRequest getItemRequest = new GetItemRequest(reqCred, "333460893922", "ReturnAll");
		getItemRequest.setItemID("1");
		getItemRequest.setDetailLevel("null");
		assertEquals(getItemRequest.getItemID(),"1");
		assertEquals(getItemRequest.getDetailLevel(),"null");
	}
}
