package com.fedex.PageObject;

import java.util.Properties;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.infogain.uap.framework.utilities.PropFileHandler;
import com.infogain.uap.framework.webcontrols.Controls;

public class HomePage {
	 Controls _controls;
	  int _timeOut;
	  
	  private String singl_sheet="xpath~//div[@class='card__image_vector-lg icon__single-page-files']";
	  private String multiple_sheet="xpath~//div[@class='card__image_vector-lg icon__multi-page-files']";
	  private String poster_signs="xpath~//div[@class='card__image_vector-lg icon__large-format']";
	  private String Speciality_graphics="xpath~//div[text()='Specialty Graphics']";
	  private String getStarted_button="xpath~//a[@data-upload-url='/apps/printonline/#!upload/single/pid%3D1447174746733/false']";
	  private String getStarted_multiple_button="xpath~//a[@data-upload-url='/apps/printonline/#!upload/multiple/pid%3D1456773326927/false']";
	  private String getStarted_speciality="xpath~//a[@title='Get Started - Car Magnets']";
	  private String document_printing="xpath~//div[text()='Document Printing']";
	  private String close_sign="xpath~//area[@alt='close']";
	  private String floating_icon="xpath~//div[@id='floatingCirclesG']";
	  
	  
	  private String marketing_materl="xpath~//div[text()='Marketing Materials']";
	  private String mark_banner="xpath~//img[@title='Banner Stands']";
	  private String fileUpload="xpath~//a[text()='Use Your File']";
	  
	  PropFileHandler _propFileHandler = new PropFileHandler(new Properties());
	  public HomePage(ThreadLocal<Controls> _controls) {

			this._controls = _controls.get();
			if (_propFileHandler.readProperty("testData", "timeout") != null) {
				this._timeOut = Integer.parseInt(_propFileHandler.readProperty("testData", "timeout"));
			} else
				this._timeOut =0;

		}
	  /* This method is use to launch the URL */
	  
	  public void launch_url(){
		  _controls.launchUrl("launching the URL", _propFileHandler.readProperty("testData", "URL"));
		System.out.println("Dimensions of window before set the size"+_controls.getDriver().manage().window().getSize());
	//	  _controls.getDriver().manage().window().setSize(new Dimension(1360,760)); 
		  
		  System.out.println("Dimensions of window after set the size"+_controls.getDriver().manage().window().getSize());
	  }

	  /* This method is use to select the speciality feature */
	  
	  public void Selecting_specialityFeature() throws InterruptedException{
		  _controls.waitUntilElementToBeClickable("Speciality feature button",Speciality_graphics,"Waiting for Speciality feature" );
		  _controls.click("Speciality feature",Speciality_graphics,"Click on speciality feature");
		  _controls.waitUntilElementToBeClickable("Get Started button",getStarted_speciality, "Waiting for get Started button");
		  _controls.click("Get Started button",getStarted_speciality, "clicking on get started button");
		  Thread.sleep(10000);
		  
	  }
	  /* This method is use to selct the document for single sheet */
	  public void Selecting_DocumentPrinting(){
		   _controls.waitUntilElementToBeClickable("Document Printing link",document_printing,"Waiting for document printing");
		   _controls.clickUsingJavaScriptExecutors("Document printing button",document_printing,"clicking on document printing link");
		   _controls.waitUntilElementIsInVisible("Floating icon",floating_icon,70000,"Waiting for loading icon to be invisible");
		   _controls.waitUntilElementToBeClickable("get start button",getStarted_button,"waiting for get start button");
	//	   _controls.waitUntilElementToBeClickable("Get Started button", getStarted_button,"waiting for Get started button");
		   _controls.clickUsingJavaScriptExecutors("Get Started button",getStarted_button, "clicking on Get Stareted button");
		   _controls.waitUntilElementIsInVisible("Floating icon",floating_icon,70000,"Waiting for loading icon to be invisible");
	  }
	  
	  /* This method is use to selct the document for multiple sheet*/
	  public void Selecting_DocumentPrinting_forMultiple(){
		   _controls.waitUntilElementToBeClickable("Document Printing link",document_printing,"Waiting for document printing");
		   _controls.clickUsingJavaScriptExecutors("Document printing button",document_printing,"clicking on document printing link");
		   _controls.waitUntilElementToBeClickable("Get Started button", getStarted_multiple_button,"waiting for Get started button for multiple");
		   _controls.clickUsingJavaScriptExecutors("Get Started button",getStarted_multiple_button, "clicking on Get Stareted button for multiple");
	  }
	  
	  /* This method is for marketing materials */
	  public void clickOn_marketing_materl() throws InterruptedException{
		  _controls.waitUntilElementToBeClickable("marketing material button",marketing_materl,"Waiting for marketing material" );
		  _controls.click("marketing material",marketing_materl,"Click on marketing material");
		  Thread.sleep(1000);
		  _controls.click("marketing material -mark_banner",mark_banner,"Click on mark_banner");
		  Thread.sleep(1000);
		  _controls.click("marketing material -fileUpload",fileUpload,"Click on fileUpload");
		  
		  }
	  
	  
}
