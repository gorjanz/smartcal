package com.smartcal.repositories;

import java.sql.Date;
import java.util.List;

import com.smartcal.models.Event;

public interface EventsRepository {

	public void addEvent(Event evt);
	
	public void removeEvent(Event evt);
	
	public void updateEvent(int id, Event newEvt);
	
	public void attend(int usr, int evt);
	
	public void cancel(int usr, int evt);
	
	public Event getById(int evtId);
	
	public List<Event> getEvents();
	
	public List<Event> getPastEvents(int usrId);
	
	public List<Event> getIncomingEvents(int usrId);
	
	public List<Event> getByDate(Date date);
	
}
