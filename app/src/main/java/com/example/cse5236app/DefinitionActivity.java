package com.example.cse5236app;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

public class DefinitionActivity extends AppCompatActivity {

    private static final String EXTRA_WORD = "com.example.cse5236app.EXTRA_WORD";

    /**
     * Returns a new intent which launches this activity for displaying a definition.
     */
    public static Intent newDefinitionIntent(Context packageContext, String word) {

        Intent intent = new Intent(packageContext, DefinitionActivity.class);
        intent.putExtra(EXTRA_WORD, word);

        return intent;
    }

    public String getExtraWord() {
        Bundle extras = getIntent().getExtras();
        if (extras != null && extras.containsKey(EXTRA_WORD)) {
            return extras.getString(EXTRA_WORD);
        } else {
            return null;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_definition);

        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.definition_fragment_container);

        if (fragment == null) {
            fragment = new DefinitionFragment();
            fm.beginTransaction()
                    .add(R.id.definition_fragment_container, fragment)
                    .commit();
        }
    }

}
