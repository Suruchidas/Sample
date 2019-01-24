package com.fedex.PageObject;

import java.util.Properties;

import com.infogain.uap.framework.utilities.PropFileHandler;
import com.infogain.uap.framework.webcontrols.Controls;

public class Login_page {
	Controls _controls;
	 int _timeOut;
	 
	 private String login_link="xpath~//a[@id='globalHeaderLogin']";
	 private String login_email="xpath~//input[@id='customerEmail']";
	 private String login_password="xpath~//input[@id='customerPassword']";
	 private String login_submit="xpath~//*[text()='Log in']";
	 PropFileHandler _propFileHandler = new PropFileHandler(new Properties());
	 public Login_page(ThreadLocal<Controls> _controls) {

			this._controls = _controls.get();
			if (_propFileHandler.readProperty("testData", "timeout") != null) {
				this._timeOut = Integer.parseInt(_propFileHandler.readProperty("testData", "timeout"));
			} else
				this._timeOut =0;
	  }
	 
	 /* This method is use to login the fedEX
	  *  Printonline application
	  */
	 public void login_fedex(){
		 _controls.click("Login button",login_link , "click on login button");
		 _controls.enterText("User id text box", login_email, _propFileHandler.readProperty("testData", "USERID"),"Enter user id");
		 _controls.enterText("Password text box", login_password,  _propFileHandler.readProperty("testData", "PASSWORD"),"enter password");
		 _controls.click("Login button",login_submit, "click on login button");
	 }
}
