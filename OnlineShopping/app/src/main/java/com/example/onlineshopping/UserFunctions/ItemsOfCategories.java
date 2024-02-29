package com.example.onlineshopping.UserFunctions;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import androidx.activity.result.ActivityResultLauncher;

import androidx.annotation.Nullable;

import android.speech.RecognizerIntent;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


import com.example.onlineshopping.AdaptersAndModels.itemAdapter;
import com.example.onlineshopping.AdaptersAndModels.itemModel;
import com.example.onlineshopping.DateBaseSql.CategoryItemsDataBase;
import com.example.onlineshopping.R;
import com.journeyapps.barcodescanner.ScanContract;
import com.journeyapps.barcodescanner.ScanOptions;

public class ItemsOfCategories extends AppCompatActivity {

    private static final int REQUEST_CODE_SPEECH_INPUT = 1000;

    List<itemModel> itemlist;
    RecyclerView recyclerView2;
    itemAdapter itemAdapter2;
    CategoryItemsDataBase itemHelperDatabase;
    List<itemModel> itemlistsearch;

    EditText name_of_item;
    TextView barcode_of_item;
    ImageView search_btn;
    ImageButton voice,cam_scan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_items_of_categories);


        TextView CateText = (TextView)findViewById(R.id.Categorynamename);
        String category = getIntent().getStringExtra("name");
        String cat = category+" Items";
        CateText.setText(cat);
        itemlist = new ArrayList<>();

        itemHelperDatabase = new CategoryItemsDataBase(getApplicationContext());
        //Books
        if(itemHelperDatabase.checkItem()) {
            itemHelperDatabase.CreateItem("My hero academia", "Action,Mystery & shounen", 50, R.drawable.deku, R.drawable.animebackground, 2, "Books","777342215");
            itemHelperDatabase.CreateItem("One punch man", "Mystery,Action & blood", 30, R.drawable.saitama, R.drawable.animebackground, 3, "Books","036000291452");
            itemHelperDatabase.CreateItem("demon slayer", "Mystery,Action & Demon", 20, R.drawable.tanjiro, R.drawable.animebackground, 4, "Books","831455425");
            itemHelperDatabase.CreateItem("Naruto", "Action & shounen", 75, R.drawable.kakasgi, R.drawable.animebackground, 3, "Books","1234612");
            itemHelperDatabase.CreateItem("Dragon ball", "Action & shounen", 10, R.drawable.songuko, R.drawable.animebackground, 5, "Books","8646113");
            itemHelperDatabase.CreateItem("Haikyuu part 1", "Sports,Drama & shounen", 40, R.drawable.shoyo, R.drawable.animebackground, 2, "Books","831455426");
            itemHelperDatabase.CreateItem("Haikyuu part 2", "Sports,Drama & shounen", 35, R.drawable.kagyama, R.drawable.animebackground, 4, "Books","123456789012");
            itemHelperDatabase.CreateItem("Jujutsu Kaisen", "Mystery,Action & Demon", 20, R.drawable.gojosatruo, R.drawable.animebackground, 1, "Books","058649234759655");


            //Electronics
            itemHelperDatabase.CreateItem("Mouse", "High quality wired mouse", 50, R.drawable.mouse, R.drawable.electroincsbackground, 5, "Electronics","413489482");
            itemHelperDatabase.CreateItem("Keyboard", "High quality black wired keyboard", 30, R.drawable.keyboard, R.drawable.electroincsbackground, 3, "Electronics","4631662");
            itemHelperDatabase.CreateItem("PS4", "PlayStation running the latest games", 20, R.drawable.ps4, R.drawable.electroincsbackground, 1, "Electronics","314643116");
            itemHelperDatabase.CreateItem("Screen", "50-inch screen with super quality", 55, R.drawable.television, R.drawable.electroincsbackground, 3, "Electronics","413519864");
            itemHelperDatabase.CreateItem("Smart Phone", "Smartphone 10 inch, 4 RAM and 64 GB", 10, R.drawable.smartphone, R.drawable.electroincsbackground, 4, "Electronics","136463");


            //Clothes
            itemHelperDatabase.CreateItem("Jacket", "pink jacket, size 40", 50, R.drawable.jacksell, R.drawable.clothesbackground, 2, "Clothes","513641");
            itemHelperDatabase.CreateItem("Shirt", "shirt for travels", 30, R.drawable.shirt, R.drawable.clothesbackground, 1, "Clothes","5539641");
            itemHelperDatabase.CreateItem("Skirt", "skirt for school", 20, R.drawable.skirt, R.drawable.clothesbackground, 4, "Clothes","621631");
            itemHelperDatabase.CreateItem("Santa", "santa ice-cap small size", 55, R.drawable.santa, R.drawable.clothesbackground, 7, "Clothes","7131981");
            itemHelperDatabase.CreateItem("trouser", "trouser with size 38", 10, R.drawable.trousers, R.drawable.clothesbackground, 5, "Clothes","9813161");
            itemHelperDatabase.CreateItem("Shoes", "colored shoes, size 42", 15, R.drawable.shoes, R.drawable.clothesbackground, 3, "Clothes","6316813");


            //Games
            itemHelperDatabase.CreateItem("Mario", "Adventure game", 30, R.drawable.mario, R.drawable.toysbackground, 2, "Games","46346841");
            itemHelperDatabase.CreateItem("League of Legends", "5v5 Online game", 20, R.drawable.legoflegends, R.drawable.toysbackground, 4, "Games","316431613");
            itemHelperDatabase.CreateItem("Sonic", "Adventure game", 55, R.drawable.sonic, R.drawable.toysbackground, 6, "Games","0021144121");
            itemHelperDatabase.CreateItem("Mobile Legends", "5v5 online game", 10, R.drawable.mobilelegend, R.drawable.toysbackground, 5, "Games","05431613");
            itemHelperDatabase.CreateItem("Smiley", "Survival game", 50, R.drawable.smiley, R.drawable.toysbackground, 1, "Games","21648641");


            //musical instruments
            itemHelperDatabase.CreateItem("Piano", "The piano is a stringed keyboard instrument", 50, R.drawable.piano, R.drawable.musicsbackground, 2, "musical instruments","52164631");
            itemHelperDatabase.CreateItem("Guitar", "wooden body with six strings stretched over it", 30, R.drawable.guitar, R.drawable.musicsbackground, 8, "musical instruments","352161");
            itemHelperDatabase.CreateItem("Recorder", "wooden or plastic musical instrument", 20, R.drawable.vinyl, R.drawable.musicsbackground, 1, "musical instruments","476431");
            itemHelperDatabase.CreateItem("Drums", "makes a noise by being hit", 55, R.drawable.drum, R.drawable.musicsbackground, 3, "musical instruments","4632316");
            itemHelperDatabase.CreateItem("Flute", "wind instrument, sound is produced by a stream of air", 10, R.drawable.flute, R.drawable.musicsbackground, 5, "musical instruments","318463");
        }


        Cursor cursor = itemHelperDatabase.fetchAllItems();
        while (!cursor.isAfterLast()) {
            if (category.equals(cursor.getString(6)) && cursor.getInt(5) != 0) {
                itemlist.add(new itemModel(cursor.getString(0), cursor.getString(1), cursor.getInt(2), cursor.getInt(3), cursor.getInt(4), cursor.getInt(5)));
            }
            cursor.moveToNext();
        }

        recyclerView2 = findViewById(R.id.recyclerviewitems);
        recyclerView2.setLayoutManager(new LinearLayoutManager(null));

        itemAdapter2 = new itemAdapter( this,itemlist);
        recyclerView2.setAdapter(itemAdapter2);


        name_of_item = (EditText)findViewById(R.id.itemnamesearch);
        barcode_of_item = (TextView)findViewById(R.id.itembarcodesearch);
        search_btn = (ImageView)findViewById(R.id.search_btn);
        itemlistsearch = new ArrayList<>();


        search_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                recyclerView2 = findViewById(R.id.recyclerviewitems);
                recyclerView2.setLayoutManager(new LinearLayoutManager(null));
                itemlistsearch.clear();
                String [] items  = itemHelperDatabase.Search(((TextView)name_of_item).getText().toString().toLowerCase().trim());

                if(!name_of_item.equals("")){
                    for (int i = 0 ; i < items.length ; i++) {
                        Cursor cursor3 = itemHelperDatabase.fetchAllItems();
                        while (!cursor3.isAfterLast()) {
                            if (category.equals(cursor3.getString(6)) && items[i].equals(cursor3.getString(0))) {
                                itemlistsearch.add(new itemModel(cursor3.getString(0), cursor3.getString(1), cursor3.getInt(2), cursor3.getInt(3), cursor3.getInt(4), cursor3.getInt(5)));
                            }
                            cursor3.moveToNext();
                        }
                    }
                    itemAdapter2 = new itemAdapter(  ItemsOfCategories.this,itemlistsearch);
                    recyclerView2.setAdapter(itemAdapter2);
                }
                else{

                    itemAdapter2 = new itemAdapter(  ItemsOfCategories.this,itemlist);
                    recyclerView2.setAdapter(itemAdapter2);
                }

            }
        });


        Button backpressed = (Button)findViewById(R.id.backpage);
        backpressed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent inte = new Intent(ItemsOfCategories.this, Categories_Activity.class);
                startActivity(inte);
            }
        });

        voice= (ImageButton)findViewById(R.id.search_voice_btn);
        voice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                speak();
                Toast.makeText(getApplicationContext(),"press button search",Toast.LENGTH_SHORT).show();
            }
        });


        cam_scan = (ImageButton)findViewById(R.id.seach_camera_btn);
        cam_scan.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                scan_code();
                Toast.makeText(getApplicationContext(),"press on camera again to search",Toast.LENGTH_SHORT).show();
                return false;
            }
        });


        cam_scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s = barcode_of_item.getText().toString().toLowerCase().trim();
                if (!s.equals("")) {
                    recyclerView2 = findViewById(R.id.recyclerviewitems);
                    recyclerView2.setLayoutManager(new LinearLayoutManager(null));
                    itemlistsearch.clear();


                    Cursor cursor = itemHelperDatabase.fetchAllItems();
                    while (!cursor.isAfterLast()) {
                        if (s.equals(cursor.getString(7)) && cursor.getInt(5) != 0) {
                            itemlistsearch.add(new itemModel(cursor.getString(0), cursor.getString(1), cursor.getInt(2), cursor.getInt(3), cursor.getInt(4), cursor.getInt(5)));
                        }
                        cursor.moveToNext();
                    }
                    barcode_of_item.setText("");
                    itemAdapter2 = new itemAdapter(ItemsOfCategories.this, itemlistsearch);
                    recyclerView2.setAdapter(itemAdapter2);
                }
                else{
                    Toast.makeText(getApplicationContext(),"Hold The camera to scan barcode",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    private void speak(){
        Intent intent=new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,"hi say something");
        try{
            startActivityForResult(intent,REQUEST_CODE_SPEECH_INPUT);

        }
        catch(Exception e){
            Toast.makeText(this,""+e.getMessage(),Toast.LENGTH_SHORT).show();

        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case REQUEST_CODE_SPEECH_INPUT:{
                if(resultCode==RESULT_OK && null!=data){
                    ArrayList<String>result=data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    name_of_item.setText(result.get(0));
                }
                break;
            }
        }
    }


    private void scan_code() {
        ScanOptions options= new ScanOptions();
        options.setPrompt("volume up to flash on");
        options.setBeepEnabled(true);
        options.setOrientationLocked(true);
        options.setCaptureActivity(CaptureAct.class);
        barlancher.launch(options);
    }
    ActivityResultLauncher<ScanOptions> barlancher =registerForActivityResult(new ScanContract(),result ->
    {
        if(result.getContents()!=null){
            String resu =result.getContents();
            barcode_of_item.setText(resu);
        }
    });
}