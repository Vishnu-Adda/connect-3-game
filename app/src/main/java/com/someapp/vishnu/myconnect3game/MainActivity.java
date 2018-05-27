package com.someapp.vishnu.myconnect3game;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2}; // 2 is empty
    int[][] winningPositions = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8},
                                {0, 4, 8}, {2, 4, 6}};
    int player = 0; // 0 is yellow, and 1 is red
    boolean finishedGame = false;

    public void dropped(View view) {

        if(finishedGame) { return; }

        ImageView counter = (ImageView) view;

        Log.i("Tag", counter.getTag().toString() + " tapped");

        int tappedCounter = Integer.parseInt(counter.getTag().toString());

        if(gameState[tappedCounter] == 0 || gameState[tappedCounter] == 1) {
            Toast.makeText(this, "No cheating!", Toast.LENGTH_SHORT).show();
            return;
        }

        gameState[tappedCounter] = player;

        counter.setTranslationY(-1500);

        if(player  == 0) {

            counter.setImageResource(R.drawable.yellow);
            player++;

        } else {
            counter.setImageResource(R.drawable.red);
            player--;
        }

        counter.animate().alpha(1).translationYBy(1500).setDuration(500);

        for (int[] winningPosition : winningPositions) {

            if(gameState[winningPosition[0]] == gameState[winningPosition[1]] &&
                    gameState[winningPosition[1]] == gameState[winningPosition[2]] &&
                    gameState[winningPosition[0]] != 2) {
                if(gameState[winningPosition[0]] == 1) {
                    Toast.makeText(this, "Red has won!", Toast.LENGTH_SHORT ).show();
                    finishedGame = true;
                } else{
                    Toast.makeText(this,
                            "Yellow has won!", Toast.LENGTH_SHORT ).show();
                    finishedGame = true;
                }

            }

        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //test
    }
}
