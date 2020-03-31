package com.ebay.queens.demo.resource;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.QueryParam;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ebay.queens.demo.repository.CharityRepository;
import com.ebay.queens.responses.paypalgetcharityresponse.GetCharityResult;

@RestController
@RequestMapping("/Charity")
public class CharityController {
	private static final Logger LOGGER = LoggerFactory.getLogger(CharityController.class);
	@Autowired
	private CharityRepository charityRepository;

	@PostMapping("/AddCharity")
	public void addCharity(@RequestBody GetCharityResult getCharityResult) {
		boolean addCharity = true;
		for (GetCharityResult charity : charityRepository.findAll()) {
			if (charity.getName().equals(getCharityResult.getName())) {
				addCharity = false;
			}
		}
		if (addCharity) {
			this.charityRepository.save(getCharityResult);
			LOGGER.info("Added Charity: " + getCharityResult.getName());
		}
	}

	@GetMapping("/DeleteCharities")
	public String deleteAllCharities() {
		charityRepository.deleteAll();
		return "Charities Deleted";
	}

	@GetMapping("/GetCharities")
	public String getAllCharities() {
		String returnedCharities = "";
		for (GetCharityResult charity : charityRepository.findAll()) {
			returnedCharities += charity.getName() + " ";
		}
		LOGGER.info("Charity list size: " + charityRepository.findAll().size());
		return returnedCharities;
	}

	@GetMapping("/GetCharityType")
	public List<GetCharityResult> getCharityType(@QueryParam("charityType") String charityType) {
		List<GetCharityResult> charityResults = new ArrayList();
		for (GetCharityResult charity : charityRepository.findAll()) {
			if (charity.getCause_area() != null) {
				if (charity.getCause_area()[0].getName().equals(charityType)) {
					if (charityResults.size() <= 15) {
						charityResults.add(charity);
					}
				}
			}
		}
		return charityResults;

	}

}
