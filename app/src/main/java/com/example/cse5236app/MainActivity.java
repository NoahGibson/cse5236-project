package com.example.cse5236app;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

public class MainActivity extends AppCompatActivity {

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

        initSavedWordsButton();
    }

    private void initSavedWordsButton() {

        ExtendedFloatingActionButton buttonSavedWords = findViewById(R.id.button_saved_words);
        buttonSavedWords.setOnClickListener(view -> {
            Intent intent = new Intent(this, WordActivity.class);
            startActivity(intent);
        });
    }

}




