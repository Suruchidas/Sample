package com.fedEx.BusinessObject;

import java.util.Properties;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;

import com.fedex.PageObject.AddToCart;
import com.fedex.PageObject.Browser;
import com.fedex.PageObject.Checkout_page;
import com.fedex.PageObject.ContactInformation;
import com.fedex.PageObject.DocumentInProgress_page;
import com.fedex.PageObject.HomePage;
import com.fedex.PageObject.ImageComparison;
import com.fedex.PageObject.Login_page;
import com.fedex.PageObject.Payment;
import com.fedex.PageObject.PickUpDelivery;
import com.fedex.PageObject.PriceValidation_RestAssured;
import com.fedex.PageObject.PriceValidation_fromUI;
import com.fedex.PageObject.ProceedAsGuest_page;
import com.fedex.PageObject.Registration;
import com.fedex.PageObject.SelectProjectType_Page;
import com.fedex.PageObject.UploadFile;
import com.infogain.uap.framework.uapControls.UapControls;
import com.infogain.uap.framework.utilities.PropFileHandler;

public class FedEx extends UapControls{
	 Registration _registration;
	 Login_page _loginPage;
	 HomePage _homePage;
	 UploadFile _uploadFile;
	 AddToCart _addToCart;
	 Checkout_page _checkout;
	 PickUpDelivery _pickUpDelivery;
	 ContactInformation _contactInformation;
	 ImageComparison _imageComparison;
	 Payment _paymentDetails;
	 SelectProjectType_Page _selectprojectTypePage;
	 ProceedAsGuest_page _proceedAsGuest_page;
	 DocumentInProgress_page _documentinprogresspage;
	 PriceValidation_RestAssured _priceValidation_restAssured;
	 PriceValidation_fromUI _priceValidationUI;
	 PropFileHandler _propFileHandler = new PropFileHandler(new Properties());
	 
	 @Test(enabled=true)
	 public void registration_fedex(){
		 _registration=new Registration(_controls);
		 _homePage=new HomePage(_controls);
		 _uploadFile=new UploadFile(_controls);
		 try{
		
		 _homePage.launch_url();
		 _uploadFile.close_pop_up();
		 _registration.fedEx_Registration();
		 }catch(Exception e){
			 e.printStackTrace();
		 }
	 }
	 
