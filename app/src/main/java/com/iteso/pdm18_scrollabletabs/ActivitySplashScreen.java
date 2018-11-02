package com.iteso.pdm18_scrollabletabs;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.iteso.pdm18_scrollabletabs.Controllers.ItemProductControl;
import com.iteso.pdm18_scrollabletabs.Controllers.StoreControl;
import com.iteso.pdm18_scrollabletabs.beans.City;
import com.iteso.pdm18_scrollabletabs.beans.ItemProduct;
import com.iteso.pdm18_scrollabletabs.beans.Store;
import com.iteso.pdm18_scrollabletabs.beans.User;
import com.iteso.pdm18_scrollabletabs.tools.DataBaseHandler;

import java.util.ArrayList;
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
                DataBaseHandler dh = DataBaseHandler.getInstance(getApplicationContext());
                ArrayList<Store> stores = StoreControl.getStores(dh);
                if(stores.size()==0){
                    Store store = new Store();
                    store.setName("BestBuy Puerto Vallarta");
                    store.setPhone("3222317467");
                    store.setThumbnail(1);
                    store.setLongitude(1121.1211);
                    store.setLatitude(2212.12121);
                    City city = new City();
                    city.setId(2);
                    store.setCity(city);
                    StoreControl.addStore(store, dh);

                    Store store2 = new Store();
                    store2.setName("BestBuy Guadalajara Andares");
                    store2.setPhone("40405541");
                    store2.setThumbnail(1);
                    store2.setLongitude(1121.1211);
                    store2.setLatitude(2212.12121);
                    City city2 = new City();
                    city2.setId(1);
                    store2.setCity(city);
                    StoreControl.addStore(store2, dh);

                    Store store3 = new Store();
                    store3.setName("BestBuy Guadalajara Gran Plaza");
                    store3.setPhone("33283321");
                    store3.setThumbnail(1);
                    store3.setLongitude(1121.1211);
                    store3.setLatitude(2212.12121);
                    City city3 = new City();
                    city3.setId(1);
                    store3.setCity(city);
                    StoreControl.addStore(store3, dh);
                }

                User user = loadPreferences();
                if(user.isLogged()){
                    Intent intent = new Intent(ActivitySplashScreen.this, ActivityMain.class);
                    startActivity(intent);
                    finish();
                }else{
                    Intent intent = new Intent(ActivitySplashScreen.this, ActivityLogin.class);
                    startActivity(intent);
                    finish();
                }
            }
        };
        Timer timer = new Timer();
        timer.schedule(timert, 2000);
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
