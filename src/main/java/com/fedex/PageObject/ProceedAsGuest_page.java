package com.fedex.PageObject;

import java.util.Properties;

import com.infogain.uap.framework.utilities.PropFileHandler;
import com.infogain.uap.framework.webcontrols.Controls;

public class ProceedAsGuest_page {
	Controls _controls;
	 int _timeOut;
	 
	 PropFileHandler _propFileHandler = new PropFileHandler(new Properties());
	 
	  private String my_device="xpath~//a[@id='continueAsGuest']";
	  private String floating_icon="xpath~//div[@id='floatingCirclesG']";
	 
	  public ProceedAsGuest_page(ThreadLocal<Controls> _controls) {

			this._controls = _controls.get();
			if (_propFileHandler.readProperty("testData", "timeout") != null) {
				this._timeOut = Integer.parseInt(_propFileHandler.readProperty("testData", "timeout"));
			} else
				this._timeOut =0;
	  }
	/* this method is use to click on the asAGuest option
	 * in the application ,if user don't want to login  
	 */
	  public void select_asAGuestOption(){
		   _controls.waitUntilElementIsInVisible("Floating icon",floating_icon,25000,"Waiting for loading icon to be invisible");
		   _controls.waitUntilElementToBeClickable("Continue as a guest option button",my_device, "Waiting for continue as a guest option button");
		   _controls.clickUsingJavaScriptExecutors("Continue as a guest option",my_device, "clickin on continue as a guest option");
		   
	  }
    
}
