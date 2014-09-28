package com.smartcal.services;

import java.util.List;

import com.smartcal.models.User;

public interface UsersService {

	public User getUserById(int id);
	
	public void registerNewUser(User newUsr);
	
	public void deleteUser(int usr);
	
	public List<User> getUsers();
	
	public List<User> getFriendsFor(int usr);
	
	public List<User> getFriendsWithSimilarityAbove(int usr, double minSimilarity);
	
	public List<User> getAttendies(int evtId);
	
	public List<User> getFriendsAttending(int usrid, int evtid);
}
