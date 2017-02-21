package com.example.caejay.appetizer;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


//this is the customization page for an orderItem
public class Customize extends AppCompatActivity {
    private DbHelper dbHelper;
    private SQLiteDatabase sqLiteDatabase;
    private Context context = this;
    Integer orderNum=0;
    String tableName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //get table name
        tableName=getIntent().getExtras().getString("tableName");

        setContentView(R.layout.customize);
       // Button tableInput = (Button) findViewById(R.id.okButton);
       // tableInput.setText(getIntent().getExtras().getString("orderNum"));
        orderNum=getIntent().getIntExtra("orderNumber",0);
    }
    //when ok button hit the service page will re-open
    public void openMenu(View view)
    {
        //get data input
        EditText tableInput = (EditText) findViewById(R.id.customize);
        String custom = tableInput.getText().toString();

        //edit table in database
        editTable(custom,orderNum);


        /*Intent intent = new Intent(this, Service.class);
        intent.putExtra("tableName",tableName);
        startActivityForResult(intent, 1);*/
    }

    public void editTable(String custom, Integer orderNum)
    {
        //update database
        dbHelper = new DbHelper(context);
        sqLiteDatabase = dbHelper.getWritableDatabase();
        dbHelper.customOrder(custom,orderNum);

        // Event added
        //Toast.makeText(this, "Event added.", Toast.LENGTH_SHORT).show();
        dbHelper.close();
        setResult(RESULT_OK, null);
        finish();




    }

}
