package com.smartcal.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smartcal.models.Event;
import com.smartcal.models.User;
import com.smartcal.repositories.EventsRepository;
import com.smartcal.repositories.FriendsRepository;
import com.smartcal.repositories.UsersRepository;

@Service
public class UsersServiceImpl implements UsersService {

	@Autowired
	private EventsRepository eventsRepository;
	
	@Autowired
	private UsersRepository usersRepository;
	
	@Autowired
	private FriendsRepository friendsRepository;
	
	@Override
	public User getUserById(int id) {
		return usersRepository.getById(id);
	}

	@Override
	public void registerNewUser(User newUsr) {
		usersRepository.addUser(newUsr);
	}

	@Override
	public void deleteUser(User usr) {
		usersRepository.deleteUser(usr);
	}

	@Override
	public List<User> getAttendies(Event evt) {
		return eventsRepository.getAttendies(evt);
	}

	@Override
	public List<User> getFriendsFor(User usr) {
		return friendsRepository.getFriendsFor(usr);
	}

	@Override
	public List<User> getFriendsWithSimilarityAbove(User usr,
			double minSimilarity) {
		return friendsRepository.getFriendsWithSimilarityAbove(usr, minSimilarity);
	}

	@Override
	public List<User> getFriendsAttending(User usr, Event evt) {
		return friendsRepository.getFriendsAttending(usr, evt);
	}
	
}
