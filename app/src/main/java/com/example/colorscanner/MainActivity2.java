package com.example.colorscanner;
import androidx.appcompat.app.AppCompatActivity;
import androidx.palette.graphics.Palette;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity2 extends AppCompatActivity {


    Button to_camera_button;
    private static final int pic_id = 123;


    //Called when this activity is started
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        to_camera_button = findViewById(R.id.to_camera_button);

        // launch camera / image
        to_camera_button.setOnClickListener(v -> {
            // Create the camera_intent ACTION_IMAGE_CAPTURE it will open the camera for capture the image
            Intent camera_intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            // Start the activity with camera_intent, and request pic id
            startActivityForResult(camera_intent, pic_id);
        });

        //Used to determine if the last activity wanted to go home or use the camera.
        //Programatically clicks the button if they wants to use the camera
        if (getIntent().hasExtra("activity") && getIntent().getStringExtra("activity").equals("results")) {
            if (getIntent().getStringExtra("button").equals("camera")) {
                to_camera_button.performClick();
            }
        }
    }

    //Captures the image and proceeds to the results screen
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // Match request pic id with requestCode
        if (requestCode == pic_id) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            detectColor(photo);
            setContentView(R.layout.activity_main);
            Toast.makeText(getApplicationContext(), "Scanning...", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(MainActivity2.this,ResultActivity.class);
            finish();
            startActivity(i);
        }
    }

    //Detects the most prominent color within a bitmap
    protected void detectColor(Bitmap photo){
        //Used to globally access the color detection across activities
        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0); // 0 - for private mode
        SharedPreferences.Editor editor = pref.edit();

        //Palette class does the actual detecting
        Palette palette = Palette.from(photo).generate();
        int dominantColor = palette.getDominantColor(Color.BLACK);
        String color = String.format("#%06X", (0xFFFFFF & dominantColor));

        //Set the color to be used across activities
        editor.putString("color_detected", color);
        editor.apply();
    }

}