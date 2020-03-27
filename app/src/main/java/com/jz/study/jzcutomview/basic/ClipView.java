package com.jz.study.jzcutomview.basic;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.jz.study.jzcutomview.R;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

public class ClipView extends View {

    private static final String TAG = "ClipView";
    private Bitmap mGirlBitmap;
    private Paint mPaint;
    private Path mPath;
    private int mGirlBitmapWidth;
    private int mGirlBitmapHeight;
    private Matrix mMatrix;
    private Camera mCamera;

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
        mGirlBitmapWidth = mGirlBitmap.getWidth();
        mGirlBitmapHeight = mGirlBitmap.getHeight();
        mPaint = new Paint();
        mPath = new Path();
        mPath.addCircle(400,400,400,Path.Direction.CW);

        mMatrix = new Matrix();
        mMatrix.reset();
        mMatrix.postTranslate(200,200);
        mMatrix.postRotate(45,mGirlBitmapHeight /2 ,mGirlBitmapWidth / 2);

        mCamera = new Camera();

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        //保存绘制之前状态
        canvas.save();
        canvas.clipRect(200,200,500,600);
        canvas.drawBitmap(mGirlBitmap,0,0,mPaint);
        //恢复绘制之前状态
        canvas.restore();

        canvas.save();
        canvas.clipOutPath(mPath);
        canvas.drawBitmap(mGirlBitmap,300,300,mPaint);
        canvas.restore();

        canvas.save();
        canvas.translate(200,0);
        canvas.rotate(45,mGirlBitmapHeight /2 ,mGirlBitmapWidth / 2);
        canvas.drawBitmap(mGirlBitmap,0,0,mPaint);
        canvas.restore();

        canvas.save();
        canvas.scale(0.5f, 1.3f,  mGirlBitmapWidth / 2, mGirlBitmapHeight / 2);
        canvas.drawBitmap(mGirlBitmap, 0, 0, mPaint);
        canvas.restore();

        canvas.save();
        canvas.skew(0.5f,0f);
        canvas.drawBitmap(mGirlBitmap,100,100,mPaint);
        canvas.restore();

        canvas.save();
        canvas.concat(mMatrix);
        canvas.drawBitmap(mGirlBitmap,0,0,mPaint);
        canvas.restore();

        canvas.save();
        mCamera.save();
        mCamera.rotateX(10);
        mCamera.applyToCanvas(canvas);
        mCamera.restore();
        canvas.drawBitmap(mGirlBitmap,100,300,mPaint);
        canvas.restore();

        canvas.save();
        mCamera.save();
        mCamera.rotateX(5);
        canvas.translate(mGirlBitmapWidth/2,mGirlBitmapHeight/2);
        mCamera.applyToCanvas(canvas);
        canvas.translate(-mGirlBitmapWidth/2,-mGirlBitmapHeight/2);
        mCamera.restore();
        canvas.drawBitmap(mGirlBitmap,100,0,mPaint);
        canvas.restore();



    }
}
