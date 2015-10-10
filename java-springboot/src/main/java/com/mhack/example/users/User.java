package com.mhack.example.users;

import com.fasterxml.jackson.annotation.JsonProperty;

public class User {
	private final String name;

	public User(String name) {
		this.name = name;
	}

	@JsonProperty
	public String name() {
		return name;
	}
}
