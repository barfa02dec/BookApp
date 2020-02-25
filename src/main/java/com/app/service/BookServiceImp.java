package com.app.service;

import java.util.List;

import javax.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.transaction.UnexpectedRollbackException;
import org.springframework.transaction.annotation.Transactional;
import com.app.dao.BookDaoInf;
import com.app.exception.BookException;
import com.app.model.Book;
import com.app.repositry.BookRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Service
@Repository
@Transactional
/**
 * 	Class that perform basic CRUD Operation Using Repository or DAO on class Book
 * */
public class BookServiceImp implements BookServiceInf{

	@Autowired
	private BookRepository admin_repo;
	@Autowired
	private BookDaoInf dao;
	
	/******** Insert Book *************/
	/**
	 * Taking Book as an argument
	 * add book into database
	 * return true on success
	 * @throws BookException 
	 * */
	@Override
	public boolean addBook(Book book) throws BookException {
		try{
			admin_repo.save(book);
			return true;
		}catch (ConstraintViolationException cve) {
			throw new BookException("Null value can not be stored");
		}catch (UnexpectedRollbackException e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	/********** get Book details **************/
	
	/**
	 * return all books available in database
	 * */
	@Override
	public Iterable<Book> getAllBook() {
		return admin_repo.findAll();
	}
	
	/** 
	 * taking book id
	 * and return book detail based on book id
	 * @throws BookException
	 *  */
	@Override
	public Book getBookById(int book_id) throws BookException {
		return admin_repo.findById(book_id).orElseThrow(() -> new BookException("Book doesn't exist"));
	}
	
	/** 
	 * taking book name 
	 * return list of books based on book name
	 * @throws BookException
	 * */
	@Override
	public List<Book> getBookByName(String name) throws BookException {
		return dao.getBookByName(name);
	}
	/** 
	 * return list of books in stock
	 * @throws BookException
	 * */
	@Override
	public List<Book> getBooksInStock() throws BookException {
		return dao.getBooksInStock();
	}
	/** 
	 * taking book author name 
	 * return list of books
	 * */
	public List<Book> getBookByAuthorName(String author_name) throws BookException{
		return dao.getBookByAuthorName(author_name);
	}
	/***************************** delete Book *********************/

	/** 
	 * taking book id 
	 * if book is already ordered throw exception
	 * else delete book from database by book Id 
	 * return true on success
	 * @throws BookException
	 * */
	@Override
	public boolean removeBook(Integer book_id) throws BookException {
//		if(order_service.isOrderPlacedOfBookId(book_id))
//			throw new BookException("You can not remove book beacause it's already ordered");
		if(this.isOrderPlacedOfBookId(book_id))
			throw new BookException("You can not remove book beacause it's already ordered");
		try {
			admin_repo.deleteById(book_id);
			return true;
		}catch(EmptyResultDataAccessException erdae) {
			throw new BookException("Book doesn't exist");
		}
	}
	
	/**
	 * taking book name
	 * delete book from database by book name
	 * return number of books deleted
	 * @throws BookException
	 * */
	@Override
	public int removeBookByName(String name) throws BookException {
		  return dao.removeBookByName(name);
	}
		
	/************************** update Book ****************************/
	/**
	 * Taking Book as input argument
	 * Update book details if book available
	 * return updated details
	 * @throws BookException
	 * */
	@Override
	
//	@Transactional(rollbackFor = BookException.class)
	public Book updateBook(Book book) throws BookException {
		try {
			if(this.getBookById(book.getBook_id()) !=null)
				return admin_repo.save(book);
			else
				throw new BookException("Book doesn't exist");
		}catch (BookException e) {
			throw e;
		} catch (RuntimeException e) {
			throw e;
		}
	}
	
	/**************** Filters *****************/

	/**
	 * taking upper and lower rating
	 * Filter book between upper and lower rating
	 * return list of book
	 * */
	@Override
	public List<Book> getAllBooksByRating(float lower_rating, float upper_rating) {
		return dao.getAllBooksByRating(lower_rating, upper_rating);
	}
	/**
	 * taking upper and lower price
	 * Filter book between upper and lower price
	 * return list of book
	 * */
	@Override
	public List<Book> getAllBooksByPrice(double lower_price, double upper_price) {
		return dao.getAllBooksByPrice(lower_price, upper_price);
	}
	/**
	 * Filter book by category type
	 * return list of book
	 * */
	@Override
	public List<Book> getAllBooksByCategories(String category_type) {
		return dao.getAllBooksByCategories(category_type);
	}
	/**
	 * Filter book by language 
	 * return list of book
	 * */	
	@Override
	public List<Book> getAllBooksByLanguage(String language) {
		return dao.getAllBooksByLanguage(language);
	}
	/**
	 * taking language,category,upper and lower price,rating 
	 * filter book based on rating, price, language, category
	 * return list of book
	 * */
	@Override
	public List<Book> getAllBooksByLanguage(String language,float lower_rating,float upper_rating,double lower_price,double upper_price,String category){
		return dao.getAllBooksByLanguage(language,lower_rating,upper_rating,lower_price,upper_price,category);
	}
	/**
	 * taking book id as argument
	 * check order is placed for given book id
	 * return true if order is placed
	 * @throws BookException
	 */
	@Override
	public boolean isOrderPlacedOfBookId(int book_id) throws BookException {
		try {
			return dao.isOrderPlacedOfBookId(this.getBookById(book_id));
		}catch(BookException e) {
			throw e;
		}
	}	
}