package com.example.cse5236app;

import android.os.Bundle;
import android.util.Log;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class TextHighlightFragment extends Fragment {

    private String mText;

    private TextView mSelectedTextView;

    private static final int DEFINITION = 1;

    private static final String TAG = "TextHighlightFragment";

    public TextHighlightFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_text_highlight, container, false);

        TextHighlightActivity parentActivity = (TextHighlightActivity) getActivity();
        if (parentActivity != null) {
            mText = parentActivity.getExtraText();
        }

        mSelectedTextView = (TextView) v.findViewById(R.id.selected_text);
        mSelectedTextView.setText(mText);
        mSelectedTextView.setCustomSelectionActionModeCallback(new ActionMode.Callback() {

            @Override
            public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
                menu.add(0, DEFINITION, 0, "Definition");
                return true;
            }

            @Override
            public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
                menu.removeItem(android.R.id.selectAll);
                menu.removeItem(android.R.id.cut);
                menu.removeItem(android.R.id.copy);
                menu.removeItem(android.R.id.shareText);
                return true;
            }

            @Override
            public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case DEFINITION:
                        int min = 0;
                        int max = mSelectedTextView.getText().length();
                        if (mSelectedTextView.isFocused()) {
                            final int selStart = mSelectedTextView.getSelectionStart();
                            final int selEnd = mSelectedTextView.getSelectionEnd();
                            min = Math.max(0, Math.min(selStart, selEnd));
                            max = Math.max(0, Math.max(selStart, selEnd));
                        }
                        final CharSequence selectedText = mSelectedTextView.getText().subSequence(min, max);
                        launchDefinitionScreen(selectedText.toString());
                        actionMode.finish();
                        return true;
                    default:
                        break;
                }
                return false;
            }

            @Override
            public void onDestroyActionMode(ActionMode actionMode) {}

        });

        return v;
    }

    private void launchDefinitionScreen(String text) {

    }

}
