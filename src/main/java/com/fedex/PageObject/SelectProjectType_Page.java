package com.fedex.PageObject;

import java.util.Properties;

import com.infogain.uap.framework.utilities.PropFileHandler;
import com.infogain.uap.framework.webcontrols.Controls;

public class SelectProjectType_Page {
	 Controls _controls;
	  int _timeOut;
	  
	  String use_your_file="xpath~//a[@class='fx-btn'][1]";
	  
	  PropFileHandler _propFileHandler = new PropFileHandler(new Properties());
	  public SelectProjectType_Page(ThreadLocal<Controls> _controls) {

			this._controls = _controls.get();
			if (_propFileHandler.readProperty("testData", "timeout") != null) {
				this._timeOut = Integer.parseInt(_propFileHandler.readProperty("testData", "timeout"));
			} else
				this._timeOut =0;

		}
	  
	  public void select_useYuorFileOtion()throws Exception{
		  _controls.waitUntilElementToBeClickable("Use your file option button",use_your_file , "waiting for use your file option");
		  _controls.clickUsingJavaScriptExecutors("Use your file button", use_your_file, "Clicking on use your file button");
		  Thread.sleep(10000);
	  }
}
