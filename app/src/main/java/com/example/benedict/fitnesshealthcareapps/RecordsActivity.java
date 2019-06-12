package com.example.benedict.fitnesshealthcareapps;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.TextView;

import java.util.ArrayList;

public class RecordsActivity extends Activity {
    TextView txtName;
    String USERNAME;
    MediaPlayer GumPop;
    DataBaseHelper myDB;
    GridView gvRecords;
    ArrayAdapter<String> dataAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_records);

        init();
    }

    private void init() {
        myDB = new DataBaseHelper(this);

        GumPop = MediaPlayer.create(this,R.raw.gum_bubble);

        txtName = (TextView)findViewById(R.id.txtName);

        gvRecords = (GridView)findViewById(R.id.gvRecords);

        GetIntentData();

        DisplayGridviewActivities();
    }

    private void DisplayGridviewActivities() {
        ArrayList<String> data;
        data = myDB.RetriveActivities(USERNAME);
        if(data != null) {
            dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_gallery_item, data);
            gvRecords.setAdapter(dataAdapter);
        }
        else{

        }
    }

    @Override
    public void onBackPressed() {
        GumPop.start();
        Intent i = new Intent(this, UserMenuActivity.class);
        i.putExtra("USERNAME", USERNAME);
        i.putExtra("NAME", txtName.getText().toString());
        startActivity(i);
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }


    public void GetIntentData(){
        txtName.setText(getIntent().getExtras().getString("NAME"));
        USERNAME = getIntent().getExtras().getString("USERNAME");
        Log.e("GetIntentData",txtName.getText().toString() +" "+USERNAME);
    }
}
