package org.systemcall.scores;

import java.util.ArrayList;
import java.util.List;

public class Game {
	private List<User> users;
	private int maxScore;
	public static int NO_WINNER = -1;
	
	public Game (int max) {
		users = new ArrayList<User>();
		maxScore = max;
	}
	
	public void addUser(String name) {
		users.add(new User(name));
	}
	
	public int winner() {
		int i;
		for (i=0; i<users.size(); i++)
			if (users.get(i).getTotal() > maxScore)
				return i;
		return NO_WINNER;
	}
	
	public int numUsers() {
		return users.size();
	}
	
	public User getUser(int id) {
		return users.get(id);
	}
}
