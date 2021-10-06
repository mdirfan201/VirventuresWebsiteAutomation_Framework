package com.qa.VirventureWebsite.TestCases;

/**
 * @author Mohammed Irfan
 */
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.qa.Base.TestBase;
import com.qa.CustomeListener.CustomeListner;
import com.qa.VirventureWebsite.page.HomePage;

@Listeners(CustomeListner.class)
public class HomePageTest extends TestBase {
	static HomePage homepage;
	
	public static ExtentReports extent;
	public static ExtentSparkReporter spark;
	public static ExtentTest test;
	public HomePageTest() {
		super();
	}
	
	@BeforeSuite()
	public void setupExtentReport() {
		extent= new ExtentReports();
		spark= new ExtentSparkReporter("D:\\IRFAN---\\java program\\VirventuresWebsiteAutomation_Framework\\Extent-Report\\VirventureWebsite-HomePage.html");
		spark.config().setTheme(Theme.DARK);
		spark.config().setDocumentTitle("Furniture-Stores Automation");
		spark.config().setReportName("Mohammed Irfan Ansari");
		extent.attachReporter(spark);

		//Setting Environment
		extent.setSystemInfo("Author","Mohammed Irfan");
		extent.setSystemInfo("Environment","QA");
		extent.setSystemInfo("System","Windows10");
		extent.setSystemInfo("Applicatoin","Eclipse");
		extent.setSystemInfo("Tools","Selenium With Java");
	}
	@AfterSuite
	public void tearExtentReport() {
		extent.flush();
	}
	@BeforeMethod
	public void setup() {
		initialization();
		homepage = new HomePage();
	}
	@Test(priority=1)
	public void verifyHomePageTitle() {
		test=extent.createTest("TC_01: HOMEPAGE-VerifyHomePageTitle");
		System.out.println("The actual Title is====>" + driver.getTitle());
		test.info("Current Page title is====>"+driver.getTitle());
		String homeTitle=homepage.validateTitle();
		String expected_Title="Buy Automotive & Industrial, Beauty & Health Care, Kids Furniture, Books & Audible, Cell Phones & Accessories,Clothing, Shoes & Jewelry - virventures.com";
		Assert.assertEquals(homeTitle, expected_Title,"HomePage Title not Matched");
		test.pass("Actual and Expected Title is Equal and Test is Pass");
	}
	@Test(priority=2)
	public void verifyingHomePageLogoTest(){
		test=extent.createTest("TC_02 : HOMEPAGE-VerifyHomePageLogo");
		boolean flag=homepage.validateLable();
		Assert.assertTrue(flag);
		test.pass("The Logo is Displayed is ErgoedBook Website");
	}
	@Test(priority=3)
	public void validateHomePageLoginBtnTest() {
		test=extent.createTest("TC_03: HOMEPAGE-validate HomePage LoginBtn Test");
		homepage.validateLoginLink();
	}
	@Test(priority=4)
	public void validateHomePageCreateAccountBtnTest() {
		test=extent.createTest("TC_04: HOMEPAGE-validate HomePage CreateAccountBtn Test");
		homepage.validateCreateAccountLink();
	}
	
	@Test(priority=5)
	public void validateHomePageWishListBtnTest() {
		test=extent.createTest("TC_05: HOMEPAGE-validate HomePage WishListBtn Test");
		homepage.validateWishListLink();
		test.info("WishList-Page");
	}
	@Test(priority=6)
	public void validateHomePageMyAccountLinkTest() {
		test=extent.createTest("TC_06: HOMEPAGE-validate HomePage MyAccountLink Test");
		homepage.validateMyAccountLink();
		test.info("MyAccountLink-Page");
	}
	
	@Test(priority=7)
	public void validateHomePageCartLinkTest() {
		test=extent.createTest("TC_07: HOMEPAGE-validate HomePage CartLink Test");
		homepage.validateCartLink();
		test.info("CartLink-Page");	
	}
	@Test(priority=8)
	public void validateHomePageTrackOrderLinkTest() {
		test=extent.createTest("TC_08: HOMEPAGE-validate HomePage TrackOrderLink Test");
		homepage.validateTrackOrder();
		test.info("TrackOrderLink-Page");	
	}
	
