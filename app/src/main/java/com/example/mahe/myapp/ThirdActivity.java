package com.example.mahe.myapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class ThirdActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        Bundle b=this.getIntent().getExtras();
        String[] finalcost=b.getStringArray("key1");
        ListView lv =(ListView)findViewById(R.id.lv2);

        ArrayAdapter<String> listAdapter=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, finalcost);

        lv.setAdapter(listAdapter);

        //Toast.makeText(this, "shop 1 : "+finalcost[0], Toast.LENGTH_SHORT).show();
    }
}
