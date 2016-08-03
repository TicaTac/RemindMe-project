package com.clm.remindme;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;

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
        View v=inflater.inflate(R.layout.single_actions_item, null);
        return v;

    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView actionNameTV = (TextView) view.findViewById(R.id.actionItemTV);
        String actionName = cursor.getString(cursor.getColumnIndexOrThrow(mConstants.DB_ITEM_NAME));
        actionNameTV.setText(actionName);

        ImageView iconIV = (ImageView) view.findViewById(R.id.iconItemIV);
        String image = cursor.getString(cursor.getColumnIndexOrThrow(mConstants.DB_ITEM_IMAGE));
        iconIV.setImageBitmap(mCommands.encodeStringToBitmap(image));


    }
}
