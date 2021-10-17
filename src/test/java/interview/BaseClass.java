package interview;

import java.io.IOException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

import functions.Utility;

public class BaseClass extends Utility {

	public WebDriver wDriver;
	public EventFiringWebDriver driver;
	static ExtentTest test;
	static ExtentReports report;

	@BeforeTest
	public void launchReport() throws MalformedURLException {

		report = new ExtentReports(System.getProperty("user.dir")+"\\ExtentReportResults.html");
	}
	
	@BeforeMethod
	public void launchBrowser() throws MalformedURLException {

		ChromeOptions options = new ChromeOptions();
		options.addArguments("start-maximized");
		//driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), options);

		System.setProperty("webdriver.chrome.driver", "C:\\Selenium\\chromedriver.exe");
		wDriver = new ChromeDriver(options);
	}

	@DataProvider(name="testdata")
	public Object[][] testDataProvider(Method method) throws IOException {

		String methodName = method.getName();
		Object[][] arrObj = excelReader("TestData.xlsx", "Sheet1", methodName);
		return arrObj;
	}

	@AfterMethod
	public void closeBrowser() {
		
		wDriver.quit();		
	}
	
	@AfterTest
	public void closeReport() {
		
		report.endTest(test);
		report.flush();
	}

}
