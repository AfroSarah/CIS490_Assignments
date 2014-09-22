package com.example.sarah.hw2_rockpaperscissors;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;


public class Rock_Paper_Scissors extends Activity implements OnClickListener {

    //Variable 'option' set to rock, paper, or scissors
    public enum Option {
        ROCK, PAPER, SCISSORS
    }
    //Variable 'result' set to win, loss, or draw
    public enum Result {
        WIN, LOSS, DRAW
    }

    private Option userSelection;
    private Result gameResult;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rock__paper__scissors);

        Button buttonRock = (Button) findViewById(R.id.buttonRock);
        Button buttonPaper = (Button) findViewById(R.id.buttonPaper);
        Button buttonScissors = (Button) findViewById(R.id.buttonScissors);

        // On click listening for buttons
        buttonRock.setOnClickListener(this);
        buttonPaper.setOnClickListener(this);
        buttonScissors.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        ImageView imageView = (ImageView) findViewById(R.id.imageViewAnswerUser);
        boolean play = true;

        switch (v.getId()) {
            case R.id.buttonRock:
                userSelection = Option.ROCK;
                imageView.setImageResource(R.drawable.rock);
                break;
            case R.id.buttonPaper:
                userSelection = Option.PAPER;
                imageView.setImageResource(R.drawable.paper);
                break;
            case R.id.buttonScissors:
                userSelection = Option.SCISSORS;
                imageView.setImageResource(R.drawable.scissors);
                break;
        }
        //Play is always true
        if(play) {
            play();
            showResults();
        }
    }
    //Alert displays results
    private void showResults() {
        AlertDialog.Builder builder = new AlertDialog.Builder(Rock_Paper_Scissors.this);
        builder.setCancelable(false);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Do nothing
            }
        });

        // Sets the right message according to result.
        if(gameResult == Result.LOSS) {
            builder.setMessage("Loser!");
        } else if(gameResult == Result.WIN) {
            builder.setMessage("Winner!");
        } else if(gameResult == Result.DRAW) {
            builder.setMessage("It's a draw, try again!");
        }

        AlertDialog alert = builder.create();
        alert.show();
    }

    private void play() {
        // Generates a random number by which the android chooses its weapon
        int rand =  ((int)(Math.random() * 10)) % 3;
        Option androidSelection = null;
        ImageView imageView = (ImageView) findViewById(R.id.ImageViewAnswerAndroid);

        // Selects the right image to replace the placeholder hand
        switch (rand) {
            case 0:
                androidSelection = Option.ROCK;
                imageView.setImageResource(R.drawable.rock);
                break;
            case 1:
                androidSelection = Option.PAPER;
                imageView.setImageResource(R.drawable.paper);
                break;
            case 2:
                androidSelection = Option.SCISSORS;
                imageView.setImageResource(R.drawable.scissors);
                break;
        }
        // This determines the game result by looking at the android selection compared to the user selection.
        if(androidSelection == userSelection) {
            gameResult = Result.DRAW;
        }
        else if(androidSelection == Option.ROCK && userSelection == Option.SCISSORS) {
            gameResult = Result.LOSS;
        }
        else if(androidSelection == Option.PAPER && userSelection == Option.ROCK) {
            gameResult = Result.LOSS;
        }
        else if(androidSelection == Option.SCISSORS && userSelection == Option.PAPER) {
            gameResult = Result.LOSS;
        } else {
            gameResult = Result.WIN;
        }
    }
}
