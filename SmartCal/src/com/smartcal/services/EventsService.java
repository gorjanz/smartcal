package com.smartcal.services;

import java.util.List;

import com.smartcal.models.Event;
import com.smartcal.models.User;

public interface EventsService {

	public void addEvent(Event evt);
	
	public void removeEvent(Event evt);
	
	public void attend(User usr, Event evt);
	
	public List<User> getAttendies(Event evt);
	
}
