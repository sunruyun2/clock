// https://pastebin.com/hLPnyzjV
package com.example.clock;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class ClockSurfaceView extends SurfaceView implements Runnable {

    private float length;
    private Thread thread;
    private boolean running = false;
    private SurfaceHolder holder;

    public ClockSurfaceView(Context context, float length){
        super(context);
        this.length = length;
        this.holder = getHolder();
    }

    public void onResumeClock(){
        thread = new Thread(this);
        thread.start();
        running = true;
    }

    public void onPauseClock(){
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

                //draw the marks
                paint.setColor(Color.RED);
                RegPoly setMarks = new RegPoly(60, getWidth()/2, getHeight()/2, length,canvas, paint);
                RegPoly secHand = new RegPoly(60, getWidth()/2, getHeight()/2, length -20,canvas, paint);
                //n -- spead -- sperate
                //RegPoly minHand = new RegPoly(3600, getWidth()/2, getHeight()/2, length -60,canvas, paint);
                //RegPoly setHourMarks = new RegPoly(12, getWidth()/2, getHeight()/2, length - 10,canvas, paint);
                setMarks.drawNodes();
                //setHourMarks.drawNodes();
                secHand.drawRadius(sec + 45);
                //minHand.drawRadius(sec + 2700);

                //sleep for 1 sec
                //try{Thread.sleep(1000);}
                //catch (Exception e){}
                sec++;
                holder.unlockCanvasAndPost(canvas);
            }
        }
    }

}
