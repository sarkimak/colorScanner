package com.example.colorscanner;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0); // 0 - for private mode
        SharedPreferences.Editor editor = pref.edit();

        if(pref.contains("color_detected")) {
            TextView word = (TextView) findViewById(R.id.detectedColor);
            ConstraintLayout bg = (ConstraintLayout) findViewById(R.id.layout);

            String color = pref.getString("color_detected", "");
            word.setTextColor(Color.parseColor(color));
            bg.setBackgroundColor(Color.parseColor(color));
        }

    }

    public void toCamera(View view){
        Intent i = new Intent(ResultActivity.this,MainActivity.class);
        i.putExtra("activity","results");
        i.putExtra("button","camera");
        finish();
        startActivity(i);
    }

    public void toHome(View view){
        Intent i = new Intent(ResultActivity.this,MainActivity2.class);
        i.putExtra("activity","results");
        i.putExtra("button","home");
        finish();
        startActivity(i);
    }

    public void saveScan(View view){
        Toast.makeText(getApplicationContext(), "Saving...", Toast.LENGTH_LONG).show();
    }
}