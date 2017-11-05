package com.fv.pages;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import cucumber.api.Scenario;

public class FVpage {
	WebDriver driver;

	public FVpage(WebDriver driver) {
		this.driver = driver;
	}

	public By FVlogo = By.cssSelector("img[alt='first view logo']");
	public By Email = By.id("emailOrPhone");
	public By Password = By.id("password");
	public By Login = By.xpath("//span[contains(.,'Login')]");
	public By logout = By.linkText("Logout");
	public By skipButton = By.xpath("//button[contains(.,'Skip')]");
	public By User = By.xpath("//*[@id=\"app\"]/div/div/section/div/aside/div/div/p");
	public By errorOnLogin = By.cssSelector("p.dziilP");
    public By dashboard = By.xpath("//nav[@class='menu-dashboard']/ul/li/a");
	public By FName = By.id("firstName");
	public By LName = By.id("lastName");
	public By DoneButton = By.xpath("//button[contains(.,'Done')]");
	public By student =By.xpath("//*[@id=\"app\"]/div/div/section/div/section/div/div/ul[2]/li[1]/a/div/span");
	public By notification =By.xpath("//*[@id=\"app\"]/div/div/section/div/section/div/div/div[3]/div[2]/ul/li[2]/a");
	public By five =		By.xpath("/html/body/div[4]/div/div[2]/div/div[1]/div[2]/ul/li[2]/div[1]");
	public By ten =		By.xpath("/html/body/div[4]/div/div[2]/div/div[1]/div[2]/ul/li[3]/div[1]");
	public By none =		By.xpath("/html/body/div[4]/div/div[2]/div/div[1]/div[2]/ul/li[1]/div[1]");
	private Scenario scenario;
	public static String ScenarioName = null;
	public static int ScenarioID = 0;
	public By DoneButtonTime = By.xpath("//button[contains(.,'Done')]");
	
	public By username =By.xpath("//*[@id=\"app\"]/div/div/section/div/section/div/div/ul[1]/li[1]/a/div/span");

	public By lister = By.xpath("//*[@id=\"app\"]/div/div/section/div/section/div/div/div[3]/div[2]/ul/li[2]/a");
	public By zoomin = By.cssSelector("a[title='Zoom in']");
	public By zoomout = By.cssSelector("a[title='Zoom out']");
	public void setscenario(Scenario scenario) {
		this.scenario = scenario;
		ScenarioName = scenario.getName();
		ScenarioID++;
	}

	public WebElement getFVlogo() {

		WebElement a = driver.findElement(FVlogo);

		return a;
	}

	public WebElement getEmail() {
		WebElement a = driver.findElement(Email);
		return a;
	}

	public WebElement getPassword() {
		WebElement a = driver.findElement(Password);
		return a;
	}

	public WebElement getLogin() {
		WebElement a = driver.findElement(Login);
		return a;
	}

	public WebElement getskipButton() {

		WebElement a = (new WebDriverWait(driver, 10)).until(ExpectedConditions.elementToBeClickable(skipButton));

		return a;
	}

	public void enterEmail(String email) {
		driver.findElement(Email).clear();
		driver.findElement(Email).sendKeys(email);
	}

	public void enterPass(String pass) {
		driver.findElement(Password).clear();
		driver.findElement(Password).sendKeys(pass);
	}

	public void enterFName(String fname) {
		driver.findElement(FName).clear();
		driver.findElement(FName).sendKeys(fname);
	}

	public void enterLName(String lname) {
		driver.findElement(LName).clear();
		driver.findElement(LName).sendKeys(lname);
	}

	public void getScreenshot(WebDriver driver) throws Exception {
		String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		TakesScreenshot ts = ((TakesScreenshot) driver);
		
		File source = ts.getScreenshotAs(OutputType.FILE);

		File finalDestination = new File("./screenshots/" + ScenarioID + "_" + ScenarioName + ".jpeg");
		FileUtils.copyFile(source, finalDestination);

	}

}
