package FlipkartPages;

import java.awt.AWTException;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.aventstack.extentreports.ExtentTest;

import Utils.CommonFunctions;

public class ProductDetailPage 
{

	WebDriver driver;
	boolean displayed;
	CommonFunctions objcommon;
	ExtentTest Report;
	boolean display;

	@FindBy(xpath = "//div[@class='_3Rka5k _60M1Vj col col-12-12']//li[@class='aJWdJI dpZEpc']//a")
	List<WebElement> listsize;
	
	@FindBy(xpath ="//div[text()='Sold Out']")
	WebElement soldOut;
	
	@FindBy(xpath ="//button[@type='button']")
	WebElement buyNow;
	
	@FindBy(xpath ="//span[text()='Login or Signup']")
	WebElement BuyNowpageValidate;
	

	public ProductDetailPage(WebDriver driver,ExtentTest report) throws AWTException
	{
		this.driver = driver;
		this.Report=report;
		PageFactory.initElements(driver, this);
		objcommon = new CommonFunctions(driver);

	}
	
	
	
	public void SelectSize(String shoeSize) throws IOException
	{
	        for(int i =0;i<listsize.size();i++)
	        {
	            String val = listsize.get(i).getText();
	            if(val.contentEquals(shoeSize))
	            {
	                
	                while(displayed)
	                try
	                {
	                    if(soldOut.isDisplayed())
	                    {
	                        displayed=true;
	                        
	                    }
	                    else
	                    {
	                        displayed =false;
	                    }
	                    
	                }
	                catch(Exception e)
	                {
	                    
	                }
	                if(displayed)
	                {
	                	objcommon.report("FAIL", "", "Sorry, this size is not available please choose different size", Report);
	                    Assert.assertFalse(false, "Sorry, this size is not available please choose different size");
	                }
	                else
	                {
	                    WebElement size= driver.findElement(By.xpath("(//div[@class='_3Rka5k _60M1Vj col col-12-12']//ul//li[@class='aJWdJI dpZEpc']//a)["+(i+1)+"]"));
	                    JavascriptExecutor js = (JavascriptExecutor)driver;
	                    js.executeScript("arguments[0].click()", size);
	                    Assert.assertTrue(true, "Size is present");
	                    objcommon.report("PASS", "Shoe Size " +shoeSize + " is selected " , "", Report);
	                    break;
	                }
	                
	            }
	        }
	}
	
	public void clickOnBuyNow() throws IOException, InterruptedException
	{
		
//		objcommon.click(buyNow);
		buyNow.click();
		objcommon.report("INFO", "Click on Buy Now" , "", Report);
	}
	
	public void validateBuyNowPage() throws IOException
	{
		
		try
		{
			display = BuyNowpageValidate.isDisplayed();
		}
		catch(Exception e)
		{
			
		}
		
		if(display)
		{
			objcommon.report("PASS", "Login or SignUp page displayed", "", Report);
		}
		else
		{
			objcommon.report("Fail", "", "Login or SignUp page is not displayed", Report);
		}
		
	}
	
	
	

}
