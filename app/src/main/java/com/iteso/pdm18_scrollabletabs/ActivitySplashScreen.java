package com.iteso.pdm18_scrollabletabs;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.iteso.pdm18_scrollabletabs.beans.User;
import com.iteso.pdm18_scrollabletabs.tools.DataBaseHandler;

import java.util.Timer;
import java.util.TimerTask;

public class ActivitySplashScreen extends AppCompatActivity {
    public static final String MY_PREFERENCES = "com.iteso.sesion15.PREFERENCES";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        TimerTask timert = new TimerTask() {
            @Override
            public void run() {
                User user = loadPreferences();
                if(user.isLogged()){
                    /*Intent intent = new Intent(ActivitySplashScreen.this, ActivityMain.class);
                    startActivity(intent);
                    finish();*/
                }else{
                    /*Intent intent = new Intent(ActivitySplashScreen.this, ActivityLogin.class);
                    startActivity(intent);
                    finish();*/
                }
            }
        };
        Timer timer = new Timer();
        timer.schedule(timert, 2000);

        DataBaseHandler.getInstance(this);

    }
    public User loadPreferences(){
        SharedPreferences sp = getSharedPreferences(MY_PREFERENCES, MODE_PRIVATE);
        User user = new User();
        user.setUser(sp.getString("name","UNKNOWN"));
        user.setPassword(sp.getString("password", "1234"));
        user.setLogged(sp.getBoolean("logged", false));
        return user;
    }
}
