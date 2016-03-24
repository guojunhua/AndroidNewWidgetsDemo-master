package com.ding.drawCircleAndLine;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by dingli on 2015-7-1.
 * 画圆点
 */
public class Circle extends View {
    private int color=getResources().getColor(R.color.red_ff3939),radius=0;

    public Circle(Context context) {
        super(context);
    }
    public Circle(int  color,int radius,Context context){
        super(context);
        this.radius=radius;
        this.color=color;

    }
    public Circle(Context context, AttributeSet attrs) {
        super(context, attrs);
//        if(attrs!=null){
//
//            TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.circle);
//            radius = a.getDimensionPixelSize(R.styleable.circle_radius, 0);
//            color = a.getColor(R.styleable.circle_color, color);
//
//        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint=new Paint();
        int center=getWidth()/2;
        paint.setColor(getResources().getColor(R.color.red_ff3939));
        paint.setAntiAlias(true);
        paint.setStrokeWidth(1.0f);
        canvas.drawCircle(center,center,radius,paint);
    }
}
