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
import com.prudential.carRental.bean.Order;
import com.prudential.carRental.config.CarRentalTestConfig;
import com.prudential.carRental.service.OrderService;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = {CarRentalTestConfig.class})
public class OrderControllerTest {
	
	private MockMvc mockMvc;
	@Autowired OrderService orderService;
	@Autowired WebApplicationContext wac;
	@Autowired ObjectMapper objectMapper;
	
	
	@Test
	public void getCars() throws Exception {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
		
		Order order = new Order();
		order.setCarId(1);
		order.setCustomerId(1);
		order.setStartTime("2021-3-10 9:00:00");
		order.setReturnTime("2021-3-10 10:00:00");
		Mockito.when(orderService.getAllOrders()).thenReturn(Collections.singletonList(order));
		
		this.mockMvc
			.perform(
					get("/orders"))
			.andExpect(status().isOk())
			.andDo(MockMvcResultHandlers.print())
			.andReturn();
	}
	
	@Test
	public void getOrderByUserId() throws Exception {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
		
		this.mockMvc
			.perform(
					get("/orders/1"))
			.andExpect(status().isOk())
			.andDo(MockMvcResultHandlers.print())
			.andReturn();
	}
	
	@Test
	public void addOrder() throws Exception {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
		
		Order order = new Order();
		order.setCarId(1);
		order.setCustomerId(1);
		order.setStartTime("2021-3-10 9:00:00");
		order.setReturnTime("2021-3-10 10:00:00");
		
		this.mockMvc
			.perform(
					post("/orders")
					.contentType(MediaType.APPLICATION_JSON)
					.content(objectMapper.writeValueAsString(order)))
			.andExpect(status().isOk())
			.andDo(MockMvcResultHandlers.print())
			.andReturn();
	}
	
	

}
