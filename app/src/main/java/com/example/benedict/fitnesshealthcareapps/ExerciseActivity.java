package com.example.benedict.fitnesshealthcareapps;

import android.app.Activity;
import android.graphics.PixelFormat;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.VideoView;

import static com.example.benedict.fitnesshealthcareapps.R.id.txtName;

public class ExerciseActivity extends Activity {
    String NAME,USERNAME,EXECISE,uriPath;
    MediaPlayer GumPop;
    VideoView VV;
    Uri uri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise);

        init();
    }

    private void init() {
        GumPop = MediaPlayer.create(this,R.raw.gum_bubble);
        GumPop.start();

        getWindow().setFormat(PixelFormat.UNKNOWN);
        VV = (VideoView)findViewById(R.id.VideoMedia);
        uriPath = "android.resource://com.example.benedict.fitnesshealthcareapps/";

        GetIntentData();
        Log.e("CheckVideoToPlay",EXECISE);
        CheckVideoToPlay();
    }

    private void CheckVideoToPlay() {
        if(EXECISE.equals("1")){
            uriPath += R.raw.biceps;
        }
        else if(EXECISE.equals("2")){
            uriPath += R.raw.chest;
        }
        else if(EXECISE.equals("3")){
            uriPath += R.raw.shoulder;
        }
        else if(EXECISE.equals("4")){
            uriPath += R.raw.triceps;
        }
        else if(EXECISE.equals("5")){
            uriPath += R.raw.abs;
        }
        else if(EXECISE.equals("6")){
            uriPath += R.raw.legs;
        }
        Log.e("CheckVideoToPlay",uriPath);
        uri = uri.parse(uriPath);
        VV.setVideoURI(uri);
        VV.requestFocus();
        VV.start();
    }

    public void GetIntentData(){
        NAME = getIntent().getExtras().getString("NAME");
        USERNAME = getIntent().getExtras().getString("USERNAME");
        EXECISE = getIntent().getExtras().getString("EXECISE");
        Log.e("USERNAME",NAME + " " + USERNAME);
    }
}
