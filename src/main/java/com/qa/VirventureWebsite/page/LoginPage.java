package com.qa.VirventureWebsite.page;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qa.Base.TestBase;
import com.qa.util.JavaScriptUtil;

public class LoginPage extends TestBase{
	
	public LoginPage() {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//h2[normalize-space()='Account Login']")
	public WebElement loginPageLable;
	@FindBy(xpath="//a[normalize-space()='Submit']")
	public WebElement loginpageSubmitBtn;
	@FindBy(xpath="//input[@name='email']")
	public WebElement txtEmail;
	@FindBy(xpath="//input[@name='password']")
	public WebElement txtPassword;
	@FindBy(xpath="//input[@value='Login']")
	public WebElement ClickLoginBtn;
	@FindBy(xpath="//div[@class='warning']")
	public WebElement warningtext;
	@FindBy(xpath="//div[@id='welcome']//a[normalize-space()='Logout']")
	public WebElement logoutBtn;
	//--------------Under LoginPage----------------
	
	
	//---------------------------------------------------------------------
	public String validateTitle() {
		return driver.getTitle();
	}
	public boolean verifyLoginPageLable() {
		JavaScriptUtil.drawBorder(loginPageLable, driver);
		return loginPageLable.isDisplayed();
	}
	
	public void validateLoginEmptyData() {
		ClickLoginBtn.click();
		
	}
	
	public void setLoginValidData(String email, String pwd) {
		txtEmail.sendKeys(email,Keys.TAB);
		txtPassword.sendKeys(pwd,Keys.TAB);
		ClickLoginBtn.click();
		WebElement elename=driver.findElement(By.xpath("//div[@id='welcome']//a[text()='Mohammed Irfan']"));
		String ValidUserID=driver.findElement(By.xpath("//div[@id='welcome']//a[text()='Mohammed Irfan']")).getText();
		JavaScriptUtil.drawBorder(elename, driver);
		logoutBtn.click();
	}
}
