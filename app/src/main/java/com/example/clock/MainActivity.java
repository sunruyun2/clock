package com.example.clock;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    ClockSurfaceView clock;
    //TimerSurfaceView clock;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //get the size of the screen

        clock = new ClockSurfaceView(this , 300);
        //clock = new ClockSurfaceView(this, 300 , 60);
        setContentView(clock);
    }

    @Override
    protected void onPause(){
        super.onPause();
        clock.onPauseClock();
    }
    @Override
    protected void onResume(){
        super.onResume();
        clock.onResumeClock();
    }
}