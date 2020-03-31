package com.ebay.queens.demo.resource;

import java.util.List;

import javax.ws.rs.QueryParam;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ebay.queens.demo.model.SearchType;
import com.ebay.queens.demo.repository.SearchTypeRepository;

@RestController
@RequestMapping("/SearchType")
public class SearchTypeController {
	private static final Logger LOGGER = LoggerFactory.getLogger(SearchTypeController.class);
	@Autowired
	private SearchTypeRepository searchTypeRepository;
	
	@PostMapping("/AddSearchType")
	public String addSearchType(@QueryParam("searchType") String searchType) {
		LOGGER.info(searchType);
		SearchType se = new SearchType();
		se.setType(searchType);
		searchTypeRepository.save(se);
		return searchType;
	}
	
	@GetMapping("/GetSearchTypes")
	public List getSearchTypes() {
		List<SearchType> searchTypes = this.searchTypeRepository.findAll();
		for (int i = 0; i <= this.searchTypeRepository.findAll().size() - 1; i++) {
			LOGGER.info("Search Type: " + searchTypes.get(i));
		}
		return searchTypes;
	}

}
