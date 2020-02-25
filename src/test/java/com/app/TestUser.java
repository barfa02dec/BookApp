package com.app;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.app.controller.UserController;
import com.app.exception.BookException;
import com.app.model.User;

@SpringBootTest
public class TestUser {
	
	@Autowired
	UserController user_controller;
	
	@Test
	public void testRegistrationSuccessfully() throws BookException {
		User user = new User();
		user.setUser_id(111);
		user.setName("Barfa");
		user.setAddress("Madiwala");
		user.setContact("12345678901");
		user.setEmail("barfa02dec@gmail.com");
		user.setPassword("password");
		user.setRole("User");
		assertEquals(true, user_controller.registerUser(user));
		assertThrows(BookException.class, () -> user_controller.registerUser(user));

	}
	@Test
	public void testRegistrationWithOutValidation() throws BookException {
//		User empty_user = new User("","","","","","");
//		User null_user = new User(null,null,null,null,null,null);
//		User invalid_user = new User("Ba","pass","emailEmail","contact","BTM1","Role");
//		User invalid_user2 = new User("Barfs","password","dharmendra02dec@gmail.com","1234567891231","BTM1","Role");		
//		User valid_user = new User("Dharmendra","Password","dharmendra02dec@gmail.com","7067896709","BTM1","User"); 
		assertThrows(BookException.class, () -> user_controller.registerUser(new User("","","","","","")));
		assertThrows(BookException.class, () -> user_controller.registerUser(new User(null,null,null,null,null,null)));
		assertThrows(BookException.class, () -> user_controller.registerUser(new User("Ba","pass","emailEmail","contact","BTM1","Role")));
		assertThrows(BookException.class, () -> user_controller.registerUser(new User("Barfs","password","dharmendra02dec@gmail.com","1234567891231","BTM1","Role")));
		assertEquals(true, user_controller.registerUser( new User("Dharmendra","Password","dharmendra02dec@gmail.com","7067896709","BTM1","User")));

	}

	@Test
	public void testLogin() throws BookException{
		Map<String,String> json = new HashMap<>();
		Map<String,String> json1 = new HashMap<>();
		Map<String,String> json2 = new HashMap<>();
		Map<String,String> json3 = new HashMap<>();
		Map<String,String> json4 = new HashMap<>();
		
		json.put("email", "");
		json.put("pass", "");
		
		json1.put("email", null);
		json1.put("pass", null);
		
		json2.put("email", "hacom");
		json2.put("pass", "1");
		//Incorrect
		json3.put("email", "happiest@minds.com");
		json3.put("pass", "12345");
		//correct
		json4.put("email", "barfa02dec@gmail.com");
		json4.put("pass", "password");
		

		assertThrows(BookException.class,() -> user_controller.validateUser(json));
		assertThrows(BookException.class,() -> user_controller.validateUser(json1));
		assertThrows(BookException.class,() -> user_controller.validateUser(json2));
		assertThrows(BookException.class,() -> user_controller.validateUser(json3));
		
		User user = new User();
		user.setUser_id(111);
		user.setName("Barfa");
		user.setAddress("Madiwala");
		user.setContact("12345678901");
		user.setEmail("barfa02dec@gmail.com");
		user.setPassword("password");
		user.setRole("User");
		
		assertEquals(user,user_controller.validateUser(json4));
	}
	
	@Test
	public void testRemoveUser() throws BookException {
		//incorrect
		assertThrows(BookException.class,() -> user_controller.removeUser(0));
		//correct
		assertEquals(true, user_controller.removeUser(102));
	}
	
	@Test
	public void testUpdateUser() throws BookException{

		User user = new User();
		user.setUser_id(8);
		user.setName("Barfa");
		user.setAddress("Madiwala");
		user.setContact("12345678901");
		user.setEmail("happiest@minds.com");
		user.setPassword("123451");
		user.setRole("User");
		
		//user doesn't exist
		assertThrows(BookException.class, () -> user_controller.updateUser(user));
		
		//user exist
		user.setUser_id(1);
		assertEquals(user, user_controller.updateUser(user));
	}
	
	@Test
	public void testGetUser() throws BookException{
		//Invalid user
		assertThrows(BookException.class,() -> user_controller.getUser(6));
		
		//Valid user
		User user = new User();
		assertEquals(user, user_controller.getUser(101));	
	}
	
	@Test
	public void testGetUserByEmail() throws BookException{
		assertThrows(BookException.class,()->user_controller.getUserByEmail(""));
		assertThrows(BookException.class,()->user_controller.getUserByEmail(null));
		assertThrows(BookException.class,()->user_controller.getUserByEmail("barfagmail"));
	
		//Invalid user
		assertEquals(null,user_controller.getUserByEmail("barfa@gmail.com"));
		//valid user
		User user= new User();
		assertEquals(user,user_controller.getUserByEmail("barfa02dec@gmail.com"));
	}	
}

