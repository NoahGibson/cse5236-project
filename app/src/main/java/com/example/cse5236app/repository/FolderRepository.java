package com.example.cse5236app.repository;

import android.app.Application;
import android.os.AsyncTask;

import com.example.cse5236app.dao.FolderDao;
import com.example.cse5236app.db.AppDatabase;
import com.example.cse5236app.model.Folder;

import java.util.List;

import androidx.lifecycle.LiveData;

public class FolderRepository {

    private FolderDao folderDao;
    private LiveData<List<Folder>> allFolders;

    public FolderRepository(Application application) {
        AppDatabase database = AppDatabase.getInstance(application);
        folderDao = database.folderDao();
        allFolders = folderDao.getAllFolders();
    }

    public LiveData<Folder> get(int id) {
        return folderDao.findFolderById(id);
    }

    public void insert(Folder folder) {
        new InsertFolderAsyncTask(folderDao).execute(folder);
    }

    public void update(Folder folder) {
        new UpdateFolderAsyncTask(folderDao).execute(folder);
    }

    public void delete(Folder folder) {
        new DeleteFolderAsyncTask(folderDao).execute(folder);
    }

    public LiveData<List<Folder>> getAllFolders() {
        return allFolders;
    }

    private static class InsertFolderAsyncTask extends AsyncTask<Folder, Void, Void> {
        private FolderDao folderDao;

        private InsertFolderAsyncTask(FolderDao folderDao) {
            this.folderDao = folderDao;
        }

        @Override
        protected Void doInBackground(Folder... folders) {
            folderDao.insert(folders[0]);
            return null;
        }
    }

    private static class UpdateFolderAsyncTask extends AsyncTask<Folder, Void, Void> {
        private FolderDao folderDao;

        private UpdateFolderAsyncTask(FolderDao folderDao) {
            this.folderDao = folderDao;
        }

        @Override
        protected Void doInBackground(Folder... folders) {
            folderDao.update(folders[0]);
            return null;
        }
    }

    private static class DeleteFolderAsyncTask extends AsyncTask<Folder, Void, Void> {
        private FolderDao folderDao;

        private DeleteFolderAsyncTask(FolderDao folderDao) {
            this.folderDao = folderDao;
        }

        @Override
        protected Void doInBackground(Folder... folders) {
            folderDao.delete(folders[0]);
            return null;
        }
    }

}
