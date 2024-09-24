package BaseClass;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import Datautils.Report_utility;

public class Base {
	
	protected WebDriver driver;
	public static String folder_path_extentreport;
	public static String extentreport_execution_folderpath;
	public static String screenshot_folderpath;
	static String screenshot_path= null;
	protected static ExtentTest test = null;
	protected static ExtentReports report = null;
	
	
	
	@BeforeSuite
	public void extent_report() {
		
		//we are creating folders in our current directory.
		folder_path_extentreport = System.getProperty("user.dir") + "\\report_of_extenttest\\";
		extentreport_execution_folderpath =Report_utility.main_Folder_Creation(folder_path_extentreport);
		screenshot_folderpath = Report_utility.screenshot_folder_creation(extentreport_execution_folderpath);
		
		//Extent report is created here
		ExtentSparkReporter  extent_report = new ExtentSparkReporter(extentreport_execution_folderpath + "\\flight_report_file.html");
		extent_report.config().setDocumentTitle("Makemytrip_automation");
		extent_report.config().setReportName("automation");
		extent_report.config().setTheme(Theme.DARK);
		report = new ExtentReports();
		report.attachReporter(extent_report);
		
	

		
		
		
	}
	
	
	@AfterSuite
	public void extent_end() {
		report.flush();
		
	}
	
	@BeforeTest
	public void launchBrowser() {
//		WebDriverManager.edgedriver().setup();
		System.setProperty("webdriver.chrome.driver", "C:\\Drivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.navigate().to("https://www.flipkart.com/");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		driver.manage().window().maximize();
		
		
		
		
		
		
		
		
	}
	
	@AfterTest
	public void CloseBrowser() {
		driver.quit();
	}

}
