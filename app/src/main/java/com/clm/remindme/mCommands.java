package com.clm.remindme;

import android.content.ContentValues;
import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

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
            mItem item = new mItem("Diaper","",null,getTimeStamp());
            addToDb(mConstants.DB_HISTORY_TABLE,item);
        }

        if (getDbCount(mConstants.DB_ITEM_TABLE)==0){
                addToDb(mConstants.DB_ITEM_TABLE, initDbItems());
        }
    }

    public List<mItem> initDbItems()
    {
        ArrayList<mItem> items=new ArrayList<>();
        Resources r=Resources.getSystem();
        Bitmap defaultImage= BitmapFactory.decodeResource(r,R.drawable.plus);
        String image = encodeBitmapToString(defaultImage);

        items.add(new mItem("Green","",image,getTimeStamp()));
        items.add(new mItem("Violet","",image,getTimeStamp()));
        items.add(new mItem("Morning","",image,getTimeStamp()));
        items.add(new mItem("Noon","",image,getTimeStamp()));
        items.add(new mItem("Evening","",image,getTimeStamp()));
        items.add(new mItem("Left","",image,getTimeStamp()));
        items.add(new mItem("Right","",image,getTimeStamp()));


        return items;
    }


    public Cursor getDbQuery(String table)
    {
        Cursor cursor;
        cursor=helper.getReadableDatabase().query(table,null,null,null,null,null,null);
        return cursor;
    }

    public Cursor getDbQueryDescending(String table)
    {
        Cursor cursor;
        cursor=helper.getReadableDatabase().query(table,null,null,null,null,null,mConstants.DB_ID+" DESC");

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

    public int deleteItemFromDb(String table,int dbID)
    {
        int result= helper.getWritableDatabase().delete(table,mConstants.DB_ID+"=?",new String[]{""+dbID});

        return result;
    }


    public void updateDb(String table, mItem item)
    {


    }

    public boolean clearDb(String table)
    {
        boolean wasDbEmpty =helper.getWritableDatabase().delete(table,"1",null)!=0;
        return (wasDbEmpty);
    }

    public static String getTimeStamp()
    {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm dd/MM/yy");
        sdf.setTimeZone(TimeZone.getDefault());
        String currentDateandTime = sdf.format(new Date());

        return currentDateandTime;
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

    public void addToDb(String table, List<mItem> items)
    {
        for (int i=0; i< items.size(); i++)
        {
            addToDb(table,items.get(i));
        }
    }

    /////////////////////// static _Bitmap to String Encoding_ ////////////////////////////

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
