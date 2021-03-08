package com.prudential.carRental.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Collections;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.prudential.carRental.bean.Car;
import com.prudential.carRental.bean.User;
import com.prudential.carRental.config.CarRentalTestConfig;
import com.prudential.carRental.service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = {CarRentalTestConfig.class})
public class UserControllerTest {
	
	private MockMvc mockMvc;
	@Autowired UserService userService;
	@Autowired WebApplicationContext wac;
	@Autowired ObjectMapper objectMapper;
	
	@Test
	public void getUsers() throws Exception {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
		
		User user = new User();
		user.setName("testUser");
		user.setPassword("test");;
		Mockito.when(userService.getAll()).thenReturn(Collections.singletonList(user));
		
		this.mockMvc
			.perform(
					get("/users"))
			.andExpect(status().isOk())
			.andDo(MockMvcResultHandlers.print())
			.andReturn();
	}
	
	@Test
	public void getUserById() throws Exception {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
		
		this.mockMvc
			.perform(
					get("/users/1"))
			.andExpect(status().isOk())
			.andDo(MockMvcResultHandlers.print())
			.andReturn();
	}


}