	 @Test(enabled=true)
	 public void Checkout_singlesheet(){
		 _registration=new Registration(_controls);
	
		 _homePage=new HomePage(_controls);
		 _uploadFile=new UploadFile(_controls);
		 _addToCart=new AddToCart(_controls);
		 _checkout=new Checkout_page(_controls);
		 _pickUpDelivery=new PickUpDelivery(_controls);
		 _contactInformation=new ContactInformation(_controls);
		 _paymentDetails=new Payment(_controls);
		 try{
	    _homePage.launch_url();
	    _uploadFile.close_pop_up();
	    _registration.fedEx_Registration();
	    if(UploadFile.pop_flag==false){
			 _uploadFile.close_pop_up();
		}
		 _homePage.Selecting_DocumentPrinting();
		 if(UploadFile.pop_flag==false){
			 _uploadFile.close_pop_up();
		}
		 _uploadFile.upload_file();
	   if(UploadFile.pop_flag==false){
			 _uploadFile.close_pop_up();
		 }
		 _addToCart.add_backPage();
		 _addToCart.AddToCart();
		 _checkout.checkout_item();
		 _pickUpDelivery.pickup();
		 _contactInformation.fill_contactInfo();
		 
		 _paymentDetails.select_paymentInfo();
		 _paymentDetails.billing_address();
		 _paymentDetails.Validate_page();
		 }catch(Exception e){
			 e.printStackTrace();
		 }
		
	 }
	 @Test(enabled=true)
	 public void Checkout_singleSheetSize(){
		 _registration=new Registration(_controls);
	
		 _homePage=new HomePage(_controls);
		 _uploadFile=new UploadFile(_controls);
		 _addToCart=new AddToCart(_controls);
		 _checkout=new Checkout_page(_controls);
		 _pickUpDelivery=new PickUpDelivery(_controls);
		 _contactInformation=new ContactInformation(_controls);
		 _paymentDetails=new Payment(_controls);
		 try{
		 _homePage.launch_url();
		 _uploadFile.close_pop_up();
	     _registration.fedEx_Registration();
	     if(UploadFile.pop_flag==false){
			 _uploadFile.close_pop_up();
		 }
	//     _uploadFile.close_pop_up();
		 _homePage.Selecting_DocumentPrinting();
		 if(UploadFile.pop_flag==false){
		 _uploadFile.close_pop_up();
		 }
		 _uploadFile.upload_file();
		 if(UploadFile.pop_flag==false){
			 _uploadFile.close_pop_up();
			 }
		 _addToCart.sizeOfSingleSheet();
		 _addToCart.select_size("8.5\" x 14\"");
		 _addToCart.AddToCart();
		 _checkout.checkout_item();
		 _pickUpDelivery.pickup();
		 _contactInformation.fill_contactInfo();
		 _paymentDetails.select_paymentInfo();
		 _paymentDetails.billing_address();
		 _paymentDetails.Validate_page();
		 }catch(Exception e){
			 e.printStackTrace();
		 }
		
	 }
	 @Test(enabled=true)
	 public void Singlesheet_orientation(){
		 _homePage=new HomePage(_controls);
		 _uploadFile=new UploadFile(_controls);
		 _addToCart=new AddToCart(_controls);
		 _imageComparison=new ImageComparison(_controls);
		 try{
			 _homePage.launch_url();
			
			 if(UploadFile.pop_flag==false){
				 _uploadFile.close_add();
			 }
			 _uploadFile.close_pop_up();
			 _homePage.Selecting_DocumentPrinting();
			 if(UploadFile.pop_flag==false){
				 _uploadFile.close_pop_up();
			 }
			 _uploadFile.upload_file();
			
			 if(UploadFile.pop_flag==false){
				 _uploadFile.close_pop_up();
			 }
			 _addToCart.Select_Orientation();
			 _imageComparison.Validate_OrientationOfImage();
		 }catch(Exception e){
			 e.printStackTrace();
		 }
	 }
	 // Not in use now
	 @Test(enabled=true)
	 public void Comparison_Image(){
		 _registration=new Registration(_controls);
		 _addToCart=new AddToCart(_controls);
		 _homePage=new HomePage(_controls);
		 _uploadFile=new UploadFile(_controls);
		 _imageComparison=new ImageComparison(_controls);
		 try{
			 _registration.fedEx_Registration();
			 if(UploadFile.pop_flag==false){
				 _uploadFile.close_add();
			 }
			 _uploadFile.close_pop_up();
			 _homePage.Selecting_DocumentPrinting();
			
			 if(UploadFile.pop_flag==false){
				 _uploadFile.close_pop_up();
			}
			 _uploadFile.upload_file();
			
			 if(UploadFile.pop_flag==false){
				 _uploadFile.close_pop_up();
			}
			
			 _addToCart.add_forMultiplesheet();
			
			 _uploadFile.upload_multipleFile();
			
			 _addToCart.Select_bindingCover();
	//		 _imageComparison.CaptureImage();
			 _imageComparison.ValidateCoilImage();
		 }catch(Exception e){
			 
		 }
	 }
	 @Test(enabled=true)
	 public void checkout_multiplesheet(){
		 _registration=new Registration(_controls);
			
		 _homePage=new HomePage(_controls);
		 _uploadFile=new UploadFile(_controls);
		 _addToCart=new AddToCart(_controls);
		 _checkout=new Checkout_page(_controls);
		 _pickUpDelivery=new PickUpDelivery(_controls);
		 _contactInformation=new ContactInformation(_controls);
		 _paymentDetails=new Payment(_controls);
		 try{
			 _homePage.launch_url();
			 _uploadFile.close_pop_up();
			 _registration.fedEx_Registration();
			 _uploadFile.close_pop_up();
			 _homePage.Selecting_DocumentPrinting();
			 if(UploadFile.pop_flag==false){
				 _uploadFile.close_pop_up();
			}
			 _uploadFile.upload_file();
		   if(UploadFile.pop_flag==false){
				 _uploadFile.close_pop_up();
			 }
             _addToCart.add_forMultiplesheet();
			 _uploadFile.upload_multipleFile();
		     _addToCart.newAddtoCart();
			 _checkout.checkout_item();
			 _pickUpDelivery.pickup();
			 _contactInformation.fill_contactInfo();
			 _paymentDetails.select_paymentInfo();
			 _paymentDetails.billing_address();
			 _paymentDetails.Validate_page();
		 }catch(Exception e){
			 e.printStackTrace();
		 }
	 }
	 
