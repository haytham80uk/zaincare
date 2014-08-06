package sd.zain.care2;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.Socket;
import java.net.URL;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.provider.ContactsContract;
import org.apache.cordova.*;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import sd.zain.database.CustomerInfo;
import sd.zain.database.ZainCareDataSource;


//import sd.zain.zain_care.R;

import android.text.InputFilter;
import android.text.InputType;
import android.view.WindowManager;
import android.webkit.JavascriptInterface;
import android.widget.EditText;
import android.widget.Toast;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.telephony.SmsManager;
import android.util.Log;


@SuppressWarnings("unused")
public class ZainCare2 extends CordovaActivity 
{
	 public BroadcastReceiver broadcastReceiver;
	 private ZainCareDataSource zcdatasource;
	 
   // @Override
    @JavascriptInterface
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);    
        super.init();
        appView.addJavascriptInterface(this, "Main");
       // super.loadUrl("file:///android_asset/www/tab/lang.html");
        //super.loadUrl("http://41.223.202.29/tab/lang.html");
        //super.loadUrl("file:///android_asset/www/tab/index.html");
        
        // Initialization and open DB
        zcdatasource = new ZainCareDataSource(this);
        zcdatasource.open();
        
        Toast.makeText(getContext(),zcdatasource.getMSISDN(),
                Toast.LENGTH_SHORT).show();
        
        if (hasConnection())
			try {
				// If no Internet access will show these URLs ....
				if (urlreachable())
					//super.loadUrl("http://41.223.202.29/tab/lang.html");
					super.loadUrl("http://41.223.202.29/ZainCareJSP/");
					//super.loadUrl("http://41.223.202.29/ZainCare-v2/1msisdn.html");
				
				/** TODO 1- Check the database user availability ...... 
				/*  DONE Dump data user name and password zaincare
				/* createUSER (String newPIN, String phoneNumber ) 
				/*
				/* TODO 2- IF New User will return 0 records find Then point (4) ...
				/* TODO 3- IF Exist User will return 1 record Then point (5) ...
				/* TODO 4- Create new user on server should be triggered and (save the new PIN in phone when enter the PIN Successfully --> aPIN) then point (X). ..
				/* TODO 5- Get the pin from the Android database --> aPIN..
				/* TODO 6- Check the aPIN with the existing PIN in Zain Server 172.17.4.46 --> zPIN...
				/* TODO 7- IF aPIN = zPIN then point (X)...
				/* TODO 8- Open the error ...
				/* TODO X- Open Application...
				 * 
				 */
				else
					super.loadUrl("file:///android_asset/www/urlconnection.html");
			} catch (IOException e) {
					super.loadUrl("file:///android_asset/www/urlconnection.html");
				e.printStackTrace();
			}
		else
			// If Internet access available will show this URL....
        	super.loadUrl("file:///android_asset/www/connection.html");
        	
        
        broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                   Log.d("dataReg_log ","CheckingConnService : Starting onReceive Function");
                   try {
                	  
                         	ConnectivityManager con_mgr = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
                            NetworkInfo myInfo = con_mgr.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

                            if (con_mgr.getNetworkInfo(0).getState() == NetworkInfo.State.CONNECTED) {
                                    Toast.makeText(context, "Connection is Up",
                                    Toast.LENGTH_SHORT).show();
                                    loadUrl("http://41.223.202.29/ZainCareJSP/");
                                    //loadUrl("http://41.223.202.29/ZainCare-v2/1msisdn.html");
                            
                            	} else {
                                       Toast.makeText(context, "Connection is Down",
                                         Toast.LENGTH_SHORT).show();
                                       loadUrl("file:///android_asset/www/connection.html");
                                            }
                            } catch (Exception e) {
                                            // Toast.makeText(context, e.getStackTrace()+"Here",
                                            // Toast.LENGTH_SHORT).show();
                                            Log.d("Error", e.getMessage() + "Get Class" + e.getClass());
                            }

            }

};

