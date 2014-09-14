package com.smartcal.models;

import java.sql.Date;

public class Event {

	private int eventId;
	private String title;
	private String description;
	private String url;
	private Date startTime;
	private Date endTime;

	public Event(String description, String title, String url, Date startTime,
			Date endTime) {
		this.eventId = User.NOT_PERSISTED;
		this.title = title;
		this.description = description;
		this.url = url;
		this.startTime = startTime;
		this.endTime = endTime;
	}

	public Event(int id, String title, String description, String url,
			Date startTime, Date endTime) {
		this.eventId = id;
		this.title = title;
		this.description = description;
		this.url = url;
		this.startTime = startTime;
		this.endTime = endTime;
	}

	public int getEventId() {
		return eventId;
	}

	public void setEventId(int eventId) {
		this.eventId = eventId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (obj.getClass() != this.getClass())
			return false;
		Event evt = (Event) obj;
		return this.eventId == evt.getEventId();
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Event ID: " + this.eventId + "\n");
		sb.append("Title: " + this.title + "\n");
		sb.append("Description: " + this.description + "\n");
		sb.append("Starting at: " + this.startTime.toString() + "\n");
		sb.append("Ending at: " + this.endTime.toString() + "\n");
		return sb.toString();
	}

}
