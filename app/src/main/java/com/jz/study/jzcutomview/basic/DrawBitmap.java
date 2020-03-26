package com.jz.study.jzcutomview.basic;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.ComposeShader;
import android.graphics.LightingColorFilter;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.PorterDuffXfermode;
import android.graphics.Shader;
import android.graphics.Xfermode;
import android.util.AttributeSet;
import android.view.View;
import com.jz.study.jzcutomview.R;
import androidx.annotation.Nullable;

/**
 * @Auther: JerryZhu
 * @datetime: 2020/3/26
 * https://hencoder.com/ui-1-2/
 */
public class DrawBitmap extends View {

    private Paint mPaint;
    private Bitmap mGirlBitmap;
    private Bitmap mIconBitmap;
    private Xfermode mXfermode;

    public DrawBitmap(Context context) {
        this(context,null);
    }

    public DrawBitmap(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public DrawBitmap(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

       setLayerType(View.LAYER_TYPE_SOFTWARE, null);


        mIconBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_launcher);
        mGirlBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.girl);

        mPaint = new Paint();
        mPaint.setColor(Color.CYAN);
        mPaint.setStrokeWidth(30);

        /*
        * 它的构造方法 BlurMaskFilter(float radius, BlurMaskFilter.Blur style) 中， radius 参数是模糊的范围， style 是模糊的类型。一共有四种：
          NORMAL: 内外都模糊绘制
          INNER: 内部模糊，外部不绘制
          SOLID: 内部正常绘制，外部模糊
          OUTER: 内部不绘制，外部模糊（什么鬼？）
        * */
        mPaint.setMaskFilter(new BlurMaskFilter(60,BlurMaskFilter.Blur.NORMAL));

        /*
        * 它的构造方法 EmbossMaskFilter(float[] direction, float ambient, float specular, float blurRadius) 的参数里，
        * direction 是一个 3 个元素的数组，指定了光源的方向；
        *  ambient 是环境光的强度，数值范围是 0 到 1； specular 是炫光的系数； blurRadius 是应用光线的范围。
        * */
//        mPaint.setMaskFilter(new EmbossMaskFilter(new float[]{0, 1, 1}, 0.2f, 8, 10));

       /* 参数：
        bitmap：用来做模板的 Bitmap 对象
        tileX：横向的 TileMode
        tileY：纵向的 TileMode*/
        Shader bitmapShader1 = new BitmapShader(mGirlBitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        Shader bitmapShader2 = new BitmapShader(mIconBitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);

      /*  PorterDuff.Mode 是用来指定两个图像共同绘制时的颜色策略的。它是一个 enum，不同的 Mode 可以指定不同的策略。「颜色策略」的意思，
        就是说把源图像绘制到目标图像处时应该怎样确定二者结合后的颜色，而对于 ComposeShader(shaderA, shaderB, mode) 这个具体的方法，
        就是指应该怎样把 shaderB 绘制在 shaderA 上来得到一个结合后的 Shader*/
        Shader composeShader = new ComposeShader(bitmapShader1, bitmapShader2, PorterDuff.Mode.SCREEN);
//        mPaint.setShader(composeShader);

        ColorFilter lightingColorFilter = new LightingColorFilter(0xffffff, 0x000030);
        PorterDuffColorFilter porterDuffColorFilter = new PorterDuffColorFilter(Color.RED,PorterDuff.Mode.SCREEN);
        mXfermode = new PorterDuffXfermode(PorterDuff.Mode.DST_OUT);
//        mPaint.setColorFilter(lightingColorFilter);

        mPaint.setDither(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        canvas.translate(300,300);
//        canvas.drawCircle(300,300,500,mPaint);

        int saved = canvas.saveLayer(null, null, Canvas.ALL_SAVE_FLAG);

         canvas.drawBitmap(mGirlBitmap,400,400,mPaint);
         mPaint.setXfermode(mXfermode);
         canvas.drawBitmap(mIconBitmap,500,500,mPaint);
         mPaint.setXfermode(null);

        canvas.restoreToCount(saved);
    }
}
