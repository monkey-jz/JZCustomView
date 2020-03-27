package com.jz.study.jzcutomview.anim;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

import java.text.NumberFormat;

import androidx.annotation.Nullable;

/**
 * @Auther: JerryZhu
 * @datetime: 2020/3/27
 */
public class PropertyAnimView extends View {

    private int mWidth;
    private int mHeight;
    private Paint mPaint;


    public float process;
    private Paint mTextPaint;

    public PropertyAnimView(Context context) {
        super(context);
    }

    public PropertyAnimView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.RED);
        mPaint.setStrokeWidth(100);
        mPaint.setStrokeCap(Paint.Cap.ROUND);

        mTextPaint = new Paint();
        mTextPaint.setTextSize(100);
        mTextPaint.setColor(Color.BLACK);
        //该方法即为设置基线上那个点究竟是left,center,还是right  这里我设置为center
        mTextPaint.setTextAlign(Paint.Align.CENTER);
    }

    public PropertyAnimView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawArc(100,100,900,900,0,process,false,mPaint);
        float i = process / 360 ;
        NumberFormat format = NumberFormat.getPercentInstance();
        //小数最大保留2位
        format.setMaximumFractionDigits(0);
        String str = format.format(i);
        //文字的y轴坐标
        Paint.FontMetrics fontMetrics = mTextPaint.getFontMetrics();
        float y = 500 + (Math.abs(fontMetrics.ascent) - fontMetrics.descent) / 2;

        float textWidth = mTextPaint.measureText(str);
        Rect rect = new Rect();
        mTextPaint.getTextBounds(str,0,str.length(),rect);

        canvas.drawText(str,500 ,y,mTextPaint);

    }

    public float getProcess() {
        return process;
    }

    public void setProcess(float process) {
        this.process = process;
        if(process == 360){
            process =0;
        }
        invalidate();
    }

}
