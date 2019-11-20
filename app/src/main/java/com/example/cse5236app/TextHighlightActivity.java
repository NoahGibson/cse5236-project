package com.example.cse5236app;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

public class TextHighlightActivity extends AppCompatActivity {

    private static final String EXTRA_TEXT = "com.example.cse5236app.EXTRA_TEXT";

    /**
     * Returns a new intent which launches this activity for highlighting the given text.
     */
    public static Intent newTextHighlightIntent(Context packageContext, String text) {

        Intent intent = new Intent(packageContext, TextHighlightActivity.class);
        intent.putExtra(EXTRA_TEXT, text);

        return intent;
    }

    public String getExtraText() {
        Bundle extras = getIntent().getExtras();
        if (extras != null && extras.containsKey(EXTRA_TEXT)) {
            return extras.getString(EXTRA_TEXT);
        } else {
            return null;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_highlight);

        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.text_highlight_fragment_container);

        if (fragment == null) {
            fragment = new TextHighlightFragment();
            fm.beginTransaction()
                    .add(R.id.text_highlight_fragment_container, fragment)
                    .commit();
        }
    }

}
