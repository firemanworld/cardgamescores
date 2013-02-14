package org.systemcall.scores;

import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class GameActivity extends Activity {
	private Game game;
	private int ID_OFFSET = 1024*1024;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game);
		initGame();
	}

	private void initGame() {
        Intent intent = getIntent();
        int max = intent.getIntExtra(AddPlayersActivity.MAX_POINTS, 100);
		String users = intent.getStringExtra(AddPlayersActivity.USER_LIST);

		// Create game, setup users
		game = new Game(max);
		int cur = 0;
		while (cur < users.length()) {
			int pos = users.indexOf(',', cur);
			if (pos == -1)
				pos = users.length();
			String user = users.substring(cur,  pos);
			if (!user.isEmpty())
				game.addUser(user);
			cur = pos+1;
		}
		updateScreen();
	}

	private void updateScreen() {
		LinearLayout userList = (LinearLayout) findViewById(R.id.game_user_list);
		userList.removeAllViews();

		int winner = game.winner();
		boolean finished = (winner != Game.NO_WINNER);
		
		// Fill screen with users
		int id;
		for (id=0; id<game.numUsers(); id++) {
			User user = game.getUser(id);
			LinearLayout block = new LinearLayout(this); // default horizontal
			
			// User name
			TextView txt = new TextView(this);
			txt.setText(user.getName());
			txt.setTextSize(20);
			txt.setLayoutParams(new TableRow.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 2));
			block.addView(txt);

			// Total text
			TextView points = new TextView(this);
			points.setText(user.getTotal().toString());
			points.setTextSize(20);
			points.setLayoutParams(new TableRow.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 1));
			block.addView(points);

			LinearLayout editOrWin = new LinearLayout(this); // default horizontal
			editOrWin.setLayoutParams(new TableRow.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 2));
			if (finished) {
				if (id == winner) {
					// This is the end
					TextView win = new TextView(this);
					win.setText("WINNER!");
					win.setTextSize(20);
					editOrWin.addView(win);
					
					// Remove update button
					Button update = (Button) findViewById(R.id.game_update_scores);
					LinearLayout layout = (LinearLayout) findViewById(R.id.update_scores_layout);
					layout.removeView(update);
				}
			} else {
				// Add points entry
				EditText addPoints = new EditText(this);
				addPoints.setId(ID_OFFSET + id);
				addPoints.setInputType(InputType.TYPE_CLASS_NUMBER);
				addPoints.setTextSize(20);
				addPoints.setWidth(100);
				editOrWin.addView(addPoints);
			}
			block.addView(editOrWin);

			userList.addView(block);
		}
	}

	public void updateScores(View view) {
		int id;
		for (id=0; id<game.numUsers(); id++) {
			EditText points = (EditText) findViewById(ID_OFFSET + id);
			String pointText = points.getText().toString();
			if (!pointText.isEmpty()) {
				int add = Integer.valueOf(pointText);
				game.getUser(id).addPoint(add);
			}
		}
		updateScreen();
	}
}
