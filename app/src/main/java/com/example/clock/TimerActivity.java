package com.example.clock;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import java.util.Timer;

public class TimerActivity extends AppCompatActivity {

    ClockSurfaceView clock;
    TimerSurfaceView timer;
    ConstraintLayout timerLayout;
    boolean isClock = false;

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


        clock = new ClockSurfaceView(this , 300);
        timer = new TimerSurfaceView(this, 300 , 600);
        timerLayout = (ConstraintLayout) findViewById(R.id.timerLayout);
        timerLayout.addView(timer);
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