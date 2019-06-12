package com.example.benedict.fitnesshealthcareapps;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Rect;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends Activity implements OnTouchListener {
    AnimationClass AC;
    DataBaseHelper myDB;
    EditText editTextUsername,editTextPassword,editTextName;
    Button btnOk;
    MediaPlayer GumPop;
    InputMethodManager imm;
    private Rect rect;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        init();
    }

    public void init(){
        AC = new AnimationClass();
        myDB = new DataBaseHelper(this);
        GumPop = MediaPlayer.create(this,R.raw.gum_bubble);

        editTextUsername = (EditText) findViewById(R.id.editTextUsername);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        editTextName = (EditText) findViewById(R.id.editTextName);

        btnOk = (Button) findViewById(R.id.btnOk);

        btnOk.setOnTouchListener(this);
        editTextUsername.setOnTouchListener(this);
        editTextPassword.setOnTouchListener(this);
        editTextName.setOnTouchListener(this);

        imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
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
                    if (view.getId() == btnOk.getId()) {
                        AC.TextColorAnimation(btnOk, 250, 0, getResources().getColor(R.color.sky_blue), 3);
                        Register();
                    }
                    else if(view.getId() == editTextUsername.getId()){
                        checkEditText(editTextUsername);
                    }
                    else if(view.getId() == editTextPassword.getId()){
                        checkEditText(editTextPassword);
                    }
                    else if(view.getId() == editTextName.getId()){
                        checkEditText(editTextName);
                    }
                    return true;
                }
                return false;
        }
        return false;
    }

    public void checkEditText(EditText ET){
        ET.setText("");
        ET.requestFocus();
        imm.showSoftInput(ET, InputMethodManager.SHOW_IMPLICIT);
    }

    public void Register(){
        CountDownTimer tmrCallActivity = new CountDownTimer(2 * 1000, 1) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                AlertDialog.Builder dialog = new AlertDialog.Builder(RegisterActivity.this);
                dialog.setCancelable(false);
                dialog.setTitle("Done?");
                dialog.setMessage("Please check if the information about you is correct");
                dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        //Action for "OK".
                        GumPop.start();
                        InsertDatabase();
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
        }.start();
    }

    public void InsertDatabase(){
        try {
            myDB.insertUser(editTextUsername.getText().toString(), editTextPassword.getText().toString(), editTextName.getText().toString());
            Toast.makeText(this, "Register Complete", Toast.LENGTH_SHORT).show();
            onBackPressed();
        }
        catch(Exception ex){
            Toast.makeText(this, "Username has already used", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onBackPressed() {
        GumPop.start();
        startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }
}
