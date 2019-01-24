package com.fedex.PageObject;

import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.infogain.uap.framework.utilities.PropFileHandler;
import com.infogain.uap.framework.webcontrols.Controls;

public class Checkout_page {
	Controls _controls;
	 int _timeOut;
	 
	 private String checkout="xpath~//a[@id='cartToCheckoutBtn']";
	 private String checkout1="xpath~//a[text()='Checkout']";
	 private String floating_icon="xpath~//div[@id='floatingCirclesG']";
	 PropFileHandler _propFileHandler = new PropFileHandler(new Properties());
	 public Checkout_page(ThreadLocal<Controls> _controls) {

			this._controls = _controls.get();
			if (_propFileHandler.readProperty("testData", "timeout") != null) {
				this._timeOut = Integer.parseInt(_propFileHandler.readProperty("testData", "timeout"));
			} else
				this._timeOut =0;
	  }
	 /* This method is use to click on the checkout button */
	 public void checkout_item()throws Exception{
		 Thread.sleep(5000);
		 _controls.waitForPageLoad("waiting for page to load");
		 _controls.waitUntilElementIsInVisible("Floating icon",floating_icon,25000,"Waiting for loading icon to be invisible");
		 _controls.waitUntilElementToBeClickable("Checkout button",checkout1,"Waiting for checkout button to be clickable");
		 _controls.waitUntilElementIsVisible("checkout button",checkout1,15000,"waiting for checkout button",false);
	//	 Thread.sleep(15000);

		 _controls.clickUsingJavaScriptExecutors("Checkout button",checkout,"click on checkout button");
	//	 _controls.waitUntilElementIsInVisible("Floating icon",floating_icon,25000,"Waiting for loading icon to be invisible");
		
	 }
	/* This method is use to scroll the page by 500 pixcel vertical */
	 
	 public void scroll_page() throws InterruptedException{
		// Thread.sleep(5000);
		 _controls.waitUntilElementIsInVisible("Floating icon",floating_icon,25000,"Waiting for loading icon to be invisible");
		 _controls.waitForPageLoad("waiting for page to load");
		 _controls.waitUntilElementToBeClickable("Checkout button",checkout1,"Waiting for checkout button to be clickable");
		 JavascriptExecutor js = (JavascriptExecutor) _controls.getDriver();
		 js.executeScript("window.scrollBy(0,500)");
	 }
}
