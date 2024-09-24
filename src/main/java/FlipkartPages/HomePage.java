package FlipkartPages;

import java.awt.AWTException;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;

import Utils.CommonFunctions;

public class HomePage {
	
	WebDriver driver;
	CommonFunctions objcommon ;
	ExtentTest report;
	@FindBy(xpath = "//span[@role='button']")
	WebElement PopUp;
	
	@FindBy(xpath = "//input[@type='text']")
	WebElement Search;
	
	@FindBy(xpath = "//span[@class='BUOuZu']//span")
	WebElement CheckSearch;
//	
//	@FindBy(xpath = "//span[@role='button']")
//	WebElement PopUp;
//	
//	@FindBy(xpath = "//span[@role='button']")
//	WebElement PopUp;
//	
	
	public HomePage(WebDriver driver,ExtentTest Report) throws AWTException
	{
		this.driver=driver;
		this.report=Report;
		PageFactory.initElements( driver, this);
		objcommon= new CommonFunctions(driver);
	}
	
	
	public void closePopUp() throws IOException
	{
		PopUp.click();
		if(PopUp.isDisplayed())
		{
			objcommon.report("PASS", "Popup is displayed", "Popup is not displayed", report);
		}
		else
		{
			objcommon.report("FAIL", "Popup is displayed", "Popup is not displayed", report);
		}
	}
	
	public void Search(String nameOfProduct) throws Exception
	{
		Search.sendKeys(nameOfProduct);
		objcommon.robotKeyPressOperation(KeyEvent.VK_ENTER);
		Thread.sleep(Duration.ofSeconds(10));
		objcommon.report("INFO", "Searching product is: "+ nameOfProduct, "", report);
		
	
	}

}
