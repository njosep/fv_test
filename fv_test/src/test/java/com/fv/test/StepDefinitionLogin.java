package com.fv.test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.fv.pages.FVpage;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import cucumber.api.DataTable;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import utility.PwdEncryption;

public class StepDefinitionLogin {
	WebDriver driver;
	Scenario scenario;
	ExtentReports extent;
	ExtentTest logger;
	public static String fname = null;
	public static String lname = null;
	public static String time = null;
	Logger log =  Logger.getLogger("devpinoyLogger");
	@Before({ "@fv" })
	public void ChromeBrowserSetup(Scenario scenario) throws NullPointerException {
		
		System.setProperty("webdriver.chrome.driver", "src/driver/chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--start-maximized");
		options.addArguments("--disable-web-security");
		options.addArguments("--no-proxy-server");

		Map<String, Object> prefs = new HashMap<String, Object>();
		prefs.put("credentials_enable_service", false);
		prefs.put("profile.password_manager_enabled", false);
		prefs.put("profile.default_content_setting_values.notifications", 2);
		options.setExperimentalOption("prefs", prefs);
		driver = new ChromeDriver(options);
		driver.navigate().to("https://web.staging.firstviewbackend.com/login");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

		log.debug("Test Environment Set up");
		log.debug("----------------------------------------------------------------------");
		log.debug("Executing Scenario : " + scenario.getName());

		FVpage fv = new FVpage(driver);
		fv.setscenario(scenario);
	}

	@After({ "@fv" })
	public void tearDown(Scenario scenario) throws NullPointerException {
		scenario.write("Finished scenario");
		log.debug("Test Environment Destroyed");
		log.debug("----------------------------------------------------------------------");
		driver.close();
		driver.quit();
	}

	public void browserLaunch() throws NullPointerException {

		System.setProperty("webdriver.chrome.driver", "src/driver/chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--start-maximized");
		options.addArguments("--disable-web-security");
		options.addArguments("--no-proxy-server");

		Map<String, Object> prefs = new HashMap<String, Object>();
		prefs.put("credentials_enable_service", false);
		prefs.put("profile.password_manager_enabled", false);
		prefs.put("profile.default_content_setting_values.notifications", 2);
		options.setExperimentalOption("prefs", prefs);
		driver = new ChromeDriver(options);
		driver.navigate().to("https://web.staging.firstviewbackend.com/login");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

		FVpage fv = new FVpage(driver);
	}

	@Given("First View Login page is Accesible$")
	public void FVPageAcess() throws Throwable {
		FVpage fv = new FVpage(driver);
		try {
			(new WebDriverWait(driver, 50)).until(ExpectedConditions.elementToBeClickable(fv.FVlogo));
			if (fv.getEmail().isDisplayed()) {
				log.debug("FV launched Succesfully");
			}
		} catch (Exception e) {
			log.debug("FV not Launched");
			e.printStackTrace();
		}
	}

	@When("^The user provides an email address$")
	public void enterEmail(DataTable email) throws Throwable {
		FVpage fv = new FVpage(driver);
		List<List<String>> data = email.raw();

		try {
			fv.enterEmail(data.get(0).get(1).toString());

			log.debug("Email Entered : ");
			for (int i = 0; i < data.size(); i++) {
				log.debug(data.get(i).toString());
			}

		} catch (Exception e) {
			log.debug("Email Could not be Entered");
			e.printStackTrace();
		}
	}

	@And("^The user provides a password$")
	public void enterPassWord(DataTable password) throws Throwable {
		PwdEncryption pwd = new PwdEncryption(driver);
		FVpage fv = new FVpage(driver);
		List<List<String>> data = password.raw();

		try {
			fv.enterPass(pwd.passwordDecoder(data.get(0).get(1).toString()));
			log.debug("Password Entered :");
			for (int j = 0; j < data.size(); j++) {
				log.debug(data.get(j).toString());
			}
		} catch (Exception e) {
			log.debug("Password Could not be Entered");
			e.printStackTrace();
		}
	}

	@And("^Click on the Login button$")
	public void clickLogin() throws InterruptedException {
		FVpage fv = new FVpage(driver);
		try {
			driver.findElement(fv.Login).click();
			log.debug("Login Button Clicked");
		} catch (Exception e) {
			log.debug("Login Button Could not be Clicked");
			e.printStackTrace();
		}

	}

	@Then("^User should be logged in successfully$")
	public void user_will_be_able_to_login_successfully() throws Throwable {
		FVpage fv = new FVpage(driver);

		try {
			fv.getskipButton().click();
			log.debug("Skip Button Clicked on Tips Page");

		} catch (Exception e) {
			log.debug("Skip Button Could not be clicked");
			
			e.printStackTrace();
		}
	}

