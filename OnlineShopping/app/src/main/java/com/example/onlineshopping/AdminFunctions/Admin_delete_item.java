package com.example.onlineshopping.AdminFunctions;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.onlineshopping.DateBaseSql.CategoryItemsDataBase;
import com.example.onlineshopping.R;

import java.util.ArrayList;

public class Admin_delete_item extends AppCompatActivity {
    CategoryItemsDataBase admin ;
    ArrayList<String> Categories;
    AutoCompleteTextView Cat_naem;
    ArrayAdapter<String> adpterCategory;

    Button Delete;

    ArrayList<String> ITems;
    AutoCompleteTextView ITems_naem;
    ArrayAdapter<String> adpterItems;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_delete_item);



        admin = new CategoryItemsDataBase(getApplicationContext());

        Categories = new ArrayList<>();
        Cursor cursor = admin.fetchAllCategories();
        while (!cursor.isAfterLast()){
            Categories.add(cursor.getString(0));
            cursor.moveToNext();
        }

        ITems = new ArrayList<>();
        Cat_naem = findViewById(R.id.auto_complete_category);
        adpterCategory = new ArrayAdapter<String>(this,R.layout.list_category,Categories);
        Cat_naem.setAdapter(adpterCategory);
        Cat_naem.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String Category = adapterView.getItemAtPosition(i).toString();
                Toast.makeText(getApplicationContext(),"Category "+Category,Toast.LENGTH_SHORT).show();
                ITems.clear();
                Cursor cursor2 = admin.fetchAllItems();
                while (!cursor2.isAfterLast()){
                    if (Category.equals(cursor2.getString(6)))
                        ITems.add(cursor2.getString(0));
                    cursor2.moveToNext();
                }
            }
        });



        ITems_naem = findViewById(R.id.auto_complete_user);
        adpterItems = new ArrayAdapter<String>( this,R.layout.list_category, ITems);
        ITems_naem.setAdapter(adpterItems);
        ITems_naem.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String Item = adapterView.getItemAtPosition(i).toString();
                Toast.makeText(getApplicationContext(),"Item "+Item,Toast.LENGTH_SHORT).show();
            }
        });


        Delete = (Button)findViewById(R.id.delete_Itemes);
        Delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String item = ITems_naem.getText().toString();
                admin.Delete_item(item);
                Toast.makeText(getApplicationContext(),"Item "+item +"Is Deleted",Toast.LENGTH_SHORT).show();
            }
        });

        ImageView backpressed = (ImageView)findViewById(R.id.backpage);
        backpressed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Admin_delete_item.super.onBackPressed();
            }
        });
    }


}