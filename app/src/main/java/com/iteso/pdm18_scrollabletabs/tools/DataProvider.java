package com.iteso.pdm18_scrollabletabs.tools;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.iteso.pdm18_scrollabletabs.Controllers.ItemProductControl;
import com.iteso.pdm18_scrollabletabs.beans.ItemProduct;

import java.util.ArrayList;

public class DataProvider extends ContentProvider {
    static final String PROVIDER_NAME = "com.iteso.pdm18_scrollabletabs";
    static final int PRODUCTS_BY_CATEGORY = 1;

    private static final UriMatcher uriMatcher;
    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(PROVIDER_NAME, "products/category/#", PRODUCTS_BY_CATEGORY);
    }

    @Override
    public boolean onCreate() {
        DataBaseHandler.getInstance(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query( Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        Cursor cursor = null;
        Log.e("URI_APP", uri.toString());
        switch (uriMatcher.match(uri)){
           case PRODUCTS_BY_CATEGORY:
               Log.e("URI_APP", uri.toString());
               int categoryId;
               try {
                   categoryId = Integer.parseInt(uri.getLastPathSegment());
               }catch (Exception e){
                   break;
               }
               String[] cursorColumns = new String[]{"id", "name", "description", "tienda", "ciudad", "telefono"};
               cursor = new MatrixCursor(cursorColumns);
               ArrayList<ItemProduct> products = ItemProductControl.getItemProductsByCategoryId(
                       categoryId, DataBaseHandler.getInstance(getContext()));
               Log.e("URI_APP", products.size()+"");
               for(ItemProduct product:products) {
                ((MatrixCursor) cursor).newRow().add("id", product.getId())
                        .add("name", product.getName())
                        .add("description", product.getDescription())
                        .add("tienda", product.getStore().getName())
                        .add("ciudad", product.getStore().getCity().getName())
                        .add("telefono", product.getStore().getPhone());
                Log.e("Product ID", product.getName());
               }

       }
       return cursor;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String where, @Nullable String[] whereArgs) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String where, @Nullable String[] whereArgs) {
        return 0;
    }
}
