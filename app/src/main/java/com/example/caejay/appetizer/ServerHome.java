package com.example.caejay.appetizer;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.TextView;

import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TableRow;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;
//where user manages tables and todos
public class ServerHome extends AppCompatActivity {
    private GoogleApiClient client;
    private static int NUM_ROWS = 0;
    private static int NUM_COLS = 3;
    private static int numTable=0;
    private GridView gridView;
    private ListView listView;

    SQLiteDatabase sqLiteDatabase;
    DbHelper dbHelper;
    Cursor cursor;

    ImageAdapter imageAdapter;
    ArrayList<Table> list= new ArrayList<Table>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_server_home);
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
        populateButtons();
        populateList();


        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedName = (((TextView) view.findViewById(R.id.tableName)).getText()).toString();
                openTable(view, selectedName);
            }
        });


    }
    public void onResume()
    {
        super.onResume();
        populateButtons();
        populateList();

    }
    public void newTable(View view)
    {
        Intent intent = new Intent(this, TableNameInput.class);
        startActivityForResult(intent, 1);

    }
    private void openTable(View view,String name)
    {
        //get selected item name
        TextView tableName = (TextView)findViewById(R.id.tableName);

        //find chosen item in list

        //pass chosen item info to ItemPage Activity
        Intent intent = new Intent(this, Service.class);
        intent.putExtra("tableName",name);
        startActivityForResult(intent, 1);
    }
    public void clearOrders(View view)
    {
        dbHelper = new DbHelper(getApplicationContext());
        sqLiteDatabase = dbHelper.getReadableDatabase();
        dbHelper.removeAll(sqLiteDatabase);
    }
    public void deleteTodo(View view)
    {
        String oNumber =  (String) view.getTag();

        //Log.d("hello",oNumber);

        dbHelper = new DbHelper(getApplicationContext());
        sqLiteDatabase = dbHelper.getWritableDatabase();
        dbHelper.deleteTodo(oNumber);
        dbHelper.close();
        recreate();
    }
    //populate list of todos
    private void populateList(){
        listView = (ListView) findViewById(R.id.todoListView);
        TodoListAdapter todoListAdapter= new TodoListAdapter(this, R.layout.todo_swipe_item);
        listView.setAdapter(todoListAdapter);

        dbHelper = new DbHelper(getApplicationContext());
        sqLiteDatabase = dbHelper.getReadableDatabase();
        cursor = dbHelper.getTodo(sqLiteDatabase);
        //cursor =dbHelper.getAllData();
        if(cursor.moveToFirst()) {
            do {
                //tab= tab + cursor.getString(1)+"\n";
                TodoItem item=new TodoItem();
                item.setId(cursor.getInt(0));
                item.setTName(cursor.getString(1));
                item.setTodo(cursor.getString(2));
                todoListAdapter.add(item);


            }
            while (cursor.moveToNext());// true if there is rows after
        }
        dbHelper.close();
        //list of items to be displayed
        //list= new ArrayList<MenuItem>();

    }

    //populate list of tables
    private void populateButtons() {

        gridView = (GridView) findViewById(R.id.homegridview);
        ImageAdapter imageAdapter = new ImageAdapter(this, R.layout.activity_server_home);
        gridView.setAdapter(imageAdapter);
        //list of items to be displayed
        dbHelper = new DbHelper(getApplicationContext());
        sqLiteDatabase = dbHelper.getReadableDatabase();
        cursor = dbHelper.getTableInfo(sqLiteDatabase);
        list = new ArrayList<Table>();
        /*
        Table table = new Table("Table 1");
        list.add(table);
        table = new Table("Table 2");
        list.add(table);
        table = new Table("Table 3");
        list.add(table);
        table = new Table("Table 4");
        list.add(table);
        list.add(table); list.add(table);
        list.add(table);  list.add(table);
        list.add(table);
        list.add(table); list.add(table);
        list.add(table);  list.add(table);*/
        String tname;
        if(cursor.moveToFirst()) {
            do {
                tname=cursor.getString(0);
                Table table=new Table(tname);

                imageAdapter.add(table);

            }
            while (cursor.moveToNext()); // true if there is rows after
        }




        /*int i;
        for(i=0;i<list.size();i++) {

            imageAdapter.add(list.get(i));

        }*/


    }






}
