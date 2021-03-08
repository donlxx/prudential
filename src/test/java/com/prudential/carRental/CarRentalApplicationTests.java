package com.prudential.carRental;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import com.prudential.carRental.config.CarRentalTestConfig;

@SpringBootTest
@ContextConfiguration(classes = {CarRentalTestConfig.class})
class CarRentalApplicationTests {

	@Test
	void contextLoads() {
	}

}
