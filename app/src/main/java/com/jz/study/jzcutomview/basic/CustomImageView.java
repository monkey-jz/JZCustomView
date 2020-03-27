package com.jz.study.jzcutomview.basic;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.jz.study.jzcutomview.R;

import androidx.annotation.Nullable;

/**
 * @Auther: JerryZhu
 * @datetime: 2020/3/27
 * https://hencoder.com/ui-1-5/
 */
@SuppressLint("AppCompatCustomView")
public class CustomImageView extends View {
    private static final String TAG = "CustomImageView";

    String text = "girl";

    private Paint mPaint;
    private Paint mBacPaint;
    private Bitmap mBitmap;
    private int mMeasuredWidth;
    private int mMeasuredHeight;
    private int mBitmapWidth;
    private int mBitmapHeight;
    private Camera mCamera;
    public float degreeY;
    public float degreeZ;

    public float getDegreeZ() {
        return degreeZ;
    }

    public void setDegreeZ(float degreeZ) {
        this.degreeZ = degreeZ;
        invalidate();
    }

    public float getDegreeY() {
        return degreeY;
    }

    public void setDegreeY(float degreeY) {
        this.degreeY = degreeY;
        invalidate();
    }

    public CustomImageView(Context context) {
        super(context);
    }

    public CustomImageView(Context context, @Nullable AttributeSet attrs) {

        super(context, attrs);

        mBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.girl);

        mBitmapWidth = mBitmap.getWidth();
        mBitmapHeight = mBitmap.getHeight();


        mPaint = new Paint();
        mPaint.setTextSize(100);
        mPaint.setColor(Color.WHITE);

        mBacPaint = new Paint();
        mBacPaint.setColor(Color.RED);

        mCamera = new Camera();

        //沿Z轴平移，防止相机旋转后图片“糊”到脸上
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        float newZ = - displayMetrics.density * 6;
        mCamera.setLocation(0,0,newZ);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        mMeasuredWidth = getMeasuredWidth();
        mMeasuredHeight = getMeasuredHeight();
    }

    public CustomImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.save();
        mCamera.save();
        canvas.translate(mMeasuredWidth/2,mMeasuredHeight/2);
        canvas.rotate( -degreeZ );
        mCamera.rotateY(degreeY);
        mCamera.applyToCanvas(canvas);
        canvas.clipRect(0,-mMeasuredHeight/2,mMeasuredWidth/2,mMeasuredHeight/2);
        canvas.rotate( degreeZ);
        mCamera.restore();
        canvas.drawBitmap(mBitmap,-mBitmapWidth/2,-mBitmapHeight/2,mBacPaint);
        canvas.restore();

        canvas.save();
//        mCamera.save();
        canvas.translate(mMeasuredWidth/2,mMeasuredHeight/2);
        canvas.rotate(-degreeZ);
        canvas.clipRect(-mMeasuredWidth/2,-mMeasuredHeight/2,0,mMeasuredHeight/2);
        canvas.rotate(degreeZ);
        canvas.drawBitmap(mBitmap,-mBitmapWidth/2,-mBitmapHeight/2,mBacPaint);
        canvas.restore();


    }

    @Override
    public void onDrawForeground(Canvas canvas) {
        
//        canvas.drawRect(0,300,500,500,mBacPaint);
        Rect rect = new Rect();
        mPaint.getTextBounds(text,0,text.length(),rect);

//        Log.d(TAG,"rect.left === " + rect.left);
//        Log.d(TAG,"rect.right === " + rect.right);
//        Log.d(TAG,"rect.top === " + rect.top);
//        Log.d(TAG,"rect.bottom === " + rect.bottom);

//        canvas.drawText(text,200,400 + rect.bottom ,mPaint);
        super.onDrawForeground(canvas);

    }
}
