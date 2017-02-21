package com.example.caejay.appetizer;

import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.swipe.adapters.ArraySwipeAdapter;
import com.daimajia.swipe.SwipeLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rosejana on 10/1/16.
 */

//helps create list

public class TodoListAdapter extends ArraySwipeAdapter {
    private int layout;
    private List list = new ArrayList();
    ItemList itemList=new ItemList();

    public TodoListAdapter(Context context, int resource){
        super(context,resource);
        layout=resource;

    }
    @Override//return the `SwipeLayout` resource id in your listview | gridview item layout.
    public  int getSwipeLayoutResourceId(int position)
    {
        return R.id.swipe;

    }



    /*fill values to your item layout returned from `generateView`.
      The position param here is passed from the BaseAdapter's 'getView()*/

    static class LayoutHandler
    {
        TextView todo;
        TextView table;
        Button done;
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
            row = layoutInflater.inflate(R.layout.todo_swipe_item, parent, false);
            layoutHandler = new LayoutHandler();

            //set value for views
            layoutHandler.todo = (TextView) row.findViewById(R.id.todoInfoSwipe);
            layoutHandler.table = (TextView) row.findViewById(R.id.todoTableSwipe);
            layoutHandler.done = (Button) row.findViewById(R.id.done);
            SwipeLayout swipeLayout =  (SwipeLayout)row.findViewById(R.id.swipe);

            //set       //show mode.
            swipeLayout.setShowMode(SwipeLayout.ShowMode.LayDown);

            //add       //drag edge.(If the BottomView has 'layout_gravity' attribute, this line is unnecessary)
            swipeLayout.addDrag(SwipeLayout.DragEdge.Left, row.findViewById(R.id.bottom_wrapper));



            row.setTag(layoutHandler);



        }

        else{
            layoutHandler = (LayoutHandler) convertView.getTag();
        }

        TodoItem todoItem = (TodoItem) this.getItem(position);
        layoutHandler.todo.setText(todoItem.getTodo());
        layoutHandler.table.setText(todoItem.getTName());

        layoutHandler.done.setTag(Integer.toString(todoItem.getId()));

        return row;

    }



}


