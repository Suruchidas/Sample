package com.fedex.PageObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.Reader;
import java.util.ArrayList;

import org.json.JSONObject;

import com.google.gson.JsonObject;
import com.infogain.uap.framework.restwebServiceControls.RestControlsLibrary;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;

public class PriceValidation_RestAssured {
	
	private RestControlsLibrary _restControls;
	String accessToken;
	String tokenType;
	String document_id;
	String document_id1_first_file;
	String docuement_id2_second_file;
	String document_name;
	String docuemnt_name1_first_file;
	String document_name2_second_file;
	String printreadyPayload;
	String printreadypayload_first_file;
	String printreadypayload_second_file;
	String printreadypayload_first_file_orginal;
	String printreadypayload_second_file_orginal;
    String printreadyPayload1=null;
    String document_payload_file;
    String document_payload_second_file;
    String document_payload_second_file_final;
    String document_payload_file1;
    String printready_parentId;
    String printready_originalDocumentName;
    String printready_documentId;
    String printready_parentId_first_file;
    String printready_parentId_second_file;
    String printready_originalDocumentName_first_file;
    String printready_documentId_first_file;
    String printready_originalDocumentName_second_file;
    String printready_documentId_second_file;
	String orderratePayload_file=null;
	String orderRate_localReadString=null;
	String orderRate_payload1;
	String orderRate_payload1_final;
	ArrayList<String> total_price;
	ArrayList<String> total_price_multiple_file;
	String total_price_multiple_final;
	String total_price_final;
    
