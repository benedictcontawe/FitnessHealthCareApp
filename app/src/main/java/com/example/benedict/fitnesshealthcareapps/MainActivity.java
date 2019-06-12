package com.example.benedict.fitnesshealthcareapps;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Rect;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.CountDownTimer;
import android.os.Bundle;
import android.os.Process;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.Button;

public class MainActivity extends Activity implements OnTouchListener {
    AnimationClass AC;
    Button btnStart,btnAbout,btnCredits;
    MediaPlayer GumPop;
    private Rect rect;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    public void init(){
        AC = new AnimationClass();
        GumPop = MediaPlayer.create(this,R.raw.gum_bubble);

        btnStart = (Button)findViewById(R.id.btnStart);
        btnAbout = (Button)findViewById(R.id.btnAbout);
        btnCredits = (Button)findViewById(R.id.btnCredits);

        btnStart.setOnTouchListener(this);
        btnAbout.setOnTouchListener(this);
        btnCredits.setOnTouchListener(this);
    }

    @Override
    public boolean onTouch(View view, MotionEvent event)  {
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
                if(rect.contains(view.getLeft() + (int) x, view.getTop() + (int) y)){
                    // User moved inside bounds
                        if (view.getId() == btnStart.getId()) {
                            AC.TextColorAnimation(btnStart, 250, 0, getResources().getColor(R.color.sky_blue), 3);
                            CallActivity(1);
                        } else if (view.getId() == btnAbout.getId()) {
                            AC.TextColorAnimation(btnAbout, 250, 0, getResources().getColor(R.color.sky_blue), 3);
                            CallActivity(2);
                        } else if (view.getId() == btnCredits.getId()) {
                            AC.TextColorAnimation(btnCredits, 250, 0, getResources().getColor(R.color.sky_blue), 3);
                            CallActivity(3);
                        }
                }
                return true;
        }
        return false;
    }

    public void CallActivity(final int Choice){
        CountDownTimer tmrCallActivity = new CountDownTimer(2 * 1000, 1) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                GumPop.start();
                Log.e("Checking","CHECKING.");
                ActivityIntent(Choice);
            }
        }.start();
    }

    public void ActivityIntent(int choice){
        if(choice == 1){
            Log.e("Checking","CHECKING. .");
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        }
        else if(choice == 2){
            startActivity(new Intent(MainActivity.this, AboutActivity.class));
        }
        else if(choice == 3){
            startActivity(new Intent(MainActivity.this, CreditsActivity.class));
        }
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
        dialog.setCancelable(false);
        dialog.setTitle("Leaving already?");
        dialog.setMessage("Are you sure you want to exit the game");
        dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                //Action for "OK".
                EXIT();
            }
        })
                .setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //Action for "Cancel".
                    }
                });

        final AlertDialog alert = dialog.create();
        alert.show();
    }

    @Override
    protected void onDestroy() {
        //android.os.Process; //This shouldn't be java.Process
        Process.killProcess(Process.myPid());
        super.onDestroy();
    }

    private void EXIT() {
        finish();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            finishAffinity();
        }
    }
}
