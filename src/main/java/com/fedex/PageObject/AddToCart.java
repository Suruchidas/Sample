package com.fedex.PageObject;

import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import com.infogain.uap.framework.utilities.PropFileHandler;
import com.infogain.uap.framework.webcontrols.Controls;
import com.infogain.uap.logger.LogType;
import com.infogain.uap.logger.Reporting;
import com.thoughtworks.selenium.webdriven.commands.WaitForPageToLoad;

public class AddToCart {
	Controls _controls;
	 int _timeOut;
	 PropFileHandler _propFileHandler = new PropFileHandler(new Properties());
	 
	 private String back_link="xpath~//a[@id='js-btn-back']";
	 private String plus_sign="xptah~//li[@id='settings-uploaded-files']//span[@class='valign-mid flyout-text__parent']";
	 private String use_same_file="xpath~//a[@id='sameBackBtn']";
	 private String addToCart="xpath~//button[text()='Add to cart']";
	 private String addAnotherFile="xpath~//a[text()='Add another file']";
	 private String size="xpath~//li[@class='settings-item m-flyout fxo-feature ']//span[text()='Size']";
	 private String select_size="xpath~//li[@class='settings-subitem p0 materialBanner']//following::span[6]";
	 private String text_size="xpath~//span[@class='valign-mid flyout-text__parent']//following::em[@name='previewconfigurator-printOptions-lbl'][2]";
	 private String binding_cover="xpath~//li[@class='settings-item m-flyout fxo-feature']//span[text()='Binding & Covers']";
	 private String binding="xpath~//div[@class='placard-header ' and text()='Binding']";
	 private String coil_binding="xpath~//div[@class='placard-header ' and text()='Binding']//following::li[3]/a";
	 private String finish_button="xpath~//div[@class=' cols-2 col']//following::button[3]";
	 private String landscape="xpath~//li[@class='settings-subitem p0 materialBanner']//span[text()='Landscape']";
	 private String orientation="xpath~//li[@class='settings-item m-flyout fxo-feature ']//span[text()='Orientation']";
	 private String additional_instruction="xpath~//a[@class='settings-link settings-link-more ico ico-plus']//span[1]";
	 private String textArea="xpath~//textarea[@id='special-instructions-entered']";
	 private String price_configrator="xpath~//span[@name='update_quanity-splInstPrice-lbl']";
	 private String Paper="xpath~//li[@class='settings-item m-flyout fxo-feature ']//span[text()='Paper']";
	 private String standard_color_phote="xpath~//div[@class='placard-header' and text()='Standard Color Papers']";
	 private String Canary_color="xpath~//strong[text()='Canary (24 lb.)']";
	 private String entire_document="xpath~//a[@class='settings-sublink ico ico-entire-document']/span[@class='valign-mid'][1]";
	 private String floating_icon="xpath~//div[@id='floatingCirclesG']";
	 public AddToCart(ThreadLocal<Controls> _controls) {

			this._controls = _controls.get();
			if (_propFileHandler.readProperty("testData", "timeout") != null) {
				this._timeOut = Integer.parseInt(_propFileHandler.readProperty("testData", "timeout"));
			} else
				this._timeOut =0;
	  }
	 /* This method is use to add back page in single sheet*/
	 public void add_backPage()throws Exception{
		   
		    _controls.waitUntilElementToBeClickable("back button", back_link, "Waiting for element to be clickable");
			 _controls.click("Back link",back_link, "click on back link");
	
			
		//	 Thread.sleep(10000);
		//	 _controls.waitUntilElementIsVisible("plus sign", plus_sign, 10000, "waiting for plus sign",false);
			 _controls.waitUntilElementToBeClickable("Plush sign", plus_sign, "waiting for plush sign to click");
			 _controls.getDriver().findElement(By.xpath("//li[@id='settings-uploaded-files']//span[@class='valign-mid flyout-text__parent']")).click();
			 
		//	 _controls.waitUntilElementIsVisible("Use same file button", use_same_file, 10000, "waiting for use same button",false);
			 _controls.waitUntilElementToBeClickable("Use same file button", use_same_file,"waiting for use same button to click");
			 _controls.clickUsingJavaScriptExecutors("Use same file button", use_same_file,"click on use same file");
	
	 }
	 /* This method is use to click on the plus sign and then click on addAnotherFile button */
	 public void add_forMultiplesheet()throws Exception{
		 _controls.waitUntilElementToBeClickable("Plush sign", plus_sign, "Waiting for plush sign");
	//	 Thread.sleep(10000);
		 _controls.getDriver().findElement(By.xpath("//li[@id='settings-uploaded-files']//span[@class='valign-mid flyout-text__parent']")).click();
		 _controls.waitUntilElementIsVisible("waiting for add another file button", addAnotherFile, 10000, null,false);
	//	 Thread.sleep(4000);
		 _controls.waitUntilElementToBeClickable("Add another file button", addAnotherFile, "waiting for add another file button to click");
		 _controls.clickUsingJavaScriptExecutors("Add another file button",addAnotherFile,"click on add another file");
	//	 Thread.sleep(10000);
		 
		 }
	 
