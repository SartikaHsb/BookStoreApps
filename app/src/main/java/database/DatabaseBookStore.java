package database;

import android.content.ContentValues;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by sartikahasibuan on 7/10/2016.
 */
public class DatabaseBookStore extends SQLiteOpenHelper {
    private static final String DEBUG_TAG = "DatabaseBookStore";
    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "DatabaseBookStore1";

    /*Table Book*/
    public static final String TABLE_BOOK = "book";
    public static final String ID_BOOK = "book_id";
    public static final String BOOK_NAME = "book_name";
    public static final String BOOK_SUMMARY = "book_summary";
    public static final String AUTHOR_ID = "author_id";
    public static final String CATEGORY_ID = "category_id";
    public static final String BOOK_CA = "book_created_at";
    public static final String BOOK_UA = "book_updated_at";
    public static final String PRICE = "price";

    /*Table Category*/
    public static final String TABLE_CATEGORY = "category";
    public static final String ID_CATEGORY = "category_id";
    public static final String CATEGORY_NAME = "category_name";
    public static final String CATEGORY_CA = "category_created_at";
    public static final String CATEGORY_UA = "category_updated_at";

    /*Table Author*/
    public static final String TABLE_AUTHOR = "author";
    public static final String ID_AUTHOR = "author_id";
    public static final String AUTHOR_NAME = "author_name";
    public static final String AUTHOR_BIO = "author_bio";
    public static final String AUTHOR_CA = "author_created_at";
    public static final String AUTHOR_UA = "author_updated_at";

    /*create all tables*/
    /*private static final String CREATE_TABLE_BOOK = "create table " + TABLE_BOOK
            + " (" + ID_BOOK + " integer , " + BOOK_NAME + " text , " + BOOK_SUMMARY + " text , " +
            AUTHOR_ID + " text , " +CATEGORY_ID+ " text , " + BOOK_CA + " text , " + BOOK_UA +
            "text , " + PRICE + "text );";*/

    private static final String CREATE_TABLE_BOOK = "CREATE TABLE "+TABLE_BOOK+" (book_id integer primary key, book_name text, book_summary text, author_id text, category_id text, book_created_at text, book_updated_at text, price text);";

    private static final String CREATE_TABLE_CATEGORY = "create table " + TABLE_CATEGORY
            + " (" + ID_CATEGORY + " integer , " + CATEGORY_NAME + " text , " + CATEGORY_CA + " text , " +
            CATEGORY_UA + " text  );";

    private static final String CREATE_TABLE_AUTHOR = "create table " + TABLE_AUTHOR
            + " (" + ID_AUTHOR + " integer , " + AUTHOR_NAME + " text , " + AUTHOR_BIO + " text , " +
            AUTHOR_CA + " text , " +AUTHOR_UA+ " text  );";


    public DatabaseBookStore(Context context) {

        super(context, DB_NAME, null, DB_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try{
            db.execSQL(CREATE_TABLE_BOOK);
            db.execSQL(CREATE_TABLE_CATEGORY);
            db.execSQL(CREATE_TABLE_AUTHOR);

            /******************************DUMMY DATA*********************************************/
            ContentValues values = new ContentValues();
            try
            {
                db.beginTransaction();

                values.put(ID_BOOK, 1);
                values.put(BOOK_NAME, "Buku Ajaib");
                values.put(BOOK_SUMMARY, "Summary Buku Ajaib");
                values.put(AUTHOR_ID, "1");
                values.put(CATEGORY_ID, "1");
                values.put(BOOK_CA, "10/07/2016");
                values.put(BOOK_UA, "10/07/2016");
                values.put(PRICE, "RM 0.0");
                db.insert(TABLE_BOOK, null, values);


                values.put(ID_BOOK, 5);
                values.put(BOOK_NAME, "Buku Ajaib1");
                values.put(BOOK_SUMMARY, "Summary Buku Ajaib1");
                values.put(AUTHOR_ID, "2");
                values.put(CATEGORY_ID, "2");
                values.put(BOOK_CA, "10/07/2016");
                values.put(BOOK_UA, "10/07/2016");
                values.put(PRICE, "RM 0.0");
                db.insert(TABLE_BOOK, null, values);


                values.put(ID_BOOK, 2);
                values.put(BOOK_NAME, "Buku Ajaib2");
                values.put(BOOK_SUMMARY, "Summary Buku Ajaib2");
                values.put(AUTHOR_ID, "3");
                values.put(CATEGORY_ID, "3");
                values.put(BOOK_CA, "10/07/2016");
                values.put(BOOK_UA, "10/07/2016");
                values.put(PRICE, "RM 0.0");
                db.insert(TABLE_BOOK, null, values);

                db.endTransaction();
                //db.setTransactionSuccessful();
                Log.e("sucess","Sucess create table");
            }catch (Exception e){
                Log.e(" Insert Dummy Exception",Log.getStackTraceString(e));
            }

        }catch (SQLException se) {
            Log.e(" Oncreate SQLException",Log.getStackTraceString(se));
        } catch (Exception e) {
            Log.e(" Oncreate Exception",Log.getStackTraceString(e));
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(DEBUG_TAG, "Upgrading database. Existing contents will be lost. ["
                + oldVersion + "]->[" + newVersion + "]");
        db.execSQL("DROP TABLE IF EXISTS" + CREATE_TABLE_BOOK);
        onCreate(db);

    }
}

