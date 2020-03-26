package com.jz.study.jzcutomview.basic;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RadialGradient;
import android.graphics.Shader;
import android.graphics.SweepGradient;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * @Auther: JerryZhu
 * @datetime: 2020/3/26
 */
public class ShaderDraw extends View {
    private static final String TAG = "ShaderDraw";

    private Paint mPaint;
    private Matrix mMatrix;

    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            invalidate();
        }
    };

    public ShaderDraw(Context context) {
        super(context);
        init();
    }

    public ShaderDraw(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ShaderDraw(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {


        mPaint = new Paint();
//        mPaint.setColor(Color.CYAN);
//        参数：
//        x0 y0 x1 y1：渐变的两个端点的位置
//        color0 color1 是端点的颜色
//        tile：端点范围之外的着色规则，类型是 TileMode。TileMode 一共有 3 个值可选： CLAMP, MIRROR 和 REPEAT。CLAMP （夹子模式？？？算了这个词我不会翻）会在端点之外延续端点处的颜色；MIRROR 是镜像模式；REPEAT 是重复模式。
        LinearGradient linearGradient = new LinearGradient(0, 0, 300, 300,
                Color.parseColor("#E91E63"),  Color.parseColor("#2196F3"), Shader.TileMode.REPEAT);
        mPaint.setShader(linearGradient);
        mPaint.setTextSize(50f);


//        参数：
//        centerX centerY：辐射中心的坐标
//        radius：辐射半径
//        centerColor：辐射中心的颜色
//        edgeColor：辐射边缘的颜色
//        tileMode：辐射范围之外的着色模式。

        RadialGradient radialGradient = new RadialGradient(600, 600, 50, Color.parseColor("#E91E63"),
                Color.parseColor("#2196F3"), Shader.TileMode.CLAMP);
        mPaint.setShader(radialGradient);

//        参数：
//        cx cy ：扫描的中心
//        color0：扫描的起始颜色
//        color1：扫描的终止颜色
        SweepGradient sweepGradient = new SweepGradient(600, 600, new int[]{Color.TRANSPARENT, 0x00FA7298, 0xFFFA7298},
                new float[]{0, 0.7f, 1f});
        mPaint.setShader(sweepGradient);
//        mPaint.setStyle(Paint.Style.STROKE);

        mMatrix = new Matrix();

    }

    float degrees = 0f;

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        canvas.drawCircle(200,200,100,mPaint);
//        canvas.drawText("ceshi",200,100,mPaint);
//        canvas.drawRect(0,0,300,300,mPaint);

        if(degrees == 360f){
            degrees = 0f;
        }
        degrees += 5f;

        mMatrix.setRotate(degrees,600,600);
        canvas.concat(mMatrix);

        canvas.drawCircle(600,600,500,mPaint);

        mHandler.sendEmptyMessageDelayed(0,10);

    }
}
