package com.example.cse5236app;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cse5236app.model.Word;
import com.example.cse5236app.viewmodel.WordViewModel;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

public class DefinitionFragment extends Fragment {

    private static final String DEF_KEY = "DEFINITION";

    private static final String TAG = "DefinitionFragment";

    private String mWord;

    private TextView mWordTextView;
    private TextView mDefinitionTextView;
    private Button mSaveWordButton;

    private WordViewModel mWordViewModel;

    public DefinitionFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_definition, container, false);

        DefinitionActivity parentActivity = (DefinitionActivity) getActivity();
        if (parentActivity != null) {
            mWord = parentActivity.getExtraWord();
        }

        mWordTextView = (TextView) v.findViewById(R.id.definition_word);
        mWordTextView.setText(mWord);

        mDefinitionTextView = (TextView) v.findViewById(R.id.definition_definition);
        if (savedInstanceState != null && mDefinitionTextView != null) {
            mDefinitionTextView.setText(savedInstanceState.getString(DEF_KEY));
        } else {
            fetchDefinition();
        }

        mSaveWordButton = (Button) v.findViewById(R.id.save_word_button);
        mSaveWordButton.setOnClickListener(view -> saveWord());

        mWordViewModel = ViewModelProviders.of(this).get(WordViewModel.class);

        return v;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(DEF_KEY, mDefinitionTextView.getText().toString());
    }

    private void fetchDefinition() {
        DictionaryRequest dictionaryRequest = new DictionaryRequest(getContext(), mDefinitionTextView);
        dictionaryRequest.execute(mWord);
    }

    private void saveWord() {
        String word = mWord;
        String definition = mDefinitionTextView.getText().toString();

        if (!word.trim().isEmpty() && !definition.trim().isEmpty()) {
            mWordViewModel.insert(new Word(word, definition));
            Toast.makeText(getContext(), "Word saved", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getContext(), WordActivity.class);
            startActivity(intent);
        } else {
            Toast.makeText(getContext(), "Unable to save word", Toast.LENGTH_SHORT).show();
        }
    }

}
