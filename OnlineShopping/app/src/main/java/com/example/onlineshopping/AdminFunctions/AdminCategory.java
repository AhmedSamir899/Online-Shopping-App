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

public class AdminCategory extends AppCompatActivity {

    CategoryItemsDataBase admin ;
    Button Add_category,delete_category,Update_category;
    EditText cat_name_To_add , cat_name_to_delete;
    EditText New_name;

    ArrayList<String> Categories;
    AutoCompleteTextView Old_name;
    ArrayAdapter<String> adpterCategory;

    AutoCompleteTextView Old_name22;
    ArrayAdapter<String> adpterCategory22;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_category);


        admin = new CategoryItemsDataBase(getApplicationContext());
        cat_name_To_add = (EditText)findViewById(R.id.Category_name);

        Add_category = (Button)findViewById(R.id.add_Category);
        Add_category.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name_category = cat_name_To_add.getText().toString();
                admin.CreateCategory(name_category, R.drawable.admin_itemms, R.drawable.admin_item_bakce);
                Toast.makeText(AdminCategory.this,name_category+" is Added succesfully",Toast.LENGTH_SHORT).show();
            }
        });


        Categories = new ArrayList<>();
        Cursor cursor = admin.fetchAllCategories();
        while (!cursor.isAfterLast()){
            Categories.add(cursor.getString(0));
            cursor.moveToNext();
        }


        Old_name22 = findViewById(R.id.auto_complete_category22);
        adpterCategory22 = new ArrayAdapter<String>(this,R.layout.list_category,Categories);
        Old_name22.setAdapter(adpterCategory22);
        Old_name22.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String Category = adapterView.getItemAtPosition(i).toString();
                Toast.makeText(getApplicationContext(),"Category"+Category,Toast.LENGTH_SHORT).show();
            }
        });

        delete_category = (Button)findViewById(R.id.delete_Cate);
        delete_category.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name_category = Old_name22.getText().toString();
                admin.Delete_category(name_category);
                Toast.makeText(AdminCategory.this, name_category+" is Deleted succesfully",Toast.LENGTH_SHORT).show();
            }
        });



        Old_name = findViewById(R.id.auto_complete_category);
        adpterCategory = new ArrayAdapter<String>(this,R.layout.list_category,Categories);
        Old_name.setAdapter(adpterCategory);
        Old_name.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String Category = adapterView.getItemAtPosition(i).toString();
                Toast.makeText(getApplicationContext(),"Category"+Category,Toast.LENGTH_SHORT).show();
            }
        });


        New_name = (EditText)findViewById(R.id.New_Name);
        Update_category = (Button)findViewById(R.id.Update_cate);
        Update_category.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String old = Old_name.getText().toString();
                String New = New_name.getText().toString();
                int id = admin.get_category_id(old);
                Toast.makeText(AdminCategory.this,old +" is updated to "+ New +" succesfully",Toast.LENGTH_SHORT).show();

                int icon_ = 0;
                int pic_ = 0;
                Cursor cursor2 = admin.fetchAllCategories();
                while (!cursor2.isAfterLast()){
                    if (cursor2.getString(0).equals(old))
                    {
                        icon_ = cursor2.getInt(1);
                        pic_ = cursor2.getInt(2);
                    }
                    cursor2.moveToNext();
                }

                admin.Update_Category_item(id,New,icon_, pic_);
                Cursor cursor = admin.fetchAllItems();
                while (!cursor.isAfterLast()){
                    if (cursor.getString(6).equals(old))
                    {
                        admin.Update_count_item(cursor.getString(0),cursor.getString(1),cursor.getInt(2),cursor.getInt(3),cursor.getInt(4),cursor.getInt(5),New,cursor.getString(7));
                    }
                    cursor.moveToNext();
                }
            }
        });

        ImageView backpressed = (ImageView)findViewById(R.id.backpage);
        backpressed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AdminCategory.super.onBackPressed();
            }
        });
    }

}