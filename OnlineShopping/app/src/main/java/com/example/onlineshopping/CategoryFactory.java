package com.example.onlineshopping;

public class CategoryFactory {
    public static Category createCategory(String categoryName, int categoryIcon, int categoryBackground) {
        return new CustomCategory(categoryName, categoryIcon, categoryBackground);
    }
}