	 /*This method is use to click on the Add to cart button */
	 public void AddToCart()throws Exception{

	//	 Thread.sleep(10000);
	    _controls.waitUntilElementToBeClickable("Add to cart button",addToCart,"Waiting for add to cart button");
		 _controls.clickUsingJavaScriptExecutors("Add to cart", addToCart, "click on add to cart");
	   _controls.waitForPageLoad("waiting for page to upload");
	 }
	 /* This method is use to click on addToCart  button */
	 public void newAddtoCart() throws InterruptedException{
	//	 Thread.sleep(15000);
		 _controls.waitUntilElementToBeClickable("Add to cart button",addToCart,"Waiting for add to cart button");
		 _controls.getDriver().findElement(By.xpath("//button[text()='Add to cart']")).click();
	 }
	 
	 /* This method is use to select the size header of sheet */
	 public void sizeOfSingleSheet()throws Exception{
		 _controls.waitForPageLoad("waiting for page load ");
		 _controls.waitUntilElementToBeClickable("Add to cart button",addToCart,"Waiting for add to cart button");
		 _controls.waitUntilElementToBeClickable("Size element", size, "Waiting for size element");
		 _controls.getDriver().findElement(By.xpath("//li[@class='settings-item m-flyout fxo-feature ']//span[text()='Size']")).click();
	
	 }
	 
	 /* This methos is use to get the size and match with the expected size */
	 public void select_size(String expected_size)throws Exception{
		//  Thread.sleep(8000);
		  _controls.waitUntilElementToBeClickable("Add to cart button",addToCart,"Waiting for add to cart button");
		 _controls.waitUntilElementToBeClickable("select size element", select_size,"waiting for select size element");
		 _controls.clickUsingJavaScriptExecutors("select size", select_size, "selecting size");
//		 Thread.sleep(70000);
//		 _controls.waitUntilElementToBeClickable("Add to cart button",addToCart,"Waiting for add to cart button");
		 _controls.waitUntilElementIsVisible("size text",text_size,10000,"taking size",false);
//		 _controls.getDriver().findElement(By.xpath("//li[@class='settings-item m-flyout fxo-feature']//span[@class='valign-mid flyout-text__parent']")).click();
		 String actual_size=_controls.getText("Size", text_size, "taking value of size");
		 System.out.println("actual size:  "+actual_size);
		 if(actual_size.equals(expected_size)){
			 Reporting.getLogger().log(LogType.PASS, "size is correct");
			 
		 }else{
			 Reporting.getLogger().log(LogType.FAIL, "size is not correct");
		 }
			 
		  Thread.sleep(15000);
	 }
	 /* This method is use to select the binding option as Coil in sheet */
	 public void Select_bindingCover()throws Exception{
	//	_controls.waitUntilElementIsVisible("Binding element", binding_cover, 15000, "Waiting for binding element",false);
		 _controls.waitUntilElementToBeClickable("Binding cover element", binding_cover,"waiting for binding cover element");
		 _controls.getDriver().findElement(By.xpath("//li[@class='settings-item m-flyout fxo-feature ']//span[text()='Binding & Covers']")).click();
		 _controls.clickUsingJavaScriptExecutors("Sub heading of binding", binding,"Click on sub heading of binding");
		 _controls.waitUntilElementToBeClickable("Coil option", coil_binding,"waiting for coil option");
		 _controls.clickUsingJavaScriptExecutors("Coil option",coil_binding, "Clicking on coil option");
	//	 Thread.sleep(10000);
		 _controls.waitUntilElementIsVisible("Finish button", finish_button, 15000,"Waiting for finish button",false);
		 _controls.waitUntilElementToBeClickable("Finish button", finish_button,"Waiting for finish button");
		 _controls.clickUsingJavaScriptExecutors("Finish button",finish_button,"clicking on finish button");
		   _controls.waitUntilElementIsInVisible("Floater element",floating_icon,25000,"waiting for floating element to invisible");
	
	 }
	 
