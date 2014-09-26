package com.example.casey.fragmentedprog;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.util.Log;

/**
 * Created by casey on 9/15/14.
 */
//making the table
public final class Database extends SQLiteOpenHelper{
    //public static abstract class TableInfo{
    public static final String TABLE_NAME= "Messages";
    //}

    private static final String TEXT_TYPE = "TEXT";
    //private static final String SQL_CREATE_ENTRIES = "CREATE TABLE" + TableInfo.TABLE_NAME + TEXT_TYPE;
    //private static final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS" + TableInfo.TABLE_NAME;

//public class Database extends SQLiteOpenHelper{
    //Table Name
    //public static final String TABLE_NAME = "Chats";


    //Table Fields
    public static final String USERNAME = "Username";
    public static final String MESSAGE = "Message";
    public static final String TIME = "Time";

    //public static final String KITTY_NAME = "name";


    //Database Info
    private static final String DATABASE_NAME = "ChatMessageDatabase";
    private static final int DATABASE_VERSION = 1;

    // Database creation sql statement
    private static final String DATABASE_CREATE = "create table "
            + TABLE_NAME + "("
            + USERNAME + " TEXT NOT NULL, "
            + MESSAGE + " TEXT NOT NULL UNIQUE, "
            + TIME + " TEXT NOT NULL UNIQUE ";
    //delete database
    private static final String DATABASE_DELETE = "DROP TABLE IF EXISTS";

    //Default Constructor
    public Database(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    //OnCreate Method - creates the Database
    public void onCreate(SQLiteDatabase database){
        database.execSQL(DATABASE_CREATE);


    }
    @Override
    //OnUpgrade Method - upgrades Database if applicable
    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion){
        Log.w(Database.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        database.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME +  USERNAME +  MESSAGE + TIME);
        database.execSQL(DATABASE_DELETE);
        onCreate(database);
    }
}
