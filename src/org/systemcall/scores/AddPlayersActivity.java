package org.systemcall.scores;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;

public class AddPlayersActivity extends Activity {
	private String users = "";
	public final static String MAX_POINTS = "org.systemcall.myfirstapp.MAX_POINTS";
	public final static String USER_LIST = "org.systemcall.myfirstapp.USER_LIST";
	public final static String WIN_GAME = "org.systemcall.myfirstapp.WIN_GAME";
	private int max;
	private boolean win;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_players);
		initScreen();
	}

	private void initScreen() {
        Intent intent = getIntent();
        max = intent.getIntExtra(AddPlayersActivity.MAX_POINTS, 0);
		String u = intent.getStringExtra(AddPlayersActivity.USER_LIST);
		win = intent.getBooleanExtra(AddPlayersActivity.WIN_GAME, false);
		if (max > 0) {
			TextView textView = (TextView) findViewById(R.id.max_score);
			textView.setText(String.valueOf(max));
		}
		if (u.length() > 0) {
			String[] us = u.split(",");
            for (String i : us)
				_addUser(i);
		}
		RadioGroup winGame = (RadioGroup) findViewById(R.id.win_game);
		if (win)
			winGame.check(R.id.win);
		else
			winGame.check(R.id.lose);
	}

	public void startGame(View view) {
		if (users.isEmpty())
			return;
		Intent intent = new Intent(this, GameActivity.class);
        CheckBox playForeverBox = (CheckBox) findViewById(R.id.play_forever);
        if (playForeverBox.isChecked()) {
            max = 0;
            win = false;
        } else {
            TextView textView = (TextView) findViewById(R.id.max_score);
            max = Integer.valueOf(textView.getText().toString());
            RadioGroup winGame = (RadioGroup) findViewById(R.id.win_game);
            win = (winGame.getCheckedRadioButtonId() == R.id.win);
        }
		updatePreferences();

		intent.putExtra(USER_LIST, users);
		intent.putExtra(MAX_POINTS, max);
		intent.putExtra(WIN_GAME, win);
		startActivity(intent);
	}
	
	public void addUser(View view) {
		TextView textView = (TextView) findViewById(R.id.new_user_name);
		String userName = textView.getText().toString();
		userName = userName.trim();
		if (userName.length() == 0)
			return;
		if (users.length() > 0)
			users += ",";
		users += userName;
		_addUser(userName);
		textView.setText("");
	}
	
	public void _addUser(String userName) {
		// Fill screen with users
		LinearLayout userList = (LinearLayout) findViewById(R.id.players_list);
		TextView user = new TextView(this);
		user.setTextSize(20);
		user.setText(userName);
		userList.addView(user);
	}

    public void setPlayForever(View view) {
        CheckBox playForeverBox = (CheckBox) findViewById(R.id.play_forever);
        TextView textView = (TextView) findViewById(R.id.max_score);

        if (playForeverBox.isChecked()) {
            textView.setFocusable(false);
        } else {
            textView.setFocusableInTouchMode(true);
        }
    }

	private void updatePreferences() {
		SharedPreferences sharedPref = getSharedPreferences(getString(R.string.app_pref_key), Context.MODE_PRIVATE);
		SharedPreferences.Editor editor = sharedPref.edit();
		editor.putInt(getString(R.string.saved_max), max);
		editor.putString(getString(R.string.saved_users), users);
		editor.putBoolean(getString(R.string.saved_win), win);
		editor.commit();
	}
}
