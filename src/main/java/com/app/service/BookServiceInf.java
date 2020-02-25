package com.app.service;

import java.util.List;
import com.app.exception.BookException;
import com.app.model.Book;

public interface BookServiceInf {
	
	/********** Insert Book **********/
	
	public boolean addBook(Book book) throws BookException;
	
	/************ Select Books **********/
	
	public Iterable<Book> getAllBook();	
	public List<Book> getBooksInStock() throws BookException;
	public Book getBookById(int book_id) throws BookException;
	public List<Book> getBookByName(String name) throws BookException;
	public List<Book> getBookByAuthorName(String author_name) throws BookException;
	public boolean isOrderPlacedOfBookId(int book_id) throws BookException;
	/************ Remove Book **************/
	public boolean removeBook(Integer book_id) throws BookException;
	public int removeBookByName(String name) throws BookException;
	
	/******** Update Book ************/
	public Book updateBook(Book book) throws BookException;
	
	
	/***************** Filters on Book ***********/
	public List<Book> getAllBooksByRating(float lower_rating,float upper_rating);
	public List<Book> getAllBooksByPrice(double lower_price,double upper_price);
	public List<Book> getAllBooksByCategories(String category_type);
	public List<Book> getAllBooksByLanguage(String language);
	public List<Book> getAllBooksByLanguage(String language,float lower_rating,float upper_rating,double lower_price,double upper_price,String category);
	
}