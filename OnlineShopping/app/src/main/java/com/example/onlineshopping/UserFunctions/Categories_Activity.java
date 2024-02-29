package com.example.onlineshopping.UserFunctions;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.onlineshopping.AdaptersAndModels.CategoryAdapter;
import com.example.onlineshopping.AdaptersAndModels.CategoryModel;
import com.example.onlineshopping.CategoryFactory;
import com.example.onlineshopping.DateBaseSql.CategoryItemsDataBase;
import com.example.onlineshopping.R;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

public class Categories_Activity extends AppCompatActivity {

    List<CategoryModel> CategoryList;
    RecyclerView recyclerView;
    CategoryAdapter CatAdapter;
    CategoryItemsDataBase CategoryHelperDatabase;

    NavigationView navView;
    Button List;
    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.categories_activity);

        CategoryList = new ArrayList<>();
        CategoryHelperDatabase = new CategoryItemsDataBase(getApplicationContext());

        if (CategoryHelperDatabase.checkCategory()) {
            // Use the CategoryFactory to create predefined categories
            CategoryFactory.createCategory("Books", R.drawable.hi, R.drawable.animebackground).createCategory(CategoryHelperDatabase);
            CategoryFactory.createCategory("Electronics", R.drawable.joystick, R.drawable.electroincsbackground).createCategory(CategoryHelperDatabase);
            CategoryFactory.createCategory("Clothes", R.drawable.jacket, R.drawable.clothesbackground).createCategory(CategoryHelperDatabase);
            CategoryFactory.createCategory("Games", R.drawable.pikachu, R.drawable.toysbackground).createCategory(CategoryHelperDatabase);
            CategoryFactory.createCategory("Musical Instruments", R.drawable.musician, R.drawable.musicsbackground).createCategory(CategoryHelperDatabase);
        }


        Cursor cursor = CategoryHelperDatabase.fetchAllCategories();
        while (!cursor.isAfterLast()) {
            CategoryList.add(new CategoryModel(cursor.getString(0), cursor.getInt(1), cursor.getInt(2)));
            cursor.moveToNext();
        }

        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(null));

        CatAdapter = new CategoryAdapter(this, CategoryList);
        recyclerView.setAdapter(CatAdapter);

        List = findViewById(R.id.menuBtn_user);
        drawerLayout = findViewById(R.id.draw_layout);

        List.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });

        navView = findViewById(R.id.nav_view_user);
        navView.setNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.My_cart_page:
                    Intent intent = new Intent(Categories_Activity.this, My_Cart.class);
                    startActivity(intent);
                    break;
                case R.id.logou_user:
                    Intent intent3 = new Intent(Categories_Activity.this, Login.class);
                    startActivity(intent3);
                    break;
            }
            return false;
        });
    }
}
