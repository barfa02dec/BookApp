package com.app.service;

import com.app.exception.BookException;
import com.app.model.User;

public interface UserServiceInf {	
	
	/************ insert User details ***************/
	public boolean registerUser(User usr) throws BookException;
	
	/************** get User details ***************/
	public User validateUser(String email,String pass) throws BookException;
	public User getUser(int user_id) throws BookException;
	public User getUserByEmail(String email) throws BookException;
	
	/*************** remove User details ************/
	public boolean removeUser(int user_id) throws BookException;
	
	/************** Update user details *************/
	public User updateUser(User usr) throws BookException;

}
