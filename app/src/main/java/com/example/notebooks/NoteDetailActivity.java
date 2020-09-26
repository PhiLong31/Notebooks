package com.example.notebooks;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.notebooks.model.Note;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class NoteDetailActivity extends AppCompatActivity implements NoteFeatures{
    private EditText noteTitle;
    private EditText edtNote;

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference colRef;

    private Date currentTime = Calendar.getInstance().getTime();
    private SimpleDateFormat formatter = new SimpleDateFormat("HH:mm dd/MM/yyyy");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_detail);
        // my_child_toolbar is defined in the layout file
        Toolbar myChildToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(myChildToolbar);
        myChildToolbar.setNavigationIcon(R.drawable.left_arrow);
        // Get a support ActionBar corresponding to this toolbar
        ActionBar ab = getSupportActionBar();
        // Enable the Up button
        ab.setDisplayHomeAsUpEnabled(true);

        colRef = db.collection("user1");

        initView();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_detail, menu);
        return true;
    }

    private void initView() {
        noteTitle = findViewById(R.id.note_title);
        edtNote = findViewById(R.id.edtNote);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        String title = noteTitle.getText().toString();
        String content = edtNote.getText().toString();
        addNote(title, content);
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void addNote(String title, String content) {
        if(!content.equals("")){
            Note note = new Note(title ,content, formatter.format(currentTime), formatter.format(currentTime));
            colRef.add(note).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Log.e("NoteDetail", e.getMessage());
                }
            });
        }
    }

    @Override
    public void removeNote(String idNote) {

    }

    @Override
    public void updateNote(String title, String content) {

    }

}