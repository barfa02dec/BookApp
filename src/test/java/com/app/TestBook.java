package com.app;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.hibernate.exception.ConstraintViolationException;
import org.junit.jupiter.api.Test;
import org.mockito.internal.matchers.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.UnexpectedRollbackException;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.app.controller.BookController;
import com.app.controller.UserController;
import com.app.exception.BookException;
import com.app.model.Book;
import com.app.model.User;

@SpringBootTest
public class TestBook {
	
	@Autowired
	BookController book_controller;
	
	@Test
	public void testAddBook() throws BookException, IOException {
		Book book = new Book();
		Date date = new Date();
		byte [] image = {(byte)11};
		book.setName("Book");
		book.setAuthor("Author");
		book.setBook_id(11);
		book.setCategory("category");
		book.setDescription("description");
		book.setEdition(date);
		book.setImage(image);
		book.setLanguage("language");
		book.setPrice(111.1);
		book.setPublisher("publisher");
		book.setRating(1.1f);
		book.setStock(111);

//		assertThrows(BookException.class, () -> book_controller.addBook(1,null, 1.1f,null,null, date, -1121, null, null, -111, null));
//		assertThrows(BookException.class, () -> book_controller.addBook(111,"", 1.1f, "","", date, 111.1, "", "", 111, ""));
//		assertEquals(true, book_controller.addBook(111,"Amish", 1.1f, "Description","Author", date, 111.1, "publisher", "language", 3, "category"));
	}	
	@Test
	public void testGetAllBook() throws BookException{
		
//		assertThrows(BookException.class, () -> book_controller.getAllBooks());
		
		List<Book> b = new ArrayList<>();
//		assertEquals(b,book_controller.getAllBooks());
	}
	@Test
	public void testGetBookById() throws BookException, IOException{
		//Invalid data
//		assertThrows(BookException.class, () -> book_controller.getBookById(-1));
		Book b = new Book();
//		assertEquals(b,book_controller.getBookById(22));
	}
	@Test
	public void testGetBooksInStock() throws BookException, IOException{
//		assertEquals(null,book_controller.getBooksInStock());
	}
	@Test
	public void testGetBookByName() throws BookException{
//		assertThrows(Exception.class, () -> book_controller.getBookByName(""));
//		assertThrows(Exception.class, () -> book_controller.getBookByName(null));
		List<Book> b = null;
//		assertEquals(b,book_controller.getBookByName("Meluha"));
	}
	
	@Test
	public void testGetBookByAuthorName() throws BookException {

//		assertThrows(BookException.class, () -> book_controller.getBookByAuthorName(""));
//		assertThrows(BookException.class, () -> book_controller.getBookByAuthorName(null));
		
		//Invalid Author name
//		assertEquals(true,book_controller.getBookByAuthorName("Medhjk").isEmpty());
		
		//Valid Author name
//		assertEquals(null,book_controller.getBookByAuthorName("Author"));
	}
	@Test
	public void testRemoveBook() throws BookException{
//		assertThrows(BookException.class,() -> book_controller.removeBook(1));
//		assertThrows(BookException.class,() -> book_controller.removeBook(23));
		
		//order placed for book id
//		assertThrows(BookException.class,() -> book_controller.removeBook(23));
		//no order placed for book id
//		assertEquals(true,book_controller.removeBook(68));
	}

	@Test
	public void testRemoveBookByName() throws BookException{
		assertThrows(BookException.class,() -> book_controller.removeBookByName(""));
		assertThrows(BookException.class,() -> book_controller.removeBookByName(null));
		
		//Invalid name
//		assertEquals(0, book_controller.removeBookByName("Invalid"));
//		assertEquals(1, book_controller.removeBookByName("BookMyShop"));
	}
	@Test
	public void testUpdateBook() throws BookException, IOException{

		Book book = new Book();
		Date date = new Date();
		byte [] image = {(byte)11};
		book.setName("BookMyShop");
		book.setAuthor("Author");
		book.setBook_id(26);
		book.setCategory("category");
		book.setDescription("Description");
		book.setEdition(date);
		book.setImage(image);
		book.setLanguage("language");
		book.setPrice(111.1);
		book.setPublisher("publisher");
		book.setRating(1.1f);
		book.setStock(111);
//		assertThrows(BookException.class, () -> book_controller.updateBook("Book", 1.1f, "Description","Author", date, 111.1, "publisher", "language", 111, "category",22));
//		assertThrows(BookException.class, () -> book_controller.updateBook("Book", 1.1f, "Description","Author", date, 111.1, "publisher", "language", 111, "egffsg",26));
		
		//Invalid book
//		assertThrows(BookException.class, () -> book_controller.updateBook("Book", 1.1f, "Description","Author", date, 111.1, "publisher", "language", 111, "egffsg",26));
		
		//Valid book
//		assertEquals(book, book_controller.updateBook("BookMyShop", 1.1f, "Description","Author", date, 111.1, "publisher", "language", 111, "category",2));

	}
}

