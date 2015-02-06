package org.systemcall.scores;

import java.util.ArrayList;
import java.util.List;

public class User implements Comparable<User> {
	private String name;
	private List<Integer> points;
    private Integer totalPoints;
	
	public User (String _name) {
		name = _name;
		points = new ArrayList<Integer>();
        totalPoints=0;
	}
	
	public void addPoint(Integer point) {
        points.add(point);
        totalPoints+=point;
	}
	
	public String getName() {
		return name;
	}
	
	public Integer getTotal() {
		return totalPoints;
	}

    @Override
    public int compareTo(User user) {
        return totalPoints.compareTo(user.getTotal());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (!name.equals(user.name)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
