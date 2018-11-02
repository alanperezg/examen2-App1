package com.iteso.pdm18_scrollabletabs.Controllers;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.iteso.pdm18_scrollabletabs.beans.Category;
import com.iteso.pdm18_scrollabletabs.tools.DataBaseHandler;

import java.util.ArrayList;

public class CategoryControl {
    public static ArrayList<Category> getCategories(DataBaseHandler dh){
        ArrayList<Category> categories = new ArrayList<Category>();
        String selectQuery = "SELECT "+DataBaseHandler.KEY_CATEGORY_ID+", "
                +DataBaseHandler.KEY_CATEGORY_NAME
                +" FROM "+DataBaseHandler.TABLE_CATEGORY;
        SQLiteDatabase db = dh.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        Boolean cursorStatus = cursor.moveToFirst();
        while(cursorStatus){
            Category category = new Category();
            category.setId(cursor.getInt(0));
            category.setName(cursor.getString(1));
            categories.add(category);
            cursorStatus = cursor.moveToNext();
        }
        try{
            cursor.close();
            db.close();
        }catch (Exception e){}
        db = null;
        cursor = null;
        return categories;
    }
}
