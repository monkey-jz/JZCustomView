package com.jz.study.jzcutomview.basic;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * @Auther: JerryZhu
 * @datetime: 2020/3/25
 *
 * https://hencoder.com/ui-1-1/
 */
public class Basc1 extends View {

    private Paint mCirclePaint;
    private Paint mTextPaint;
    private Paint mPointPaint;
    private Paint mArcPaint;
    private Float radius = 200f;
    private Float centX = 500f;
    private Float centY = 500f;
    private String text = "测试";
    private int mTextWidth;
    private int mTextHeight;
    private int mWidth;
    private int mHeight;
    private float[] mPoints;

    public Basc1(Context context) {
        this(context,null);

    }

    public Basc1(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public Basc1(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mCirclePaint = new Paint();
        mTextPaint = new Paint();
        mPointPaint = new Paint();
        mArcPaint = new Paint();

        mCirclePaint.setColor(Color.RED);
        mCirclePaint.setStyle(Paint.Style.STROKE);
        mCirclePaint.setStrokeWidth(20f);
        mCirclePaint.setAntiAlias(true);
        mCirclePaint.setTextSize(30f);


        mTextPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        mTextPaint.setStrokeWidth(1f);
        mTextPaint.setColor(Color.CYAN);
        mTextPaint.setTextSize(100f);
        mTextPaint.setAntiAlias(true);

        Rect textRect = new Rect();
        mTextPaint.getTextBounds(text,0,text.length(),textRect);
        mTextWidth = textRect.width();
        mTextHeight = textRect.height();


        mPoints = new float[]{0, 0, 50, 50, 50, 100, 100, 50, 100, 100, 150, 50, 150, 100};
        mPointPaint.setStrokeWidth(30);
        mPointPaint.setStrokeCap(Paint.Cap.ROUND);
        mPointPaint.setColor(Color.BLUE);

        mArcPaint.setStyle(Paint.Style.FILL);
        mArcPaint.setColor(Color.CYAN);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        mWidth = MeasureSpec.getSize(widthMeasureSpec);
        mHeight = MeasureSpec.getSize(heightMeasureSpec);
        centX =(float) mWidth / 2;
        centY = (float)mHeight / 2;
        setMeasuredDimension(mWidth,mHeight);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawColor(Color.parseColor(("#ff045500")));
        canvas.drawColor(Color.BLACK);

        canvas.drawCircle(centX,centY,radius, mCirclePaint);
        canvas.drawText(text,centX - mTextWidth / 2,centY + mTextHeight / 2,mTextPaint);

        canvas.drawRect(50,50,200,200,mCirclePaint);

        canvas.drawPoint( 300,300,mPointPaint);
        canvas.drawPoints(mPoints,2,8,mPointPaint);

        canvas.drawOval(50,100,400,600,mCirclePaint);

        canvas.drawLine(300,300,700,700,mCirclePaint);

        canvas.drawRoundRect(mWidth / 2 ,mHeight / 2 ,mWidth,mHeight,100,100,mCirclePaint);

        canvas.drawArc(200, 100, 800, 500, -110, 100, true, mArcPaint);

        canvas.drawArc(200, 100, 800, 500, 20, 140, false, mArcPaint);


    }
}
