package com.iteso.pdm18_scrollabletabs.Controllers;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.iteso.pdm18_scrollabletabs.beans.Category;
import com.iteso.pdm18_scrollabletabs.beans.City;
import com.iteso.pdm18_scrollabletabs.beans.ItemProduct;
import com.iteso.pdm18_scrollabletabs.beans.Store;
import com.iteso.pdm18_scrollabletabs.tools.DataBaseHandler;

import java.util.ArrayList;

public class ItemProductControl {
    public static void addItemProduct(ItemProduct product, DataBaseHandler dh){
        long insertId = 0;
        SQLiteDatabase db = dh.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(DataBaseHandler.KEY_PRODUCT_NAME, product.getName());
        cv.put(DataBaseHandler.KEY_PRODUCT_DESCRIPTION, product.getDescription());
        cv.put(DataBaseHandler.KEY_PRODUCT_IMAGE, product.getImage());
        cv.put(DataBaseHandler.KEY_PRODUCT_CATEGORYID, product.getCategory().getId());
        insertId=db.insert(DataBaseHandler.TABLE_PRODUCT, null, cv);
        if(insertId>0){
            cv.put(DataBaseHandler.KEY_STORE_PRODUCT_PRODUCTID, insertId);
            cv.put(DataBaseHandler.KEY_STORE_PRODUCT_STOREID, product.getStore().getId());
            db.insert(DataBaseHandler.TABLE_STORE_PRODUCT, null, cv);
        }
        try{
            db.close();
        }catch (Exception e){}
        db = null;
        cv = null;
    }
    public static ArrayList<ItemProduct> getItemProductsByCategoryId(int categoryId, DataBaseHandler dh){
        ArrayList<ItemProduct> products = new ArrayList<ItemProduct>();
        String selectQuery = "SELECT p."+DataBaseHandler.KEY_PRODUCT_ID+", "
                +"p."+DataBaseHandler.KEY_PRODUCT_NAME+", "
                +"p."+DataBaseHandler.KEY_PRODUCT_DESCRIPTION+", "
                +"p."+DataBaseHandler.KEY_PRODUCT_IMAGE+", "
                +"c."+DataBaseHandler.KEY_CATEGORY_ID+", "
                +"c."+DataBaseHandler.KEY_CATEGORY_NAME+", "
                +"s."+DataBaseHandler.KEY_STORE_ID+", "
                +"s."+DataBaseHandler.KEY_STORE_NAME+", "
                +"s."+DataBaseHandler.KEY_STORE_PHONE+", "
                +"s."+DataBaseHandler.KEY_STORE_THUMBNAIL+", "
                +"s."+DataBaseHandler.KEY_STORE_LATITUDE+", "
                +"s."+DataBaseHandler.KEY_STORE_LONGITUDE+", "
                +"ci."+DataBaseHandler.KEY_CITY_ID+", "
                +"ci."+DataBaseHandler.KEY_CITY_NAME+" "
                +"FROM "+DataBaseHandler.TABLE_STORE_PRODUCT+" sp "
                +"INNER JOIN "+DataBaseHandler.TABLE_PRODUCT+" p "
                +"ON sp."+DataBaseHandler.KEY_STORE_PRODUCT_PRODUCTID+" = p."+DataBaseHandler.KEY_PRODUCT_ID+" "
                +"INNER JOIN "+DataBaseHandler.TABLE_STORE+" s "
                +"ON sp."+DataBaseHandler.KEY_STORE_PRODUCT_STOREID+" = s."+DataBaseHandler.KEY_STORE_ID+" "
                +"INNER JOIN "+DataBaseHandler.TABLE_CITY+" ci "
                +"ON s."+DataBaseHandler.KEY_STORE_CITYID+" = ci."+DataBaseHandler.KEY_CITY_ID+" "
                +"INNER JOIN "+DataBaseHandler.TABLE_CATEGORY+" c "
                +"ON p."+DataBaseHandler.KEY_PRODUCT_CATEGORYID+" = c."+DataBaseHandler.KEY_CATEGORY_ID+" "
                +"WHERE c."+DataBaseHandler.KEY_CATEGORY_ID+" = "+categoryId;
        SQLiteDatabase db = dh.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        Log.e("URI_APP", "In getItemProductByCategory");
        Boolean cursorStatus = cursor.moveToFirst();
        Log.e("URI_APP", cursorStatus.toString());
        while(cursorStatus){
            Log.e("URI_APP", "Something in the DB");
            ItemProduct itemProduct = new ItemProduct();
            itemProduct.setId(cursor.getInt(0));
            itemProduct.setName(cursor.getString(1));
            itemProduct.setDescription(cursor.getString(2));
            itemProduct.setImage(cursor.getInt(3));

            Category category = new Category();
            category.setId(cursor.getInt(4));
            category.setName(cursor.getString(5));
            itemProduct.setCategory(category);

            Store store = new Store();
            store.setId(cursor.getInt(6));
            store.setName(cursor.getString(7));
            store.setPhone(cursor.getString(8));
            store.setThumbnail(cursor.getInt(9));
            store.setLatitude(cursor.getDouble(10));
            store.setLongitude(cursor.getDouble(11));


            City city = new City();
            city.setId(cursor.getInt(12));
            city.setName(cursor.getString(13));

            store.setCity(city);
            itemProduct.setStore(store);

            products.add(itemProduct);
            cursorStatus = cursor.moveToNext();
        }
        try{
            cursor.close();
            db.close();
        }catch (Exception e){}
        db = null;
        cursor = null;
        return products;
    }
}
