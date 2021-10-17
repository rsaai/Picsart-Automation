package pageClass;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class BlogPage {

	WebDriver driver;

	@FindBy(xpath = "//a[text()='Design School']")
	WebElement designSchool;

	@FindBy(xpath = "//a[text()='Trends']")
	WebElement trends;

	@FindBy(xpath = "//a[text()='Picsart Pro']")
	WebElement picsartPro;

	@FindBy(xpath = "//a[text()='News']")
	WebElement news;

	@FindBy(className = "search-form-searchButton-0-2-129")
	WebElement searchButton;

	@FindBy(className = "search-form-searchInput-0-2-128")
	WebElement searhTextbox;

	@FindBy(xpath = "//div[contains(@class, 'main-carousel-item-centeredContent')]")
	WebElement mainView;

	@FindBy(xpath = "//i[contains(@class, 'main-carousel-arrow-icon')]")
	WebElement leftArrowButton;

	@FindBy(xpath = "//a[contains(@class,'main-carousel-item-titleLink')]")
	WebElement centrePost;

	@FindBy(xpath = "//h1[contains(@class, 'post-single-postTitle')]")
	WebElement postTitle;

	@FindBy(xpath = "//button[contains(@class,'main-carousel-suggestions') and contains(@data-index,'1')]")
	WebElement suggestedImage;



	//Click on Design School button
	public void clickDesignSchool(){
		designSchool.click();
	}

	//Click on Trends button
	public void clickTrends(){
		trends.click();     
	}

	//Click on Picsart Pro button
	public void clickPicsartPro(){
		picsartPro.click();
	}

	//Click on News button
	public void clickNews(){
		news.click();
	}  

	public void clickSearch() {
		searchButton.click();
	}

	public void enterSearchBox(String searchValue) {
		searhTextbox.sendKeys(searchValue);
	}


	//This POM method will be used to explore the different categories in the Blog page
	public void validateBlogPage(WebDriver driver, ExtentTest test, String blogPageURL, String category1,String category2, String category3, String category4, String searchValue) throws InterruptedException{
		try {


			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

			//Click on Design School button in the Home screen
			this.clickDesignSchool();
			Thread.sleep(2000);
			Assert.assertEquals(driver.getCurrentUrl(), blogPageURL + "/category/" + category1);
			test.log(LogStatus.PASS, "Click on: " + category1 + " button in the Blog page and expected page appears");    

			//Click on Trends button in the Home screen
			this.clickTrends();
			Thread.sleep(2000);
			Assert.assertEquals(driver.getCurrentUrl(), blogPageURL + "/category/" + category2);
			test.log(LogStatus.PASS, "Click on: " + category2 + " button in the Blog page and expected page appears");

			//Click on Picsart Pro button in the Home screen
			this.clickPicsartPro();
			Thread.sleep(2000);
			Assert.assertEquals(driver.getCurrentUrl(), blogPageURL + "/category/" + category3);
			test.log(LogStatus.PASS, "Click on: " + category3 + " button in the Blog page and expected page appears");

			//Click on Design School button in the Home screen
			this.clickNews();
			Thread.sleep(2000);
			Assert.assertEquals(driver.getCurrentUrl(), blogPageURL + "/category/" + category4);
			test.log(LogStatus.PASS, "Click on: " + category4 + " button in the Blog page and expected page appears");

			driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
			Thread.sleep(2000);
			this.clickSearch();
			this.enterSearchBox(searchValue);
			searhTextbox.sendKeys(Keys.ENTER);
			test.log(LogStatus.INFO, "Enter: " + searchValue + " in the search box");

			Assert.assertEquals(driver.getCurrentUrl(), blogPageURL + "/search?s=" + searchValue);
		} catch (Exception e) {
			// TODO: handle exception
			test.log(LogStatus.FAIL, e);
			e.printStackTrace();
		}

	}


	public void validateBlogPostArrowAndImage(WebDriver driver, ExtentTest test) throws InterruptedException {
		try {
			WebDriverWait explicitWait = new WebDriverWait(driver, 10);
			try {
				explicitWait.until(ExpectedConditions.elementToBeClickable(mainView));
			} catch (StaleElementReferenceException e) {
				explicitWait.until(ExpectedConditions.elementToBeClickable(mainView));
			}

			Actions actionBuilder1 = new Actions(driver);
			actionBuilder1.moveToElement(mainView).moveToElement(leftArrowButton).click().build().perform();
			actionBuilder1.moveToElement(centrePost).click().build().perform();
			test.log(LogStatus.PASS, "Clicked on left arrow and selected the respective image post");

			explicitWait = new WebDriverWait(driver, 10);
			try {
				explicitWait.until(ExpectedConditions.elementToBeClickable(postTitle));
			} catch (StaleElementReferenceException e) {
				explicitWait.until(ExpectedConditions.elementToBeClickable(postTitle));
			}
			String postTextAfterClick = postTitle.getText();
			System.out.println(postTextAfterClick);
			Assert.assertEquals(postTextAfterClick, "15 Best Fonts for Websites");
			test.log(LogStatus.PASS, "Clicked on the image post and validated the specific Post page");

		} catch (Exception e) {
			// TODO: handle exception
			test.log(LogStatus.FAIL, e);
			e.printStackTrace();
		}

	}

	public void validateBlogChoosenPost(WebDriver driver, ExtentTest test) throws InterruptedException {
		try {
			WebDriverWait explicitWait = new WebDriverWait(driver, 20);
			try {
				explicitWait.until(ExpectedConditions.elementToBeClickable(suggestedImage));
			} catch (StaleElementReferenceException e) {
				explicitWait.until(ExpectedConditions.elementToBeClickable(suggestedImage));
			}
			suggestedImage.click();
			test.log(LogStatus.INFO, "Clicked on the suggested post");

			explicitWait = new WebDriverWait(driver, 10);
			try {
				explicitWait.until(ExpectedConditions.elementToBeClickable(centrePost));
			} catch (StaleElementReferenceException e) {
				explicitWait.until(ExpectedConditions.elementToBeClickable(centrePost));
			}
			String postTextAfterClick = centrePost.getText();
			System.out.println(postTextAfterClick);
			test.log(LogStatus.PASS, "Clicked on the suggested post and validated the specific Post page");
		} catch (Exception e) {
			// TODO: handle exception
			test.log(LogStatus.FAIL, e);
			e.printStackTrace();
		}

	}



}
