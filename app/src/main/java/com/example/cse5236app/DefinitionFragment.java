package com.example.cse5236app;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class DefinitionFragment extends Fragment {

    private static final String DEF_KEY = "DEFINITION";

    private static final String TAG = "DefinitionFragment";

    private String mWord;

    private TextView mWordTextView;
    private TextView mDefinitionTextView;

    public DefinitionFragment() {}

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

}