	public PriceValidation_RestAssured() {
		
		this._restControls = new RestControlsLibrary();
		
	 }
	public String PriceValidation_multisheet(){
	   RestAssured.proxy("172.18.65.22", 80); 
		RequestSpecification httpRequest=RestAssured.given();
		Response response=httpRequest.param("client_id", "l7xx794f94502b7e426d9cd9756b08e70734")
				                     .param("client_secret", "e90344b0345f4ecc9937ec5e7f1652e3")
				                     .param("grant_type", "client_credentials")
				                     .param("scope", "oob")
				                     .when()
				                     .post("https://api.fedex.com/auth/oauth/v2/token")
				                     .thenReturn();
		ResponseBody responseBody=response.getBody();
		String authorization_body=responseBody.asString();
		JsonPath jsonPath_token=response.jsonPath();
		accessToken=jsonPath_token.get("access_token");
		tokenType=jsonPath_token.get("token_type");
		String authrization_token=tokenType+" "+accessToken;
		System.out.println(authrization_token);
		
	// Document webservice************************************************
		
        File document_file=new File(System.getProperty("user.dir")+"\\src\\main\\resources\\Document_payload.txt");
        try{
        BufferedReader reader=new BufferedReader(new FileReader(document_file));
        while((document_payload_file=reader.readLine())!=null){
        	document_payload_file1=document_payload_file;
        }
        }catch(Exception e){
        	e.printStackTrace();
        }
		Response response_document=httpRequest.header("Authorization", authrization_token)
				                              .header("Content-Type","multipart/form-data; boundary=----WebKitFormBoundaryMjW5mHMrli1nVutn")
				                              .multiPart(new File(System.getProperty("user.dir")+"\\src\\main\\resources\\FEDEX_TESTING.docx"))
				                               .body(document_payload_file1)
				                              .when()
				                              .post("https://dunc.fedex.com/document/fedexoffice/v1/documents")
				                              .thenReturn();
                   
		ResponseBody responseBody1=response_document.getBody();
		String document_body=responseBody1.asString();  
		JsonPath document_jsonpath=response_document.jsonPath();
		document_id=document_jsonpath.get("output.document.documentId");
		document_name=document_jsonpath.get("output.document.documentName");
        System.out.println("document id :"+document_id);
        System.out.println("document id :"+document_name);
        
        /*Print ready web service*/
        
        File printready_payload=new File(System.getProperty("user.dir")+"\\src\\main\\resources\\PrintReady_payload.txt");
        try{
        BufferedReader br=new BufferedReader(new FileReader(printready_payload));
        while((printreadyPayload=br.readLine())!=null){
        
        	printreadyPayload1=printreadyPayload;
        }
        }catch(Exception e){
        	e.printStackTrace();
        }
        Response response_printReady=RestAssured.given().header("Authorization", authrization_token)
        		                                        .header("Content-Type","application/json")
        		                                        .body(printreadyPayload1)
        		                                        .when()
        		                                        .post("https://api.fedex.com/document/fedexoffice/v1/documents/"+document_id+"/printready")
        		                                        .andReturn();
        		                                
        ResponseBody printready_responseBody=response_printReady.getBody();
        String printReady_body=printready_responseBody.asString();
        System.out.println(printReady_body); 
        JsonPath printpayload_jsonpath=printready_responseBody.jsonPath();
        printready_parentId=printpayload_jsonpath.get("output.document.parentDocumentId");
        printready_originalDocumentName=printpayload_jsonpath.get("output.document.originalDocumentName");
        printready_documentId=printpayload_jsonpath.get("output.document.documentId");
        System.out.println("document id:"+printready_documentId+"                         "+printready_originalDocumentName);
       /*===========================================Now orderrate webservice=============================================================*/
        try{
        File orderrate_file=new File(System.getProperty("user.dir")+"\\src\\main\\resources\\new_orderRate_payload.txt");
        BufferedReader br_orderRate=new BufferedReader(new FileReader(orderrate_file));
      
        while (( orderRate_localReadString=br_orderRate.readLine())!=null){
        	orderratePayload_file=orderRate_localReadString;
        	System.out.println(orderratePayload_file);
        }
   
       }catch(Exception e){
        	e.printStackTrace();
        }
     String x=orderratePayload_file.replace("PARENT_CONTENT_REFRENCE", printready_parentId);
    
     String replace_content_reference=x.replace("CONTENT_REFERENCE", printready_documentId);
     String final_replacement_document=replace_content_reference.replace("FILE_NAME", printready_originalDocumentName);

       Response orderrate_response=RestAssured.given().header("Authorization", authrization_token)
                                                      .header("Content-Type","application/json")
                                                      .body(final_replacement_document)
                                                      .when()
                                                      .post("https://api.fedex.com/rate/fedexoffice/v1/orderrates")
                                                      .thenReturn();
       
       ResponseBody orderRate_responseBody=orderrate_response.getBody();
       String orderrate_body=orderRate_responseBody.asString();
       System.out.println(orderrate_body);
       // ************************Json path for getting total price value************************************//
       JsonPath orderRate_jsonpath=orderRate_responseBody.jsonPath();
       System.out.println(""+orderRate_jsonpath.get("output.orderRate.documentPrices.totalPrice").getClass());
       total_price=orderRate_jsonpath.get("output.orderRate.documentPrices.totalPrice");
       System.out.println("total price"+total_price);
      
       for(String s:total_price){
    	   total_price_final=s;
       }
       System.out.println("total price"+total_price_final);
       return total_price_final;
		
	}
	
	/*******************************Creating method for uploading two files**************************************************************/
	
