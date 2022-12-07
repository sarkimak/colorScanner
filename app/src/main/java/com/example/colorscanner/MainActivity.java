package com.example.colorscanner;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private static final int pic_id = 123;
    Button camera_open_id;
    ImageView click_img_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        camera_open_id = (Button)findViewById(R.id.camera_button);
        click_img_id = findViewById(R.id.img);

        // launch camera / image
        camera_open_id.setOnClickListener(v -> {
            // Create the camera_intent ACTION_IMAGE_CAPTURE it will open the camera for capture the image
            Intent camera_intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            // Start the activity with camera_intent, and request pic id
            startActivityForResult(camera_intent, pic_id);
        });

        if(getIntent().getStringExtra("activity").equals("results")){
            if(getIntent().getStringExtra("button").equals("camera")){
                camera_open_id.performClick();
            }
        }
    }

    // retrieve the image
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // Match request pic id with requestCode
        if (requestCode == pic_id) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            click_img_id.setImageBitmap(photo);
            setContentView(R.layout.activity_main);
            Toast.makeText(getApplicationContext(), "Scanning...", Toast.LENGTH_LONG).show();
            try {
                Thread.sleep(3000);
                Intent i = new Intent(MainActivity.this,ResultActivity.class);
                finish();
                startActivity(i);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

