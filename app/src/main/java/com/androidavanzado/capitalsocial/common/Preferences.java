package com.androidavanzado.capitalsocial.common;

import android.content.SharedPreferences;

import androidx.appcompat.app.AppCompatActivity;

public class Preferences extends AppCompatActivity {

    public void setLoginPreferences(SharedPreferences sharedPreferences, String user, String pass, int id)
    {
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putInt("Id", id);
        editor.putString("User", user);
        editor.putString("Pass", pass);

        editor.apply();
    }

}
