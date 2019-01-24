package com.fedex.PageObject;

import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.infogain.uap.framework.utilities.PropFileHandler;
import com.infogain.uap.framework.webcontrols.Controls;

public class PickUpDelivery {
	Controls _controls;
	 int _timeOut;
	 
	 private String delivery_radio="xpath~//input[@name='deliverytype' and @value='2']";
	 private String continue_pickup="xpath~//a[@id='checkout-show-location']";
	 private String search_box="xpath~//input[@id='loc_seacrh_text']";
	 private String select_location="xpath~//ul[@id='centersListRows']/li[1]";
	 private String floating_icon="xpath~//div[@id='floatingCirclesG']";
	 
	 PropFileHandler _propFileHandler = new PropFileHandler(new Properties());
	 public PickUpDelivery(ThreadLocal<Controls> _controls) {

			this._controls = _controls.get();
			if (_propFileHandler.readProperty("testData", "timeout") != null) {
				this._timeOut = Integer.parseInt(_propFileHandler.readProperty("testData", "timeout"));
			} else
				this._timeOut =0;
	  }
	 /* here in this method we are selecting the zip code
	  * and pickup address, where we want the product
	  */
	 public void pickup()throws Exception{
	
		  Thread.sleep(20000);
		/*  _controls.waitForPageLoad("waiting for pickup page to load");
		  _controls.waitUntilElementToBeClickable("search box",continue_pickup,"waiting for search box to be appear");
		  _controls.waitUntilElementIsVisible("search box",continue_pickup,20000,"waiting for search box",false);*/
	//	 _controls.waitUntilElementIsInVisible("Floating icon",floating_icon,25000,"Waiting for loading icon to be invisible");
		_controls.enterText("Pincode code", search_box,_propFileHandler.readProperty("testData", "STATEZIPCODE"),"enter pincode");
	//	_controls.waitUntilElementToBeClickable("continue button", continue_pickup,"Waiting for continue button");
		_controls.sendKeys("Enter ", search_box,Keys.ENTER,"Press enter");
	//	  Thread.sleep(15000);
		_controls.waitUntilElementToBeClickable("Location to popup", select_location, "Waiting for location to popup");
		_controls.clickUsingJavaScriptExecutors("Map element", select_location, "Selecting map");
	    _controls.clickUsingJavaScriptExecutors("Continue button",continue_pickup , "Click on continue button");
	 }
}
