package com.example.cse5236app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cse5236app.model.Folder;
import com.example.cse5236app.model.FolderAdapter;
import com.example.cse5236app.model.FolderViewModel;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.widget.Toast;

import com.example.cse5236app.model.FolderViewModel;

import java.util.List;

public class FolderActivity extends AppCompatActivity {

    private FolderViewModel folderViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_folder);

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        final FolderAdapter adapter = new FolderAdapter();
        recyclerView.setAdapter(adapter);

        folderViewModel = ViewModelProviders.of(this).get(FolderViewModel.class);
        folderViewModel.getAllFolders().observe(this, new Observer<List<Folder>>() {
            @Override
            public void onChanged(List<Folder> folders) {
                //update RecyclerView
                //Toast.makeText(FolderActivity.this, "onChanged", Toast.LENGTH_SHORT).show();

                adapter.setFolders(folders);
            }
        });

    }
}

