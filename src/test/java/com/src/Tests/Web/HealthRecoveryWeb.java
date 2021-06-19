package com.src.Tests.Web;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.src.Pages.Web.LoginPage;
import com.src.base.Driver;

public class HealthRecoveryWeb {
	
	WebDriver driver = null;
	Properties prop;
	String browserName;
	String baseUrl;
	
	
	@BeforeSuite
	public void setUp() throws IOException {
		readProperty();
		baseUrl = prop.getProperty("baseUrl");
		
		System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
		driver = new ChromeDriver();
		
		Driver.setDriver(driver);
		driver.get(baseUrl);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	}
	
	@Test
	public void verifyLoginPageIsDisplayed() throws InterruptedException {
		
		// Login to Health Recovery Solutions
		LoginPage homePage = new LoginPage(driver);
		
		homePage.verifyLoginPageIconDisplayed();
		
	}
	
	@Test
	public void verifyInvalidCredentials() {
		LoginPage homePage = new LoginPage(driver);
		
		homePage.verifyLoginPageIconDisplayed();
		homePage.doLogin("cjadhav@gmail.com", "Abc@123");
		homePage.verifyInvalidCrendialMessage();
	}
	
	
	@AfterSuite
	public void tearDown() {
		driver.close();
	}
	
	public void readProperty() throws IOException {
		FileReader reader=new FileReader("Config.properties");
		prop=new Properties();
		prop.load(reader);
	}
	
}
