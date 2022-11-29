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

    //edit menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.example_menu, menu);

        return true;
    }

    //edit menu activity
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.item1:
                Intent myIntent = new Intent(TimerActivity.this, MainActivity.class);//Optional parameters
                TimerActivity.this.startActivity(myIntent);

            case R.id.item2:
                //Intent myIntent = new Intent(MainActivity.this, TimerActivity.class);//Optional parameters
                //MainActivity.this.startActivity(myIntent);
            default:return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);


        clock = new ClockSurfaceView(this , 300);
        timer = new TimerSurfaceView(this, 300 , 60);
        timerLayout = (ConstraintLayout) findViewById(R.id.timerLayout);
        timerLayout.addView(timer);
    }
}