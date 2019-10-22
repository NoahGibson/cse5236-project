package com.example.cse5236app;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.cse5236app.model.Folder;
import com.example.cse5236app.viewadapter.FolderAdapter;
import com.example.cse5236app.viewmodel.FolderViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class FolderActivity extends AppCompatActivity {

    public static final int ADD_FOLDER_REQUEST = 1;

    public static final int UPDATE_FOLDER_REQUEST = 2;

    private FolderViewModel folderViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_folder);

        folderViewModel = ViewModelProviders.of(this).get(FolderViewModel.class);

        initAddFolderButton();
        initFolderList();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ADD_FOLDER_REQUEST && resultCode == RESULT_OK) {
            String title = AddFolderActivity.getNewFolderTitle(data);

            Folder folder = new Folder(title);
            folderViewModel.insert(folder);

            Toast.makeText(this, "Folder saved", Toast.LENGTH_SHORT).show();

        } else if (requestCode == UPDATE_FOLDER_REQUEST && resultCode == RESULT_OK) {
            int id = AddFolderActivity.getUpdatedFolderId(data);
            String title = AddFolderActivity.getNewFolderTitle(data);

            folderViewModel.get(id).observe(this, folder -> {
                folder.setTitle(title);
                folderViewModel.update(folder);
            });

            Toast.makeText(this, "Folder updated", Toast.LENGTH_SHORT).show();

        } else {
            Toast.makeText(this, "Folder not saved", Toast.LENGTH_SHORT).show();

        }
    }

    private void initAddFolderButton() {

        FloatingActionButton buttonAddFolder = findViewById(R.id.button_add_folder);
        buttonAddFolder.setOnClickListener(view -> {
            Intent intent = AddFolderActivity.newCreateFolderIntent(FolderActivity.this);
            startActivityForResult(intent, ADD_FOLDER_REQUEST);
        });
    }

    private void initFolderList() {

        RecyclerView recyclerView = findViewById(R.id.recycler_view);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        final FolderAdapter adapter = new FolderAdapter();
        recyclerView.setAdapter(adapter);

        folderViewModel.getAllFolders().observe(this, adapter::setFolders);

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder,
                                  @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                folderViewModel.delete(adapter.getFolderAt(viewHolder.getAdapterPosition()));
                Toast.makeText(FolderActivity.this, "Folder deleted", Toast.LENGTH_SHORT).show();
            }
        }).attachToRecyclerView(recyclerView);
    }

}
