package com.example.tictactoe;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.progressindicator.LinearProgressIndicator;

import java.util.ArrayList;
import java.util.Random;

public class GameLayout extends AppCompatActivity {
    protected int turn, player1, player2,xPoint,oPoint,drawPoints;
    protected String[] nac;
    protected String p1, p2, row1, row2, row3, col1, col2, col3, side1, side2, randomNac, p1NacData, p2NacData,xPts,oPts;
    protected TextView r0c0, r0c1, r0c2, r1c0, r1c1, r1c2, r2c0, r2c1, r2c2, p1Turn, p2Turn,xCounter,oCounter,drawCounter,whoWon,goMenu,round;
    protected Handler ticHandler = new Handler();
    protected boolean check,xWon,oWon,dRaw,anyoneWon;
    protected MaterialButton goContinue;
    protected AlertDialog.Builder popupBuilder;
    protected AlertDialog dialog;
    protected LinearProgressIndicator goContinueProgressBar,row2Line;
    protected ProgressBar rowLine,col2Line, sideLine;
    protected int rounds=1;
    protected int p1Pts,p2Pts;



    private RelativeLayout.LayoutParams params1 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,RelativeLayout.LayoutParams.WRAP_CONTENT);
    private RelativeLayout.LayoutParams params2 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,RelativeLayout.LayoutParams.WRAP_CONTENT);
    private RelativeLayout.LayoutParams params3 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,RelativeLayout.LayoutParams.WRAP_CONTENT);
    private RelativeLayout.LayoutParams params4 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,RelativeLayout.LayoutParams.WRAP_CONTENT);

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
        row2Line = findViewById(R.id.row2Line);
        rowLine = findViewById(R.id.rowLine);
        col2Line=findViewById(R.id.col2Line);
        sideLine=findViewById(R.id.sideLine);



        p1NacData = p1Turn.getText().toString();
        p2NacData = p2Turn.getText().toString();

        xCounter.setText(String.valueOf(p1Pts));
        oCounter.setText(String.valueOf(p2Pts));
        drawCounter.setText(String.valueOf(drawPoints));


       ticHandler.post(nacs);


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
                    p1Turn.setTextColor(Color.parseColor("black"));
                    p2Turn.setTextColor(Color.parseColor("grey"));
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
                    p1Turn.setTextColor(Color.parseColor("grey"));
                    p2Turn.setTextColor(Color.parseColor("black"));
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
                    if(anyoneWon){
                        switchoffTouch();
                    }
                    setColor();
                    turn = 1;
                    break;
                }
                if (turn == 1) {
                    r0c0.setText(p2);
                    r0c0.setClickable(false);
                    winPattern();
                    winCondition();
                    if(anyoneWon){
                        switchoffTouch();
                    }
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
                    if(anyoneWon){
                        switchoffTouch();
                    }
                    setColor();
                    turn = 1;
                    break;
                }
                if (turn == 1) {
                    r0c1.setText(p2);
                    r0c1.setClickable(false);
                    winPattern();
                    winCondition();
                    if(anyoneWon){
                        switchoffTouch();
                    }
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
                    if(anyoneWon){
                        switchoffTouch();
                    }
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
                    if(anyoneWon){
                        switchoffTouch();
                    }
                    setColor();
                    turn = 1;
                    break;
                }
                if (turn == 1) {
                    r1c0.setText(p2);
                    r1c0.setClickable(false);
                    winPattern();
                    winCondition();
                    if(anyoneWon){
                        switchoffTouch();
                    }
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
                    if(anyoneWon){
                        switchoffTouch();
                    }
                    setColor();
                    turn = 1;
                    break;
                }
                if (turn == 1) {
                    r1c1.setText(p2);
                    r1c1.setClickable(false);
                    winPattern();
                    winCondition();
                    if(anyoneWon){
                        switchoffTouch();
                    }
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
                    if(anyoneWon){
                        switchoffTouch();
                    }
                    setColor();
                    turn = 1;
                    break;
                }
                if (turn == 1) {
                    r1c2.setText(p2);
                    r1c2.setClickable(false);
                    winPattern();
                    winCondition();
                    if(anyoneWon){
                        switchoffTouch();
                    }
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
                    if(anyoneWon){
                        switchoffTouch();
                    }
                    setColor();
                    turn = 1;
                    break;
                }
                if (turn == 1) {
                    r2c0.setText(p2);
                    r2c0.setClickable(false);
                    winPattern();
                    winCondition();
                    if(anyoneWon){
                        switchoffTouch();
                    }
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
                    if(anyoneWon){
                        switchoffTouch();
                    }
                    setColor();
                    turn = 1;
                    break;
                }
                if (turn == 1) {
                    r2c1.setText(p2);
                    r2c1.setClickable(false);
                    winPattern();
                    winCondition();
                    if(anyoneWon){
                        switchoffTouch();
                    }
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
                    if(anyoneWon){
                        switchoffTouch();
                    }
                    setColor();
                    turn = 1;
                    break;
                }
                if (turn == 1) {
                    r2c2.setText(p2);
                    r2c2.setClickable(false);
                    winPattern();
                    winCondition();
                    if(anyoneWon){
                        switchoffTouch();
                    }
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

        int drawLength;
        drawLength = r0c0.length()+r0c1.length()+r0c2.length()+r1c0.length()+r1c1.length()+r1c2.length()+r2c0.length()+r2c1.length()+r2c2.length();
        for (String combo:combos){
            if(combo.equals("XXX")){
                 x=true;
                 break;
            }

//            if((side1.equals("XXX")||(side1.equals("OOO")))) {
//                if(drawLength==9) {
//                    draw = true;
//                    break;
//                }
//            }
//            if((!combo.equals("XXX")||(!combo.equals("OOO")))) {
//                if(drawLength==9) {
//                    draw = true;
//                    break;
//                }
//            }
            if(combo.equals("OOO")){
                 o=true;
                 break;
            }

        }
        if((!combos.contains("XXX")&&!combos.contains("OOO"))&&drawLength==9){
            draw=true;
        }
        if(row1.equals("XXX")||row1.equals("OOO")){

            rowWinLine(10,-35,90,200,0,320);
        }
        if(row2.equals("XXX")||row2.equals("OOO")){
            rowWinLine(10,70,90,200,0,320);
        }
        if(row3.equals("XXX")||row3.equals("OOO")){

            rowWinLine(10,180,90,200,0,320);
        }
        if(col2.equals("XXX")||col2.equals("OOO")){
            colWinLine(10,80,180,200,0,320);
        }
        if(col1.equals("XXX")||col1.equals("OOO")){
            colWinLine(10,80,180,85,0,320);
        }
        if(col3.equals("XXX")||col3.equals("OOO")){
            colWinLine(10,80,180,318,0,320);
        }
        if(side1.equals("XXX")||side1.equals("OOO")){
            sideWinLine(10,28,135,200,0,400);
        }
        if(side2.equals("XXX")||side2.equals("OOO")){
            sideWinLine(10,35,45,200,0,400);
        }

if(x){
    xWon=true;
    
    xPoint+=1;
    xPts= String.valueOf(xPoint);
//    xCounter.setText(xPts);
//    Toast.makeText(this, "Game Finished, X Won", Toast.LENGTH_SHORT).show();
    ticHandler.postDelayed(ticable,400);
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

    oPoint+=1;
    oPts= String.valueOf(oPoint);
//    oCounter.setText(oPts);
//    Toast.makeText(this, "Game Finished, O Won", Toast.LENGTH_SHORT).show();
    ticHandler.postDelayed(ticable,400);
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
    ticHandler.postDelayed(ticable,400);
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

        if(x||o||draw){
            anyoneWon=true;
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
                afterDraw();
                anyoneWon=false;

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
//                        overridePendingTransition(R.anim.slide_top,R.anim.slide_bottom);
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
            if(p1Pts>p2Pts){
                whoWon.setTextSize(52);
                whoWon.setText("Player 1 Won");
            }
            if(p2Pts>p1Pts){
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
                    anyoneWon=false;
                    rowLine.setVisibility(View.INVISIBLE);
                    row2Line.setVisibility(View.INVISIBLE);
                    col2Line.setVisibility(View.GONE);
                    sideLine.setVisibility(View.GONE);
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
    public void rowWinLine(int width, int topMargin,int rotateDegree,int leftMargin,int rightMargin,int height){
        col2Line.setVisibility(View.VISIBLE);
        final float scale = getApplicationContext().getResources().getDisplayMetrics().density;
        int topMar =  (int)(topMargin * scale + 0.5f);
        int leftMar =  (int)(leftMargin * scale + 0.5f);
        int rightMar =  (int)(rightMargin * scale + 0.5f);
        int ht =  (int)(height * scale + 0.5f);

        params4.width = (int)(width * scale + 0.5f);
        params4.height=ht;
        params4.setMargins(leftMar, topMar,rightMar,0);

        col2Line.setLayoutParams(params4);
        col2Line.setRotation(rotateDegree);

        new Thread(new Runnable() {
            @Override
            public void run() {
                int progressStatus=0;
                while(progressStatus<160) {
                    progressStatus++;
                    int finalProgressStatus = progressStatus;
                    ticHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            col2Line.setProgress(finalProgressStatus);


                        }
                    });
                    try{
                        Thread.sleep(5);
                    }catch (Exception e){}
                }

            }

        }).start();
    }
    public void colWinLine(int width, int topMargin,int rotateDegree,int leftMargin,int rightMargin,int height){
        col2Line.setVisibility(View.VISIBLE);
        final float scale = getApplicationContext().getResources().getDisplayMetrics().density;
        int topMar =  (int)(topMargin * scale + 0.5f);
        int leftMar =  (int)(leftMargin * scale + 0.5f);
        int rightMar =  (int)(rightMargin * scale + 0.5f);
        int ht =  (int)(height * scale + 0.5f);

        params4.width = (int)(width * scale + 0.5f);
        params4.height=ht;
        params4.setMargins(leftMar, topMar,rightMar,0);

        col2Line.setLayoutParams(params4);
        col2Line.setRotation(rotateDegree);

        new Thread(new Runnable() {
            @Override
            public void run() {
                int progressStatus=0;
                while(progressStatus<160) {
                    progressStatus++;
                    int finalProgressStatus = progressStatus;
                    ticHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            col2Line.setProgress(finalProgressStatus);


                        }
                    });
                    try{
                        Thread.sleep(5);
                    }catch (Exception e){}
                }

            }

        }).start();
    }
    public void sideWinLine(int width, int topMargin,int rotateDegree,int leftMargin,int rightMargin,int height){
        sideLine.setVisibility(View.VISIBLE);
        final float scale = getApplicationContext().getResources().getDisplayMetrics().density;
        int topMar =  (int)(topMargin * scale + 0.5f);
        int leftMar =  (int)(leftMargin * scale + 0.5f);
        int rightMar =  (int)(rightMargin * scale + 0.5f);
        int ht =  (int)(height * scale + 0.5f);

        params3.width = (int)(width * scale + 0.5f);
        params3.height=ht;
        params3.setMargins(leftMar, topMar,rightMar,0);

        sideLine.setLayoutParams(params3);
        sideLine.setRotation(rotateDegree);

        new Thread(new Runnable() {
            @Override
            public void run() {
                int progressStatus=0;
                while(progressStatus<200) {
                    progressStatus++;
                    int finalProgressStatus = progressStatus;
                    ticHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            sideLine.setProgress(finalProgressStatus);


                        }
                    });
                    try{
                        Thread.sleep(5);
                    }catch (Exception e){}
                }

            }

        }).start();
    }


}
