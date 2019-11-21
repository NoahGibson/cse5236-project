package com.example.cse5236app;

import android.view.View;

import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;

public class WordActivityTest {

    @Rule
    public ActivityTestRule<WordActivity> wordActivityActivityTestRule = new ActivityTestRule<>(WordActivity.class);
    private WordActivity wordActivity = null;

    @Before
    public void setUp() throws Exception {
        wordActivity = wordActivityActivityTestRule.getActivity();
    }

    @Test
    public void testWord() {
        View view = wordActivity.findViewById(R.layout.activity_word);
        assertNotNull(view);
    }

    @After
    public void tearDown() throws Exception {
        wordActivity = null;
    }
}