registerReceiver(broadcastReceiver, new IntentFilter(
                            ConnectivityManager.CONNECTIVITY_ACTION));

            



    }
    
    
    
    
    
    
    
    
    

    //cheack weather device has active network type connection
    public  boolean hasConnection() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(
            Context.CONNECTIVITY_SERVICE);

        NetworkInfo wifiNetwork = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        if (wifiNetwork != null && wifiNetwork.isConnected()) {
          return true;
        }

        NetworkInfo mobileNetwork = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        if (mobileNetwork != null && mobileNetwork.isConnected()) {
          return true;
        }

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if (activeNetwork != null && activeNetwork.isConnected()) {
          return true;
        }

        return false;
      }
    
    
    //check weather url is reachable
    public  boolean urlreachable() throws IOException {
    	try{
    		
    		if (android.os.Build.VERSION.SDK_INT > 9) {
    			StrictMode.ThreadPolicy policy = 
    			        new StrictMode.ThreadPolicy.Builder().permitAll().build();
    			StrictMode.setThreadPolicy(policy);
    			}
    		
    	 HttpClient httpclient = new DefaultHttpClient();
    	    HttpResponse response = httpclient.execute(new HttpGet("http://41.223.202.29/zaincare/main_page.html"));
    	    StatusLine statusLine = response.getStatusLine();
    	    if(statusLine.getStatusCode() == HttpStatus.SC_OK)
    	    	return true;
    	    	else return false;
    	}catch (Exception e){
    		Log.d( "UrlReachable",e.getMessage());
    				
    				return false;}
    	
    	
    
    
    }

    
    @JavascriptInterface
    public void nothing() {
    }
    
    @JavascriptInterface
    public void DialUSSD(String ussd){
    	if (ussd.charAt(0)=='*')
    	{
    		String encodedHash = Uri.encode("#");
    		String ussdcall = "*" + ussd  + encodedHash;
    		ussd=ussd+encodedHash;
    	}
    	startActivityForResult(new Intent("android.intent.action.CALL",Uri.parse("tel:" + ussd)), 1
    			);
    }
    

    
    // *******************************TAB functions*************************************************
   
    @JavascriptInterface
    public void whocalled(){

			 AlertDialog.Builder alert = new AlertDialog.Builder(this);
	         alert.setTitle("Who Called");
	         alert.setMessage("????");
	         final EditText Text = new EditText(this);
	         Text.setInputType(InputType.TYPE_CLASS_NUMBER|InputType.TYPE_NUMBER_FLAG_DECIMAL|InputType.TYPE_NUMBER_FLAG_SIGNED);  
	         int maxLength = 1;
	         InputFilter[] fArray = new InputFilter[1];
	         fArray[0] = new InputFilter.LengthFilter(maxLength);
	         Text.setFilters(fArray);
	       //  alert.setView(Text);
	        
	         
	         alert.setNeutralButton("Activate", new DialogInterface.OnClickListener()
	            {
	               public void onClick(DialogInterface dialog, int id)
	               {
	            	   DialUSSD("*111*1");
	               }
	            }); 
	         alert.setPositiveButton("Deactivate", new DialogInterface.OnClickListener()
		        {
		    @SuppressLint("UseValueOf")
			public void onClick(DialogInterface dialog, int id)
		       {
		    	 DialUSSD("*111*0");
		      }
		        });
		     alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener()
		            {
		               public void onClick(DialogInterface dialog, int id)
		               {
		               }
		            });         
		            
		            
		            AlertDialog builder = alert.create();     
					 builder.show(); 
				     builder.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE  | WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
				     builder.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
		    	
		    	
	}
    

    //********************* OTHER_SERVICIES ********************************


