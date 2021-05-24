package com.qa.VirventureWebsite.page;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qa.Base.TestBase;

public class ContactUsPage extends TestBase {
	public ContactUsPage() {
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath="//h1[normalize-space()='Contact Us']")
	public WebElement contactusLable;
	@FindBy(xpath="//input[@name='name']")
	public WebElement txtName;
	@FindBy(xpath="//input[@name='email']")
	public WebElement txtEmail;
	@FindBy(xpath="//textarea[@name='enquiry']")
	public WebElement txtEnquiry;
	@FindBy(xpath="//input[@value='SUBMIT']")
	public WebElement submitBtn;
	@FindBy(xpath="//input[@name='captcha']")
	public WebElement txtCaptcha;
	
	//--------Waarning message------------------
	@FindBy(xpath="//span[normalize-space()='Name must be between 3 and 33 characters!']")
	public WebElement nameWarning;
	@FindBy(xpath="//span[normalize-space()='E-Mail Address does not appear to be valid!']")
	public WebElement EmailWarning;
	@FindBy(xpath="//span[normalize-space()='Enquiry must be between 10 and 3000 characters!']")
	public WebElement EnquiryWarning;
	@FindBy(xpath="//span[contains(text(),'Verification code does not match the image!')]")
	public WebElement CaptchaWarning;
	//---------------------------------------------------
	
	public String validateTitle() {
		return driver.getTitle();
	}
	public boolean verifyLable() {
		return contactusLable.isDisplayed();
	}
	public void validateEmptyContactUsdata() {
		submitBtn.click();
	}
	
	public void setContactUsData(String name, String email, String enq) {
		txtName.sendKeys(name,Keys.TAB);
		txtEmail.sendKeys(email, Keys.TAB);
		txtEnquiry.sendKeys(enq,Keys.TAB);
		submitBtn.click();
	}
}
