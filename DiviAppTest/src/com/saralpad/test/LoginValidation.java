package com.saralpad.test;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.test.ActivityInstrumentationTestCase2;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.saralpad.LoginActivity;
import com.saralpad.R;

public class LoginValidation extends
		ActivityInstrumentationTestCase2<LoginActivity> {
	  private Button login;
	  private EditText nameBox;
	  private ImageButton settings;
	  LoginActivity loginActivity;
	  Context context;
	  ConnectivityManager connec;
	  NetworkInfo info,mobile,wifi ;
	  private static final String TAG = "LoginValidation";



	public LoginValidation() {
		super("com.saralpad",LoginActivity.class);
	}

	protected void setUp() throws Exception {
		super.setUp();
		setActivityInitialTouchMode(false);
		loginActivity=getActivity();
		settings=(ImageButton)loginActivity.findViewById(R.id.settings_button);
		
		// loginActivity=new LoginActivity();
		//loginActivity=getActivity();
		nameBox = (EditText) loginActivity.findViewById(R.id.login_name);
		login=(Button)loginActivity.findViewById(R.id.login_button);
		
		//setActivityIntent(new Intent(WifiManager.ACTION_PICK_WIFI_NETWORK));
		 // connec=(ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
		  try {
			 		       connec = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		        info = connec.getActiveNetworkInfo();    
		         wifi= connec.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
		         Log.d(TAG, "value of wifi" + wifi);
				 mobile=connec.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

		    } catch (Exception e) {
		        Log.e("connectivity", e.toString());
		    }
	

	
	 } 
	/*public void testAValidUserCanLogIn() {
		 
		 Instrumentation instrumentation=getInstrumentation();
		// Register we are interested in the LoginActivity activiry...
		Instrumentation.ActivityMonitor monitor=instrumentation.addMonitor(LoginActivity.class.getName(),null,false);
		// Start the LoginActivity as the first activity...
		
		 Intent intent=new Intent(Intent.ACTION_MAIN);
		 intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		 intent.setClassName(instrumentation.getTargetContext(), LoginActivity.class.getName());
		 instrumentation.startActivitySync(intent);
		 
		// Wait for it to start...
		 Activity currentActivity=getInstrumentation().waitForMonitorWithTimeout(monitor, 5);
		assertNotNull(currentActivity);
		}
	*/

		    public final boolean isInternetOn() {
				
				// ARE WE CONNECTED TO THE NET
				if ( connec.getNetworkInfo(0).getState() == NetworkInfo.State.CONNECTED ||
				connec.getNetworkInfo(0).getState() == NetworkInfo.State.CONNECTING ||
				connec.getNetworkInfo(1).getState() == NetworkInfo.State.CONNECTING ||
				connec.getNetworkInfo(1).getState() == NetworkInfo.State.CONNECTED ) {
				// MESSAGE TO SCREEN FOR TESTING (IF REQ)
				//Toast.makeText(this, connectionType + ” connected”, Toast.LENGTH_SHORT).show();
				return true;
				} else if ( connec.getNetworkInfo(0).getState() == NetworkInfo.State.DISCONNECTED ||  connec.getNetworkInfo(1).getState() == NetworkInfo.State.DISCONNECTED  ) {
				//System.out.println(“Not Connected”);
				return false;
				}
				return false;
				}
/*		    public static boolean isNetworkAvailable(Context context) {

	            boolean value = false;

	            ConnectivityManager manager = (ConnectivityManager) context
	                             .getSystemService(Context.CONNECTIVITY_SERVICE);

	            NetworkInfo info = manager.getActiveNetworkInfo();

	            if (info != null && info.isAvailable()) {
	                           value = true;
	            }

	            return value;
	 }
			*/
			
		
	
	
	private static final String Name = "Divi";
	private static final String Name1="@";
	private static final String Result="LOGIN SUCCESSFUL";
	private static final String EnterName="Please enter ur name";
	private static final String NetworkUnavaliable="No Internet Connection";
	private static final String NetworkAvailable="Network is Available so.. logged in";
	private static final String MobileConn="Mobile is Connected to the Internet";
	public void testPreConditions(){
	    assertTrue(nameBox !=null);
	    assertFalse(context!=null);
	}
	public void testPreConditions1(){
		String msg=nameBox.getText().toString();
	    assertFalse(nameBox ==null);
	    assertTrue("Message" + EnterName +msg,msg.equals(null));
	    
	}
	
	public void testLoginValues(){
		// sendKeys("DIVI");
		 
		 String loginResult=nameBox.getText().toString();
		/* assertTrue("User Name should be" +Name + " but was with message"
	                + loginResult, loginResult.equals(Result));*/
		assertEquals(Name,loginResult);
		assertEquals(Name1,loginResult);
	    }
}
	/*public void testNetwork(){
		
		 
	//	 connec=(ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
		
		assertTrue(connec==null);
		settings.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				
			//	launchActivity(new Intent(WifiManager.ACTION_PICK_WIFI_NETWORK));
			}
		});
		
		 android.net.NetworkInfo wifi= connec.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
		 android.net.NetworkInfo mobile=connec.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
		 
		   assertTrue("Netowrk is available"+NetworkAvailable+ "msg",isInternetOn());
		   assertTrue("mobile is connected to the Internet"+MobileConn+"some String",mobile.isConnected());
	}*/
	/*public void testIfNetworkUnavailable()  throws Exception{
		
		
		 //  connec=(ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
		
		 
		  // assertTrue("Netowrk is not available"+NetworkUnavaliable+ "msg",isNetworkAvailable(context));
		 //  assertTrue("mobile is not connected to wifi",!(mobile.isConnected()));
		   
	}
	
		
	 }

*/
