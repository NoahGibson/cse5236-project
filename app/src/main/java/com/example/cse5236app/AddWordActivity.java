package com.example.cse5236app;
import android.content.Context;
import android.content.Intent;
import android.net.Network;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.net.ConnectivityManager;

import androidx.appcompat.app.AppCompatActivity;

import java.net.URL;

public class AddWordActivity extends AppCompatActivity {

    public static final String EXTRA_WORD = "com.example.cse5236app.EXTRA_WORD";
    public static final String EXTRA_DEF = "com.example.cse5236app.EXTRA_DEF";

    private EditText editTextWord;
    private TextView textDefinition;


    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_word);

        editTextWord = findViewById(R.id.edit_text_word);
        textDefinition = findViewById(R.id.edit_text_definition);


        Button button_definition = (Button) findViewById(R.id.button_get_definition);
        button_definition.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (isNetworkAvailable()) {
                    requestAPIButtonClick(v);
                } else {
                    Toast toast = Toast.makeText(AddWordActivity.this,
                            "No Connection", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                }
            }
        });
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    /**
     * passes url
     */
    public void requestAPIButtonClick(View v){
        DictionaryRequest dictionaryRequest = new DictionaryRequest(this, textDefinition);
        dictionaryRequest.execute(editTextWord.getText().toString());
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
        String definition = textDefinition.getText().toString();

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

