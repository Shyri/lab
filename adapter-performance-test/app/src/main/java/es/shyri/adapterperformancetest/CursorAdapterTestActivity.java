package es.shyri.adapterperformancetest;

import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import es.shyri.adapterperformancetest.adapter.NewsCursorAdapter;
import es.shyri.adapterperformancetest.database.MySQLiteHelper;
import es.shyri.adapterperformancetest.database.NewsItemsProvider;

/**
 * Created by Shyri on 07/10/2015.
 */
public class CursorAdapterTestActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {
    NewsCursorAdapter cursorAdapter;
    ListView listView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cursor_adapter_test);
        listView = ((ListView) findViewById(R.id.newsList));
        getLoaderManager().initLoader(0, null, this);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(
                this,                                                                                                                           // Context
                Uri.parse("content://" + NewsItemsProvider.CONTENT_AUTHORITY).buildUpon().appendPath(NewsItemsProvider.CONTENT_TYPE).build(),   // Uri
                new String[] {MySQLiteHelper.COLUMN_ID, MySQLiteHelper.COLUMN_TITLE, MySQLiteHelper.COLUMN_SUBTITLE},                           // Projection
                null,                                                                                                                           // Selection Params
                null,                                                                                                                           // Selection Args
                null);
    }

    @Override
    public void onResume(){
        super.onResume();
        getLoaderManager().restartLoader(0, null, this);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        cursorAdapter = new NewsCursorAdapter(this, data, false);
        listView.setAdapter(cursorAdapter);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }
}
