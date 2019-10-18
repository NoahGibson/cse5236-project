package com.example.cse5236app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cse5236app.model.Folder;
import com.example.cse5236app.model.FolderAdapter;
import com.example.cse5236app.model.FolderViewModel;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.widget.Toast;
import com.example.cse5236app.model.FolderViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class FolderActivity extends AppCompatActivity {
    public static final int ADD_FOLDER_REQUEST = 1;

    private FolderViewModel folderViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_folder);


        FloatingActionButton buttonAddFolder = findViewById(R.id.button_add_folder);
        buttonAddFolder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                Intent intent = new Intent(FolderActivity.this, AddFolderActivity.class);
                startActivityForResult(intent,ADD_FOLDER_REQUEST);
            }
        });


        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        final FolderAdapter adapter = new FolderAdapter();
        recyclerView.setAdapter(adapter);

        folderViewModel = ViewModelProviders.of(this).get(FolderViewModel.class);
        folderViewModel.getAllFolders().observe(this, new Observer<List<Folder>>() {
            @Override
            public void onChanged(List<Folder> folders) {

                adapter.setFolders(folders);
            }
        });

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                folderViewModel.delete(adapter.getFolderAt(viewHolder.getAdapterPosition()));
                Toast.makeText(FolderActivity.this, "Folder deleted", Toast.LENGTH_SHORT).show();
            }
        }).attachToRecyclerView(recyclerView);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ADD_FOLDER_REQUEST && resultCode == RESULT_OK) {
            String title = data.getStringExtra(AddFolderActivity.EXTRA_TITLE);

            Folder folder = new Folder(title);
            folderViewModel.insert(folder);

            Toast.makeText(this, "Note saved", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Note not saved", Toast.LENGTH_SHORT).show();
        }
    }


}
