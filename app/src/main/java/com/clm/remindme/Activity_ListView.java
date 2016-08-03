package com.clm.remindme;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;

public class Activity_ListView extends AppCompatActivity {
    ListView historyLV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

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

        historyLV=(ListView) findViewById(R.id.historyLV);

        mCommands commands = new mCommands(this);


        Cursor cursor = commands.getDbQuery(mConstants.DB_HISTORY_TABLE);
        mHistoryCursorAdapter adapter = new mHistoryCursorAdapter(this,cursor,0);




        historyLV.setAdapter(adapter);






    }

}
