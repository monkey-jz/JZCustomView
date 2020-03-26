package com.jz.study.jzcutomview.basic;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.CornerPathEffect;
import android.graphics.DashPathEffect;
import android.graphics.DiscretePathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathDashPathEffect;
import android.graphics.PathEffect;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.jz.study.jzcutomview.R;

import java.util.Locale;

import androidx.annotation.Nullable;

/**
 * @Auther: JerryZhu
 * @datetime: 2020/3/25
 */
public class CustomPathView extends View {

    private static final String TAG = "CustomPathView";

    private Paint mPathPaint;
    private Path mPath;
    private Bitmap mGirlBitmap;
    private TextPaint mTextPaint;

    public CustomPathView(Context context) {
        this(context,null);
    }

    public CustomPathView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public CustomPathView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        mPathPaint = new Paint();
        mPathPaint.setColor(Color.BLACK);
        mPathPaint.setStyle(Paint.Style.STROKE);
        mPathPaint.setAntiAlias(true);
        mPathPaint.setTextSize(50);

        mTextPaint = new TextPaint();
        mTextPaint.setColor(Color.BLACK);
        mTextPaint.setAntiAlias(true);
        mTextPaint.setTextSize(50);
        //设置字体
        mTextPaint.setTypeface(Typeface.SERIF);
        //字体加粗
        mTextPaint.setFakeBoldText(true);
        //删除线
        mTextPaint.setStrikeThruText(true);
        //下划线
        mTextPaint.setUnderlineText(true);
        //横向错切
//        mTextPaint.setTextSkewX(-0.5f);
        //横向缩放
//        mTextPaint.setTextScaleX(1.5f);
        //字体间隔
        mTextPaint.setLetterSpacing(0.2f);
        //用 CSS 的 font-feature-settings 的方式来设置文字
        mTextPaint.setFontFeatureSettings("smcp");
        //对齐方式
        mTextPaint.setTextAlign(Paint.Align.LEFT);
        //语言本地化
        mTextPaint.setTextLocale(Locale.TAIWAN); // 简体中文

        CornerPathEffect cornerPathEffect = new CornerPathEffect(50);
        mPathPaint.setPathEffect(cornerPathEffect);

        /*
        *DiscretePathEffect 具体的做法是，把绘制改为使用定长的线段来拼接，并且在拼接的时候对路径进行随机偏离。
        * 它的构造方法 DiscretePathEffect(float segmentLength, float deviation) 的两个参数中，
        * segmentLength 是用来拼接的每个线段的长度， deviation 是偏离量。
        * 这两个值设置得不一样，显示效果也会不一样，具体的你自己多试几次就明白了，这里不再贴更多的图。
        * */

        DiscretePathEffect discretePathEffect = new DiscretePathEffect(30, 8);
//        mPathPaint.setPathEffect(discretePathEffect);

        /*它的构造方法 DashPathEffect(float[] intervals, float phase) 中，
        第一个参数 intervals 是一个数组，它指定了虚线的格式：数组中元素必须为偶数（最少是 2 个），
        按照「画线长度、空白长度、画线长度、空白长度」……的顺序排列，例如上面代码中的 20, 5, 10, 5 就表示虚线是按照
       「画 20 像素、空 5 像素、画 10 像素、空 5 像素」的模式来绘制；第二个参数 phase 是虚线的偏移量。*/

        PathEffect pathEffect = new DashPathEffect(new float[]{60, 10, 5, 10}, 0);
//        mPathPaint.setPathEffect(pathEffect);

