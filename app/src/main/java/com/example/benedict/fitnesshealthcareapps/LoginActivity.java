package com.example.benedict.fitnesshealthcareapps;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.view.MotionEvent;
import android.view.View.OnTouchListener;
import android.os.Bundle;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends Activity implements OnTouchListener{
    AnimationClass AC;
    EditText editTextUsername,editTextPassword;
    Button btnOk,btnRegister;
    MediaPlayer GumPop;
    DataBaseHelper myDB;
    InputMethodManager imm;
    String PasswordEditText,PasswordDatabase;
    private Rect rect;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        init();
    }

    public void init(){
        AC = new AnimationClass();
        myDB = new DataBaseHelper(this);
        GumPop = MediaPlayer.create(this,R.raw.gum_bubble);

        editTextUsername = (EditText) findViewById(R.id.editTextUsername);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);

        btnOk = (Button) findViewById(R.id.btnOk);
        btnRegister = (Button) findViewById(R.id.btnRegister);

        btnOk.setOnTouchListener(this);
        btnRegister.setOnTouchListener(this);

        editTextUsername.setOnTouchListener(this);
        editTextPassword.setOnTouchListener(this);

        imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
    }

    @Override
    public void onBackPressed() {
        GumPop.start();
        startActivity(new Intent(LoginActivity.this, MainActivity.class));
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
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
                        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                        AC.TextColorAnimation(btnOk, 250, 0, getResources().getColor(R.color.sky_blue), 3);
                        login();
                    }
                    else if(view.getId() == btnRegister.getId()) {
                        AC.TextColorAnimation(btnRegister, 250, 0, getResources().getColor(R.color.sky_blue), 3);
                        register();
                    }
                    else if(view.getId() == editTextUsername.getId()){
                        checkEditText(editTextUsername);
                    }
                    else if(view.getId() == editTextPassword.getId()){
                        checkEditText(editTextPassword);
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

    public void login(){
        CountDownTimer tmrDelay = new CountDownTimer(2 * 1000, 1) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                CheckLogin();
            }
        }.start();
    }

    public void register(){
        CountDownTimer tmrDelay = new CountDownTimer(2 * 1000, 1) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                GumPop.start();
                CallRegisterActivity();
            }
        }.start();
    }

    public void CheckLogin(){
        PasswordEditText = editTextPassword.getText().toString();
        PasswordDatabase = myDB.RetriveUserInfo(editTextUsername.getText().toString(),"PASSWORD");
        Log.e("EQUAL PASSWORD CHECKING",PasswordEditText + " " + PasswordDatabase);
        if (PasswordEditText.equals(PasswordDatabase)){
            CallMainMenu();
        }
        else{
            Toast.makeText(this, "Username or Password incorrect", Toast.LENGTH_LONG).show();
        }
    }

    public void CallMainMenu(){
        GumPop.start();
        Intent i = new Intent(this, UserMenuActivity.class);
        Log.e("CallMainMenu",editTextUsername.getText().toString()+" "+myDB.RetriveUserInfo(editTextUsername.getText().toString(),"NAME"));
        i.putExtra("USERNAME", editTextUsername.getText().toString());
        i.putExtra("NAME", myDB.RetriveUserInfo(editTextUsername.getText().toString(),"NAME"));
        startActivity(i);
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }

    public void CallRegisterActivity(){
        startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }
}




//myDB.RetriveUserInfo(editTextUsername.getText().toString(),editTextPassword.getText().toString());
//myDB.RetriveUserInfo(editTextUsername.getText().toString(),"NAME");
//myDB.RetriveUserInfo(editTextUsername.getText().toString(),editTextPassword.getText().toString());
//myDB.RetriveUserInfo(editTextUsername.getText().toString(),"NAME");
//String Pass = myDB.RetriveUserInfo(editTextUsername.getText().toString(),editTextPassword.getText().toString());
//if (Pass == editTextPassword.getText().toString())


/*
try {
        if ().equals(myDB.RetriveUserInfo(editTextUsername.getText().toString(), editTextPassword.getText().toString()))) {
        //Toast.makeText(this, "Login Done " + myDB.RetriveUserInfo(editTextUsername.getText().toString(), "NAME"), Toast.LENGTH_SHORT).show();
        GumPop.start();
        startActivity(new Intent(LoginActivity.this, LoginActivity.class));
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);

        } else {
        Toast.makeText(this, "Please Try Again", Toast.LENGTH_SHORT).show();
        }
        }
        catch(Exception ex){
        Toast.makeText(this, "Please Try Again", Toast.LENGTH_SHORT).show();
        }
*/