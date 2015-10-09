package es.shyri.adapterperformancetest.adapter;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import es.shyri.adapterperformancetest.R;

/**
 * Created by Shyri on 08/10/2015.
 */
public class NewsCursorAdapter extends CursorAdapter {

    public NewsCursorAdapter(Context context, Cursor c, boolean autoRequery) {
        super(context, c, autoRequery);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.news_item_list_item, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        // Lookup view for data population
        TextView textTitle = (TextView) view.findViewById(R.id.textTitle);
        TextView textSubtitle = (TextView) view.findViewById(R.id.textSubtitle);
        // Populate the data into the template view using the data object
        textTitle.setText(cursor.getString(0));
        textSubtitle.setText(cursor.getString(1));
        // Return the completed view to render on screen
    }
}
