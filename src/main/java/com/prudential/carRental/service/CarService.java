package com.prudential.carRental.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.prudential.carRental.bean.Car;
import com.prudential.carRental.constant.AppContants;
import com.prudential.carRental.repository.CarDao;

@Service
public class CarService {
	@Autowired
	private CarDao cd;
	
	public Car getById(int id) {
		return cd.findById(id).get();
	
	}
	public List<Car> getAll(){
		return cd.findAll();
	}
	public ResponseEntity<?> addCar(Car car){
		cd.save(car);
		return new ResponseEntity<>(AppContants.SUCCESS,HttpStatus.OK);
	}
	public ResponseEntity<?> updateCar(Car car){
		if(cd.findById(car.getId()).isEmpty()) {
			return new ResponseEntity<>(AppContants.DONOT_HAVE_SUCH_ID,HttpStatus.BAD_REQUEST);
		}
		Car curCar=cd.findById(car.getId()).get();
		curCar.setName(car.getName());
		curCar.setQuantity(car.getQuantity());
		cd.save(curCar);
		return new ResponseEntity<>(AppContants.SUCCESS,HttpStatus.OK);
	}
	
	public ResponseEntity<?> deleteCar(int id){
		if(cd.findById(id).isEmpty()) {
			return new ResponseEntity<>(AppContants.DONOT_HAVE_SUCH_ID,HttpStatus.BAD_REQUEST);
		}
		cd.deleteById(id);
		return new ResponseEntity<>(AppContants.SUCCESS,HttpStatus.OK);
		
	}
}
