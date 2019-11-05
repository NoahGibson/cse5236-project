package com.example.cse5236app;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddWordActivity extends AppCompatActivity {

    public static final String EXTRA_WORD = "com.example.cse5236app.EXTRA_WORD";
    public static final String EXTRA_DEF = "com.example.cse5236app.EXTRA_DEF";

    private EditText editTextWord;
    private EditText editTextDefinition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_word);

        editTextWord = findViewById(R.id.edit_text_word);
        editTextDefinition = findViewById(R.id.edit_text_definition);
    }

    /**
     * using our options menu created
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_word_menu, menu);
        return true;
    }

    /**
     *
     * if save button is clicked then saveWord() is called
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){
            case R.id.save_word:
                saveWord();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    /**
     * passing extras in order to save word
     */
    public void saveWord(){
        String word_name = editTextWord.getText().toString();
        String definition = editTextDefinition.getText().toString();

        if(word_name.trim().isEmpty() || definition.trim().isEmpty()){
            Toast.makeText(this, "Please add word or definition", Toast.LENGTH_SHORT).show();
            return;
        }
        else{
            Intent data = new Intent();
            data.putExtra(EXTRA_WORD, word_name);
            data.putExtra(EXTRA_DEF, definition);
            setResult(RESULT_OK, data);
            finish();
        }

    }
}

