	package com.qa.VirventureWebsite.page;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qa.Base.TestBase;

public class TrackOrderPage extends TestBase{

	public TrackOrderPage() {
	PageFactory.initElements(driver, this);
}	

	//Action---------------------
	@FindBy(xpath="//h1[normalize-space()='Track Your Order']")
	public WebElement trackorderLable;
	
	@FindBy(xpath="//input[@id='inputEmail']")
	public WebElement txttrackorderEmail;
	@FindBy(xpath="//input[@id='inputOrderId']")
	public WebElement txttrackorderID;
	@FindBy(xpath="//button[normalize-space()='Track Order']")
	public WebElement trackorderBtn;
	
	//----------warning
	@FindBy(xpath="//span[normalize-space()='Please enter a valid Email']")
	public WebElement warnEmail;
	@FindBy(xpath="//span[normalize-space()='Please enter a valid order ID']")
	public WebElement warnTrackOrderId;
	
	//-----Perform -Action
	public String validateTitle() {
	return driver.getTitle();
	}
	
	public boolean verifyPageLable() {
		return trackorderLable.isDisplayed();
	}
	
	public void submitEmptyData() throws InterruptedException {
		trackorderBtn.click();
		Thread.sleep(2000);
		System.out.println("The Eamil warning-->"+ warnEmail);
		System.out.println("The Eamil warning-->"+ warnTrackOrderId);
	}
	
	public void validateTrackOrder(String email,String orderid) {
		txttrackorderEmail.sendKeys(email,Keys.TAB);
		txttrackorderID.sendKeys(orderid,Keys.TAB);
		trackorderBtn.click();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
