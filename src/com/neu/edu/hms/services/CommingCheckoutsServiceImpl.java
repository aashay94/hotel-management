package com.neu.edu.hms.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neu.edu.hms.DAO.GuestDAO;
import com.neu.edu.hms.entity.Guest;


@Service
public class CommingCheckoutsServiceImpl implements CommingCheckoutsService {
	
	@Autowired
	private GuestDAO guestDAO;
	
	@Override
	@Transactional
	public List<Guest> getCheckouts() {
		return guestDAO.getCommingCheckouts();
	}

}
