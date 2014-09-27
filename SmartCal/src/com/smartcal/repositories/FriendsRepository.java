package com.smartcal.repositories;

import java.util.List;

import com.smartcal.models.User;

public interface FriendsRepository {

	public List<User> getFriendsFor(User usr);
	
	public List<User> getFriendsWithSimilarityAbove(User usr, double minSimilarity);
	
}
