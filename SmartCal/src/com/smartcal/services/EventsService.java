package com.smartcal.services;

import java.util.List;

import com.smartcal.models.Event;
import com.smartcal.models.User;

public interface EventsService {

	public Event getById(int eventId);
	
	public void attend(User u, Event evt);
	
	public void cancel(User u, Event evt);

	public List<Event> getPastEvents(User usr);
	
	public List<Event> getIncomingEvents(User usr);
	
}
