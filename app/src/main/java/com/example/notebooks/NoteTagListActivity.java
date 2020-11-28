package com.example.notebooks;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.os.Bundle;

import com.example.notebooks.adapters.AdapterTagList;
import com.example.notebooks.model.Note;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class NoteTagListActivity extends AppCompatActivity {
    private ArrayList<Note> notes;
    private RecyclerView recyclerView;
    private int numberCol = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_tag_list);
        notes = getIntent().getParcelableArrayListExtra("notes");
        numberCol = notes.size() > 1 ? 2 : 1;
        init();
    }

    private void init() {
        recyclerView = findViewById(R.id.recyclerView_list_tags);
        AdapterTagList adapterTagList = new AdapterTagList(this, notes);
        recyclerView.setAdapter(adapterTagList);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(numberCol, LinearLayoutManager.VERTICAL));
    }
}