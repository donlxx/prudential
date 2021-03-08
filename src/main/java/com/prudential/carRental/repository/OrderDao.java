package com.prudential.carRental.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.prudential.carRental.bean.Car;
import com.prudential.carRental.bean.Order;

public interface OrderDao extends JpaRepository<Order, Integer> {
	
	List<Order> findByCustomerId(int id);
	List<Order> findByCarId(int carId);

}
