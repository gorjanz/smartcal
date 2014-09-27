package com.smartcal.models;

/**
 * 
 * @author Gorjan
 *	Model class for the relation attending(uid,eid)
 */
public class Attending {

	public static final String COLUMN_UID = "uid";
	public static final String COLUMN_EID = "eid";
	
	private User user;
	private Event event;

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
