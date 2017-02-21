package com.example.caejay.appetizer;

/**
 * Created by rosejana on 11/14/16.
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rosejana on 10/1/16.
 */

//adapter for tabs

public class TabListAdapter extends ArrayAdapter {
    private int layout;
    private List list = new ArrayList();

    public TabListAdapter(Context context, int resource){
        super(context,resource);
        layout=resource;
    }
    //
    static class LayoutHandler
    {
        TextView itemName;
        TextView itemPrice;
        TextView custom;
        TextView itemNum;
    }


    @Override
    public void add(Object object) {
        super.add(object);
        list.add(object);
    }




    @Override
    public View getView(final int position, View convertView, ViewGroup parent)
    {

        View row = convertView;
        final LayoutHandler layoutHandler;
        if(row == null){
            LayoutInflater layoutInflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = layoutInflater.inflate(R.layout.tab_items, parent, false);
            layoutHandler = new LayoutHandler();

            //set value for views
            layoutHandler.itemName = (TextView) row.findViewById(R.id.tabItemName);
            layoutHandler.itemPrice = (TextView) row.findViewById(R.id.tabItemPrice);
            layoutHandler.custom = (TextView) row.findViewById(R.id.tabItemCustom);
            layoutHandler.itemNum = (TextView) row.findViewById(R.id.tabItemNumber);



            row.setTag(layoutHandler);


        }

        else{
            layoutHandler = (LayoutHandler) convertView.getTag();
        }

        OrderItem orderItem = (OrderItem) this.getItem(position);
        layoutHandler.itemName.setText(orderItem.getName());
        layoutHandler.itemPrice.setText("N/A");
        layoutHandler.custom.setText(orderItem.getInfo());
        layoutHandler.itemNum.setText(Integer.toString(orderItem.getNum()));


        return row;

    }
    public Object getItem(int position) {
        return list.get(position);
    }




}