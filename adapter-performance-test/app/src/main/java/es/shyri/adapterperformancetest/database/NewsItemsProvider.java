package es.shyri.adapterperformancetest.database;

import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.Nullable;

/**
 * Created by Shyri on 08/10/2015.
 */
public class NewsItemsProvider extends ContentProvider {
    public static String CONTENT_AUTHORITY;
    MySQLiteHelper database;

    private static final UriMatcher dbUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
    public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "newsitems";
    public static Uri CONTENT_URI;


    @Override
    public boolean onCreate() {
        database = new MySQLiteHelper(getContext());
        CONTENT_AUTHORITY = getContext().getPackageName();
        dbUriMatcher.addURI(CONTENT_AUTHORITY, "newsitems", 0);
        CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY + "/" + "newsitems");
        return true;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        final SQLiteDatabase db = database.getReadableDatabase();
        Cursor c = db.query(MySQLiteHelper.TABLE_NEWS_ENTRIES, projection, selection, selectionArgs, null, null, sortOrder);
        c.setNotificationUri(getContext().getContentResolver(), uri);


        return c;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        switch (dbUriMatcher.match(uri)) {
            case 0:
                return CONTENT_TYPE ;
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        return null;        // Asuming datbase is already filled up with data for this test
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;           // We don't need delete for this test
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;           // We don't need update for this test.
    }
}
