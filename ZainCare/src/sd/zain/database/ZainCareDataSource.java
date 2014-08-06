/**
 * 
 */
package sd.zain.database;

/**
 * @author haythamhassan
 *
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class ZainCareDataSource {
	 // Database fields
	  private SQLiteDatabase database;
	  
	  private final ZainCareSQLiteHelper dbHelper;
	  
	  private final String[] allColumns = { ZainCareSQLiteHelper.COLUMN_CUSTOMER_ID,
	      ZainCareSQLiteHelper.COLUMN_USERNAME, ZainCareSQLiteHelper.COLUMN_PASSWORD,
	      ZainCareSQLiteHelper.COLUMN_TIMESTAMP};
	  
	  
	  public ZainCareDataSource(Context context) {
		    dbHelper = new ZainCareSQLiteHelper(context);
		  }
	  public void open() throws SQLException {
		    database = dbHelper.getWritableDatabase();
		  }
	  public void close() {
		    dbHelper.close();
		  }
	  
	  public String createUserInfo(String username,String password,String timestamp) {
		
		  ContentValues values = new ContentValues();
		    values.put(ZainCareSQLiteHelper.COLUMN_USERNAME, username);
		    values.put(ZainCareSQLiteHelper.COLUMN_PASSWORD, password);
		    values.put(ZainCareSQLiteHelper.COLUMN_TIMESTAMP, timestamp);

		    
		    long insertId = database.insert(ZainCareSQLiteHelper.TABLE_ZAINCARE, null,values);
		    
		    Cursor cursor = database.query(ZainCareSQLiteHelper.TABLE_ZAINCARE,
		        allColumns, ZainCareSQLiteHelper.COLUMN_CUSTOMER_ID + " = " + insertId, null,
		        null, null, null);
		    cursor.moveToFirst();
		    String newInfo = cursor.getString(0);
		    cursor.close();
		    Log.d("INSERT_INFODATASORCE::", Integer.toString(cursor.getCount()));
		    return newInfo;
		  }
          
          // Get The User Phone number for the customer
	  public String getMSISDN(){
          String MSISDN = null;        
		  Cursor cursor = database.query(ZainCareSQLiteHelper.TABLE_ZAINCARE,
		        allColumns, null, null, null, null, null);
                  cursor.moveToFirst();
                  	MSISDN = cursor.getString(1);
		    cursor.close();
              return MSISDN;
          }
      // Get The User Phone number for the customer
	  public String getPIN(){
		  String aPIN = null;        
		  Cursor cursor = database.query(ZainCareSQLiteHelper.TABLE_ZAINCARE,
	        allColumns, null, null, null, null, null);
              cursor.moveToFirst();
              	aPIN = cursor.getString(2);
	    cursor.close();
          return aPIN;
      }
	  // Update the PIN number after get the new generated from the server when successful access done with provided by SMS
	  public String updatePIN_MSISDN(String phoneNumber, String newPIN){
		  	String x=null;
		  		String[] args={phoneNumber};
		  		ContentValues values = new ContentValues();
		  		values.put(ZainCareSQLiteHelper.COLUMN_USERNAME, phoneNumber);
		  		values.put(ZainCareSQLiteHelper.COLUMN_PASSWORD, newPIN);
		  		try{
		  			Integer num = database.update(ZainCareSQLiteHelper.TABLE_ZAINCARE, values, null, null);//ZainCareSQLiteHelper.COLUMN_USERNAME + "= ?"
		  			Log.d("updatePIN", "Update Done !!"+" FOR "+Integer.toString(num) +" Record ");
		  			Cursor cu = database.query(ZainCareSQLiteHelper.TABLE_ZAINCARE, allColumns, ZainCareSQLiteHelper.COLUMN_USERNAME + "= ?", args, null, null, null);
		  				while (cu.moveToNext()){
		  					x = cu.getString(2);
		  					}
		  				cu.close();
		  		}catch(Exception e){
		  			Log.d("updatePIN", "No Update Done for the password");
		  		}
		  	return x;
	  }
	  	  // Get the number of records in table
	  public Integer getCount(){
		  Integer count=0;        
		  Cursor cursor = database.query(ZainCareSQLiteHelper.TABLE_ZAINCARE,
		        allColumns, null, null, null, null, null);
		  		count = cursor.getCount();
		  return count;
	  }
          // Delete the customer information from the phone db
	  public void deleteInfo(CustomerInfo info) {
		    long id = info.getId();
		    //System.out.println("PRODUCT deleted with id: " + id);
		    database.delete(ZainCareSQLiteHelper.TABLE_ZAINCARE, ZainCareSQLiteHelper.COLUMN_CUSTOMER_ID
		        + " = " + id, null);
		  }
	  /**  
          private CustomerInfo CursorToCustomerName(Cursor cursor){
                  CustomerInfo username = new CustomerInfo();
                  username.setUsername(cursor.getString(1));
              return username;
          }
	  public Boolean checkUserInDatabase(){
		  Boolean full = false;
		  String sync = "NO";
		  String[] arg = {sync};
		  Cursor cursor = database.query(ZainCareSQLiteHelper.TABLE_CUSTOMER, allColumns, StockSQLiteHelper.COLUMN_LOAD_SYNC+" = ? ", arg, null, null, null);
		  Integer num = cursor.getCount();
		  if (num > 0){ full = true;}
		  if (num < 0){ full = false;}
		  cursor.close();
		  return full;
	  }	
	  
	  private CustomerInfo cursorToCustomerInfo(Cursor cursor) {
		    CustomerInfo info = new CustomerInfo();
		    info.setId(cursor.getLong(0));
		    info.setUsername(cursor.getString(1));
		    info.setPassword(cursor.getString(2));
		    info.setTimestamp(cursor.getString(3));
		    
		    return info;
		  }**/

}
