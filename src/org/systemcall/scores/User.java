package org.systemcall.scores;

import java.util.ArrayList;
import java.util.List;

public class User {
	private String name;
	private List<Integer> points;
	
	public User (String _name) {
		name = _name;
		points = new ArrayList<Integer>();
	}
	
	public void addPoint(Integer point) {
		points.add(point);
	}
	
	public String getName() {
		return name;
	}
	
	public Integer getTotal() {
		int i;
		Integer total = 0;
		for (i=0; i<points.size(); i++)
			total += points.get(i);
		return total;
	}
}
