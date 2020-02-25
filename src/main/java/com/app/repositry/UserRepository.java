package com.app.repositry;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.app.model.User;

/**
 * Interface that implements CRUD repository for performing basic CRUD operation on user table
 * */
@Repository
public interface UserRepository extends CrudRepository<User, Integer>{

}
