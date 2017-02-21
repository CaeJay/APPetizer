package com.example.caejay.appetizer;

import android.renderscript.Float2;
import android.view.Menu;

/**
 * Created by cj on 10/1/16.
 */

//Items on menu
public class MenuItem {
    private String name; //item name
    private String picName; // filename of picture without extension
    private String info;
    private String price;// item info


    public MenuItem(String name,String picName, String info)
    {
        this.name=name;
        this.picName=picName;
        this.info=info;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPicName() {
        return picName;
    }

    public void setPicName(String picName) {
        this.picName = picName;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