	@Then("^User Name should be Displayed on Home Screen$")
	public void home_page_should_be_displayed() throws Throwable {
		FVpage fv = new FVpage(driver);
		try {

			log.debug("User Name on Home Page : " + driver.findElement(fv.User).getText());
			fv.getScreenshot(driver);
			

		} catch (Exception e) {
			log.debug("Home Page Not Loaded");
			e.printStackTrace();
		}
	}

	@Then("^User should be logged out successfully$")
	public void user_should_be_logged_out_successfully() throws Throwable {

		FVpage fv = new FVpage(driver);
		List<WebElement> elems = driver.findElements(fv.dashboard);
		// log.debug(elems.size());

		try {

			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("window.scrollTo(0, Math.max(document.documentElement.scrollHeight, document.body.scrollHeight, document.documentElement.clientHeight));");
			js.executeScript("arguments[0].click();", elems.get(5));
			log.debug("Logout Button Clicked");

		} catch (NoSuchElementException e) {
			log.debug("Logout Button Could not be Clicked");
			e.printStackTrace();
		}

	}

	@Then("^Error Message as Expected must be Displayed$")
	public void error_Message_as_Expected_must_be_Displayed(String text) throws Throwable {
		FVpage fv = new FVpage(driver);
		try {
			driver.findElement(fv.errorOnLogin).getText();
			log.debug("Asserting  Error Message :" + driver.findElement(fv.errorOnLogin).getText());
			Assert.assertTrue(driver.findElement(fv.errorOnLogin).getText().equalsIgnoreCase(text));
			fv.getScreenshot(driver);

			log.debug("Expected Error Message Dispalyed");
		} catch (Exception e) {
			log.debug("Expected Error Message Not Dispalyed");
			e.printStackTrace();
		}
	}

