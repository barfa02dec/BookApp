package com.app.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.app.exception.BookException;
import com.app.model.Orders;
import com.app.service.OrderServiceInf;
import com.app.validation.ValidateOrderData;

@RestController
/**
 *  Controller for navigation between model and view 
 *  performs Booking operations that related to User and Book
 *  */
public class OrderController {

	@Autowired
	OrderServiceInf service;
	
	/**
	 * taking user id,book id and status of the order
	 *  validate input arguments
	 *  if book is in stock decrease book stock and update it in book table
	 *  insert book and user details into order table 
	 *	@throws BookException
	 * */
	@PostMapping("/place_order")
	public @ResponseBody boolean placeOrder(@RequestParam (name = "user_id") int user_id,@RequestParam (name = "book_id") int book_id) throws BookException {
		try{
			ValidateOrderData.checkDataValidation(user_id, "user_id");
			ValidateOrderData.checkDataValidation(book_id, "book_id");
			return service.placeOrder(user_id, book_id);
		}catch(BookException e) {
			throw e;
		}
	}
	/**
	 * taking user id and book id and check null value
	 * increment stock of book
	 * delete order detail of user from order table
	 * @throws BookException
	 */
	@DeleteMapping("/remove_order")
	public @ResponseBody int removeOrder(@RequestParam (name = "user_id") int user_id,@RequestParam (name = "book_id") int book_id) throws BookException {
		try{
			ValidateOrderData.checkDataValidation(user_id, "user_id");
			ValidateOrderData.checkDataValidation(book_id, "book_id");
			return service.removeOrder(user_id, book_id);
		}catch (BookException e) {
			throw e;
		}
	}
	/**
	 *  taking user id 
	 *  return the all order details related to user id
	 *  */
	@GetMapping("/all_orders")
	public @ResponseBody List<Orders> getAllOrders(@RequestParam (name = "user_id") int user_id) throws BookException{
		try {
			ValidateOrderData.checkDataValidation(user_id, "user_id");	
			return service.getAllOrders(user_id);
		}catch (BookException e) {
			throw e;
		}
	}
	
	
	

	/**
	 * taking user id and validate
	 * delete all orders details from order based on user id
	 * return no of orders deleted
	 * @throws BookException
	 */
	@DeleteMapping("/remove_all_orders")
	public @ResponseBody int removeAllOrdersOfUser(@RequestParam (name = "user_id") int user_id) throws BookException {
		try{
			ValidateOrderData.checkDataValidation(user_id, "user_id");
			return service.removeAllOrdersOfUser(user_id);
		}catch (BookException e) {
			throw e;
		}
	}
}
