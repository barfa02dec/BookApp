package com.app.repositry;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.app.model.Book;

/**
 * Interface that implements CRUD repository for performing basic CRUD operation on book table
 * */
@Repository
public interface BookRepository extends CrudRepository<Book, Integer>{
	
}
