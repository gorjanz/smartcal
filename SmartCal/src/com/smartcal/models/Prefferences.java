package com.smartcal.models;

public class Prefferences {

	public static final String COLUMN_USERID = "userid";
	public static final String COLUMN_SPORTS = "sports";
	public static final String COLUMN_NIGHTLIFE = "nightlife";
	public static final String COLUMN_SCIENCE = "science";

	private int userId;
	private int sports;
	private int nightLife;
	private int science;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getSports() {
		return sports;
	}

	public void setSports(int sports) {
		this.sports = sports;
	}

	public int getNightLife() {
		return nightLife;
	}

	public void setNightLife(int nightLife) {
		this.nightLife = nightLife;
	}

	public int getScience() {
		return science;
	}

	public void setScience(int science) {
		this.science = science;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (obj.getClass() != this.getClass())
			return false;
		Prefferences otherPrefs = (Prefferences) obj;
		return ((Integer.compare(sports, otherPrefs.sports) == 0)
				&& (Integer.compare(nightLife, otherPrefs.nightLife) == 0)
				&& (Integer.compare(science, otherPrefs.science) == 0));
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("User ID: " + this.userId + "\n");
		sb.append("Sports: " + this.sports + "\n");
		sb.append("NightLife: " + this.nightLife + "\n");
		sb.append("Science: " + this.science + "\n");
		return sb.toString();
	}

}
