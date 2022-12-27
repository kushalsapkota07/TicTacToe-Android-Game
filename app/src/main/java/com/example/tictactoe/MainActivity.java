package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Pair;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
private ImageView t1,i2,c3,t4,a5,c6,t7,o8,e9;
private Animation bottom_animation,middle_animation,top_animation,slide_animation;
private final int DURATION=3500;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION,WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        if (Build.VERSION.SDK_INT >= 19 && Build.VERSION.SDK_INT < 21) {
            setWindowFlag(this, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, true);
        }
        //make fully Android Transparent Status bar
        if (Build.VERSION.SDK_INT >= 21) {
            setWindowFlag(this, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, false);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = getWindow(); w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }
        setContentView(R.layout.activity_main);

        t1=findViewById(R.id.t1);
        i2=findViewById(R.id.i2);
        c3=findViewById(R.id.c3);
        t4=findViewById(R.id.t4);
        a5=findViewById(R.id.a5);
        c6=findViewById(R.id.c6);
        t7=findViewById(R.id.t7);
        o8=findViewById(R.id.o8);
        e9=findViewById(R.id.e9);

top_animation= AnimationUtils.loadAnimation(this,R.anim.top_animation);
middle_animation=AnimationUtils.loadAnimation(this,R.anim.middle_animation);
bottom_animation=AnimationUtils.loadAnimation(this,R.anim.bottom_animation);
slide_animation=AnimationUtils.loadAnimation(this,R.anim.slide_bottom);
t1.setAnimation(top_animation);
i2.setAnimation(top_animation);
c3.setAnimation(top_animation);
t4.setAnimation(middle_animation);
a5.setAnimation(middle_animation);
c6.setAnimation(middle_animation);
t7.setAnimation(bottom_animation);
o8.setAnimation(bottom_animation);
e9.setAnimation(bottom_animation);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.this,welcomeScreen.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_bottom,R.anim.slide_top);
                finish();
            }
        },DURATION);


    }

    private void setWindowFlag(MainActivity mainActivity, int flagTranslucentStatus, boolean b) {
    }

}