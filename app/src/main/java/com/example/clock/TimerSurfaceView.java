// https://pastebin.com/trKKeRbA
package com.example.clock;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class TimerSurfaceView extends SurfaceView implements Runnable {

    private int secs = 600;
    private float length;
    private Thread thread;
    private boolean running = false;
    private SurfaceHolder holder;

    public TimerSurfaceView(Context context, float length , int secs){
        super(context);
        this.length = length;
        this.secs = secs;
        this.holder = getHolder();
    }

    public void onResumeTimer(){
        thread = new Thread(this);
        thread.start();
        running = true;
    }

    public void onPauseTimer(){
        running = false;
        boolean reentry = true;
        while (reentry) {
            try {
                thread.join();
                reentry = false;
            } catch (Exception e) {

            }
        }
    }
    @Override
    public void run(){
        int sec = 0;
        while(running){
            if(holder.getSurface().isValid()){
                Canvas canvas = holder.lockCanvas();
                Paint paint = new Paint(); paint.setColor(Color.BLACK);
                canvas.drawPaint(paint);
                paint.setColor(Color.WHITE);
                TimerRegPoly timer = new TimerRegPoly(getWidth()/2, getHeight()/2, length, canvas,paint);
                timer.drawTimer();
                try{Thread.sleep(1000*secs/60);}
                catch (Exception e){}
                sec++;
                if (sec == secs){
                    running = false;
                }
                holder.unlockCanvasAndPost(canvas);
            }
        }
    }

}

