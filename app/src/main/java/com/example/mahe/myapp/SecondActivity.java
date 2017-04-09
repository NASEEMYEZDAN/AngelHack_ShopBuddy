package com.example.mahe.myapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SecondActivity extends AppCompatActivity {

    AutoCompleteTextView actv ;
    List<String> items;
    ItemManager itemManager;
    ShopManager shopManager;
    ItemAndShopManager itemAndShopManager;

    String[] additem;
    ListView mainListView ;
    ArrayAdapter<String> listAdapter ;
    int []price;
    int []finalPrice;
    int i=0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        actv = (AutoCompleteTextView) findViewById(R.id.actv);

        itemManager = new ItemManager(this);
        itemManager = itemManager.open();


        //itemManager.clearTable();
        itemManager.insertEntry(1,"Rice");
        itemManager.insertEntry(2,"Wheat");
        itemManager.insertEntry(3,"Roasted peanuts");
        itemManager.insertEntry(4,"Apples");
        itemManager.insertEntry(5,"Tomato");
        itemManager.insertEntry(6,"Chips");
        itemManager.insertEntry(7,"Roasted Almonds");
        itemManager.insertEntry(8,"Tomato Sauce");
        itemManager.insertEntry(9,"Soya Sauce");
        itemManager.insertEntry(10,"Jackfruit");
        itemManager.insertEntry(11,"Chillies");
        itemManager.insertEntry(12,"Potato");
        itemManager.insertEntry(13,"Maida");
        itemManager.insertEntry(14,"Rava");
        itemManager.insertEntry(15,"Oreo");
        itemManager.insertEntry(16,"Jam");
        itemManager.insertEntry(17,"Beef");
        itemManager.insertEntry(18,"Milk");
        itemManager.insertEntry(19,"Maggi");
        itemManager.insertEntry(20,"Boost");

         //itemManager.close();

        shopManager=new ShopManager(this);
        shopManager=shopManager.open();

        //shopManager.clearTable();
        shopManager.insertEntry(1,"More");
        shopManager.insertEntry(2,"Hyper Market");
        shopManager.insertEntry(3,"Big Baazaar");
        shopManager.insertEntry(4,"Spar");
        shopManager.insertEntry(5,"Spencer's");

        itemAndShopManager=new ItemAndShopManager(this);
        itemAndShopManager=itemAndShopManager.open();

        itemAndShopManager.insertEntry(1,1,50);
        itemAndShopManager.insertEntry(2,1,40);
        itemAndShopManager.insertEntry(3,1,70);
        itemAndShopManager.insertEntry(4,1,80);
        itemAndShopManager.insertEntry(5,1,20);
        itemAndShopManager.insertEntry(6,1,35);
        itemAndShopManager.insertEntry(15,1,32);
        itemAndShopManager.insertEntry(1,2,25);
        itemAndShopManager.insertEntry(2,2,30);
        itemAndShopManager.insertEntry(3,2,45);
        itemAndShopManager.insertEntry(5,2,60);
        itemAndShopManager.insertEntry(6,2,90);
        itemAndShopManager.insertEntry(7,2,40);
        itemAndShopManager.insertEntry(2,3,25);
        itemAndShopManager.insertEntry(3,3,60);
        itemAndShopManager.insertEntry(4,3,55);
        itemAndShopManager.insertEntry(5,3,65);
        itemAndShopManager.insertEntry(7,3,62);
        itemAndShopManager.insertEntry(10,3,15);
        itemAndShopManager.insertEntry(15,3,70);
        itemAndShopManager.insertEntry(16,3,85);
        itemAndShopManager.insertEntry(2,4,23);
        itemAndShopManager.insertEntry(4,4,80);
        itemAndShopManager.insertEntry(5,4,32);
        itemAndShopManager.insertEntry(6,4,38);
        itemAndShopManager.insertEntry(7,4,94);
        itemAndShopManager.insertEntry(8,4,26);
        itemAndShopManager.insertEntry(12,4,42);
        itemAndShopManager.insertEntry(13,4,68);
        itemAndShopManager.insertEntry(14,4,92);
        itemAndShopManager.insertEntry(18,4,83);
        itemAndShopManager.insertEntry(20,4,82);
        itemAndShopManager.insertEntry(3,5,63);
        itemAndShopManager.insertEntry(5,5,74);
        itemAndShopManager.insertEntry(6,5,88);
        itemAndShopManager.insertEntry(9,5,49);
        itemAndShopManager.insertEntry(11,5,99);
        itemAndShopManager.insertEntry(12,5,26);
        itemAndShopManager.insertEntry(14,5,68);
        itemAndShopManager.insertEntry(15,5,69);
        itemAndShopManager.insertEntry(18,5,44);
        itemAndShopManager.insertEntry(19,5,56);
        itemAndShopManager.insertEntry(20,5,36);



        /*String s1 = new String("Big Baazaar");
        Log.d("hi","hello")  ;
        Toast.makeText(this, shopManager.getID(s1), Toast.LENGTH_SHORT).show(); */


        items = new ArrayList<String>();
        items = itemManager.getAllEntries();
        String[] array = items.toArray(new String[0]);
        ArrayAdapter aa = new ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, array);
        actv.setAdapter(aa);




        //final String[] items = new String[] {"A","B"};
        final ArrayList<String> itemList = new ArrayList<String>();

        additem = new String[20];
        mainListView = (ListView) findViewById( R.id.lv );


        price=new int[6];
        finalPrice=new int[6];
        for(int j=1;j<6;j++) finalPrice[j]=0;
        actv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                   int id1;
                String selectedItem = (String) parent.getItemAtPosition(position);
                //listAdapter.add(selectedItem);
                //additem[i++]=selectedItem;
                id1=Integer.valueOf(itemManager.getID(selectedItem));
                price=itemAndShopManager.getPrices(id1);
                for(int i=1;i<6;i++) finalPrice[i]=price[i];

                itemList.add(selectedItem);
                listAdapter = new ArrayAdapter<String>(SecondActivity.this, android.R.layout.simple_list_item_1, itemList);
                mainListView.setAdapter(listAdapter);

                actv.setText("");


            }
        });













        //listAdapter = new ArrayAdapter<String>(SecondActivity.this, R.layout.activity_second, itemList);
        //mainListView.setAdapter(listAdapter);
    }
    public void openNext(View view) {

        Intent i = new Intent(this, ThirdActivity.class);
        String[] finalsum = new String[5];
        for (int l = 0; l < 5; l++) {
            finalsum[l] = Integer.toString(finalPrice[l+1]);


        }
        //Toast.makeText(this, "shop 1 : "+finalPrice[0], Toast.LENGTH_SHORT).show();
        Bundle b = new Bundle();
        b.putStringArray("key1", finalsum);
        i.putExtras(b);
        startActivity(i);

    }



}
