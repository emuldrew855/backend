package com.ebay.queens.main.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.ebay.queens.main.model.User;
import com.ebay.queens.main.repository.UserRepository;


@RestController
@RequestMapping("/v2")
public class UserController {
	
	@Autowired
	private UserRepository userRepository;

	@PostMapping("/AddUser")
	public String saveUser(@RequestBody User user) {
		userRepository.save(user);
		return "Added user with id: " + user.getId();
	}

    @GetMapping("/all")
    public List<User> getAll(){
        List<User> users = this.userRepository.findAll();
        return users;
    }
}
