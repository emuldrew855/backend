package com.ebay.queens.demo;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.ebay.queens.requests.findnonprofit.FindNonProfitRequest;
import com.ebay.queens.requests.findnonprofit.PaginationInput;
import com.ebay.queens.requests.findnonprofit.SearchFilter;

public class FindNonProfitTests {

	@Test
	public void testFindNonProfitGetter() {
		SearchFilter searchFilter = new SearchFilter("10484");
		PaginationInput paginationInput = new PaginationInput("25","1");
		FindNonProfitRequest findNonProfitRequest = new FindNonProfitRequest(searchFilter, paginationInput);
		assertEquals(findNonProfitRequest.getPaginationInput().getPageNumber(), "1");
		assertEquals(findNonProfitRequest.getPaginationInput().getPageSize(), "25");
		assertEquals(findNonProfitRequest.getSearchFilter().getExternalID(), "10484");
	}
	
	@Test
	public void testFindNonProfitSetter() {
		SearchFilter searchFilter = new SearchFilter("10484");
		PaginationInput paginationInput = new PaginationInput("25","1");
		FindNonProfitRequest findNonProfitRequest = new FindNonProfitRequest(searchFilter, paginationInput);
		assertEquals(findNonProfitRequest.getPaginationInput().getPageNumber(), "1");
		assertEquals(findNonProfitRequest.getPaginationInput().getPageSize(), "25");
		PaginationInput testSetterInput = new PaginationInput("100","50");
		findNonProfitRequest.setPaginationInput(testSetterInput);
		assertEquals(findNonProfitRequest.getPaginationInput().getPageNumber(), "50");
		assertEquals(findNonProfitRequest.getPaginationInput().getPageSize(), "100");
	}
}
