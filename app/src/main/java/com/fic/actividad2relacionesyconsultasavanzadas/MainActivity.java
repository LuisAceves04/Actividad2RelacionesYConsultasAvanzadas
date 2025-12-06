package com.fic.actividad2relacionesyconsultasavanzadas;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private NoteViewModel noteViewModel;
    private RecyclerView recyclerView;
    private CategoryAdapter adapter;
    private EditText etSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        adapter = new CategoryAdapter();
        recyclerView.setAdapter(adapter);

        noteViewModel = new ViewModelProvider(this).get(NoteViewModel.class);

        noteViewModel.getAllNotesGrouped().observe(this, new Observer<List<CategoryWithNotes>>() {
            @Override
            public void onChanged(List<CategoryWithNotes> categories) {
                adapter.setCategories(categories);

            }
        });

        FloatingActionButton fab = findViewById(R.id.fabAdd);
        fab.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, AddNoteActivity.class);
            startActivity(intent);
        });

        etSearch = findViewById(R.id.etSearch);
        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });
    }
}