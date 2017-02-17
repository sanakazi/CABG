package com.example.sanakazi.cabg.wheelview;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.DisplayMetrics;
import android.util.TypedValue;

import com.example.sanakazi.cabg.R;

public class TextDrawable extends Drawable {

    private final String text;
    private final Paint paint;
    Context c;


/*    public TextDrawable(String text) {

        this.text = text;
        this.paint = new Paint();
        paint.setColor(Color.BLACK);
      //  paint.setTextSize(35f);
        paint.setTextSize(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,c.getResources().getDimension(R.dimen.text_size_12), c.getResources().getDisplayMetrics()));
        paint.setAntiAlias(true);
        paint.setFakeBoldText(true);
      //  paint.setShadowLayer(12f, 0, 0, Color.BLACK);
        paint.setStyle(Paint.Style.FILL);
        paint.setTextAlign(Paint.Align.LEFT);
    }*/
    public TextDrawable(Context c,String text){
        this.text = text;
        this.paint = new Paint();
        this.c = c;
        paint.setColor(Color.BLACK);
        paint.setTextSize(25f);
       // paint.setTextSize(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,c.getResources().getDimension(R.dimen.text_size_12), c.getResources().getDisplayMetrics()));
        paint.setAntiAlias(true);
        paint.setFakeBoldText(true);
        //  paint.setShadowLayer(12f, 0, 0, Color.BLACK);
        paint.setStyle(Paint.Style.FILL);
        paint.setTextAlign(Paint.Align.LEFT);
    }

    @Override
    public void draw(Canvas canvas) {
        Rect bounds = getBounds();
        canvas.drawText(text, bounds.centerX() - 15f /*just a lazy attempt to centre the text*/ * text.length(), bounds.centerY() + 15f, paint);
    }

    @Override
    public void setAlpha(int alpha) {
        paint.setAlpha(alpha);
    }

    @Override
    public void setColorFilter(ColorFilter cf) {
        paint.setColorFilter(cf);
    }

    @Override
    public int getOpacity() {
        return PixelFormat.TRANSLUCENT;
    }

    public String getText() {
        return text;
    }


}