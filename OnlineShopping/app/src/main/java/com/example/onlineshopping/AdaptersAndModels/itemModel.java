package com.example.onlineshopping.AdaptersAndModels;

public class itemModel {
    String itemname;
    String itemDesc;
    int itemprice;
    int itemcount;
    int itemphoto;
    int backgrounditem;



    public itemModel(String itemname, String itemDesc, int itemprice, int itemphoto, int backgrounditem , int itemcount) {
        this.itemname = itemname;
        this.itemDesc = itemDesc;
        this.itemprice = itemprice;
        this.itemphoto = itemphoto;
        this.backgrounditem = backgrounditem;
        this.itemcount= itemcount;
    }

    public String getItemname() {
        return itemname;
    }

    public String getItemDesc() {
        return itemDesc;
    }

    public int getItemprice() {
        return itemprice;
    }

    public int getItemphoto() {
        return itemphoto;
    }

    public int getBackgrounditem() {
        return backgrounditem;
    }

    public int getItemcount() {
        return itemcount;
    }
}
