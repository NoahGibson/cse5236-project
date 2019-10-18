package com.example.cse5236app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

public class AddFolderActivity extends AppCompatActivity {
    public static final String EXTRA_TITLE = "com.example.cse5236app.EXTRA_TITLE";

    private EditText editTextTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_folder);

        editTextTitle = findViewById(R.id.edit_text_title);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close_black_24dp);
        setTitle("Add Folder");
    }

    public void saveFolder(){
        String title = editTextTitle.getText().toString();
        if(title.trim().isEmpty()){
            Toast.makeText(this, "Please insert a title", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent data = new Intent();
        data.putExtra(EXTRA_TITLE, title);
        setResult(RESULT_OK, data);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_folder_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){
            case R.id.save_folder:
            saveFolder();
            return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }



}
