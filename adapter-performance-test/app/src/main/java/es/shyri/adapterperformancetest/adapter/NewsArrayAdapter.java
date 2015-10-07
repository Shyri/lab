package es.shyri.adapterperformancetest.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import es.shyri.adapterperformancetest.R;
import es.shyri.adapterperformancetest.model.NewsEntry;

/**
 * Created by Shyri on 07/10/2015.
 */
public class NewsArrayAdapter extends ArrayAdapter<NewsEntry> {
    public NewsArrayAdapter(Context context, ArrayList<NewsEntry> newsEntries) {
        super(context, 0, newsEntries);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        NewsEntry newsEntry = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.news_entry_list_item, parent, false);
        }
        // Lookup view for data population
        TextView textTitle = (TextView) convertView.findViewById(R.id.textTitle);
        TextView textSubtitle = (TextView) convertView.findViewById(R.id.textSubtitle);
        // Populate the data into the template view using the data object
        textTitle.setText(newsEntry.getTitle());
        textSubtitle.setText(newsEntry.getSubitle());
        // Return the completed view to render on screen
        return convertView;
    }
}