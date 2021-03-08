package com.prudential.carRental.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prudential.carRental.bean.User;
import com.prudential.carRental.repository.UserDao;

@Service
public class UserService {
	@Autowired
	UserDao ud;
	
	public User getById(int id) {

		return ud.findById(id).get();

	}
	
	public User getByName(String username){
		return ud.findByName(username);
	}

	public List<User> getAll() {
		System.out.println("users Service: ");

		return ud.findAll();
	}
}
