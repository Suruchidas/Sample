package com.fedex.PageObject;

import java.util.Properties;

import com.infogain.uap.framework.utilities.PropFileHandler;
import com.infogain.uap.framework.webcontrols.Controls;
import com.infogain.uap.logger.LogType;
import com.infogain.uap.logger.Reporting;

public class PriceValidation_fromUI {
	Controls _controls;
	int _timeOut;
	String Ui_price; 
	String Webservice_price;
	 PropFileHandler _propFileHandler = new PropFileHandler(new Properties());
	 PriceValidation_RestAssured _priceValidation;
	 private String price_configrator="xpath~//span[@id='uq_itemPrice']";
	  
	 
	  public PriceValidation_fromUI(ThreadLocal<Controls> _controls) {

			this._controls = _controls.get();
			if (_propFileHandler.readProperty("testData", "timeout") != null) {
				this._timeOut = Integer.parseInt(_propFileHandler.readProperty("testData", "timeout"));
			} else
				this._timeOut =0;
	  }
	public void validate_multisheet_price(String actual_price) throws InterruptedException{
	Thread.sleep(10000);
	Ui_price=_controls.getText("Price element",price_configrator, "Taking price of product");
	System.out.println("UI price :  "+Ui_price);
	
	if(Ui_price.equals(actual_price)){
		Reporting.getLogger().log(LogType.PASS, "Price matched successfully ");
	}else{
		Reporting.getLogger().log(LogType.FAIL, "Price is not sucessfully matched");
	}
	}
	
}