        Path tempPath = new Path();
        tempPath.moveTo(20,0);
        tempPath.lineTo(40,20);
        tempPath.lineTo(0,20);
        tempPath.close();
        PathEffect pathDashPathEffect = new PathDashPathEffect(tempPath, 50, 0,
                PathDashPathEffect.Style.MORPH);

//        mPathPaint.setPathEffect(pathDashPathEffect);

//        drawPath();

    }

    private void drawPath() {
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
//        canvas.drawPath(mPath,mPathPaint);
//        drawLine(canvas);
//        drawtext(canvas);
//        staticDraw(canvas);

        measureAndDraw(canvas);
    }

    private void measureAndDraw(Canvas canvas) {
        String text = "Hello HenCoder \uD83C\uDDE8\uD83C\uDDF3";
        float offsetX = 120f;
        float offsetY = 300f;
        //删除线
        mTextPaint.setStrikeThruText(false);
        //下划线
        mTextPaint.setUnderlineText(false);

        mTextPaint.setStyle(Paint.Style.STROKE);

//        canvas.drawText(text,120,300,mTextPaint);

        Rect rect = new Rect();
        mTextPaint.getTextBounds(text,0,text.length(),rect);

        Log.d(TAG,"text height === " + (rect.top - rect.bottom));
        Log.d(TAG,"text width === " + (rect.right - rect.left));
        rect.left += offsetX;
        rect.right += offsetX;
        rect.top += offsetY;
        rect.bottom += offsetY;
//        canvas.drawRect(rect,mTextPaint);

        float textWidth = mTextPaint.measureText(text);
        Log.d(TAG,"textWidth === " + textWidth);

//        canvas.drawLine(offsetX,offsetY,offsetX + textWidth,offsetY,mTextPaint);

        int measuredCount;
        float[] measuredWidth = {0};

         // 宽度上限 300 （不够用，截断）
        measuredCount = mTextPaint.breakText(text, 0, text.length(), true, 500, measuredWidth);
//        canvas.drawText(text, 0, measuredCount, offsetX, offsetY, mTextPaint);
//        canvas.drawLine(offsetX,offsetY,offsetX + measuredWidth[0],offsetY,mTextPaint);

        int length = text.length();
        float advance = mTextPaint.getRunAdvance(text, 0, length, 0, length, false, length - 5);
        canvas.drawText(text, offsetX, offsetY, mTextPaint);
        canvas.drawLine(offsetX + advance, offsetY - (rect.bottom - rect.top) + 20, offsetX + advance, offsetY + 10, mTextPaint);

    }

    private void staticDraw(Canvas canvas) {

        String text1 = "fiisdkg nkgbkfb ggkbskdkfnbksdbnfksdbfklskdnfsdfbnksdbfksdb";
        String text2 = "fiisdkg /n \n 中文简体字\n dkfnbksd bnfksdb \n fklskdnfsd fbnksdbfksdb";

        /*
        * StaticLayout 的构造方法是 StaticLayout(CharSequence source, TextPaint paint, int width,
        * Layout.Alignment align, float spacingmult, float spacingadd, boolean includepad)，其中参数里：

          width 是文字区域的宽度，文字到达这个宽度后就会自动换行；
          align 是文字的对齐方向；
          spacingmult 是行间距的倍数，通常情况下填 1 就好；
          spacingadd 是行间距的额外增加值，通常情况下填 0 就好；
          includepad 是指是否在文字上下添加额外的空间，来避免某些过高的字符的绘制出现越界。
        * */
        StaticLayout staticLayout1 = new StaticLayout(text1, mTextPaint, 600,
                Layout.Alignment.ALIGN_NORMAL, 1, 0, true);
        StaticLayout staticLayout2 = new StaticLayout(text2, mTextPaint, 600,
                Layout.Alignment.ALIGN_NORMAL, 1, 0, true);

        canvas.save();
        canvas.translate(50, 100);
        staticLayout1.draw(canvas);
        canvas.translate(0, 500);
        staticLayout2.draw(canvas);
        canvas.restore();
    }

    private void drawtext(Canvas canvas) {

        Path path = new Path();
        path.moveTo(300,700);
        path.lineTo(500,100);
        path.rLineTo(100,400);
        path.rLineTo(200,-150);
        path.rLineTo(200,600);
        path.rLineTo(200,-100);
        canvas.drawPath(path,mPathPaint);

        /*参数里，需要解释的只有两个： hOffset 和 vOffset。它们是文字相对于 Path 的水平偏移量和竖直偏移量，利用它们可以调整文字的位置。
         例如你设置 hOffset 为 5， vOffset 为 10，文字就会右移 5 像素和下移 10 像素。
        * */
        canvas.drawTextOnPath("dfhksdhfkgfhkdlfghkdfnhgkfdnkgkfdsdhfkdshfd",path,0,-10,mPathPaint);

    }

    private void drawLine(Canvas canvas) {
        Path path = new Path();
//        path.moveTo(300,700);
//        path.lineTo(500,100);
//        path.rLineTo(100,400);
//        path.rLineTo(200,-150);
//        path.rLineTo(200,600);
//        path.rLineTo(200,-100);

        path.addRoundRect(400,600,1000,1200,100,100, Path.Direction.CW);
//        path.close();

        canvas.drawPath(path,mPathPaint);
    }
}
