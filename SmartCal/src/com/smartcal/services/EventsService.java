package com.smartcal.services;

import java.util.List;

import com.smartcal.models.Event;

public interface EventsService {

	public Event getById(int eventId);
	
	public void attend(int userId, int evtId);
	
	public void cancel(int userId, int evtId);

	public List<Event> getPastEvents(int usrId);
	
	public List<Event> getIncomingEvents(int usrId);
	
	public List<Event> getEvents();
	
}
