package com.example.cse5236app;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.content.Intent;


public class MainActivity extends AppCompatActivity {
    private Button camera_button;
    private Button folder_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        System.out.println("in onCreate");
        setContentView(R.layout.activity_main);

        camera_button = (Button) findViewById(R.id.camera_button);
        camera_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity2();
            }
        });

        folder_button = (Button) findViewById(R.id.folder_button);
        folder_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                openActivityFolder();
            }
        });

    }

    public void openActivity2() {
        Intent intent = new Intent(this, Activity2.class);
        startActivity(intent);
    }

    public void openActivityFolder(){
        Intent intent = new Intent(this, FolderActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        System.out.println("in onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        System.out.println("in onPause");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.out.println("in onDestroy");
    }

}




