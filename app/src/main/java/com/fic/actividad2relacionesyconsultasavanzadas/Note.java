package com.fic.actividad2relacionesyconsultasavanzadas;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;


import static androidx.room.ForeignKey.CASCADE; // Importamos CASCADE para que al borrar una categoría, sus notas se borren automáticamente
import java.util.Date;


@Entity(tableName = "notes", foreignKeys = @ForeignKey(entity = Category.class,
                                                        parentColumns = "category_id",
                                                        childColumns = "category_id",
                                                        onDelete = ForeignKey.CASCADE))
public class Note {

    @PrimaryKey(autoGenerate = true)
    private int note_id;
    private String note_title;
    private String note_content;

    private Long created_at;

    private int category_id;


    public Note(String note_title, String note_content, long created_at, int category_id) {
        this.note_title = note_title;
        this.note_content = note_content;
        this.created_at = created_at;
        this.category_id = category_id;
    }

    //getters y setters
    public int getNote_id() {
        return note_id;
    }

    public void setNote_id(int note_id) {
        this.note_id = note_id;
    }

    public String getNote_title() {
        return note_title;
    }

    public void setNote_title(String note_title) {
        this.note_title = note_title;
    }

    public String getNote_content() {
        return note_content;
    }

    public void setNote_content(String note_content) {
        this.note_content = note_content;
    }

    public Long getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Long created_at) {
        this.created_at = created_at;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }



}
