package com.example.caejay.appetizer;


import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


//the page for menu item
public class ItemPage extends AppCompatActivity {
    private DbHelper dbHelper;
    private SQLiteDatabase sqLiteDatabase;
    private Context context = this;
    String tableName="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        tableName=getIntent().getExtras().getString("tableName");

        TextView name= (TextView)findViewById(R.id.pageName);


        //get item chosen desired from ItemList
        name.setText(getIntent().getExtras().getString("nameData"));
        //gets id of photo for item
        int id = getResources().getIdentifier(getIntent().getExtras().getString("picData"), "drawable", getPackageName());

        //set food pic
        ImageView foodPic = (ImageView)findViewById(R.id.itemImage);
        foodPic.setImageResource(id);

        //set info
        TextView info= (TextView)findViewById(R.id.itemInfo);
        info.setText(getIntent().getExtras().getString("infoData"));



    }
    //when + clicked add order
    public void addOrder(View view)
    {
        TextView item = (TextView) findViewById(R.id.pageName);
        dbHelper = new DbHelper(context);
        sqLiteDatabase = dbHelper.getWritableDatabase();
        String itemName = item.getText().toString();
        dbHelper.addOrder(tableName,itemName);
        dbHelper.close();

        Intent intent = new Intent(this, Service.class);
        intent.putExtra("tableName",tableName);
        startActivityForResult(intent, 1);




    }

}
