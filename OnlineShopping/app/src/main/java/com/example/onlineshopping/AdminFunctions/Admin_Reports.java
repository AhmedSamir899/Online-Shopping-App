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

public class Admin_Reports extends AppCompatActivity {

    CategoryItemsDataBase itemsDataBase;
    ArrayList<String> items;
    AutoCompleteTextView name_items;
    ArrayAdapter<String> Adapter_items;

    EditText Date,time_From,time_To;
    Button additem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_reports);

        itemsDataBase = new CategoryItemsDataBase(getApplicationContext());

        Date = (EditText)findViewById(R.id.Date_item);
        time_From = (EditText)findViewById(R.id.Time_item_From);
        time_To = (EditText)findViewById(R.id.Time_item_To);
        additem = (Button)findViewById(R.id.add_date);
        name_items = findViewById(R.id.auto_complete_item_user);

        items = new ArrayList<>();
        additem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                items.clear();

                String date = Date.getText().toString();
                int from =toMins(time_From.getText().toString());
                int To = toMins(time_To.getText().toString());

                Cursor cursor = itemsDataBase.fetchAllReports();
                while (!cursor.isAfterLast()){
                    int time = toMins(cursor.getString(3));
                    if (date.equals(cursor.getString(2)) && time >= from && time <= To)
                    {
                        items.add(cursor.getString(1));
                    }
                    cursor.moveToNext();
                }


                Adapter_items = new ArrayAdapter<String>(getApplicationContext(),R.layout.list_category,items);
                name_items.setAdapter(Adapter_items);
            }
        });


        name_items.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String Item = adapterView.getItemAtPosition(i).toString();
                Toast.makeText(getApplicationContext(),"Item "+Item,Toast.LENGTH_SHORT).show();
            }
        });



        ImageView backpressed = (ImageView)findViewById(R.id.backpage);
        backpressed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Admin_Reports.super.onBackPressed();
            }
        });
    }

    private static int toMins(String s) {
        String[] hourMin = s.split(":");
        int hour = Integer.parseInt(hourMin[0]);
        int mins = Integer.parseInt(hourMin[1]);
        int secs = Integer.parseInt(hourMin[2]);
        int hoursInMins = hour * 60;
        int secsInMins = secs / 60;
        return hoursInMins + mins + secsInMins;
    }
}