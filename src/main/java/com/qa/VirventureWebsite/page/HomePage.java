package com.qa.VirventureWebsite.page;


import java.util.ArrayList;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.Base.TestBase;
import com.qa.util.JavaScriptUtil;

public class HomePage extends TestBase{
	public HomePage() {
		PageFactory.initElements(driver, this);
	}
	
	//Action
	@FindBy(xpath="//div[@id='logo']")
	public WebElement VirventureWebsiteLogo;
	@FindBy(xpath="//div[@id='welcome']//a[text()='login']")
	public WebElement headerLoginBtn;
	@FindBy(xpath="//div[@id='welcome']//a[normalize-space()='create an account']")
	public WebElement headerCreateAccountBtn;
	@FindBy(xpath="//a[@id='wishlist-total']")
	public WebElement headerWishListBtn;
	@FindBy(xpath="//div[@class='top-links']//a[normalize-space()='My Account']")
	public WebElement headerMyAccountBtn;
	@FindBy(xpath="//div[@class='top-links']//a[normalize-space()='Cart']")
	public WebElement headerCartBtn;
	@FindBy(xpath="//div[@class='top-links']//a[normalize-space()='Checkout']")
	public WebElement headerCheckOutBtn;
	@FindBy(xpath="//a[normalize-space()='Track Your Order']")
	public WebElement headerTrackOrderBtn;
	@FindBy(xpath="//a[normalize-space()='Customer Say']")
	public WebElement headercustomerSayBtn;
	@FindBy(xpath="//a[@href='/contactus']")
	public WebElement headerContactUsBtn;
	
	
	//---Submit Btn
	@FindBy(xpath="//a[normalize-space()='Submit']")
	public WebElement SubmitBtn;
	//SearcButton
	@FindBy(xpath="//input[@id='custom_change1']")
	public WebElement txtSearchBar;
	@FindBy(xpath="//div[@id='search_click']//i[@class='fa fa-search']")
	public WebElement clickSearchBtn;
	
	//---Social Media
	@FindBy(xpath="//a[normalize-space()='facebook']")
	public WebElement facebookClick;
	@FindBy(xpath="//a[normalize-space()='Twitter']")
	public WebElement twitterClick;
	@FindBy(xpath="//a[normalize-space()='Youtube']")
	public WebElement youtubeClick;
	@FindBy(xpath="//a[normalize-space()='Instagram']")
	public WebElement instagramClick;
	@FindBy(xpath="//a[normalize-space()='Pinterest']")
	public WebElement pinterestClick;
	
	//INFORMATION---
	@FindBy(xpath="//a[normalize-space()='About Us']")
	public WebElement AboutUs;
	@FindBy(xpath="//a[normalize-space()='Delivery Information']")
	public WebElement DeliveryInfo;
	@FindBy(xpath="//a[normalize-space()='Privacy Policy']")
	public WebElement PrivacyPolicy;
	@FindBy(xpath="//a[normalize-space()='Terms & Conditions']")
	public WebElement TermCondotion;
	@FindBy(xpath="//a[normalize-space()='Return Policy']")
	public WebElement ReturnPolicy;
	@FindBy(xpath="//a[normalize-space()='Damage Goods Policy']")
	public WebElement DamageGoodsPolicy;
	@FindBy(xpath="//a[normalize-space()='Our Blog']")
	public WebElement OurBlog;
	@FindBy(xpath="//a[@href='https://virventures.com/index.php?route=information/contact']")
	public WebElement ContactUs;
	@FindBy(xpath="//a[normalize-space()='Returns']")
	public WebElement Return;
	@FindBy(xpath="//a[normalize-space()='Site Map']")
	public WebElement SiteMap;
	
	//---MY-ACCOUNT---
	@FindBy(xpath="//div[@class='column span3 noborder column5']//a[normalize-space()='My Account']")
	public WebElement MyAccount;
	@FindBy(xpath="//a[normalize-space()='Order History']")
	public WebElement OrderHistory;
	@FindBy(xpath="//a[normalize-space()='Wish List']")
	public WebElement WishList;
	@FindBy(xpath="//a[normalize-space()='Newsletter']")
	public WebElement NewsLetter;
	@FindBy(xpath="//a[normalize-space()='Brands']")
	public WebElement Brands;
	@FindBy(xpath="//a[normalize-space()='Specials']")
	public WebElement Specials;
	//-----------------------------------
	
	//----------------WHAT'S TRADING----
	@FindBy(xpath="//div[@id='module47']//div[@class='box-heading']")
	public WebElement whatstradingLable;
	@FindBy(xpath="//div[@id='productcarousel47']//a[@class='carousel-control left'][contains(text(),'‹')]")
	public WebElement whattradingLeftClick;
	@FindBy(xpath="//div[@id='productcarousel47']//a[@class='carousel-control right'][contains(text(),'›')]")
	public WebElement whattradingRightClick;
	
	//--------------Fast Moving Products
	@FindBy(xpath="//h2[normalize-space()='Fast Moving Products']")
	public WebElement FastMovingLable;
	
