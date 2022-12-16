package com.example.colorscanner;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity2 extends AppCompatActivity {


    Button to_camera_button;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        to_camera_button =findViewById(R.id.to_camera_button);

        to_camera_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openAvtivity1();
            }
        });
}

    public void openAvtivity1(){
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("activity","not-results");
        startActivity(intent);
    }

}