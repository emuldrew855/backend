package com.ebay.queens.demo.resource;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.ws.rs.QueryParam;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.ebay.queens.demo.Version1Api;
import com.ebay.queens.demo.model.User;
import com.ebay.queens.demo.model.UserActions;
import com.ebay.queens.demo.repository.UserRepository;
import com.ebay.queens.responses.findnonprofitresponse.FindNonProfitResponse;

@RestController
@RequestMapping("/v2")
public class UserController {
	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
	public ArrayList userViewedItemOnEbay = new ArrayList();
	
	UserController(){
		HashMap<String, Boolean> userActionA = new HashMap<>();
		userActionA.put("A", true);
		HashMap<String, Boolean> userActionB = new HashMap<>();
		userActionB.put("B", true);
		userViewedItemOnEbay.add(userActionA);
		userViewedItemOnEbay.add(userActionB);
	}
	int index = 0;
	
	@Autowired
	private UserRepository userRepository;
	
	@PostMapping("/GetUser")
	public Optional<User> getUser(int i) {
		return userRepository.findById(i);
	}

	@PostMapping("/AddUser")
	public String saveUser(@RequestBody User user) {
		System.out.println("User: " + user);
		userRepository.save(user);
		return "Added user with id: " + user.getId();
	}

	@PostMapping("/AddAllUser")
	public String saveUser(@RequestBody List<User> users) {
		System.out.println("User: " + users);
		String addedUsers = "Users added: ";
		userRepository.saveAll(users);
		for (int i = 0; i <= users.size() - 1; i++) {
			LOGGER.info("User: " + users.get(i).getUsername());
			addedUsers += users.get(i).getUsername();
		}
		return addedUsers;
	}

	@GetMapping("/GetUsers")
	public List<User> getAll() {
		List<User> users = this.userRepository.findAll();
		for (int i = 0; i <= this.userRepository.findAll().size() - 1; i++) {
			LOGGER.info("User: " + users.get(i).getUsername());
		}
		return users;
	}

	@GetMapping("/DeleteAllUsers")
	public String deleteAll() {
		String deletedUsers = "Users deleted: ";
		List<User> users = this.userRepository.findAll();
		for (int i = 0; i <= this.userRepository.findAll().size() - 1; i++) {
			LOGGER.info("User deleted: " + users.get(i).getUsername());
			deletedUsers += users.get(i).getUsername();
		}
		this.userRepository.deleteAll();
		return deletedUsers;
	}

	@GetMapping("/DeleteUser")
	public String deleteUser(User user) {
		String deletedUser = "User: " + user.getUsername() + " deleted!";
		this.userRepository.delete(user);
		LOGGER.info(deletedUser);
		return deletedUser;
	}

	@GetMapping("/AddUserAction")
	public String deleteUser(@QueryParam("userGroup") String userGroup, @QueryParam("viewOnEbay") boolean viewOnEbay) {
		LOGGER.info("User Group: " + userGroup + " View On Ebay? " + viewOnEbay);
		HashMap<String, Boolean> userAction = new HashMap<>();
		userAction.put(userGroup, viewOnEbay);
		userViewedItemOnEbay.add(index,userAction);
		index = index + 1;
		return "User action added";
	}
	
	@GetMapping("/GetUserActions")
	public List getUserActions() {
		return userViewedItemOnEbay;
	}
}
