package com.example.notebooks;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.notebooks.Adapter.AdapterNote;
import com.example.notebooks.Model.Model;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private FloatingActionButton fab;
    private RecyclerView recyclerView;
    private ArrayList<Model> arrayList;
    private AdapterNote adapter;
    FirebaseDatabase database;
    DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        detail();
        SelectData();
            }
    private void init() {
        fab = (FloatingActionButton) findViewById(R.id.fab);
        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        RecyclerView.LayoutManager layoutManager = linearLayoutManager;
        recyclerView.setLayoutManager(layoutManager);
    }

    private void detail() {
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, NoteDetailActivity.class);
                startActivity(intent);

            }
        });
    }
    public void SelectData(){
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("note");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                arrayList = new ArrayList<>();
                arrayList.clear();
                Model model = new Model();
                for(DataSnapshot data: dataSnapshot.getChildren()){
                    model = data.getValue(Model.class);
                    model.setId(data.getKey());
                    arrayList.add(model);
                    Log.d("id","onDataChange: "+model.getId());
                    Log.d("title","onDataChange: "+model.getTitle());
                }

                adapter= new AdapterNote(MainActivity.this,arrayList);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

}
