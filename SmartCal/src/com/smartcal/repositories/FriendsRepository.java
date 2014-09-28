package com.smartcal.repositories;

import java.util.List;

import com.smartcal.models.User;

public interface FriendsRepository {

	public void createFriendship(int usr1Id, int usr2Id, double similarity);
	
	public List<User> getFriendsFor(int usrId);
	
	public List<User> getFriendsWithSimilarityAbove(int usr, double minSimilarity);
	
	public List<User> getFriendsAttending(int usr, int evt);
	
}
