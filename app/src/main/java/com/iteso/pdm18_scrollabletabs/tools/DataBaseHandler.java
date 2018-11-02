package com.iteso.pdm18_scrollabletabs.tools;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseHandler extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "MyProducts.db";
    private static final int DATABASE_VERSION = 1;
    //DATABASE TABLES
    public static final String TABLE_CITY = "City";
    public static final String TABLE_CATEGORY = "Category";
    public static final String TABLE_STORE = "Store";
    public static final String TABLE_PRODUCT = "Product";
    public static final String TABLE_STORE_PRODUCT = "Store_Product";
    //CITY TABLE
    public static final String KEY_CITY_ID = "id";
    public static final String KEY_CITY_NAME = "name";
    //CATEGORY TABLE
    public static final String KEY_CATEGORY_ID = "id";
    public static final String KEY_CATEGORY_NAME = "name";
    //STORE TABLE
    public static final String KEY_STORE_ID = "id";
    public static final String KEY_STORE_NAME = "name";
    public static final String KEY_STORE_PHONE = "phone";
    public static final String KEY_STORE_CITYID = "City_id";
    public static final String KEY_STORE_THUMBNAIL = "thumbnail";
    public static final String KEY_STORE_LATITUDE = "latitude";
    public static final String KEY_STORE_LONGITUDE = "longitude";
    //PRODUCT TABLE
    public static final String KEY_PRODUCT_ID = "id";
    public static final String KEY_PRODUCT_NAME = "name";
    public static final String KEY_PRODUCT_DESCRIPTION = "description";
    public static final String KEY_PRODUCT_IMAGE = "image";
    public static final String KEY_PRODUCT_CATEGORYID = "Category_id";
    //STORE-PRODUCT TABLE
    public static final String KEY_STORE_PRODUCT_ID = "id";
    public static final String KEY_STORE_PRODUCT_PRODUCTID = "Product_id";
    public static final String KEY_STORE_PRODUCT_STOREID = "Store_id";


    private static DataBaseHandler dataBaseHandler;

    private DataBaseHandler(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public static DataBaseHandler getInstance(Context context){
        if(dataBaseHandler == null){
            return new DataBaseHandler(context);
        }else{
            return dataBaseHandler;
        }
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CITY_TABLE = "CREATE TABLE "+TABLE_CITY+"("
                +KEY_CITY_ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"
                +KEY_CITY_NAME+" TEXT)";
        db.execSQL(CREATE_CITY_TABLE);

        String CREATE_CATEGORY_TABLE = "CREATE TABLE "+TABLE_CATEGORY+"("
                +KEY_CATEGORY_ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"
                +KEY_CATEGORY_NAME+" TEXT)";
        db.execSQL(CREATE_CATEGORY_TABLE);

        String CREATE_STORE_TABLE = "CREATE TABLE "+TABLE_STORE+"("
                +KEY_STORE_ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"
                +KEY_STORE_NAME+" TEXT,"
                +KEY_STORE_PHONE+" TEXT,"
                +KEY_STORE_CITYID+" INTEGER,"
                +KEY_STORE_THUMBNAIL+" INTEGER,"
                +KEY_STORE_LATITUDE+ " REAL,"
                +KEY_STORE_LONGITUDE+" REAL,"
                +"FOREIGN KEY ("+KEY_STORE_CITYID+") REFERENCES "+TABLE_CITY+"("+KEY_CITY_ID+"))";
        db.execSQL(CREATE_STORE_TABLE);

        String CREATE_PRODUCT_TABLE = "CREATE TABLE "+TABLE_PRODUCT+"("
                +KEY_PRODUCT_ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"
                +KEY_PRODUCT_NAME+" TEXT,"
                +KEY_PRODUCT_DESCRIPTION+" TEXT,"
                +KEY_PRODUCT_IMAGE+ " INTEGER,"
                +KEY_PRODUCT_CATEGORYID+" INTEGER,"
                +"FOREIGN KEY("+KEY_PRODUCT_CATEGORYID+") REFERENCES "+TABLE_CATEGORY+"("+KEY_CATEGORY_ID+"))";
        db.execSQL(CREATE_PRODUCT_TABLE);

        String CREATE_STORE_PRODUCT_TABLE = "CREATE TABLE "+TABLE_STORE_PRODUCT+"("
                +KEY_STORE_PRODUCT_ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"
                +KEY_STORE_PRODUCT_PRODUCTID+" INTEGER,"
                +KEY_STORE_PRODUCT_STOREID+" INTEGER,"
                +"FOREIGN KEY("+KEY_STORE_PRODUCT_PRODUCTID+") REFERENCES "+TABLE_PRODUCT+"("+KEY_PRODUCT_ID+"),"
                +"FOREIGN KEY("+KEY_STORE_PRODUCT_STOREID+") REFERENCES "+TABLE_STORE+"("+KEY_STORE_ID+"))";
        db.execSQL(CREATE_STORE_PRODUCT_TABLE);

        String INSERT_CATEGORY_TECHNOLOGY = "INSERT INTO "+TABLE_CATEGORY+" ("+KEY_CATEGORY_NAME+")"
                +" VALUES ('Technology')";
        db.execSQL(INSERT_CATEGORY_TECHNOLOGY);

        String INSERT_CATEGORY_HOME = "INSERT INTO "+TABLE_CATEGORY+" ("+KEY_CATEGORY_NAME+")"
                +" VALUES ('Home')";
        db.execSQL(INSERT_CATEGORY_HOME);

        String INSERT_CATEGORY_ELECTRONICS = "INSERT INTO "+TABLE_CATEGORY+" ("+KEY_CATEGORY_NAME+")"
                +" VALUES ('Electronics')";
        db.execSQL(INSERT_CATEGORY_ELECTRONICS);

        String INSERT_CITY_GUADALAJARA = "INSERT INTO "+TABLE_CITY+" ("+KEY_CITY_NAME+")"
                +" VALUES ('Guadalajara')";
        db.execSQL(INSERT_CITY_GUADALAJARA);

        String INSERT_CITY_PVR = "INSERT INTO "+TABLE_CITY+" ("+KEY_CITY_NAME+")"
                +" VALUES ('Puerto Vallarta')";
        db.execSQL(INSERT_CITY_PVR);

        String INSERT_STORE = "INSERT INTO "+TABLE_STORE+" ("+KEY_STORE_NAME+", "
                +KEY_STORE_PHONE+", "
                +KEY_STORE_CITYID+", "
                +KEY_STORE_THUMBNAIL+", "
                +KEY_STORE_LATITUDE+", "
                +KEY_STORE_LONGITUDE+") VALUES('Tienda 1', '3222317467', 1, 0, 1.0, 1.0)";
        db.execSQL(INSERT_STORE);

        String INSERT_PRODUCT1 = "INSERT INTO "+TABLE_PRODUCT+" ("+KEY_PRODUCT_NAME+", "
                +KEY_PRODUCT_DESCRIPTION+", "
                +KEY_PRODUCT_IMAGE+", "
                +KEY_PRODUCT_CATEGORYID
                +") VALUES ('Producto 1', 'Producto 1', 1, 1)";
        db.execSQL(INSERT_PRODUCT1);

        String INSERT_PRODUCT2 = "INSERT INTO "+TABLE_PRODUCT+" ("+KEY_PRODUCT_NAME+", "
                +KEY_PRODUCT_DESCRIPTION+", "
                +KEY_PRODUCT_IMAGE+", "
                +KEY_PRODUCT_CATEGORYID
                +") VALUES ('Producto 2', 'Producto 2', 1, 1)";
        db.execSQL(INSERT_PRODUCT2);

        String INSERT_PRODUCT3 = "INSERT INTO "+TABLE_PRODUCT+" ("+KEY_PRODUCT_NAME+", "
                +KEY_PRODUCT_DESCRIPTION+", "
                +KEY_PRODUCT_IMAGE+", "
                +KEY_PRODUCT_CATEGORYID
                +") VALUES ('Producto 3', 'Producto 3', 1, 2)";
        db.execSQL(INSERT_PRODUCT3);

        String INSERT_STORE_PRODUCT1 = "INSERT INTO "+TABLE_STORE_PRODUCT+" ("+KEY_STORE_PRODUCT_PRODUCTID+", "
                +KEY_STORE_PRODUCT_STOREID
                +") VALUES(1,1)";
        db.execSQL(INSERT_STORE_PRODUCT1);

        String INSERT_STORE_PRODUCT2 = "INSERT INTO "+TABLE_STORE_PRODUCT+" ("+KEY_STORE_PRODUCT_PRODUCTID+", "
                +KEY_STORE_PRODUCT_STOREID
                +") VALUES(2,1)";
        db.execSQL(INSERT_STORE_PRODUCT2);

        String INSERT_STORE_PRODUCT3 = "INSERT INTO "+TABLE_STORE_PRODUCT+" ("+KEY_STORE_PRODUCT_PRODUCTID+", "
                +KEY_STORE_PRODUCT_STOREID
                +") VALUES(3,1)";
        db.execSQL(INSERT_STORE_PRODUCT3);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
       //No hay nuevas versiones
    }
}