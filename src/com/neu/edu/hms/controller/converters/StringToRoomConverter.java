package com.neu.edu.hms.controller.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

import com.neu.edu.hms.entity.Room;
import com.neu.edu.hms.services.GuestService;
import com.neu.edu.hms.services.GuestServiceImpl;

/**
 * @author aasha
 *
 */
public class StringToRoomConverter implements  Converter<String,Room>{
	
	@Autowired
	GuestService guestService;
	
	/**
	 * String (id of the room) to Room object conversion
	 */
	@Override
	public Room convert(String id) {
		 Room room = guestService.getRoomById(Integer.parseInt(id));
		 return room;
	}

}
