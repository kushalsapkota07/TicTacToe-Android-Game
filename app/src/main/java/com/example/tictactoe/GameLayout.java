package com.example.tictactoe;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.progressindicator.LinearProgressIndicator;

import java.util.ArrayList;
import java.util.Random;

public class GameLayout extends AppCompatActivity {
    private int turn, player1, player2,xPoint,oPoint,drawPoints;
    private String[] nac;
    private String p1, p2, row1, row2, row3, col1, col2, col3, side1, side2, randomNac, p1NacData, p2NacData,xPts,oPts;
    private TextView r0c0, r0c1, r0c2, r1c0, r1c1, r1c2, r2c0, r2c1, r2c2, p1Turn, p2Turn,xCounter,oCounter,drawCounter,whoWon,goMenu,round;
    private Handler ticHandler = new Handler();
    private boolean check,xWon,oWon,dRaw;
    private MaterialButton goContinue;
    private AlertDialog.Builder popupBuilder;
    private AlertDialog dialog;
    private LinearProgressIndicator goContinueProgressBar;
    private int rounds=1;
    private int p1Pts,p2Pts;
// nac = noughts(O) and Crosses(X).

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_layout);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION, WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        if (Build.VERSION.SDK_INT >= 19 && Build.VERSION.SDK_INT < 21) {
            setWindowFlag(this, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, true);
        }
        //make fully Android Transparent Status bar
        if (Build.VERSION.SDK_INT >= 21) {
            setWindowFlag(this, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, false);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = getWindow();
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }
        setContentView(R.layout.activity_game_layout);

        r0c0 = findViewById(R.id.r0c0);
        r0c1 = findViewById(R.id.r0c1);
        r0c2 = findViewById(R.id.r0c2);
        r1c0 = findViewById(R.id.r1c0);
        r1c1 = findViewById(R.id.r1c1);
        r1c2 = findViewById(R.id.r1c2);
        r2c0 = findViewById(R.id.r2c0);
        r2c1 = findViewById(R.id.r2c1);
        r2c2 = findViewById(R.id.r2c2);
        p1Turn = findViewById(R.id.p1Turn);
        p2Turn = findViewById(R.id.p2Turn);
        xCounter=findViewById(R.id.xCounter);
        oCounter=findViewById(R.id.oCounter);
        drawCounter=findViewById(R.id.drawCounter);
        round= findViewById(R.id.round);



        p1NacData = p1Turn.getText().toString();
        p2NacData = p2Turn.getText().toString();

       ticHandler.post(nacs);
        if (turn == 0) {
            p1Turn.setTextColor(Color.parseColor("black"));
            p2Turn.setTextColor(Color.parseColor("grey"));
        } else if (turn == 1) {
            p2Turn.setTextColor(Color.parseColor("black"));
            p1Turn.setTextColor(Color.parseColor("grey"));
        }

        System.out.println(rounds);
    }

    private Runnable nacs = new Runnable() {
        @Override
        public void run() {
            nac = new String[]{"X", "O"};
            randomNac = new Random().nextBoolean() ? nac[0] : nac[1];
            Random random = new Random();
            int x = 10;
            check=true;
            while(check) {
                player1 = random.nextInt(x);
                player2 = random.nextInt(x);
                if (player1 > player2) {
                    turn = 0;
                    Toast.makeText(GameLayout.this, "Player 1 Goes With " + randomNac, Toast.LENGTH_SHORT).show();
                    if (randomNac.equals("X")) {
                        p1 = "X";
                        p2 = "O";
                        p1Turn.setText(p1NacData + p1);
                        p2Turn.setText(p2NacData + p2);

                    } else {
                        p1 = "O";
                        p2 = "X";
                        p1Turn.setText(p1NacData + p1);
                        p2Turn.setText(p2NacData + p2);

                    }
                    check=false;
                }
                if (player2 > player1) {
                    turn = 1;
                    Toast.makeText(GameLayout.this, "Player 2 Goes With " + randomNac, Toast.LENGTH_SHORT).show();
                    if (randomNac.equals("O")) {
                        p1 = "X";
                        p2 = "O";
                        p1Turn.setText(p1NacData + p1);
                        p2Turn.setText(p2NacData + p2);

                    } else {
                        p1 = "O";
                        p2 = "X";
                        p1Turn.setText(p1NacData + p1);
                        p2Turn.setText(p2NacData + p2);
                    }
                    check=false;
                }
                if (player1 == player2) {
                    player1 = random.nextInt(x);
                    player2 = random.nextInt(x);
                }
            }
        }
    };

    private void setWindowFlag(GameLayout gameLayout, int flagTranslucentStatus, boolean b) {
    }

    public void onButtonClicked(View v) {
        TextView textView = (TextView) v;
        switch (v.getId()) {
            case R.id.r0c0:
                if (turn == 0) {
                    r0c0.setText(p1);
                    r0c0.setClickable(false);
                    winPattern();
                    winCondition();
                    setColor();
                    turn = 1;
                    break;
                }
                if (turn == 1) {
                    r0c0.setText(p2);
                    r0c0.setClickable(false);
                    winPattern();
                    winCondition();
                    setColor();
                    turn = 0;
                    break;
                }
            case R.id.r0c1:
                if (turn == 0) {
                    r0c1.setText(p1);
                    r0c1.setClickable(false);
                    winPattern();
                    winCondition();
                    setColor();
                    turn = 1;
                    break;
                }
                if (turn == 1) {
                    r0c1.setText(p2);
                    r0c1.setClickable(false);
                    winPattern();
                    winCondition();
                    setColor();
                    turn = 0;
                    break;
                }
            case R.id.r0c2:
                if (turn == 0) {
                    r0c2.setText(p1);
                    r0c2.setClickable(false);
                    winPattern();
                    winCondition();
                    setColor();
                    turn = 1;
                    break;
                }
                if (turn == 1) {
                    r0c2.setText(p2);
                    r0c2.setClickable(false);
                    winPattern();
                    winCondition();
                    setColor();
                    turn = 0;
                    break;
                }
            case R.id.r1c0:
                if (turn == 0) {
                    r1c0.setText(p1);
                    r1c0.setClickable(false);
                    winPattern();
                    winCondition();
                    setColor();
                    turn = 1;
                    break;
                }
                if (turn == 1) {
                    r1c0.setText(p2);
                    r1c0.setClickable(false);
                    winPattern();
                    winCondition();
                    setColor();
                    turn = 0;
                    break;
                }
            case R.id.r1c1:
                if (turn == 0) {
                    r1c1.setText(p1);
                    r1c1.setClickable(false);
                    winPattern();
                    winCondition();
                    setColor();
                    turn = 1;
                    break;
                }
                if (turn == 1) {
                    r1c1.setText(p2);
                    r1c1.setClickable(false);
                    winPattern();
                    winCondition();
                    setColor();
                    turn = 0;
                    break;
                }
            case R.id.r1c2:
                if (turn == 0) {
                    r1c2.setText(p1);
                    r1c2.setClickable(false);
                    winPattern();
                    winCondition();
                    setColor();
                    turn = 1;
                    break;
                }
                if (turn == 1) {
                    r1c2.setText(p2);
                    r1c2.setClickable(false);
                    winPattern();
                    winCondition();
                    setColor();
                    turn = 0;
                    break;
                }
            case R.id.r2c0:
                if (turn == 0) {
                    r2c0.setText(p1);
                    r2c0.setClickable(false);
                    winPattern();
                    winCondition();
                    setColor();
                    turn = 1;
                    break;
                }
                if (turn == 1) {
                    r2c0.setText(p2);
                    r2c0.setClickable(false);
                    winPattern();
                    winCondition();
                    setColor();
                    turn = 0;
                    break;
                }
            case R.id.r2c1:
                if (turn == 0) {
                    r2c1.setText(p1);
                    r2c1.setClickable(false);
                    winPattern();
                    winCondition();
                    setColor();
                    turn = 1;
                    break;
                }
                if (turn == 1) {
                    r2c1.setText(p2);
                    r2c1.setClickable(false);
                    winPattern();
                    winCondition();
                    setColor();
                    turn = 0;
                    break;
                }
            case R.id.r2c2:
                if (turn == 0) {
                    r2c2.setText(p1);
                    r2c2.setClickable(false);
                    winPattern();
                    winCondition();
                    setColor();
                    turn = 1;
                    break;
                }
                if (turn == 1) {
                    r2c2.setText(p2);
                    r2c2.setClickable(false);
                    winPattern();
                    winCondition();
                    setColor();
                    turn = 0;
                    break;
                }
        }

    }

    public void setColor() {
        if (turn == 0) {
            p1Turn.setTextColor(Color.parseColor("grey"));
            p2Turn.setTextColor(Color.parseColor("black"));
        } else if (turn == 1) {
            p2Turn.setTextColor(Color.parseColor("grey"));
            p1Turn.setTextColor(Color.parseColor("black"));
        }
    }

    public void winPattern() {
        row1 = r0c0.getText().toString() + r0c1.getText().toString() + r0c2.getText().toString();
        row2 = r1c0.getText().toString() + r1c1.getText().toString() + r1c2.getText().toString();
        row3 = r2c0.getText().toString() + r2c1.getText().toString() + r2c2.getText().toString();
        //Columns
        col1 = r0c0.getText().toString() + r1c0.getText().toString() + r2c0.getText().toString();
        col2 = r0c1.getText().toString() + r1c1.getText().toString() + r2c1.getText().toString();
        col3 = r0c2.getText().toString() + r1c2.getText().toString() + r2c2.getText().toString();
        //sides
        side1 = r0c0.getText().toString() + r1c1.getText().toString() + r2c2.getText().toString();
        side2 = r0c2.getText().toString() + r1c1.getText().toString() + r2c0.getText().toString();


    }
    //insert winPattern() in winCondition
    public void afterDraw(){
        r0c0.setText("");
        r0c0.setClickable(true);
        r0c1.setText("");
        r0c1.setClickable(true);
        r0c2.setText("");
        r0c2.setClickable(true);
        r1c0.setText("");
        r1c0.setClickable(true);
        r1c1.setText("");
        r1c1.setClickable(true);
        r1c2.setText("");
        r1c2.setClickable(true);
        r2c0.setText("");
        r2c0.setClickable(true);
        r2c1.setText("");
        r2c1.setClickable(true);
        r2c2.setText("");
        r2c2.setClickable(true);
    }

    public void switchoffTouch() {
        r0c0.setClickable(false);
        r0c1.setClickable(false);
        r0c2.setClickable(false);
        r1c0.setClickable(false);
        r1c1.setClickable(false);
        r1c2.setClickable(false);
        r2c0.setClickable(false);
        r2c1.setClickable(false);
        r2c2.setClickable(false);
    }
