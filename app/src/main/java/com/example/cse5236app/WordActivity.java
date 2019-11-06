package com.example.cse5236app;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cse5236app.model.Word;
import com.example.cse5236app.viewadapter.WordAdapter;
import com.example.cse5236app.viewmodel.WordViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.w3c.dom.Text;

import java.util.List;

public class WordActivity extends AppCompatActivity {
        private WordViewModel wordViewModel;
        public static final int ADD_WORD_REQUEST = 1;



        @Override
        protected void onCreate(Bundle savedInstanceState){
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_word);

                FloatingActionButton buttonAddNote = findViewById(R.id.button_add_word);
                buttonAddNote.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                                Intent intent = new Intent(WordActivity.this, AddWordActivity.class);
                                startActivityForResult(intent, ADD_WORD_REQUEST);
                        }
                });


                RecyclerView recyclerView = findViewById(R.id.recycler_view_word);
                recyclerView.setLayoutManager(new LinearLayoutManager(this));
                recyclerView.setHasFixedSize(true);

                final WordAdapter adapter = new WordAdapter();
                recyclerView.setAdapter(adapter);

                wordViewModel = ViewModelProviders.of(this).get(WordViewModel.class);
                wordViewModel.getAllWords().observe(this, new Observer<List<Word>>() {
                        @Override
                        public void onChanged( List<Word> words) {
                                adapter.setWords(words);
                        }
                });
                }

         @Override
        protected void onActivityResult(int requestCode, int resultCode, Intent data){
                super.onActivityResult(requestCode,resultCode,data);
                if(requestCode == ADD_WORD_REQUEST && resultCode == RESULT_OK){
                        String word_name = data.getStringExtra(AddWordActivity.EXTRA_WORD);
                        String definition = data.getStringExtra(AddWordActivity.EXTRA_DEF);
                        Word word = new Word(word_name, definition);
                        wordViewModel.insert(word);
                        Toast.makeText(this, "Word saved", Toast.LENGTH_SHORT).show();
                }
                else{
                        Toast.makeText(this, "Word not saved", Toast.LENGTH_SHORT).show();
                }

         }

        }



