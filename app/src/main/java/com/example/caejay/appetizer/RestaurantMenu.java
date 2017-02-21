package com.example.caejay.appetizer;

/**
 * Created by rosejana on 11/13/16.
 */
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class RestaurantMenu extends Fragment{

    String tableName="";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.menu, container, false);
        Bundle args = getArguments();
        tableName=args.getString("tableName");

        Button entreeButton= (Button)rootView.findViewById(R.id.entrees);
        Button drinkButton= (Button)rootView.findViewById(R.id.drinks);
        Button dessertButton = (Button)rootView.findViewById(R.id.desserts);
        Button appButton = (Button)rootView.findViewById(R.id.appetizers);


        // Create on click listeners for every button
        entreeButton.setOnClickListener(new View.OnClickListener(){
            @Override
            //On click function
            public void onClick(View view) {
                String itemType="";
                //Create the intent to start another activity
                openItemList(view,"entrees");
            }
        });
        drinkButton.setOnClickListener(new View.OnClickListener(){
            @Override
            //On click function
            public void onClick(View view) {
                String itemType="";
                //Create the intent to start another activity
                openItemList(view,"drinks");
            }
        });
        appButton.setOnClickListener(new View.OnClickListener(){
            @Override
            //On click function
            public void onClick(View view) {
                String itemType="";
                //Create the intent to start another activity
                openItemList(view,"apps");
            }
        });
        dessertButton.setOnClickListener(new View.OnClickListener(){
            @Override
            //On click function
            public void onClick(View view) {
                String itemType="";
                //Create the intent to start another activity
                openItemList(view,"desserts");
            }
        });


        return rootView;
    }
    public void openItemList (View view,String itemType) {
        Intent intent = new Intent(getActivity(), ItemList.class);
        intent.putExtra("itemType",itemType);
        intent.putExtra("tableName",tableName);//pass itemType to ItemPage
        startActivityForResult(intent, 1);
    }

}
