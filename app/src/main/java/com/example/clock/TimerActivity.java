package com.example.clock;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.Timer;

public class TimerActivity extends AppCompatActivity {

    ClockSurfaceView clock;
    TimerSurfaceView timer;
    ConstraintLayout timerLayout;
    Button start ,add, minus;
    int secs = 30;
    boolean timerStart = false;
    Button button3, button4;

    //edit menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.example_menu_for_timer, menu);

        return true;
    }

    //edit menu activity
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.item1:
                Intent myIntent = new Intent(TimerActivity.this, MainActivity.class);//Optional parameters
                TimerActivity.this.startActivity(myIntent);
            default:return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);


        clock = new ClockSurfaceView(this , 400);
        timer = new TimerSurfaceView(this, 400 , secs);
        timerLayout = (ConstraintLayout) findViewById(R.id.timerLayout);
        timerLayout.addView(timer);


        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.button4);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timer.changeTheme(true);
            }
        });
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timer.changeTheme(false);
            }
        });

        //start the timer
        start = findViewById(R.id.timerStart);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timer.setStart();
            }
        });

        //add secs
        add = findViewById(R.id.plus30);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (secs < 3570){
                    secs = secs + 30;
                    timer.setSecs(secs);
                } else {
                    Toast.makeText(TimerActivity.this, "time is beyond the limit", Toast.LENGTH_SHORT).show();
                }
            }
        });



        //reduce secs
        minus = findViewById(R.id.minus30);
        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (secs >= 30){
                    secs = secs - 30;
                    timer.setSecs(secs);
                } else {
                    Toast.makeText(TimerActivity.this, "time ie below the limit", Toast.LENGTH_SHORT).show();
                }
            }
        });



    }

    @Override
    protected void onPause(){
        super.onPause();
        clock.onPauseClock();
        timer.onPauseTimer();
    }
    @Override
    protected void onResume(){
        super.onResume();
        clock.onResumeClock();
        timer.onResumeTimer();
    }
}