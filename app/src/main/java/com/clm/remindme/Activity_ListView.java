package com.clm.remindme;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

public class Activity_ListView extends AppCompatActivity {
    ListView historyLV;
    mCommands commands;

    @Override
    protected void onResume() {
        super.onResume();
        refreshList();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.exitItem :
                finish();
                break;
            case R.id.clearDBItem:
                commands.clearDb(mConstants.DB_HISTORY_TABLE);
                refreshList();
                break;

        }

        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        refreshList();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.history_options,menu);
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);

        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        commands = new mCommands(this);
        historyLV=(ListView) findViewById(R.id.historyLV);

        refreshList();



        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                Intent intent = new Intent(Activity_ListView.this,Activity_AddItem.class);
                startActivityForResult(intent,1);
            }
        });


        historyLV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(Activity_ListView.this,"Click "+position,Toast.LENGTH_SHORT).show();
            }
        });

        historyLV.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {


                Toast.makeText(Activity_ListView.this,"LongClick "+position,Toast.LENGTH_SHORT).show();
                return true;
            }
        });








    }

    public void refreshList()
    {

        Cursor cursor = commands.getDbQueryDescending(mConstants.DB_HISTORY_TABLE);
        mHistoryCursorAdapter adapter = new mHistoryCursorAdapter(this,cursor,0);
        historyLV.setAdapter(adapter);


    }



}
