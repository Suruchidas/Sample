package com.fedex.PageObject;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Properties;

import javax.imageio.ImageIO;

import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;

import com.infogain.uap.framework.utilities.PropFileHandler;
import com.infogain.uap.framework.webcontrols.Controls;
import com.infogain.uap.logger.LogType;
import com.infogain.uap.logger.Reporting;

import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.comparison.ImageDiff;
import ru.yandex.qatools.ashot.comparison.ImageDiffer;

public class ImageComparison {
	private static final int ThreadLocal = 0;
	Controls _controls;
	 int _timeOut;
	 Browser _browser;
	 WebDriver driver;
	 Capabilities cap;
	 
	 
	 private String clip_image="xpath~//div[@class='p1  frontPageCover hard flipImageDiv page odd']//img[@data-class='frontPageCover hard ']";
	 private String Singlesheet_orientationPageImage="xpath~//img[@id='singleSheetImage']";
	 private String paper_color_image="xpath~//div[contains(@class,'flipImageDiv')][1]//div[@class='gradient ']";
	 private String clip_image_multiFeature_multisheet="xpath~//div[@class='p1  flipImageDiv frontPageCover hard page odd']/div/img[@data-class='frontPageCover hard']";
	 private String clip_image_speciality="xpath~//img[@id='front_image']";
	 PropFileHandler _propFileHandler = new PropFileHandler(new Properties());
	 public ImageComparison(ThreadLocal<Controls> _controls) {
            this._controls = _controls.get();
			if (_propFileHandler.readProperty("testData", "timeout") != null) {
				this._timeOut = Integer.parseInt(_propFileHandler.readProperty("testData", "timeout"));
			} else
				this._timeOut =0;
	  }
	 /* This method is use to capture the image */ 
   public void CaptureImage() throws Exception{
	     Thread.sleep(5000);
	     WebElement Clip_image=_controls.getDriver().findElement(By.xpath("//img[@id='front_image']"));
	     Screenshot screenshot=new AShot().takeScreenshot(_controls.getDriver(), Clip_image);
	     ImageIO.write(screenshot.getImage(),"PNG",new File("C:\\Users\\manish.rai\\Desktop\\Speciality_size\\image.png"));
   }
   
   /* Validating binding coil image here*/
   public void ValidateCoilImage() throws Exception{
	   Thread.sleep(15000);
//		 _imageComparison.Validate_speciality_size_image(System.getProperty(("user.dir") + _propFileHandler.readProperty("testData", "SPECIALITYFEATURESIZE"));
	   System.out.println("path of image"+System.getProperty("user.dir")+_propFileHandler.readProperty("testData","IMAGEPATH" ));
	   BufferedImage expectedImage=ImageIO.read(new File(System.getProperty("user.dir")+_propFileHandler.readProperty("testData","IMAGEPATH" )));
	   WebElement coil_image=_controls.getDriver().findElement(By.xpath("//div[@class='p1  frontPageCover hard flipImageDiv page odd']//img[@data-class='frontPageCover hard ']"));
	   Screenshot screenshot=new AShot().takeScreenshot(_controls.getDriver(), coil_image);
     //    ImageIO.write(screenshot.getImage(),"PNG",new File("C:\\Users\\manish.rai\\Desktop\\DocImage\\checking.png"));
         BufferedImage actualImage=screenshot.getImage();
	    ImageDiffer imgdiff=new ImageDiffer();
	    ImageDiff diff=imgdiff.makeDiff(expectedImage, actualImage);
	    System.out.println("verifying image value :"+diff.hasDiff());
	    if(diff.hasDiff()){
	    	Reporting.getLogger().log(LogType.FAIL, "Image is not same");
	    }else{
	    	Reporting.getLogger().log(LogType.PASS, "Image is same");
	    }
   }   
   public  String getBrowserName(){
		WebDriver driver=_controls.getDriver();
		Capabilities cap=((RemoteWebDriver)driver).getCapabilities();
		String browser_name=cap.getBrowserName();
		System.out.println("browser name currntly   :"+browser_name);
		return browser_name;
	}
   /* This method is use to validate the orientation of image */
   public void Validate_OrientationOfImage() throws Exception{
	//   _controls.waitForPageLoad("waiting for page to load");
	   Thread.sleep(10000); 
       BufferedImage expectedImage=ImageIO.read(new File(System.getProperty("user.dir")+_propFileHandler.readProperty("testData","ORIENTATION" )));
//	   _controls.waitUntilElementIsVisible("Waiting for image", "//img[@id='singleSheetImage']", 20000, null,false);
       BufferedImage expectedImage_firefox=ImageIO.read(new File(System.getProperty("user.dir")+_propFileHandler.readProperty("testData","ORIENTATION_FIREFOX" )));
	   WebElement orientation_image=_controls.getDriver().findElement(By.xpath("//img[@id='singleSheetImage']"));
	   Screenshot screenshot=new AShot().takeScreenshot(_controls.getDriver(), orientation_image);
       ImageIO.write(screenshot.getImage(),"PNG",new File("C:\\Users\\manish.rai\\Desktop\\Orientation_image\\image.png"));
       BufferedImage actualImage=screenshot.getImage();
	   ImageDiffer imgdiff=new ImageDiffer();
	   ImageDiff diff=null;
	   if(this.getBrowserName().equals("chrome")){
		  diff = imgdiff.makeDiff(expectedImage, actualImage);
		  
	   }else if(this.getBrowserName().equals("firefox")){
		   diff= imgdiff.makeDiff(expectedImage_firefox, actualImage);
	    }
	   System.out.println("verifying image value :"+diff.hasDiff());
	   if(diff.hasDiff()){
	    	Reporting.getLogger().log(LogType.FAIL, "Image is not same");
	    }else{
	    	Reporting.getLogger().log(LogType.PASS, "Image is same");
	    }
	}
   /* This method is use to vaidate the orientation of image 
    * in a generic way for multisheet as well as single sheet*/
   
