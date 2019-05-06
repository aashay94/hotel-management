package com.neu.edu.hms.DAO;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.neu.edu.hms.entity.Guest;

@Repository
public class GuestDAOImpl implements GuestDAO {

	@Autowired
	private SessionFactory sessionFactory;
	@Override
	public List<Guest> getActualGuests() {
		Session session = sessionFactory.getCurrentSession();
		Query q = session.createQuery("select g from Guest g where g.isCheckedout=0 order by lastName", Guest.class); 
		List<Guest> guests = q.getResultList();	
		return guests;
	}

	@Override
	public List<Guest> getCheckedoutGuests() {
	
		Session session = sessionFactory.getCurrentSession();
		Query q = session.createQuery("select g from Guest g where g.isCheckedout=1 order by lastName", Guest.class); 
		List<Guest> guests = q.getResultList();
		
		return guests;
	}
	@Override
	public void saveUpdateGuest(Guest theGuest) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(theGuest);
		
	}
	@Override
	public Guest getGuestById(int id) {
		Session session = sessionFactory.getCurrentSession();
		Guest theGuest = session.get(Guest.class, id);
		return theGuest;
	}

	@Override
	public List<Guest> getCommingCheckouts() {	
		LocalDate tmw = LocalDate.now();
		tmw.plusDays(2);
		Session session = sessionFactory.getCurrentSession();
		Query q = session.createQuery("select g from Guest g where g.checkoutDate <= :tomorrow and g.isCheckedout = 1 order by checkoutDate", Guest.class); 
		q.setParameter("tomorrow", tmw);
		List<Guest> guests = q.getResultList();
		return guests;
	}

}
