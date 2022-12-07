package com.example.colorscanner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
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