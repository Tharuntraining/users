package com.training.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.training.dto.UserRegistrationRequest;
import com.training.dto.UsersResponse;
import com.training.service.UsersService;
import com.training.utils.Status;

@RestController
public class UsersController {

	@Autowired
	private UsersService usersService;

	@GetMapping("getusers")
	public List<UsersResponse> getUsers() {
		return usersService.getUsers();

	}

	@PostMapping("userregistration")
	public Status addUser(@RequestBody UserRegistrationRequest userRegistration) {
		return usersService.addUser(userRegistration);
	}

}
