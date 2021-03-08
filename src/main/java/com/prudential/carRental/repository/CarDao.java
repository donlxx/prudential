package com.prudential.carRental.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.prudential.carRental.bean.Car;

public interface CarDao extends JpaRepository<Car,Integer>{

	@Query("update Car set quantity = :quantity where id =  :id")
	@Modifying
	void updateCarQuantity(@Param("quantity") int quantity, @Param("id") int id);
	
	
	//int findQuantityById(@Param("id") int id);
	
}