package com.example.notebooks.activities.signin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.notebooks.R;
import com.example.notebooks.activities.MainActivity;
import com.example.notebooks.activities.signup.SignUpActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignInActivity extends AppCompatActivity {
    private TextView signUp;
    private Button btnSignUp;
    private TextInputEditText edEmail;
    private TextInputEditText edPass;

    private FirebaseAuth fbAuth = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        initView();

        signUp.setOnClickListener(view -> {
            Intent intent = new Intent(this, SignUpActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
        });

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = edEmail.getText().toString();
                String pass = edPass.getText().toString();
                if (email.equals("") || pass.equals(""))
                    Toast.makeText(getApplicationContext(),
                            "Can not be empty",
                            Toast.LENGTH_LONG).show();
                else if (!checkEmail(email))
                    Toast.makeText(getApplicationContext(),
                            "Invalid email",
                            Toast.LENGTH_LONG).show();
                else {
                    Task<AuthResult> task = signIn(email, pass);
                    task.addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(intent);
                                finish();
                            } else Toast.makeText(getApplicationContext(),
                                    "Login failed, please try again",
                                    Toast.LENGTH_LONG).show();
                        }
                    });
                }
            }
        });
    }


    private boolean checkEmail(String email) {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private Task<AuthResult> signIn(String email, String pass) {
        return fbAuth.signInWithEmailAndPassword(email, pass);
    }

    private void initView() {
        signUp = findViewById(R.id.sign_up);
        btnSignUp = findViewById(R.id.btnSignUp);
        edEmail = findViewById(R.id.edtEmailAddress);
        edPass = findViewById(R.id.password);
    }
}