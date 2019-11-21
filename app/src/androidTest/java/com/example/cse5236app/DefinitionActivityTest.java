package com.example.cse5236app;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import org.junit.Test;

import androidx.test.rule.ActivityTestRule;

import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class DefinitionActivityTest extends ActivityTestRule<DefinitionActivity> {

    private DefinitionActivity mDefinitionActivity;
    private DefinitionFragment mDefinitionFragment;

    private TextView mDisplayedWord;

    public DefinitionActivityTest() {
        super(DefinitionActivity.class);

        Intent intent = DefinitionActivity.newDefinitionIntent(getInstrumentation().getTargetContext(), "Word");
        launchActivity(intent);
        mDefinitionActivity = getActivity();
        mDefinitionFragment = mDefinitionActivity.getFragmentForTest();

        getInstrumentation().waitForIdleSync();

        if (mDefinitionFragment != null) {
            View fragmentView = mDefinitionFragment.getView();
            if (fragmentView != null) {
                mDisplayedWord = fragmentView.findViewById(R.id.definition_word);
            }
        }
    }

    @Override
    protected void beforeActivityLaunched() {
        super.beforeActivityLaunched();
    }

    @Test
    public void testPreconditions() {
        assertNotNull(mDefinitionActivity);
        assertNotNull(mDefinitionFragment);
    }

    @Test
    public void testWordDisplayed() {
        getInstrumentation().waitForIdleSync();
        CharSequence displayedWord = mDisplayedWord.getText();

        assertNotNull(displayedWord);
        assertEquals("Word", displayedWord.toString());
    }

    @Override
    protected void afterActivityFinished() {
        super.afterActivityFinished();
        if (!getActivity().isFinishing()) {
            mDefinitionActivity.finish();
        }
    }

}