package com.smartcal.services;

import java.util.List;

import com.smartcal.models.Event;
import com.smartcal.models.User;

public interface UsersService {
	
	public void addUser(User usr);
	
	public void deleteUser(User usr);
	
	public List<User> getFriends(User usr);
	
	public List<Event> getPastEvents(User usr);
	
	public List<Event> getIncomingEvents(User usr);
	
}
