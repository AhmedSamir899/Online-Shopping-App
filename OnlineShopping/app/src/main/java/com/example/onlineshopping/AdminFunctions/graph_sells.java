package com.example.onlineshopping.AdminFunctions;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.onlineshopping.AdminFunctions.barchart;
import com.example.onlineshopping.AdminFunctions.piechart;
import com.example.onlineshopping.R;


public class graph_sells extends AppCompatActivity {

    Button bar,pie;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph_sells);
        bar = (Button)findViewById(R.id.barchartdiagram);
        pie = (Button)findViewById(R.id.piehartdiagram);


        bar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), barchart.class);
                startActivity(intent);
            }
        });

        pie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), piechart.class);
                startActivity(intent);
            }
        });


    }
}