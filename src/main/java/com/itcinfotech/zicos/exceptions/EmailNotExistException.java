package com.itcinfotech.zicos.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.OK,reason="No User Exists with given mail, Please enter valid email id.")
public class EmailNotExistException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}