	@FindBy(xpath="//div[@class='image']//img[@alt='Deluxe Fire Fighter Dress Up Costume Set - Medium 8-10']")
	public WebElement FastMovingProduct;
	
	//Perform
	public String validateTitle() {
		return driver.getTitle();
	}
	public boolean validateLable() {
		JavaScriptUtil.drawBorder(VirventureWebsiteLogo, driver);
		return VirventureWebsiteLogo.isDisplayed();
	}
	public LoginPage validateLoginLink() {
		headerLoginBtn.click();
		return new LoginPage();
	}
	
	public RegisterAccountPage validateCreateAccountLink() {
		headerCreateAccountBtn.click();
		return new RegisterAccountPage();
	}
	
	public void validateWishListLink(){
		headerWishListBtn.click();	
	}
	public MyAccountPage validateMyAccountLink() {
		headerMyAccountBtn.click();
		return new MyAccountPage();
	}
	
	public void validateCartLink() {
		headerCartBtn.click();
		
	}
	public TrackOrderPage validateTrackOrder() {
		headerTrackOrderBtn.click();
		return new TrackOrderPage();
	}
	public void validateCustomnersayLink() {
		headercustomerSayBtn.click();
	}
	public ContactUsPage validateContactusLink() {
		headerContactUsBtn.click();
		return new ContactUsPage();
	}
	
	public void validateSearchBox() {
		String product="800ML Paint Spray Painter 650W Oil Primer Water";
		txtSearchBar.sendKeys(product);
		clickSearchBtn.click();
		WebDriverWait wait= new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h2[text()='Products meeting the search criteria']")));
		WebElement element=driver.findElement(By.xpath("//h2[text()='Products meeting the search criteria']"));
		JavaScriptUtil.scrolluptoanelementByJS(element, driver);
	}
	public ForgotPasswordPage validateForgotPage() throws InterruptedException {
		headerLoginBtn.click();
		driver.findElement(By.xpath("//div[@class='content']//a[normalize-space()='Forgot Your Password?']")).click();
		Thread.sleep(2000);
		WebElement element= driver.findElement(By.xpath("//h2[normalize-space()='Forgot Your Password?']"));
		JavaScriptUtil.drawBorder(element, driver);
		return new ForgotPasswordPage();
	}
	
	
	public void validateFooterStayConnected() throws InterruptedException {
		String parentWindowID=driver.getWindowHandle();
		System.out.println("The Parent window Id is===>" + parentWindowID);
		
		facebookClick.click();
		Set<String>facebookwindows=driver.getWindowHandles();
		int fcount= facebookwindows.size();
		System.out.println("Totla number of fcount windows are==>"+ fcount);
		ArrayList<String> ftabs= new ArrayList(facebookwindows);
		driver.switchTo().window(ftabs.get(1));
		Thread.sleep(3000);
		driver.close();
		driver.switchTo().window(ftabs.get(0));
		
		twitterClick.click();
		Set<String> twitterWindows=driver.getWindowHandles();
		int tcount= twitterWindows.size();
		System.out.println("Totla number of tcount windows are==>"+ tcount);
		ArrayList<String>ttabs= new ArrayList<String>(twitterWindows);
		driver.switchTo().window(ttabs.get(1));
		Thread.sleep(3000);
		driver.close();
		driver.switchTo().window(ttabs.get(0));
		
		youtubeClick.click();
		Set<String> youtubeWindows=driver.getWindowHandles();
		int ycount= youtubeWindows.size();
		System.out.println("Totla number of ycount windows are==>"+ ycount);
		ArrayList<String>ytabs= new ArrayList<String>(youtubeWindows);
		driver.switchTo().window(ytabs.get(1));
		Thread.sleep(3000);
		driver.close();
		driver.switchTo().window(ytabs.get(0));
		
		instagramClick.click();
		Set<String> instaWindows=driver.getWindowHandles();
		int icount= instaWindows.size();
		System.out.println("Totla number of icount windows are==>"+ icount);
		ArrayList<String>itabs= new ArrayList<String>(instaWindows);
		driver.switchTo().window(itabs.get(1));
		Thread.sleep(3000);
		driver.close();
		driver.switchTo().window(itabs.get(0));
		
		pinterestClick.click();
		Set<String> pinWindows=driver.getWindowHandles();
		int pcount= pinWindows.size();
		System.out.println("Totla number of pcount windows are==>"+ pcount);
		ArrayList<String>ptabs= new ArrayList<String>(instaWindows);
		driver.switchTo().window(ptabs.get(1));
		Thread.sleep(3000);
		driver.close();
		driver.switchTo().window(ptabs.get(0));
	}
	public void validateFastMovingProducts() {
		JavaScriptUtil.drawBorder(FastMovingLable, driver);
		FastMovingProduct.click();
		driver.findElement(By.xpath("//input[@id='button-cart']")).click();
	}
	public void scrollupToInfo() {
		WebElement element= driver.findElement(By.xpath("//h3[normalize-space()='Information']"));
		JavaScriptUtil.scrolluptoanelementByJS(element, driver);	
	}
	
}