// Balance Transfer start here .....
@JavascriptInterface
public void getUssdBalanceTransfer(final String num){
	 AlertDialog.Builder alert = new AlertDialog.Builder(this);	
	 alert.setTitle("���� �����");
	 alert.setMessage("Enter number:");
	 final EditText Number = new EditText(this);
	 int maxLength = 15;
	 InputFilter[] fArray = new InputFilter[1];
	 fArray[0] = new InputFilter.LengthFilter(maxLength);
	 Number.setFilters(fArray);
	 alert.setView(Number);
	 Number.setText(num);
	 
	 		alert.setNeutralButton("Contacts", new DialogInterface.OnClickListener()
	 		{
	 			public void onClick(DialogInterface dialog, int id)
	 			{
	 				comingfrom= "BALTRA";
	 				callc();
	 			}
	 		});

 
 
 alert.setPositiveButton("Continue", new DialogInterface.OnClickListener()
    {
   @SuppressLint("UseValueOf")
public void onClick(DialogInterface dialog, int id)
   {
	   String number = Number.getText().toString();
	   if (number.length()!=10)
		{
		   getUssdBalanceTransfer(number);
		   final Context con = getApplicationContext();
			 Toast.makeText(con,"Number :09xxxxxxxx",Toast.LENGTH_LONG).show(); 
		}
		else
		{
			dialogCrdt(number);
		}
	   
   }
    });
        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener()
        {
           public void onClick(DialogInterface dialog, int id)
           {
           }
        });         
        
        AlertDialog builder = alert.create();     
		 builder.show(); 
	     builder.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE  | WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
	     builder.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
	
	
}

@SuppressLint("UseValueOf")
@JavascriptInterface
public void dialogCrdt(final String num){
 
	AlertDialog.Builder alert = new AlertDialog.Builder(this);	
	 alert.setTitle("���� ������");
	 alert.setMessage("Enter amount:");
	 final EditText amount = new EditText(this);
	 amount.setInputType(InputType.TYPE_CLASS_NUMBER|InputType.TYPE_NUMBER_FLAG_DECIMAL|InputType.TYPE_NUMBER_FLAG_SIGNED);  

	 int maxLength = 10;
	 InputFilter[] fArray = new InputFilter[1];
	 fArray[0] = new InputFilter.LengthFilter(maxLength);
	 amount.setFilters(fArray);
	 alert.setView(amount);
 
         alert.setPositiveButton("Continue", new DialogInterface.OnClickListener()
	        {
	       public void onClick(DialogInterface dialog, int id)
	       	{
	    	   int crd = new Integer(amount.getText().toString()).intValue();
	    	   String numbercrdit = crd+"*"+num+"*"+num;
	    	   dialogPinCode(numbercrdit);
	    	}
	      });
            alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener()
            {
               public void onClick(DialogInterface dialog, int id)
               {
               }
            });         
            
            AlertDialog builder = alert.create();     
			 builder.show(); 
		     builder.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE  | WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
		     builder.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);

}
@SuppressLint("UseValueOf")
@JavascriptInterface
public void dialogPinCode(final String numbercrdit){
 
   /* */
 AlertDialog.Builder alert = new AlertDialog.Builder(this);
 alert.setTitle("���� ����� �����");
 alert.setMessage("Enter your PIN Code:");
 final EditText Text = new EditText(this);
 Text.setInputType(InputType.TYPE_CLASS_NUMBER|InputType.TYPE_NUMBER_FLAG_DECIMAL|InputType.TYPE_NUMBER_FLAG_SIGNED);  
 int maxLength = 4;
 InputFilter[] fArray = new InputFilter[1];
 fArray[0] = new InputFilter.LengthFilter(maxLength);
 Text.setFilters(fArray);
 alert.setView(Text);
 
 alert.setPositiveButton("Continue", new DialogInterface.OnClickListener()
    {
	 public void onClick(DialogInterface dialog, int id)
	 	{
		 	int pin = new Integer(Text.getText().toString()).intValue();
			String encodedHash = Uri.encode("#");
    		String ussdcall = "*"+200+"*"+pin+"*"+numbercrdit+"*"+encodedHash;
    		 //final Context con = getApplicationContext();
    		 //Toast.makeText(con,"FnF => "+ussdcall ,Toast.LENGTH_LONG).show(); 
    		 
	    	startActivityForResult(new Intent("android.intent.action.CALL",Uri.parse("tel:" + ussdcall)), 1	 );
     
	 	}
    });
        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener()
        {
           public void onClick(DialogInterface dialog, int id)
           {
           }
        });         
        
        AlertDialog builder = alert.create();     
		 builder.show(); 
	     builder.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE  | WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
	     builder.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
		 
}


