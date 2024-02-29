package com.example.onlineshopping.AdaptersAndModels;

public class CategoryModel {

    String Categoryname;
    int CategoryPhoto;
    int Categorybackground;


    public CategoryModel(String Categoryname, int CategoryPhoto, int Categorybackground) {
        this.Categoryname = Categoryname;
        this.CategoryPhoto = CategoryPhoto;
        this.Categorybackground = Categorybackground;
    }

    public String getCategoryname() {
        return Categoryname;
    }

    public int getCategoryPhoto() {
        return CategoryPhoto;
    }

    public int getCategorybackground() {
        return Categorybackground;
    }

}