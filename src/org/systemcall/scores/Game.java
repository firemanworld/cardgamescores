package org.systemcall.scores;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Game {
	private List<User> users;
	private int maxScore;
    private int differenceScore;
	
	public Game (int max, int difference) {
		users = new ArrayList<User>();
		maxScore = max;
        differenceScore = difference;
	}
	
	public void addUser(String name) {
		users.add(new User(name));
	}
	
	public boolean winner(User user) {
        if ( user.getTotal()>=maxScore )
        {
            sortUsers();
            int index = users.indexOf(user);

            if (differenceScore > 0)
            {
                if (index==0)
                {
                    if (users.size()>1)
                    {
                        return user.getTotal() - users.get(index + 1).getTotal() >= differenceScore;
                    }
                    return true;
                }
            }
            else
            {
                if ( index != 0 )
                {
                    return user.getTotal() == users.get(0).getTotal();
                }
                return true;
            }
        }
        return false;
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
