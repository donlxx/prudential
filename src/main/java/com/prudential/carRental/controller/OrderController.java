package com.prudential.carRental.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prudential.carRental.bean.Order;
import com.prudential.carRental.constant.AppContants;
import com.prudential.carRental.service.OrderService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


@Api(tags="order management")
@RestController
@RequestMapping("orders")
public class OrderController {
	
	@Autowired OrderService orderService;
	
	@ApiOperation("place order")
	@PostMapping
    public ResponseEntity<?> addOrder(@RequestBody Order order) {
        String result = orderService.saveOrder(order);
        
        if(result.equalsIgnoreCase(AppContants.SUCCESS)) {
        	return new ResponseEntity<>("success",HttpStatus.OK);
        }else if(result.equalsIgnoreCase(AppContants.ALREADY_HAS_CAR)) {
        	return new ResponseEntity<>(AppContants.ALREADY_HAS_CAR,HttpStatus.BAD_REQUEST);
        }else if(result.equalsIgnoreCase(AppContants.CAR_NOT_ENOUGH)){
        	return new ResponseEntity<>(AppContants.CAR_NOT_ENOUGH,HttpStatus.BAD_REQUEST);
        }else {
        	return null;
        }
		
    }
	
	@ApiOperation("get order by user Id")
	@GetMapping("/{id}")
	public List<Order> getOrderByUserId(@PathVariable int id){
		List<Order> orders=orderService.getOrderByUserId(id);
		return orders;
	}
	
	@ApiOperation("get all orders (for admin use)")
	@GetMapping()
	public List<Order> getAllOrders(){
		List<Order> orders=orderService.getAllOrders();
		return orders;
	}
	
	@ApiOperation("delete order by order id")
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteOrderById(@PathVariable int id){
		
		return orderService.deleteOrderById(id);
	}
	@ApiOperation("clear all order(for testing other apis)")
	@DeleteMapping()
	public ResponseEntity<?> deleteAllOrder(){
		
		return orderService.deleteAllOrder();
	}
	
}
