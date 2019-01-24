package com.fedex.PageObject;

import java.util.Properties;

import com.infogain.uap.framework.utilities.PropFileHandler;
import com.infogain.uap.framework.webcontrols.Controls;

public class DocumentInProgress_page {
	Controls _controls;
	 int _timeOut;
	    private String setprintoption="xpath~//a[text()='Set Print Options'][1]";
	    private String floating_icon="xpath~//div[@id='floatingCirclesG']";
	    private String addToCart="xpath~//button[text()='Add to cart']";
	 
	 PropFileHandler _propFileHandler = new PropFileHandler(new Properties());
	 public DocumentInProgress_page(ThreadLocal<Controls> _controls) {

			this._controls = _controls.get();
			if (_propFileHandler.readProperty("testData", "timeout") != null) {
				this._timeOut = Integer.parseInt(_propFileHandler.readProperty("testData", "timeout"));
			} else
				this._timeOut =0;
	  }
	 /* This method is use to click on the set print option of any page */
	 
	 public void select_setprintoption(){
		 _controls.waitUntilElementToBeClickable("Set print option", setprintoption,"Waiting for set print option"); 
		 _controls.click("Set print option", setprintoption,"click on set print option");
	//	 _controls.waitUntilElementIsInVisible("Floating icon",floating_icon,25000,"Waiting for loading icon to be invisible");
		 _controls.waitUntilElementToBeClickable("Add to cart button",addToCart,"Waiting for add to cart button"); 
	 }
}
