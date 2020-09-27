package com.example.notebooks;

import android.util.Patterns;

public class Utils {

    public static boolean checkEmail(String email) {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
}
