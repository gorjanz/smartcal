package com.smartcal.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	
	public void setEventsRepository(EventsRepository eventsRepository) {
		this.eventsRepository = eventsRepository;
	}

	public void setUsersRepository(UsersRepository usersRepository) {
		this.usersRepository = usersRepository;
	}

	public void setFriendsRepository(FriendsRepository friendsRepository) {
		this.friendsRepository = friendsRepository;
	}

	@Override
	public List<User> getUsers() {
		return usersRepository.getUsers();
	}
	
	@Override
	public User getUserById(int id) {
		return usersRepository.getById(id);
	}

	@Override
	public void registerNewUser(User newUsr) {
		usersRepository.addUser(newUsr);
	}

	@Override
	public void deleteUser(int usr) {
		usersRepository.deleteUser(usr);
	}

	@Override
	public List<User> getAttendies(int evtId) {
		return usersRepository.getAttendies(evtId);
	}

	@Override
	public List<User> getFriendsFor(int usr) {
		return friendsRepository.getFriendsFor(usr);
	}

	@Override
	public List<User> getFriendsWithSimilarityAbove(int usrId,
			double minSimilarity) {
		return friendsRepository.getFriendsWithSimilarityAbove(usrId, minSimilarity);
	}

	@Override
	public List<User> getFriendsAttending(int usr, int evt) {
		return friendsRepository.getFriendsAttending(usr, evt);
	}
	
}
