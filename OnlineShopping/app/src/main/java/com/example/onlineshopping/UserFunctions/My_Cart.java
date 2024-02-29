package com.example.onlineshopping.UserFunctions;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.onlineshopping.AdaptersAndModels.itemModel;
import com.example.onlineshopping.AdaptersAndModels.itemcartAdapter;
import com.example.onlineshopping.DateBaseSql.CategoryItemsDataBase;
import com.example.onlineshopping.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class My_Cart extends AppCompatActivity {

    List<itemModel> itemlist;
    RecyclerView recyclerView2;
    itemcartAdapter itemAdapter2;
    CategoryItemsDataBase itemHelperDatabase;

    TextView Total_price;
    int Total_price_cala=0;
    Button DeleteAll;
    String username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_cart);

        itemlist = new ArrayList<>();
        itemHelperDatabase = new CategoryItemsDataBase(getApplicationContext());
        Cursor cursor = itemHelperDatabase.fetchAllCartItems();

        String category = getIntent().getStringExtra("name");

        Session session = Session.getInstance();
        username = session.getUsername();
        while (!cursor.isAfterLast()){
            if (cursor.getInt(5)==0) {
                itemHelperDatabase.Delete_item_from(cursor.getString(0));
            }
            else if (cursor.getString(8).equals(username)){
                itemlist.add(new itemModel(cursor.getString(0), cursor.getString(1), cursor.getInt(2), cursor.getInt(3), cursor.getInt(4), cursor.getInt(5)));
                Total_price_cala+=(cursor.getInt(2)*cursor.getInt(5));
            }
            cursor.moveToNext();
        }

        recyclerView2 = findViewById(R.id.recyclerviewCart);
        recyclerView2.setLayoutManager(new LinearLayoutManager(null));

        itemAdapter2 = new itemcartAdapter(this,itemlist);
        recyclerView2.setAdapter(itemAdapter2);


        Button backpressed = (Button)findViewById(R.id.backpage);
        backpressed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent inte = new Intent(My_Cart.this, Categories_Activity.class);

                startActivity(inte);
            }
        });

        Total_price = (TextView)findViewById(R.id.Total_Price);
        Total_price.setText(String.valueOf(Total_price_cala));

        DeleteAll = (Button)findViewById(R.id.DeleteAllItemCart);
        DeleteAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor cursorcart = itemHelperDatabase.fetchAllCartItems();
                Cursor cursoritem = itemHelperDatabase.fetchAllItems();
                while (!cursoritem.isAfterLast()){
                    while (!cursorcart.isAfterLast()){
                        if (cursoritem.getString(0).equals(cursorcart.getString(0))){
                            itemHelperDatabase.Update_count_item(cursoritem.getString(0),cursoritem.getString(1),cursoritem.getInt(2),cursoritem.getInt(3),cursoritem.getInt(4),(cursoritem.getInt(5)+cursorcart.getInt(5)),cursoritem.getString(6),cursoritem.getString(7));
                        }
                        cursorcart.moveToNext();
                    }
                    cursorcart.moveToFirst();
                    cursoritem.moveToNext();
                }
                cursorcart.moveToFirst();
                while (!cursorcart.isAfterLast()) {
                    if (cursorcart.getString(8).equals(username))
                        itemHelperDatabase.Delete_item_from(cursorcart.getString(0));
                    cursorcart.moveToNext();
                }
                itemlist.clear();
                Toast.makeText(getApplicationContext(), "ALl Your Items are Deleted", Toast.LENGTH_LONG).show();
                Intent inte = new Intent(My_Cart.this, My_Cart.class);
                inte.putExtra("name",category);
                startActivity(inte);
            }
        });


        Button Sumb = (Button)findViewById(R.id.Sumbition);
        Sumb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Date dateAndTime = Calendar.getInstance().getTime();
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
                SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm:ss", Locale.getDefault());

                String Date = dateFormat.format(dateAndTime);
                String time = timeFormat.format(dateAndTime);

                boolean itemsell = true;

                Cursor cursorcartitems = itemHelperDatabase.fetchAllCartItems();
                Cursor cursorbestitem = itemHelperDatabase.fetchAllbestsell();
                while (!cursorcartitems.isAfterLast()){
                    while (!cursorbestitem.isAfterLast()){
                        if (cursorcartitems.getString(0).equals(cursorbestitem.getString(0))){
                            itemsell = false;
                            int id = itemHelperDatabase.get_item_sell_id(cursorbestitem.getString(0));
                            itemHelperDatabase.Update_item_sell(id,cursorbestitem.getString(0),(cursorbestitem.getInt(1)+cursorcartitems.getInt(5)));
                        }
                        cursorbestitem.moveToNext();
                    }
                    if (itemsell){
                        itemHelperDatabase.CreateBestSell(cursorcartitems.getString(0),cursorcartitems.getInt(5));
                        itemsell = true;
                    }
                    cursorbestitem.moveToFirst();
                    cursorcartitems.moveToNext();
                }



                boolean check_cart_item = false;
                Cursor cursorcart = itemHelperDatabase.fetchAllCartItems();
                while (!cursorcart.isAfterLast()) {
                    if (cursorcart.getString(8).equals(username))
                        check_cart_item = true;
                    cursorcart.moveToNext();
                }


                if (check_cart_item) {
                    cursorcart.moveToFirst();
                    while (!cursorcart.isAfterLast()) {
                        if (cursorcart.getString(8).equals(username))
                            itemHelperDatabase.CreateReport(username, cursorcart.getString(0), Date, time);
                        cursorcart.moveToNext();
                    }

                    Toast.makeText(getApplicationContext(), username + " your order is done ", Toast.LENGTH_LONG).show();
                    cursorcart.moveToFirst();
                    while (!cursorcart.isAfterLast()) {
                        if (cursorcart.getString(8).equals(username))
                            itemHelperDatabase.Delete_item_from(cursorcart.getString(0));
                        cursorcart.moveToNext();
                    }




                    // move to GPS
                    Intent intent2 = new Intent(getApplicationContext(), MapsActivity.class);
                    startActivity(intent2);
                }
                else
                    Toast.makeText(getApplicationContext(), username + " your cart is Empty", Toast.LENGTH_LONG).show();
            }
        });

    }
}