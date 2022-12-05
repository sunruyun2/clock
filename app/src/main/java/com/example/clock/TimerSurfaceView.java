// https://pastebin.com/trKKeRbA
package com.example.clock;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class TimerSurfaceView extends SurfaceView implements Runnable {

    private int secs;
    private float length;
    private Thread thread;
    private boolean running = false;
    private SurfaceHolder holder;
    private boolean start = false;
    private boolean isBlackTheme = true;

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

    public void changeTheme(boolean isBlackTheme){
        this.isBlackTheme = isBlackTheme;
    }

    //draw a text before the button(based on input) is clicked
    @Override
    public void run(){
        int counter = 0;
        while(running){
            if(holder.getSurface().isValid()){
                Canvas canvas = holder.lockCanvas();
                Paint paint = new Paint();

                paint.setColor(Color.BLACK);
                if (!isBlackTheme){paint.setColor(Color.WHITE);};
                canvas.drawPaint(paint);

                paint.setColor(Color.WHITE);
                if (!isBlackTheme){paint.setColor(Color.BLACK);};

                TimerRegPoly timer = new TimerRegPoly(getWidth()/2, getHeight()/2, length, canvas,paint, secs);
                timer.drawTimer();
                try{Thread.sleep(1000);}
                catch (Exception e){}
                if (start & secs >0){
                    secs--;
                }

                if (start & secs <= 0){
                    if (counter % 2 == 1){
                        paint.setColor(Color.BLACK);
                        if (!isBlackTheme){paint.setColor(Color.WHITE);};
                        canvas.drawPaint(paint);
                    }
                    counter++;

                }
                holder.unlockCanvasAndPost(canvas);
            }
        }
    }

    public void setStart(){
        this.start = true;
    }

    public void setStart(boolean isStart){
        this.start = isStart;
    }

    public void setSecs(int secs){
        this.secs = secs;
    }


}

