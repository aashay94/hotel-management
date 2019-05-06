package com.neu.edu.hms.services;

import java.util.List;


import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neu.edu.hms.DAO.GuestDAO;
import com.neu.edu.hms.DAO.RoomDAO;
import com.neu.edu.hms.entity.Guest;
import com.neu.edu.hms.entity.Room;

@Service
public class HotelStatusServiceImpl implements HotelStatusService{
	
	@Autowired
	private RoomDAO roomDAO; 
	
	@Autowired
	private GuestDAO guestDAO;
	
	/**
	 * @see RoomDAO
	 */
	@Override
	@Transactional
	public List<Room> getAllRooms() {
		return roomDAO.getAllRooms();
	}
	
	/**
	 * @see GuestDAO
	 */
	@Override
	@Transactional
	public List<Guest> getActualGuests() {
		return guestDAO.getActualGuests();
	}

}
