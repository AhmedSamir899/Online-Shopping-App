package com.example.onlineshopping;

import com.example.onlineshopping.DateBaseSql.CategoryItemsDataBase;

public class CustomCategory implements Category {
    private String categoryName;
    private int categoryIcon;
    private int categoryBackground;

    public CustomCategory(String categoryName, int categoryIcon, int categoryBackground) {
        this.categoryName = categoryName;
        this.categoryIcon = categoryIcon;
        this.categoryBackground = categoryBackground;
    }

    @Override
    public void createCategory(CategoryItemsDataBase database) {
        database.CreateCategory(categoryName, categoryIcon, categoryBackground);
    }
}

