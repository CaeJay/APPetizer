package com.example.caejay.appetizer;

import android.content.ClipData;
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

//adapter for item list

public class ItemListAdapter extends ArrayAdapter {
    private int layout;
    private List list = new ArrayList();
    ItemList itemList=new ItemList();

    public ItemListAdapter(Context context, int resource){
        super(context,resource);
        layout=resource;
    }
    //
    static class LayoutHandler
    {
        TextView itemName;
        ImageButton add;
    }


    @Override
    public void add(Object object) {
        super.add(object);
        list.add(object);
    }

   /* @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.itemName:
                TextView select= (TextView) v.findViewById(R.id.itemName);
                itemList.openItemPage(v,select.getText().toString());
                break;
            case R.id.addButton:;
            default:
                break;
        }
    }

*/

    @Override
    public View getView(final int position, View convertView, ViewGroup parent)
    {

        View row = convertView;
        final LayoutHandler layoutHandler;
        if(row == null){
            LayoutInflater layoutInflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = layoutInflater.inflate(R.layout.list_item, parent, false);
            layoutHandler = new LayoutHandler();

            //set value for views
            layoutHandler.itemName = (TextView) row.findViewById(R.id.itemName);
            layoutHandler.add = (ImageButton) row.findViewById(R.id.addButton);



            row.setTag(layoutHandler);



        }

        else{
            layoutHandler = (LayoutHandler) convertView.getTag();
        }

        MenuItem menuItem = (MenuItem) this.getItem(position);
        layoutHandler.itemName.setText(menuItem.getName());
        layoutHandler.add.setTag(menuItem.getName());

        return row;

    }



}


