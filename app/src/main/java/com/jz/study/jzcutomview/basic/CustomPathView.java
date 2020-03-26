package com.jz.study.jzcutomview.basic;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

import com.jz.study.jzcutomview.R;

import androidx.annotation.Nullable;

/**
 * @Auther: JerryZhu
 * @datetime: 2020/3/25
 */
public class CustomPathView extends View {

    private Paint mPathPaint;
    private Path mPath;
    private Bitmap mGirlBitmap;

    public CustomPathView(Context context) {
        this(context,null);
    }

    public CustomPathView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public CustomPathView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        mGirlBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.girl);

        mPathPaint = new Paint();
        mPathPaint.setColor(Color.CYAN);
        mPathPaint.setStrokeWidth(30);
        mPath = new Path();
        mPath.addArc(200, 200, 400, 400, -225, 225);
        mPath.arcTo(400, 200, 600, 400, -180, 225, false);
        mPath.lineTo(400, 542);
        mPath.addCircle(500,500,200, Path.Direction.CCW);
        mPath.addRoundRect(600,600,900,900,100,100, Path.Direction.CW);

//        mPathPaint.setStyle(Paint.Style.STROKE);
//        mPath.lineTo(200,1000);
//        mPath.rLineTo(300,0);
//        mPath.moveTo( 400,400);
//        mPath.lineTo(600,600);

//        mPath.arcTo(210,700,600,1090,-180,270,true);
        mPath.close();


    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawPath(mPath,mPathPaint);
        canvas.drawBitmap(mGirlBitmap,0,0,mPathPaint);
    }
}
