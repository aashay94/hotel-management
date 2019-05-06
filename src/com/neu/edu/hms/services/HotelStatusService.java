package com.neu.edu.hms.services;

import java.util.List;

import com.neu.edu.hms.entity.Guest;
import com.neu.edu.hms.entity.Room;

public interface HotelStatusService {
	
	public List<Room> getAllRooms();
	public List<Guest> getActualGuests();
}
