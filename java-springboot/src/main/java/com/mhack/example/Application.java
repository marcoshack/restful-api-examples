package com.mhack.example;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.mhack.example.users.User;
import com.mhack.example.users.UsersService;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
	@Bean
	public UsersService usersServices() {
		return new UsersService() {
			@Override
			public List<User> all() {
				List<User> res = new ArrayList<User>();
				res.add(new User("foo"));
				res.add(new User("bar"));
				return res;
			}
		};
	}
}
