package com.example.caejay.appetizer;

/**
 */
import android.app.Fragment;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.*;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.ScrollView;
import android.widget.Toast;

import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.server.converter.StringToIntConverter;

// This is a tab in service. This shows everything that has been ordered by a table.
public class CustomerTab extends Fragment{
    private static final String ARG_SECTION_NUMBER = "section_number";
    String tableName;
    TextView name,tab;
    SQLiteDatabase sqLiteDatabase;
    DbHelper dbHelper;
    ScrollView scroller;
    Cursor cursor;
    private ListView listView;
    private View rootView = null;

    /*public static RestaurantMenu newInstance(int sectionNumber) {
        RestaurantMenu fragment = new RestaurantMenu();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }*/


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
         inflater = getActivity().getLayoutInflater();

        rootView = inflater.inflate(R.layout.tab, container, false);
        //get table clicked on
        Bundle args = getArguments();
        tableName=args.getString("tableName");
        name = (TextView)rootView.findViewById(R.id.tabTname);
        name.setText(tableName);

        //create list of orders
        listView = (ListView) rootView.findViewById(R.id.tabItemList);
        getOrders();

        //opens customization page when order item clicked
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                OrderItem item =(OrderItem) listView.getItemAtPosition(position);
                //String itemNum = (((TextView) view.findViewById(R.id.tabItemNumber)).getText()).toString();
                //Toast toast = Toast.makeText(getActivity().getApplicationContext(), selected, Toast.LENGTH_SHORT);
                //toast.show();
                openOptions(view,item.getNum());

            }
        });



        return rootView;
    }
    //when order item clicked, open options
    public void openOptions(View view, int orderNum)
    {


        Intent intent = new Intent(getActivity(), OrderItemOptions.class);
        intent.putExtra("orderNumber",orderNum);
        intent.putExtra("tableName",tableName);
        startActivityForResult(intent, 1);

    }
    @Override
    public void onResume()
    {  // After a pause OR at startup
        super.onResume();
        getOrders();
        //Refresh your stuff here
    }

    // get orders from database
    public void getOrders()
    {
        //set list adapter
        TabListAdapter tabListAdapter = new TabListAdapter(getActivity(), R.layout.tab_items);
        listView.setAdapter(tabListAdapter);


        // get data from database and put in list
        dbHelper = new DbHelper(getActivity().getApplicationContext());
        sqLiteDatabase = dbHelper.getReadableDatabase();
        cursor = dbHelper.getOrderInfo(sqLiteDatabase,tableName);
        if(cursor.moveToFirst()) {
            do {
                //tab= tab + cursor.getString(1)+"\n";
                OrderItem item=new OrderItem();
                item.setNum(cursor.getInt(0));
                item.setName(cursor.getString(2));
                item.setInfo(cursor.getString(3));
                item.setPrice("tba");
                tabListAdapter.add(item);


            }
            while (cursor.moveToNext());// true if there is rows after
        }
        dbHelper.close();



    }

}
