package org.systemcall.scores;

import android.annotation.SuppressLint;
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

import java.util.Collections;

public class GameActivity extends Activity {
	private Game game;
	private int ID_OFFSET = 1024*1024;
	private int max;
	private int difference;
	private String users;
	private boolean win;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game);
		initGame();
	}

	private void initGame() {
        Intent intent = getIntent();
        max = intent.getIntExtra(AddPlayersActivity.MAX_POINTS, 0);
        difference = intent.getIntExtra(AddPlayersActivity.DIFFERENCE_POINTS, 0);
		users = intent.getStringExtra(AddPlayersActivity.USER_LIST);
		win = intent.getBooleanExtra(AddPlayersActivity.WIN_GAME, false);

		// Create game, setup users
		game = new Game(max, difference);
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

	@SuppressLint("InlinedApi")
	private void updateScreen() {
		LinearLayout userList = (LinearLayout) findViewById(R.id.game_user_list);
		userList.removeAllViews();

		// Fill screen with users
		int id;
        boolean finished = game.winner(game.getUsers().get(0));
		for (User user:game.getUsers()) {
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
			if (finished)
            {
                if (game.winner(user))
                {
                    // This is the end
                    TextView winText = new TextView(this);
                    if (win)
                        winText.setText("WINNER!");
                    else
                        winText.setText("LOSER!");
                    winText.setTextSize(20);
                    editOrWin.addView(winText);

                    // Remove update button
                    Button update = (Button) findViewById(R.id.game_update_scores);
                    LinearLayout layout = (LinearLayout) findViewById(R.id.update_scores_layout);
                    layout.removeView(update);
                }
			} else {
				// Add points entry
				EditText addPoints = new EditText(this);
				addPoints.setId(ID_OFFSET + user.hashCode());
				addPoints.setInputType(InputType.TYPE_NUMBER_FLAG_SIGNED);
				addPoints.setTextSize(20);
				addPoints.setWidth(200);
				editOrWin.addView(addPoints);
			}
			block.addView(editOrWin);

			userList.addView(block);
		}
	}

	public void updateScores(View view) {
		int id;
		for (User user: game.getUsers()) {
			EditText points = (EditText) findViewById(ID_OFFSET + user.hashCode());
			String pointText = points.getText().toString();
			if (!pointText.isEmpty()) {
                try {
                    int add = Integer.parseInt(pointText);
                    user.addPoint(add);
                } catch (NumberFormatException n) {
                    // Just don't add if it's not a number
                }
			}
		}
        game.sortUsers();
		updateScreen();
	}
}
