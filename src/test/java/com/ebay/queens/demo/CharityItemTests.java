package com.ebay.queens.demo;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.ebay.queens.requests.charityitems.CharityItemRequest;
import com.ebay.queens.requests.charityitems.Constraints;
import com.ebay.queens.requests.charityitems.GlobalAspect;
import com.ebay.queens.requests.charityitems.SearchRequest;
import com.ebay.queens.requests.findnonprofit.PaginationInput;

public class CharityItemTests {
	
	@Test
	public void testCharityItemGetters() { 
		PaginationInput paginationInput = new PaginationInput("1", "25");
		String[] valueIds = {"88"};
		String[] charityOnly = {"true"};
		GlobalAspect globalAspect1 = new GlobalAspect("CharityIds", valueIds);
		GlobalAspect globalAspect2 = new GlobalAspect("CharityOnly", charityOnly);
		GlobalAspect globalAspectList[] = new GlobalAspect[2]; 
		globalAspectList[0] = globalAspect1;
		globalAspectList[1] = globalAspect2;
		Constraints constraints = new Constraints(globalAspectList);
		SearchRequest searchRequest = new SearchRequest("StartTimeNewest", paginationInput, constraints); 
		CharityItemRequest charityItemRequest = new CharityItemRequest(searchRequest);
		assertEquals(charityItemRequest.getSearchRequest().getSortOrder(), "StartTimeNewest");
	}
	
	
	@Test
	public void testCharityItemSetters() {
		PaginationInput paginationInput = new PaginationInput("1", "25");
		String[] valueIds = {"88"};
		String[] charityOnly = {"true"};
		GlobalAspect globalAspect1 = new GlobalAspect("CharityIds", valueIds);
		GlobalAspect globalAspect2 = new GlobalAspect("CharityOnly", charityOnly);
		GlobalAspect globalAspectList[] = new GlobalAspect[2]; 
		globalAspectList[0] = globalAspect1;
		globalAspectList[1] = globalAspect2;
		Constraints constraints = new Constraints(globalAspectList);
		SearchRequest searchRequest = new SearchRequest("StartTimeNewest", paginationInput, constraints); 
		CharityItemRequest charityItemRequest = new CharityItemRequest(searchRequest);
	}

}
