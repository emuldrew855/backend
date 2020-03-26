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
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import com.ebay.queens.demo.Ebay;
import com.ebay.queens.demo.model.User;
import com.ebay.queens.demo.model.UserActions;
import com.ebay.queens.demo.model.UserGroup;
import com.ebay.queens.demo.repository.UserRepository;
import com.ebay.queens.responses.findnonprofitresponse.FindNonProfitResponse;

@Component
@RestController
@RequestMapping("/v2")
public class UserController {
	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
	int index = 0;
	int searchTypeIndex = 0;

	@Autowired
	private UserRepository userRepository;
	
	UserController() {
		
	}
	
	@GetMapping("/LoadStartUsers")
	public String loadStartUsers() {
		User userA = new User("1","userA","userA");
		userA.setUserGroup(UserGroup.A);
		User userB = new User("2","userB","userB");
		userB.setUserGroup(UserGroup.B);
		User admin = new User("3","admin","admin");
		admin.setUserGroup(UserGroup.B);
		List<User> startUpUsers = new ArrayList<User>();
		startUpUsers.add(userA);
		startUpUsers.add(userB);
		startUpUsers.add(admin);
		this.saveAllUsers(startUpUsers);
		return "Loaded Start Users";
	}
	
	@PostMapping("/GetUser")
	public User getUser(String username) {
		User findUser = null;
		for(User user: userRepository.findAll()) {
			if(user.getUsername().equals(username)) {
				findUser = user;
				LOGGER.info("Found user: " + user.getUsername());
			}
		}
		return findUser;
	}

	@PostMapping("/AddUser")
	public String saveUser(@RequestBody User user) {
		this.userRepository.save(user);
		LOGGER.info("Added user: " + user.getUsername());
		return "Added user: " + user.getUsername();
	}

	@PostMapping("/AddAllUser")
	public String saveAllUsers(@RequestBody List<User> users) {
		String addedUsers = "Users added: ";
		this.userRepository.saveAll(users);
		for (int i = 0; i <= users.size() - 1; i++) {
			addedUsers += users.get(i).getUsername() + " ";
		}
		LOGGER.info("Added Users: " + addedUsers);
		return addedUsers;
	}

	@GetMapping("/GetUsers")
	public List<User> getAllUsers() {
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
			deletedUsers += users.get(i).getUsername() + " ";
		}
		LOGGER.info("Users deleted: " + deletedUsers);
		this.userRepository.deleteAll();
		return deletedUsers;
	}

	@GetMapping("/DeleteUser")
	public String deleteUser(User user) {
		String deletedUser = "User: " + user.getUsername() + " deleted!";
		this.userRepository.delete(user);
		LOGGER.info("Deleted User: " + deletedUser);
		return deletedUser;
	}
}
