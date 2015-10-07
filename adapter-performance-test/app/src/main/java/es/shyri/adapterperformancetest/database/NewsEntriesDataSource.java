package es.shyri.adapterperformancetest.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import es.shyri.adapterperformancetest.model.NewsEntry;

/**
 * Created by Shyri on 07/10/2015.
 */
public class NewsEntriesDataSource {
    private SQLiteDatabase database;
    private MySQLiteHelper dbHelper;
    private String[] allColumns = { MySQLiteHelper.COLUMN_ID, MySQLiteHelper.COLUMN_TITLE, MySQLiteHelper.COLUMN_SUBTITLE, MySQLiteHelper.COLUMN_BODY };

    public NewsEntriesDataSource(Context context) {
        dbHelper = new MySQLiteHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public NewsEntry createNewsEntry(String title, String subtitle, String body) {
        ContentValues values = new ContentValues();
        values.put(MySQLiteHelper.COLUMN_TITLE, title);
        values.put(MySQLiteHelper.COLUMN_SUBTITLE, subtitle);
        values.put(MySQLiteHelper.COLUMN_BODY, body);
        long insertId = database.insert(MySQLiteHelper.TABLE_NEWS_ENTRIES, null, values);
        Cursor cursor = database.query(MySQLiteHelper.TABLE_NEWS_ENTRIES,
                allColumns, MySQLiteHelper.COLUMN_ID + " = " + insertId, null,
                null, null, null);
        cursor.moveToFirst();
        NewsEntry newComment = cursorToNewsEntry(cursor);
        cursor.close();
        return newComment;
    }

    public void deleteNewsEntry(NewsEntry newsEntry) {
        long id = newsEntry.getId();
        System.out.println("Comment deleted with id: " + id);
        database.delete(MySQLiteHelper.TABLE_NEWS_ENTRIES, MySQLiteHelper.COLUMN_ID
                + " = " + id, null);
    }

    public ArrayList<NewsEntry> getAllNewsEntries() {
        ArrayList<NewsEntry> newsEntries = new ArrayList<NewsEntry>();

        Cursor cursor = database.query(MySQLiteHelper.TABLE_NEWS_ENTRIES,
                allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            NewsEntry newsEntry = cursorToNewsEntry(cursor);
            newsEntries.add(newsEntry);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        return newsEntries;
    }

    private NewsEntry cursorToNewsEntry(Cursor cursor) {
        NewsEntry comment = new NewsEntry();
        comment.setId(cursor.getLong(0));
        comment.setTitle(cursor.getString(1));
        comment.setSubitle(cursor.getString(2));
        comment.setBody(cursor.getString(3));
        return comment;
    }
}
