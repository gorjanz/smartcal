package com.smartcal.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.smartcal.models.Event;
import com.smartcal.services.EventsService;

@Controller
public class EventsController {

	@Autowired
	private EventsService eventsService;

	public EventsController(EventsService eventsService) {
		super();
		this.eventsService = eventsService;
	}

	@RequestMapping(value = "/events", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody List<Event> getEvents() {
		return eventsService.getEvents();
	}

	@RequestMapping(value = "/events/{eventid}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody Event getById(@PathVariable int eventid) {
		return eventsService.getById(eventid);
	}

	@RequestMapping(value = "/users/{userid}/past", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody List<Event> getPastEventsForUser(
			@PathVariable int userid) {
		return eventsService.getPastEvents(userid);
	}

	@RequestMapping(value = "/users/{userid}/future", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody List<Event> getFutureEventsForUser(
			@PathVariable int userid) {
		return eventsService.getIncomingEvents(userid);
	}

	@RequestMapping(value = "/events/{eventid}/attend", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.OK)
	public void attend(int userid, int eventid) {
		eventsService.attend(userid, eventid);
	}

	@RequestMapping(value = "/events/{eventid}/cancel", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.OK)
	public void cancel(int userid, int eventid) {
		eventsService.cancel(userid, eventid);
	}

}
