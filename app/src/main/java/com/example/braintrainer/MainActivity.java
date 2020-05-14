package com.example.braintrainer;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    ArrayList<Integer> randomAnswers;
    Button button1;
    Button button2;
    Button button3;
    Button button4;
    Button playAgain;
    TextView questionBox;
    TextView verificationText;
    TextView scoreText;
    GridLayout answerGrid;
    TextView timerText;
    int randomLocation;
    int totalAttempted = 0;
    int correctAnswers = 0;

    public void startGame(View view) {
        Button goButton = findViewById(R.id.goButton);
        goButton.setVisibility(View.INVISIBLE);
    }

    public void chooseAnswer(View view) {

        if (Integer.toString(randomLocation).equals(view.getTag().toString())) {
            verificationText.setText("Correct !");
            correctAnswers++;
        } else {
            verificationText.setText("Wrong");
        }
        totalAttempted++;
        fillAnswerAndQuestionGrid();
        scoreText.setText(correctAnswers + "/" + totalAttempted);
    }

    public void fillAnswerAndQuestionGrid() {
        Random rand = new Random();
        int a = rand.nextInt(21);
        int b = rand.nextInt(21);

        questionBox.setText(a + " + " + b);

        randomAnswers = new ArrayList<Integer>();
        for (int i = 0; i < 4; i++) {
            randomAnswers.add(rand.nextInt(21) + 21);
        }

        randomLocation = rand.nextInt(4);
        randomAnswers.add(randomLocation, a + b);

        button1.setText(randomAnswers.get(0).toString());
        button2.setText(randomAnswers.get(1).toString());
        button3.setText(randomAnswers.get(2).toString());
        button4.setText(randomAnswers.get(3).toString());
    }

    public void startGameAgain(View view) {
        button1.setEnabled(true);
        button2.setEnabled(true);
        button3.setEnabled(true);
        button4.setEnabled(true);

        fillAnswerAndQuestionGrid();
        startGameTimer();
        scoreText.setText("");
        totalAttempted = 0;
        correctAnswers = 0;
    }

    public void startGameTimer() {

        new CountDownTimer(32000, 1000) {
            public void onTick(long millsLeft) {
                timerText.setText((millsLeft / 1000) + "s");
            }

            public void onFinish() {
                button1.setEnabled(false);
                button2.setEnabled(false);
                button3.setEnabled(false);
                button4.setEnabled(false);
                playAgain.setVisibility(View.VISIBLE);
//                MediaPlayer m = new MediaPlayer(getApplicationContext(),)

            }
        }.start();
        playAgain.setVisibility(View.INVISIBLE);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.button4);
        playAgain = findViewById(R.id.playAgainButton);
        questionBox = findViewById(R.id.questionBox);
        verificationText = findViewById(R.id.verificationText);
        scoreText = findViewById(R.id.scoreText);

        timerText = findViewById(R.id.timerText);

        fillAnswerAndQuestionGrid();
        startGameTimer();

    }
}
