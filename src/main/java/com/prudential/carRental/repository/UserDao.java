package com.prudential.carRental.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.prudential.carRental.bean.User;

public interface UserDao extends JpaRepository<User, Integer>{
	User findByName(String name);
}
