package com.smartcal.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smartcal.models.Event;
import com.smartcal.models.User;
import com.smartcal.repositories.EventsRepository;
import com.smartcal.repositories.UsersRepository;

@Service
public class EventsServiceImpl implements EventsService {

	@Autowired
	public EventsRepository eventsRepository;
	
	@Autowired
	public UsersRepository usersRepository;
		
	public void setEventsRepository(EventsRepository eventsRepository) {
		this.eventsRepository = eventsRepository;
	}

	public void setUsersRepository(UsersRepository usersRepository) {
		this.usersRepository = usersRepository;
	}

	@Override
	public Event getById(int eventId) {
		return eventsRepository.getById(eventId);
	}
	
	@Override
	public List<Event> getEvents() {
		return eventsRepository.getEvents();
	}

	@Override
	public void attend(int usr, int evt) {
		eventsRepository.attend(usr, evt);
	}

	@Override
	public void cancel(int usr, int evt) {
		eventsRepository.cancel(usr, evt);
	}

	@Override
	public List<Event> getPastEvents(int usr) {
		return eventsRepository.getPastEvents(usr);
	}

	@Override
	public List<Event> getIncomingEvents(int usr) {
		return eventsRepository.getIncomingEvents(usr);
	}
	
}
