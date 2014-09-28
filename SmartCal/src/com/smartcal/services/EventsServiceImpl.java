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
	public void attend(User usr, Event evt) {
		eventsRepository.attend(usr, evt);
	}

	@Override
	public void cancel(User usr, Event evt) {
		eventsRepository.cancel(usr, evt);
	}

	@Override
	public List<Event> getPastEvents(User usr) {
		return eventsRepository.getPastEvents(usr);
	}

	@Override
	public List<Event> getIncomingEvents(User usr) {
		return eventsRepository.getIncomingEvents(usr);
	}
	
}
