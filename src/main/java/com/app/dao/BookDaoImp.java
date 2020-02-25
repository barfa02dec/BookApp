package com.app.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.app.exception.BookException;
import com.app.model.Book;
import com.app.model.Orders;

@Repository
@Transactional
/**
 * Class that perform CRUD operation using entity manager on Book table
 * */
public class BookDaoImp implements BookDaoInf {

	@Autowired
	private EntityManager entity_manager;
	
	/************* get methods ***************/

	/**
	 *  taking book name
	 *  return list of books based on book name 
	 *  */
	@Override
	public List<Book> getBookByName(String name) {
		return entity_manager.createQuery("select b from Book b where b.name = :name",Book.class).setParameter("name", name).getResultList();
	}
	
	/**
	 *  return in stock list of books 
	 *  */
	@Override
	public List<Book> getBooksInStock() {
		return entity_manager.createQuery("select b from Book b where b.stock > 0",Book.class).getResultList();
	}
	
	/**
	 *  taking book author name
	 *  return list of books 
	 *  */
	@Override
	public List<Book> getBookByAuthorName(String author_name) throws BookException{
		return entity_manager.createQuery("select b from Book b where b.author = :author_name",Book.class).setParameter("author_name", author_name).getResultList();		
	}
	/************** delete book **********/
	/**
	 * delete book from database by book name
	 * return number of books delete
	 * */
	@Override
	public int removeBookByName(String name) throws BookException {
		  return entity_manager.createQuery("DELETE FROM Book b WHERE b.name = :name").setParameter("name", name).executeUpdate();
	}
	
	/*************** Filters on book ****************/
	/**
	 * Filter book by rating between upper and lower rating
	 *  return list of book
	 * */
	@Override
	public List<Book> getAllBooksByRating(float lower_rating, float upper_rating) {
		return entity_manager.createQuery("SELECT b FROM Book b WHERE b.rating BETWEEN :lower_rating AND :upper_rating",Book.class).setParameter("lower_rating",lower_rating).setParameter("upper_rating", upper_rating).getResultList();
	}
	
	/**
	 *  Filter book by price between upper and lower price
	 *  return list of book
	 * */
	@Override
	public List<Book> getAllBooksByPrice(double lower_price, double upper_price) {
		return entity_manager.createQuery("SELECT b FROM Book b WHERE b.price BETWEEN :lower_price AND :upper_price",Book.class).setParameter("lower_price",lower_price).setParameter("upper_price", upper_price).getResultList();
	}
	/**
	 *  Filter book by category 
	 *  list of book
	 * */
	@Override
	public List<Book> getAllBooksByCategories(String category_type) {
		return entity_manager.createQuery("SELECT b FROM Book b WHERE b.category = :category_type",Book.class).setParameter("category_type",category_type).getResultList();
	}
	/**
	 * Filter book by language
	 * return list of book
	 * */
	@Override
	public List<Book> getAllBooksByLanguage(String language) {
		return this.entity_manager.createQuery("SELECT b FROM Book b WHERE b.language = :language",Book.class).setParameter("language", language).getResultList();
	}
	/**
	 *  filter book by rating, price, language, category 
	 *  return list of book
	 *  */
	@Override
	public List<Book> getAllBooksByLanguage(String language, float lower_rating, float upper_rating, double lower_price,
			double upper_price, String category) {
		return this.entity_manager.createQuery("SELECT b FROM Book b WHERE b.language = :language AND b.price BETWEEN :lower_price AND :upper_price"
				+ " AND b.rating BETWEEN :lower_rating AND :upper_rating AND b.category = :category",Book.class).setParameter("language", language).
				setParameter("lower_price",lower_price).setParameter("upper_price", upper_price).setParameter("lower_rating",lower_rating).
				setParameter("upper_rating", upper_rating).setParameter("category",category).getResultList();
	}

	/**
	 * taking book id as argument
	 * check order is placed for given book id
	 * return true if order is placed
	 * @throws BookException
	 */
	@Override
	public boolean isOrderPlacedOfBookId(Book book) throws BookException{
		return !entity_manager.createQuery("select o from Orders o where o.book_id = :book",Orders.class).setParameter("book", book).getResultList().isEmpty(); 
	}
}
