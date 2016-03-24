package com.ding.drawCircleAndLine;

import android.content.Context;
import android.graphics.*;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by dingli on 2015-7-1.
 * 画虚线
 */
public class DashedLineView extends View{
    private int color=getResources().getColor(R.color.red_ff3939);
    private float line_width=0f;
    public DashedLineView(Context context) {
        super(context);
    }
    public DashedLineView(int color,float line_width,Context context) {
        super(context);
        this.color=color;
        this.line_width=line_width;
    }

    public DashedLineView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint=new Paint();
        paint.setColor(getResources().getColor(android.R.color.holo_red_light));
        paint.setStrokeWidth(2f);
        Path path=new Path();
        path.moveTo(0,0);
        path.lineTo(line_width,0);
        paint.setStyle(Paint.Style.STROKE);
        PathEffect pathEffect=new DashPathEffect(new float[]{10,10,10,10},1);
        paint.setPathEffect(pathEffect);
        canvas.drawPath(path, paint);
    }
}
