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

public class Admin_item_update extends AppCompatActivity {

    CategoryItemsDataBase admin ;
    ArrayList<String> Categories;
    AutoCompleteTextView Cat_naem;
    ArrayAdapter<String> adpterCategory;

    EditText itemnewname,itemdescription,item_price,item_count,item_barcode;
    Button update;

    ArrayList<String> ITems;
    AutoCompleteTextView ITems_naem;
    ArrayAdapter<String> adpterItems;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_item_update);



        itemnewname = (EditText)findViewById(R.id.item_admin_name_update);
        itemdescription = (EditText)findViewById(R.id.Item_Admin_descriptionS_update);
        item_price = (EditText)findViewById(R.id.Item_Admin_Price_update);
        item_count = (EditText)findViewById(R.id.Item_Admin_Count_update);
        item_barcode = (EditText)findViewById(R.id.Item_Admin_barcode_update);


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
                Cursor cursor = admin.fetchAllItems();
                while (!cursor.isAfterLast()){
                    if (cursor.getString(0).equals(Item)){
                        String p = String.valueOf(cursor.getInt(2));
                        String s = String.valueOf(cursor.getInt(5));
                        itemnewname.setText(cursor.getString(0));
                        itemdescription.setText(cursor.getString(1));
                        item_price.setText(p);
                        item_count.setText(s);
                        item_barcode.setText(cursor.getString(7));
                        break;
                    }
                    cursor.moveToNext();
                }
            }
        });


        update=(Button)findViewById(R.id.Update_Itemes);
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String cate = Cat_naem.getText().toString();
                String item_name = ITems_naem.getText().toString();
                String newname= itemnewname.getText().toString();
                String desc = itemdescription.getText().toString();
                int price = Integer.parseInt(item_price.getText().toString());
                int count = Integer.parseInt(item_count.getText().toString());
                String barcode =item_barcode.getText().toString();
                int id = admin.get_item_id(item_name);
                Cursor cursor = admin.fetchAllItems();
                int item_icon=0,item_back=0;
                while (!cursor.isAfterLast()){
                    if (cursor.getString(0).equals(item_name)){
                        item_icon = cursor.getInt(3);
                        item_back = cursor.getInt(4);
                        break;
                    }
                    cursor.moveToNext();
                }

                admin.Update_item_Admin(id,newname,desc,price,item_icon, item_back,count,cate,barcode);
                Toast.makeText(getApplicationContext(),item_name+" Updated to "+newname + " Successfully",Toast.LENGTH_SHORT).show();
            }
        });


        ImageView backpressed = (ImageView)findViewById(R.id.backpage);
        backpressed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Admin_item_update.super.onBackPressed();
            }
        });
    }


}