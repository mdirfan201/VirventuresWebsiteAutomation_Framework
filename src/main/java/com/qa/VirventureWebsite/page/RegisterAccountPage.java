package com.qa.VirventureWebsite.page;

import java.awt.Checkbox;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.qa.Base.TestBase;

public class RegisterAccountPage extends TestBase{
	public RegisterAccountPage() {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//h2[normalize-space()='Register Account']")
	public WebElement RegisterAccountLable;
	//-----Your Personal Details
	//Action:--
	@FindBy(xpath="//input[@name='firstname']")
	public WebElement txtfirstname;
	//span[normalize-space()='First Name must be between 3 and 33 characters!']
	@FindBy(xpath="//input[@name='lastname']")
	public WebElement txtlastname;
	//span[normalize-space()='Last Name must be between 3 and 33 characters!']
	@FindBy(xpath="//input[@name='email']")
	public WebElement txtemail;
	//span[normalize-space()='E-Mail Address does not appear to be valid!']
	@FindBy(xpath="//input[@name='telephone']")
	public WebElement txtTelephone;
	//span[normalize-space()='Telephone must be between 10 and 15 numbers!']
	@FindBy(xpath="//input[@name='fax']")
	public WebElement txtfax;
	
	//------------Your Address---------
	@FindBy(xpath="//input[@name='address_1']")
	public WebElement txtAddress;
	//span[normalize-space()='Address 1 must be between 3 and 128 characters!'
	@FindBy(xpath="//input[@name='city']")
	public WebElement txtCity;
	//span[normalize-space()='City must be between 2 and 128 characters!']
	@FindBy(xpath="//input[@name='postcode']")
	public WebElement txtPostCode;
	//span[normalize-space()='Invalid Zip Code']
	@FindBy(xpath="//select[@name='country_id']")
	public WebElement Country;
	
	@FindBy(xpath="//select[@name='zone_id']")
	public WebElement Region;
	//span[normalize-space()='Please select a region / state!']
	
	//------------Your Password---------
	@FindBy(xpath="//input[@name='password']")
	public WebElement txtpassword;
	//span[normalize-space()='Password must be between 4 and 20 characters!']
	@FindBy(xpath="//input[@name='confirm']")
	public WebElement txtcfnpassword;
	
	//---------con
	@FindBy(xpath="//input[@name='agree']")
	public WebElement clickCheckbox;
	@FindBy(xpath="//input[@type='submit']")
	public WebElement submitBtn;
	
	//-----warning xpath action
	@FindBy(xpath="//div[@class='warning']")
	public WebElement warning;
	
	@FindBy(xpath="//span[normalize-space()='First Name must be between 3 and 33 characters!']")
	public WebElement warfirstname;
	@FindBy(xpath="//span[normalize-space()='Last Name must be between 3 and 33 characters!']")
	public WebElement warlastname;
	@FindBy(xpath="//span[normalize-space()='E-Mail Address does not appear to be valid!']")
	public WebElement waremail;
	@FindBy(xpath="//span[normalize-space()='Telephone must be between 10 and 15 numbers!']")
	public WebElement wartelephone;
	@FindBy(xpath="//span[normalize-space()='Address 1 must be between 3 and 128 characters!'")
	public WebElement waraddress;
	@FindBy(xpath="//span[normalize-space()='City must be between 2 and 128 characters!']")
	public WebElement warcity;
	@FindBy(xpath="//span[normalize-space()='Invalid Zip Code']")
	public WebElement warzipcode;
	@FindBy(xpath="//span[normalize-space()='Please select a region / state!']")
	public WebElement warState;
	@FindBy(xpath="//span[normalize-space()='Password must be between 4 and 20 characters!']")
	public WebElement warpassword;
	
	//---------------------------
	@FindBy(xpath="//h2[normalize-space()='Your Personal Details']")
	public WebElement YourPersonalDetails;
	@FindBy(xpath="//h2[normalize-space()='Your Address']")
	public WebElement YourAddressLable;
	@FindBy(xpath="//h2[normalize-space()='Your Password']")
	public WebElement YourPasswordLable;
	
	//=======================
	//Action--To--Perform
	public String validateTitle() {
		return driver.getTitle();
	}
	public boolean verifyPageLogo() {
		return RegisterAccountLable.isDisplayed();
	}
	public void validateWithEmptyData() {
		clickCheckbox.click();
		submitBtn.click();
	}
	public void validateDataFileds(String fname,String lname,String email, String telephone,String add, String city, String postcode,
			String cntry,String state, String pwd, String cnfpwd) {
		txtfirstname.sendKeys(fname, Keys.TAB);
		txtlastname.sendKeys(lname,Keys.TAB);
		txtemail.sendKeys(email,Keys.TAB);
		txtTelephone.sendKeys(telephone,Keys.TAB);
		
		txtAddress.sendKeys(add,Keys.TAB);
		txtCity.sendKeys(city,Keys.TAB);
		txtPostCode.sendKeys(postcode,Keys.TAB);
		Select selCntry= new Select(Country);
		selCntry.selectByVisibleText(cntry);
		Select selRegion=new Select(Region);
		selRegion.selectByVisibleText(state);
		
		txtpassword.sendKeys(pwd,Keys.TAB);
		txtcfnpassword.sendKeys(cnfpwd,Keys.TAB);
		
		clickCheckbox.click();
		submitBtn.click();
	}
	
}
