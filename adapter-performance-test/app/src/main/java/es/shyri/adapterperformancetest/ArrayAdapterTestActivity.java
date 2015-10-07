package es.shyri.adapterperformancetest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;

import es.shyri.adapterperformancetest.adapter.NewsArrayAdapter;
import es.shyri.adapterperformancetest.database.NewsEntriesDataSource;
import es.shyri.adapterperformancetest.model.NewsEntry;

/**
 * Created by Shyri on 07/10/2015.
 */
public class ArrayAdapterTestActivity extends AppCompatActivity {
    NewsEntriesDataSource datasource;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_array_adapter_test);
        datasource = new NewsEntriesDataSource(this);
        datasource.open();

        ArrayList<NewsEntry> values = datasource.getAllNewsEntries();

        // use the SimpleCursorAdapter to show the
        // elements in a ListView
        NewsArrayAdapter adapter = new NewsArrayAdapter(this, values);
        ((ListView) findViewById(R.id.newsList)).setAdapter(adapter);
    }


    @Override
    protected void onResume() {
        datasource.open();
        super.onResume();

    }

    @Override
    protected void onPause() {
        datasource.close();
        super.onPause();
    }

    private void initDatabase() {
        for (int i =0; i < 250; i++) {
            datasource.createNewsEntry("Title " + i, "Subtitle: " + i, "Body " + i);
        }
    }
}
