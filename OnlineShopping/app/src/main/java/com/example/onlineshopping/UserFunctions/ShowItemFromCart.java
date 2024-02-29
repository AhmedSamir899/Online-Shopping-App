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


public class ShowItemFromCart extends AppCompatActivity {

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
    int removeitemcout = 0;
    int pricevalue = 0;
    int countofitem = 0;

    int count_Feedback = 0;
    EditText Feedback;
    Button Save_to_Cart, Delete_tems, add_feedback;
    String item_name_toremove_add;
    int item_picture, it_background;
    String Category_name, item_description, barcode, username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_show_item_from_cart);
        Itemname = (TextView) findViewById(R.id.itemnamefromcart);
        ItemDesc = (TextView) findViewById(R.id.itemdescriptionfromcart);
        Priceitem = (TextView) findViewById(R.id.itempricefromcart);
        countitem = (TextView) findViewById(R.id.itemcountfromcart);
        itemphoto = (ImageView) findViewById(R.id.itempicturefromcart);
        itembackground = (ImageView) findViewById(R.id.backgrounditemfromcart);

        String itemname = getIntent().getStringExtra("itemname");


        itemHelperDatabase = new CategoryItemsDataBase(getApplicationContext());
        Cursor cursor = itemHelperDatabase.fetchAllCartItems();
        while (!cursor.isAfterLast()) {
            if (itemname.equals(cursor.getString(0))) {
                Itemname.setText(cursor.getString(0));
                ItemDesc.setText(cursor.getString(1));
                Priceitem.setText(String.valueOf(cursor.getInt(2)));
                itemphoto.setImageResource(cursor.getInt(3));
                itembackground.setImageResource(cursor.getInt(4));
                countitem.setText(String.valueOf(cursor.getInt(5)));

                item_description = cursor.getString(1);
                item_picture = cursor.getInt(3);
                it_background = cursor.getInt(4);
                item_name_toremove_add = cursor.getString(0);
                removeitemcout = cursor.getInt(5);
                pricevalue = cursor.getInt(2);
                Category_name = cursor.getString(6);
                barcode = cursor.getString(7);
            }
            cursor.moveToNext();
        }


        Cursor getallitems = itemHelperDatabase.fetchAllItems();
        while (!getallitems.isAfterLast()) {
            if (itemname.equals(getallitems.getString(0))) {
                countofitem = getallitems.getInt(5);
            }
            getallitems.moveToNext();
        }

        countview = findViewById(R.id.countitemaddedfromcart);
        add_item_btn = findViewById(R.id.add_item_btnfromcart);
        add_item_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (removeitemcout != 0) {
                    removeitemcout--;
                    countitem_view++;
                    countview.setText(String.valueOf(countitem_view));
                    countitem.setText(String.valueOf(removeitemcout));
                    Priceitem.setText(String.valueOf(countitem_view * pricevalue));
                }
            }
        });

        min_item_btn = findViewById(R.id.min_item_btnfromcart);
        min_item_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (countitem_view != 0) {
                    removeitemcout++;
                    countitem_view--;
                    countview.setText(String.valueOf(countitem_view));
                    countitem.setText(String.valueOf(removeitemcout));
                    Priceitem.setText(String.valueOf(countitem_view * pricevalue));
                }
            }
        });
        Session session = Session.getInstance();
        username = session.getUsername();

        Save_to_Cart = (Button) findViewById(R.id.save_it);
        Save_to_Cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (countitem_view == 0) {
                    Toast.makeText(getApplicationContext(), "You Can't Save ZERO Items", Toast.LENGTH_LONG).show();
                } else {
                    itemHelperDatabase.Update_count_Cart(item_name_toremove_add, item_description, pricevalue, item_picture, it_background, countitem_view, Category_name, barcode, username);
                    itemHelperDatabase.Update_count_item(item_name_toremove_add, item_description, pricevalue, item_picture, it_background, countofitem + removeitemcout, Category_name, barcode);
                    Toast.makeText(getApplicationContext(), "Item Saved In your Cart", Toast.LENGTH_LONG).show();
                }
            }
        });


        Delete_tems = (Button) findViewById(R.id.delete_it);
        Delete_tems.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemHelperDatabase.Update_count_item(item_name_toremove_add, item_description, pricevalue, item_picture, it_background, countofitem + removeitemcout, Category_name, barcode);
                itemHelperDatabase.Delete_item_from(item_name_toremove_add);
                Toast.makeText(getApplicationContext(), "Item is Deleted", Toast.LENGTH_LONG).show();
                Intent inte = new Intent(ShowItemFromCart.this, My_Cart.class);
                inte.putExtra("name", Category_name);
                startActivity(inte);
            }
        });


        Feedback = (EditText) findViewById(R.id.Feedbacktxtfromcart);
        add_feedback = (Button) findViewById(R.id.addfeedbackfromcart);
        add_feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String fb = Feedback.getText().toString();

                if (fb.equals("") && count_Feedback == 0) {
                    Toast.makeText(getApplicationContext(), "bro Enter Feedback!", Toast.LENGTH_LONG).show();
                    count_Feedback++;
                } else if (fb.equals("") && count_Feedback == 1) {
                    Toast.makeText(getApplicationContext(), "bro i Told You Enter Feedback!!!", Toast.LENGTH_LONG).show();
                } else {
                    itemHelperDatabase.Create_feedback(item_name_toremove_add, fb,username);
                    Feedback.setText("");
                    Toast.makeText(getApplicationContext(), "Thanks bro", Toast.LENGTH_LONG).show();
                    count_Feedback = 0;
                }
            }
        });


        Button backpressed = (Button) findViewById(R.id.backpage);
        backpressed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent inte = new Intent(ShowItemFromCart.this, My_Cart.class);
                inte.putExtra("name", Category_name);
                startActivity(inte);
            }
        });


    }
}