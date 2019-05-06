package com.neu.edu.hms.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.neu.edu.hms.entity.Guest;
import com.neu.edu.hms.entity.Room;
import com.neu.edu.hms.services.HotelStatusService;
/**
 * 
 * @author aasha
 *
 */

@Controller
@RequestMapping("/hotelStatus")
public class HotelStatusController {

	@Autowired
	private HotelStatusService hotelStatusService;

	@GetMapping("/")
	public String showStatusOfHotel(Model theModel) {

		List<Guest> guestList = hotelStatusService.getActualGuests();
		List<Room> roomList = hotelStatusService.getAllRooms();
		Integer noOfRooms = 0, noOfOccupiedRooms = 0, noOfVacantRooms = 0, noOfVacantStandardRooms = 0, noOfVacantBusinessRooms = 0, noOfVacantPremiumRooms = 0;

		for(Room theRoom : roomList) {
			noOfRooms ++;
			if(theRoom.getIsOccupied()) {
				noOfOccupiedRooms ++;	
			}else {
				noOfVacantRooms ++;
				if(theRoom.getStandard().equals("standard")) {
					noOfVacantStandardRooms ++;
				}else if(theRoom.getStandard().equals("business")) {
					noOfVacantBusinessRooms ++;
				}else {
					noOfVacantPremiumRooms ++;
				}
			}

		}

		Integer noOfGuests = 0, upComingCheckOuts = 0;

		for(Guest theGuest : guestList) {
			if(!theGuest.getIsCheckedout()) {
				noOfGuests++;
			}else {
				if(theGuest.getCheckoutDate().isEqual(LocalDate.now()) || theGuest.getCheckoutDate().isBefore(LocalDate.now())) {
					upComingCheckOuts++;
				}
			}
		}
		theModel.addAttribute("numberOfRooms", noOfRooms);
		theModel.addAttribute("numberOfOccupiedRooms", noOfOccupiedRooms);
		theModel.addAttribute("numberOfVacantRooms", noOfVacantRooms);
		theModel.addAttribute("numberOfVacantStandardRooms", noOfVacantStandardRooms);
		theModel.addAttribute("numberOfVacantBusinessRooms", noOfVacantBusinessRooms);
		theModel.addAttribute("numberOfVacantPremiumRooms", noOfVacantPremiumRooms);
		theModel.addAttribute("numberOfGuests", noOfGuests);
		theModel.addAttribute("upcommingCheckOuts", upComingCheckOuts);

		return "hotelStatus";
	}
}
