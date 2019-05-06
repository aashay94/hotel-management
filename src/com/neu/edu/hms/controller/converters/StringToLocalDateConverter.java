package com.neu.edu.hms.controller.converters;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.core.convert.converter.Converter;

/**
 * @author aasha
 *
 */
public class StringToLocalDateConverter implements Converter<String, LocalDate>{

	/**
	 * String to LocalDate conversion. 
	 */
	@Override
	public LocalDate convert(String dateString) {
		return LocalDate.parse(dateString, DateTimeFormatter.ISO_DATE);
	}

}
