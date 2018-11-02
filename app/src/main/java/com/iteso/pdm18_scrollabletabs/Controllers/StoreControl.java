package com.iteso.pdm18_scrollabletabs.Controllers;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.iteso.pdm18_scrollabletabs.beans.City;
import com.iteso.pdm18_scrollabletabs.beans.Store;
import com.iteso.pdm18_scrollabletabs.tools.DataBaseHandler;

import java.util.ArrayList;

public class StoreControl {
    public static void addStore(Store store, DataBaseHandler dh){
        SQLiteDatabase db = dh.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(DataBaseHandler.KEY_STORE_NAME, store.getName());
        cv.put(DataBaseHandler.KEY_STORE_PHONE, store.getPhone());
        cv.put(DataBaseHandler.KEY_STORE_CITYID, store.getCity().getId());
        cv.put(DataBaseHandler.KEY_STORE_THUMBNAIL, store.getThumbnail());
        cv.put(DataBaseHandler.KEY_STORE_LATITUDE, store.getLatitude());
        cv.put(DataBaseHandler.KEY_STORE_LONGITUDE, store.getLongitude());
        db.insert(DataBaseHandler.TABLE_STORE, null, cv);
        try{
            db.close();
        }catch (Exception e){}
        db = null;
        cv = null;
    }
    public static ArrayList<Store> getStores(DataBaseHandler dh){
        ArrayList<Store> stores = new ArrayList<Store>();
        String selectQuery = "SELECT s."+DataBaseHandler.KEY_STORE_ID+", "
                +"s."+DataBaseHandler.KEY_STORE_NAME+", "
                +"s."+DataBaseHandler.KEY_STORE_PHONE+", "
                +"s."+DataBaseHandler.KEY_STORE_THUMBNAIL+", "
                +"s."+DataBaseHandler.KEY_STORE_LATITUDE+", "
                +"s."+DataBaseHandler.KEY_STORE_LONGITUDE+", "
                +"c."+DataBaseHandler.KEY_CITY_ID+", "
                +"c."+DataBaseHandler.KEY_CITY_NAME+" FROM "+DataBaseHandler.TABLE_STORE+" s"
                +" INNER JOIN "+DataBaseHandler.TABLE_CITY+" c ON "
                +"s."+DataBaseHandler.KEY_STORE_CITYID+" = c."+DataBaseHandler.KEY_CITY_ID;
        SQLiteDatabase db = dh.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        Boolean cursorStatus = cursor.moveToFirst();
        while(cursorStatus){
            Store store = new Store();
            store.setId(cursor.getInt(0));
            store.setName(cursor.getString(1));
            store.setPhone(cursor.getString(2));
            store.setThumbnail(cursor.getInt(3));
            store.setLatitude(cursor.getDouble(4));
            store.setLongitude(cursor.getDouble(5));
            City city = new City();
            city.setId(cursor.getInt(6));
            city.setName(cursor.getString(7));
            store.setCity(city);
            stores.add(store);
            cursorStatus = cursor.moveToNext();
        }
        try{
            cursor.close();
            db.close();
        }catch (Exception e){}
        db = null;
        cursor = null;
        return stores;
    }

    public static Store getStoreById(int id, DataBaseHandler dh){
        Store store = null;
        String selectQuery = "SELECT s."+DataBaseHandler.KEY_STORE_ID+", "
                +"s."+DataBaseHandler.KEY_STORE_NAME+", "
                +"s."+DataBaseHandler.KEY_STORE_PHONE+", "
                +"s."+DataBaseHandler.KEY_STORE_THUMBNAIL+", "
                +"s."+DataBaseHandler.KEY_STORE_LATITUDE+", "
                +"s."+DataBaseHandler.KEY_STORE_LONGITUDE+", "
                +"c."+DataBaseHandler.KEY_CITY_ID+", "
                +"c."+DataBaseHandler.KEY_CITY_NAME+" FROM "+DataBaseHandler.TABLE_STORE+" s"
                +" INNER JOIN "+DataBaseHandler.TABLE_CITY+" c ON "
                +"s."+DataBaseHandler.KEY_STORE_CITYID+" = c."+DataBaseHandler.KEY_CITY_ID
                +" WHERE "+DataBaseHandler.KEY_STORE_ID+" = "+id;
        SQLiteDatabase db = dh.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
       if(cursor.moveToFirst()){
            store = new Store();
            store.setId(cursor.getInt(0));
            store.setName(cursor.getString(1));
            store.setPhone(cursor.getString(2));
            store.setThumbnail(cursor.getInt(3));
            store.setLatitude(cursor.getDouble(4));
            store.setLongitude(cursor.getDouble(5));
            City city = new City();
            city.setId(cursor.getInt(6));
            city.setName(cursor.getString(7));
            store.setCity(city);
        }
        try{
            cursor.close();
            db.close();
        }catch (Exception e){}
        db = null;
        cursor = null;
        return store;
    }
}
