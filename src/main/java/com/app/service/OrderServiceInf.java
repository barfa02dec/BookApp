package com.app.service;

import java.util.List;

import com.app.exception.BookException;
import com.app.model.Orders;

public interface OrderServiceInf {
	
	/**
	 * Insert order
	 * */
	public boolean placeOrder(int user_id, int book_id) throws BookException;

	/**
	 *remove order
	 **/
	public int removeOrder(int user_id, int book_id) throws BookException;
	public int removeAllOrdersOfUser(int user_id) throws BookException;
	/**
	 * get orders 
	 */
	public List<Orders> getAllOrders(int user_id) throws BookException;
	//public boolean isOrderPlacedOfBookId(int book_id)throws BookException;
}
