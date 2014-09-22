package com.example.casey.fragmentedprog;

/**
 * Created by casey on 9/20/14.
 */

        import android.content.ContentValues;
        import android.content.Context;
        import android.database.Cursor;
        import android.database.sqlite.SQLiteDatabase;
        import android.provider.ContactsContract;

        import java.util.ArrayList;

/**
 * Created by chris on 12/23/13.
 */
public class Handler {
     //Database Model
    private Database model;

    //Database
    private SQLiteDatabase database;

    //All Fields
    private String[] allColumns = {
            Database.USERNAME,
            Database.MESSAGE,
            Database.TIME,
    };

    //Public Constructor - create connection to Database
    public Handler(Context context){
        model = new Database(context);
    }

    /**
     * Add
     */
    public void addToDatabase(ChatObject chat){
        ContentValues values = new ContentValues();
        //values.put(Database.KITTY_URL, url);
        values.put(Database.USERNAME, chat.userId);
        values.put(Database.MESSAGE, chat.message);
        values.put(Database.TIME, chat.time);

        //values.put(Database.KITTY_CATEGORY, cat);
        //values.put(Database.KITTY_STATUS, "N/A");
        //values.put(Database.KITTY_IMAGE, image);
        //database.insertWithOnConflict(Database.TABLE_NAME, null, values, SQLiteDatabase.CONFLICT_IGNORE);
        database.insertWithOnConflict(Database.TABLE_NAME, null, values, SQLiteDatabase.CONFLICT_IGNORE);
        //insert(Database.TABLE_NAME, null, values);

        //database.insert(Database.TABLE_NAME, null, values);
    }
//    public void updateDatabase(ChatObject chat){
//        ContentValues values = new ContentValues();
//        //values.put(Database.KITTY_URL, kitty.url);
//        values.put(Database.MESSAGE, chat.message);
//        values.put(Database.TIME, chat.time);
//        //values.put(Database.KITTY_FAVORITE, kitty.favorite);
//        //values.put(Database.KITTY_CATEGORY, kitty.category);
//        //values.put(Database.KITTY_STATUS, kitty.status);
//        //values.put(Database.KITTY_IMAGE, kitty.image);
//        database.update(Database.TABLE_NAME, values, Database.SENDER + " like '%" + Database.TIME + "%'", null);
//    }

    /**
     * Get
     */
    public ArrayList<ChatObject> getAllChats(){
        return sweepCursor(database.query(Database.TABLE_NAME, allColumns, null, null, null, null, null));
    }

//    public ChatObject getKittyById(String id){
//        return sweepCursor(database.query(
//                Database.TABLE_NAME,
//                allColumns,
//                Database.id + " like '%" + id + "%'",
//                null, null, null, null
//        )).get(0);
//    }

    /**
     * Delete
     */
    public void deleteKittiesByCategory(String cat){
        database.delete(
                Database.TABLE_NAME,
                Database.USERNAME + Database.MESSAGE + " like '%" + cat + "%' AND " + Database.TIME+ " like '%false%'",
                null
        );
    }
    public void deleteKittyById(String id){
        database.delete(
                Database.TABLE_NAME,
                Database.USERNAME + Database.MESSAGE + " like '%" + Database.TIME + "%'",
                null
        );
    }

    /**
     * Additional Helpers
     */
    //Sweep Through Cursor and return a List of Kitties
    private ArrayList<ChatObject> sweepCursor(Cursor cursor){
        ArrayList<ChatObject> kitties = new ArrayList<ChatObject>();

        //Get to the beginning of the cursor
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            //Get the Kitty
            ChatObject chatObject = new ChatObject(
                    cursor.getString(0),
                    cursor.getString(1),
                    cursor.getString(2)
            );
            //Add the Kitty
            kitties.add(chatObject);
            //Go on to the next Kitty
            cursor.moveToNext();
        }
        return kitties;
    }

    //Get Writable Database - open the database
    public void open(){
        database = model.getWritableDatabase();
    }
//    public void close(){
//        database.close();
//    }
}

