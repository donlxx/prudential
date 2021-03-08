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
import com.prudential.carRental.config.CarRentalTestConfig;
import com.prudential.carRental.service.CarService;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = {CarRentalTestConfig.class})
public class CarControllerTest {
	
	private MockMvc mockMvc;
	@Autowired CarService carService;
	@Autowired WebApplicationContext wac;
	@Autowired ObjectMapper objectMapper;
	
	@Test
	public void getCars() throws Exception {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
		
		Car car = new Car();
		car.setName("testCar");
		car.setQuantity(5);
		Mockito.when(carService.getAll()).thenReturn(Collections.singletonList(car));
		
		this.mockMvc
			.perform(
					get("/cars"))
			.andExpect(status().isOk())
			.andDo(MockMvcResultHandlers.print())
			.andReturn();
	}
	
	@Test
	public void getCarById() throws Exception {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
		
		this.mockMvc
			.perform(
					get("/cars/1"))
			.andExpect(status().isOk())
			.andDo(MockMvcResultHandlers.print())
			.andReturn();
	}
	
	@Test
	public void addCars() throws Exception {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
		
		Car car = new Car();
		car.setName("testCarForAdd");
		car.setQuantity(5);
		
		this.mockMvc
			.perform(
					post("/cars")
					.contentType(MediaType.APPLICATION_JSON)
					.content(objectMapper.writeValueAsString(car)))
			.andExpect(status().isOk())
			.andDo(MockMvcResultHandlers.print())
			.andReturn();
	}


}
