package com.app.validation;

import java.util.Optional;
import com.app.exception.BookException;

/**
 * Performing validation on fields of Book class
 */
public class ValidateBookDetails {
	/**
	 * taking filed value and field name as input arguments
	 * check null value for each field of book table
	 * check other validation based on field type
	 * @param obj
	 * @param field
	 * @throws BookException
	 */
	public static void checkNullValue(Object obj,String field) throws BookException {
		Optional<Object> optional_obj = Optional.ofNullable(obj);
		optional_obj.orElseThrow(() -> new BookException( field + " is null!!!!!!"));
		if(obj instanceof String) {
			checkZeroLength((String)obj,field);
		}
		if(field == "name")
			validateName((String) obj);
		else if(field == "rating")
			validateRating((float) obj);
		else if(field=="price")
			validatePrice((double) obj);
		else if(field=="stock")
			validateStock((int) obj);
		
	} 
	/**
	 * taking filed value and field name as input arguments
	 * check validation for string isEmpty
	 * @param obj
	 * @param field
	 * @throws BookException
	 */
	public static void checkZeroLength(String obj,String field) throws BookException {
		if(obj.length()==0)
			throw new BookException("Zero length of "+field);	
	}
	/**
	 * taking name of book
	 * performing validation on book name
	 * perform 
	 * @param name
	 * @throws BookException
	 */
	public static void validateName(String name) throws BookException {
		if(!name.matches("^[a-zA-Z]*$"))
			throw new BookException("Name contains only Alphabets");
	}
	/**
	 * taking rating of book
	 * performing validation on book rating
	 * @param rating
	 * @throws BookException
	 */
	public static void validateRating(float rating) throws BookException {
		if(rating < 0 || rating > 5) {
			throw new BookException("Rate between 0 and 5");
		}
	}
	public static void validatePrice(double price) throws BookException {
		if(price < 10 || price > 5000)
			throw new BookException("Invalid price of book");
	}
	public static void validateStock(int stock) throws BookException {
		if(stock < 0) {
			throw new BookException("Stock can not be negative");
		}
	}
}
