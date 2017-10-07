package com.itcinfotech.pbb.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.FOUND,reason="This user is already exist in the system")
public class UserAlreadyExistException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
