package com.smartcal.services;

import java.util.List;

import com.smartcal.models.Event;
import com.smartcal.models.User;

public interface UsersService {

	public User getUserById(int id);
	
	public void registerNewUser(User newUsr);
	
	public void deleteUser(User usr);
	
	public List<User> getFriendsFor(User usr);
	
	public List<User> getFriendsWithSimilarityAbove(User usr, double minSimilarity);
	
	public List<User> getAttendies(Event evt);
	
	public List<User> getFriendsAttending(User usr, Event evt);
}
