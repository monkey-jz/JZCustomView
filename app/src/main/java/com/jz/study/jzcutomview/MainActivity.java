package com.jz.study.jzcutomview;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.jz.study.jzcutomview.anim.PropertyAnimView;
import com.jz.study.jzcutomview.basic.CustomImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        PropertyAnimView propertyAnimView = (PropertyAnimView)findViewById(R.id.property_anim_view);
//        ObjectAnimator process = ObjectAnimator.ofFloat(propertyAnimView, "process", 1, 360);
//        process.setDuration(2000);
//        process.start();

        AnimatorSet animatorSet = new AnimatorSet();
        CustomImageView imageView = (CustomImageView) findViewById(R.id.CustomImageView);
        ObjectAnimator rotatY = ObjectAnimator.ofFloat(imageView, "degreeY", 0, -30);
        rotatY.setDuration(1000);
        rotatY.setStartDelay(500);

        ObjectAnimator rotatZ = ObjectAnimator.ofFloat(imageView, "degreeZ", 0, 270);
        rotatZ.setDuration(1000);
        rotatZ.setStartDelay(500);

        animatorSet.playSequentially(rotatY,rotatZ);

        animatorSet.start();
    }
}
