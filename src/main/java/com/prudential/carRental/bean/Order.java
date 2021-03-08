package com.prudential.carRental.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="orders")
public class Order {

	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	int id;
	
	@Column(name="start_time")
	private String startTime;
	
	@Column(name="return_time")
	private String returnTime;
	
	@Column(name="car_id")
	private int carId;
	
	@Column(name="customer_id")
	private int customerId;
	
	
	
}
