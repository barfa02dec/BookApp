package com.app.validation;

import java.util.Optional;
import com.app.exception.BookException;
/**
 * class that perform validation on Orders POJO 
 */
public class ValidateOrderData {
	/**
	 * check null value for each field of order table
	 * check isEmpty for string field of order class
	 * @param obj
	 * @param field
	 * @throws BookException
	 */
	public static void checkDataValidation(Object obj,String field) throws BookException {
		Optional<Object> optional_obj = Optional.ofNullable(obj);
		optional_obj.orElseThrow(() -> new BookException("Null value not allowed for field " + field));
		if(obj instanceof String) 
			checkEmptyData((String)obj,field);		
	}
	/**
	 * check isEmpty for string field of order class
	 * @param obj
	 * @param field
	 * @throws BookException
	 */
	public static void checkEmptyData(String obj,String field) throws BookException {
		if(obj.length()==0)
			throw new BookException("Zero length of "+field);	
	}

}
