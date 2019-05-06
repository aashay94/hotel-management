package com.neu.edu.hms.DAO;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.neu.edu.hms.entity.Guest;
import com.neu.edu.hms.entity.Room;

@Repository
public class RoomDAOImpl implements RoomDAO {

	@Autowired
	private SessionFactory sessionFactory;
	@Override
	public List<Room> getAllRooms() {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Room order by number", Room.class);
		List<Room> rooms = query.getResultList();
		return rooms;
	}
	@Override
	public List<Room> getVacantRooms() {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("select r from Room r where r.isOccupied = 0 order by r.number", Room.class);
		List<Room> rooms = query.getResultList();
		return rooms;
	}

	@Override
	public Room getRoomById(int id) {
		Session session = sessionFactory.getCurrentSession();
		Room room = session.get(Room.class, id);
		Hibernate.initialize(room.getOccupants());
		return room;
	}

	@Override
	public void saveUpdateRoom(Room room) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(room);
		
	}
	
	@Override
	public List<Room> getOccupiedRooms() {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("select r from Room r where r.isOccupied = 1 order by r.number", Room.class);
		List<Room> rooms = query.getResultList();
		rooms.forEach(r -> Hibernate.initialize(r.getOccupants())); //otherwise lazy fetch excpetion - this object would not be available outside of transaction)
		return rooms;
	}


}
