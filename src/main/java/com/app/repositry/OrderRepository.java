package com.app.repositry;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.app.model.Orders;

/**
 * Interface that implements CRUD repository for performing basic CRUD operation on orders table
 * */
@Repository
public interface OrderRepository extends CrudRepository<Orders, Integer>{
	
}
