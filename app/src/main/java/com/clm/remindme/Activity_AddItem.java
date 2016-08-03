package com.clm.remindme;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.*;
import android.widget.ListView;

public class Activity_AddItem extends AppCompatActivity {
    ListView addItemsLV;
    mCommands commands;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        //////////// link Activity_ListView to Cursor from DB
        commands =new mCommands(this);
        Cursor cursor=commands.getDbQuery(mConstants.DB_ITEM_TABLE);
        mItemCursorAdapter adapter=new mItemCursorAdapter(this,cursor,0);

        addItemsLV= (ListView) findViewById(R.id.addItemsLV);
        addItemsLV.setAdapter(adapter);







    }
}
