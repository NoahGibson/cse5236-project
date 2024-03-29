package com.example.cse5236app.viewmodel;

import android.app.Application;

import com.example.cse5236app.model.Folder;
import com.example.cse5236app.repository.FolderRepository;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class FolderViewModel extends AndroidViewModel {

    private FolderRepository repository;
    private LiveData<List<Folder>> allFolders;

    public FolderViewModel(@NonNull Application application) {
        super(application);
        repository = new FolderRepository(application);
        allFolders = repository.getAllFolders();
    }

    public LiveData<Folder> get(int id) {
        return repository.get(id);
    }

    public void insert(Folder folder) {
        repository.insert(folder);
    }

    public void update(Folder folder) {
        repository.update(folder);
    }

    public void delete(Folder folder) {
        repository.delete(folder);
    }

    public LiveData<List<Folder>> getAllFolders() {
        return allFolders;
    }

}
