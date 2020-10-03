package com.example.notebooks;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.notebooks.activities.note.NoteDetailActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    DrawerLayout drawerLayout;
    private FloatingActionButton fab;
    private Toolbar myChildToolbar;
    private ActionBarDrawerToggle toggle;
    private DrawerLayout rootView;
    private NavigationView navigationView;
    private ImageView img_setting;
    private View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // my_child_toolbar is defined in the layout file
        init();
        setSupportActionBar(myChildToolbar);

        detail();
        navigation();

        //click setting
        view = navigationView.getHeaderView(0);
        img_setting = view.findViewById(R.id.img_setting);
        img_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SettingActivity.class);
                startActivity(intent);
            }
        });


    }

    private void navigation() {

        toggle = new ActionBarDrawerToggle(this, drawerLayout, myChildToolbar, R.string.drawerOpen, R.string.drawerClose);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        myChildToolbar.setNavigationIcon(R.drawable.navigation);


    }

    private void init() {
        fab = (FloatingActionButton) findViewById(R.id.fab);
        rootView = (DrawerLayout) findViewById(R.id.rootView);
        drawerLayout = findViewById(R.id.rootView);
        myChildToolbar = (Toolbar) findViewById(R.id.main_toolbar);
        navigationView = findViewById(R.id.navigationView);


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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.nav_menu, menu);
        return true;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.all_note:
                Toast.makeText(MainActivity.this, "Profile", Toast.LENGTH_SHORT).show();
                break;
            case R.id.tag:
                Toast.makeText(MainActivity.this, "Contact", Toast.LENGTH_SHORT).show();
                break;
            case R.id.trash:
                Toast.makeText(MainActivity.this, "About us", Toast.LENGTH_SHORT).show();
                break;
            case R.id.profile:
                Toast.makeText(MainActivity.this, "Log out", Toast.LENGTH_SHORT).show();
                break;
        }
        return false;
    }
}
