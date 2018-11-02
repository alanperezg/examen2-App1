package com.iteso.pdm18_scrollabletabs;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ActivityLogin extends AppCompatActivity {
    EditText user, password;
    Button loginBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        user = findViewById(R.id.activity_login_user);
        password = findViewById(R.id.activity_login_password);
        loginBtn = findViewById(R.id.activity_login_loginBtn);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*savePreferences();
                Intent intent = new Intent(ActivityLogin.this, ActivityMain.class);
                startActivity(intent);
                finish();*/
            }
        });
    }
    private void savePreferences(){
        SharedPreferences sp = getSharedPreferences(ActivitySplashScreen.MY_PREFERENCES, MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("user", user.getText().toString());
        editor.putString("password", password.getText().toString());
        editor.putBoolean("logged", true);
        editor.apply();
    }
}
