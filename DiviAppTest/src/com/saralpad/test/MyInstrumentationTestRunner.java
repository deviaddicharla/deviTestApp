package com.saralpad.test;

import junit.framework.TestSuite;
import android.test.InstrumentationTestRunner;
import android.test.InstrumentationTestSuite;

public class MyInstrumentationTestRunner extends InstrumentationTestRunner {
	@Override
	public TestSuite getAllTests(){
		InstrumentationTestSuite suite=new InstrumentationTestSuite(this);
		suite.addTestSuite(LoginValidation.class);
		//and u will keep on add the tests u want to 
		//runner to run
		
		return suite;
	}
	@Override
	
	public ClassLoader getLoader(){
		return MyInstrumentationTestRunner.class.getClassLoader();
	}
	

}
