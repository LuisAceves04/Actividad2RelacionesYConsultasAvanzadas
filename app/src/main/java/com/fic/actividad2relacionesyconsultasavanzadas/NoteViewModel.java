package com.fic.actividad2relacionesyconsultasavanzadas;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import java.util.List;

public class NoteViewModel extends AndroidViewModel {

    private final NoteDao noteDao;
    private final CategoryDao categoryDao;

    private final LiveData<List<CategoryWithNotes>> allNotesGrouped; // La lista principal (1:N)
    private final LiveData<List<Category>> allCategories;            // Lista solo de categorías

    // --- CONSTRUCTOR ---
    public NoteViewModel(@NonNull Application application) {
        super(application);

        AppDatabase db = AppDatabase.getDatabase(application);

        noteDao = db.noteDao();
        categoryDao = db.categoryDao();


        allNotesGrouped = noteDao.getCategoriesWithNotes();
        allCategories = categoryDao.getAllCategories();
    }

    //gettes y setters

    public LiveData<List<CategoryWithNotes>> getAllNotesGrouped() {
        return allNotesGrouped;
    }

    public LiveData<List<Category>> getAllCategories() {
        return allCategories;
    }

    public LiveData<List<Note>> searchNotes(String query) {
        return noteDao.searchNotes(query);
    }

    public void insertNote(Note note) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            noteDao.insert(note);
        });
    }

    public void insertCategory(Category category) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            categoryDao.insert(category);
        });
    }
}