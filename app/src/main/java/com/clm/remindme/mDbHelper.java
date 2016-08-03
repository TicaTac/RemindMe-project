package com.clm.remindme;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by CLM on 03/08/2016.
 */
public class mDbHelper extends SQLiteOpenHelper {
    Context c;

    public mDbHelper(Context context) {
        super(context, mConstants.DB_FILE, null, mConstants.DB_VERSION);
        c=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String DbCreateItemTableQuery=
                  "CREATE TABLE "+mConstants.DB_ITEM_TABLE
                + "( "+mConstants.DB_ID+" INTEGER PRIMARY AUTOINCREMENT ,"
                + mConstants.DB_ITEM_NAME+" String, "
                + mConstants.DB_ITEM_IMAGE+" String"
                + " );" ;
        String DbCreateHistoryTableQuery=
                "CREATE TABLE "+mConstants.DB_HISTORY_TABLE
                        + "( "+mConstants.DB_ID+" INTEGER PRIMARY AUTOINCREMENT ,"
                        + mConstants.DB_HISTORY_ITEM+" String, "
                        + mConstants.DB_HISTORY_DESC+" String, "
                        + mConstants.DB_HISTORY_TIME+" String, "
                        + mConstants.DB_HISTORY_IMAGE+" String "
                        + " );" ;
        db.execSQL(DbCreateItemTableQuery);
        db.execSQL(DbCreateHistoryTableQuery);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
