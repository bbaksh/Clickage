package com.example.brandon.clickage;

import android.app.Activity;
import android.os.CountDownTimer;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;


public class MainActivity extends Activity implements View.OnClickListener {
    public RelativeLayout screen;
    public TextView t;
    public TextView score;
    public TextView highScoreDisplay;
    public ProgressBar timer;
    public CountDownTimer countDown;
    public Button pauseBut;
    public int time = 0;
    public int x = 0;
    public int highScore = 0;
    public int timeRemaining = 15000;
    public boolean isPaused=false;

    public void timeActivation() {
        countDown = new CountDownTimer(timeRemaining, 500) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeRemaining = (int)millisUntilFinished;
                time++;
                timer.setProgress(time/2);
            }

            @Override
            public void onFinish() {
                time++;
                timer.setProgress(time/2);
                if (x > highScore) {
                    highScore = x;
                    highScoreDisplay.setText("Highscore: " + String.valueOf(highScore));
                }
                if (x > 0 && x <= 50) {
                    score.setText(String.valueOf(x));
                    score.setTextSize(80);
                    t.setText("Do you even click bro?!");
                    t.setTextSize(25);
                }
                if (x > 50 && x <= 100) {
                    score.setText(String.valueOf(x));
                    score.setTextSize(80);
                    t.setText("Mediocre clicker!");
                    t.setTextSize(25);
                }
                if (x > 100 && x <= 150) {
                    score.setText(String.valueOf(x));
                    score.setTextSize(80);
                    t.setText("Amazing clicker!");
                    t.setTextSize(25);
                }
                if (x > 150) {
                    score.setText(String.valueOf(x));
                    score.setTextSize(80);
                    t.setText("CLICK MASTER!");
                    t.setTextSize(25);
                }
            }
        }.start();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //setup screen
        screen = (RelativeLayout)findViewById(R.id.main);

        //setup buttons
        pauseBut = (Button)findViewById(R.id.button2);

        //set up main text views for tapping counter
        t = (TextView)findViewById(R.id.editText);
        t.setOnClickListener(this);
        t.setText("Tap as fast as you can in 15 seconds");
        t.setTextSize(20);
        score = (TextView)findViewById(R.id.textView);
        score.setText("");
        highScoreDisplay = (TextView)findViewById(R.id.highScoreText);
        highScoreDisplay.setText("");

        //set up timer for progress bar
        timer = (ProgressBar)findViewById(R.id.progressBar);
        timer.setProgress(time);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onClick(View view){
        if(time>=30 || isPaused==true){
            return;
        }
        ++x;
        if(x==1) {
            timeActivation();
        }
        t.setText(String.valueOf(x));
        t.setTextSize(100);
        if(x>highScore){
            highScore=x;
            highScoreDisplay.setText("Highscore: " + String.valueOf(highScore));
        }
    }
    public void restart(View view){
        if(x>0){
            countDown.cancel();
        }
        x=0;
        time=0;
        timeRemaining=15000;
        timer.setProgress(time);
        t.setText("Tap as fast as you can in 15 seconds");
        t.setTextSize(20);
        score.setText("");
        pauseBut.setText("PAUSE");
        isPaused = false;
    }

    public void pauseButton(View view) {
        if (time < 30 && time>0) {
            countDown.cancel();
            if (isPaused == false) {
                isPaused = true;
                pauseBut.setText("UNPAUSE");
                return;
            }
            if (isPaused == true) {
                isPaused = false;
                pauseBut.setText("PAUSE");
                timeActivation();
                return;
            }
        }
    }
}
