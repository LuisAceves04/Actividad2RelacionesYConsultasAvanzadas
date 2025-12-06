package com.fic.actividad2relacionesyconsultasavanzadas;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import java.util.ArrayList;
import java.util.List;

public class AddNoteActivity extends AppCompatActivity {

    private NoteViewModel noteViewModel;
    private EditText etTitle, etContent, etNewCategory;
    private Spinner spinnerCategories;

    private List<Category> categoriesList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        etTitle = findViewById(R.id.etTitle);
        etContent = findViewById(R.id.etContent);
        etNewCategory = findViewById(R.id.etNewCategory);
        spinnerCategories = findViewById(R.id.spinnerCategories);
        Button btnSaveCategory = findViewById(R.id.btnSaveCategory);
        Button btnSaveNote = findViewById(R.id.btnSaveNote);

        noteViewModel = new ViewModelProvider(this).get(NoteViewModel.class);

        noteViewModel.getAllCategories().observe(this, categories -> {
            categoriesList = categories;

            List<String> categoryNames = new ArrayList<>();
            for (Category c : categories) {
                categoryNames.add(c.getCategory_name());
            }

            ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                    android.R.layout.simple_spinner_item, categoryNames);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinnerCategories.setAdapter(adapter);
        });

        btnSaveCategory.setOnClickListener(v -> {
            String catName = etNewCategory.getText().toString();
            if (!TextUtils.isEmpty(catName)) {
                Category newCat = new Category(catName);
                noteViewModel.insertCategory(newCat);
                etNewCategory.setText(""); // Limpiar campo
                Toast.makeText(this, "Categoría creada", Toast.LENGTH_SHORT).show();
            }
        });

        btnSaveNote.setOnClickListener(v -> {
            saveNote();
        });
    }

    private void saveNote() {
        String title = etTitle.getText().toString();
        String content = etContent.getText().toString();

        if (TextUtils.isEmpty(title) || TextUtils.isEmpty(content)) {
            Toast.makeText(this, "Por favor llena todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }

        if (categoriesList.isEmpty()) {
            Toast.makeText(this, "Primero debes crear una categoría", Toast.LENGTH_LONG).show();
            return;
        }

        int position = spinnerCategories.getSelectedItemPosition();
        Category selectedCategory = categoriesList.get(position);
        int categoryId = selectedCategory.getCategory_id();

        Note note = new Note(title, content, System.currentTimeMillis(), categoryId);

        noteViewModel.insertNote(note);

        Toast.makeText(this, "Nota guardada", Toast.LENGTH_SHORT).show();
        finish();
    }
}