	 @Test(enabled=true)
	 public void multiplesheet_priceValidation(){
		 _registration=new Registration(_controls);
		 _homePage=new HomePage(_controls);
		 _uploadFile=new UploadFile(_controls);
		 _addToCart=new AddToCart(_controls);
		 _checkout=new Checkout_page(_controls);
		 _pickUpDelivery=new PickUpDelivery(_controls);
		 _contactInformation=new ContactInformation(_controls);
		 _paymentDetails=new Payment(_controls);
		 _priceValidation_restAssured=new PriceValidation_RestAssured();
		 _priceValidationUI=new PriceValidation_fromUI(_controls);
		 try{
	
			 _homePage.launch_url();
			 _uploadFile.close_pop_up();
			 _homePage.Selecting_DocumentPrinting();
			 if(UploadFile.pop_flag==false){
				 _uploadFile.close_pop_up();
			}
			 _uploadFile.upload_file();
			
		String price=	 _priceValidation_restAssured.PriceValidation_multisheet();
		System.out.println("master call"+price);
			 _priceValidationUI.validate_multisheet_price(price);
		 }catch(Exception e){
			 e.printStackTrace();
		 }
	 }
	 
	/*Price validation after uploading two files*/
	 @Test(enabled=true)
	 public void multiplesheet_priceValidation_uploading2files(){
		 _registration=new Registration(_controls);
			
		 _homePage=new HomePage(_controls);
		 _uploadFile=new UploadFile(_controls);
		 _addToCart=new AddToCart(_controls);
		 _checkout=new Checkout_page(_controls);
		 _pickUpDelivery=new PickUpDelivery(_controls);
		 _contactInformation=new ContactInformation(_controls);
		 _paymentDetails=new Payment(_controls);
		 _priceValidation_restAssured=new PriceValidation_RestAssured();
		 _priceValidationUI=new PriceValidation_fromUI(_controls);
		 try{
		//	 _registration.fedEx_Registration();
			 _homePage.launch_url();
			 _uploadFile.close_pop_up();
			 _homePage.Selecting_DocumentPrinting();
			 if(UploadFile.pop_flag==false){
				 _uploadFile.close_pop_up();
			}
			 _uploadFile.upload_file();
			 _addToCart.add_forMultiplesheet();
			 _uploadFile.upload_multipleFile();
        String price=_priceValidation_restAssured.price_validation_by_uploading_multipleFile();
		System.out.println("master call"+price);
		 _priceValidationUI.validate_multisheet_price(price);
	  }catch(Exception e){
			 e.printStackTrace();
		 }
	 }
	 @Test(enabled=true)
	 public void checkout_multiplesheetAtOneTime(){
		 _registration=new Registration(_controls);
		 _homePage=new HomePage(_controls);
		 _uploadFile=new UploadFile(_controls);
		 _addToCart=new AddToCart(_controls);
		 _documentinprogresspage=new DocumentInProgress_page(_controls);
		 _checkout=new Checkout_page(_controls);
		 _pickUpDelivery=new PickUpDelivery(_controls);
		 _contactInformation=new ContactInformation(_controls);
		 _paymentDetails=new Payment(_controls);
		 try{
			 WebDriver driver=_controls.get().getDriver();
			 Capabilities cap = ((RemoteWebDriver) driver).getCapabilities();
			 String bname = cap.getBrowserName();
		     System.out.println(" my browser :"+bname);
		     _homePage.launch_url();
		     _uploadFile.close_pop_up();
			 _registration.fedEx_Registration();
			 _uploadFile.close_pop_up();
			 _homePage.Selecting_DocumentPrinting_forMultiple();
			 if(UploadFile.pop_flag==false){
				 _uploadFile.close_pop_up();
			}
			_uploadFile.upload_singlefile(System.getProperty("user.dir")+"\\src\\main\\resources\\FileUpload\\Fileupload_multiple1.exe",bname,System.getProperty("user.dir")+"\\src\\main\\resources\\FEDEX2.docx",System.getProperty("user.dir")+"\\src\\main\\resources\\FEDEX_TESTING.docx" );
			if(UploadFile.pop_flag==false){
				 _uploadFile.close_pop_up();
			}
			 _documentinprogresspage.select_setprintoption();
			 _addToCart.AddToCart();
			 _checkout.checkout_item();
			 _pickUpDelivery.pickup();
			 _contactInformation.fill_contactInfo();
		     _paymentDetails.select_paymentInfo();
			 _paymentDetails.billing_address();
			 _paymentDetails.Validate_page();
		 }catch(Exception e){
			 e.printStackTrace();
		 }
	 }
	 
/*	 @Test(enabled=false)
	 public void checkout_posterAndBanner(){
		 _homePage=new HomePage(_controls);
		 _uploadFile=new UploadFile(_controls);
		 _addToCart=new AddToCart(_controls);
		 _checkout=new Checkout_page(_controls);
		 _pickUpDelivery=new PickUpDelivery(_controls);
		 _contactInformation=new ContactInformation(_controls);
		 _selectprojectTypePage=new SelectProjectType_Page(_controls);
		 _proceedAsGuest_page=new ProceedAsGuest_page(_controls);
		 try{
			 _homePage.launch_url();
			 _homePage.Selecting_Poster_Signs();
			 _selectprojectTypePage.select_useYuorFileOtion();
			 _uploadFile.upload_file();
			 _addToCart.AddToCart();
			 _checkout.checkout_item();
			 _proceedAsGuest_page.select_asAGuestOption();
			 _pickUpDelivery.pickup();
			 _contactInformation.fill_contactInfo();
			 
		 }catch(Exception e){
			 e.printStackTrace();
		 }
	 }*/
	 @Test(enabled=true)
	 public void addSpecial_instruction_insinglesheet(){
		 _homePage=new HomePage(_controls);
		 _selectprojectTypePage=new SelectProjectType_Page(_controls);
		 _uploadFile=new UploadFile(_controls);
		 _addToCart=new AddToCart(_controls);
		 try{
			 _homePage.launch_url();
			 _uploadFile.close_pop_up();
			 _homePage.Selecting_DocumentPrinting();
			 if(UploadFile.pop_flag==false){
				 _uploadFile.close_pop_up();
			}
		
			 _uploadFile.fileUploadAtRuntime();
			
			 if(UploadFile.pop_flag==false){
				 _uploadFile.close_pop_up();
			}
			
			 _addToCart.addAdditionalInstruction();
			 
		 }catch(Exception e){
			 e.printStackTrace();
		 }
	 }
	 
