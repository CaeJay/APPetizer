package com.example.caejay.appetizer;

import java.util.ArrayList;



//yet to be used
//table of customers
public class Table {
    private String tableLabel="";
    private ArrayList<MenuItem> itemsOrdered = new ArrayList<MenuItem>();

    public Table(String tableLabel)//, ArrayList<MenuItem> itemsOrdered)
    {
        this.tableLabel=tableLabel;
        //this.itemsOrdered=itemsOrdered;
    }

    public String getTableLabel() {
        return tableLabel;
    }

    public void setTableLabel(String tableLabel) {
        this.tableLabel = tableLabel;
    }

    public ArrayList<MenuItem> getItemsOrdered() {
        return itemsOrdered;
    }

    public void setItemsOrdered(ArrayList<MenuItem> itemsOrdered) {
        this.itemsOrdered = itemsOrdered;
    }
}
