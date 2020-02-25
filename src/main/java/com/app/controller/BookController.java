package com.app.controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.app.exception.BookException;
import com.app.model.Book;
import com.app.service.BookServiceInf;
import com.app.validation.ValidateBookDetails;

@CrossOrigin(origins = "http://localhost:4200",allowedHeaders = "*")
@RestController
/**
 *  Controller that navigates between model and view for performing CRUD operation on Book table
 *  */
public class BookController {

	//service class variable for book
	@Autowired
	private BookServiceInf admin_sevice;
	
	/************************* insert ********************/
	/**
	 *  taking book details
	 *  Validate book details
	 *  return true if book successfully add into database
	 *  @throws BookException
	 *  @throws IOException
	 *  */
	@PostMapping("/add_book")
	public boolean addBook(@RequestParam(name = "image") MultipartFile image,@RequestParam(name = "book_id") int book_id,@RequestParam(name = "name") String name,
			@RequestParam(name = "rating") float rating,@RequestParam(name = "description") String description,
			@RequestParam(name = "author") String author,@RequestParam(name = "edition") Date edition,@RequestParam(name = "price") double price,
			@RequestParam(name = "publisher") String publisher,@RequestParam(name = "language") String language,@RequestParam(name = "stock") int stock,
			@RequestParam(name = "category") String category) throws IOException, BookException {
		try {
			Book book = new Book(book_id,name,rating,description,author,edition,price,publisher,language,stock,category);
			book.setImage(image.getBytes());
			return admin_sevice.addBook(book);	
		}catch (BookException e) {
			throw e;
		}
	}

	/************************ Get Books *****************/
	
	/**
	 *  return all books
	 *  */
	@GetMapping("/get_all_books")
	public @ResponseBody Iterable<Book> getAllBooks(){
		return admin_sevice.getAllBook();
	}
	/**
	 *  return books in stock
	 * @throws BookException 
	 *  */
	@GetMapping("/get_books_in_stock")
	public @ResponseBody Iterable<Book> getBooksInStock() throws BookException{
		return admin_sevice.getBooksInStock();
	}
	/** 
	 * taking book id
	 * check validation of book id
	 * return book details if book present
	 * @throws BookException
	 * */
	@GetMapping("/get_book_by_id")
	public @ResponseBody Book getBookById(@RequestParam(name = "id") int book_id) throws BookException {
		try{
			ValidateBookDetails.checkNullValue(book_id, "book_id");
			return admin_sevice.getBookById(book_id);
		}catch(BookException e) {
			throw e;
		}
	}
	/**
	 *  taking book name
	 *  check null and isEmpty for name 
	 *  return list of books
	 *  @throws BookException
	 *  */	
	@GetMapping("/get_book_by_name")
	public @ResponseBody List<Book> getBookByName(@RequestParam(name = "name") String name) throws BookException {
		try{
			ValidateBookDetails.checkNullValue(name, "name");
			return admin_sevice.getBookByName(name);
		}catch(BookException e) {
			throw e;
		}
	}
	/**
	 *  taking book author name
	 *  check null and isEmpty for author 
	 *  return list of books
	 *  @throws BookException
	 *  */
	@GetMapping
	public @ResponseBody List<Book> getBookByAuthorName(@RequestParam(name = "author") String author_name) throws BookException{
		try {
			ValidateBookDetails.checkNullValue(author_name, "author");
			return admin_sevice.getBookByAuthorName(author_name);
		}catch(BookException e) {
			throw e;
		}
	}
	/*********************** Remove Book ***********************/
	
	/** 
	 * taking book id and validate book id
	 * if book is already ordered throw exception
	 * else delete book from database by book Id 
	 * return true on success
	 * @throws BookException
	 * */
	@DeleteMapping("/remove_book")
	public boolean removeBook(@RequestParam(name = "id") int id) throws BookException {
		try{
			ValidateBookDetails.checkNullValue(id, "book_id");
			return admin_sevice.removeBook(id);		
		}catch(BookException e) {
			throw e;
		}
	}
	/**
	 * taking book name and check null and other validation
	 * delete book from database by book name
	 * return number of books deleted
	 * @throws BookException
	 * */
	
	@DeleteMapping("/remove_book_by_name")
	public @ResponseBody int removeBookByName(@RequestParam(name = "name") String name) throws BookException {
		try{
			ValidateBookDetails.checkNullValue(name, "name");
			return admin_sevice.removeBookByName(name);		
		}catch (BookException e) {
			throw e;
		}
	}

	/******************** Update Book ************************/
	
