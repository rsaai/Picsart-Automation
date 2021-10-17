package pageClass;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class HomePage {

	WebDriver driver;

	@FindBy(xpath = "//button[@data-test='login-button']")
	WebElement loginHomeScren;

	@FindBy(name="username")
	WebElement userName;

	@FindBy(name="password")
	WebElement password;    

	@FindBy(xpath = "//button[@data-test='login']")
	WebElement loginButton;

	@FindBy(xpath = "//img[@title='User avatar']")
	WebElement userAvatar;

	@FindBy(xpath="//strong[@data-test='navigation-learn']")
	WebElement learn;

	@FindBy(xpath="//a[@class='rc-header-root-0-2-29 has-description']/strong")
	WebElement blog;


	
	//Click on login button
	public void clickLoginHomeScreen(){
		loginHomeScren.click();
	}

	//Set user name in textbox
	public void setUserName(String strUserName){
		userName.sendKeys(strUserName);     
	}

	//Set password in password textbox
	public void setPassword(String strPassword){
		password.sendKeys(strPassword);
	}

	//Click on login button
	public void clickLogin(){
		loginButton.click();
	}  


	//This POM method will be used to login to the application
	public void loginToPicsart(WebDriver driver, ExtentTest test, String strUserName,String strPasword) throws InterruptedException{
		try {

			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

			//Click on Login button in the Home screen
			this.clickLoginHomeScreen();
			test.log(LogStatus.INFO, "Click on Login button in the Home screen");

			//Fill user name
			this.setUserName(strUserName);
			test.log(LogStatus.INFO, "Enter Username in the login screen: " + strUserName);

			//Fill password
			this.setPassword(strPasword);
			test.log(LogStatus.INFO, "Enter Password in the login screen ");

			//Click Login button
			this.clickLogin(); 
			test.log(LogStatus.INFO, "Click on Login button in the Login screen");


			WebDriverWait explicitWait = new WebDriverWait(driver, 30);
			try {
				explicitWait.until(ExpectedConditions.elementToBeClickable(userAvatar));
			} catch (StaleElementReferenceException e) {
				explicitWait.until(ExpectedConditions.elementToBeClickable(userAvatar));
			}
		} catch (Exception e) {
			// TODO: handle exception
			test.log(LogStatus.FAIL, e);
			e.printStackTrace();
		}

	}


}
