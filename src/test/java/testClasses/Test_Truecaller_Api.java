package testClasses;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import com.healthkart.hkAutomation.util.CommonFunctions;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class Test_Truecaller_Api
{
	CommonFunctions common;
	static String email;
	static String password;
	static String profileTag = "Not found";
	
	static int counter = 0;
	static int statusCode = 200;
	static int authCounter = -1;
	static String authorization = "Bearer a1w0Q1300038888TDkuYTCGqQ9ah5N0B8d-s3Coq_KLXKHtBG_QUA3-DE4W-BF2s";
	TrueCallerData tdata;
	public Test_Truecaller_Api() 
	{
		common = new CommonFunctions();
	}
	static
	{
		email = "gofroautomation@gmail.com";
		password = "Automation123";
	}
	public static void main(String args[]) throws IOException
	{
	    int counter = 0;
	    Workbook workbook = new XSSFWorkbook();
	    

	    List<TrueCallerData> truecallerDataList = new ArrayList<TrueCallerData>();
	    BufferedReader bufReader = new BufferedReader(new FileReader("E:\\truecaller_data.txt"));
	    		ArrayList<String> listOfLines = new ArrayList<String>();
	    		String line = bufReader.readLine(); while (line != null)
	    		{
	    		    listOfLines.add(line);
	    		    line = bufReader.readLine();
	    		} 
	    		bufReader.close();
     
	    		HashMap<String, String> resultMap = new HashMap<String, String>();

	    		for(String phoneNumber : listOfLines)
				{
		    		RestAssured.baseURI = "https://www.truecaller.com/api/search?";
		    		Response response = RestAssured.given().when()
					.and().param("type", "5")
					.and().param("countryCode", "in")
					.and().param("q", phoneNumber)
					.and().header("authority", "webapi-noneu.truecaller.com")
					.and().header("method", "GET")
					.and().header("path", "/search?countryCode=in&q=9987588772&recaptcha=03AJpayVFVTC3m00_mO-FsI99BH9TMUbU163zb9umVcemkzRD2Ds8edQDC3AhXSJOdrYJv6g9UilfRFzUCxwnA4CNx8Z_3oqPdQJGeeeX3oc2sjD1BH8wSetOZaWNwy-UQ13UhanVzioyhEHaTeZKC62lvHi4c_qqn2fuPDAT9WTiStYMvm4rm80eIMVdjCkpwGO9QEnNLoQSBHj4c_I2YyUBcdrNRugVoPfzlGT5goTVTDPHOSk9L5tFaQ6looYaittk9iG1_EQLZ2HZ9vRmhLgrKOzSdwV6A1zTXF6hh_wD7i5zZa_OlcR-p-OCyiwkyi50pBL89o8spcki1-HPCNpBeNY-heAAbyY6Z2VqmRCerkXyvIMkEu7NO33AXt2vjB1zzz1L-4QJpY1gbiCxX_Wm3QrMdHQUjVowogeq40yxUnLCAHCcAxBz3wUuO_8zWfUc2T4xAmFPxKCiEM1OEQF7t0Ky0CU9lWuCgF7uWqsaNz_A1H5czpiuyH8ox7emjzRiYi_lNpbCS5zY_ELtd-o-oV7uLY_g5wZCbtRZ97bLiYuXfqRm8kqEe5JWU9fLlNMkdTE9k3UuHjvoxJ-iWhi3I0h-ByTZn7w")
					.and().header("scheme","https")
					.and().header("accept","application/json, text/plain, */*")
					.and().header("accept-encoding","gzip, deflate, br")
					.and().header("accept-language","en-US,en;q=0.9")
					.and().header("authorization","Bearer a1w0Q1300038888TDkuYTCGqQ9ah5N0B8d-s3Coq_KLXKHtBG_QUA3-DE4W-BF2s")
					.and().header("origin","https://www.truecaller.com")
					.and().header("referer","https://www.truecaller.com/search/in/9987588772")
					.and().header("user-agent", "Mozilla/5.0 (Linux; Android 6.0; Nexus 5 Build/MRA58N) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/67.0.3396.30 Mobile Safari/537.36")
					.and().header("countryCode", "in")
					.and().header("q", "9987588772")
					.and().header("recaptcha","03AJpayVFVTC3m00_mO-FsI99BH9TMUbU163zb9umVcemkzRD2Ds8edQDC3AhXSJOdrYJv6g9UilfRFzUCxwnA4CNx8Z_3oqPdQJGeeeX3oc2sjD1BH8wSetOZaWNwy-UQ13UhanVzioyhEHaTeZKC62lvHi4c_qqn2fuPDAT9WTiStYMvm4rm80eIMVdjCkpwGO9QEnNLoQSBHj4c_I2YyUBcdrNRugVoPfzlGT5goTVTDPHOSk9L5tFaQ6looYaittk9iG1_EQLZ2HZ9vRmhLgrKOzSdwV6A1zTXF6hh_wD7i5zZa_OlcR-p-OCyiwkyi50pBL89o8spcki1-HPCNpBeNY-heAAbyY6Z2VqmRCerkXyvIMkEu7NO33AXt2vjB1zzz1L-4QJpY1gbiCxX_Wm3QrMdHQUjVowogeq40yxUnLCAHCcAxBz3wUuO_8zWfUc2T4xAmFPxKCiEM1OEQF7t0Ky0CU9lWuCgF7uWqsaNz_A1H5czpiuyH8ox7emjzRiYi_lNpbCS5zY_ELtd-o-oV7uLY_g5wZCbtRZ97bLiYuXfqRm8kqEe5JWU9fLlNMkdTE9k3UuHjvoxJ-iWhi3I0h-ByTZn7w")						
					.get();
		    	    System.out.println(response.getStatusCode());
		    	    statusCode = response.getStatusCode();
		    	    System.out.println(++counter + " searched");
		    	    System.out.println(response.asString());
		    	    if(statusCode==403)
		    	    {
		    	    	System.out.println(++authCounter);
		    	    	ArrayList<String> authKeys = new ArrayList<String>();
		    	    	authKeys.add("Bearer a1w0U1023202156TKKkoktBSnoWhw1tI25-hfuVMYZY6n2mTWBS0-OQii4W4E6Hz");
		    	    	authKeys.add("Bearer a1w0S1023204460TOMmBz1KLD0BXsKCA1LtO4x7eqOam8q-hPoOz8ValZ4W4_whY");
		    	    	authorization = authKeys.get(authCounter);
		    	    }
		    	    
		    	    else if(statusCode==200 && StringUtils.contains(response.asString(), "tags\":[\"5\",\"71\"]"))
		    	    {
		    	    	profileTag = "Gym";
		    	    }
		    	    
		    	    else if(statusCode==200)
		    	    {
		    	    	profileTag = "Not found";
		    	    }
		    	    resultMap.put(phoneNumber, profileTag);
		    	    
		    	    TrueCallerData tdata = new TrueCallerData(phoneNumber, profileTag);
		    	    truecallerDataList.add(tdata);
//		    	    System.out.println(response.asString());
	}
	    		try 
	    		{
		           /*  FileWriter writer = new FileWriter("E:\\result.txt", true);
		             writer.write(resultMap.toString());
		             writer.close();*/
	    			
	    			Sheet sheet = workbook.createSheet("truecaller_data");
	    			Iterator<TrueCallerData> iterator = truecallerDataList.iterator();
	    			int rowIndex = 0;
	    			while(iterator.hasNext())
	    			{
	    				TrueCallerData tData = iterator.next();
	    				Row row = sheet.createRow(rowIndex++);
	    				Cell cell0 = row.createCell(0);
	    				cell0.setCellValue(tData.getMobileNumber());
	    				Cell cell1 = row.createCell(1);
	    				cell1.setCellValue(tData.getProfileTag());
	    			}
	    			
		        }
	    		catch (Exception e) 
	    		{
	    		     e.printStackTrace();
		        }
	    		
	    		try
	    		  {
	    		   //Write the workbook in file system
	    		   FileOutputStream out = new FileOutputStream(new File("E:\\truecaller_result.xlsx"));
	    		   workbook.write(out);
	    		   out.close();
	    		   System.out.println("xlsx has been created successfully");
	    		  } 
	    		  catch (Exception e) 
	    		  {
	    		   e.printStackTrace();
	    		  }
	}
}