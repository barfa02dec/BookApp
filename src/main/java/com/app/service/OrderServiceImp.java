package com.app.service;

import java.util.List;
import java.util.NoSuchElementException;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.app.dao.OrderDaoInf;
import com.app.exception.BookException;
import com.app.model.Book;
import com.app.model.Orders;

@Service
@Transactional
/**
 *  Class that perform CRUD operations on orders table that related to user and book
 *  */
public class OrderServiceImp implements OrderServiceInf{
	
	@Autowired
	UserServiceInf user_Service;
	@Autowired
	BookServiceInf book_Service;	
	@Autowired
	OrderDaoInf dao;

	/**
	 *  taking user id and book id as input
	 *  if book is in stock decrease book stock and update it in book table
	 *  insert book and user details into order table
	 *  Roll back on RuntimeException while inserting data in order table
	 *	@throws BookException
	 * */
	@Transactional(rollbackOn = BookException.class)
	@Override
	public boolean placeOrder(int user_id, int book_id) throws BookException {
		try{
			Book book = (book_Service.getBookById(book_id));
			if(book.getStock()>0) {
				book.setStock(book.getStock()-1);
				book_Service.updateBook(book);
				return dao.placeOrder(user_Service.getUser(user_id),book);
			}else 
				throw new BookException("Out of Stock"); 
		}catch(BookException e) {
			throw e;
		}catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	/**
	 * taking user id get user details
	 * get all books booked by user
	 * delete all orders detail from order based on user id
	 * update stock for each deleted book
	 * return no of orders deleted
	 * @throws BookException
	 */
	@Override
	public int removeAllOrdersOfUser(int user_id) throws BookException{
		try {
			int removed_books = 0;
			List<Book> books = dao.getAllBooks(user_Service.getUser(user_id));
			for(Book b : books)
				removed_books += this.removeOrder(user_id, b.getBook_id());	
			return removed_books;
		}catch (BookException e) {
			throw e;
		}
		catch(NoSuchElementException e) {
			throw e;
		}
	}
	/**
	 * taking user id and book id
	 * increment stock of book
	 * delete order detail of user from order table
	 * @throws BookException
	 *  */
	@Transactional(rollbackOn = BookException.class)
	@Override
	public int removeOrder(int user_id, int book_id) throws BookException {
		try{
			Book book = book_Service.getBookById(book_id);
			int removed_books = dao.removeOrder(user_Service.getUser(user_id),book);
			book.setStock(book.getStock() + removed_books);
			book_Service.updateBook(book);
			return removed_books;
		}catch(BookException e) {
			throw e;
		}
	}
	
	/**
	 * taking user id
	 * return all the ordered books detail
	 * */
	@Override
	public List<Orders> getAllOrders(int user_id) throws BookException {
		return dao.getAllOrders(user_Service.getUser(user_id));
	}	
	
//	@Override
//	public boolean isOrderPlacedOfBookId(int book_id) throws BookException {
//		try {
//			return dao.isOrderPlacedOfBookId(book_Service.getBookById(book_id));
//		}catch(BookException e) {
//			throw e;
//		}
//	}
}