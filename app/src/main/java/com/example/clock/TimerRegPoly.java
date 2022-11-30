// https://pastebin.com/hLPnyzjV
package com.example.clock;

import android.graphics.Canvas;
import android.graphics.Paint;

public class TimerRegPoly {
    // ivars
    private int n;
    private float x0, y0, r;
    private float x[], y[];
    int secs = 600;

    private Canvas canvas; private Paint paint;

    // constructor
    public TimerRegPoly( float x0, float y0, float r, Canvas canvas, Paint paint) {
        this.x0 = x0;
        this.y0 = y0;
        this.r = r;

        this.canvas = canvas;
        this.paint = paint;

    }

    public void drawTimer(){
        paint.setTextSize(r);
        String time = convertTimeToText(secs);
        this.canvas.drawText(time, x0 - r,y0,paint);
    }

    public String convertTimeToText(int secs){
        int firstDigit = secs / 600;
        int secondDigit = secs % 600 / 60;
        int thirdDigit = secs % 60 / 10;
        int forthDigit = secs % 10;
        return Integer.toString(firstDigit)
                + Integer.toString(secondDigit)
                + ":"
                + Integer.toString(thirdDigit)
                + Integer.toString(forthDigit);
    }

}
