package com.fedex.PageObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.infogain.uap.framework.utilities.PropFileHandler;
import com.infogain.uap.framework.webcontrols.Controls;

public class UploadFile {
	Controls _controls;
	 int _timeOut;
	public static boolean pop_flag=false;
	public static boolean pop_flag_add_close=false; 
	public static boolean pop_inactive_flag=false;
	 PropFileHandler _propFileHandler = new PropFileHandler(new Properties());
	 
	  private String my_device="xpath~//img[@class='upload-icon '][1]";
	  private String close_button="xpath~//area[@href='javascript:ipe125413.clWin()']";
	  private String addToCart="xpath~//button[text()='Add to cart']";
	  private String progressBar="xpath~//progress[@id='progressBar']";
	  private String ok_popUp_button="xpath~//a[text()='OK']";
	  private String close_btn_add="xpath~//img[@alt='Close']";
	  public UploadFile(ThreadLocal<Controls> _controls) {

			this._controls = _controls.get();
			if (_propFileHandler.readProperty("testData", "timeout") != null) {
				this._timeOut = Integer.parseInt(_propFileHandler.readProperty("testData", "timeout"));
			} else
				this._timeOut =0;
	  }
	  /* This method is use to close the pop-up which is randomly coming */
	  
	  public void close_pop_up() throws InterruptedException{
		  
		  List<WebElement> list_alert=new ArrayList<>();
		  try{
//		 _controls.waitUntilElementIsVisible("close pop-up",close_button,10000,"waiting for close button",true);
		WebDriverWait wait=new WebDriverWait(_controls.getDriver(),10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//area[@href='javascript:ipe125413.clWin()']")));
		 list_alert= _controls.getDriver().findElements(By.xpath("//area[@href='javascript:ipe125413.clWin()']"));
		  System.out.println("alert size:  "+list_alert.size());
		  if(list_alert.size()>0){
			  pop_flag=true;
			  System.out.println(pop_flag);
		  _controls.clickUsingJavaScriptExecutors("No button", close_button,"Handeling pop up");
		  }else{
			  System.out.println("No alert present");
		  }
		  }catch(Exception e){
			  System.out.println("no close pop up");
			
		 }
	}
	  
	  public void close_add(){
		  List<WebElement> list_alert=new ArrayList<>();
		  try{
//		 _controls.waitUntilElementIsVisible("close pop-up",close_button,10000,"waiting for close button",true);
		WebDriverWait wait=new WebDriverWait(_controls.getDriver(),10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//img[@alt='Close']")));
		 list_alert= _controls.getDriver().findElements(By.xpath("//img[@alt='Close']"));
		  System.out.println("alert size:  "+list_alert.size());
		  if(list_alert.size()>0){
			  pop_flag=true;
			  System.out.println("Add is there :"+pop_flag);
		  _controls.clickUsingJavaScriptExecutors("No button", close_btn_add,"Closing add pop up");
		  }else{
			  System.out.println("No add is coming");
		  }
		  }catch(Exception e){
			  System.out.println("no add pop up");
			
		 }
	  }
	  
	  /* Closing pop-up related to inactivity of page*/
	  
	  public void close_inactive_popUp(){
		  
			  int ok_button_size;
			  List<WebElement> list_alert=new ArrayList<>();
			  try{
		//	  _controls.waitUntilElementIsVisible("close pop-up",ok_popUp_button,10,"waiting for add",true);
			  WebDriverWait wait=new WebDriverWait(_controls.getDriver(),15);
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='OK']")));
              list_alert= _controls.getDriver().findElements(By.xpath("//a[text()='OK']"));
			  if(list_alert.size()>2){
				  ok_button_size=list_alert.size();
				  System.out.println(" size of pop_inactive alert     "+ok_button_size );
			  while(ok_button_size>2){
				
			  _controls.clickUsingJavaScriptExecutors("OK button", ok_popUp_button,"Handeling inactive pop up");
			  ok_button_size--;
			  }
			  pop_inactive_flag=true;
			  _controls.waitUntilElementIsInVisible("progress bar",progressBar,70000,"Waiting till progress bar get invisible");
			  _controls.waitForPageLoad("waiting for page to upload again");
			  }else{
				  System.out.println("No in active pop up");
			  }
			  }catch(Exception e){
				  System.out.println(" no maintainence pop up");
				
				  
			  }
			 
		  }
	  
	  
	  /* this method is use to upload the file */
	  public void upload_file()throws Exception{
		  _controls.waitForPageLoad("waiting for uploading page");
		 
		  _controls.waitUntilElementIsVisible("waiting for element to be clickable", my_device,15000, null,false);
		  _controls.waitUntilElementToBeClickable("My device button", my_device,"Clicking on my device option" );
		  _controls.click("clicking on my device",my_device, null);
		  Thread.sleep(5000);
		   WebDriver driver=_controls.getDriver();
			 Capabilities cap = ((RemoteWebDriver) driver).getCapabilities();
			 String bname = cap.getBrowserName();
		    System.out.println(" my browser :"+bname);
		  Runtime.getRuntime().exec(System.getProperty("user.dir")+"\\src\\main\\resources\\AutoIt\\FileUpload.exe "+ " " + bname + " " + System.getProperty("user.dir")+"\\src\\main\\resources\\FEDEX_TESTING.docx");
//		 
//		  Runtime.getRuntime().exec("D:\\workspace\\TestingWorkspace\\FedEX_printApplication\\src\\main\\resources\\AutoIt\\FileUpload.exe D:\\workspace\\TestingWorkspace\\FedEX_printApplication\\src\\main\\resources\\FEDEX_TESTING.docx");
		  _controls.waitForPageLoad("waiting for page load");
//		  _controls.waitUntilElementIsInVisible("progress bar",progressBar,70000,"Waiting till progress bar get invisible");
		 Thread.sleep(15000);
	  }
	  
/*	  public void upload_file_fireFox() throws Exception{
		  _controls.waitForPageLoad("waiting for uploading page");
			 
		  _controls.waitUntilElementIsVisible("waiting for element to be clickable", my_device,15000, null,false);
		  _controls.waitUntilElementToBeClickable("My device button", my_device,"Clicking on my device option" );
		  _controls.click("clicking on my device",my_device, null);
		  Thread.sleep(5000);
		  Runtime.getRuntime().exec("C:\\Users\\manish.rai\\Desktop\\FireFox_upload\\upload_file_firefox.exe "+ System.getProperty("user.dir")+"\\src\\main\\resources\\FEDEX_TESTING.docx");
//		  Runtime.getRuntime().exec("D:\\workspace\\TestingWorkspace\\FedEX_printApplication\\src\\main\\resources\\AutoIt\\FileUpload.exe D:\\workspace\\TestingWorkspace\\FedEX_printApplication\\src\\main\\resources\\FEDEX_TESTING.docx");
		  _controls.waitForPageLoad("waiting for page load"); 
	  }*/
	  /* This method is use to upload the multiple file */
	  public void upload_multipleFile()throws Exception{
		  _controls.waitForPageLoad("waiting for uploading page");
		  _controls.waitUntilElementIsVisible("My device button", my_device,15000, "waiting for element to be clickable",false);
		  _controls.waitUntilElementToBeClickable("My device element", my_device, "waiting for my device element");
		  _controls.click("My device",my_device, "clicking on my device");
		  Thread.sleep(5000);
		  WebDriver driver=_controls.getDriver();
			 Capabilities cap = ((RemoteWebDriver) driver).getCapabilities();
			 String bname = cap.getBrowserName();
		    System.out.println(" my browser :"+bname);
		  Runtime.getRuntime().exec(System.getProperty("user.dir")+"\\src\\main\\resources\\autoit_multiple\\FileUpload_multiple.exe "+ " " + bname + " " + System.getProperty("user.dir")+"\\src\\main\\resources\\FEDEX2.docx");
	//	  Runtime.getRuntime().exec("D:\\workspace\\TestingWorkspace\\FedEX_printApplication\\src\\main\\resources\\autoit_multiple\\FileUpload_multiple.exe D:\\workspace\\TestingWorkspace\\FedEX_printApplication\\src\\main\\resources\\FEDEX2.docx");
		  _controls.waitForPageLoad("waiting for page load");
	//	  _controls.waitUntilElementToBeClickable("Add to cart button",addToCart,"Waiting for add to cart button");
	//	  _controls.waitUntilElementIsInVisible("progress bar",progressBar,70000,"Waiting till progress bar get invisible");
		 Thread.sleep(15000);
	  }
	  /* This method is use to upload 
	   * multiple file at a time
	   */
	  public void upload_singlefile( String strFileUploadScriptPath, String strBrowser, String... strFileNames)throws Exception{
		//  Thread.sleep(3000);
		  _controls.waitUntilElementToBeClickable("waiting for element to be clickable", my_device, null);
		  _controls.click("My device button",my_device, "clicking on my device");
		  Thread.sleep(3000);
          StringBuffer strbFileNames = new StringBuffer();
          for (String strFileName : strFileNames)
          {
                strbFileNames.append("\"");
                strbFileNames.append(strFileName);
                strbFileNames.append("\" ");
          }
          Runtime.getRuntime().exec(strFileUploadScriptPath + " " + strBrowser + " " + strbFileNames.toString().trim());

       //   _controls.waitUntilElementIsInVisible("progress bar",progressBar,30000,"Waiting till progress bar get invisible");
	  }
	  /* Uploading file at runtime */
	  public void fileUploadAtRuntime()throws Exception{
		  _controls.waitForPageLoad("waiting for uploading page");
		  _controls.waitUntilElementIsVisible("My device button", my_device,15000, "waiting for element to be clickable",false);
		  _controls.waitUntilElementToBeClickable("My device element", my_device,"waiting for my device element");
		  _controls.click("My device",my_device, "clicking on my device");
		  Thread.sleep(3000);
		  WebDriver driver=_controls.getDriver();
		  Capabilities cp=((RemoteWebDriver) driver).getCapabilities();
		  String bname=cp.getBrowserName();
		  Runtime.getRuntime().exec(System.getProperty("user.dir")+"\\src\\main\\resources\\Upload_runtime\\uploadAtRuntime.exe"+ " " + bname +" " + System.getProperty("user.dir")+"\\src\\main\\resources\\FedTesting.gif");
	//	  Runtime.getRuntime().exec("D:\\workspace\\TestingWorkspace\\FedEX_printApplication\\src\\main\\resources\\Upload_runtime\\uploadAtRuntime.exe"+" "+"D:\\workspace\\TestingWorkspace\\FedEX_printApplication\\src\\main\\resources\\FedTesting.gif");
		  _controls.waitForPageLoad("waiting for page load");
		  _controls.waitUntilElementIsInVisible("progress bar",progressBar,80000,"Waiting till progress bar get invisible");
	  }
	  
	  public void upload_file_separet()throws Exception{
//		  _controls.waitForPageLoad("waiting for uploading page");
//		 
//		  _controls.waitUntilElementIsVisible("waiting for element to be clickable", my_device,15000, null,false);
//		  _controls.waitUntilElementToBeClickable("My device button", my_device,"Clicking on my device option" );
//		  _controls.click("clicking on my device",my_device, null);
//		  Thread.sleep(5000);
//		  WebDriver driver=_controls.getDriver();
//		  Capabilities cp=((RemoteWebDriver) driver).getCapabilities();
//		  String bname=cp.getBrowserName();
		  // for single file upload we have to use this below code
		//  Runtime.getRuntime().exec(System.getProperty("user.dir")+"\\src\\main\\resources\\AutoIt\\new-upload.au3.exe");
//		   WebDriver driver=_controls.getDriver();
//			 Capabilities cap = ((RemoteWebDriver) driver).getCapabilities();
//			 String bname = cap.getBrowserName();
//		    System.out.println(" my browser :"+bname);
		   Runtime.getRuntime().exec(System.getProperty("user.dir")+"\\src\\main\\resources\\autoit_multiple\\FileUpload_multiple.exe");
//+ " " + System.getProperty("user.dir")+"\\src\\main\\resources\\FEDEX_TESTING.docx");
		   Thread.sleep(3000);
		//   Runtime.getRuntime().exec(System.getProperty("user.dir")+"\\src\\main\\resources\\AutoIt\\_multiple-upload.exe"+ " " + System.getProperty("user.dir")+"\\src\\main\\resources\\FEDEX2.docx");
			  
		 // Runtime.getRuntime().exec(System.getProperty("user.dir")+"\\src\\main\\resources\\AutoIt\\FileUpload.exe "+ " " + bname + " " + System.getProperty("user.dir")+"\\src\\main\\resources\\FEDEX_TESTING.docx");
//		 
//		  Runtime.getRuntime().exec("D:\\workspace\\TestingWorkspace\\FedEX_printApplication\\src\\main\\resources\\AutoIt\\FileUpload.exe D:\\workspace\\TestingWorkspace\\FedEX_printApplication\\src\\main\\resources\\FEDEX_TESTING.docx");
		  _controls.waitForPageLoad("waiting for page load");
		 // _controls.waitUntilElementIsInVisible("progress bar",progressBar,70000,"Waiting till progress bar get invisible");
		 Thread.sleep(15000);
	  }
}
