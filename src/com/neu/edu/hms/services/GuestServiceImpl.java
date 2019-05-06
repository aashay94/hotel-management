package com.neu.edu.hms.services;


import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

import javax.mail.MessagingException;
import javax.swing.JOptionPane;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itextpdf.text.DocumentException;
import com.neu.edu.hms.DAO.GuestDAO;
import com.neu.edu.hms.DAO.RoomDAO;
import com.neu.edu.hms.entity.Guest;
import com.neu.edu.hms.entity.Room;
import com.neu.edu.hms.services.helper.EmailMessage;
import com.neu.edu.hms.services.helper.PDFPrinter;

import java.time.temporal.ChronoUnit;

@Service
public class GuestServiceImpl implements GuestService{

	@Autowired
	private GuestDAO guestDAO;
	
	@Autowired
	private RoomDAO roomDAO;
	
	@Override
	@Transactional
	public List<Guest> getGuests() {
		return guestDAO.getActualGuests();
	}
	
	@Override
	@Transactional
	public List<Room> getOccupiedRooms() {
		return roomDAO.getOccupiedRooms();
	}
	
	@Override
	@Transactional
	public List<Room> getVacantRooms() {
		return roomDAO.getVacantRooms();
	}
	
	@Override
	@Transactional
	public Guest getGuestById(int id) {
		return guestDAO.getGuestById(id);
	}

	@Override
	@Transactional
	public void saveUpdateGuest(Guest theGuest) {
		guestDAO.saveUpdateGuest(theGuest);
		
	}

	@Override
	@Transactional
	public Room getRoomById(int id) {
		return roomDAO.getRoomById(id);
	}

	@Override
	@Transactional
	public void saveUpdateRoom(Room theRoom) {
		roomDAO.saveUpdateRoom(theRoom);
		
	}

	
	
	// Returns number of nights that guest stayed in the hotel
	@Override
	public int getNumberOfNights(Guest guest) {
		LocalDate checkIn = guest.getCheckinDate();
		LocalDate checkOut;
		
		if(guest.getIsCheckedout()) {
			checkOut = guest.getCheckoutDate();
		}else {
			checkOut = LocalDate.now();
		}
		
		int nightsNumber = (int) ChronoUnit.DAYS.between(checkIn, checkOut);
		return nightsNumber;
	}

	@Override
	public void saveBillCopyInPDF(Guest guest) {
		
		Room room;
		
		if(guest.getIsCheckedout()) {
		room = guest.getLastCheckedoutRoom();
		}else {
		room = guest.getRoom();
		}
		String location = "C:\\bills";
		File dir = new File(location);
		if(!dir.exists()) {
			dir.mkdirs();
		}
		String bill = generateBill(guest, room);
		try {
			PDFPrinter.createPDF(bill, location + "\\" + guest.getFirstName() + "_" + guest.getLastName() + "_" + guest.getId() + ".pdf");		
		} catch (DocumentException | IOException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void emailBill(Guest guest, String email) throws MessagingException {
		Room room;
		if(guest.getIsCheckedout()) {
		room = guest.getLastCheckedoutRoom();
		}else {
		room = guest.getRoom();
		}
		String generateBill = generateBill(guest, room);
		
		LinkedList <String> to = new LinkedList<String>();
		to.add(email);
		EmailMessage emailMsg = new EmailMessage.EmailBuilder("aashaytiwari1994@gmail.com", to)
			.addSubject("Ticket purchase confirmation " + guest.getFirstName() + " " + guest.getLastName())
			.addContent(generateBill)
			.build();
			emailMsg.send("ppgtiwari", "smtp.gmail.com", 465);
		    
	}

	private String generateBill(Guest guest, Room room) {
		int numberOfNights = getNumberOfNights(guest);
		String pdfData = "Your Bill is as follows -"
				+ "First Name: " + guest.getFirstName() +"\n"
				+ "Last Name: " + guest.getLastName() +"\n"
				+ "Checkin: " + guest.getCheckinDate() +"\n"
				+ "Checkout: " + guest.getCheckoutDate() +"\n"
				+ "Room Type: " + room.getStandard() +"\n"
				+ "Room Number: " + room.getNumber() +"\n"
				+ "Rate: " + room.getRate() + "$" +"\n"
				+ "No of nights: " + numberOfNights +"\n" 
				+ "Tax (VAT 24%): " + numberOfNights*room.getRate()*room.TAX + "$" +"\n"
				+ "Final Total: " + numberOfNights*room.getRate()*(1 + room.TAX) + "$" +"\n"
				+ "Thank you. Visit Again ! :)";
		return pdfData;
	}

}