//    public void switchOnTouch() {
//        r0c0.setClickable(true);
//        r0c1.setClickable(true);
//        r0c2.setClickable(true);
//        r1c0.setClickable(true);
//        r1c1.setClickable(true);
//        r1c2.setClickable(true);
//        r2c0.setClickable(true);
//        r2c1.setClickable(true);
//        r2c2.setClickable(true);
//    }

    public void winCondition() {

        ArrayList<String> combos = new ArrayList<>(8);
        combos.add(row1);
        combos.add(row2);
        combos.add(row3);
        combos.add(col1);
        combos.add(col2);
        combos.add(col3);
        combos.add(side1);
        combos.add(side2);
boolean x = false;
boolean o = false;
boolean draw = false;
        int drawLength = (row1+row2+row3).length();
        for (String combo:combos){
            if(combo.equals("XXX")){
                 x=true;
                 break;
            }
            if(combo.equals("OOO")){
                 o=true;
                 break;
            }
            if((drawLength==9)&&((!combo.equals("XXX")||(!combo.equals("OOO"))))){
                 draw=true;
                 break;
            }
        }
if(x){
    xWon=true;
    switchoffTouch();
    xPoint+=1;
    xPts= String.valueOf(xPoint);
//    xCounter.setText(xPts);
//    Toast.makeText(this, "Game Finished, X Won", Toast.LENGTH_SHORT).show();
    ticHandler.postDelayed(ticable,200);
    rounds++;
}

//        if (row1.equals("XXX") || row2.equals("XXX") || row3.equals("XXX") || col3.equals("XXX") || col2.equals("XXX") || col1.equals("XXX") || side1.equals("XXX") || side2.equals("XXX")) {
//            switchoffTouch();
//            xPoint+=1;
//            String xPts= String.valueOf(xPoint);
//            xCounter.setText(xPts);
//            Toast.makeText(this, "Game Finished, X Won", Toast.LENGTH_SHORT).show();
//        }
//        if (row1.equals("OOO") || row2.equals("OOO") || row3.equals("OOO") || col3.equals("OOO") || col2.equals("OOO") || col1.equals("OOO") || side1.equals("OOO") || side2.equals("OOO")) {
//            switchoffTouch();
//            oPoint+=1;
//            String oPts= String.valueOf(oPoint);
//            oCounter.setText(oPts);
//            Toast.makeText(this, "Game Finished, O Won", Toast.LENGTH_SHORT).show();
//
//        }
if(o){
    oWon=true;
    switchoffTouch();
    oPoint+=1;
    oPts= String.valueOf(oPoint);
//    oCounter.setText(oPts);
//    Toast.makeText(this, "Game Finished, O Won", Toast.LENGTH_SHORT).show();
    ticHandler.postDelayed(ticable,200);
    rounds++;
}
//        if ((r0c0.getText().toString().length() + r0c1.getText().toString().length() + r0c2.getText().toString().length() + r1c0.getText().toString().length() + r1c1.getText().toString().length() + r1c2.getText().toString().length() + r2c0.getText().toString().length() + r2c1.getText().toString().length() + r2c2.getText().toString().length()) == 9) {
//            if (!(row1.equals("OOO") || row2.equals("OOO") || row3.equals("OOO") || col3.equals("OOO") || col2.equals("OOO") || col1.equals("OOO") || side1.equals("OOO") || side2.equals("OOO")||row1.equals("XXX") || row2.equals("XXX") || row3.equals("XXX") || col3.equals("XXX") || col2.equals("XXX") || col1.equals("XXX") || side1.equals("XXX") || side2.equals("XXX"))) {
//                r0c0.setText("");
//                r0c0.setClickable(true);
//                r0c1.setText("");
//                r0c1.setClickable(true);
//                r0c2.setText("");
//                r0c2.setClickable(true);
//                r1c0.setText("");
//                r1c0.setClickable(true);
//                r1c1.setText("");
//                r1c1.setClickable(true);
//                r1c2.setText("");
//                r1c2.setClickable(true);
//                r2c0.setText("");
//                r2c0.setClickable(true);
//                r2c1.setText("");
//                r2c1.setClickable(true);
//                r2c2.setText("");
//                r2c2.setClickable(true);
//                drawPoints+=1;
//                String drawPts = String.valueOf(drawPoints);
//                drawCounter.setText(drawPts);
//                Toast.makeText(this, "Game Finished,Draw", Toast.LENGTH_SHORT).show();
//
//            }
//
//        }

if(draw){
    dRaw=true;
    drawPoints+=1;
    String drawPts = String.valueOf(drawPoints);
    drawCounter.setText(drawPts);
//    Toast.makeText(this, "Game Finished,Draw", Toast.LENGTH_SHORT).show();
    ticHandler.postDelayed(ticable,200);
    rounds++;
}
        if(x &&p1.equals("X")){
            p1Pts++;
            xCounter.setText(String.valueOf(p1Pts));
        }
        if(x &&p2.equals("X")){
            p2Pts++;
            oCounter.setText(String.valueOf(p2Pts));
        }

        if(o &&p1.equals("O")){
            p1Pts++;
            xCounter.setText(String.valueOf(p1Pts));
        }
        if(o &&p2.equals("O")){
            p2Pts++;
            oCounter.setText(String.valueOf(p2Pts));
        }
    }

