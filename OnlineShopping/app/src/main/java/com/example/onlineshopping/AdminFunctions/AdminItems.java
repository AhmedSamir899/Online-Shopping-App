package com.example.onlineshopping.AdminFunctions;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.onlineshopping.DateBaseSql.CategoryItemsDataBase;
import com.example.onlineshopping.R;

import java.util.ArrayList;

public class AdminItems extends AppCompatActivity {

    CategoryItemsDataBase admin ;
    Button Add;

    EditText itemname,itemdescription,item_price,item_count,item_barcode;

    ArrayList<String> Categories;
    AutoCompleteTextView Cat_naem;
    ArrayAdapter<String> adpterCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_items);


        admin = new CategoryItemsDataBase(getApplicationContext());

        Categories = new ArrayList<>();
        Cursor cursor = admin.fetchAllCategories();
        while (!cursor.isAfterLast()){
            Categories.add(cursor.getString(0));
            cursor.moveToNext();
        }

        Cat_naem = findViewById(R.id.auto_complete_category);
        adpterCategory = new ArrayAdapter<String>(this,R.layout.list_category,Categories);
        Cat_naem.setAdapter(adpterCategory);
        Cat_naem.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String Category = adapterView.getItemAtPosition(i).toString();
                Toast.makeText(getApplicationContext(),"Category"+Category,Toast.LENGTH_SHORT).show();
            }
        });



        itemname = (EditText)findViewById(R.id.item_admin_name);
        itemdescription = (EditText)findViewById(R.id.Item_Admin_descriptionS);
        item_price = (EditText)findViewById(R.id.Item_Admin_Price);
        item_count = (EditText)findViewById(R.id.Item_Admin_Count);
        item_barcode = (EditText)findViewById(R.id.Item_Admin_barcode);
        Add = (Button)findViewById(R.id.add_Itemes);
        Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = itemname.getText().toString();
                String desc = itemdescription.getText().toString();
                String category = Cat_naem.getText().toString();
                int price = Integer.parseInt(item_price.getText().toString());
                int count = Integer.parseInt(item_count.getText().toString());
                String barcode = item_barcode.getText().toString();
                admin.CreateItem(name,desc,price,R.drawable.admin_itemms, R.drawable.admin_item_bakce,count,category,barcode);
                Toast.makeText(getApplicationContext(),"Item"+name+" is Added Successfully",Toast.LENGTH_SHORT).show();
            }
        });



        ImageView backpressed = (ImageView)findViewById(R.id.backpage);
        backpressed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AdminItems.super.onBackPressed();
            }
        });

    }

}