/// Edit Password Code start here ...
@JavascriptInterface
public void getUssdEditPassword(){
   /* */
	 AlertDialog.Builder alert = new AlertDialog.Builder(this);
    alert.setTitle("Enter your PIN number:");
    final EditText Text = new EditText(this);
    Text.setInputType(InputType.TYPE_CLASS_NUMBER|InputType.TYPE_NUMBER_FLAG_DECIMAL|InputType.TYPE_NUMBER_FLAG_SIGNED);  
    int maxLength = 4;
    InputFilter[] fArray = new InputFilter[1];
    fArray[0] = new InputFilter.LengthFilter(maxLength);
    Text.setFilters(fArray);
    alert.setView(Text);
    	
    	alert.setPositiveButton("Yes", new DialogInterface.OnClickListener()
	        {
        	 @SuppressLint("UseValueOf")
			public void onClick(DialogInterface dialog, int id)
        	 	{
        		
        		int pinnumber = new Integer(Text.getText().toString()).intValue();
        		String pincode = Integer.toString(pinnumber);
        		buildSTR(pincode,"Enter your New PIN number:", 4,InputType.TYPE_CLASS_NUMBER|InputType.TYPE_NUMBER_FLAG_DECIMAL|InputType.TYPE_NUMBER_FLAG_SIGNED);
 		    	}
	        });
    	
        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener()
        {
           public void onClick(DialogInterface dialog, int id)
           {
           }
        }); 
        
        AlertDialog builder = alert.create();     
		 builder.show(); 
	     builder.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE  | WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
	     builder.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);

}



@SuppressLint("UseValueOf")
@JavascriptInterface
public void buildSTR(final String input, String msg, int length , int inType){
  
 AlertDialog.Builder alert = new AlertDialog.Builder(this);
    alert.setTitle(msg);
    final EditText Text = new EditText(this);
    Text.setInputType(inType);  
    int maxLength = length;
    InputFilter[] fArray = new InputFilter[1];
    fArray[0] = new InputFilter.LengthFilter(maxLength);
    Text.setFilters(fArray);
    alert.setView(Text);
    	
    	alert.setPositiveButton("Yes", new DialogInterface.OnClickListener()
	        {
        	 public void onClick(DialogInterface dialog, int id)
        	 	{
        		 int pin = new Integer(Text.getText().toString()).intValue();
        		 String code = input +"*"+pin;
        		 buildSTR2(code,"Enter your New PIN number again :", 4,InputType.TYPE_CLASS_NUMBER|InputType.TYPE_NUMBER_FLAG_DECIMAL|InputType.TYPE_NUMBER_FLAG_SIGNED);
 		    	}
	        });
    	
            alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener()
            {
               public void onClick(DialogInterface dialog, int id)
               {
               }
            }); 
        
        AlertDialog builder = alert.create();     
		 builder.show(); 
	     builder.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE  | WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
	     builder.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
		
	}

@SuppressLint("UseValueOf")
@JavascriptInterface
public void buildSTR2(final String input, String msg, int length , int inType){

 AlertDialog.Builder alert = new AlertDialog.Builder(this);
    alert.setTitle(msg);
    final EditText Text = new EditText(this);
    Text.setInputType(inType);  
    int maxLength = length;
    InputFilter[] fArray = new InputFilter[1];
    fArray[0] = new InputFilter.LengthFilter(maxLength);
    Text.setFilters(fArray);
    alert.setView(Text);
    	
    	alert.setPositiveButton("Yes", new DialogInterface.OnClickListener()
	        {
        	 public void onClick(DialogInterface dialog, int id)
        	 	{
        		 int pin2 = new Integer(Text.getText().toString()).intValue();
        		 String concatnate = input +"*"+ pin2;
		        		String encodedHash = Uri.encode("#");
					    String ussdcall =  "*" + 201  +"*"+concatnate+encodedHash;
					    startActivityForResult(new Intent("android.intent.action.CALL",Uri.parse("tel:" + ussdcall)), 1);
 		    	}
	        });
    	
            alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener()
            {
               public void onClick(DialogInterface dialog, int id)
               {
               }
            }); 
        
        AlertDialog builder = alert.create();     
		 builder.show(); 
	     builder.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE  | WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
	     builder.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
	}





 /// Edit Password Code start here ...
