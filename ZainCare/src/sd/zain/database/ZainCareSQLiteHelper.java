package sd.zain.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class ZainCareSQLiteHelper extends SQLiteOpenHelper {

	public static final String TABLE_ZAINCARE= "zaincare_table";
	public static final String COLUMN_CUSTOMER_ID = "_id"; 
	
	
	public static final String COLUMN_USERNAME = "username";
	public static final String COLUMN_PASSWORD = "password";
	public static final String COLUMN_TIMESTAMP = "timestamp";
	
	    
  private static final String DATABASE_NAME = "zaincaredb.db";
  private static final int DATABASE_VERSION = 1;

  // Database creation sql statement
  private static final String DATABASE_CREATE = "create table "
      + TABLE_ZAINCARE + "(" + COLUMN_CUSTOMER_ID
      + " integer primary key autoincrement, " + COLUMN_USERNAME
      + " text not null, " + COLUMN_PASSWORD
      + " text not null, " + COLUMN_TIMESTAMP
      + " text not null);";
  
 // private static final String DATABASE_CREATE ="create table zaincare (_id integer primary key autoincrement );";
  
  public ZainCareSQLiteHelper(Context context) {
    super(context, DATABASE_NAME, null, DATABASE_VERSION);
  }

  @Override
  public void onCreate(SQLiteDatabase database) {
    database.execSQL(DATABASE_CREATE);
   // database.execSQL(TABLE_ZAINCARE);
    createDump(database);
  }

  @Override
  public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    Log.w(ZainCareSQLiteHelper.class.getName(),
        "Upgrading database from version " + oldVersion + " to "
            + newVersion + ", which will destroy all old data");
    db.execSQL("DROP TABLE IF EXISTS " + TABLE_ZAINCARE);
    onCreate(db);
    
  }
  
  //Dump data to create the user default PIN (cancel the first check when open the application)
  public void createDump(SQLiteDatabase database){
	  final String[] allColumns = { ZainCareSQLiteHelper.COLUMN_CUSTOMER_ID,
		      ZainCareSQLiteHelper.COLUMN_USERNAME, ZainCareSQLiteHelper.COLUMN_PASSWORD,
		      ZainCareSQLiteHelper.COLUMN_TIMESTAMP};
	  
	  ContentValues values = new ContentValues();
	    values.put(ZainCareSQLiteHelper.COLUMN_USERNAME, "zain");
	    values.put(ZainCareSQLiteHelper.COLUMN_PASSWORD, "zain");
	    values.put(ZainCareSQLiteHelper.COLUMN_TIMESTAMP, "00"); 
	    long insertId = database.insert(ZainCareSQLiteHelper.TABLE_ZAINCARE, null,values);
	    
	    Cursor cursor = database.query(ZainCareSQLiteHelper.TABLE_ZAINCARE,
	        allColumns, ZainCareSQLiteHelper.COLUMN_CUSTOMER_ID + " = " + insertId, null,
	        null, null, null);
	    cursor.moveToFirst();
	    Log.i("createDump", Integer.toString(cursor.getCount()));
	    cursor.close();
	   
	  
  }
  
}