	 /* This will select the orientation of page as Landscape */
	 public void Select_Orientation() throws InterruptedException{
	//Need to be check--	 Thread.sleep(20000);
		 _controls.waitUntilElementToBeClickable("Add to cart button",addToCart,"Waiting for add to cart button");
		 _controls.waitUntilElementToBeClickable("Binding cover element", orientation, "waiting for binding cover element");
		_controls.getDriver().findElement(By.xpath("//li[@class='settings-item m-flyout fxo-feature ']//span[text()='Orientation']")).click();
		_controls.clickUsingJavaScriptExecutors("Landscape",landscape,"CLick on landscape");
	//	 _controls.waitUntilElementIsInVisible("Floater element",floating_icon,30000,"waiting for floating element to invisible");
		 _controls.waitUntilElementToBeClickable("Add to cart button",addToCart,"Waiting for add to cart button");
		 Thread.sleep(3000);
	 }
	 /* This method is use to select the paperColor of sheet */
	 public void Select_paperColor() throws InterruptedException{
		//need to be check ---   Thread.sleep(8000);
		   _controls.waitUntilElementToBeClickable("Add to cart button",addToCart,"Waiting for add to cart button");
		//   _controls.waitUntilElementToBeClickable("Paper option",Paper,"Waiting for paper option");
		   _controls.getDriver().findElement(By.xpath("//li[@class='settings-item m-flyout fxo-feature ']//span[text()='Paper']")).click();
		   _controls.clickUsingJavaScriptExecutors("STANDARD COLOR PAPER", standard_color_phote,"CLICKING ON STANDARD COLOR PAPER");
		   _controls.waitUntilElementToBeClickable("Canary option",Canary_color,"Wating for canary option");
		   _controls.clickUsingJavaScriptExecutors("Canary value",Canary_color, "Selecting canary value");
//		   _controls.waitUntilElementIsInVisible("Floater element",floating_icon,25000,"waiting for floating element to invisible");
		   Thread.sleep(15000);
	 }
	 /* This method is use to select the paper color of multisheet */
	 
	 public void Select_paperColor_ofMultisheet() throws InterruptedException{
		  _controls.waitUntilElementToBeClickable("Paper option",Paper, "Waiting for paper option");
		// Need to be check--   Thread.sleep(10000);
		   _controls.waitUntilElementToBeClickable("Add to cart button",addToCart,"Waiting for add to cart button");
		   _controls.getDriver().findElement(By.xpath("//li[@class='settings-item m-flyout fxo-feature']//span[text()='Paper']")).click();
	
		   _controls.clickUsingJavaScriptExecutors("Entire option",entire_document,"Clicking on entire option");
		   _controls.waitUntilElementToBeClickable("Standard color option", standard_color_phote,"waiting for standard color option");
		   _controls.clickUsingJavaScriptExecutors("STANDARD COLOR PAPER", standard_color_phote, "CLICKING ON STANDARD COLOR PAPER");
		   _controls.waitUntilElementToBeClickable("Canary option",Canary_color,"Waiting og canary option");
		   _controls.clickUsingJavaScriptExecutors("Canary value",Canary_color,"Selecting canary value");
//		   _controls.waitUntilElementIsInVisible("Floater element",floating_icon,25000,"waiting for floating element to invisible");
		  Thread.sleep(15000);
	 }
	 
	/* This method is use to add some special instruction and comparing the price with expected one */ 
	 
	 public void addAdditionalInstruction()throws Exception{
		 _controls.waitUntilElementToBeClickable("Additinal instruction text area",additional_instruction,"waiting for additinal instruction thing");
//		 Thread.sleep(6000);
		 _controls.clickUsingJavaScriptExecutors("Additional instruction text area", additional_instruction, "Click on additional instruction button");
		 _controls.enterText("Text area", textArea, " special price ", "enter instruction");
	//	 Thread.sleep(2000);
	     _controls.getDriver().findElement(By.xpath("//textarea[@id='special-instructions-entered']")).sendKeys(Keys.TAB);
		// _controls.sendKeys("click on additional instruction button", textArea, Keys.TAB, null);
		 _controls.clickUsingJavaScriptExecutors("Additional instruction button", additional_instruction, "click on additional instruction button");
	     String value= _controls.getText("special instruction", price_configrator, "Taking value from price after applying the special instruction");
	     System.out.println("value of price after instruction :"+value);
	     if(value.equals("$--.--")){
	    	 Reporting.getLogger().log(LogType.PASS, " correct");
		 } else{
		       Reporting.getLogger().log(LogType.FAIL, "Not correct");
			 }
		
	 }
}
