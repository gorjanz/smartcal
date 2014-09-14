package com.smartcal.models;

public class Friendship {

	private User user1;
	private User user2;
	private double similarity;

	public Friendship(User usr1, User usr2) {
		user1 = usr1;
		user2 = usr2;

	}

	public double calculateSimilarity() {
		// TO-DO calculate similarity based on collaborative filtering rules
		return 0.00;
	}

	public User getUser1() {
		return user1;
	}

	public void setUser1(User user1) {
		this.user1 = user1;
	}

	public User getUser2() {
		return user2;
	}

	public void setUser2(User user2) {
		this.user2 = user2;
	}

	public double getSimilarity() {
		return similarity;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (obj.getClass() != this.getClass())
			return false;
		Friendship friendship = (Friendship) obj;
		return (this.user1.equals(friendship.getUser1()) && 
				this.user2.equals(friendship.getUser2()))
				|| (this.user1.equals(friendship.getUser2()) &&
					this.user2.equals(friendship.getUser1()));
	}

	@Override
	public String toString() {
		return String.format("%s - %s : %.2f", user1.getName(),
				user2.getName(), similarity);
	}

}
