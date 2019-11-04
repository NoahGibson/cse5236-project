package com.example.cse5236app.viewmodel;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import com.example.cse5236app.model.Word;
import com.example.cse5236app.repository.WordRepository;

import java.util.List;

public class WordViewModel extends AndroidViewModel {
    private WordRepository repository;
    private LiveData<List<Word>> allWords;

    public WordViewModel(@NonNull Application application) {
        super(application);
        repository = new WordRepository(application);
        allWords = repository.getAllWords();
    }

    public LiveData<Word> get(int id) {
        return repository.get(id);
    }

    public void insert(Word word){
        repository.insert(word);
    }

    public void delete(Word word){
        repository.delete(word);
    }

    public LiveData<List<Word>> getAllWords() {
        return allWords;
    }


}
