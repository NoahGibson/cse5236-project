package com.example.cse5236app;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

public class DefinitionActivity extends AppCompatActivity {

    private static final String EXTRA_WORD = "com.example.cse5236app.EXTRA_WORD";

    private DefinitionFragment mDefinitionFragment;

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

        recoverFragment();
    }

    public DefinitionFragment getFragmentForTest() {
        recoverFragment();
        return mDefinitionFragment;
    }

    private void recoverFragment() {
        if (mDefinitionFragment == null) {
            FragmentManager fm = getSupportFragmentManager();
            if (fm.getFragments().size() == 0) {
                DefinitionFragment fragment = new DefinitionFragment();
                fm.beginTransaction()
                        .add(R.id.definition_fragment_container, fragment)
                        .commit();
            } else {
                for (Fragment fragment: fm.getFragments()) {
                    if (fragment instanceof DefinitionFragment) {
                        mDefinitionFragment = (DefinitionFragment) fragment;
                    }
                }
            }
        }
    }

}
