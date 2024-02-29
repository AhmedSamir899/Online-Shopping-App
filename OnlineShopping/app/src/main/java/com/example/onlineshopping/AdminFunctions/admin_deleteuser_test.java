package com.example.onlineshopping.AdminFunctions;

import android.database.Cursor;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.onlineshopping.DateBaseSql.CategoryItemsDataBase;
import com.example.onlineshopping.DateBaseSql.Login_Register_DBHelper;
import com.example.onlineshopping.R;


import java.util.ArrayList;

public class admin_deleteuser_test extends AppCompatActivity {
    Login_Register_DBHelper admin ;
    ArrayList<String> Users;
    AutoCompleteTextView User_name;
    ArrayAdapter<String> adpterUsers;

    Button Delete;

    ArrayList<String> ITems;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_deleteuser_test);

        admin = new Login_Register_DBHelper(getApplicationContext());

        Users = new ArrayList<>();
        Cursor cursor = admin.fetchAllUsers();
        while (!cursor.isAfterLast()){
            Users.add(cursor.getString(0));
            cursor.moveToNext();
        }
        final String[] uu = new String[1];
        ITems = new ArrayList<>();
        User_name = findViewById(R.id.auto_complete_user);
        adpterUsers = new ArrayAdapter<String>(this,R.layout.list_category,Users);
        User_name.setAdapter(adpterUsers);
        User_name.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                uu[0] = adapterView.getItemAtPosition(i).toString();
                Toast.makeText(getApplicationContext(),"User "+ uu[0],Toast.LENGTH_SHORT).show();

            }
        });




        Delete = (Button)findViewById(R.id.delete_Users);
        Delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                admin.Delete_user_from(uu[0]);
                Toast.makeText(getApplicationContext(),"User "+ uu[0] +"Is Deleted",Toast.LENGTH_SHORT).show();
            }
        });

        ImageView backpressed = (ImageView)findViewById(R.id.backpage);
        backpressed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                admin_deleteuser_test.super.onBackPressed();
            }
        });
    }
}