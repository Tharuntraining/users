package com.training.service;

import java.util.List;

import com.training.dto.UserRegistrationRequest;
import com.training.dto.UsersResponse;
import com.training.utils.Status;

public interface UsersService {

	public List<UsersResponse> getUsers();

	public Status addUser(UserRegistrationRequest userRegistration);

}
