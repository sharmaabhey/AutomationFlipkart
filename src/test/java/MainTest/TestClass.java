package MainTest;

import java.time.Duration;
import java.util.Map;

import org.testng.annotations.Test;

import BaseClass.Base;
import Datautils.Data_Supplier;
import FlipkartPages.HomePage;
import FlipkartPages.ProductDetailPage;
import FlipkartPages.ProductPage;
import Utils.CommonFunctions;

public class TestClass extends Base
{
	
	
	
	
	
	@Test(dataProvider="flipkart" , dataProviderClass= Data_Supplier.class)
	
	public void FlipkartTest(Map objFlipkart) throws Exception 
	{
		String TestCaseName = ((String) objFlipkart.get("TestCaseName"));
		String ProductName = ((String) objFlipkart.get("ProductName"));
		String BrandName = ((String) objFlipkart.get("BrandFilter"));
		String Price = ((String) objFlipkart.get("PriceFilter"));
		String ShoeSize = ((String) objFlipkart.get("ShoeSize"));
		
		test = report.createTest(TestCaseName);
		
		HomePage objh = new HomePage(driver,test);
		ProductPage objproduct = new ProductPage(driver,test);
		CommonFunctions objcommon = new CommonFunctions(driver);
		ProductDetailPage objdetail = new ProductDetailPage(driver,test);
	
		objh.Search(ProductName);
		objproduct.ValidateSearch(ProductName);
		objproduct.selectBrand(BrandName);
		objproduct.selectPrice(Price);
		objproduct.selectFirstResult();
		objcommon.SwitchWindow();
		objdetail.SelectSize(ShoeSize);
		objdetail.clickOnBuyNow();
		objdetail.validateBuyNowPage();
		
		Thread.sleep(Duration.ofSeconds(10));
		
		
		
		
		
	}

}
