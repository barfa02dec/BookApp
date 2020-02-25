package com.app.service;

import java.util.NoSuchElementException;
import javax.transaction.Transactional;
import javax.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.UnexpectedRollbackException;
import com.app.dao.UserDaoInf;
import com.app.exception.BookException;
import com.app.model.User;
import com.app.repositry.UserRepository;

@Service
@Transactional
@Repository
/**
 * Class that perform basic CRUD Operation Using Repository or DAO on User table
 * */
public class UserServiceImp implements UserServiceInf{

	@Autowired
	UserRepository user_repo;
	@Autowired
	UserDaoInf dao;
	@Autowired
	OrderServiceInf order_service;

	/*********** insert User details ***********/
	
	/**
	 * Taking User details and check email id already exist or not
	 * add details into database
	 * return true on success
	 * @throws BookException 
	 * */
	@Override
	public boolean registerUser(User usr) throws BookException {
		try{
			if(this.getUserByEmail(usr.getEmail())==null)
				user_repo.save(usr);
			else
				throw new BookException("Email id already exist");
		}catch (ConstraintViolationException cve) {
			throw new BookException("Null value can not be stored");
		}catch (UnexpectedRollbackException e) {
			e.printStackTrace();
		}
		return true;
	}

	/***************** get User details ***************/
	
	/**
	 * taking email id and password 
	 * return user details if user details is available
	 * @throws BookException
	 * */
	@Override
	public User validateUser(String email, String pass) throws BookException {	
		try{
			return dao.validateUser(email, pass);
		}catch(BookException e) {
			throw e;
		}
	}
	
	/**
	 * Taking user id 
	 * return user details
	 * @throws BookException
	 * */
	@Override
	public User getUser(int user_id) throws BookException {
		return user_repo.findById(user_id).orElseThrow(()->new BookException ("Invalid User"));
	}
	/**
	 * taking email id
	 * return user detail if available
	 * @throws BookException
	 */
	@Override
	public User getUserByEmail(String email) throws BookException{
		try {
			return dao.getUserByEmail(email);
		}catch (BookException e) {
			throw e;
		}
	}
	/************* remove User details *****************/
	
	/**
	 * Taking user id
	 * first delete user data from order table if available 
	 * then delete user from user data
	 * return true on success
	 * @throws BookException
	 * */
	@Override
	public boolean removeUser(int user_id) throws BookException {
		try {
			order_service.removeAllOrdersOfUser(user_id);
		}catch (NoSuchElementException e) {
			System.out.println("No order data available of user id " + user_id);
		}
		try {
			user_repo.deleteById(user_id);
			return true;
		}catch(EmptyResultDataAccessException erdae) {
			throw new BookException("Invalid User");
		}
	}
	
	/************** Update user details *******************/
	
	/**
	 * Taking new details of user
	 * Replace old user by new user data if old user available  
	 * returns updated user details
	 * @throws BookException
	 * */
	@Override
	public User updateUser(User usr) throws BookException {	
		try{
			return user_repo.save(this.getUser(usr.getUser_id()));
		}catch (BookException e) {
			throw e;
		}
	}	
}