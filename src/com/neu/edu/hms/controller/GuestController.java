package com.neu.edu.hms.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.List;

import javax.mail.MessagingException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.neu.edu.hms.entity.Guest;
import com.neu.edu.hms.entity.Room;
import com.neu.edu.hms.services.GuestService;
import com.neu.edu.hms.services.RoomService;
/**
 * 
 * @author aasha
 *
 */

@Controller
@RequestMapping("/guest")
public class GuestController {

	@Autowired
	private GuestService guestService;

	@GetMapping("/list")
	public String guestsList(Model theModel) {
		//taking the guest list to get the no of guests
		List<Guest> guestList = guestService.getGuests();
		theModel.addAttribute("guestList", guestList);

		return "guestList";
	}

	@GetMapping("/showAddGuest")
	public String displayAddGuest(Model theModel) {
		//function for showing the added guest
		Guest guest = new Guest();
		List<Room> vacantRoom = guestService.getVacantRooms();
		Room firstRoom = vacantRoom.get(0);
		vacantRoom.remove(firstRoom);
		LinkedHashMap<String, Room> vacantRoomMap = populateRoomsMap(vacantRoom);

		theModel.addAttribute("guest", guest);
		theModel.addAttribute("roomsMap", vacantRoomMap);
		theModel.addAttribute("selectedRoom", firstRoom);

		return "addGuestForm";
	}

	@PostMapping("/saveGuest")
	public String saveTheGuest (@Valid @ModelAttribute("guest") Guest theGuest, BindingResult bindingResult, Model theModel) {
		//function to save the guest
		if(bindingResult.hasErrors()) {
			List<Room> vacantRoom = guestService.getVacantRooms();
			Room firstRoom = vacantRoom.get(0);
			LinkedHashMap<String, Room> vacantRoomMap = populateRoomsMap(vacantRoom);
			theModel.addAttribute("roomsMap", vacantRoomMap);
			theModel.addAttribute("selectedRoom", firstRoom);

			return "addGuestForm";

		}else {
			theGuest.getRoom().setOccupied(true);
			theGuest.setCheckinDate(LocalDate.now());
			guestService.saveUpdateRoom(theGuest.getRoom());
			guestService.saveUpdateGuest(theGuest);
			return "redirect:/guest/list";

		}
	}

	@GetMapping("/checkout")
	public String checkoutTheGuest(@RequestParam("guestId") int theId, Model theModel) {
		//function for getting the checkout guest
		Guest theGuest = guestService.getGuestById(theId);
		Room theRoom = guestService.getRoomById(theGuest.getRoom().getId());
		LocalDate localDate = LocalDate.now();
		localDate.format(DateTimeFormatter.ISO_LOCAL_DATE);

		theGuest.setCheckoutDate(localDate);
		theGuest.setCheckedout(true);
		theGuest.setRoom(null);
		theGuest.setLastCheckedoutRoom(theRoom);
		theRoom.getOccupants().remove(theGuest);
		if(theRoom.getOccupants().size() == 0) {
			theRoom.setOccupied(false);
		}

		guestService.saveUpdateGuest(theGuest);
		guestService.saveUpdateRoom(theRoom);

		return "redirect:/guest/list";
	}

	@GetMapping("/update")
	public String udpateTheGuest(@RequestParam("guestId") int theId, Model theModel) {
		//function for updating the guest
		Guest theGuest = guestService.getGuestById(theId);

		List<Room> vacantRooms = guestService.getVacantRooms();
		Room selectedRoom = theGuest.getRoom();
		vacantRooms.remove(selectedRoom);
		LinkedHashMap<String, Room> vacantRoomsMap = populateRoomsMap(vacantRooms);
		theModel.addAttribute("guest", theGuest);
		theModel.addAttribute("roomsMap", vacantRoomsMap);
		theModel.addAttribute("selectedRoom", selectedRoom);
		return "addGuestForm";
	}

	@GetMapping("/checkin")
	public String checkinGuestToParticularRoom(@RequestParam("roomId") int theRoomId, Model theModel) {
		Guest theGuest = new Guest();
		Room selectedRoom = guestService.getRoomById(theRoomId);
		theModel.addAttribute("guest", theGuest);
		theModel.addAttribute("selectedRoom", selectedRoom);
		return "addGuestForm";
	}

	@GetMapping("/checkInToOccupiedRoom")
	public String checkInToOccupiedRoom(Model theModel) {

		Room room = null;
		Guest guest = new Guest();
		LinkedHashMap<String, Room> occupiedRoomMap = null;
		List<Room> occupiedRoom = guestService.getOccupiedRooms();
		if(!occupiedRoom.isEmpty()) {
			room = occupiedRoom.get(0);
			occupiedRoomMap = populateRoomsMap(occupiedRoom);
		}

		theModel.addAttribute("selectedRoom", room);
		theModel.addAttribute("guest", guest);
		theModel.addAttribute("roomsMap", occupiedRoomMap);

		return "addGuestForm";
	}

	@GetMapping("/bill")
	public String displayBill(@RequestParam("guestId") int id, Model theModel) {
		//function for showing the generated bill
		Guest guest = guestService.getGuestById(id);
		Room room;
		int nightsNumber = guestService.getNumberOfNights(guest);

		if(guest.getIsCheckedout()) {
			room = guest.getLastCheckedoutRoom();
		}else {
			room = guest.getRoom();
		}

		double rate = room.getRate();
		theModel.addAttribute("guest", guest);
		theModel.addAttribute("room", room);
		theModel.addAttribute("nightsNumber", nightsNumber);
		theModel.addAttribute("rate",rate);
		theModel.addAttribute("tax", Room.TAX*rate*nightsNumber);
		theModel.addAttribute("total", nightsNumber*rate*(1+Room.TAX));
		return "guestBill";
	}

	@PostMapping("bill/save")
	public String saveBill(@RequestParam("guestId") int id, Model theModel) {
		//function for saving the bill
		Guest guest = guestService.getGuestById(id);
		theModel.addAttribute("guestId", id);
		guestService.saveBillCopyInPDF(guest);
		return "redirect:/guest/list";
	}

	@PostMapping("/bill/mail")
	public String mailBill(@RequestParam("guestId") int id, @RequestParam("email") String email, Model theModel) {
		//function for sending the bill by mail
		Guest guest = guestService.getGuestById(id);
		try {
			guestService.emailBill(guest, email);
		} catch(javax.mail.internet.AddressException | javax.mail.SendFailedException e){
			theModel.addAttribute("error", "Please enter the correct email address!");
			e.printStackTrace();
		} catch (MessagingException e) {
			theModel.addAttribute("error", "There is some problem with the email address");
			e.printStackTrace();
		}
		List<Guest> guestList = guestService.getGuests();
		theModel.addAttribute("guestList", guestList);				

		return "guestList";
	}

	private LinkedHashMap<String, Room> populateRoomsMap(List<Room> rooms){

		if(rooms == null || rooms.size() == 0) {
			rooms.add(new Room(0, "No vacant rooms", true, null));
		}
		LinkedHashMap<String, Room> roomMap = new LinkedHashMap<String, Room>();
		for(Room room : rooms) {
			roomMap.put(Integer.toString(room.getId()), room);
		}
		return roomMap;
	}

	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
		dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
	}
}
