package com.src.Pages.Web;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.src.base.Driver;

public class LoginPage {
	
	@FindBy(name="username")
	public WebElement usernameTextbox;
	
	@FindBy(name="password")
	public WebElement passwordTextbox;
	
	@FindBy(xpath="//button[@id='loginSubmitButton']")
	public WebElement SignInButton;
	
	@FindBy(id="loginHeaderLogo")
	public WebElement loginHeaderLogo;
	
	@FindBy(xpath="//h3[contains(.,'Login')]")
	public WebElement loginText;
	
	@FindBy(xpath="//span[text()='Username and/or password invalid']")
	public WebElement invalidUsernamePassword;
	
	public LoginPage(WebDriver driver) {
		PageFactory.initElements(Driver.getDriver(), LoginPage.class);
	}
	
	public void doLogin(String uname, String password) {
		usernameTextbox.sendKeys(uname);
		passwordTextbox.sendKeys(password);
		SignInButton.click();
	}
	
	public void verifyInvalidCrendialMessage() {
		
		if(invalidUsernamePassword.isDisplayed()) {
			Assert.assertEquals(invalidUsernamePassword.getText(), "Username and/or password invalid");
		} else {
			Assert.fail("Login successful");
		}
	}
	
	public void verifyLoginPageIconDisplayed() {
		
		WebDriverWait wait = new WebDriverWait(Driver.getDriver(),50);
		wait.until(ExpectedConditions.elementToBeClickable(loginHeaderLogo));
		
		if(loginText.isDisplayed() && loginHeaderLogo.isDisplayed()){
			Assert.assertEquals(loginText.getText(), "Login");
		} else {
			Assert.fail("Unable to display Login Page");
		}
		
	}
	

}