@SuppressLint("UseValueOf")
@JavascriptInterface
public void getUssdEditFF(String num){
   /* */
	final String code ="555";
	 AlertDialog.Builder alert = new AlertDialog.Builder(this);
    alert.setTitle("Enter F&F number:'09xxxxxxxx'");
    alert.setMessage("���� ��� ������");
    final EditText Text = new EditText(this);
   // Text.setInputType(InputType.TYPE_CLASS_NUMBER|InputType.TYPE_NUMBER_FLAG_DECIMAL|InputType.TYPE_NUMBER_FLAG_SIGNED);  
    int maxLength = 15;
    InputFilter[] fArray = new InputFilter[1];
    fArray[0] = new InputFilter.LengthFilter(maxLength);
    Text.setFilters(fArray);
    alert.setView(Text);
    Text.setText(num);
    	
    alert.setNeutralButton("Contacts", new DialogInterface.OnClickListener()
    {
    	public void onClick(DialogInterface dialog, int id)
    	{
    		comingfrom= "FNFPRE";
    		callc();
    	}
    });

    final Context con = getApplicationContext();
    
    	alert.setPositiveButton("Continue", new DialogInterface.OnClickListener()
	        {
        	 public void onClick(DialogInterface dialog, int id)
        	 	{
        			String phoneforfnf = Text.getText().toString();
        			if (phoneforfnf.charAt(0)!='0')
        			{
        				 getUssdEditFF(phoneforfnf);
        				 Toast.makeText(con,"Number :09xxxxxxxx" +
        				 		" International:00Number",Toast.LENGTH_LONG).show(); 
        			}
        			else
        			{
        				FFSlot(phoneforfnf,"Enter Slot Number: '1-6'", 1,InputType.TYPE_CLASS_NUMBER|InputType.TYPE_NUMBER_FLAG_DECIMAL|InputType.TYPE_NUMBER_FLAG_SIGNED, code);
        				
        			}	
        	 	}
        	 	
	        });
    	
        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener()
        {
           public void onClick(DialogInterface dialog, int id)
           {
           }
        }); 
        
        AlertDialog builder = alert.create();     
		 builder.show(); 
	     builder.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE  | WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
	     builder.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);

}

@SuppressLint("UseValueOf")
@JavascriptInterface
public void getUssdEditFF_post(String num){
	   /* */
		final String code ="550";
		 AlertDialog.Builder alert = new AlertDialog.Builder(this);
		    alert.setTitle("Enter F&F number:'09xxxxxxxx'");
		    alert.setMessage("���� ��� ������");
		    final EditText Text = new EditText(this);
		   // Text.setInputType(InputType.TYPE_CLASS_NUMBER|InputType.TYPE_NUMBER_FLAG_DECIMAL|InputType.TYPE_NUMBER_FLAG_SIGNED);  
		    int maxLength = 15;
		    InputFilter[] fArray = new InputFilter[1];
		    fArray[0] = new InputFilter.LengthFilter(maxLength);
		    Text.setFilters(fArray);
		    alert.setView(Text);
		    Text.setText(num);
		    	
		    alert.setNeutralButton("Contacts", new DialogInterface.OnClickListener()
		    {
		    	public void onClick(DialogInterface dialog, int id)
		    	{
		    		comingfrom= "FNFPOST";
		    		callc();
		    	}
		    });

		    final Context con = getApplicationContext();
		    
		    	alert.setPositiveButton("Continue", new DialogInterface.OnClickListener()
			        {
		        	 public void onClick(DialogInterface dialog, int id)
		        	 	{
		        			String phoneforfnf = Text.getText().toString();
		        			if (phoneforfnf.charAt(0)!='0')
		        			{
		        				 getUssdEditFF_post(phoneforfnf);
		        				 Toast.makeText(con,"Number :09xxxxxxxx" +
		        				 		" International:00Number",Toast.LENGTH_LONG).show(); 
		        			}
		        			else
		        			{
		        				FFSlot(phoneforfnf,"Enter Slot Number: '1-6'", 1,InputType.TYPE_CLASS_NUMBER|InputType.TYPE_NUMBER_FLAG_DECIMAL|InputType.TYPE_NUMBER_FLAG_SIGNED, code);
		        				
		        			}	
		        	 	}
		        	 	
			        });
		    	
		        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener()
		        {
		           public void onClick(DialogInterface dialog, int id)
		           {
		           }
		        }); 
		        
		        AlertDialog builder = alert.create();     
				 builder.show(); 
			     builder.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE  | WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
			     builder.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);

		}


