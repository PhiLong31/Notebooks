package com.example.notebooks;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.notebooks.Adapter.AdapterTag;
import com.example.notebooks.model.Tag;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class NoteTagActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArrayList<Tag> arrayList;
    private AdapterTag adapter;

    private FirebaseAuth fbAuth = FirebaseAuth.getInstance();
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private String userId = null;

    private DocumentReference docRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_tag);
        // my_child_toolbar is defined in the layout file
        Toolbar myChildToolbar = (Toolbar) findViewById(R.id.tag_toolbar);
        setSupportActionBar(myChildToolbar);
        getSupportActionBar().setTitle("Tags");
        myChildToolbar.setNavigationIcon(R.drawable.left_arrow);
        // Get a support ActionBar corresponding to this toolbar
        ActionBar ab = getSupportActionBar();

        // Enable the Up button
        ab.setDisplayHomeAsUpEnabled(true);

        userId = fbAuth.getUid();
        init();
        SelectData();
    }

    private void init() {
        recyclerView = findViewById(R.id.recyclerviewtag);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        RecyclerView.LayoutManager layoutManager = linearLayoutManager;
        recyclerView.setLayoutManager(layoutManager);
    }

    public void SelectData() {
        String formatDocRef = String.format("%s/%s", userId, Utils.KEY_LIST);
        docRef = db.document(formatDocRef);
        arrayList = new ArrayList<>();
        arrayList.clear();
        docRef.collection(Utils.KEY_LIST_TAGS).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot documentSnapshot : task.getResult()) {
                        Tag tag = documentSnapshot.toObject(Tag.class);
                        tag.setTagId(documentSnapshot.getId());
                        arrayList.add(tag);
                        Log.d("tagName",tag.gettagname());
                    }
                    adapter = new AdapterTag(NoteTagActivity.this, arrayList);
                    recyclerView.setAdapter(adapter);
                } else {
                    Log.e("error", task.getException().getMessage());
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.nav_menu, menu);
        return true;
    }

}