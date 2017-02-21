package com.example.caejay.appetizer;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.database.sqlite.SQLiteDatabase;
import android.content.Context;
import android.widget.Toast;



public class TableNameInput extends Activity {

    private DbHelper dbHelper;
    private SQLiteDatabase sqLiteDatabase;
    private Context context = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table_name_input);
    }
    public void openMenu(View view)
    {
        EditText tableInput = (EditText) findViewById(R.id.tableNameInput);
        //Intent intent = new Intent(this, MainActivity.class);
        Intent intent = new Intent(this, ServerHome.class);
        String tableName = tableInput.getText().toString();
        createTable(tableName);
        //intent.putExtra("nameData",tableName);
        startActivityForResult(intent, 1);
    }
    public void createTable(String tableName)
    {
        dbHelper = new DbHelper(context);
        sqLiteDatabase = dbHelper.getWritableDatabase();
        dbHelper.addTable(tableName);

        // Event added
        Toast.makeText(this, "Event added.", Toast.LENGTH_SHORT).show();
        dbHelper.close();

        setResult(RESULT_OK, null);
        finish();


    }


}
