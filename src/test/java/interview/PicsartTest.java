package interview;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import functions.EventListeners;
import pageClass.BlogPage;
import pageClass.HomePage;
import restAssuredValidation.SchemaValidation;

public class PicsartTest extends BaseClass {

	public String blogURL = "https://picsart.com/blog";

	@Test(dataProvider="testdata")
	public void picsartUIValidation(HashMap<String, String> data) throws InterruptedException {

		try {
			//Extent Report Start
			test = report.startTest("PicsArt Validation");

			//Webdriver listener
			driver = new EventFiringWebDriver(wDriver);
			EventListeners eCapture = new EventListeners();
			driver.register(eCapture);

			//Open Picsart Home page
			driver.navigate().to(data.get("URL"));
			driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
			test.log(LogStatus.PASS, "Successfully opened the Picsart url " + data.get("URL"));

			//HomePage URL before Login 
			String beforeLoginURL = driver.getCurrentUrl();
			Assert.assertEquals(beforeLoginURL, data.get("URL"));
			test.log(LogStatus.PASS, "HomePage URL before Login: " + beforeLoginURL);

			//Login into Picsart
			HomePage homePage = PageFactory.initElements(driver, HomePage.class);
			homePage.loginToPicsart(driver, test, data.get("UserName"), decodedString(data.get("Password")));
			test.log(LogStatus.PASS, "Successfully logged into the Picsart application ");


			//HomePage URL after Login
			String afterLoginURL = driver.getCurrentUrl();
			Assert.assertEquals(afterLoginURL, data.get("URL") + "create");
			test.log(LogStatus.PASS, "HomePage URL after Login: " + afterLoginURL);


			//Blog Page Category validation 
			driver.navigate().to(blogURL); 
			driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
			BlogPage blogPage = PageFactory.initElements(driver, BlogPage.class);
			blogPage.validateBlogPage(driver, test, blogURL, data.get("Category1"), data.get("Category2"), data.get("Category3"), data.get("Category4"), data.get("SearchText"));
			test.log(LogStatus.PASS, "Successfully returned search results for the keyword: " + data.get("SearchText"));
			
			//Blog Page Post Validation
			driver.navigate().to(blogURL); 
			driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
			blogPage.validateBlogPostArrowAndImage(driver, test);
			test.log(LogStatus.PASS, "Successfully Validated Blog page posts, Images and arrow ");

			//Blog Page Post Validation 
			driver.navigate().back();
			driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
			blogPage.validateBlogChoosenPost(driver, test);
			test.log(LogStatus.PASS, "Successfully Validated Blog page chosen post");


			//Schema Validation
			SchemaValidation restAssuredValidation = new SchemaValidation();
			restAssuredValidation.schemaValidator();
			test.log(LogStatus.PASS, "Successfully Validated the schema for the given URI");
			driver.unregister(eCapture);
			
		} catch (Exception e) {
			// TODO: handle exception
			test.log(LogStatus.ERROR, e);
			e.printStackTrace();
		}
	}
}
