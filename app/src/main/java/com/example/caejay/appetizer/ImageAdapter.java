package com.example.caejay.appetizer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rosejana on 10/21/16.
 */

//this is the adaoter for the grid of tables

public class ImageAdapter extends ArrayAdapter {

    public ImageAdapter(Context context, int resource) {
        super(context, resource);
    }
    private int layout;
    private List list = new ArrayList();

    static class LayoutHandler
    {
        TextView tableName;
        ImageView tableButton;
    }
    public void add(Object object) {
        super.add(object);
        list.add(object);
    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View row = convertView;
        ImageAdapter.LayoutHandler layoutHandler;
        if(row == null){
            LayoutInflater layoutInflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = layoutInflater.inflate(R.layout.tablebutton, parent, false);
            layoutHandler = new ImageAdapter.LayoutHandler();

            layoutHandler.tableName = (TextView) row.findViewById(R.id.tableName);
            layoutHandler.tableButton = (ImageView) row.findViewById(R.id.tableButton);



            row.setTag(layoutHandler);


        }

        else{
            layoutHandler = (ImageAdapter.LayoutHandler) convertView.getTag();
        }

        Table table = (Table) this.getItem(position);
        layoutHandler.tableName.setText(table.getTableLabel());

        return row;
    }
}
