package com.prudential.carRental.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prudential.carRental.bean.User;
import com.prudential.carRental.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags="user")
@RestController
@RequestMapping("/users")
public class UserController {
	@Autowired
	private UserService us;
	
	@ApiOperation("user list")
	@GetMapping
	public List<User> getAll() {
		System.out.println("/users: ");
		return us.getAll();
	}
	
	@ApiOperation("get user")
	@GetMapping("/{id}")
	public User getById(@PathVariable int id) {
		return us.getById(id); 
	}
	
}
