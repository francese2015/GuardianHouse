package it.unisa.guardianhouse.helpers;

import java.util.HashMap;
 
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
 
public class SQLiteHandler extends SQLiteOpenHelper {
 
    private static final String TAG = SQLiteHandler.class.getSimpleName();
 
    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 5;
 
    // Database Name
    private static final String DATABASE_NAME = "test_app";
 
    // Login table name
    private static final String TABLE_LOGIN = "user_info";
 
    // Login Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_API_KEY = "api_key";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_ENCRYPTED_PASSWORD = "encrypted_password";
    private static final String KEY_SALT = "salt";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_NAME = "name";
    private static final String KEY_SURNAME = "surname";
    private static final String KEY_BIRTHDATE = "birthdate";
    private static final String KEY_BIRTHPLACE = "birthplace";
    private static final String KEY_ADDRESS = "address";
    private static final String KEY_PHONE = "phone";
    private static final String KEY_ROLE = "role";
 
    public SQLiteHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
 
    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {        
        String CREATE_LOGIN_TABLE = "CREATE TABLE user_info (row_id integer primary key autoincrement, id text not null, api_key text not null,"
        		+ " username text not null, encrypted_password text not null, salt text not null, email text not null,"
        		+ " name text not null, surname text not null, birthdate text not null, birthplace text not null,"
        		+ " address text not null, phone text not null, role text not null);";
        db.execSQL(CREATE_LOGIN_TABLE);
 
        Log.d(TAG, "Database table created");
    }
 
    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_LOGIN);
 
        // Create tables again
        onCreate(db);
    }
 
    /**
     * Storing user details in database
     * */
    public void addUser(String id, String api_key, String username, String encrypted_password,
    		String salt, String email, String name, String surname, String birthdate, String birthplace,
    		String address, String phone, String role) {
        SQLiteDatabase db = this.getWritableDatabase();
 
        ContentValues values = new ContentValues();
        values.put(KEY_ID, id);
        values.put(KEY_API_KEY, api_key);
        values.put(KEY_USERNAME, username);
        values.put(KEY_ENCRYPTED_PASSWORD, encrypted_password);
        values.put(KEY_SALT, salt);
        values.put(KEY_EMAIL, email);
        values.put(KEY_NAME, name);
        values.put(KEY_SURNAME, surname);
        values.put(KEY_BIRTHDATE, birthdate);
        values.put(KEY_BIRTHPLACE, birthplace);
        values.put(KEY_ADDRESS, address);
        values.put(KEY_PHONE, phone);
        values.put(KEY_ROLE, role);
 
        // Inserting Row
        long ins_id = db.insert(TABLE_LOGIN, null, values);
        db.close(); // Closing database connection
 
        Log.d(TAG, "New user inserted into sqlite: " + ins_id);
    }
 
    /**
     * Getting user data from database
     * */
    public HashMap<String, String> getUserDetails() {
        HashMap<String, String> user = new HashMap<String, String>();
        String selectQuery = "SELECT  * FROM " + TABLE_LOGIN;
 
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // Move to first row
        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            user.put("id", cursor.getString(1));
            user.put("api_key", cursor.getString(2));
            user.put("username", cursor.getString(3));
            user.put("encrypted_password", cursor.getString(4));
            user.put("salt", cursor.getString(5));
            user.put("email", cursor.getString(6));
            user.put("name", cursor.getString(7));
            user.put("surname", cursor.getString(8));
            user.put("birthdate", cursor.getString(9));
            user.put("birthplace", cursor.getString(10));
            user.put("address", cursor.getString(11));
            user.put("phone", cursor.getString(12));
            user.put("role", cursor.getString(13));
        }
        cursor.close();
        db.close();
        // return user
        Log.d(TAG, "Fetching user from Sqlite: " + user.toString());
 
        return user;
    }
 
    /**
     * Getting user login status return true if rows are there in table
     * */
    public int getRowCount() {
        String countQuery = "SELECT  * FROM " + TABLE_LOGIN;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int rowCount = cursor.getCount();
        db.close();
        cursor.close();
 
        // return row count
        return rowCount;
    }
 
    /**
     * Re crate database Delete all tables and create them again
     * */
    public void deleteUsers() {
        SQLiteDatabase db = this.getWritableDatabase();
        // Delete All Rows
        db.delete(TABLE_LOGIN, null, null);
        db.close();
 
        Log.d(TAG, "Deleted all user info from sqlite");
    }
 
}