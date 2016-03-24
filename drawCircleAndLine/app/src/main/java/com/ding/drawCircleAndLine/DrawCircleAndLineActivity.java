package com.ding.drawCircleAndLine;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class DrawCircleAndLineActivity extends Activity {
    /**
     * Called when the activity is first created.
     * 画虚线，画圆点，画实线，仿物流进度demo
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.draw_circle_line);
        initStateView();
    }
    private RelativeLayout draw_state_layout;
    private final static int SMALL_DIAMETER = 30;
    private final static int BIG_DIAMETER = 40;
    private final static int MARGIN_TOP=10;
    private final static int LINEWIDTH=100;
    private final static int LINEHEIGHT=2;
    private void initStateView() {
        draw_state_layout=(RelativeLayout)findViewById(R.id.draw_state_layout);

        TextView textView1_top=new TextView(getApplicationContext());
        textView1_top.setId(R.id.text_a_top);
        RelativeLayout.LayoutParams textParams0=new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,RelativeLayout.LayoutParams.WRAP_CONTENT);
        textView1_top.setText("2015-07-01");
        textParams0.setMargins(20, MARGIN_TOP, 0, 0);
        textView1_top.setTextColor(getResources().getColor(R.color.red_ff3939));
        textView1_top.setLayoutParams(textParams0);
        draw_state_layout.addView(textView1_top);

        Circle circle=new Circle(R.color.red_ff3939,SMALL_DIAMETER/4,getApplicationContext());
        circle.setId(R.id.circle_a);
        RelativeLayout.LayoutParams circleParams=new RelativeLayout.LayoutParams(SMALL_DIAMETER/4*2,SMALL_DIAMETER/4*2);
        circleParams.addRule(RelativeLayout.BELOW, textView1_top.getId());
        circleParams.setMargins(20, MARGIN_TOP, 0, 0);
        circle.setLayoutParams(circleParams);
        draw_state_layout.addView(circle);

        TextView textView=new TextView(getApplicationContext());
        textView.setId(R.id.text_a);
        RelativeLayout.LayoutParams textParams1=new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,RelativeLayout.LayoutParams.WRAP_CONTENT);
        textParams1.addRule(RelativeLayout.BELOW, R.id.circle_a);
        textView.setText("ding1");
        textParams1.setMargins(20, MARGIN_TOP, 0, 0);
        textView.setTextColor(getResources().getColor(R.color.red_ff3939));
        textView.setLayoutParams(textParams1);
        draw_state_layout.addView(textView);

        View line1=new View(getApplicationContext());
        line1.setId(R.id.line_a);
        RelativeLayout.LayoutParams line1Params=new RelativeLayout.LayoutParams(LINEWIDTH,LINEHEIGHT);
        line1Params.addRule(RelativeLayout.RIGHT_OF,R.id.circle_a);
        line1Params.addRule(RelativeLayout.BELOW,textView1_top.getId());
        line1Params.setMargins(0, MARGIN_TOP + SMALL_DIAMETER / 4, 0, 0);
        line1.setBackgroundColor(getResources().getColor(R.color.red_ff3939));
        line1.setLayoutParams(line1Params);
        draw_state_layout.addView(line1);

        Circle circle1=new Circle(R.color.red_ff3939,SMALL_DIAMETER/3,getApplicationContext());
        circle1.setId(R.id.circle_b);
        RelativeLayout.LayoutParams circle1Params=new RelativeLayout.LayoutParams(SMALL_DIAMETER/3*2,SMALL_DIAMETER/3*2);
        circle1Params.addRule(RelativeLayout.RIGHT_OF, R.id.line_a);
        circle1Params.addRule(RelativeLayout.BELOW,textView1_top.getId());
        circle1Params.setMargins(0, MARGIN_TOP - 2, 0, 0);
        circle1.setLayoutParams(circle1Params);
        draw_state_layout.addView(circle1);

        View line2=new View(getApplicationContext());
        line2.setId(R.id.line_b);
        RelativeLayout.LayoutParams line2Params=new RelativeLayout.LayoutParams(LINEWIDTH,LINEHEIGHT);
        line2Params.addRule(RelativeLayout.RIGHT_OF,R.id.circle_b);
        line2Params.addRule(RelativeLayout.BELOW, textView1_top.getId());
        line2Params.setMargins(0,MARGIN_TOP+SMALL_DIAMETER/4,0,0);
        line2.setBackgroundColor(getResources().getColor(R.color.red_ff3939));
        line2.setLayoutParams(line2Params);
        draw_state_layout.addView(line2);

        Circle circle2=new Circle(R.color.red_ff3939,SMALL_DIAMETER/2,getApplicationContext());
        circle2.setId(R.id.circle_c);
        RelativeLayout.LayoutParams circle2Params=new RelativeLayout.LayoutParams(SMALL_DIAMETER/2*2,SMALL_DIAMETER/2*2);
        circle2Params.addRule(RelativeLayout.RIGHT_OF, R.id.line_b);
        circle2Params.addRule(RelativeLayout.BELOW,textView1_top.getId());
        circle2Params.setMargins(0, MARGIN_TOP-8, 0, 0);
        circle2.setLayoutParams(circle2Params);
        draw_state_layout.addView(circle2);


        DashedLineView dashedLineView=new DashedLineView(getResources().getColor(R.color.red_ff3939),LINEWIDTH,getApplicationContext());
        dashedLineView.setId(R.id.line_c);
        RelativeLayout.LayoutParams line3Params=new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        line3Params.addRule(RelativeLayout.RIGHT_OF, R.id.circle_c);
        line3Params.addRule(RelativeLayout.BELOW, textView1_top.getId());
        line3Params.setMargins(0, MARGIN_TOP + SMALL_DIAMETER / 4, 0, 0);
        dashedLineView.setLayoutParams(line3Params);
        draw_state_layout.addView(dashedLineView);

    }
}
