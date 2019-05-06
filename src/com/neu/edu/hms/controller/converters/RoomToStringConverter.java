package com.neu.edu.hms.controller.converters;

import org.springframework.core.convert.converter.Converter;

import com.neu.edu.hms.entity.Room;

/**
 * @author aasha
 *
 */

public class RoomToStringConverter  implements  Converter<Room,String>{

	/**
	 * String to Room conversion
	 */
	@Override
	public String convert(Room room) {
		return String.valueOf(room.getStandard() + " " + room.getNumber());
	}

}