//    private Runnable runnable = new Runnable() {
//        @Override
//        public void run() {
//            if (player1 > player2) {
//                turn = 0;
//                Toast.makeText(GameLayout.this, "Player 1 Goes With " + randomNac, Toast.LENGTH_SHORT).show();
//                if (randomNac.equals("X")) {
//                    p1 = "X";
//                    p2 = "O";
//                    p1Turn.setText(p1NacData + p1);
//                    p2Turn.setText(p2NacData + p2);
//
//                } else {
//                    p1 = "O";
//                    p2 = "X";
//                    p1Turn.setText(p1NacData + p1);
//                    p2Turn.setText(p2NacData + p2);
//
//                }
//                check=false;
//            }
//        }
//    };
    private Runnable ticable = new Runnable() {
    @Override
    public void run() {
        createNewPopup();
    }
};
    public void createNewPopup(){
        popupBuilder = new AlertDialog.Builder(this);
        final View popupView = getLayoutInflater().inflate(R.layout.popup,null);

        whoWon = popupView.findViewById(R.id.whoWon);
        goMenu = popupView.findViewById(R.id.goMenu);
        goContinue = popupView.findViewById(R.id.goContinue);
        goContinueProgressBar = popupView.findViewById(R.id.goContinueProgressBar);

        goContinue.setText("Round-"+rounds);
        goContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                afterDraw();
                round.setText("Round - "+rounds);
                dialog.hide();

            }
        });

        goMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Handler().post(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(GameLayout.this,welcomeScreen.class);
                        startActivity(intent);
                        overridePendingTransition(R.anim.slide_top,R.anim.slide_bottom);
                        finish();
                    }
                });
                dialog.hide();
            }
        });
        if(xWon){
            whoWon.setText("X - won");
            xWon=false;
        }
        if(oWon){
            whoWon.setText("O - won");
            oWon=false;
        }
        if(dRaw){
            whoWon.setText("Draw");
            dRaw=false;
        }
        if(rounds==6){
            if(xPoint>oPoint){
                whoWon.setTextSize(52);
                whoWon.setText("Player 1 Won");
            }
            if(oPoint>xPoint){
                whoWon.setTextSize(52);
                whoWon.setText("Player 2 Won");
            }
            if(xPoint==oPoint){
                whoWon.setTextSize(52);
                whoWon.setText("Game Drawed");
            }
           goContinue.setText(" Restart ");
            goContinue.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    p1Pts=0;
                    xCounter.setText(String.valueOf(p1Pts));
                    p2Pts=0;
                    oCounter.setText(String.valueOf(p2Pts));
                    drawPoints=0;
                    drawCounter.setText(String.valueOf(drawPoints));
                    rounds=1;
                    round.setText("Round - "+String.valueOf(rounds));
                    afterDraw();
                    dialog.hide();
                    ticHandler.post(nacs);
                }
            });
        }
       popupBuilder.setView(popupView);
       dialog=popupBuilder.create();
        dialog.show();
        dialog.setCancelable(false);
        new Thread(new Runnable() {
            @Override
            public void run() {
                 int progressStatus=0;
                while(progressStatus<125) {
                    progressStatus++;
                    int finalProgressStatus = progressStatus;
                    ticHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            goContinueProgressBar.setProgress(finalProgressStatus);

                        }
                    });
                    try{
                        Thread.sleep(20 );
                    }catch (Exception e){}
                }

            }

        }).start();
       Window window = dialog.getWindow();
       window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    }

}
