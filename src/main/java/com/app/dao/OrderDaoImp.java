package com.app.dao;

import java.time.LocalDate;
import java.util.List;
import javax.persistence.EntityManager;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.app.exception.BookException;
import com.app.model.Book;
import com.app.model.Orders;
import com.app.model.User;

@Repository
/**
 *   Class that perform CRUD operations on orders table that related to user and book using entity manager   
 */
public class OrderDaoImp implements OrderDaoInf{
	
	@Autowired
	EntityManager entity_manager;
	
	/**
	 * Returns HIBERNATE session
	 * */
	private Session getSession() {
		return entity_manager.unwrap(Session.class);
	}

	/**
	 *  taking user and book as input
	 *  insert details into orders table
	 *	@throws BookException
	 * */
	@Override
	public boolean placeOrder(User user, Book book) throws BookException {
		/*
		 Try block for check roll back is working for BookException class
		 */
//		try{
//			throw new BookException("");
		try{
			this.getSession().save(new Orders( LocalDate.now().plusDays(7),"placed",book,user));
			return true;
		}catch(RuntimeException e) {
			throw new BookException("");
		}
	}
	
	
	/**
	 * taking user details
	 * return all books booked by user
	 * @throws BookException
	 */	
	@SuppressWarnings("unchecked")
	@Override
	public List<Book> getAllBooks(User user) throws BookException{
		return this.getSession().createQuery("SELECT DISTINCT book_id FROM Orders o WHERE o.user_id = :user").setParameter("user", user).getResultList();
	}
	
	
	/**
	 * Taking book details and user details
	 * delete orders of user 
	 * return no of orders deleted
	 * @throws BookException
	 */
	@Override
	public int removeOrder(User user, Book book) throws BookException {
		try{
			return this.getSession().createQuery("DELETE FROM Orders o WHERE o.user_id = :user and o.book_id = :book").setParameter("user", user).setParameter("book", book).executeUpdate();			
		}catch (RuntimeException e) {
			throw new BookException("");
		}
	}
	/**
	 * taking user details
	 * return all ordered books details 
	 * */
	@Override
	public List<Orders> getAllOrders(User user) throws BookException {
		return entity_manager.createQuery("select o from Orders o where o.user_id = :user",Orders.class).setParameter("user", user).getResultList();
	}
	
//	@Override
//	public boolean isOrderPlacedOfBookId(Book book) throws BookException{
//		if(entity_manager.createQuery("select o from Orders o where o.book_id = :book",Orders.class).setParameter("book", book).getResultList()!=null) 
//			return true;
//		return false;
//	}
}