	@Test(priority=9)
	public void validateHomePageCustomerSayLinkTest() {
		test=extent.createTest("TC_09: HOMEPAGE-validate HomePage CustomerSayLink Test");
		homepage.validateCustomnersayLink();
		test.info("CustomerSayLink-Page");
	}
	
	@Test(priority=10)
	public void validateHomePageContactUsLinkTest() {
		test=extent.createTest("TC_10: HOMEPAGE-validate HomePage ContactUsLink Test");
		homepage.validateContactusLink();
		test.info("ContactUsLink-Page");
	}
	@Test(priority=11)
	public void validateSearchBtnEmptyTest() throws InterruptedException {
		test=extent.createTest("TC_11: HOMEPAGE-validate SearchBtn Empty Test");
		homepage.clickSearchBtn.click();
		Thread.sleep(1000);
		String text=driver.findElement(By.xpath("//*[@id=\"cboxLoadedContent\"]/div/div/h4/b")).getText();
		System.out.println(text);
		test.pass("The msg is===>"+text, MediaEntityBuilder.createScreenCaptureFromBase64String(getBase64ScreenShot()).build());
		
		driver.findElement(By.xpath("//div[@id='cboxClose']")).click();
		Thread.sleep(3000);
	}
	@Test(priority=12)
	public void validateSearchBtnWithValidDataTest() {
		test=extent.createTest("TC_12: HOMEPAGE-validate SearchBtn With ValidData Test");
		homepage.validateSearchBox();
	}
	
	@Test(priority=13)
	public void validateForgotPageLinkTest() throws InterruptedException {
		test=extent.createTest("TC_13: HOMEPAGE-validate ForgotPage Link Test");
		homepage.validateForgotPage();
		
	}

	@Test(priority=14)
	public void validateHomePageBannerLinkTest() {
		test=extent.createTest("TC_14: HOMEPAGE-validate HomePage Banner Link Test");
		ValidateCarouselInnerBanner();
	}
	
	@Test(priority=15)
	public void validateFastMovingProductsLinkTes() {
		test=extent.createTest("TC_14: HOMEPAGE-validate FastMovingProducts Link Test");
		homepage.validateFastMovingProducts();
		driver.findElement(By.xpath("//input[@id='use_coupon']")).click();
		driver.findElement(By.xpath("//input[@name='coupon']")).sendKeys("ABCDELX-2021");
		driver.findElement(By.xpath("//input[@value='Apply Coupon']")).click();
		test.info("The waring msg foe cupon is=="+ driver.findElement(By.xpath("//div[@class='warning']")).getText());
		driver.findElement(By.xpath("//a[@class='button'][normalize-space()='Checkout']")).click();
		
		
	}
	@Test(priority=16)
	public void validateHomePageFooterSocialMediaLinkTest() throws InterruptedException {
		test=extent.createTest("TC_16: HOMEPAGE-validate HomePage Footer-SocialMedia Link Test");
		homepage.validateFooterStayConnected();
	}
	@Test(priority=17)
	public void validateHomePageFooterUnderInformatioLinkTest() throws InterruptedException {
		test=extent.createTest("TC_17: HOMEPAGE-validate HomePage Footer-Under Informatio Link Test");
		validateFooterLinkUnderInformation();
	}
	@Test(priority=18)
	public void validateHomePageFooterUnderMyAccountLinkTest() throws InterruptedException {
		test=extent.createTest("TC_18: HOMEPAGE-validate HomePage Footer-Under MyAccount Link Test");
		validateFooterLinkUnderMyAccount();
	}
	
	
	@AfterMethod
	public void tearDown(ITestResult result) {
		if(result.getStatus()==ITestResult.FAILURE) {
			test.log(Status.FAIL, "Test cases MethodName Failed ==>" + result.getName());
			test.log(Status.FAIL, "Test cases MethodName Error is==>" + result.getThrowable());
			test.log(Status.FAIL, MediaEntityBuilder.createScreenCaptureFromBase64String(getBase64ScreenShot()).build());
		}
		else if(result.getStatus()==ITestResult.SKIP) {
			test.log(Status.SKIP, "Test cases MethodName Skiped ==>" + result.getName());
			test.log(Status.SKIP, "Test cases MethodName Skiped ==>" + result.getThrowable());	
		}else if(result.getStatus()==ITestResult.SUCCESS) {
			test.log(Status.PASS, "Test cases MethodName==>" + result.getName());
			//String screenshotPath=LoginPageTest.getBase64ScreenShots();
			test.log(Status.PASS, MediaEntityBuilder.createScreenCaptureFromBase64String(getBase64ScreenShot()).build());
		}
		
		driver.close();
	}
	
