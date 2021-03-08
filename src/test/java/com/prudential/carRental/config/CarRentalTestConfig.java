package com.prudential.carRental.config;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.prudential.carRental.controller.CarController;
import com.prudential.carRental.controller.OrderController;
import com.prudential.carRental.controller.UserController;
import com.prudential.carRental.repository.CarDao;
import com.prudential.carRental.repository.OrderDao;
import com.prudential.carRental.repository.UserDao;
import com.prudential.carRental.service.CarService;
import com.prudential.carRental.service.OrderService;
import com.prudential.carRental.service.UserService;

@Configuration
@EnableWebMvc
public class CarRentalTestConfig {
	
	@Bean(name = "carService")
	public CarService carSerivce() {
		return Mockito.mock(CarService.class);
	}
	
	@Bean(name = "orderService")
	public OrderService orderSerivce() {
		return Mockito.mock(OrderService.class);
	}
	
	@Bean
	public ObjectMapper objectMapper() {
		return new ObjectMapper();
	}
	
	@Bean(name = "userService")
	public UserService userSerivce() {
		return Mockito.mock(UserService.class);
	}
	
	@Bean(name = "carController")
	public CarController carController() {
		return Mockito.mock(CarController.class);
	}
	
	@Bean(name = "userController")
	public UserController userController() {
		return Mockito.mock(UserController.class);
	}
	
	@Bean(name = "orderController")
	public OrderController orderController() {
		return Mockito.mock(OrderController.class);
	}
	
	@Bean(name = "carDao")
	public CarDao carDao() {
		return Mockito.mock(CarDao.class);
	}
	
	@Bean(name = "orderDao")
	public OrderDao orderDao() {
		return Mockito.mock(OrderDao.class);
	}
	
	@Bean(name = "userDao")
	public UserDao userDao() {
		return Mockito.mock(UserDao.class);
	}

}