	 @Test(enabled=true)
	 public void Singlesheet_mutiplefeatue_papercolor(){
		 _registration=new Registration(_controls);
		 _addToCart=new AddToCart(_controls);
		 _homePage=new HomePage(_controls);
		 _uploadFile=new UploadFile(_controls);
		 _imageComparison=new ImageComparison(_controls);
		 try{
			 _homePage.launch_url();
			 _uploadFile.close_pop_up();
			 _homePage.Selecting_DocumentPrinting();
			 if(UploadFile.pop_flag==false){
				 _uploadFile.close_pop_up();
			}
			 _uploadFile.upload_file();
			
			 if(UploadFile.pop_flag==false){
				 _uploadFile.close_pop_up();
			}
			_addToCart.Select_paperColor(); 
		  if(_imageComparison.getBrowserName().contains("chrome")){
			_imageComparison.Validate_OrientationOfImage_generic(System.getProperty("user.dir")+_propFileHandler.readProperty("testData","COLORPAPER2" ));
		  } else if(_imageComparison.getBrowserName().contains("firefox")){
			  _imageComparison.Validate_OrientationOfImage_generic(System.getProperty("user.dir")+_propFileHandler.readProperty("testData","COLORPAPER_firefox" )); 
		  }
		  }catch(Exception e){
			 e.printStackTrace();
		 }
	 }	
	 
