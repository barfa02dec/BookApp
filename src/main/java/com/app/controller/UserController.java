package com.app.controller;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.app.exception.BookException;
import com.app.model.User;
import com.app.service.UserServiceInf;
import com.app.validation.ValidateUserData;

@CrossOrigin(origins = "http://localhost:4200",allowedHeaders = "*")
@RestController
/**
 * Controller that performs CRUD operation that related to User
 * Work as navigator between model and view
 * */
public class UserController {
	
	@Autowired
	UserServiceInf service;
	
	/**
	 *  taking User data
	 *  Validate user data
	 *  return true if data successfully register into user table
	 * @throws BookException 
	 *  */
	
	@PostMapping("/user_add")
	public boolean registerUser(@RequestBody User usr) throws BookException {
		return service.registerUser(usr);
	}
	
	/**
	 *  taking user name and password and check validation 
	 *  return user detail if user is valid(expect password)
	 *  @throws BookException
	 *  */ 
	@PostMapping("/user_validet")
	public User validateUser(@RequestBody Map<String, String> json) throws BookException {
		try{
			ValidateUserData.checkNullValue(json.get("email"),"email");
			ValidateUserData.checkNullValue(json.get("pass"),"password");
			return service.validateUser(json.get("email"), json.get("pass"));
		}catch(BookException e) {
			throw e;
		}
		
	}
	/**
	 *  Taking the user id and check validation on user id
	 *  delete the details of user from user table if available
	 *  return true if user data deleted
	 *  @throws BookException 
	 *  */
	@DeleteMapping("/user_remove")
	public boolean removeUser(@RequestParam(name = "user_id") int user_id) throws BookException {
		try{
			ValidateUserData.checkNullValue(user_id, "user_id");
			return service.removeUser(user_id);
		}catch(BookException e) {
			throw e;
		}
	}
	/**
	 * Taking the new user details and check validation on all fields of user
	 * update user details if user is exist
	 * return updated details
	 * @throws BookException
	 * */
	@PutMapping("/user_update")
	public User updateUser(@RequestBody User usr) throws BookException {
		return service.updateUser(usr);
	}
	
	/**
	 *  taking user_id check validation
	 *  return user details
	 *  @throws BookException
	 *  */
	@GetMapping("/get_user")
	public User getUser(@RequestParam(name = "id") int user_id) throws BookException {
		ValidateUserData.checkNullValue(user_id, "user_id");
		return service.getUser(user_id);
	}
	
	@GetMapping("/get_user_by_email")
	public User getUserByEmail(String email) throws BookException{
		try {
			ValidateUserData.checkNullValue(email, "email");
			return service.getUserByEmail(email);
		}catch(BookException e) {
			throw e;
		}
	}
	

	/**
	 * test null value using optional class 
	 * @param json
	 * @throws BookException
	 */
	
//	@PostMapping("/test_optional")
//	public void test(@RequestBody Map<String,String> json ) throws BookException{
//		String contact = json.get("contact");
//		User u = new User();
//		u.setContact(contact);
//	}

	/**
	 * 
	 *Test how to pass multiple objects to Controller in request body
	 */
//	@PostMapping("/test")
//	public void testJsonArg(@RequestBody Map<String,String> json) {
//		System.out.println(json.get("id").getClass());
//		System.out.println(json.get("name").getClass());
//		System.out.println(json.get("rating").getClass());
//	}
	
}
