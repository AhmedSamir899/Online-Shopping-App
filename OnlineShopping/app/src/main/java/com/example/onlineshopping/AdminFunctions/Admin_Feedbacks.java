package com.example.onlineshopping.AdminFunctions;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import com.example.onlineshopping.DateBaseSql.CategoryItemsDataBase;
import com.example.onlineshopping.AdaptersAndModels.FeedbackAdapter;
import com.example.onlineshopping.AdaptersAndModels.FeedbackModel;
import com.example.onlineshopping.R;

import java.util.ArrayList;
import java.util.List;

public class Admin_Feedbacks extends AppCompatActivity {

    List<FeedbackModel> feedlist;
    RecyclerView recyclerView2;
    FeedbackAdapter FeedbacAdater;
    CategoryItemsDataBase itemHelperDatabase;


    ArrayList<String> Items;
    AutoCompleteTextView Items_ssss;
    ArrayAdapter<String> AdapterUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_feedbacks);


        itemHelperDatabase = new CategoryItemsDataBase(getApplicationContext());
        feedlist = new ArrayList<>();
        recyclerView2 = findViewById(R.id.recyclerviewfeedback);
        recyclerView2.setLayoutManager(new LinearLayoutManager(null));

        Items = new ArrayList<>();
        Cursor cursor = itemHelperDatabase.fetchAllItems();
        while (!cursor.isAfterLast()){
            Items.add(cursor.getString(0));
            cursor.moveToNext();
        }

        Items_ssss = findViewById(R.id.auto_complete_User_feedback);
        AdapterUser = new ArrayAdapter<String>(this,R.layout.list_category,Items);
        Items_ssss.setAdapter(AdapterUser);
        Items_ssss.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String Item = adapterView.getItemAtPosition(i).toString();
                Toast.makeText(getApplicationContext(),"Item "+Item,Toast.LENGTH_SHORT).show();
                feedlist.clear();
                Cursor cursor = itemHelperDatabase.fetchAllFeedback();
                while (!cursor.isAfterLast()) {
                    if (cursor.getString(0).equals(Item)) {
                        feedlist.add(new FeedbackModel(cursor.getString(1),cursor.getString(2)));
                    }
                    cursor.moveToNext();
                }

                FeedbacAdater = new FeedbackAdapter( getApplicationContext(),feedlist);
                recyclerView2.setAdapter(FeedbacAdater);
            }
        });



    }
}