   public void Validate_OrientationOfImage_generic(String imagepath) throws Exception{
		//   _controls.waitForPageLoad("waiting for page to load");
	     
		  Thread.sleep(15000); 
	      BufferedImage expectedImage=ImageIO.read(new File(imagepath));
//		   _controls.waitUntilElementIsVisible("Waiting for image", "//img[@id='singleSheetImage']", 20000, null,false);
		  WebElement orientation_image=_controls.getDriver().findElement(By.xpath("//img[@id='singleSheetImage']"));
		  Screenshot screenshot=new AShot().takeScreenshot(_controls.getDriver(), orientation_image);
	      ImageIO.write(screenshot.getImage(),"PNG",new File("C:\\Users\\manish.rai\\Desktop\\docimage2\\checking.png"));
	      BufferedImage actualImage=screenshot.getImage();
		  ImageDiffer imgdiff=new ImageDiffer();
		  
		  ImageDiff diff=null;
		   if(this.getBrowserName().equals("chrome")){
			  diff = imgdiff.makeDiff(expectedImage, actualImage);
			  
		   }else if(this.getBrowserName().equals("firefox")){
			   diff= imgdiff.makeDiff(expectedImage, actualImage);
		    }
		  System.out.println("verifying image value :"+diff.hasDiff());
		  if(diff.hasDiff()){
		    	Reporting.getLogger().log(LogType.FAIL, "Image is not same");
		    }else{
		    	Reporting.getLogger().log(LogType.PASS, "Image is same");
		    }
		}
   
   /* This method is use to validate the color of paper in a generic way */
   public void Validate_ColoOfPaper_generic(String imagepath) throws Exception{
		 Thread.sleep(10000); 
	     BufferedImage expectedImage=ImageIO.read(new File(imagepath));
//		   _controls.waitUntilElementIsVisible("Waiting for image", "//img[@id='singleSheetImage']", 20000, null,false);
		 WebElement orientation_image=_controls.getDriver().findElement(By.xpath("//div[contains(@class,'flipImageDiv')][1]//div[@class='gradient ']"));
		 Screenshot screenshot=new AShot().takeScreenshot(_controls.getDriver(), orientation_image);
	     ImageIO.write(screenshot.getImage(),"PNG",new File("C:\\Users\\manish.rai\\Desktop\\DocImage\\checking_firefox.png"));
	     BufferedImage actualImage=screenshot.getImage();
         ImageDiffer imgdiff=new ImageDiffer();
         ImageDiff diff=imgdiff.makeDiff(expectedImage, actualImage);
		 System.out.println("verifying image value :"+diff.hasDiff());
		 if(diff.hasDiff()){
		    	Reporting.getLogger().log(LogType.FAIL, "Image is not same");
		    }else{
		    	Reporting.getLogger().log(LogType.PASS, "Image is same");
		    }
		 }
   /* This method is use to validate the multifeatyre of imgae */
   public void Validate_multifeature_multisheet_image(String imagepath) throws Exception{
	   Thread.sleep(10000); 
	   BufferedImage expectedImage=ImageIO.read(new File(imagepath));
//	   _controls.waitUntilElementIsVisible("Waiting for image", "//img[@id='singleSheetImage']", 20000, null,false);
	   WebElement multifeatue_multisheet_image=_controls.getDriver().findElement(By.xpath("//div[@class='p1  flipImageDiv frontPageCover hard page odd']/div/img[@data-class='frontPageCover hard']"));
	   Screenshot screenshot=new AShot().takeScreenshot(_controls.getDriver(), multifeatue_multisheet_image);
	   ImageIO.write(screenshot.getImage(),"PNG",new File("C:\\Users\\manish.rai\\Desktop\\Multifeature_image\\image_firefox.png"));
	   BufferedImage actualImage=screenshot.getImage();
       ImageDiffer imgdiff=new ImageDiffer();
	   ImageDiff diff=imgdiff.makeDiff(expectedImage, actualImage);
	   System.out.println("verifying image value :"+diff.hasDiff());
	   if(diff.hasDiff()){
	        Reporting.getLogger().log(LogType.FAIL, "Image is not same");
	   }else{
		    Reporting.getLogger().log(LogType.PASS, "Image is same");
	   }
   }
   
   /* This method is use to validate  the size of an image for speciality feature */
   
   public void Validate_speciality_size_image(String imagepath) throws Exception{
	     Thread.sleep(10000); 
	     BufferedImage expectedImage=ImageIO.read(new File(imagepath));
//		   _controls.waitUntilElementIsVisible("Waiting for image", "//img[@id='singleSheetImage']", 20000, null,false);
		 WebElement multifeatue_multisheet_image=_controls.getDriver().findElement(By.xpath("//img[@id='front_image']"));
		 Screenshot screenshot=new AShot().takeScreenshot(_controls.getDriver(), multifeatue_multisheet_image);
	     ImageIO.write(screenshot.getImage(),"PNG",new File("C:\\Users\\manish.rai\\Desktop\\Speciality_size\\image_firefox.png"));
	     BufferedImage actualImage=screenshot.getImage();
		 ImageDiffer imgdiff=new ImageDiffer();
		 ImageDiff diff=imgdiff.makeDiff(expectedImage, actualImage);
		 System.out.println("verifying image value :"+diff.hasDiff());
		 if(diff.hasDiff()){
		    	Reporting.getLogger().log(LogType.FAIL, "Image is not same");
		    }else{
		    	Reporting.getLogger().log(LogType.PASS, "Image is same");
		    }
      }
}
