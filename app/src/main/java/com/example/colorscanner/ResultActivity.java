package com.example.colorscanner;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class ResultActivity extends AppCompatActivity {

    //Called when this activity is created
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        //Used to store and access the detected color across activities
        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0); // 0 - for private mode
        SharedPreferences.Editor editor = pref.edit();

        //When on results screen, color should always be detected, but just a double check
        if(pref.contains("color_detected")) {
            TextView word = (TextView) findViewById(R.id.detectedColor);
            ConstraintLayout bg = (ConstraintLayout) findViewById(R.id.layout);

            //Get the detected color from the "global" variable and use it to customize the result screen
            String color = pref.getString("color_detected", "");
            word.setTextColor(Color.parseColor(color));
            word.setText(color);
            bg.setBackgroundColor(Color.parseColor(color));
        }

    }

    //Goes to the screen to capture a new image
    public void toCamera(View view){
        Intent i = new Intent(ResultActivity.this,MainActivity.class);

        //Both used to capture what the user is intending to do. Affects logic in MainActivity2
        i.putExtra("activity","results");
        i.putExtra("button","camera");
        finish();
        startActivity(i);
    }

    //Takes the user back to the home screen
    public void toHome(View view){
        Intent i = new Intent(ResultActivity.this,MainActivity2.class);

        //Both used to capture what the user is intending to do. Affects logic in MainActivity2
        i.putExtra("activity","results");
        i.putExtra("button","home");
        finish();
        startActivity(i);
    }

    //Would ideally have logic to save and tag a scan, but just something visual for now
    public void saveScan(View view){
        Toast.makeText(getApplicationContext(), "Saving...", Toast.LENGTH_LONG).show();
    }
}