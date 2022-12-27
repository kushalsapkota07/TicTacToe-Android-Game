package com.example.tictactoe;

import static android.os.Build.VERSION_CODES.S;

import static java.util.TimeZone.SHORT;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.card.MaterialCardView;

public class welcomeScreen extends AppCompatActivity {
private MaterialCardView pvp,pvc;
private final int DURATION=200;
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
        setContentView(R.layout.activity_welcome_screen);

        pvp=findViewById(R.id.cpvp);
    }
    public void modeClicked(View v) {
        MaterialCardView button = (MaterialCardView) v;

        switch (v.getId()) {
            case R.id.cpvp:
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(welcomeScreen.this, GameLayout.class);
                        startActivity(intent);
                        overridePendingTransition(R.anim.slide_bottom, R.anim.slide_top);

                    }
                }, DURATION);
                break;
            case R.id.cpvc:
                Toast.makeText(this,"Please Click Player vs Player Mode",Toast.LENGTH_SHORT).show();
                break;
        }
    }
    private void setWindowFlag(welcomeScreen welcomeScreen, int flagTranslucentStatus, boolean b) {
    }
}