	public static String getBase64ScreenShot() {
		return ((TakesScreenshot)driver).getScreenshotAs(OutputType.BASE64);
	}
	
	public static void ValidateCarouselInnerBanner() {
		driver.findElement(By.xpath("//li[@data-slide-to='0']")).click();
		WebElement element=driver.findElement(By.xpath("//*[@id=\"pavcontentslider114\"]/div/div[1]/a/img"));
//		Actions act= new Actions(driver);
//		act.moveToElement(element).build().perform();
		element.click();
		
	}
	
	public void validateFooterLinkUnderInformation() {
		homepage.scrollupToInfo();
		homepage.AboutUs.click();
		test.info("AboutUs-page", MediaEntityBuilder.createScreenCaptureFromBase64String(getBase64ScreenShot()).build());
		
		homepage.scrollupToInfo();
		homepage.DeliveryInfo.click();
		test.info("DeliveryInfo-page", MediaEntityBuilder.createScreenCaptureFromBase64String(getBase64ScreenShot()).build());
		homepage.scrollupToInfo();
		homepage.PrivacyPolicy.click();
		test.info("PrivacyPolicy-page", MediaEntityBuilder.createScreenCaptureFromBase64String(getBase64ScreenShot()).build());
		homepage.TermCondotion.click();
		test.info("TermCondotion-page", MediaEntityBuilder.createScreenCaptureFromBase64String(getBase64ScreenShot()).build());
		homepage.ReturnPolicy.click();
		test.info("ReturnPolicy-page", MediaEntityBuilder.createScreenCaptureFromBase64String(getBase64ScreenShot()).build());
		homepage.DamageGoodsPolicy.click();
		test.info("DamageGoodsPolicy-page", MediaEntityBuilder.createScreenCaptureFromBase64String(getBase64ScreenShot()).build());
		homepage.OurBlog.click();
		test.info("OurBlog-page", MediaEntityBuilder.createScreenCaptureFromBase64String(getBase64ScreenShot()).build());
		homepage.ContactUs.click();
		test.info("ContactUs-page", MediaEntityBuilder.createScreenCaptureFromBase64String(getBase64ScreenShot()).build());
		homepage.Return.click();
		test.info("Return-page", MediaEntityBuilder.createScreenCaptureFromBase64String(getBase64ScreenShot()).build());
		homepage.SiteMap.click();
		test.info("SiteMap-page", MediaEntityBuilder.createScreenCaptureFromBase64String(getBase64ScreenShot()).build());
		homepage.VirventureWebsiteLogo.click();
	}
	
	public void validateFooterLinkUnderMyAccount() {
		homepage.scrollupToInfo();
		homepage.MyAccount.click();
		test.info("MyAccount-page", MediaEntityBuilder.createScreenCaptureFromBase64String(getBase64ScreenShot()).build());
		homepage.OrderHistory.click();
		test.info("OrderHistory-page", MediaEntityBuilder.createScreenCaptureFromBase64String(getBase64ScreenShot()).build());
		homepage.WishList.click();
		test.info("WishList-page", MediaEntityBuilder.createScreenCaptureFromBase64String(getBase64ScreenShot()).build());
		homepage.NewsLetter.click();
		test.info("NewsLetter-page", MediaEntityBuilder.createScreenCaptureFromBase64String(getBase64ScreenShot()).build());
		homepage.Brands.click();
		test.info("Brands-page", MediaEntityBuilder.createScreenCaptureFromBase64String(getBase64ScreenShot()).build());
		homepage.Specials.click();
		test.info("Specials-page", MediaEntityBuilder.createScreenCaptureFromBase64String(getBase64ScreenShot()).build());
		homepage.VirventureWebsiteLogo.click();
	}
}
