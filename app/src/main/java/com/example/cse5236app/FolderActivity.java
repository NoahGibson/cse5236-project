package com.example.cse5236app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;

public class FolderActivity extends AppCompatActivity {

    private Button words_folder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_folder);

        words_folder = (Button) findViewById(R.id.words_folder);
        words_folder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivityStoredWords();
            }
        });

    }

    public void openActivityStoredWords(){
        Intent intent = new Intent(this, StoredWordsActivity.class);
        startActivity(intent);
    }

    }

