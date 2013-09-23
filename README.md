Card Game Scores
================

How To Build
------------

Android App to keep card games scores.

* Fork & Check-out outside the workspace
* Import in Eclipse as "Android Project from Existing Code"
* Share the project (Team > Share Project)
* Run as "Android Application" and it'll build automatically

License
-------

This app is licensed under the GPLv3+ license, with images and media
cotent licensed under the CC BY-SA 3.0+.

Introduction
------------

This app's purpose is to keep scores for games where the each turn
could give you any (random) score, and not a fixed block. For that,
use KeepScore available in F-Droid, too. The original intent was to
help our family to keep Uno scores, which it does well.

Please note that it might have bugs, and I might not be able to fix
and/or add new features too often. Still, feel free to leave comments
and I'll do my best.

App Manual
----------

You have two options: "New Game" or "Last Game". On either of them,
you can add/delete people, change the maximum score and choose
between win or lose at that score.

For example, on Uno, we normally set the maximum score at 200 and set it
to lose, so that when someone crosses the 200 barrier, the game ends and
a "LOOSER" banner appear alongside the name who lost.

During the game, a number box will be available for you to input the
points accumulated, with ENTER changing down for the next, and an empty
box being the same as zero points. After updating all scores, click on
the update button.

The app will save the data (names, score, win/lose) of your last game,
so you can re-use it later via the "Last Game" button.

Know Issues
-----------

* When multi-tasking, Android saves the state of the current game, but
  if you have an app-killer, you might lose your current data. Though,
  the "Last Game" data wiull be saved permanently.
* The columns on the update screen are not aligned, and that's because
  my Android development skills are lacking. I broke it a few times already
  trying to fix that, but I need some time to look properly again.
* When the game ends, it'd be good to sort the players by their score
  depending on if it was set to lose or win, with the winner always on top.
* Art-work is seriously lacking, sorry.
