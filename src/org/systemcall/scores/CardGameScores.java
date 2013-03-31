package org.systemcall.scores;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

public class CardGameScores extends Activity {
	public final static String MAX_POINTS = "org.systemcall.myfirstapp.MAX_POINTS";
	public final static String USER_LIST = "org.systemcall.myfirstapp.USER_LIST";
	public final static String WIN_GAME = "org.systemcall.myfirstapp.WIN_GAME";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	public void newGame(View view) {
		Intent intent = new Intent(this, AddPlayersActivity.class);
		intent.putExtra(MAX_POINTS, 200);
		intent.putExtra(USER_LIST, "");
		intent.putExtra(WIN_GAME, false);
		startActivity(intent);
	}

	public void lastGame(View view) {
		Intent intent = new Intent(this, AddPlayersActivity.class);
		SharedPreferences sharedPref = getSharedPreferences(getString(R.string.app_pref_key), Context.MODE_PRIVATE);
		int max = sharedPref.getInt(getString(R.string.saved_max), 0);
		String users = sharedPref.getString(getString(R.string.saved_users), "");
		boolean win = sharedPref.getBoolean(getString(R.string.saved_win), false);

		intent.putExtra(MAX_POINTS, max);
		intent.putExtra(USER_LIST, users);
		intent.putExtra(WIN_GAME, win);
		startActivity(intent);
	}
}
