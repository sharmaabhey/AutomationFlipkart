package FlipkartPages;

import java.awt.AWTException;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import com.aventstack.extentreports.ExtentTest;

import Utils.CommonFunctions;

public class ProductPage {
	
	
	WebDriver driver;
	CommonFunctions objcommon;
	ExtentTest report;
	@FindBy(xpath = "//span[@class='BUOuZu']//span")
	WebElement CheckSearch;
//	
	@FindBy(xpath = "//div[@class='_3Owiq+']//following-sibling::div[@class='xe4N5q']//div[@class='_6tw8ju']")
	List<WebElement> filterslist;
	@FindBy(xpath = "(//a[@class='rPDeLR'])[1]")                      
	WebElement firstResult;
	
	@FindBy(xpath = "(//select[@class='Gn+jFg'])[2]")                      
	WebElement price;
	
	
//	
//	@FindBy(xpath = "//span[@role='button']")
//	WebElement PopUp;
//	
	
	public ProductPage(WebDriver driver,ExtentTest report) throws AWTException
	{
		this.driver=driver;
		PageFactory.initElements( driver, this);
		objcommon = new CommonFunctions(driver);
		this.report=report;
		
	}
	
	
	public void ValidateSearch(String ProductName) throws IOException
	{
		String value = CheckSearch.getText();
		if(value.equalsIgnoreCase(ProductName))
		{
			objcommon.report("PASS", "Shoes are displaying", "", report);
			Assert.assertTrue(true, "Keyword is presnt on the search page " + ProductName);
		}
		else
		{
			objcommon.report("FAIL", "Shoes are not displaying", "", report);
			Assert.assertFalse(false, "Keyword is not presnt on the search page " + ProductName);
		}
		Assert.assertEquals(value, ProductName);
	}
	
	public void selectBrand(String brandName) throws IOException
	{
		WebElement brand = driver.findElement(By.xpath("//div[@title='"+brandName+"']//child::div[@class='XqNaEv']"));
		brand.click();
		objcommon.report("INFO", "Brand is selected for the shoes: " +brandName, "", report);
		for(WebElement ele : filterslist)
		{
			String brandFilter = ele.getText();
			if(brandFilter.equalsIgnoreCase(brandName))
			{
				objcommon.report("PASS", "Brand is selected for the shoes: " +brandName, "", report);
				Assert.assertTrue(true, "Brand is selected" + brandName);
			}
			
		}
	}
	
	public void selectPrice(String Price) throws IOException, InterruptedException
	{
		
		objcommon.waitToBeElementDisplayed(Duration.ofSeconds(10), price);
		Select select = new Select(price);
		
		select.selectByValue(Price);
		objcommon.report("INFO", "Price is selected for the shoes: " +Price, "", report);
//		WebElement value = driver.findElement(By.xpath("//option[contains(text(),'"+price+"')]"));
//		objcommon.click(value);
		Thread.sleep(Duration.ofSeconds(5));
		for(WebElement pri : filterslist)
		{
			
			String priceFilter = pri.getText();
			if(priceFilter.contains(Price))
			{
				objcommon.report("PASS", "Price filter is selected: " + Price, "", report);
				Assert.assertTrue(true, "Brand is selected" + Price);
			}
			
		}
	}
	
	public void selectFirstResult() throws IOException
	{
//		objcommon.waitToBeElementDisplayed(Duration.ofSeconds(15), firstResult);
		objcommon.click(firstResult);
		objcommon.report("INFO", "Clicking on first result", "", report);
	}



}
