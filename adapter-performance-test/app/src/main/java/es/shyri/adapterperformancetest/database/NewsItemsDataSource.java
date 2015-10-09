package es.shyri.adapterperformancetest.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import es.shyri.adapterperformancetest.model.NewsItem;

/**
 * Created by Shyri on 07/10/2015.
 */
public class NewsItemsDataSource {
    private SQLiteDatabase database;
    private MySQLiteHelper dbHelper;
    private String[] allColumns = { MySQLiteHelper.COLUMN_ID, MySQLiteHelper.COLUMN_TITLE, MySQLiteHelper.COLUMN_SUBTITLE, MySQLiteHelper.COLUMN_BODY };

    public NewsItemsDataSource(Context context) {
        dbHelper = new MySQLiteHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public NewsItem createNewsEntry(String title, String subtitle, String body) {
        ContentValues values = new ContentValues();
        values.put(MySQLiteHelper.COLUMN_TITLE, title);
        values.put(MySQLiteHelper.COLUMN_SUBTITLE, subtitle);
        values.put(MySQLiteHelper.COLUMN_BODY, body);
        long insertId = database.insert(MySQLiteHelper.TABLE_NEWS_ENTRIES, null, values);
        Cursor cursor = database.query(MySQLiteHelper.TABLE_NEWS_ENTRIES,
                allColumns, MySQLiteHelper.COLUMN_ID + " = " + insertId, null,
                null, null, null);
        cursor.moveToFirst();
        NewsItem newComment = cursorToNewsEntry(cursor);
        cursor.close();
        return newComment;
    }

    public void deleteNewsEntry(NewsItem newsItem) {
        long id = newsItem.getId();
        System.out.println("Comment deleted with id: " + id);
        database.delete(MySQLiteHelper.TABLE_NEWS_ENTRIES, MySQLiteHelper.COLUMN_ID + " = " + id, null);
    }

    public ArrayList<NewsItem> getAllNewsEntries() {
        ArrayList<NewsItem> newsEntries = new ArrayList<NewsItem>();

        Cursor cursor = database.query(MySQLiteHelper.TABLE_NEWS_ENTRIES,
                allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            NewsItem newsItem = cursorToNewsEntry(cursor);
            newsEntries.add(newsItem);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        return newsEntries;
    }

    public static NewsItem cursorToNewsEntry(Cursor cursor) {
        NewsItem comment = new NewsItem();
        comment.setId(cursor.getLong(0));
        comment.setTitle(cursor.getString(1));
        comment.setSubitle(cursor.getString(2));
        comment.setBody(cursor.getString(3));
        return comment;
    }
}
