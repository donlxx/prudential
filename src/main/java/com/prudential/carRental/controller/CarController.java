package com.prudential.carRental.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prudential.carRental.bean.Car;
import com.prudential.carRental.service.CarService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags="car management")
@RestController
@RequestMapping("cars")
public class CarController {

	@Autowired
	private CarService cs;
	
	@ApiOperation("get car by car Id")
	@GetMapping("{id}")
	Car getById(@PathVariable int id) {
		return cs.getById(id);
	}
	
	@ApiOperation("get all car information")
	@GetMapping
	List<Car> getAll(){
		return cs.getAll();
	}
	
	@ApiOperation("put new car in the system")
	@PostMapping
	ResponseEntity<?> addCar(@RequestBody Car car){
		return cs.addCar(car);
	}
	
	@ApiOperation("update car in the system")
	@PutMapping
	ResponseEntity<?> updateCar(@RequestBody Car car){
		return cs.updateCar(car);
	}
	
	@ApiOperation("delete car in the system")
	@DeleteMapping("/{id}")
	ResponseEntity<?> deleteCar(@PathVariable int id){
		return cs.deleteCar(id);
	}
	
}
