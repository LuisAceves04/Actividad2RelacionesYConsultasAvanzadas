package com.fic.actividad2relacionesyconsultasavanzadas;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;
@Dao
public interface CategoryDao {
    //insertar nueva categoria
    @Insert
    void insert(Category category);

    //eliminar categoria
    @Delete
    void delete(Category category);

    //obtener todas las categorias ordenados por nombre
    @Query("SELECT * FROM categories ORDER BY category_name ASC")
    LiveData<List<Category>> getAllCategories();

    //obtener categforia por id
    @Query("SELECT * FROM categories WHERE category_id = :categoryId")
    LiveData<Category> getCategoryById(int categoryId);

    //obtener categorias por nombre
    @Query("SELECT * FROM categories WHERE category_name = :categoryName")
    LiveData<Category> getCategoryByName(String categoryName);

}
