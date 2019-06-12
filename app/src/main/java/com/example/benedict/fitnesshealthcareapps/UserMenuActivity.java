package com.example.benedict.fitnesshealthcareapps;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Rect;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.TextView;

public class UserMenuActivity extends Activity implements OnTouchListener {
    AnimationClass AC;
    DataBaseHelper myDB;
    TextView txtName;
    Button btnRecords,btnBiceps,btnChest,btnShoulder,btnTriceps,btnAbs,btnLegs;
    String USERNAME;
    MediaPlayer GumPop;
    private Rect rect;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_menu);

        init();
    }

    @Override
    public void onBackPressed() {
        GumPop.start();
        startActivity(new Intent(UserMenuActivity.this, LoginActivity.class));
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }

    public void init(){
        AC = new AnimationClass();

        myDB = new DataBaseHelper(this);

        GumPop = MediaPlayer.create(this,R.raw.gum_bubble);

        txtName = (TextView)findViewById(R.id.txtName);

        btnRecords = (Button)findViewById(R.id.btnRecords);

        btnBiceps = (Button)findViewById(R.id.btnBiceps);
        btnChest = (Button)findViewById(R.id.btnChest);
        btnShoulder = (Button)findViewById(R.id.btnShoulder);
        btnTriceps = (Button)findViewById(R.id.btnTriceps);
        btnAbs = (Button)findViewById(R.id.btnAbs);
        btnLegs = (Button)findViewById(R.id.btnLegs);


        btnRecords.setOnTouchListener(this);
        btnBiceps.setOnTouchListener(this);
        btnChest.setOnTouchListener(this);
        btnShoulder.setOnTouchListener(this);
        btnTriceps.setOnTouchListener(this);
        btnAbs.setOnTouchListener(this);
        btnLegs.setOnTouchListener(this);

        GetIntentData();
    }

    public void GetIntentData(){
        txtName.setText(getIntent().getExtras().getString("NAME"));
        USERNAME = getIntent().getExtras().getString("USERNAME");
        Log.e("GetIntentData",txtName.getText().toString() +" "+USERNAME);
    }

    @Override
    public boolean onTouch(View view, MotionEvent event) {
        float x = event.getX();
        float y = event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                // Write your code to perform an action on down
                rect = new Rect(view.getLeft(), view.getTop(), view.getRight(), view.getBottom());
                return true;
            case MotionEvent.ACTION_MOVE:
                // Write your code to perform an action on continuous touch move
                return true;
            case MotionEvent.ACTION_UP:
                // Write your code to perform an action on touch up
                if (rect.contains(view.getLeft() + (int) x, view.getTop() + (int) y)) {
                    // User moved inside bounds
                    if (view.getId() == btnRecords.getId()) {
                        AC.TextColorAnimation(btnRecords, 250, 0, getResources().getColor(R.color.sky_blue), 3);
                        CallRecords();
                    }
                    else if(view.getId() == btnBiceps.getId()){
                        myDB.insertActivity(USERNAME,"Biceps");
                        CallVideoExcercise(1);
                    }
                    else if(view.getId() == btnChest.getId()){
                        myDB.insertActivity(USERNAME,"Chest");
                        CallVideoExcercise(2);
                    }
                    else if(view.getId() == btnShoulder.getId()){
                        myDB.insertActivity(USERNAME,"Shoulder");
                        CallVideoExcercise(3);
                    }
                    else if(view.getId() == btnTriceps.getId()){
                        myDB.insertActivity(USERNAME,"Triceps");
                        CallVideoExcercise(4);
                    }
                    else if(view.getId() == btnAbs.getId()){
                        myDB.insertActivity(USERNAME,"Abs");
                        CallVideoExcercise(5);
                    }
                    else if(view.getId() == btnLegs.getId()){
                        myDB.insertActivity(USERNAME,"Legs");
                        CallVideoExcercise(6);
                    }
                    return true;
                }
                return false;
        }
        return false;
    }

    private void CallVideoExcercise(int Choice) {
        GumPop.start();
        Intent i = new Intent(this, ExerciseActivity.class);
        i.putExtra("USERNAME", USERNAME);
        i.putExtra("NAME", txtName.getText().toString());
        if(Choice == 1){
            i.putExtra("EXECISE", String.valueOf(Choice));
            startActivity(i);
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        }
        else if(Choice == 2){
            i.putExtra("EXECISE", String.valueOf(Choice));
            startActivity(i);
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        }
        else if(Choice == 3){
            i.putExtra("EXECISE", String.valueOf(Choice));
            startActivity(i);
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        }
        else if(Choice == 4){
            i.putExtra("EXECISE", String.valueOf(Choice));
            startActivity(i);
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        }
        else if(Choice == 5){
            i.putExtra("EXECISE", String.valueOf(Choice));
            startActivity(i);
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        }
        else if(Choice == 6){
            i.putExtra("EXECISE", String.valueOf(Choice));
            startActivity(i);
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        }
    }

    private void CallRecords() {
        CountDownTimer tmrDelay = new CountDownTimer(2 * 1000, 1) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                CheckRecords();
            }
        }.start();
    }

    private void CheckRecords(){
        GumPop.start();
        Intent i = new Intent(this, RecordsActivity.class);
        i.putExtra("USERNAME", USERNAME);
        i.putExtra("NAME", txtName.getText().toString());
        startActivity(i);
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }




}
