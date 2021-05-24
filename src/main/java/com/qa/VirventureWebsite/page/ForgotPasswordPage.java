package com.qa.VirventureWebsite.page;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qa.Base.TestBase;

public class ForgotPasswordPage extends TestBase{
	
	public ForgotPasswordPage() {
	PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//a[normalize-space()='Back']")
	public WebElement backBtn;
	@FindBy(xpath="//div[@class='content']//a[normalize-space()='Forgot Your Password?']")
	public WebElement clickForgotPasswordLink;
	@FindBy(xpath="//input[@name='email']")
	public WebElement txtEmail;
	@FindBy(xpath="//input[@type='submit']")
	public WebElement clickSubmitBtn;
	
	
	//Perfome:
	public String validateTitle() {
		return driver.getTitle();
	}
	public void validateBackBtnLink() throws InterruptedException {
		backBtn.click();
		Thread.sleep(2000);
		clickForgotPasswordLink.click();
	}
	public void setForgotPwdEmail(String email) throws IOException {
		txtEmail.sendKeys(email,Keys.TAB);
		clickSubmitBtn.click();
		File src=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(src, new File("D:\\IRFAN---\\java program\\VirventuresWebsiteAutomation_Framework\\ScreenShot\\ForgotPageValidate.png"));
	}
}