	 @Test(enabled=true)
	 public void multisheet_mutiplefeatue_papercolor(){
		 _registration=new Registration(_controls);
		 _addToCart=new AddToCart(_controls);
		 _homePage=new HomePage(_controls);
		 _uploadFile=new UploadFile(_controls);
		 _imageComparison=new ImageComparison(_controls);
		 try{
			 _homePage.launch_url();
			 _uploadFile.close_pop_up();
			 _homePage.Selecting_DocumentPrinting_forMultiple();
			 if(UploadFile.pop_flag==false){
				 _uploadFile.close_pop_up();
			}
			 _uploadFile.upload_file();
			
			 if(UploadFile.pop_flag==false){
				 _uploadFile.close_pop_up();
			}
			_addToCart.Select_paperColor();
			if(_imageComparison.getBrowserName().contains("chrome")){
	           _imageComparison.Validate_ColoOfPaper_generic(System.getProperty("user.dir")+_propFileHandler.readProperty("testData","COLORPAPER" ));
			} else if(_imageComparison.getBrowserName().equals("firefox")){
				_imageComparison.Validate_ColoOfPaper_generic(System.getProperty("user.dir")+_propFileHandler.readProperty("testData","COLORPAPER_FIREFOX" ));
			}
			}catch(Exception e){
			 e.printStackTrace();
		 }
	 }	
	 @Test(enabled=true)
	 public void multisheet_multiplefeature(){
		 _addToCart=new AddToCart(_controls);
		 _homePage=new HomePage(_controls);
		 _uploadFile=new UploadFile(_controls);
		 _imageComparison=new ImageComparison(_controls);
		 try{
			 _homePage.launch_url();
			 _uploadFile.close_pop_up();
			 _homePage.Selecting_DocumentPrinting_forMultiple();
			 if(UploadFile.pop_flag==false){
				 _uploadFile.close_pop_up();
			}
			 _uploadFile.upload_file();
			
			 if(UploadFile.pop_flag==false){
				 _uploadFile.close_pop_up();
			}
	        _addToCart.add_forMultiplesheet();
	        _uploadFile.upload_multipleFile();
	        _addToCart.Select_bindingCover();
			_addToCart.Select_Orientation();
			_addToCart.Select_paperColor_ofMultisheet(); 
			if(_imageComparison.getBrowserName().equalsIgnoreCase("chrome")){
	        _imageComparison.Validate_multifeature_multisheet_image(System.getProperty("user.dir")+_propFileHandler.readProperty("testData","IMAGE"));
			} else if(_imageComparison.getBrowserName().equalsIgnoreCase("firefox")){
				 _imageComparison.Validate_multifeature_multisheet_image(System.getProperty("user.dir")+_propFileHandler.readProperty("testData","IMAGE_FIREFOX"));	
			}
			}catch(Exception e){
			 e.printStackTrace();
		 }
	 }
	 
