package com.app.dao;

import java.util.List;

import com.app.exception.BookException;
import com.app.model.Book;

public interface BookDaoInf {

	/**
	 * 
	 * delete book
	 * 
	 * */
	
	public int removeBookByName(String name) throws BookException;

	
	/**
	 * 
	 * Filters on book
	 * 
	 * */
	
	public List<Book> getBookByName(String name) throws BookException;
	public List<Book> getBookByAuthorName(String author_name) throws BookException;
	public List<Book> getAllBooksByRating(float lower_rating,float upper_rating);
	public List<Book> getAllBooksByPrice(double lower_price,double upper_price);
	public List<Book> getAllBooksByCategories(String category_type);
	public List<Book> getAllBooksByLanguage(String language); 
	public List<Book> getAllBooksByLanguage(String language,float lower_rating,float upper_rating,double lower_price,double upper_price,String category);
	public List<Book> getBooksInStock() throws BookException;
	
	public boolean isOrderPlacedOfBookId(Book book) throws BookException;
}
