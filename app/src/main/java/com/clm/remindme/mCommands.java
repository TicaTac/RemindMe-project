package com.clm.remindme;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

import java.io.ByteArrayOutputStream;

/**
 * Created by CLM on 03/08/2016.
 */
public class mCommands {
    mDbHelper helper;
    Context context;

    public mCommands(Context context) {
        helper=new mDbHelper(context);
        this.context = context;

        if (getDbCount(mConstants.DB_HISTORY_TABLE)==0){
            mItem item = new mItem("Left Feeding","",null,getTimeStamp());
            addToDb(mConstants.DB_HISTORY_TABLE,item);


        }

        if (getDbCount(mConstants.DB_ITEM_TABLE)==0){
            mItem item = new mItem("Left Feeding","",null,getTimeStamp());
            addToDb(mConstants.DB_ITEM_TABLE,item);


        }
    }

    public Cursor getDbQuery(String table)
    {
        Cursor cursor;
        cursor=helper.getReadableDatabase().query(table,null,null,null,null,null,null);
        return cursor;
    }

    public Cursor getDbQuery(String table,int dbID)
    {
        Cursor cursor;
        cursor=helper.getReadableDatabase().query(table,new String[]{mConstants.DB_ID},null,null,null,null,null);
        return cursor;
    }

    public int getDbCount(String table)
    {
        Cursor cursor=helper.getReadableDatabase().query(table,new String[] {mConstants.DB_ID},null,null,null,null,null);
        int count =0;
        while (cursor.moveToNext())
        {
            count++;
        }
        return count;
    }


    public void updateDb(String table, mItem item){}

    public void clearDb(String table){}

    public static String getTimeStamp()
    {
        return "00:00 16/03/16";
    }
    public void addToDb(String table, mItem item)
    {

        ContentValues cv = new ContentValues();
        switch (table)
        {
            case mConstants.DB_HISTORY_TABLE :
                cv.put(mConstants.DB_HISTORY_ITEM,item.name);
                cv.put(mConstants.DB_HISTORY_DESC,item.desc);
                cv.put(mConstants.DB_HISTORY_IMAGE,item.image);
                cv.put(mConstants.DB_HISTORY_TIME,item.timeStamp);

                break;

            case mConstants.DB_ITEM_TABLE:
                cv.put(mConstants.DB_ITEM_NAME,item.name);
                cv.put(mConstants.DB_ITEM_IMAGE,item.image);
                break;
        }

        helper.getWritableDatabase().insert(table,null,cv);
    }


    public static String encodeBitmapToString(Bitmap bitmap)
    {
        ByteArrayOutputStream baos=new  ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100, baos);
        byte [] b=baos.toByteArray();
        String temp= Base64.encodeToString(b, Base64.DEFAULT);
        return temp;
    }

    public static Bitmap encodeStringToBitmap(String encodedString)
    {
        try{
            byte [] encodeByte=Base64.decode(encodedString,Base64.DEFAULT);
            Bitmap bitmap= BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
            return bitmap;
        }catch(Exception e){
            e.getMessage();
            return null;
        }
    }

}
