package com.clm.remindme;

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

    public void updateDb(String table, mItem item){}

    public void clearDb(String table){}

    public void addToDb(String table, mItem item){    }


    public String encodeBitmapToString(Bitmap bitmap)
    {
        ByteArrayOutputStream baos=new  ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100, baos);
        byte [] b=baos.toByteArray();
        String temp= Base64.encodeToString(b, Base64.DEFAULT);
        return temp;
    }

    public Bitmap encodeStringToBitmap(String encodedString)
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
