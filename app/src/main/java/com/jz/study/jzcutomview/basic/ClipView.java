package com.jz.study.jzcutomview.basic;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.jz.study.jzcutomview.R;

import androidx.annotation.Nullable;

public class ClipView extends View {

    private static final String TAG = "ClipView";
    private Bitmap mGirlBitmap;
    private Paint mPaint;
    private Path mPath;

    public ClipView(Context context) {
        this(context,null);
    }


    public ClipView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public ClipView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }


    private void init(Context context) {

        mGirlBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.girl);
        mPaint = new Paint();
        mPath = new Path();
        mPath.addCircle(300,300,400,Path.Direction.CCW);


    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.save();
        canvas.clipRect(200,200,500,600);
        canvas.drawBitmap(mGirlBitmap,0,0,mPaint);
        canvas.restore();

        canvas.save();
        canvas.clipPath(mPath);
        canvas.drawBitmap(mGirlBitmap,100,100,mPaint);
        canvas.restore();

    }
}
