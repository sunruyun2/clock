// https://pastebin.com/hLPnyzjV
package com.example.clock;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.Calendar;

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

        //record current time
        Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int second = c.get(Calendar.SECOND);
        int minute = c.get(Calendar.MINUTE);
        hour = hour > 12 ? hour - 12 : hour;
        minute = minute * 60 + second;
        hour = hour * 60* 60 + minute;

        while(running){
            if(holder.getSurface().isValid()){
                Canvas canvas = holder.lockCanvas();
                Paint paint = new Paint(); paint.setColor(Color.BLACK);
                canvas.drawPaint(paint);

                //draw the marks
                paint.setColor(Color.WHITE);
                paint.setStrokeWidth(10);
                RegPoly setMarks = new RegPoly(60, getWidth()/2, getHeight()/2, length,canvas, paint);
                setMarks.drawNodes();

                //draw text
                RegPoly setHourMarks = new RegPoly(12, getWidth()/2, getHeight()/2, length - 40,canvas, paint);
                //setHourMarks.drawNodes();
                setHourMarks.drawNumeral();

                //draw the hands //n -- speed -- seperate
                RegPoly secHand = new RegPoly(60, getWidth()/2, getHeight()/2, length -20,canvas, paint);
                RegPoly minHand = new RegPoly(60*60, getWidth()/2, getHeight()/2, length -80,canvas, paint);
                RegPoly hourHand = new RegPoly(12*60*60, getWidth()/2, getHeight()/2, length -120,canvas, paint);


                secHand.drawRadius(sec + 45 + second); //cos(2*pi*45/60 = 0)
                minHand.drawRadius(sec + 45 * 60 + minute); // 60*60*0.75
                hourHand.drawRadius(sec + 45 * 60 * 12 + hour); //60*60*0.75

               //sleep for 1 sec
                try{Thread.sleep(1000);}
                catch (Exception e){}
                sec++;
                holder.unlockCanvasAndPost(canvas);
            }
        }
    }
}
