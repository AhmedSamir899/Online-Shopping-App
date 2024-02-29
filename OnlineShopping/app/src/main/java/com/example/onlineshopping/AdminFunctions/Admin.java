package com.example.onlineshopping.AdminFunctions;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.onlineshopping.R;
import com.example.onlineshopping.UserFunctions.Categories_Activity;
import com.example.onlineshopping.UserFunctions.Login;
import com.google.android.material.navigation.NavigationView;

public class Admin extends AppCompatActivity {

    NavigationView navView;
    Button List;
    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);


        final MediaPlayer mediaPlayer = MediaPlayer.create(this,R.raw.btnsound);
        List =findViewById(R.id.menuBtn);
        drawerLayout =findViewById(R.id.draw_layout);

        List.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(GravityCompat.START);
                mediaPlayer.start();
            }
        });
        navView = findViewById(R.id.nav_view);
        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.Categoryss:
                        //mediaPlayer.start();
                        Intent intent = new Intent(Admin.this, AdminCategory.class);
                        startActivity(intent);
                        break;
                    case R.id.Item_adds:
                        mediaPlayer.start();
                        Intent intent3 = new Intent(Admin.this, AdminItems.class);
                        startActivity(intent3);
                        break;
                    case R.id.item_updatess:
                        mediaPlayer.start();
                        Intent intent4 = new Intent(Admin.this, Admin_item_update.class);
                        startActivity(intent4);
                        break;
                    case R.id.item_delett:
                        mediaPlayer.start();
                        Intent intent2 = new Intent(Admin.this, Admin_delete_item.class);
                        startActivity(intent2);
                        break;
                    case R.id.user_delett:
                        mediaPlayer.start();
                        Intent intent5 = new Intent(Admin.this, admin_deleteuser_test.class);
                        startActivity(intent5);
                        break;
                    case R.id.reportss:
                        mediaPlayer.start();
                        intent = new Intent(Admin.this, Admin_Reports.class);
                        startActivity(intent);
                        break;
                    case R.id.feedbackss:
                        mediaPlayer.start();
                        intent = new Intent(Admin.this, Admin_Feedbacks.class);
                        startActivity(intent);
                        break;
                    case R.id.charts:
                        mediaPlayer.start();
                        intent = new Intent(Admin.this, graph_sells.class);
                        startActivity(intent);
                        break;

                    case R.id.logou_user:
                        mediaPlayer.start();
                        Intent inte = new Intent(Admin.this, Login.class);
                        startActivity(inte);
                        break;

                }
                return false;
            }
        });

    }
}