	/**
	 * Taking Book as input argument check validation of all fields
	 * Update book details if book available
	 * return updated details
	 * @throws BookException
	 * */
	@PostMapping("/update_book")
	public Book updateBook(@RequestParam(name = "image") MultipartFile image,@RequestParam(name = "name") String name,
			@RequestParam(name = "rating") float rating,@RequestParam(name = "description") String description,
			@RequestParam(name = "author") String author,@RequestParam(name = "edition") Date edition,@RequestParam(name = "price") double price,
			@RequestParam(name = "publisher") String publisher,@RequestParam(name = "language") String language,@RequestParam(name = "stock") int stock,
			@RequestParam(name = "category") String category,@RequestParam(name = "book_id")int book_id) throws IOException, BookException {
		try {
			Book book = new Book(book_id,name,rating,description,author,edition,price,publisher,language,stock,category);
			book.setImage(image.getBytes());
			return admin_sevice.updateBook(book);
		}catch(BookException e) {
			throw e;
		}
	}
	
	
	/************** Filters On Book *******************/
	
	/**
	 * taking upper and lower rating and validate arguments
	 * Filter book between upper and lower rating
	 * return list of book
	 * @throws BookException
	 * */
	@GetMapping("/get_book_by_rating")
	public List<Book> getAllBooksByRating(@RequestParam(name = "lower_rating")float lower_rating,@RequestParam(name = "upper_rating")float upper_rating) throws BookException{
		try{
			ValidateBookDetails.checkNullValue(lower_rating, "rating");
			ValidateBookDetails.checkNullValue(upper_rating, "rating");
			return admin_sevice.getAllBooksByRating(lower_rating, upper_rating);
		}catch(BookException e) {
			throw e;
		}
	}
	/**
	 * taking upper and lower price and validate arguments
	 * Filter book between upper and lower price
	 * return list of book
	 * @throws BookException
	 * */
	@GetMapping("/get_book_by_price")
	public List<Book> getAllBooksByPrice(@RequestParam(name = "lower_price")double lower_price,@RequestParam(name = "upper_price")double upper_price) throws BookException{
		try{
			ValidateBookDetails.checkNullValue(lower_price, "price");
			ValidateBookDetails.checkNullValue(upper_price, "price");
			return admin_sevice.getAllBooksByPrice(lower_price, upper_price);
		}catch(BookException e) {
			throw e;
		}
	}
	/**
	 * Filter book by category type and check validate null isEmpty validations
	 * return list of book
	 * @throws BookException
	 * */	
	@GetMapping("/get_book_by_category")
	public List<Book> getAllBooksByCategory(@RequestParam(name = "category")String category) throws BookException{
		try{
			ValidateBookDetails.checkNullValue(category, "category");
			return admin_sevice.getAllBooksByCategories(category);
		}catch(BookException e) {
			throw e;
		}
	}
	/**
	 * Filter book by language
	 * return list of book 
	 * @throws BookException 
	 * */	
	@GetMapping("/get_book_by_language")
	public List<Book> getAllBooksByLanguage(@RequestParam(name = "language")String language) throws BookException{
		try{
			ValidateBookDetails.checkNullValue(language, "language");
			return admin_sevice.getAllBooksByLanguage(language);
		}catch(BookException e) {
			throw e;
		}		
	}
	/**
	 * taking language,category,upper and lower price,rating and validate arguments
	 * filter book based on rating, price, language, category
	 * return list of book
	 * @throws BookException
	 * */
	@GetMapping("get_book_by_raing_price_category_language")
	public List<Book> getAllBooksByAllFilters(@RequestParam(name = "language")String language,
			@RequestParam(name = "lower_rating")float lower_rating,@RequestParam(name = "upper_rating")float upper_rating,
			@RequestParam(name = "lower_price")double lower_price,@RequestParam(name = "upper_price")double upper_price,
			@RequestParam(name = "category")String category) throws BookException{
		try {
			ValidateBookDetails.checkNullValue(language, "language");
			ValidateBookDetails.checkNullValue(lower_rating, "rating");
			ValidateBookDetails.checkNullValue(upper_rating, "rating");
			ValidateBookDetails.checkNullValue(lower_price, "price");
			ValidateBookDetails.checkNullValue(upper_price, "price");
			ValidateBookDetails.checkNullValue(category, "category");
			return admin_sevice.getAllBooksByLanguage(language,lower_rating,upper_rating,lower_price,upper_price,category);		
		}catch (BookException e) {
			throw e;
		}
	}
}
