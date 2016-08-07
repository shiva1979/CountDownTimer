package com.example.countdowntimer;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private TextView timerText;

    private Button timerBtn;

    private CountDownTimer countDownTimer;

    private boolean timerIsRunning= false;

    private final int START_TIME = 50000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timerText = (TextView) findViewById(R.id.timer_text);
        timerBtn = (Button) findViewById(R.id.timer_btn);

        initTimer();
        timerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!timerIsRunning)
                {
                    if(countDownTimer != null)
                    {
                        countDownTimer.start();
                        timerIsRunning = true;
                        timerBtn.setText("Stop");
                    }
                }
                else
                {
                    countDownTimer.cancel();
                    timerIsRunning = false;
                    timerBtn.setText("Start");
                }
            }
        });
    }

    private void initTimer()
    {

        countDownTimer = new CountDownTimer(START_TIME, 1000) {

            public void onTick(long millisUntilFinished) {
                timerText.setText("Seconds remaining: " + millisUntilFinished / 1000);
            }

            public void onFinish() {
                timerText.setText(R.string.end_timer_text);
                timerBtn.setText("Restart");
                countDownTimer.cancel();
                timerIsRunning = false;
                showToast("Timer Ends");
            }
        };

    }

    private void showToast(String msg)
    {
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
    }





}
