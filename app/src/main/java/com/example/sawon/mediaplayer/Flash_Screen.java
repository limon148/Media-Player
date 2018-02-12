package com.example.sawon.mediaplayer;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;

public class Flash_Screen extends AppCompatActivity{
    ImageView imageView;
    ConstraintLayout constraintLayout;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flash_screen);

        imageView = (ImageView)findViewById(R.id.image_f);

        imageView.setImageResource(R.drawable.music_icon);
        constraintLayout = (ConstraintLayout)findViewById(R.id.back_1);
        constraintLayout.setBackgroundResource(R.color.black_overlay);

        RotateAnimation rotateAnimation = new RotateAnimation(0,360,RotateAnimation.RELATIVE_TO_SELF,0.5f,RotateAnimation.RELATIVE_TO_SELF,0.5f);
        rotateAnimation.setFillAfter(true);
        rotateAnimation.setDuration(5000);
        rotateAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
        imageView.startAnimation(rotateAnimation);


        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                SystemClock.sleep(5000);
                Intent intent = new Intent(Flash_Screen.this,Options.class);
                startActivity(intent);
            }
        };

        Thread thread = new Thread(runnable);
        thread.start();


    }
}
