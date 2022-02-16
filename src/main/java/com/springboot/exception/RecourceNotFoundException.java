package com.springboot.exception;

import javax.management.RuntimeErrorException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class RecourceNotFoundException extends RuntimeErrorException {

	public RecourceNotFoundException(Error e, String message) {
		super(e, message);
		// TODO Auto-generated constructor stub
	}

	public static final Long serialVersionUID = 1L;

}
