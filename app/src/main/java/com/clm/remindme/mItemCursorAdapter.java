package com.clm.remindme;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;

/**
 * Created by CLM on 03/08/2016.
 */
public class mItemCursorAdapter extends CursorAdapter {

    public mItemCursorAdapter(Context context, Cursor c, int flags) {
        super(context, c, flags);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        
        View v=null

        return v;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {

    }
}