@SuppressLint("UseValueOf")
@JavascriptInterface
public void FFSlot(final String input, String msg, int length , int inType, final String code){
  
 AlertDialog.Builder alert = new AlertDialog.Builder(this);
    alert.setTitle(msg);

    alert.setMessage("���� ��� ������  1-6 ");
    final EditText Text = new EditText(this);
    Text.setInputType(inType);  
    int maxLength = length;
    InputFilter[] fArray = new InputFilter[1];
    fArray[0] = new InputFilter.LengthFilter(maxLength);
    Text.setFilters(fArray);
    alert.setView(Text);
    Toast.makeText(this,"fnf => "+ "*"+code+"*"+ input+"*"+"slot" +"#",Toast.LENGTH_LONG).show(); 
	
    	alert.setPositiveButton("OK", new DialogInterface.OnClickListener()
	        {
        	 public void onClick(DialogInterface dialog, int id)
        	 	{
        		 int slotno = new Integer(Text.getText().toString()).intValue();
     	    	String encodedHash = Uri.encode("#");
    	    	String ussdcall = "*"+code+"*"+ input+"*"+slotno + encodedHash;
    	    	
    	    	startActivityForResult(new Intent("android.intent.action.CALL",Uri.parse("tel:" + ussdcall)), 1
    	   			);
 		    	}
	        });
    	
            alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener()
            {
               public void onClick(DialogInterface dialog, int id)
               {
               }
            }); 
        
        AlertDialog builder = alert.create();     
		 builder.show(); 
	     builder.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE  | WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
	     builder.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
		
	}	  


            //****************** POSTPAID **************************

@JavascriptInterface
public void getUssdPayTheBill(String ussd){

    	   /* */
		 AlertDialog.Builder alert = new AlertDialog.Builder(this);
         alert.setTitle("Enter your credit serial number:");
         alert.setMessage("���� ���� ��� ��� �����");
         final EditText Text = new EditText(this);
         Text.setInputType(InputType.TYPE_CLASS_NUMBER|InputType.TYPE_NUMBER_FLAG_DECIMAL|InputType.TYPE_NUMBER_FLAG_SIGNED);  
         int maxLength = 14;
         InputFilter[] fArray = new InputFilter[1];
         fArray[0] = new InputFilter.LengthFilter(maxLength);
         Text.setFilters(fArray);
         alert.setView(Text);
        
         alert.setPositiveButton("Yes", new DialogInterface.OnClickListener()
	        {
	    @SuppressLint("UseValueOf")
		public void onClick(DialogInterface dialog, int id)
	       {
	    	   int crdt = new Integer(Text.getText().toString()).intValue();
	    		String encodedHash = Uri.encode("#");
		    	String ussdcall = "*" + 500  +"*"+ crdt + encodedHash;
		    	startActivityForResult(new Intent("android.intent.action.CALL",Uri.parse("tel:" + ussdcall)), 1	 );
	      }
	        });
	            alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener()
	            {
	               public void onClick(DialogInterface dialog, int id)
	               {
	               }
	            });         
	            
	            AlertDialog builder = alert.create();     
				 builder.show(); 
			     builder.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE  | WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
			     builder.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
    	
}


  // ********************************************** PREPAID ****************************************



