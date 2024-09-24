package Utils;

import java.awt.AWTException;
import java.awt.Robot;
import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;

import Datautils.Report_utility;

public class CommonFunctions {
	
	WebDriver driver;
	
	
	
	public  CommonFunctions(WebDriver driver) {
		this.driver=driver;
	}
	
	
	public void robotKeyPressOperation(int keyvent) throws AWTException
	{
		Robot rb = new Robot();
		rb.keyPress(keyvent);
		rb.keyRelease(keyvent);
	}
	
	public void SwitchWindow()
	{
		 String oldwin = driver.getWindowHandle();
	        for(String currwin : driver.getWindowHandles())
	        {
	            if(oldwin!=currwin)
	            {
	                driver.switchTo().window(currwin);
	            }
	            
	        }
	}
	
	public void click(WebElement element)
	{
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].click()", element);
	}
	
	public void waitToBeElementDisplayed(Duration time,WebElement Element)
	{
		WebDriverWait wait = new WebDriverWait(driver,time);
		wait.until(ExpectedConditions.visibilityOf(Element));
	}
	
	public void report(String result, String passMessage,String FailMessage, ExtentTest Report) throws IOException
	{
		if(result.equalsIgnoreCase("PASS"))
		{
			Report.pass(passMessage, MediaEntityBuilder.createScreenCaptureFromPath(Report_utility.captureSS(driver)).build());
		}
		else if(result.equalsIgnoreCase("FAIL"))
		{
			Report.fail(FailMessage, MediaEntityBuilder.createScreenCaptureFromPath(Report_utility.captureSS(driver)).build());
		}
		else
		{
			Report.info(passMessage);
		}
		
	}

}