	@Then("^After the user closes the browser and launches again$")
	public void after_the_user_closes_the_browser_and_launches_again() throws Throwable {
		try {
			driver.quit();
			this.browserLaunch();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Then("^Session should not be persisted$")
	public void session_should_not_be_persisted() throws Throwable {

		FVpage fv = new FVpage(driver);
		try {
			(new WebDriverWait(driver, 50)).until(ExpectedConditions.elementToBeClickable(fv.FVlogo));
			if (fv.getEmail().isDisplayed()) {
				log.debug("Verified User is prompted to Login again");
				fv.getScreenshot(driver);
	
			}
		} catch (Exception e) {

			e.printStackTrace();
		}

	}

	@When("^User Clicks on Name Under Profile$")
	public void user_Clicks_on_Name_Under_Profile() throws Throwable {

		FVpage fv = new FVpage(driver);
		List<WebElement> sidescroll = driver.findElements(fv.dashboard);

		try {

			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("window.scrollTo(0, Math.max(document.documentElement.scrollHeight, document.body.scrollHeight, document.documentElement.clientHeight));");
			js.executeScript("arguments[0].click();", sidescroll.get(0));

			WebElement name = driver.findElement(fv.username);

			JavascriptExecutor jsb = (JavascriptExecutor) driver;
			jsb.executeScript("arguments[0].click();", name);
			log.debug("Name Selected");

		} catch (NoSuchElementException e) {
			log.debug("Name Not Selected");
			e.printStackTrace();
		}
	}

	@When("^User Updates First Name$")
	public void user_Updates_First_Name(DataTable firstname) throws Throwable {
		FVpage fv = new FVpage(driver);
		(new WebDriverWait(driver, 50)).until(ExpectedConditions.presenceOfElementLocated(fv.FName));

		if (driver.findElement(fv.LName).isDisplayed() && driver.findElement(fv.LName).isEnabled()) {
			Thread.sleep(2000);
			driver.findElement(fv.FName).clear();
			List<List<String>> data = firstname.raw();

			try {
				fv.enterFName(data.get(0).get(1).toString());
				fname = data.get(0).get(1).toString();
				log.debug("First Name Entered : ");
				for (int i = 0; i < data.size(); i++) {
					log.debug(data.get(i).toString());
				}

			} catch (Exception e) {
				log.debug("First Name Could not be Entered");
				e.printStackTrace();
			}
		}
	}

	@When("^User Updates Last Name$")
	public void user_Updates_Last_Name(DataTable lastname) throws Throwable {
		FVpage fv = new FVpage(driver);
		(new WebDriverWait(driver, 50)).until(ExpectedConditions.presenceOfElementLocated(fv.LName));

		if (driver.findElement(fv.LName).isDisplayed() && driver.findElement(fv.LName).isEnabled()) {
			Thread.sleep(2000);
			driver.findElement(fv.LName).clear();
			List<List<String>> data = lastname.raw();
			lname = data.get(0).get(1).toString();
			try {
				fv.enterLName(data.get(0).get(1).toString());

				log.debug("Last Name Entered : ");
				for (int i = 0; i < data.size(); i++) {
					log.debug(data.get(i).toString());
				}

			} catch (Exception e) {
				log.debug("Last Name Could not be Entered");
				e.printStackTrace();
			}
		}

	}

	@When("^Clicks Done$")
	public void clicks_Done() throws Throwable {

		FVpage fv = new FVpage(driver);
		try {
			driver.findElement(fv.DoneButton).click();
			log.debug("Done Button Clicked");
		} catch (Exception e) {
			log.debug("Done Button Could not be Clicked");
			e.printStackTrace();
		}

	}

	@Then("^Profile Name should reflect the Update$")
	public void profile_Name_should_reflect_the_Update() throws Throwable {

		FVpage fv = new FVpage(driver);
		try {
			(new WebDriverWait(driver, 50)).until(ExpectedConditions.presenceOfElementLocated(fv.User));
			Thread.sleep(3000);
			fv.getScreenshot(driver);

			log.debug("User Name on Home Page : " + driver.findElement(fv.User).getText());
			Assert.assertTrue(driver.findElement(fv.User).getText().equalsIgnoreCase(fname + " " + lname));
		} catch (Exception e) {
			log.debug("Home Page Not Loaded");
			e.printStackTrace();
		}
	}

	@Then("^Profile Name should reflect the Previous Update$")
	public void profile_Name_should_reflect_the_Previous_Update() throws Throwable {

		FVpage fv = new FVpage(driver);
		try {
			(new WebDriverWait(driver, 50)).until(ExpectedConditions.presenceOfElementLocated(fv.User));
			Thread.sleep(3000);
			fv.getScreenshot(driver);

			log.debug("User Name on Home Page : " + driver.findElement(fv.User).getText());
			Assert.assertTrue(driver.findElement(fv.User).getText().equalsIgnoreCase(fname + " " + lname));
		} catch (Exception e) {
			log.debug("Home Page Not Loaded");
			e.printStackTrace();
		}
	}

	@When("^User Selects the First Child$")
	public void user_Selects_the_First_Child() throws Throwable {
		FVpage fv = new FVpage(driver);
		try {

			List<WebElement> sidescroll = driver.findElements(fv.dashboard);
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].click();", sidescroll.get(0));
			WebElement name = driver.findElement(fv.student);

			JavascriptExecutor jsc = (JavascriptExecutor) driver;
			jsc.executeScript("arguments[0].click();", name);
			log.debug("First Student Selected");

		} catch (NoSuchElementException e) {
			log.debug("Student Not Selected");
			e.printStackTrace();
		}

	}

	@Then("^User then sets a time based notification from their first stop\\.$")
	public void user_then_sets_a_time_based_notification_from_their_first_stop(DataTable timeinput) throws Throwable {
		FVpage fv = new FVpage(driver);
		(new WebDriverWait(driver, 50)).until(ExpectedConditions.presenceOfElementLocated(fv.lister));
		Thread.sleep(2000);
		WebElement name = driver.findElement(fv.notification);
		JavascriptExecutor jsc = (JavascriptExecutor) driver;
		jsc.executeScript("arguments[0].click();", name);
		log.debug("Time Based Notification Screen launched ");

		driver.switchTo().activeElement();

		List<List<String>> data = timeinput.raw();
		time = data.get(0).get(1).toString();
		if (time.equalsIgnoreCase("five")) {

			WebElement value = driver.findElement(fv.five);
			JavascriptExecutor jsb = (JavascriptExecutor) driver;
			jsb.executeScript("arguments[0].click();", value);
			log.debug("Time Based Notification set for : " + time + " minutes");
		} else if (time.equalsIgnoreCase("ten")) {
			WebElement value = driver.findElement(fv.ten);
			JavascriptExecutor jsb = (JavascriptExecutor) driver;
			jsb.executeScript("arguments[0].click();", value);
			log.debug("Time Based Notification set for : " + time + " minutes");
		} else if (time.equalsIgnoreCase("none")) {
			WebElement value = driver.findElement(fv.none);
			JavascriptExecutor jsb = (JavascriptExecutor) driver;
			jsb.executeScript("arguments[0].click();", value);
			log.debug("Time Based Notification set to : None");
		}

	}

	@Then("^User Clicks Done to Complete the setup$")
	public void user_Clicks_Done_to_Complete_the_setup() throws Throwable {
		FVpage fv = new FVpage(driver);
		try {
			driver.findElement(fv.DoneButtonTime).click();
			log.debug("Notification Setup Completed");
		} catch (Exception e) {
			log.debug("Done Button Could not be Clicked");
			e.printStackTrace();
		}
	}

	@When("^the User Clicks on Time Notification$")
	public void the_User_Clicks_on_Time_Notification() throws Throwable {
		FVpage fv = new FVpage(driver);
		Thread.sleep(2000);
		(new WebDriverWait(driver, 50)).until(ExpectedConditions.presenceOfElementLocated(fv.lister));
		WebElement name = driver.findElement(fv.notification);
		JavascriptExecutor jsc = (JavascriptExecutor) driver;
		jsc.executeScript("arguments[0].click();", name);
		log.debug("Time Based Notification Screen launched ");

		driver.switchTo().activeElement();
	}

	@Then("^The RadioButton Corresponding to the Time Based Notification set should be Selected$")
	public void the_RadioButton_Corresponding_to_the_Time_Based_Notification_set_should_be_Selected() throws Throwable {
		//the class attribute of the div is set to "icon icon-dot-circle-o" from  "icon icon-circle-o"
		try {
		FVpage fv = new FVpage(driver);
		if (time.equalsIgnoreCase("five")) {
					
			String selected = driver.findElement(fv.five).getAttribute("class");
			Assert.assertTrue(selected.equalsIgnoreCase("icon icon-dot-circle-o"));
			
		}
		else if (time.equalsIgnoreCase("ten")) {
			
			String selected = driver.findElement(fv.ten).getAttribute("class");
			Assert.assertTrue(selected.equalsIgnoreCase("icon icon-dot-circle-o"));
		}
		else if (time.equalsIgnoreCase("none")) {
			
			String selected = driver.findElement(fv.none).getAttribute("class");
			Assert.assertTrue(selected.equalsIgnoreCase("icon icon-dot-circle-o"));
		}
		fv.getScreenshot(driver);
		log.debug("Selected Radio Button Verified  : " + time);
		}
		catch(NoSuchElementException e) {
			log.debug("Radio Button Not Verified");
			e.printStackTrace();
		}
		
		
	}

	@When("^User Selects Map$")
	public void user_Selects_Map() throws Throwable {
		FVpage fv = new FVpage(driver);

		List<WebElement> sidescroll = driver.findElements(By.xpath("//nav[@class='menu-dashboard']/ul/li/a"));

		try {

			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("window.scrollTo(0, Math.max(document.documentElement.scrollHeight, document.body.scrollHeight, document.documentElement.clientHeight));");
			js.executeScript("arguments[0].click();", sidescroll.get(1));
			log.debug("Map Loaded");

		} catch (NoSuchElementException e) {
			log.debug("Map Not Loaded");
			e.printStackTrace();
		}
	}

	@Then("^Map should load without Errros$")
	public void map_should_load_without_Errros() throws Throwable {
		Thread.sleep(2000);
		FVpage fv = new FVpage(driver);
		try {
			(new WebDriverWait(driver, 50)).until(ExpectedConditions.presenceOfElementLocated(fv.zoomin));
			WebElement zoomin =driver.findElement(fv.zoomin);
			JavascriptExecutor jsc = (JavascriptExecutor) driver;
			Thread.sleep(2000);
			fv.getScreenshot(driver);

			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	
	}
	
	@Then("^User should be able to zoom in on the map$")
	public void user_should_be_able_to_zoom_in_on_the_map() throws Throwable {
	  
		Thread.sleep(2000);
		FVpage fv = new FVpage(driver);
		try {
		(new WebDriverWait(driver, 50)).until(ExpectedConditions.presenceOfElementLocated(fv.zoomin));
		WebElement zoomin =driver.findElement(fv.zoomin);
		JavascriptExecutor jsc = (JavascriptExecutor) driver;
		jsc.executeScript("arguments[0].click();", zoomin);
		JavascriptExecutor jsd = (JavascriptExecutor) driver;
		jsd.executeScript("arguments[0].click();", zoomin);
		
		log.debug("Zoom in is Working");
		Thread.sleep(2000);
		fv.getScreenshot(driver);
		}
		catch(NoSuchElementException e) {
			log.debug("Zoom Not Working");
			e.printStackTrace();
		}
	}

	@Then("^User should be able to zoom out on the map$")
	public void user_should_be_able_to_zoom_out_on_the_map() throws Throwable {
		Thread.sleep(2000);
		FVpage fv = new FVpage(driver);
		try {
		(new WebDriverWait(driver, 50)).until(ExpectedConditions.presenceOfElementLocated(fv.zoomout));
		WebElement zoomout =driver.findElement(fv.zoomout);
		JavascriptExecutor jsc = (JavascriptExecutor) driver;
		jsc.executeScript("arguments[0].click();", zoomout);
		JavascriptExecutor jsd = (JavascriptExecutor) driver;
		jsc.executeScript("arguments[0].click();", zoomout);
		log.debug("zoom out is working");
		Thread.sleep(2000);
		fv.getScreenshot(driver);
	}
		catch(NoSuchElementException e) {
			log.debug("Zoom Not Working");
			e.printStackTrace();
		}
	}

}