// TODO GET THE SCRATCH CARD FROM CUSTOMER.....
@JavascriptInterface
public void getUssdRechargeBalance(){
	
	
   /* */
	 AlertDialog.Builder alert = new AlertDialog.Builder(this);
     alert.setTitle("Enter your credit serial number:");
     alert.setMessage("���� ���� ��� ��� �����");
     final EditText Text = new EditText(this);
     Text.setInputType(InputType.TYPE_CLASS_NUMBER|InputType.TYPE_NUMBER_FLAG_DECIMAL|InputType.TYPE_NUMBER_FLAG_SIGNED);  
     int maxLength = 15;
     InputFilter[] fArray = new InputFilter[1];
     fArray[0] = new InputFilter.LengthFilter(maxLength);
     Text.setFilters(fArray);
     alert.setView(Text);
    
     alert.setPositiveButton("Yes", new DialogInterface.OnClickListener()
        {
    @SuppressLint("UseValueOf")
	public void onClick(DialogInterface dialog, int id)
       {
    	  // int crdt = new Integer(Text.getText().toString()).intValue();
    	 String crdt = (Text.getText().toString());
    		String encodedHash = Uri.encode("#");
	    	String ussdcall = "*" + 888  +"*"+ crdt + encodedHash;
	    	startActivityForResult(new Intent("android.intent.action.CALL",Uri.parse("tel:" + ussdcall)), 1	 );
      }
        });
            alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener()
            {
               public void onClick(DialogInterface dialog, int id)
               {
               }
            });         
            
            AlertDialog builder = alert.create();     
			 builder.show(); 
		     builder.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE  | WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
		     builder.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
    	
    	
}
 

// TODO GET THE MSISDN FROM CUSTOMER.....
@SuppressLint("UseValueOf")
@JavascriptInterface
public void getUssdCallBackRequest(String num){
   
	 AlertDialog.Builder alert = new AlertDialog.Builder(this);
     alert.setTitle("Enter number you want to request:'09xxxxxxxx'");
     alert.setMessage("���� ����� ���� ����� �� ���� ��");
     final EditText Text = new EditText(this);
     //Text.setInputType(InputType.TYPE_CLASS_NUMBER|InputType.TYPE_NUMBER_FLAG_DECIMAL|InputType.TYPE_NUMBER_FLAG_SIGNED);  
     int maxLength = 15;
     InputFilter[] fArray = new InputFilter[1];
     fArray[0] = new InputFilter.LengthFilter(maxLength);
     Text.setFilters(fArray);
     alert.setView(Text);
     Text.setText(num);

     alert.setNeutralButton("Contacts", new DialogInterface.OnClickListener()
     {
    public void onClick(DialogInterface dialog, int id)
    {
    	comingfrom= "CBR";
        callc();
   }
     });
     
     
     alert.setPositiveButton("OK", new DialogInterface.OnClickListener()
        {
       public void onClick(DialogInterface dialog, int id)
       {
    	   int RequesterNo = new Integer(Text.getText().toString()).intValue();
    	   String msisdnToCallback = Integer.toString(RequesterNo);
    	   String encodedHash = Uri.encode("#");
	    	String ussdcall = "*" + 121  +"*"+  msisdnToCallback + encodedHash;
	    	startActivityForResult(new Intent("android.intent.action.CALL",Uri.parse("tel:" + ussdcall)), 1);
      }
        });
            alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener()
            {
               public void onClick(DialogInterface dialog, int id)
               {
               }
            });         
            
            AlertDialog builder = alert.create();
			 builder.show(); 
		     builder.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE  | WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
		     builder.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
		   
}
//************************************************* NON PHONE ************************
@JavascriptInterface
public void sendSMS() {
    Intent smsIntent = new Intent(Intent.ACTION_VIEW);

    smsIntent.putExtra("sms_body", "Please type your inquery"); 
    smsIntent.putExtra("address", "1230");
    smsIntent.setType("vnd.android-dir/mms-sms");

    startActivity(smsIntent);
}


