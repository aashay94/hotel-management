package com.neu.edu.hms.services;

import java.util.List;

import javax.mail.MessagingException;

import com.neu.edu.hms.entity.Guest;
import com.neu.edu.hms.entity.Room;


public interface GuestService {
	//getting actual guests
	public List<Guest> getGuests();
	//getting the list of occupied rooms
	public List<Room> getOccupiedRooms();
	//getting the list of vacant rooms
	public List<Room> getVacantRooms();
	//saving and updating the room
	public void saveUpdateRoom(Room theRoom);
	//saving and updating the guest
	public void saveUpdateGuest(Guest theGuest);
	//getting room by id
	public Room getRoomById(int id);
	//getting guests by id
	public Guest getGuestById(int id);
	// getting the no of guests
	public int getNumberOfNights(Guest guest);
	// saving the bill in pdf format
	public void saveBillCopyInPDF(Guest guest);
	//sending the bill mail
	public void emailBill(Guest guest, String email) throws MessagingException;
}
