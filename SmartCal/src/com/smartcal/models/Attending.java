package com.smartcal.models;

public class Attending {

	private User user;
	private Event event;

	public Attending(User usr, Event evt) {
		user = usr;
		event = evt;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (obj.getClass() != this.getClass())
			return false;
		Attending att = (Attending) obj;
		return ((user.equals(att.getUser())) && event.equals(att.getEvent()));
	}

	@Override
	public String toString() {
		return String.format("%s attending %s at %s", user.getName(),
				event.getTitle(), event.getStartTime().toString());
	}

}
