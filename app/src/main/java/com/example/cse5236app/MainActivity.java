package com.example.cse5236app;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.util.Log;

import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class MainActivity extends AppCompatActivity {

    private Button camera_button;
    private Button folder_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.fragment_container);

        if (fragment == null) {
            fragment = new TextScannerFragment();
            fm.beginTransaction()
                    .add(R.id.fragment_container, fragment)
                    .commit();
        }

        initAddFolderButton();

//        camera_button = (Button) findViewById(R.id.camera_button);
//        camera_button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                openActivityCamera();
//            }
//        });
//
//        folder_button = (Button) findViewById(R.id.folder_button);
//        folder_button.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View v){
//                openActivityFolder();
//            }
//        });

    }

    public void openActivityCamera() {
        Intent intent = new Intent(this, CameraActivity.class);
        startActivity(intent);
    }

    public void openActivityFolder(){
        Intent intent = new Intent(this, FolderActivity.class);
        startActivity(intent);
    }

    private void initAddFolderButton() {

        FloatingActionButton buttonAddFolder = findViewById(R.id.button_add_folder);
        buttonAddFolder.setOnClickListener(view -> {
            Intent intent = new Intent(this, FolderActivity.class);
            startActivity(intent);
        });
    }

}




