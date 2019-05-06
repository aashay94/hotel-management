package com.neu.edu.hms.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.neu.edu.hms.entity.Guest;
import com.neu.edu.hms.entity.Room;
import com.neu.edu.hms.services.RoomService;
/**
 * 
 * @author aasha
 *
 */
@Controller
@RequestMapping("/room")
public class RoomController {

	@Autowired
	private RoomService roomService;
	
	@GetMapping("/list")
	public String listOfRooms(Model theModel) {
		
		List <Room> vacantRoom = roomService.getVacantRooms();
		theModel.addAttribute("roomList", vacantRoom);
		return "roomList";
		
	}
	
	@GetMapping("/occupiedRoomlist")
	public String listOfOccupiedRooms(Model theModel) {
		
		List <Room> occupiedRoom = roomService.getOccupiedRooms();
		theModel.addAttribute("roomList", occupiedRoom);
		return "occupiedRoomList";
		
	}
	
	@GetMapping("/checkout")
	public String checkoutRoom (@RequestParam("roomId") Integer theRoomId) {
		Room theRoom = roomService.getRoomById(theRoomId);
		List<Guest> occupantList = theRoom.getOccupants();
		LocalDate localDate = LocalDate.now();
		localDate.format(DateTimeFormatter.ISO_LOCAL_DATE);
		
		for(Guest guest : occupantList) {
			System.out.println(guest.getFirstName());
			guest.setCheckedout(true);
			guest.setCheckoutDate(localDate);
			theRoom.setOccupants(null);
			theRoom.setOccupied(false);

		}
		roomService.saveUpdateRoom(theRoom);
		return "redirect:/room/occupiedRoomlist"; //redirecting to occupied room list
	}
	
}
