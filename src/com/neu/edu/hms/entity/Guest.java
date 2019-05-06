package com.neu.edu.hms.entity;

import java.time.LocalDate;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * @author aasha
 */

@Entity
@Table(name="guest")
public class Guest {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "first_name")
	@NotNull
	@Pattern(regexp = "^[a-zA-Z]{1,100}", message = "Letters only!")
	private String firstName;
	
	
	@Column(name = "last_name")
	@NotNull
	@Pattern(regexp = "^[a-zA-Z]{1,100}", message = "Letters only!")
	private String lastName;

	@Column(name = "id_number")	
	@NotNull
	@Pattern(regexp = "^[a-zA-Z0-9]{1,30}", message = "Letters and numbers only!")
	private String idNumber;
	
	@Column(name = "phone_number")
	@NotNull
	@Pattern(regexp = "^[0-9]{1,16}", message = "Numbers only!")
	private String phoneNumber;
	
	
	@ManyToOne(	cascade = {
					CascadeType.DETACH,
					CascadeType.MERGE,
					CascadeType.PERSIST,
					CascadeType.REFRESH,
						})
	@JoinColumn(name = "room_id")
	private Room room;

	
	@NotNull
	@Column(name = "checkout_date")
	private LocalDate checkoutDate;
	
	@Column(name = "checkin_date")
	private LocalDate checkinDate;
	
	@Column(name = "is_checkedout")
	private boolean isCheckedout;
	
	
	@OneToOne(cascade = {})
	@JoinColumn(name = "last_checkedout_room_id")
	private Room lastCheckedoutRoom; 
	
	public Guest() {
		
	}

	
	public Guest(String firstName, String lastName, String idNumber, String phoneNumber,
			 Room room, LocalDate checkinDate, LocalDate checkoutDate, Room lastCheckedoutRoom) {
	
		this.firstName = firstName;
		this.lastName = lastName;
		this.idNumber = idNumber;
		this.phoneNumber = phoneNumber;
		this.room = room;
		this.checkinDate = checkinDate;
		this.checkoutDate = checkoutDate;
		this.lastCheckedoutRoom = lastCheckedoutRoom;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getIdNumber() {
		return idNumber;
	}

	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}



	public LocalDate getCheckoutDate() {
		return checkoutDate;
	}

	public void setCheckoutDate(LocalDate checkoutDate) {
		this.checkoutDate = checkoutDate;
	}
	
	
	public boolean getIsCheckedout() {
		return isCheckedout;
	}


	public void setCheckedout(boolean isCheckedout) {
		this.isCheckedout = isCheckedout;
	}


	public Room getLastCheckedoutRoom() {
		return lastCheckedoutRoom;
	}


	public void setLastCheckedoutRoom(Room lastCheckedoutRoom) {
		this.lastCheckedoutRoom = lastCheckedoutRoom;
	}

	public LocalDate getCheckinDate() {
		return checkinDate;
	}


	public void setCheckinDate(LocalDate checkinDate) {
		this.checkinDate = checkinDate;
	}


	@Override
	public boolean equals(Object o) {
	    if (this == o)
	        return true;
	    if (o == null)
	        return false;
	    if (getClass() != o.getClass())
	        return false;
	    Guest guest = (Guest) o;
	    return guest.getFirstName().equals(this.firstName) && 
	    		guest.getLastName().equals(this.lastName) && 
	    		guest.getId() == this.id && 
	    		guest.getIdNumber().equals(this.idNumber) && 
	    		guest.getPhoneNumber().equals(this.phoneNumber)
	    		;
	}	
	public String toString() {
		return "[Guest: id = " + id + ", firstName = " + firstName + ", lastName = " + lastName + ", idNumber = " + idNumber + ", isCheckedout = " + isCheckedout +"]";
	}
	
	  
}
