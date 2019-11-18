package com.example.cse5236app.viewadapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.cse5236app.R;
import com.example.cse5236app.model.Word;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class WordAdapter extends RecyclerView.Adapter<WordAdapter.WordHolder> {

    private List<Word> words = new ArrayList<>();

    @NonNull
    @Override
    public WordHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.word_item, parent, false);
        return new WordHolder(itemView);
    }

    /**
     * take data and place it into word holder
     */
    @Override
    public void onBindViewHolder(@NonNull WordHolder holder, int position) {
        Word currentWord = words.get(position);
        holder.textViewWord.setText(currentWord.getWord());
        holder.textViewDef.setText(currentWord.getDefinition());
    }

    /**
     * want the number of items in recycler view
     */
    @Override
    public int getItemCount() {
        return words.size();
    }

    public void setWords(List<Word> words) {
        this.words = words;
        notifyDataSetChanged();
    }

    /**
     * create holder to hold the View
     */
    public class WordHolder extends RecyclerView.ViewHolder {
        private TextView textViewWord;
        private TextView textViewDef;

        public WordHolder(View itemView) {
            super(itemView);
            textViewWord = itemView.findViewById(R.id.text_view_word);
            textViewDef = itemView.findViewById(R.id.text_view_definition);
        }
    }


}


