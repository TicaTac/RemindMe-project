package com.clm.remindme;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by CLM on 03/08/2016.
 */
public class mHistoryCursorAdapter extends CursorAdapter {

    public mHistoryCursorAdapter(Context context, Cursor c, int flags) {
        super(context, c, flags);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View v=inflater.inflate(R.layout.single_history_item,null);

        return v;

    }


    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView actionNameTV = (TextView) view.findViewById(R.id.actionHistoryTV);
        String actionName = cursor.getString(cursor.getColumnIndexOrThrow(mConstants.DB_HISTORY_ITEM));
        actionNameTV.setText(actionName);

        ImageView iconIV = (ImageView) view.findViewById(R.id.iconHistoryIV);
        String image = cursor.getString(cursor.getColumnIndexOrThrow(mConstants.DB_HISTORY_IMAGE));
        iconIV.setImageBitmap(mCommands.encodeStringToBitmap(image));

        TextView timeTV = (TextView) view.findViewById(R.id.timeTV);
        timeTV.setText(cursor.getString(cursor.getColumnIndexOrThrow(mConstants.DB_HISTORY_TIME)));

        TextView descTV = (TextView) view.findViewById(R.id.descriptionTV);
        descTV.setText(cursor.getString(cursor.getColumnIndexOrThrow(mConstants.DB_HISTORY_DESC)));

    }
}
