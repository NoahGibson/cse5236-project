package com.example.cse5236app.model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.cse5236app.db.FolderRepository;

import java.util.List;

public class FolderViewModel extends AndroidViewModel {

    private FolderRepository repository;
    private LiveData<List<Folder>> allFolders;

    public FolderViewModel(@NonNull Application application) {
        super(application);
        repository = new FolderRepository(application);
        allFolders = repository.getAllFolders();
    }

    public void insert(Folder folder){
        repository.insert(folder);

    }

    public void update(Folder folder){
        repository.update(folder);
    }

    public void delete(Folder folder){
        repository.delete(folder);
    }

    public LiveData<List<Folder>> getAllFolders() {
        return allFolders;
    }
}
