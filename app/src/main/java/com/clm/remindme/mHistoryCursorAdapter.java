package com.clm.remindme;

import android.content.Context;
import android.database.Cursor;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;

/**
 * Created by CLM on 03/08/2016.
 */
public class mHistoryCursorAdapter extends CursorAdapter {

    public mHistoryCursorAdapter(Context context, Cursor c, int flags) {
        super(context, c, flags);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        View v=null;

        return v;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {

    }
}
