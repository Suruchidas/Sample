package com.fedex.PageObject;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.infogain.uap.framework.webcontrols.Controls;

public class Browser {
    Controls _controls; 
    
	public String getBrowserName(){
		WebDriver driver=_controls.getDriver();
		Capabilities cap=((RemoteWebDriver)driver).getCapabilities();
		String browser_name=cap.getBrowserName();
		System.out.println(browser_name);
		return browser_name;
	}
}
