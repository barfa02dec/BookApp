package com.app.dao;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.app.exception.BookException;
import com.app.model.User;

@Repository
/**
 * Class that perform CRUD operation using entity manager on user table
 * */
public class UserDaoImp implements UserDaoInf{


	@Autowired
	EntityManager entity_manager;
	
	
	/**
	 * taking email id and password
	 * return user details if user details is available
	 * @throws BookException
	 * */
	@Override
	public User validateUser(String email, String pass) throws BookException{	
		try {
			return entity_manager.createQuery("select u from User u where u.email = :email and u.password = :password", User.class).setParameter("email", email).setParameter("password", pass).getSingleResult();		
		}catch (NoResultException e) {
			throw new BookException("Invalid user and password");
		}
	}
	/**
	 * taking email id
	 * return user if email id exist
	 * @throws BookException 
	 */
	@Override
	public User getUserByEmail(String email) throws BookException{
		try {
			return entity_manager.createQuery("select u from User u where u.email = :email", User.class).setParameter("email", email).getSingleResult();		
		}catch (NoResultException e) {
			return null;
 		}
	}
}
