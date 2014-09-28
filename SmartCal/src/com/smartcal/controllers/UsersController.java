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

import com.smartcal.models.User;
import com.smartcal.services.UsersService;

@Controller
public class UsersController {

	@Autowired
	private UsersService usersService;

	public UsersController(UsersService usersService) {
		super();
		this.usersService = usersService;
	}

	@RequestMapping(value = "/users", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody List<User> getUsers() {
		return usersService.getUsers();
	}

	@RequestMapping(value = "/users/{userid}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody User getById(@PathVariable int userid) {
		return usersService.getUserById(userid);
	}

	@RequestMapping(value = "/users/add", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.OK)
	public void addUser(User usr) {
		usersService.registerNewUser(usr);
	}

	@RequestMapping(value = "/events/{eventid}/users")
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody List<User> getAttendingForEvent(
			@PathVariable int eventid) {
		return usersService.getAttendies(eventid);
	}

	@RequestMapping(value = "/{userid}/{eventid}/friends")
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody List<User> getFriendsAttending(
			@PathVariable int userid, @PathVariable int eventid) {
		return usersService.getFriendsAttending(userid, eventid);
	}
	
	@RequestMapping(value = "/users/{userid}/friends", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody List<User> getFriends(@PathVariable int userid) {
		return usersService.getFriendsFor(userid);
	}

}
