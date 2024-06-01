package com.training.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.training.model.Users;

@Repository
public interface UsersDao extends JpaRepository<Users, Integer> {

	boolean existsByEmail(String email);

}
