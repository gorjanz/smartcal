package com.smartcal.db;

import java.util.List;

import com.smartcal.models.User;

public interface FriendsDAO {

	public List<User> getFriendsFor(User usr);
	
	public List<User> getFriendsWithSimilarityAbove(User usr, double minSimilarity);
	
}