	public String price_validation_by_uploading_multipleFile(){
		
	// Fetching authorization token
		  RestAssured.proxy("172.18.65.22", 80);
		RequestSpecification httpRequest=RestAssured.given();
		Response response=httpRequest.param("client_id", "l7xx794f94502b7e426d9cd9756b08e70734")
				                     .param("client_secret", "e90344b0345f4ecc9937ec5e7f1652e3")
				                     .param("grant_type", "client_credentials")
				                     .param("scope", "oob")
				                     .when()
				                     .post("https://api.fedex.com/auth/oauth/v2/token")
				                     .thenReturn();
		ResponseBody responseBody=response.getBody();
		String authorization_body=responseBody.asString();
		JsonPath jsonPath_token=response.jsonPath();
		accessToken=jsonPath_token.get("access_token");
		tokenType=jsonPath_token.get("token_type");
		String authrization_token=tokenType+" "+accessToken;
		
	// Now will call the Document webservice
		 File document_file=new File(System.getProperty("user.dir")+"\\src\\main\\resources\\Document_payload.txt");
	        try{
	        BufferedReader reader=new BufferedReader(new FileReader(document_file));
	        while((document_payload_file=reader.readLine())!=null){
	        	document_payload_file1=document_payload_file;
	        }
	        }catch(Exception e){
	        	e.printStackTrace();
	        }
			Response response_document=httpRequest.header("Authorization", authrization_token)
					                              .header("Content-Type","multipart/form-data; boundary=----WebKitFormBoundaryMjW5mHMrli1nVutn")
					                              .multiPart(new File(System.getProperty("user.dir")+"\\src\\main\\resources\\FEDEX_TESTING.docx"))
					                               .body(document_payload_file1)
					                              .when()
					                              .post("https://dunc.fedex.com/document/fedexoffice/v1/documents")
					                              .thenReturn();
	                   
			ResponseBody responseBody1=response_document.getBody();
			String document_body=responseBody1.asString();  
			JsonPath document_jsonpath=response_document.jsonPath();
			document_id1_first_file=document_jsonpath.get("output.document.documentId");
			docuemnt_name1_first_file=document_jsonpath.get("output.document.documentName");
	        System.out.println("document id for first file :"+document_id1_first_file);
	        System.out.println("document name for first file :"+docuemnt_name1_first_file);
	        
	      /*Now uploading second file fedEX2*/  
	        File document_file2=new File(System.getProperty("user.dir")+"\\src\\main\\resources\\document_payload2.txt");
	        try{
	        BufferedReader reader2=new BufferedReader(new FileReader(document_file2));
	        while((document_payload_second_file=reader2.readLine())!=null){
	        	document_payload_second_file_final=document_payload_second_file;
	        }
	        }catch(Exception e){
	        	e.printStackTrace();
	        }
			Response response_document_for_2ndFile=RestAssured.given().header("Authorization", authrization_token)
					                              .header("Content-Type","multipart/form-data; boundary=----WebKitFormBoundaryMjW5mHMrli1nVutn")
					                              .multiPart(new File(System.getProperty("user.dir")+"\\src\\main\\resources\\FEDEX2.docx"))
					                               .body(document_payload_second_file_final)
					                              .when()
					                              .post("https://dunc.fedex.com/document/fedexoffice/v1/documents")
					                              .thenReturn();
	                   
			ResponseBody responseBody_for2_File=response_document_for_2ndFile.getBody();
			String document_body_for2_file=responseBody_for2_File.asString();  
			JsonPath document_jsonpath_for2_file=responseBody_for2_File.jsonPath();
			docuement_id2_second_file=document_jsonpath_for2_file.get("output.document.documentId");
			document_name2_second_file=document_jsonpath_for2_file.get("output.document.documentName");
	        System.out.println("document id for first file :"+docuement_id2_second_file);
	        System.out.println("document name for first file :"+document_name2_second_file);
	        
	   /*Now print ready for first web service*/     
	        File printready_payload1=new File(System.getProperty("user.dir")+"\\src\\main\\resources\\PrintReady_payload.txt");
	        try{
	        BufferedReader br1=new BufferedReader(new FileReader(printready_payload1));
	        while((printreadypayload_first_file=br1.readLine())!=null){
	        
	        	printreadypayload_first_file_orginal=printreadypayload_first_file;
	        }
	        }catch(Exception e){
	        	e.printStackTrace();
	        }
	        Response response_printReady=RestAssured.given().header("Authorization", authrization_token)
	        		                                        .header("Content-Type","application/json")
	        		                                        .body(printreadypayload_first_file_orginal)
	        		                                        .when()
	        		                                        .post("https://api.fedex.com/document/fedexoffice/v1/documents/"+document_id1_first_file+"/printready")
	        		                                        .andReturn();
	        		                                
	        ResponseBody printready_responseBody_first_file=response_printReady.getBody();
	        String printReady_body_first_file=printready_responseBody_first_file.asString();
	 //       System.out.println(printReady_body); 
	        JsonPath printpayload_jsonpath_first_file=printready_responseBody_first_file.jsonPath();
	        printready_parentId_first_file=printpayload_jsonpath_first_file.get("output.document.parentDocumentId");
	     //   System.out.println("parentID:"+printready_parentId);
	        printready_originalDocumentName_first_file=printpayload_jsonpath_first_file.get("output.document.originalDocumentName");
	        printready_documentId_first_file=printpayload_jsonpath_first_file.get("output.document.documentId");
	        System.out.println("document id:"+printready_documentId_first_file+"                         "+printready_originalDocumentName_first_file);
		
	/*Print ready for second file*/	
	        File printready_payload2=new File(System.getProperty("user.dir")+"\\src\\main\\resources\\PrintReady_payload.txt");
	        try{
	        BufferedReader br2=new BufferedReader(new FileReader(printready_payload2));
	        while((printreadypayload_second_file=br2.readLine())!=null){
	        
	        	printreadypayload_second_file_orginal=printreadypayload_second_file;
	        }
	        }catch(Exception e){
	        	e.printStackTrace();
	        }
	        Response response_printReady_second_file=RestAssured.given().header("Authorization", authrization_token)
	        		                                        .header("Content-Type","application/json")
	        		                                        .body(printreadypayload_first_file_orginal)
	        		                                        .when()
	        		                                        .post("https://api.fedex.com/document/fedexoffice/v1/documents/"+docuement_id2_second_file+"/printready")
	        		                                        .andReturn();
	        		                                
	        ResponseBody printready_responseBody_second_file=response_printReady_second_file.getBody();
	        String printReady_body_second_file=printready_responseBody_second_file.asString();
	
	        JsonPath printpayload_jsonpath_second_file=printready_responseBody_second_file.jsonPath();
	        printready_parentId_second_file=printpayload_jsonpath_second_file.get("output.document.parentDocumentId");
	  
	        printready_originalDocumentName_second_file=printpayload_jsonpath_second_file.get("output.document.originalDocumentName");
	        printready_documentId_second_file=printpayload_jsonpath_second_file.get("output.document.documentId");
	        System.out.println("document id for second file:"+printready_documentId_second_file+"                         "+printready_originalDocumentName_second_file);    
	  
	        /*now orderrate webservice   */
	        File orderRate_payload=new File(System.getProperty("user.dir")+"\\src\\main\\resources\\orderrate_payload_2file.txt");
	        try{
	        BufferedReader orederRate_reader=new BufferedReader(new FileReader(orderRate_payload));
	        while((orderratePayload_file=orederRate_reader.readLine())!=null){
	        
	        	orderRate_payload1_final=orderratePayload_file;
	        }
	        }catch(Exception e){
	        	e.printStackTrace();
	        } 
	        String x=orderRate_payload1_final.replace("FIRST_PARENTDOCUMENTID", printready_parentId_first_file);
	        
	        String replace_content_reference=x.replace("FIRST_PRINTREADY_DOCUMENTID", printready_documentId_first_file);
	        String final_replacement_document=replace_content_reference.replace("FIRST_FILE_NAME", printready_originalDocumentName_first_file);
	        String second_parent_documentId=final_replacement_document.replace("SECOND_PARENT_DOCUMENT_ID", printready_parentId_second_file);
	        String second_documentId_file=second_parent_documentId.replace("SECOND_PRINT_DOCUMENT_ID",printready_documentId_second_file);
	        String second_fileName_replacement=second_documentId_file.replace("SECOND_FILE",printready_originalDocumentName_second_file);
	        
	        
	        System.out.println(second_fileName_replacement);
	        
	        Response orderRate_response=RestAssured.given().header("Authorization", authrization_token)
                                                           .header("Content-Type","application/json")
                                                           .body(second_fileName_replacement)
                                                            .when()
                                                           .post("https://api.fedex.com/rate/fedexoffice/v1/orderrates")
                                                           .thenReturn();
	        ResponseBody responseBody_orderRate=orderRate_response.getBody();
	        String orderRate_final=responseBody_orderRate.asString();
	        System.out.println(orderRate_final); 
	        // ************************Json path for getting total price value************************************//
	        JsonPath orderRate_jsonpath=responseBody_orderRate.jsonPath();
	        System.out.println(""+orderRate_jsonpath.get("output.orderRate.documentPrices.totalPrice").getClass());
	        total_price_multiple_file=orderRate_jsonpath.get("output.orderRate.documentPrices.totalPrice");
	        System.out.println("total price"+total_price_multiple_file);
	       
	        for(String s:total_price_multiple_file){
	        	total_price_multiple_final=s;
	        }
	        System.out.println("total price"+total_price_multiple_final);
	        return total_price_multiple_final;
	    }
}
