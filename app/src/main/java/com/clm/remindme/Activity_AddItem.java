package com.clm.remindme;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;
import android.widget.ListView;

public class Activity_AddItem extends AppCompatActivity {
    ListView addItemsLV;
    mCommands commands;


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.additem_options,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.exitItem :
                finish();
                break;

            case R.id.clearDBItem:
                commands.clearDb(mConstants.DB_ITEM_TABLE);
                refreshList();
                break;

        }


        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        //////////// link Activity_ListView to Cursor from DB
        commands =new mCommands(this);
        addItemsLV= (ListView) findViewById(R.id.addItemsLV);
        refreshList();

        addItemsLV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                // add item to history list with timestamp

                int dbID;
                Cursor cursor= commands.getDbQuery(mConstants.DB_ITEM_TABLE);
                if (cursor.moveToPosition(position)) {
                    dbID = cursor.getInt(cursor.getColumnIndexOrThrow(mConstants.DB_ID));
                    mItem item=new mItem(
                            cursor.getString(cursor.getColumnIndexOrThrow(mConstants.DB_ITEM_NAME)),
                            "",
                            cursor.getString(cursor.getColumnIndexOrThrow(mConstants.DB_ITEM_IMAGE)),
                            commands.getTimeStamp()
                            );


                    commands.addToDb(mConstants.DB_HISTORY_TABLE,item);
                    refreshList();
                    finish();

                }
                else  dbID=-1;

            }
        });

        addItemsLV.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                /// clear item or open a context menu or add item with Description ?
                int dbID;
                Cursor cursor= commands.getDbQuery(mConstants.DB_ITEM_TABLE);
                if (cursor.moveToPosition(position)) {
                    dbID = cursor.getInt(cursor.getColumnIndexOrThrow(mConstants.DB_ID));
                }
                else  dbID=-1;
                Log.d("*AddItem","ListItem : "+position+" LongClicked");
                commands.deleteItemFromDb(mConstants.DB_ITEM_TABLE,dbID);
                refreshList();
                return true;
            }
        });




    }

    public void refreshList()
    {


        Cursor cursor=commands.getDbQuery(mConstants.DB_ITEM_TABLE);
        mItemCursorAdapter adapter=new mItemCursorAdapter(this,cursor,0);

        addItemsLV.setAdapter(adapter);


    }
}
