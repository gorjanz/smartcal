package com.smartcal.repositories;

import java.util.List;

import com.smartcal.models.Event;
import com.smartcal.models.User;

public interface FriendsRepository {

	public void createFriendship(User usr1, User usr2, double similarity);
	
	public List<User> getFriendsFor(User usr);
	
	public List<User> getFriendsWithSimilarityAbove(User usr, double minSimilarity);
	
	public List<User> getFriendsAttending(User usr, Event evt);
	
}
