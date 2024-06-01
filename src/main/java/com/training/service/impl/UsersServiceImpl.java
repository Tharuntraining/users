package com.training.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.training.dao.UsersDao;
import com.training.dto.UserRegistrationRequest;
import com.training.dto.UsersResponse;
import com.training.model.Users;
import com.training.service.UsersService;
import com.training.utils.Status;

@Service
public class UsersServiceImpl implements UsersService {

	@Autowired
	private UsersDao usersDao;

	@Override
	public List<UsersResponse> getUsers() {
		List<Users> mappings = usersDao.findAll(Sort.by(Sort.Direction.ASC, "userId"));
		if (mappings.isEmpty()) {
			return null; // Or throw an exception if user not found
		}
		List<UsersResponse> productList = mappings.stream().map(mapping -> {
			UsersResponse detail = new UsersResponse();
			detail.setUserId(mapping.getUserId());
			detail.setUserName(mapping.getUserName());
			detail.setEmail(mapping.getEmail());
			return detail;
		}).collect(Collectors.toList());
		return productList;

	}

	@Override
	public Status addUser(UserRegistrationRequest userRegistration) {
		Status status = Status.FAILED;
		Users userRequest = new Users(userRegistration.getUserName(), userRegistration.getEmail());
		try {
			boolean userExists = usersDao.existsByEmail(userRegistration.getEmail());
			if (!userExists) {
				Users response = usersDao.save(userRequest);
				if (response != null) {
					status = Status.SUCCESS;
				}
			} else {
				status = Status.USER_ALREADY_EXISTS;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return status;
	}

}
