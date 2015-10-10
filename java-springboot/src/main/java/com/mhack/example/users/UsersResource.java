package com.mhack.example.users;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UsersResource {
	
	@Autowired
	UsersService users;

	@RequestMapping(method=GET)
	public List<User> index() {
		return users.all();
	}
}
