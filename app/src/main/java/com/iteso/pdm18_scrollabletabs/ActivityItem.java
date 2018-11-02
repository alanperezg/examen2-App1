package com.iteso.pdm18_scrollabletabs;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.iteso.pdm18_scrollabletabs.Controllers.CategoryControl;
import com.iteso.pdm18_scrollabletabs.Controllers.ItemProductControl;
import com.iteso.pdm18_scrollabletabs.Controllers.StoreControl;
import com.iteso.pdm18_scrollabletabs.beans.Category;
import com.iteso.pdm18_scrollabletabs.beans.ItemProduct;
import com.iteso.pdm18_scrollabletabs.beans.Store;
import com.iteso.pdm18_scrollabletabs.tools.DataBaseHandler;

import java.util.ArrayList;
import java.util.List;

public class ActivityItem extends AppCompatActivity {
    Spinner imageSpinner;
    EditText nombreText;
    Spinner categoriasSpinner;
    Spinner tiendasSpinner;
    Button guardarBtn;
    ArrayList<Category> categories;
    ArrayList<Store> stores;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);
        imageSpinner = findViewById(R.id.activity_item_imagenes);
        nombreText = findViewById(R.id.activity_item_nombre);
        categoriasSpinner = findViewById(R.id.activity_item_categorias);
        tiendasSpinner = findViewById(R.id.activity_item_tiendas);
        guardarBtn = findViewById(R.id.activity_item_guardarBtn);

        DataBaseHandler dh = DataBaseHandler.getInstance(getApplicationContext());
        categories = CategoryControl.getCategories(dh);
        List<String> categoryNames = new ArrayList<String>();
        for(Category category: categories){
            categoryNames.add(category.getName());
        }
        ArrayAdapter<String> categoryAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categoryNames);
        categoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categoriasSpinner.setAdapter(categoryAdapter);

        stores = StoreControl.getStores(dh);
        List<String> storeNames = new ArrayList<String>();
        for(Store store: stores){
            storeNames.add(store.getName());
        }
        ArrayAdapter<String> storeAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, storeNames);
        storeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        tiendasSpinner.setAdapter(storeAdapter);

        guardarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int idCategory = 0;
                int idStore = 0;
                for(Category category : categories){
                    if(category.getName()==categoriasSpinner.getSelectedItem().toString()){
                        idCategory=category.getId();
                    }
                }
                for(Store store : stores){
                    if(store.getName()==tiendasSpinner.getSelectedItem().toString()){
                        idStore=store.getId();
                    }
                }
                ItemProduct product = new ItemProduct();
                product.setName(nombreText.getText().toString());
                product.setImage(imageSpinner.getSelectedItemPosition());
                Category category = new Category();
                category.setId(idCategory);
                product.setCategory(category);
                Store store = new Store();
                store.setId(idStore);
                product.setStore(store);
                DataBaseHandler dh = DataBaseHandler.getInstance(getApplicationContext());
                ItemProductControl.addItemProduct(product, dh);
                finish();

            }
        });
    }
}