package com.bootcamp.santander.exceptions;

import java.util.function.Supplier;

public class NotFoundException extends RuntimeException {
	public NotFoundException(String message) {
		super(message);
	}
}
