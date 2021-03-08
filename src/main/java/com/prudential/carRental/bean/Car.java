package com.prudential.carRental.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;


@Entity
@Table(name="car")
@Data
public class Car {
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	int id;
	@Column(name="quantity")
	int quantity;
	@Column(name="car_name")
	String name;
	
}
