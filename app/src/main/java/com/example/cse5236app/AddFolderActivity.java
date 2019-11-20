package com.example.cse5236app;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.example.cse5236app.model.Folder;

import androidx.appcompat.app.AppCompatActivity;

public class AddFolderActivity extends AppCompatActivity {

    private static final String EXTRA_TITLE = "com.example.cse5236app.EXTRA_TITLE";

    private static final String EXTRA_FOLDER_ID = "com.example.cse5236app.EXTRA_FOLDER_ID";

    private EditText editTextTitle;

    private int mFolderId = -1;

    /**
     * Returns a new intent which launches this activity for creating a new folder.
     */
    public static Intent newCreateFolderIntent(Context packageContext) {

        return new Intent(packageContext, AddFolderActivity.class);
    }

    /**
     * Returns a new intent which launches this activity for updating an existing folder.
     */
    public static Intent newUpdateFolderIntent(Context packageContext, Folder folder) {

        Intent intent = new Intent(packageContext, AddFolderActivity.class);
        intent.putExtra(EXTRA_FOLDER_ID, folder.getId());
        intent.putExtra(EXTRA_TITLE, folder.getTitle());

        return intent;
    }

    /**
     * Returns the new title within the result intent.
     */
    public static String getNewFolderTitle(Intent result) {

        return result.getStringExtra(EXTRA_TITLE);
    }

    /**
     * Returns the ID of the folder being updated.
     */
    public static int getUpdatedFolderId(Intent result) {

        return result.getIntExtra(EXTRA_FOLDER_ID, -1);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_folder);

        editTextTitle = findViewById(R.id.edit_text_title);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close_black_24dp);

        Bundle extras = getIntent().getExtras();
        if (extras != null && extras.containsKey(EXTRA_FOLDER_ID)) {
            setTitle("Update Folder");
            String folderName = extras.getString(EXTRA_TITLE);
            editTextTitle.setText(folderName);
            mFolderId = extras.getInt(EXTRA_FOLDER_ID);
        } else {
            setTitle("Add Folder");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_folder_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save_folder:
                saveFolder();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void saveFolder() {
        String title = editTextTitle.getText().toString();
        if (title.trim().isEmpty()) {
            Toast.makeText(this, "Please insert a title", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent data = new Intent();
        data.putExtra(EXTRA_TITLE, title);
        data.putExtra(EXTRA_FOLDER_ID, mFolderId);
        setResult(RESULT_OK, data);
        finish();
    }

}
