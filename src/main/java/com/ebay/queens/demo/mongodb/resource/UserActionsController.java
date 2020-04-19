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

import com.ebay.queens.demo.model.UserGroup;
import com.ebay.queens.demo.model.ViewOnEbay;
import com.ebay.queens.demo.repository.UserActionRepository;


@RestController
@RequestMapping("/UserActions")
public class UserActionsController {
	private static final Logger LOGGER = LoggerFactory.getLogger(UserActionsController.class);
	UserActionsController() {
	}
	public ViewOnEbay userViewedItemOnEbay = new ViewOnEbay();
	
	int index = 0;
	
	
	@Autowired
	private UserActionRepository userActionRepository;
	
	@PostMapping("/AddUserAction")
	public String addUser(@QueryParam("userGroup") String userGroup, @QueryParam("viewOnEbay") String viewOnEbay) {
		boolean trueFalseValue = false; 
		System.out.println(viewOnEbay);
		if(viewOnEbay.equals("true")) {
			trueFalseValue=true;
		}else {
			trueFalseValue=false;
		}
		LOGGER.info("User Group: " + userGroup + " View On Ebay? " + trueFalseValue);
		userViewedItemOnEbay.setViewedOnEbay(trueFalseValue);
		if(userGroup.equals("A")) {
			userViewedItemOnEbay.setUserGroup(UserGroup.A);
		}else {
			userViewedItemOnEbay.setUserGroup(UserGroup.B);	
		}
		this.userActionRepository.save(userViewedItemOnEbay);
		index = index + 1;
		return "User action added";
	}
	
	@GetMapping("/DeleteUserActions")
	public String deleteUser() {
		String deletedViewCounts = "User Action deleted: ";
		List<ViewOnEbay> viewedOnEbay = this.userActionRepository.findAll();
		for (int i = 0; i <= this.userActionRepository.findAll().size() - 1; i++) {
			LOGGER.info("User Action deleted: " + viewedOnEbay.get(i).getUserGroup());
			deletedViewCounts += viewedOnEbay.get(i).getUserGroup();
		}
		this.userActionRepository.deleteAll();
		return deletedViewCounts;
	}
	
	@GetMapping("/GetUserActions")
	public List getUserActions() {
		List<ViewOnEbay> viewsOnEbay = this.userActionRepository.findAll();
		for (int i = 0; i <= this.userActionRepository.findAll().size() - 1; i++) {
			LOGGER.info("Search Type: " + viewsOnEbay.get(i));
		}
		return viewsOnEbay;
	}

}
