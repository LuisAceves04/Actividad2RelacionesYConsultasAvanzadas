package com.fic.actividad2relacionesyconsultasavanzadas;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import java.util.List;
@Dao
public interface NoteDao {
    @Insert
    void insert(Note note);

    @Update
    void update(Note note);

    @Delete
    void delete(Note note);
    //ordenar las notas por fecha de creacion
    @Query("SELECT * FROM notes ORDER BY created_at DESC")
    LiveData<List<Note>> getAllNotes();

    //obtener por categoria
    @Query("SELECT * FROM notes WHERE category_id = :categoryId ORDER BY created_at DESC")
    LiveData<List<Note>> getNotesByCategory(int categoryId);

    //buscar notas por texto usando like
    @Query("SELECT * FROM notes WHERE note_title LIKE '%' || :query || '%' OR note_content LIKE '%' || :query || '%'")
    LiveData<List<Note>> searchNotes(String query);

    //obtener todas las categorías con sus notas (Agrupación/Relación 1:N)
    @Transaction
    @Query("SELECT * FROM categories ORDER BY category_name ASC")
    LiveData<List<CategoryWithNotes>> getCategoriesWithNotes();

    //obtener notas por id
    @Query("SELECT * FROM notes WHERE note_id = :noteId")
    LiveData<Note> getNoteById(int noteId);
}