	 @Test(enabled=true)
	 public void multisheet_multiplefeature_checkout(){
		 _addToCart=new AddToCart(_controls);
		 _homePage=new HomePage(_controls);
		 _uploadFile=new UploadFile(_controls);
		 _checkout=new Checkout_page(_controls);
		 _pickUpDelivery=new PickUpDelivery(_controls);
		 _contactInformation=new ContactInformation(_controls);
		 _imageComparison=new ImageComparison(_controls);
		 _proceedAsGuest_page=new ProceedAsGuest_page(_controls);
		 _paymentDetails=new Payment(_controls);
		 try{
			 _homePage.launch_url();
			 _uploadFile.close_pop_up();
			 _homePage.Selecting_DocumentPrinting_forMultiple();
			 if(UploadFile.pop_flag==false){
				 _uploadFile.close_pop_up();
			}
			 _uploadFile.upload_file();
			 if(UploadFile.pop_flag==false){
				 _uploadFile.close_pop_up();
			}
			_addToCart.add_forMultiplesheet();
		    _uploadFile.upload_multipleFile();
			 _addToCart.Select_bindingCover();
			 _addToCart.Select_Orientation();
			 _addToCart.Select_paperColor_ofMultisheet(); 
			 _addToCart.AddToCart();
			 _checkout.checkout_item();
			_proceedAsGuest_page.select_asAGuestOption();
			 _pickUpDelivery.pickup();
			 _contactInformation.fill_contactInfo();
			 _paymentDetails.select_paymentInfo();
			 _paymentDetails.billing_address();
			 _paymentDetails.Validate_page();
		 }catch(Exception e){
			 e.printStackTrace();
		 }
	 }
	 @Test
	 public void singlesheet_multifeature(){
		 _addToCart=new AddToCart(_controls);
		 _homePage=new HomePage(_controls);
		 _uploadFile=new UploadFile(_controls);
		 _imageComparison=new ImageComparison(_controls);
		 try{
			 _homePage.launch_url();
			 _uploadFile.close_pop_up();
			 _homePage.Selecting_DocumentPrinting();
			 if(UploadFile.pop_flag==false){
				 _uploadFile.close_pop_up();
			}
			 _uploadFile.upload_file();
			 if(UploadFile.pop_flag==false){
				 _uploadFile.close_pop_up();
			}
			 _addToCart.Select_Orientation();
			 _addToCart.Select_paperColor(); 
	 //		 _imageComparison.CaptureImage();
			 if(_imageComparison.getBrowserName().equalsIgnoreCase("chrome")){
	 	    _imageComparison.Validate_OrientationOfImage_generic(System.getProperty("user.dir")+_propFileHandler.readProperty("testData","SINGLESHEETMULTIIMAGE"));
		  }else if(_imageComparison.getBrowserName().equalsIgnoreCase("firefox")){
			  _imageComparison.Validate_OrientationOfImage_generic(System.getProperty("user.dir")+_propFileHandler.readProperty("testData","SINGLESHEETMULTIIMAGE_FIREFOX"));
		  }
			 }catch(Exception e){
			 e.printStackTrace();
		 }
	 }
	 @Test
	 public void Singlesheet_multiplefeature_checkout(){
		 _addToCart=new AddToCart(_controls);
		 _checkout=new Checkout_page(_controls);
		 _homePage=new HomePage(_controls);
		 _uploadFile=new UploadFile(_controls);
		 _imageComparison=new ImageComparison(_controls);
		 _proceedAsGuest_page=new ProceedAsGuest_page(_controls);
		 _pickUpDelivery=new PickUpDelivery(_controls);
		 _contactInformation=new ContactInformation(_controls);
		 _paymentDetails=new Payment(_controls);
		 try{
			 _homePage.launch_url();
			 _uploadFile.close_pop_up();
			 _homePage.Selecting_DocumentPrinting();
			 if(UploadFile.pop_flag==false){
				 _uploadFile.close_pop_up();
			}
			 _uploadFile.upload_file();
			
			 if(UploadFile.pop_flag==false){
				 _uploadFile.close_pop_up();
			}
			
			 _addToCart.Select_Orientation();
			 _addToCart.Select_paperColor(); 
			 _addToCart.AddToCart();
			 _checkout.checkout_item();
			 _proceedAsGuest_page.select_asAGuestOption();
			 _pickUpDelivery.pickup();
			 _contactInformation.fill_contactInfo();
			 _paymentDetails.select_paymentInfo();
			 _paymentDetails.billing_address();
			 _paymentDetails.Validate_page();
	 //		 _imageComparison.CaptureImage();
	 //	    _imageComparison.Validate_OrientationOfImage_generic(_propFileHandler.readProperty("testData","SINGLESHEETMULTIIMAGE"));
		 }catch(Exception e){
			 e.printStackTrace();
		 }
	 }
	 @Test
	 public void checkout_multiplsheet_multifeature_multiplefile(){
		 _homePage=new HomePage(_controls);
		 _uploadFile=new UploadFile(_controls);
		 _documentinprogresspage=new DocumentInProgress_page(_controls);
		 _addToCart=new AddToCart(_controls);
		 _checkout=new Checkout_page(_controls);
		 _proceedAsGuest_page=new ProceedAsGuest_page(_controls);
		 _pickUpDelivery=new PickUpDelivery(_controls);
		 _contactInformation=new ContactInformation(_controls);
		 _paymentDetails=new Payment(_controls);
		 try
		 {   
		 WebDriver driver=_controls.get().getDriver();
		 Capabilities cap = ((RemoteWebDriver) driver).getCapabilities();
		 String bname = cap.getBrowserName();
	     System.out.println(" my browser :"+bname);
			 _homePage.launch_url();
			 _uploadFile.close_pop_up();
			 _homePage.Selecting_DocumentPrinting_forMultiple();
			 if(UploadFile.pop_flag==false){
				 _uploadFile.close_pop_up();
			}
			 _uploadFile.upload_singlefile(System.getProperty("user.dir")+"\\src\\main\\resources\\FileUpload\\Fileupload_multiple1.exe",bname,System.getProperty("user.dir")+"\\src\\main\\resources\\FEDEX2.docx",System.getProperty("user.dir")+"\\src\\main\\resources\\FEDEX_TESTING.docx" );
			
			 if(UploadFile.pop_flag==false){
				 _uploadFile.close_pop_up();
			}
			
			 _documentinprogresspage.select_setprintoption();
			 _addToCart.AddToCart();
			 _checkout.checkout_item();
			 _proceedAsGuest_page.select_asAGuestOption();
			 _pickUpDelivery.pickup();
			 _contactInformation.fill_contactInfo();
			 _paymentDetails.select_paymentInfo();
			 _paymentDetails.billing_address();
			 _paymentDetails.Validate_page();
		 }catch(Exception e){
			 e.printStackTrace();
		 }
	 }
	 @Test(enabled=true)
	 public void specialityFeature_size(){
		 _homePage=new HomePage(_controls);
		 _uploadFile=new UploadFile(_controls);
		 _addToCart=new AddToCart(_controls);
		 _checkout=new Checkout_page(_controls);
		 _pickUpDelivery=new PickUpDelivery(_controls);
		 _contactInformation=new ContactInformation(_controls);
		 _selectprojectTypePage=new SelectProjectType_Page(_controls);
		 _proceedAsGuest_page=new ProceedAsGuest_page(_controls);
		 _imageComparison=new ImageComparison(_controls);
		 try{
			 _homePage.launch_url();
			 _uploadFile.close_pop_up();
			 _homePage.Selecting_specialityFeature();
			 if(UploadFile.pop_flag==false){
				 _uploadFile.close_pop_up();
			}
			 _uploadFile.upload_file();
			
			 if(UploadFile.pop_flag==false){
				 _uploadFile.close_pop_up();
			}
			 _addToCart.sizeOfSingleSheet();
			 _addToCart.select_size("12\" x 24\"");
		     if(_imageComparison.getBrowserName().equals("chrome")){
			 _imageComparison.Validate_speciality_size_image(System.getProperty("user.dir")+_propFileHandler.readProperty("testData", "SPECIALITYFEATURESIZE"));
		     } else if(_imageComparison.getBrowserName().equalsIgnoreCase("firefox")){
		    	 _imageComparison.Validate_speciality_size_image(System.getProperty("user.dir")+_propFileHandler.readProperty("testData", "SPECIALITYFEATURESIZE_FIREFOX"));	 
		     }
		 
		     }catch(Exception e){
			 e.printStackTrace();
		 }
	 }
	 @Test
	 public void checkout_specialityFeature(){
		 _homePage=new HomePage(_controls);
		 _uploadFile=new UploadFile(_controls);
		 _addToCart=new AddToCart(_controls);
		 _checkout=new Checkout_page(_controls);
		 _pickUpDelivery=new PickUpDelivery(_controls);
		 _contactInformation=new ContactInformation(_controls);
		 _selectprojectTypePage=new SelectProjectType_Page(_controls);
		 _proceedAsGuest_page=new ProceedAsGuest_page(_controls);
		 _paymentDetails=new Payment(_controls);
		 try{
			 _homePage.launch_url();
			 _uploadFile.close_pop_up();
			 _homePage.Selecting_specialityFeature();
			 if(UploadFile.pop_flag==false){
				 _uploadFile.close_pop_up();
			}
			 _uploadFile.upload_file();
			 if(UploadFile.pop_flag==false){
				 _uploadFile.close_pop_up();
			}
			 _addToCart.AddToCart();
			 _checkout.scroll_page();
			 _checkout.checkout_item();
			 _proceedAsGuest_page.select_asAGuestOption();
			 _pickUpDelivery.pickup();
			 _contactInformation.fill_contactInfo();
			/* _paymentDetails.select_paymentInfo();
			 _paymentDetails.billing_address();
			 _paymentDetails.Validate_page();*/
		 }catch(Exception e){
			 e.printStackTrace();
		 }
	 }
	 
