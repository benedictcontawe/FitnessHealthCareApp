package com.example.benedict.fitnesshealthcareapps;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.DisplayMetrics;
import android.widget.TextView;

public class CreditsActivity extends Activity {
    String htmlTextCodes;
    TextView txtParagraph;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credits);

        txtParagraph =(TextView)findViewById(R.id.txtParagraph);

        //region For Pop UP Activity Code
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        int height = dm.heightPixels;
        getWindow().setLayout((int)(width*.8),(int)(height*.4));
        //endregion

        htmlTextCodes = "<html><body>";
        htmlTextCodes+= "<center>";
        htmlTextCodes+= "Programmers & Developers";
        htmlTextCodes+= "<br><br>";
        htmlTextCodes+= "Kristine Joy S. Adolfo<br>";
        htmlTextCodes+= "Ryen Ranz T. Morales";
        htmlTextCodes+= "<br><br>";
        htmlTextCodes+= "</center>";
        htmlTextCodes+= "</body></html>";

        txtParagraph.setText((Html.fromHtml(htmlTextCodes)));
    }
}
