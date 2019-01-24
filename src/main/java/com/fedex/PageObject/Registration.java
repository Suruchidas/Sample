package com.fedex.PageObject;

import java.util.Properties;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.infogain.uap.framework.uapControls.UapControls;
import com.infogain.uap.framework.utilities.PropFileHandler;
import com.infogain.uap.framework.webcontrols.Controls;
import com.infogain.uap.logger.LogType;
import com.infogain.uap.logger.Reporting;

public class Registration extends UapControls {
	 Controls _controls;
	  int _timeOut;
	  UploadFile _uploadFile;
	  private String register_link="xpath~//a[@title='Register']";
	  private String new_register_link="xpath~//a[@id='open-modal-register']";
	  private String login_user_menu="xpath~//span[@class='fxg-dd-trigger' and @aria-label='User Menu']";
	  private String first_name="xpath~//input[@id='first_name']";
	  private String last_name="xpath~//*[@id='last_name']";
	  private String register_email="xpath~//*[@id='register_email']";
	  private String user_id="xpath~//*[@id='user_id'] | id~user_id";
	  private String password="xpath~//*[@id='password']";
	  private String confirm_password="xpath~//*[@id='Confirm_password']";
	  private String select_question1="xpath~//*[@id='sec_Ques1']";
	  private String security_answr1="xpath~//*[@id='security_answer']";
	  private String select_question2="xpath~//*[@id='sec_Ques2']";
	  private String security_answr2="xpath~//*[@id='security_answer2']";
	  private String register_button="xpath~//button[text()='Register']";
	  private String continue_button="xpath~//*[text()='Continue']";
	  
	  PropFileHandler _propFileHandler = new PropFileHandler(new Properties());
	  Random random=new Random();
	  
	  public Registration(ThreadLocal<Controls> _controls) {

			this._controls = _controls.get();
			if (_propFileHandler.readProperty("testData", "timeout") != null) {
				this._timeOut = Integer.parseInt(_propFileHandler.readProperty("testData", "timeout"));
			} else
				this._timeOut =0;

		}
	  /* this method is use for registration process
	   *  of FedEX printonline application
	   */
	  public void fedEx_Registration()throws Exception{
		
	//	  _controls.launchUrl("Launching the url",_propFileHandler.readProperty("testData", "URL"));
		  _controls.waitForPageLoad("waiting for page load");
		  _controls.waitUntilElementToBeClickable("Register link", login_user_menu, "waiting for register link");
		   _controls.click("Register link", login_user_menu, "CLicking on register link");
		   _controls.waitForPageLoad("waiting for registration page");
		   _controls.clickUsingJavaScriptExecutors("New registration",new_register_link, "Clicking on registration link");
		
		   _controls.enterText("First name text box",first_name, _propFileHandler.readProperty("testData","FIRSTNAME"), "Entering first name");
		   _controls.enterText("Last name text box",last_name,_propFileHandler.readProperty("testData","LASTNAME"), "Entring value into last name");
		   _controls.enterText("Email id",register_email, _propFileHandler.readProperty("testData","EMAIL"), "Enter email id");
		   String user_id_value="Tsting"+random.nextInt(9999)+"@gmail.com";
		   _controls.enterText("User id text box",user_id, user_id_value, "enter user id");
		   _controls.enterText("Password text box",password, _propFileHandler.readProperty("testData","PASSWORD"),"Enter password");
		   _controls.enterText("Confirm password text box",confirm_password, _propFileHandler.readProperty("testData","PASSWORD"), "Enter confirm password");
		   _controls.selectDropDown("First question drop down", select_question1, _propFileHandler.readProperty("testData","SECURITY_QUESTION1"), "Selcting first question");
		   _controls.enterText("First answer text box",security_answr1,_propFileHandler.readProperty("testData","SECURITY_ANSWER"), "enter first answer");
		   _controls.selectDropDown("Second question drop down", select_question2, _propFileHandler.readProperty("testData","SECURITY_QUESTION2"), "Selcting second question");
		   _controls.enterText("first answer text box",security_answr2,_propFileHandler.readProperty("testData","SECURITY_ANSWER2"), "enter first answer");
		   _controls.click("Register button", register_button, "click on register button");
		   _controls.waitUntilElementToBeClickable("Continue button",continue_button,"waiting for continue button after registration");
		   _controls.clickUsingJavaScriptExecutors("Continue button", continue_button, "click on continue button");
/*		   //waiting for alert to present
		   WebDriverWait wait=new WebDriverWait(_controls.getDriver(),10);
		   wait.until(ExpectedConditions.alertIsPresent());
		   _controls.switchAndCancelAlert("Avoiding alert",true);
		//ending of alert handeling
*/		   WebElement login_user_name=_controls.getDriver().findElement(By.xpath("//span[@class='fxg-dd-trigger' and @aria-label='User Menu']"));
			  String actual_user_name=login_user_name.getText();
		      System.out.println("user name :"+actual_user_name);
			  
			  if(actual_user_name.contains(_propFileHandler.readProperty("testData", "FIRSTNAME"))){
				
				  Reporting.getLogger().log(LogType.PASS, "Logined successfully");
			  }else{
				  
				  Reporting.getLogger().log(LogType.FAIL, "Logined unsuccessful");
			  } 
		
		  
	  }
	 
}
