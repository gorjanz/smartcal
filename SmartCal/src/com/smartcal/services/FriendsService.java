package com.smartcal.services;

import java.util.List;

import com.smartcal.models.User;

public interface FriendsService {

	public List<User> getFriendsFor(User usr);
	
	public List<User> getFriendsWithSimilarityAbove(User usr, double minSimilarity);
	
}
