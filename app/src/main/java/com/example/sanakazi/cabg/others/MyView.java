package com.example.sanakazi.cabg.others;

/**
 * Created by sanakazi on 11/29/2016.
 */
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

public class MyView extends View {

    Paint paint;
    Path path;

    public MyView(Context context) {
        super(context);
        init();
    }

    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init(){
        paint = new Paint();
        paint.setColor(Color.BLUE);
        paint.setStrokeWidth(10);
        paint.setStyle(Paint.Style.STROKE);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        // TODO Auto-generated method stub
        super.onDraw(canvas);

        final RectF oval = new RectF();
        paint.setStyle(Paint.Style.STROKE);
  /*
   * drawArc(RectF oval, float startAngle, float sweepAngle, boolean useCenter, Paint paint)
   *
   * oval - The bounds of oval used to define the shape and size of the arc
   * startAngle - Starting angle (in degrees) where the arc begins
   * sweepAngle - Sweep angle (in degrees) measured clockwise
   * useCenter - If true, include the center of the oval in the arc, and close it if it is being stroked. This will draw a wedge
   * paint - The paint used to draw the arc
   */
 /*       oval.set(50, 50, 150, 150);
        canvas.drawArc(oval, 0, 45, true, paint);

        oval.set(200, 150, 450, 350);
        canvas.drawArc(oval, 0, 270, true, paint);*/

        oval.set(200, 400, 450, 600);
        canvas.drawArc(oval, 180, 180, false, paint);


    }

}
