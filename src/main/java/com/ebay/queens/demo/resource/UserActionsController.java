package com.ebay.queens.demo.resource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.ws.rs.QueryParam;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ebay.queens.demo.model.ViewOnEbay;
import com.ebay.queens.demo.repository.UserActionRepository;
import com.ebay.queens.demo.repository.UserRepository;


@RestController
@RequestMapping("/UserActions")
public class UserActionsController {
	private static final Logger LOGGER = LoggerFactory.getLogger(UserActionsController.class);
	public ViewOnEbay userViewedItemOnEbay = new ViewOnEbay();
	public ArrayList viewedItemOnEbay = new ArrayList();
	int index = 0;
	
	@Autowired
	private UserActionRepository userActionRepository;
	
	
	UserActionsController() {
		HashMap<String, Boolean> userActionA = new HashMap<>();
		userActionA.put("A", true);
		HashMap<String, Boolean> userActionB = new HashMap<>();
		userActionB.put("B", true);
		userViewedItemOnEbay.getUserViewedItemOnEbay().put("B", true);
		userViewedItemOnEbay.getUserViewedItemOnEbay().put("A", true);
		//userActionRepository.saveAll(userViewedItemOnEbay);
	}
	
	@GetMapping("/AddUserAction")
	public String deleteUser(@QueryParam("userGroup") String userGroup, @QueryParam("viewOnEbay") boolean viewOnEbay) {
		LOGGER.info("User Group: " + userGroup + " View On Ebay? " + viewOnEbay);
		HashMap<String, Boolean> userAction = new HashMap<>();
		userAction.put(userGroup, viewOnEbay);
		viewedItemOnEbay.add(index,userAction);
		this.userActionRepository.save(viewedItemOnEbay);
		index = index + 1;
		return "User action added";
	}
	
	@GetMapping("/GetUserActions")
	public List getUserActions() {
		return this.userActionRepository.findAll();
	}

}
