package com.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.app.exception.BookException;

@SpringBootApplication
public class BookLibApplication {

	public static void main(String[] args) throws BookException {
		SpringApplication.run(BookLibApplication.class, args);
	}

}
