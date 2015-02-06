package org.systemcall.scores;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Game {
	private List<User> users;
	private int maxScore;
	
	public Game (int max) {
		users = new ArrayList<User>();
		maxScore = max;
	}
	
	public void addUser(String name) {
		users.add(new User(name));
	}
	
	public boolean winner(User user) {
        return user.getTotal()>=maxScore;
	}

    public void sortUsers()
    {
        Collections.sort(users, Collections.reverseOrder());
    }

	public int numUsers() {
		return users.size();
	}
    public List<User> getUsers() { return users; };
}
