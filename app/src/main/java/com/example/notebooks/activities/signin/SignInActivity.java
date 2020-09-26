package com.example.notebooks.activities.signin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.notebooks.R;
import com.example.notebooks.activities.signup.SignUpActivity;

public class SignInActivity extends AppCompatActivity {
    private TextView signUp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        initView();
        signUp.setOnClickListener(view ->{
            Intent intent = new Intent(this, SignUpActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
        });
    }

    private void initView(){
        signUp = findViewById(R.id.sign_up);
    }
}