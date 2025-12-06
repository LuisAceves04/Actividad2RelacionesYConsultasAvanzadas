package com.fic.actividad2relacionesyconsultasavanzadas;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "categories")
public class Category {
    @PrimaryKey(autoGenerate = true)
    private int category_id;

    private String category_name;

    //constructor
    public Category(String category_name) {
        this.category_name = category_name;
    }

    // Getters y Setters
    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }
}