@JavascriptInterface
public void sendmail(){
	Intent i = new Intent(Intent.ACTION_SEND);
	i.setType("message/rfc822");
	i.putExtra(Intent.EXTRA_EMAIL  , new String[]{"info@sd.zain.com"});
	i.putExtra(Intent.EXTRA_SUBJECT, "");
	i.putExtra(Intent.EXTRA_TEXT   , "");
	try {
	    startActivity(Intent.createChooser(i, "Send mail..."));
	} catch (android.content.ActivityNotFoundException ex) {
	    Toast.makeText(this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
	}
}

//******************************************** INTERNET AND MMS ************************
@JavascriptInterface
public void activateinternetmms(String text) {
    String phoneNumber = "123";
    String message = text;

    SmsManager smsManager = SmsManager.getDefault();
    smsManager.sendTextMessage(phoneNumber, null, message, null, null);
}

//************************************************** DALEELE **************************
@JavascriptInterface
public void SMS(String body, String addr) {
	 String phoneNumber = addr;
	    String message = body;

	    SmsManager smsManager = SmsManager.getDefault();
	    smsManager.sendTextMessage(phoneNumber, null, message, null, null);
}


//************************************************* CONTACTS ************************
public String comingfrom;
@JavascriptInterface
public void callc() {
    // user BoD suggests using Intent.ACTION_PICK instead of .ACTION_GET_CONTENT to avoid the chooser
    Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
    setResult(RESULT_OK, intent);
    Toast.makeText(this,"before calling contacts=> "+ comingfrom,Toast.LENGTH_LONG).show(); 
    // BoD con't: CONTENT_TYPE instead of CONTENT_ITEM_TYPE
    intent.setType(ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE);
    startActivityForResult(intent, 1); 
    
}



@Override
protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    if (data != null) {
        Uri uri = data.getData();

        if (uri != null) {
            Cursor c = null;
            try {
                c = getContentResolver().query(uri, new String[]{ 
                            ContactsContract.CommonDataKinds.Phone.NUMBER,  
                            ContactsContract.CommonDataKinds.Phone.TYPE },
                        null, null, null);

                if (c != null && c.moveToFirst()) {
                    String number = c.getString(0);
                    int type = c.getInt(1);
                   // Toast.makeText(this,data.getStringExtra("Com"),Toast.LENGTH_LONG).show(); 
                    showSelectedNumber(type, number);
                }
            } finally {
                if (c != null) {
                    c.close();
                }
            }
        }
    }
}

public void showSelectedNumber(int type, String number) {
	Toast.makeText(this,"coming from contacts=> "+comingfrom,Toast.LENGTH_LONG).show();
	if (comingfrom == "CBR")
	{
		comingfrom = null;
		getUssdCallBackRequest(number);
	}
	
	if (comingfrom == "FNFPRE")
	{
		comingfrom = null;
		getUssdEditFF(number);
	}
	
	if (comingfrom == "FNFPOST")
	{
		comingfrom = null;
		getUssdEditFF_post(number);
	}
	
	if (comingfrom == "BALTRA")
	{
		comingfrom = null;
		getUssdBalanceTransfer(number);
	}
	
	
}
// ***********************************ANDROID DATABASE CALL*********************************************
public void updatePINMSISDN(String newPIN, String phoneNumber ){
    // Initialization and open DB
    	zcdatasource = new ZainCareDataSource(this);
    	zcdatasource.open();
    	String FeedBack = zcdatasource.updatePIN_MSISDN(phoneNumber, newPIN);
    	Toast.makeText(this,"UPDATE PIN TO :("+ FeedBack +")",Toast.LENGTH_LONG).show();  	
	}
public void getMSISDN(){
    // Initialization and open DB
    	zcdatasource = new ZainCareDataSource(this);
    	zcdatasource.open();
    	String FeedBack = zcdatasource.getMSISDN(); //updatePIN(phoneNumber, newPIN);
    	Toast.makeText(this,"WELCOME :"+ FeedBack,Toast.LENGTH_LONG).show();  	
	}
public void getPIN(){
    // Initialization and open DB
    	zcdatasource = new ZainCareDataSource(this);
    	zcdatasource.open();
    	String FeedBack = zcdatasource.getPIN(); //updatePIN(phoneNumber, newPIN);
    	Toast.makeText(this,"YOUR PIN is :("+ FeedBack+")",Toast.LENGTH_LONG).show();  	
	}
/**
public void createUSER (String newPIN, String phoneNumber ){
    // Initialization and open DB
    	zcdatasource = new ZainCareDataSource(this);
    	zcdatasource.open();
    	String FeedBack = zcdatasource.getMSISDN(); //updatePIN(phoneNumber, newPIN);
    	Toast.makeText(this,"WELCOME :"+ FeedBack,Toast.LENGTH_LONG).show();  	
	}*/
}

