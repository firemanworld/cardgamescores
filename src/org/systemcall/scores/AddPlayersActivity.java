package org.systemcall.scores;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class AddPlayersActivity extends Activity {
	private String users = "";
	public final static String MAX_POINTS = "org.systemcall.myfirstapp.MAX_POINTS";
	public final static String USER_LIST = "org.systemcall.myfirstapp.USER_LIST";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_players);
	}

	public void startGame(View view) {
		if (users.isEmpty())
			return;
		Intent intent = new Intent(this, GameActivity.class);
		TextView textView = (TextView) findViewById(R.id.max_score);
		int max = Integer.valueOf(textView.getText().toString());
		intent.putExtra(USER_LIST, users);
		intent.putExtra(MAX_POINTS, max);
		startActivity(intent);
	}
	
	public void addUser(View view) {
		TextView textView = (TextView) findViewById(R.id.new_user_name);
		users += "," + textView.getText().toString();
		// Fill screen with users
		LinearLayout userList = (LinearLayout) findViewById(R.id.players_list);
		TextView user = new TextView(this);
		user.setTextSize(20);
		user.setText(textView.getText().toString());
		userList.addView(user);
		textView.setText("");
	}
}
