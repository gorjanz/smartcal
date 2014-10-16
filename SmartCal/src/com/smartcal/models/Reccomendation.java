package com.smartcal.models;

public class Reccomendation {

	public static final String COLUMN_UID = "userid";
	public static final String COLUMN_EID = "eventid";
	public static final String COLUMN_ACCEPTED = "accepted";

	private int userid;
	private int eventid;
	private String accepted;
	
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public int getEventid() {
		return eventid;
	}
	public void setEventid(int eventid) {
		this.eventid = eventid;
	}
	public String getAccepted() {
		return accepted;
	}
	public void setAccepted(String accepted) {
		this.accepted = accepted;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (obj.getClass() != this.getClass())
			return false;
		Reccomendation otherRec = (Reccomendation) obj;
		return ((Integer.compare(userid, otherRec.userid) == 0)
				&& (Integer.compare(eventid, otherRec.eventid) == 0));
	}

	@Override
	public String toString() {
		return String.format("The reccomendation for user %d to attend event %s was %s", userid,
				eventid, (accepted.equals("yes") ? "accepted" : "not accepted"));
	}
	
}
