package com.app.dao;

import com.app.exception.BookException;
import com.app.model.User;

public interface UserDaoInf {
	/**
	 * get User
	 * */
	public User validateUser(String email, String pass) throws BookException;
	public User getUserByEmail(String email) throws BookException;
}
