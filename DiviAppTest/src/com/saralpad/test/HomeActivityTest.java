package com.saralpad.test;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Intent;
import android.test.ActivityInstrumentationTestCase;
import android.test.ActivityInstrumentationTestCase2;
import android.test.ViewAsserts;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.saralpad.HomeActivity;
import com.saralpad.LearnActivity;
import com.saralpad.LoginActivity;
import com.saralpad.PracticeHomeActivity;
import com.saralpad.ProgressActivity;
import com.saralpad.R;
import com.saralpad.SettingsActivity;
import com.saralpad.data.DatabaseHelper;
import com.saralpad.util.Config;
import com.saralpad.util.LoadInitialDataHelper;

public class HomeActivityTest extends
		ActivityInstrumentationTestCase<HomeActivity> {
	private static final String TAG = "com.saralpad.test.HomeActivityTest";
	HomeActivity mHomeActivity;
	Button importButton, reloadButton,showDownloads;
	 TextView learn,practice,progress,settings;
	 Intent myIntent;
	 Instrumentation mInstrumentation;

	public HomeActivityTest() {
		super("com.saralpad",HomeActivity.class);
	}

	protected void setUp() throws Exception {
		super.setUp();
		//(false);
		  mInstrumentation = getInstrumentation();
		 	
		  mHomeActivity = (HomeActivity) getActivity();
		 importButton=(Button) mHomeActivity.findViewById(R.id.import_button);
		 showDownloads = (Button) mHomeActivity.findViewById(R.id.showDownloads);
		 learn = (TextView) mHomeActivity.findViewById(R.id.home_learn);
		  practice = (TextView) mHomeActivity.findViewById(R.id.home_practice);
		   progress = (TextView) mHomeActivity.findViewById(R.id.home_progress);
		  settings = (TextView) mHomeActivity.findViewById(R.id.home_settings);
		
		 
	}
	public void testButtons(){
		
		 mHomeActivity.runOnUiThread(new Runnable() {
			 public void run(){
				 
				practice.requestFocus();
				learn.requestFocus();
				progress.requestFocus();
				settings.requestFocus();
				showDownloads.requestFocus();
				importButton.requestFocus();
				 
			 }
			 
		});
		 mInstrumentation.waitForIdleSync();
		 
		 this.sendKeys(KeyEvent.KEYCODE_DPAD_CENTER);
	}
		public void testAValidUserCanLogIn() {
		 
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
	
	public void testLearnButton(){
		assertNotNull(learn);
		learn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				myIntent=new Intent(Intent.ACTION_VIEW);
				myIntent.setClass(getActivity(), getClass());
				LearnActivity lActivity=launchActivityWithIntent("com.saralpad",LearnActivity.class,myIntent);
				
				assertTrue(Config.DEBUG);
				Log.d(TAG, "loading learn activity");
			}
		});
	}
	public void testPracticeButton(){
		assertNotNull(practice);
		
		practice.setOnClickListener(new View.OnClickListener() {
			
			/* (non-Javadoc)
			 * @see android.view.View.OnClickListener#onClick(android.view.View)
			 */
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				 myIntent = new Intent(Intent.ACTION_VIEW);
				 myIntent.setClass(getActivity(), getClass());
             //  setActivityIntent(myIntent);
				//mHomeActivity=getActivity();
			assertTrue(mHomeActivity!=null);
			
				PracticeHomeActivity pActivity=launchActivityWithIntent("com.saralpad",PracticeHomeActivity.class,myIntent);
				myIntent.setClass(mHomeActivity, PracticeHomeActivity.class);
				if(!mHomeActivity.courseId.equals("krishnaveni_xclass"))
				return;
				launchActivity("com.saralpad",PracticeHomeActivity.class,null);
				assertTrue(Config.DEBUG);
				Log.d(TAG,"Loading Practice Activity");
					
					//getInstrumentation().callActivityOnStart(pActivity);
					
				
			}
		});
		
	}
	public void testProgressButton(){
		assertTrue(progress!=null);
		
		
		progress.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				myIntent=new Intent(Intent.ACTION_VIEW);
				myIntent.setClass(getActivity(), getClass());
				ProgressActivity progActivity=launchActivityWithIntent("com.saralpad",ProgressActivity.class,myIntent);
				if(!mHomeActivity.courseId.equals("Krishnaveni_xclass"))
				return;
				launchActivity("com.saralpad",ProgressActivity.class,null);
				/*View view=mHomeActivity.findViewById(R.id.location);
				assertTrue(view!=null);
				assertTrue(view.getVisibility()==0);
				getInstrumentation().callActivityOnStart(progActivity);	*/		}
		});
		
		
	}
	public void testSettingsButton(){
		assertFalse(settings==null);
		settings.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				myIntent=new Intent(Intent.ACTION_VIEW);
				myIntent.setClass(getActivity(), getClass());
				
				SettingsActivity sActivity=launchActivityWithIntent("com.saralpad",SettingsActivity.class,myIntent);
			
				
			}
		});
	}
	
	  public void testViewsCreated() {
	    assertNotNull(getActivity());
	    assertNotNull(importButton);
	    assertNotNull(showDownloads);
	  }
	public void testImportButtonWork(){
		importButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Toast.makeText(mHomeActivity, "Starting content import", Toast.LENGTH_LONG).show();
				Log.d(TAG, "Starting content import");
				
			}
		});
		
	}
	
	public void testButtonVisibility(){
		 assertTrue(Config.SHOW_CONTENT_IMPORT_BUTTON);
		
		ViewAsserts.assertOnScreen(importButton.getRootView(),importButton);
		ViewAsserts.assertOnScreen(showDownloads.getRootView(), showDownloads);
	}
	
	
	public void testReloadeInitResources(){
		assertFalse(Config.LOAD_INIT_RESOURCES);
		LoadInitialDataHelper.loadResources(mHomeActivity);
		Log.w(TAG, "reloading init database!");
		DatabaseHelper.getInstance(mHomeActivity);
		
	}
	public void testReloadInitDatabase(){
		assertFalse(Config.LOAD_INIT_CONTENT_DATABASE);
		
	}
	
	
	protected void tearDown() throws Exception {
		super.tearDown();
	}

}
