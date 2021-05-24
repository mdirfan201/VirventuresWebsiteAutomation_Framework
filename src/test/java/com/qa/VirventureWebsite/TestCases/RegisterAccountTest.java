package com.qa.VirventureWebsite.TestCases;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.qa.Base.TestBase;
import com.qa.VirventureWebsite.page.HomePage;
import com.qa.VirventureWebsite.page.RegisterAccountPage;
import com.qa.util.JavaScriptUtil;
import com.qa.util.TestUtil;

public class RegisterAccountTest extends TestBase{

	static HomePage homepage;
	static RegisterAccountPage registeraccountpage;
	static ExtentReports extent;
	static ExtentSparkReporter spark;
	static ExtentTest test;
	String sheetName="Register";
	public RegisterAccountTest() {
		super();
	}
	
	@BeforeSuite
	public void setExtentReport() {
		extent= new ExtentReports();
		spark= new ExtentSparkReporter("D:\\IRFAN---\\java program\\VirventuresWebsiteAutomation_Framework\\Extent-Report\\VirventureWebsite-RegisterAccountPage.html");
		spark.config().setTheme(Theme.DARK);
		spark.config().setDocumentTitle("VirventureWebsite Automation");
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
	public void tearExtent() {
		extent.flush();
	}
	
	@BeforeMethod
	public void setup() {
		initialization();
		homepage= new HomePage();
		homepage.headerCreateAccountBtn.click();
		registeraccountpage= new RegisterAccountPage();
	}
	
	@Test(priority=1)
	public void validateLoginPageTitleTest() {
		test=extent.createTest("TC_01: REGISTER ACCOUNT PAGE-Verify LoginPage Title");
		String ExpectedTitle="Register Account";
		String actualTitle=registeraccountpage.validateTitle();
		Assert.assertEquals(actualTitle, ExpectedTitle, "Title not matched");
		test.info("The Actual and Expected Title matched==>"+ driver.getTitle());
	}
	@Test(priority=2)
	public void verifyLoginPageLableTest() {
		test=extent.createTest("TC_02: REGISTER ACCOUNT PAGE-Verify LoginPage Title");
		boolean flag=registeraccountpage.verifyPageLogo();
		Assert.assertTrue(flag);
	}
	@Test(priority=3)
	public void validateLoginWithEmptyDateTest() throws InterruptedException {
		test=extent.createTest("TC_03: REGISTER ACCOUNT PAGE-validate Login With EmptyDate Test");
		registeraccountpage.validateWithEmptyData();
		test.info("RegisterPage", MediaEntityBuilder.createScreenCaptureFromBase64String(getBase64ScreenShot()).build());
		Thread.sleep(2000);
		JavaScriptUtil.scrolluptoanelementByJS(registeraccountpage.YourPersonalDetails, driver);
		test.info("The FirstName warning is----->"+ registeraccountpage.warfirstname);
		test.info("The FirstName warning is----->"+ registeraccountpage.warlastname);
		test.info("The FirstName warning is----->"+ registeraccountpage.waremail);
		test.info("The FirstName warning is----->"+ registeraccountpage.waremail);
		test.info("Your Personal Details-SS", MediaEntityBuilder.createScreenCaptureFromBase64String(getBase64ScreenShot()).build());
		Thread.sleep(2000);
		JavaScriptUtil.scrolluptoanelementByJS(registeraccountpage.YourAddressLable, driver);
		test.info("The FirstName warning is----->"+ registeraccountpage.waraddress);
		test.info("The FirstName warning is----->"+ registeraccountpage.warcity);
		test.info("The FirstName warning is----->"+ registeraccountpage.warzipcode);
		test.info("The FirstName warning is----->"+ registeraccountpage.warState);
		test.info("Your Adress-SS", MediaEntityBuilder.createScreenCaptureFromBase64String(getBase64ScreenShot()).build());
		Thread.sleep(2000);
		JavaScriptUtil.scrolluptoanelementByJS(registeraccountpage.YourPasswordLable, driver);
		test.info("The FirstName warning is----->"+ registeraccountpage.warpassword);
		
	}
	
	@DataProvider
	public Object[][] getExcelData() {
		Object data[][]=TestUtil.getTestData(sheetName);
		return data;
	}
	@Test(priority=4, dataProvider="getExcelData")
	public void validateRegisterPageDataTest(String FirstName,String LastName,String Email,String Telephone,String Address,String City,String PostCode,String Country,String Region,String Password, String ConfirmPassword) {
		test=extent.createTest("TC_03: REGISTER ACCOUNT PAGE- validate RegisterPage Data Test");
		registeraccountpage.validateDataFileds(FirstName, LastName, Email, Telephone, Address, City, PostCode, Country, Region, Password, ConfirmPassword);
	}
	
	@AfterMethod	
	public void tearDown(ITestResult result) {
		if(result.getStatus()==ITestResult.FAILURE) {
			test.log(Status.FAIL, "Test cases MethodName Failed ==>" + result.getName());
			test.log(Status.FAIL, "Test cases MethodName Error is==>" + result.getThrowable());
			test.log(Status.FAIL, MediaEntityBuilder.createScreenCaptureFromBase64String(getBase64ScreenShot()).build());
		}else if(result.getStatus()==ITestResult.SKIP) {
			test.log(Status.SKIP, "Test cases MethodName Skiped ==>" + result.getName());
			test.log(Status.SKIP, "Test cases MethodName Skiped ==>" + result.getThrowable());	
		}else if(result.getStatus()==ITestResult.SUCCESS) {
			test.log(Status.PASS, "Test cases MethodName Pass ==>" + result.getName());
			test.log(Status.PASS, MediaEntityBuilder.createScreenCaptureFromBase64String(getBase64ScreenShot()).build());
		}
		driver.close();
	}
	
	
	public static String getBase64ScreenShot() {
		return ((TakesScreenshot)driver).getScreenshotAs(OutputType.BASE64);
	}
}
