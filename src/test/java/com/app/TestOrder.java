package com.app;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.app.controller.OrderController;
import com.app.exception.BookException;
import com.app.model.Book;
import com.app.model.Orders;

@SpringBootTest
public class TestOrder {
	
	@Autowired
	OrderController order_controller;

	@Test
	public void testPlaceOrder() throws BookException {

		//Invalid User id and Book id
//		assertThrows(BookException.class,() -> order_controller.placeOrder(100,100));
		//Invalid User id and valid Book id
//		assertThrows(BookException.class,() -> order_controller.placeOrder(100,75));
		//Valid User id and Invalid Book id
//		assertThrows(BookException.class,() -> order_controller.placeOrder(101,100));
		
		//valid User and Book id
//		assertEquals(true, order_controller.placeOrder(101,75));
		
		//valid user and book with throwing book exception for test is roll back working 
//		assertEquals(true, order_controller.placeOrder(101,76));
	}
	
	@Test
	public void testGetAllOrders() throws BookException {
		//Invalid user
//		assertThrows(BookException.class,()->order_controller.getAllOrders(1));
		//Valid user without order
		List<Orders> orders = new ArrayList<>();
//		assertEquals(orders,order_controller.getAllOrders(103));
		//valid user with orders
//		assertEquals(orders,order_controller.getAllOrders(101));
	}
	@Test
	public void testRemoveOrder() throws BookException{
		//Invalid user id and book id
//		assertThrows(BookException.class,()->order_controller.removeOrder(1,1));
		//Valid user id but without order
		//Valid book id but without order
//		assertEquals(0,order_controller.removeOrder(103,60));
		//Valid user id but with order
		//Valid book id but without order		
//		assertEquals(0,order_controller.removeOrder(101,60));
		//Valid user id but without order
		//Valid book id but with order
//		assertEquals(0,order_controller.removeOrder(103,23));

		//Valid user id but with order
		//Valid book id but with order
//		assertEquals(0,order_controller.removeOrder(101,23));
		
	}
	@Test
	public void testRemoveAllOrdersOfUser() throws BookException{
	//	assertEquals(1,order_controller.removeAllOrdersOfUser(101));
	}
	
}
