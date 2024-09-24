package Datautils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;

public class Report_utility {

	static String ScreenshotPath = null;

	public static String main_Folder_Creation(String basepath) {
		Date d = new Date();
		SimpleDateFormat date_format_ = new SimpleDateFormat("dd_MM_YYYY_HH_mm_ss");
		String timestamp = date_format_.format(d);
		
		String executionfolder_path = basepath + "Execution_Results" + timestamp;
		File reportfolder = new File(executionfolder_path);
		if (!reportfolder.exists()) {
			reportfolder.mkdir();
			System.out.println("folder is created at:" + " " + reportfolder);
		} else {
			System.out.println("Folder Already exists");
		}
		return executionfolder_path;
	}

	public static String screenshot_folder_creation(String Ssbasepath) {
//   	ScreenshotPath = System.getProperty("user.dir") + "\\Trip_Extenttest_Report\\Execution_Results" + timestamp+ "\\ScreenShot\\";
		ScreenshotPath = Ssbasepath + "\\ScreenShot\\";
		File Screenshot_folder = new File(ScreenshotPath);
		Screenshot_folder.mkdir();
		System.out.println("ScreenShot folder is created at:" + " " + Screenshot_folder);
		return ScreenshotPath;
	}

	public static String captureSS(WebDriver driver) throws IOException {
//		String imgPath = System.getProperty("user.dir") +"\\ValidScreenshots\\"+ "SS" +timestamp+ ".png";
		Date d = new Date();
		SimpleDateFormat date_format = new SimpleDateFormat("dd_MM_YYYY_HH_mm_ss");
		String timestamp2 = date_format.format(d);
		
		String imgPath =  ScreenshotPath + "SS" + timestamp2 + ".png";   
		File destpath = new File(imgPath);
		try {
			File sourseFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(sourseFile, destpath);
			System.out.println("screenshot stored at:" + destpath);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return imgPath;
	}

//	public static void assertion_text_verification(String Actual_text, String Expected_text, WebDriver driver, ExtentTest reporter,
//			String Pass_msg, String Fail_msg,boolean TakeScreenshot) throws IOException {
//
//		if (Actual_text.equals(Expected_text)) {
//			if(TakeScreenshot == true) {
//				Assert.assertEquals(Actual_text,Expected_text);
//				reporter.pass("Text Assertion passed :" + " " + Pass_msg ,MediaEntityBuilder.createScreenCaptureFromPath(Report_utility.captureSS(driver)).build());
//			}
//			else {
//				System.out.println("Assertion passed :" + " " + Pass_msg);
//			}
//			
//		} else {
//			if(TakeScreenshot == false) {
//				Assert.assertNotEquals(Actual_text,Expected_text);
//				reporter.pass("Not Equal Assertion passed :" + " " + Pass_msg);
//			//	reporter.pass("Assertion passed :" + " " + Pass_msg ,MediaEntityBuilder.createScreenCaptureFromPath(Report_utility.captureSS(driver)).build());
//			}
//			else{
//				reporter.fail("Assertion failed :" + " " + Fail_msg);
//				System.out.println("Assertion failed ");
//			}
//			
//		}
//	}

}
