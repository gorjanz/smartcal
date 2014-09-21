package com.smartcal.db;

import java.util.List;

import com.smartcal.models.Event;
import com.smartcal.models.User;

public interface EventsDAO {

	public void addEvent(Event evt);
	
	public void removeEvent(Event evt);
	
	public void updateEvent(int id, Event newEvt);
	
	public void attend(User usr, Event evt);
	
	public Event getById(int evtId);
	
	public List<User> getAttendies(Event evt);
	
}
