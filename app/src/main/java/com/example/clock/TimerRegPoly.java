// https://pastebin.com/hLPnyzjV
package com.example.clock;

import android.graphics.Canvas;
import android.graphics.Paint;

public class TimerRegPoly {
    // ivars
    private int n;
    private float x0, y0, r;
    private float x[], y[];

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
        paint.setTextSize(200);
        this.canvas.drawText("Test",x0,y0,paint);
    }

}
