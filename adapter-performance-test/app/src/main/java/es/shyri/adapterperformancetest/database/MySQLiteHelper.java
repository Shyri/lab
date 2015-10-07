package es.shyri.adapterperformancetest.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Shyri on 07/10/2015.
 */
public class MySQLiteHelper  extends SQLiteOpenHelper {

    public static final String TABLE_NEWS_ENTRIES = "news_entries";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_TITLE = "comment";
    public static final String COLUMN_SUBTITLE = "subtitle";
    public static final String COLUMN_BODY = "body";

    private static final String DATABASE_NAME = "news.db";
    private static final int DATABASE_VERSION = 1;

    // Database creation sql statement
    private static final String DATABASE_CREATE = "create table "
            + TABLE_NEWS_ENTRIES + "(" + COLUMN_ID  + " integer primary key autoincrement, "
            + COLUMN_TITLE  + " text not null, "
            + COLUMN_SUBTITLE  + " text not null, "
            + COLUMN_BODY  + " text not null);";

    public MySQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(MySQLiteHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NEWS_ENTRIES);
        onCreate(db);
    }

}