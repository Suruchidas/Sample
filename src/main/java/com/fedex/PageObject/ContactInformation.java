package com.fedex.PageObject;

import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.infogain.uap.framework.utilities.PropFileHandler;
import com.infogain.uap.framework.webcontrols.Controls;

public class ContactInformation {
	Controls _controls;
	 int _timeOut;
	 
	 private String first_name="xpath~//input[@name='CustFirstName']";
	 private String last_name="xpath~//input[@name='CustLastName']";
	 private String phone_no="xpath~//input[@name='CustPhone']";
	 private String email="xpath~//input[@name='CustEmail']";
	 private String continue_contact="xpath~//a[@id='checkout-show-payment']";
	 
	 PropFileHandler _propFileHandler = new PropFileHandler(new Properties());
	 public ContactInformation(ThreadLocal<Controls> _controls) {

			this._controls = _controls.get();
			if (_propFileHandler.readProperty("testData", "timeout") != null) {
				this._timeOut = Integer.parseInt(_propFileHandler.readProperty("testData", "timeout"));
			} else
				this._timeOut =0;
	  }
	 /* This method is use to add the contact information */
	 
	 public void fill_contactInfo()throws Exception{
		 _controls.waitUntilElementToBeClickable("First name", first_name,"Waiting for first name");
		 _controls.enterText("First name text box", first_name, _propFileHandler.readProperty("testData", "FIRSTNAME"),"Enter first name");
		 _controls.enterText("Last  name text box", last_name, _propFileHandler.readProperty("testData", "LASTNAME"),"Enter last  name");
		 _controls.enterText("Enter phone name", phone_no, _propFileHandler.readProperty("testData", "PHONENO"),"Enter phone name");
		 _controls.enterText("Enter email name", email, _propFileHandler.readProperty("testData", "EMAIL"),"Enter email name");
		 _controls.waitUntilElementIsVisible("Continue button", continue_contact, 10000,"Waiting for continue button", false);
	     Thread.sleep(4000);
	
		 WebDriverWait wait=new WebDriverWait(_controls.getDriver(),10000);
		 wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@id='checkout-show-payment']")));
		 _controls.click("Continue button", continue_contact, "Click on continue button");
		
	}
}
