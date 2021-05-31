package com.bootcamp.santander.util;

import java.text.ParseException;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public abstract class ValidationUtils {
	public static boolean dateValidator(String date) {
		DateTimeFormatter parser = DateTimeFormatter.ofPattern("yyyy-MM-dd");

		try {
			parser.parse(date);
		} catch(DateTimeParseException exception) {
			return false;
		}

		return true;
	}
}
