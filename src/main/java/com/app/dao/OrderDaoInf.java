package com.app.dao;

import java.util.List;

import com.app.exception.BookException;
import com.app.model.Book;
import com.app.model.Orders;
import com.app.model.User;

public interface OrderDaoInf {
	
	/**
	 * Insert order 
	 * */
	public boolean placeOrder(User user, Book book) throws BookException;

	
	/**
	 * get orders
	 */
	public List<Orders> getAllOrders(User user) throws BookException;
	public List<Book> getAllBooks(User user) throws BookException;
	//public boolean isOrderPlacedOfBookId(Book book) throws BookException;
	
	/**
	 * remove order
	 * */
	public int removeOrder(User user, Book book) throws BookException;
}
