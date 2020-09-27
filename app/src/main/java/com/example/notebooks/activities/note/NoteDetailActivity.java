package com.example.notebooks.activities.note;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.notebooks.R;
import com.example.notebooks.model.Note;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class NoteDetailActivity extends AppCompatActivity implements NoteActions {
    private EditText noteTitle;
    private EditText edtNote;

    private ActionBar ab;

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference colRef;

    private Date currentTime = Calendar.getInstance().getTime();
    @SuppressLint("SimpleDateFormat")
    private SimpleDateFormat formatter = new SimpleDateFormat("HH:mm dd/MM/yyyy");

    private Status status = Status.EDIT;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_detail);
        // my_child_toolbar is defined in the layout file
        Toolbar myChildToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(myChildToolbar);
        myChildToolbar.setNavigationIcon(R.drawable.left_arrow);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        // Get a support ActionBar corresponding to this toolbar
        // Enable the Up button
        ab = getSupportActionBar();
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


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                String title = noteTitle.getText().toString();
                String content = edtNote.getText().toString();
                if (!content.equals("") && status == Status.EDIT) {
                    Log.d("id", "" + item.getTitle());
                    readStatus();
                    addNote(title, content);
                } else {
                    Intent intent = getParentActivityIntent();
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                }
                return true;
            case R.id.ic_read:
                readStatus();
                return true;
            case R.id.ic_edit:
                editStatus();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void addNote(String title, String content) {
        Note note = new Note(title, content, formatter.format(currentTime), formatter.format(currentTime));
        colRef.add(note).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.e("NoteDetail", e.getMessage());
            }
        });
    }

    @Override
    public void removeNote(String documentId) {
        colRef.document(documentId).delete();
    }

    @Override
    public void updateNote(String title, String content) {

    }

    private void initView() {
        noteTitle = findViewById(R.id.note_title);
        edtNote = findViewById(R.id.edtNote);
    }

    private void readStatus(){
        status = Status.READ;
        noteTitle.setFocusable(false);
        noteTitle.setFocusableInTouchMode(false);
        edtNote.setFocusable(false);
        edtNote.setFocusableInTouchMode(false);
        hideSoftKeyboard(noteTitle);
        hideSoftKeyboard(edtNote);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void editStatus(){
        status = Status.EDIT;
        noteTitle.setFocusable(true);
        noteTitle.setFocusableInTouchMode(true);
        edtNote.setFocusable(true);
        edtNote.setFocusableInTouchMode(true);
        edtNote.requestFocus();
    }

    private void hideSoftKeyboard(EditText input) {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null && input != null) imm.hideSoftInputFromWindow(input.getWindowToken(), 0);
    }
}