	 @Test
	 public void marketing_materials(){
		 _homePage=new HomePage(_controls);
		 _uploadFile=new UploadFile(_controls);
		 _addToCart=new AddToCart(_controls);
		 _checkout=new Checkout_page(_controls);
		 _pickUpDelivery=new PickUpDelivery(_controls);
		 _contactInformation=new ContactInformation(_controls);
		 _selectprojectTypePage=new SelectProjectType_Page(_controls);
		 _proceedAsGuest_page=new ProceedAsGuest_page(_controls);
		 _imageComparison=new ImageComparison(_controls);
		 try{
			 _homePage.launch_url();
			 _uploadFile.close_pop_up();
			 _homePage.clickOn_marketing_materl();
//			 if(UploadFile.pop_flag==false){
//				 _uploadFile.close_pop_up();
//			}
			 _uploadFile.upload_multipleFile();
			
//			 if(UploadFile.pop_flag==false){
//				 _uploadFile.close_pop_up();
//			}
			 Thread.sleep(5000);
			 _addToCart.newAddtoCart();
			 _checkout.checkout_item();
//			 _addToCart.select_size("12\" x 24\"");
//		     if(_imageComparison.getBrowserName().equals("chrome")){
//			 _imageComparison.Validate_speciality_size_image(System.getProperty("user.dir")+_propFileHandler.readProperty("testData", "SPECIALITYFEATURESIZE"));
//		     } else if(_imageComparison.getBrowserName().equalsIgnoreCase("firefox")){
//		    	 _imageComparison.Validate_speciality_size_image(System.getProperty("user.dir")+_propFileHandler.readProperty("testData", "SPECIALITYFEATURESIZE_FIREFOX"));	 
//		     }
		 
		     }catch(Exception e){
			 e.printStackTrace();
		 }
	 
	 }

}

