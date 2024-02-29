package com.example.onlineshopping.AdminFunctions;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;

import com.example.onlineshopping.DateBaseSql.CategoryItemsDataBase;
import com.example.onlineshopping.R;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class piechart extends AppCompatActivity {
    CategoryItemsDataBase item ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_piechart);

        item = new CategoryItemsDataBase(getApplicationContext());


        PieChart piechart = findViewById(R.id.piechart);

        ArrayList<PieEntry> bestSelling =new ArrayList<>();
        Cursor c = item.fetchAllbestsell();
        while (!c.isAfterLast()) {
            bestSelling.add(new PieEntry(c.getInt(1), c.getString(0)));
            c.moveToNext();
        }

        PieDataSet pieDataSet = new PieDataSet(bestSelling,"Best Selling");
        pieDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        pieDataSet.setValueTextColor(Color.WHITE);
        pieDataSet.setValueTextSize(9f);

        PieData pieData = new PieData(pieDataSet);

        piechart.setData(pieData);
        piechart.getDescription().setEnabled(false);
        piechart.setCenterText("Best Selling By Users");
        piechart.animate();

    }
}