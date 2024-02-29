package com.example.onlineshopping.UserFunctions;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.onlineshopping.DateBaseSql.CategoryItemsDataBase;
import com.example.onlineshopping.R;

public class Showitem extends AppCompatActivity {

    TextView Itemname;
    TextView ItemDesc;
    TextView Priceitem;
    TextView countitem;
    ImageView itemphoto;
    ImageView itembackground;
    CategoryItemsDataBase itemHelperDatabase;

    ImageButton add_item_btn;
    ImageButton min_item_btn;
    TextView countview;
    int countitem_view = 0;
    int removeitemcout=0;
    int pricevalue=0;

    int count_Feedback = 0;
    EditText Feedback;
    Button Add_to_cart, Back_to_items, add_feedback;
    String item_name_toremove_add;
    int item_picture,it_background;
    String Category_name , Category_description , barcode ,username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showitem);

        Itemname = (TextView)findViewById(R.id.itemnameforaddcart);
        ItemDesc=(TextView)findViewById(R.id.itemdescriptionforaddcart);
        Priceitem=(TextView)findViewById(R.id.itempriceforaddtocart);
        countitem=(TextView)findViewById(R.id.itemcountforaddcart);
        itemphoto=(ImageView) findViewById(R.id.itempictureforaddcart);
        itembackground=(ImageView) findViewById(R.id.backgrounditemforaddcart);


        String itemname = getIntent().getStringExtra("itemname");

        itemHelperDatabase = new CategoryItemsDataBase(getApplicationContext());
        Cursor cursor = itemHelperDatabase.fetchAllItems();
        while (!cursor.isAfterLast()){
            if (itemname.equals(cursor.getString(0))){
                Itemname.setText(cursor.getString(0));
                ItemDesc.setText(cursor.getString(1));
                Priceitem.setText(String.valueOf(cursor.getInt(2)));
                itemphoto.setImageResource(cursor.getInt(3));
                itembackground.setImageResource(cursor.getInt(4));
                countitem.setText(String.valueOf(cursor.getInt(5)));

                Category_description=cursor.getString(1);
                item_picture = cursor.getInt(3);
                it_background =cursor.getInt(4);
                Category_name = cursor.getString(6);
                item_name_toremove_add = cursor.getString(0);
                removeitemcout = cursor.getInt(5);
                pricevalue = cursor.getInt(2);
                barcode = cursor.getString(7);
            }
            cursor.moveToNext();
        }


        countview=findViewById(R.id.countitemadded);
        add_item_btn=findViewById(R.id.add_item_btn);
        add_item_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(removeitemcout!=0){
                    removeitemcout--;
                    countitem_view++;
                    countview.setText(String.valueOf(countitem_view));
                    countitem.setText(String.valueOf(removeitemcout));
                    Priceitem.setText(String.valueOf(countitem_view*pricevalue));
                }
            }
        });

        min_item_btn=findViewById(R.id.min_item_btn);
        min_item_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(countitem_view!=0){
                    removeitemcout++;
                    countitem_view--;
                    countview.setText(String.valueOf(countitem_view));
                    countitem.setText(String.valueOf(removeitemcout));
                    Priceitem.setText(String.valueOf(countitem_view*pricevalue));
                }
            }
        });

        Session session = Session.getInstance();
        username = session.getUsername();

        Add_to_cart = (Button)findViewById(R.id.Addtocart);
        Add_to_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(countitem_view!=0){
                    itemHelperDatabase.Update_count_item(item_name_toremove_add,Category_description,pricevalue,item_picture,it_background,removeitemcout,Category_name,barcode);
                    Cursor cursorCartItem = itemHelperDatabase.fetchAllCartItems();
                    boolean chechk_item =true;
                    int old_count=0 ;
                    while (!cursorCartItem.isAfterLast()){
                        if (item_name_toremove_add.equals(cursorCartItem.getString(0))){
                            chechk_item=false;
                            old_count = cursorCartItem.getInt(5);
                        }
                        cursorCartItem.moveToNext();
                    }
                    if (chechk_item) {
                        itemHelperDatabase.CreateCartItem(item_name_toremove_add, Category_description, pricevalue, item_picture, it_background, countitem_view,Category_name,barcode,username);
                    }else{
                        itemHelperDatabase.Update_count_Cart(item_name_toremove_add,Category_description,pricevalue,item_picture,it_background,countitem_view+old_count,Category_name,barcode,username);
                    }
                    Toast.makeText(getApplicationContext(),"Add to Cart",Toast.LENGTH_LONG).show();
                }
                else
                    Toast.makeText(getApplicationContext(),"bro don't troll, please add item",Toast.LENGTH_LONG).show();
            }
        });


        Feedback= (EditText)findViewById(R.id.Feedbacktxtforaddcart);
        add_feedback=(Button)findViewById(R.id.addfeedback);
        add_feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String fb = Feedback.getText().toString();
                if (fb.equals("") && count_Feedback ==0) {
                    Toast.makeText(getApplicationContext(), "bro Enter Feedback!", Toast.LENGTH_LONG).show();
                    count_Feedback++;
                }else if (fb.equals("") && count_Feedback ==1){
                    Toast.makeText(getApplicationContext(), "bro i Told You Enter Feedback!!!", Toast.LENGTH_LONG).show();
                }
                else {
                    itemHelperDatabase.Create_feedback(item_name_toremove_add, fb,username);
                    Feedback.setText("");
                    Toast.makeText(getApplicationContext(), "Thanks bro", Toast.LENGTH_LONG).show();
                    count_Feedback =0;
                }
            }
        });


        Button backpressed = (Button)findViewById(R.id.backpage);
        backpressed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent inte = new Intent(Showitem.this, ItemsOfCategories.class);
                inte.putExtra("name",Category_name);
                startActivity(inte);
            }
        });

    }
}