package com.example.caejay.appetizer;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.ScrollView;

public class OrderItemOptions extends AppCompatActivity {
    Integer orderNum=0;
    String tableName;
    SQLiteDatabase sqLiteDatabase;
    DbHelper dbHelper;
    ScrollView scroller;
    Cursor cursor;
    private ListView listView;
    private View rootView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_item_options);
        tableName=getIntent().getExtras().getString("tableName");
        orderNum=getIntent().getIntExtra("orderNumber",0);

    }
    public void customize(View view)
    {
        Intent intent = new Intent(this, Customize.class);
        intent.putExtra("orderNumber",orderNum);
        intent.putExtra("tableName",tableName);
    }
    public void deleteOrder(View view)
    {
        dbHelper = new DbHelper(this.getApplicationContext());
        sqLiteDatabase = dbHelper.getReadableDatabase();
        dbHelper.deleteOrder(orderNum);
        dbHelper.close();
        setResult(RESULT_OK, null);
        finish();
    }

}
