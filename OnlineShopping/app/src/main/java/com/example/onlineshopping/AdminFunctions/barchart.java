package com.example.onlineshopping.AdminFunctions;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;

import com.example.onlineshopping.DateBaseSql.CategoryItemsDataBase;
import com.example.onlineshopping.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

public class barchart extends AppCompatActivity {

    CategoryItemsDataBase item ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barchart);


        List<String> itemname = new ArrayList<>();
        item = new CategoryItemsDataBase(getApplicationContext());


        BarChart barChart = (BarChart)findViewById(R.id.barchart);
        ArrayList<BarEntry> bestSelling =new ArrayList<>();
        Cursor c = item.fetchAllbestsell();
        int index = 0;
        while (!c.isAfterLast()) {
            itemname.add(c.getString(0));
            bestSelling.add(new BarEntry(index, c.getInt(1)));
            c.moveToNext();
            index+=1;
        }


        BarDataSet barDataSet = new BarDataSet(bestSelling,"Best Selling");
        barDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        barDataSet.setValueTextColor(Color.WHITE);
        barDataSet.setValueTextSize(12f);

        BarData barData = new BarData(barDataSet);
        barChart.setFitBars(true);
        barChart.setData(barData);
        barChart.getDescription().setText("Best Selling By Users");
        barChart.animateY(2000);


        XAxis xAxis = barChart.getXAxis();
        xAxis.setTextSize(7f);
        xAxis.setValueFormatter(new IndexAxisValueFormatter(itemname));
    }
}