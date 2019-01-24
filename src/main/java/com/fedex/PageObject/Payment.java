package com.fedex.PageObject;

import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import com.infogain.uap.framework.utilities.PropFileHandler;
import com.infogain.uap.framework.webcontrols.Controls;
import com.infogain.uap.logger.LogType;
import com.infogain.uap.logger.Reporting;

public class Payment {
	Controls _controls;
	 int _timeOut;
	 PropFileHandler _propFileHandler = new PropFileHandler(new Properties());
	 
	 private String card_type="xpath~//select[@id='pol-select-card']";
	 private String card_no="xpath~//input[@name='cardNumber']";
	 private String security_code="xpath~//input[@name='cardNumber']//following::input[1]";
	 private String exp_month="xpath~//select[@id='pol-select-card']//following::select[1]";
	 private String exp_year="xpath~//select[@id='pol-select-card']//following::select[2]";
	 private String nameOnCard="xpath~//input[@name='cardNumber']//following::input[2]";
	 private String sameAsContact="xpath~//input[@name='cardNumber']//following::input[4]";
	 private String address="xpath~//input[@name='cardNumber']//following::input[7]";
	 private String city="xpath~//input[@name='cardNumber']//following::input[9]";
	 private String zip_code="xpath~//input[@name='cardNumber']//following::input[10]";
	 private String country="xpath~//select[@id='pol-select-card']//following::select[3]";
	 private String state_zipcode="xpath~//select[@id='pol-select-card']//following::select[4]";
	 private String continue_review="xpath~//a[@id='checkout-show-rewiew']";
	 private String review_order="xpath~//div[@class='checkout-li-title']//h2[text()='4. Review order']";
	 private String floating_icon="xpath~//div[@id='floatingCirclesG']";
			 
	 
	 public Payment(ThreadLocal<Controls> _controls) {

			this._controls = _controls.get();
			if (_propFileHandler.readProperty("testData", "timeout") != null) {
				this._timeOut = Integer.parseInt(_propFileHandler.readProperty("testData", "timeout"));
			} else
				this._timeOut =0;
	  }
	 
	 /* This method is use to select all the valid
	  * details of credit card 
	  */
	 public void select_paymentInfo(){
		
		 _controls.waitUntilElementToBeClickable("Payment dropdown", card_type, "waiting for select payment dropdown");
		 _controls.selectDropDown("Selecting value from card", card_type,_propFileHandler.readProperty("testData","CARDTYPE"),"Selecting value from payment drop down");
		 _controls.enterText("Enter card no text box",card_no, _propFileHandler.readProperty("testData","CARDNO"),"Enter card no");
		 _controls.enterText("Security no text box",security_code, _propFileHandler.readProperty("testData","SECURITYCODE"), "Enter security no text box");
		 _controls.selectDropDown("Drop down", exp_month, _propFileHandler.readProperty("testData", "EXPMONTH"), "Selecting exp month");
		 _controls.selectDropDown("Exp year drop down", exp_year, _propFileHandler.readProperty("testData", "EXPYEAR"),"Selecting exp year");
		 _controls.enterText("Card holder text box", nameOnCard, _propFileHandler.readProperty("testData","NAMEONCARD"), "Enter name of card holder");
		 _controls.waitUntilElementIsInVisible("Floating icon",floating_icon,70000,"Waiting for loading icon to be invisible");
		 
	 }
	 /* this method is use to fill all the required
	  * billing address
	  */
	 public void billing_address() throws InterruptedException{
		/* WebDriverWait wait=new WebDriverWait(_controls.getDriver(),10000);
		 wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='cardNumber']//following::input[4]")));*/
		 _controls.waitUntilElementIsVisible("Card number",nameOnCard,10000,"Waiting for card no. visible",false);
		 _controls.click("Contact information check box", sameAsContact, "Click on the checkbox of same as contact information");
		 _controls.enterText("Address text box", address, _propFileHandler.readProperty("testData", "ADDRESS"), "Enter address");
		 _controls.selectDropDown("Selecting country", country,_propFileHandler.readProperty("testData","COUNTRY"),null);
		 _controls.enterText("enter city", city, _propFileHandler.readProperty("testData", "CITY"), null);
		 
	//	 wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//select[@id='pol-select-card']//following::select[4]")));
		 _controls.selectDropDown("State code", state_zipcode,_propFileHandler.readProperty("testData","STATECODE"),"Enter state code");
		 _controls.enterText("Zip code", zip_code, _propFileHandler.readProperty("testData", "STATEZIPCODE"), "Enter Zip code");
		 _controls.click("Continue button of review", continue_review, "Click on continue button of review");
		 _controls.waitUntilElementIsInVisible("Floating icon",floating_icon,70000,"Waiting for loading icon to be invisible");
	//	 Thread.sleep(20000);
	 }
	 
	 /* This method is use to validates the page that
	  * the review order text on the Review order page 
	  */
	 
	 public void Validate_page(){
		 _controls.waitUntilElementToBeClickable("Review order",review_order,"waiting for review order");
		 String review_order_title_actual=_controls.getText("Review order",review_order,"Fetching title of review order");
		 System.out.println("review order title"+review_order_title_actual);
		 if(review_order_title_actual.equals("4. Review order")){
			 Reporting.getLogger().log(LogType.PASS, "title is correct");
		 } else{
		       Reporting.getLogger().log(LogType.FAIL, "title is not correct");
			 }
		 
	 }
}
