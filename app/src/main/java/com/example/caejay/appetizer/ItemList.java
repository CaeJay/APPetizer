package com.example.caejay.appetizer;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

//this is the list of menu items
public class ItemList extends AppCompatActivity {

    private ListView listView;
    ItemListAdapter itemListAdapter;
    private DbHelper dbHelper;
    private SQLiteDatabase sqLiteDatabase;
    private Context context = this;
    ArrayList<MenuItem> list= new ArrayList<MenuItem>();
    String tableName ="";




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_list);

        tableName=getIntent().getExtras().getString("tableName");

        //get itemType desired from FoodMenu
        String type=getIntent().getExtras().getString("itemType");

        //creates list of items according to type
        initializeList(type);

        //when specific item gets clicked
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                MenuItem select = (MenuItem)listView.getItemAtPosition(position);
                String selectedName = select.getName();
                        //(((TextView) view.findViewById(R.id.itemName)).getText()).toString();

                   openItemPage(view, selectedName);



            }
        });




    }

    //Create List of Items based on type
    public void initializeList (String type) {
        listView = (ListView) findViewById(R.id.itemListView);
        itemListAdapter= new ItemListAdapter(this, R.layout.list_item);
        listView.setAdapter(itemListAdapter);
        //list of items to be displayed
        list= new ArrayList<MenuItem>();

        //Create temporary menu to display until database is created
        MenuItem entree= new MenuItem("Thai Fried Rice","thai_fried_rice","Comes with carrots, green onion, white onion, cilantro, bell peppers.\n" +
                "Calories 314, Can get chicken, beef, shrimp, pork, seafood, or combo");

        MenuItem entree2= new MenuItem("Pad See Ew","pad_see_ew","Comes with flat rice noodles, green onion, white onion, cilantro, bell peppers.\n" +
                "Calories 314, Can get chicken, beef, shrimp, pork, seafood, or combo");
        MenuItem entree3= new MenuItem("Green Curry","greencurry_img","Green Curry, Comes with white or fried rice. Can get chicken, beef, shrimp, pork, seafood, or combo");
        MenuItem drink= new MenuItem("Thai Tea","thaitea","Really Good Thai Tea");
        MenuItem drink2= new MenuItem("Thai Ice Coffee","thaiicecoffee","Really Good Coffee");
        MenuItem dessert= new MenuItem("Green Tea Parfait","greenteaparfait","Delicious");
        MenuItem dessert2= new MenuItem("Sticky Rice With Mango","stickyricewithmango","Seasonal");

        MenuItem app= new MenuItem("Fresh Roll","freshroll","Mmmmmmmm");

        //add to display list if item is of type
        if (type.equals("entrees")) {
            list = new ArrayList<MenuItem>();
            list.add(entree);
            list.add(entree2);
            list.add(entree3);
        }
        if (type.equals("drinks"))
        {
            list.add(drink);
            list.add(drink2);

        }
        if (type.equals("desserts"))
        {
            list.add(dessert);
            list.add(dessert2);

        }
        if (type.equals("apps"))
        {
            list.add(app);
        }

        //add display items to gui
        int i;
        for(i=0;i<list.size();i++) {

            itemListAdapter.add(list.get(i));

        }


    }
    public void addOrder(View view)
    {
        String item =  (String) view.getTag();


        dbHelper = new DbHelper(context);
        sqLiteDatabase = dbHelper.getWritableDatabase();
        dbHelper.addOrder(tableName,item);
        dbHelper.close();

        setResult(RESULT_OK, null);
        finish();


    }
    //open itemPage when selected
    public void openItemPage (View view, String name) {

        //get selected item name
        TextView itemName = (TextView)findViewById(R.id.itemName);
        int i;
        //find chosen item in list
        MenuItem chosenOne = new MenuItem("","","");
        for(i=0;i<list.size();i++) {
            if (name.equals(list.get(i).getName()))
            {
                chosenOne.setName(list.get(i).getName());
                chosenOne.setPicName(list.get(i).getPicName());
                chosenOne.setInfo(list.get(i).getInfo());

            }
        }
        //pass chosen item info to ItemPage Activity
        Intent intent = new Intent(this, ItemPage.class);
        intent.putExtra("nameData",chosenOne.getName());
        intent.putExtra("picData",chosenOne.getPicName());
        intent.putExtra("infoData",chosenOne.getInfo());
        intent.putExtra("tableName",tableName);
        startActivityForResult(intent, 1);
    }



}
