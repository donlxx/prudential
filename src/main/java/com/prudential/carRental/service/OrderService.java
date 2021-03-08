package com.prudential.carRental.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.prudential.carRental.bean.Car;
import com.prudential.carRental.bean.Order;
import com.prudential.carRental.constant.AppContants;
import com.prudential.carRental.repository.CarDao;
import com.prudential.carRental.repository.OrderDao;

@Service
public class OrderService {
	
	@Autowired OrderDao orderDao;
	@Autowired CarDao carDao;
	@Autowired
	private CarService cs;

	public static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
    private static boolean isOverlap(String startdate1, String enddate1,String startdate2, String enddate2) {  
        Date leftStartDate = null;  
        Date leftEndDate = null;  
        Date rightStartDate = null;  
        Date rightEndDate = null;  
        try {  
            leftStartDate = format.parse(startdate1);  
            leftEndDate = format.parse(enddate1);  
            rightStartDate = format.parse(startdate2);  
            rightEndDate = format.parse(enddate2);  
        } catch (ParseException e) {  
            return false;  
        }  
          
        return   
            ((leftStartDate.getTime() >= rightStartDate.getTime())   
                    && leftStartDate.getTime() < rightEndDate.getTime())  
            ||  
            ((leftStartDate.getTime() > rightStartDate.getTime())   
                    && leftStartDate.getTime() <= rightEndDate.getTime())  
            ||  
            ((rightStartDate.getTime() >= leftStartDate.getTime())   
                    && rightStartDate.getTime() < leftEndDate.getTime())  
            ||  
            ((rightStartDate.getTime() > leftStartDate.getTime())   
                    && rightStartDate.getTime() <= leftEndDate.getTime());  
              
    } 
    
    
    public boolean checkCarQuantity(int carId) {
    	Car car = cs.getById(carId);
    	int quantity = car.getQuantity();
    	if(quantity - 1 < 0) {
    		return false;
    	}else {
    		return true;
    	}
    }
    
    @Transactional
    public String saveOrder(Order order){
    	List<Order> existingOrder = orderDao.findByCustomerId(order.getCustomerId());
    	//make sure one customer cannot place two order in the same time
    	for(Order o:existingOrder) {
    		if(isOverlap(o.getStartTime(),o.getReturnTime(),order.getStartTime(),order.getReturnTime())) {
    			return AppContants.ALREADY_HAS_CAR;
    		}
    	}
    	Car curCar=carDao.findById(order.getCarId()).get();
    	List<Order> allOrders = orderDao.findByCarId(order.getCarId());
    	System.out.println("*******in order service********");
    	System.out.println(curCar.getQuantity());
    	System.out.println(checkConflictCars(allOrders,order));
    	System.out.println("*******************************");
    	if(checkConflictCars(allOrders,order)>=curCar.getQuantity()) {
    		return AppContants.CAR_NOT_ENOUGH;
    	}
    	orderDao.save(order);
    	
    	return AppContants.SUCCESS;
    }
    
    public static int checkConflictCars(List<Order> orders, Order curOrder ){
    	int result=0;
    	for(Order o:orders) {
    		if(isOverlap(curOrder.getStartTime(),curOrder.getReturnTime(),o.getStartTime(),o.getReturnTime())) {
    			result++;
    		}
    	}
    	return result;
    }
    
//    @Transactional
//    public String saveOrder(Order order){
//    	List<Order> existingOrder = orderDao.findByCustomerId(order.getCustomerId());
//    	for(Order o:existingOrder) {
//    		if(isOverlap(o.getStartTime(),o.getReturnTime(),order.getStartTime(),order.getReturnTime())) {
//    			return AppContants.ALREADY_HAS_CAR;
//    		}
//    	}
//    	List<Order> carList = new ArrayList<Order>();
//    	carList.add(order);
//    	for(Order o : carList) {
//    		if(checkCarQuantity(o.getCarId())){
//    			orderDao.save(order);
//    			Car car = carDao.findById(o.getCarId()).get();
//    			carDao.updateCarQuantity(car.getQuantity()-1, o.getCarId());
//    		}else {
//    			return AppContants.CAR_NOT_ENOUGH;
//    		}
//    	}
//    	return AppContants.SUCCESS;
//    }

    public List<Order> getOrderByUserId(int id) {
    	return orderDao.findByCustomerId(id);
    }
    public List<Order> getAllOrders(){
    	return orderDao.findAll();
    }
    public ResponseEntity<?> deleteOrderById(int id){
    	if(orderDao.findById(id).isEmpty()) {
    		return new ResponseEntity<>(AppContants.DONOT_HAVE_SUCH_ID,HttpStatus.BAD_REQUEST);
    	}else {
    		orderDao.deleteById(id);
    		return new ResponseEntity<>(AppContants.SUCCESS,HttpStatus.OK);
    	}
    }
    
    public ResponseEntity<?> deleteAllOrder(){
    	
    		orderDao.deleteAll();
    		return new ResponseEntity<>(AppContants.SUCCESS,HttpStatus.OK);
    	
    }
    
}

