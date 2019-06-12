package com.example.benedict.fitnesshealthcareapps;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.DisplayMetrics;
import android.widget.TextView;

public class AboutActivity extends Activity {
    String htmlTextCodes;
    TextView txtParagraph;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        txtParagraph =(TextView)findViewById(R.id.txtParagraph);

        //region For Pop UP Activity Code
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        int height = dm.heightPixels;
        getWindow().setLayout((int)(width*.8),(int)(height*.6));
        //endregion

        htmlTextCodes = "<html><body><p align=\"justify\">";
        htmlTextCodes+= "This project is all about the fitness and body development. You can develop your body without luxury gym materials. With the help of this app, you can increase your stamina by doing regular exercise based on the video presented by this app. You can monitor how many times you have already done a specific muscle development on the database provided. Every time you clicked a button to watch a video, it will be recorded on the databse for you to monitor what muscle development you have already watched.";
        htmlTextCodes+= "</p></body></html>";

        txtParagraph.setText((Html.fromHtml(htmlTextCodes)));
    }
}
