package com.example.caejay.appetizer;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.app.Fragment;

import android.widget.Button;
import android.widget.EditText;

public class Todo extends Fragment {
    String tableName;
    private DbHelper dbHelper;
    private SQLiteDatabase sqLiteDatabase;
    //private Context context = this;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.todo, container, false);
        Bundle args = getArguments();
        tableName = args.getString("tableName");
        Button ok= (Button) rootView.findViewById(R.id.okButton);
        ok.setOnClickListener(new View.OnClickListener(){
            @Override
            //On click function
            public void onClick(View view) {
                //Create the intent to start another activity
                openMenu(view);
            }
        });

        return rootView;

    }
    public void openMenu(View view)
    {
        EditText tableInput = (EditText) getActivity().findViewById(R.id.todoEdit);
        //Intent intent = new Intent(this, MainActivity.class);
        String todo = tableInput.getText().toString();
        addTodo(todo);
        /*Intent intent = new Intent(getActivity(), Service.class);
        intent.putExtra("tableName",tableName);
        startActivityForResult(intent, 1);*/
    }


    public void addTodo(String todo)
    {
        dbHelper = new DbHelper(getActivity().getApplicationContext());
        sqLiteDatabase = dbHelper.getWritableDatabase();
        dbHelper.addTodo(tableName,todo);

        // Event added
        //Toast.makeText(this, "Event added.", Toast.LENGTH_SHORT).show();
        dbHelper.close();
        getActivity().setResult(Activity.RESULT_OK, null);
        getActivity().finish();



    }
}
