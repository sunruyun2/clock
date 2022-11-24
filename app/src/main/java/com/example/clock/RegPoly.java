// https://pastebin.com/hLPnyzjV
package com.example.clock;

import android.graphics.Canvas;
import android.graphics.Paint;

public class RegPoly {
    // ivars
    private int n;
    private float x0, y0, r;
    private float x[], y[];

    private Canvas canvas; private Paint paint;

    // constructor
    public RegPoly(int n, float x0, float y0, float r, Canvas canvas, Paint paint) {
        this.n = n;
        this.x0 = x0;
        this.y0 = y0;
        this.r = r;

        this.canvas = canvas;
        this.paint = paint;

        // calculate x[] and y[]
        this.x = new float[this.n];this.y = new float[this.n];
        for(int i=0;i<n;i++){
            this.x[i] = (float) (x0 + r * Math.cos(2*Math.PI*i/n));
            this.y[i] = (float) (y0 + r * Math.sin(2*Math.PI*i/n));

        }
    }

    // getters
    public float getX(int i){return this.x[i % this.n];}
    public float getY(int i){return this.y[i % this.n];}

    // draw-ers
    public void drawRadius(int i){
        this.canvas.drawLine(this.x0, this.y0, this.getX(i), this.getY(i), this.paint);
    }

    public void drawNodes(){
        for(int i=0;i<this.n;i++){
            this.canvas.drawCircle(this.getX(i), this.getY(i), 4, this.paint);
        }
    }

    public void drawNumeral(){
        paint.setTextSize(30);
        int [] number = {1,2,3,4,5,6,7,8,9,10,11,12};
        for (int i = 0; i < n; i++) {
            String tmp = String.valueOf(number[(i + 2) % 12]);
            this.canvas.drawText(tmp,this.getX(i),this.getY(i), paint);
        }
    }
    /* drawText
    private void drawNumeral(Canvas canvas) {
        paint.setTextSize(fontSize);
        for (int number:numbers){
            String tmp = String.valueOf(number);
            paint.getTextBounds(tmp, 0 , tmp.length(), rect);
            double angle = Math.PI / 6 * (number - 3);
            int x = (int) (width / 2 + Math.cos(angle)*radius - rect.width() / 2);
            int y = (int) (height / 2 + Math.sin(angle)*radius - rect.width() / 2);
            canvas.drawText(tmp,x,y,paint);
        }
    }*/

}
