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
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    ClockSurfaceView clock;
    TimerSurfaceView timer;
    ConstraintLayout mainLayout;
    boolean isClock = true;

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
                Intent myIntent = new Intent(MainActivity.this, TimerActivity.class);//Optional parameters
                MainActivity.this.startActivity(myIntent);

            default:return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //get the size of the screen
        setContentView(R.layout.activity_main);

        clock = new ClockSurfaceView(this , 300);
        timer = new TimerSurfaceView(this, 300 , 60);
        mainLayout = (ConstraintLayout) findViewById(R.id.main_layout);
        //mainLayout.addView(timer);
        mainLayout.addView(clock);

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