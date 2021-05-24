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
import com.qa.VirventureWebsite.page.TrackOrderPage;
import com.qa.util.TestUtil;

public class TrackOrderTest extends TestBase{

	static HomePage homepage;
	static TrackOrderPage trackorderpage;
	
	static ExtentReports extent;
	static ExtentSparkReporter spark;
	static ExtentTest test;
	String sheetName="TrackOrder";
	public TrackOrderTest() {
		super();
	}
	
	@BeforeSuite
	public void setExtentReport() {
		extent= new ExtentReports();
		spark= new ExtentSparkReporter("D:\\IRFAN---\\java program\\VirventuresWebsiteAutomation_Framework\\Extent-Report\\VirventureWebsite-TrackOrder.html");
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
	public void tearExtent() {
		extent.flush();
	}
	
	@BeforeMethod
	public void setup() {
		initialization();
		homepage= new HomePage();
		homepage.headerTrackOrderBtn.click();
		trackorderpage= new TrackOrderPage();
	}
	
	@Test(priority=1)
	public void validateLoginPageTitleTest() {
		test=extent.createTest("TC_01: TrackOrder Page-Verify LoginPage Title");
		String ExpectedTitle="Track Your Order";
		String actualTitle=trackorderpage.validateTitle();
		Assert.assertEquals(actualTitle, ExpectedTitle, "Title not matched");
		test.info("The Actual and Expected Title matched==>"+ driver.getTitle());
	}
	
	@Test(priority=2)
	public void verifyLoginPageLableTest() {
		test=extent.createTest("TC_02: TrackOrder Page-Verify LoginPage Title");
		boolean flag=trackorderpage.verifyPageLable();
		Assert.assertTrue(flag);
	}
	@Test(priority=3)
	public void validateEmptyDataFields() throws InterruptedException {
		test=extent.createTest("TC_03: TrackOrder Page-validate Empty DataField Title");
		trackorderpage.submitEmptyData();
		test.info("The Email warning is--->"+ trackorderpage.warnEmail);
		test.info("The OrderID warning is--->"+ trackorderpage.warnTrackOrderId);
	}
	
	@DataProvider
	public Object[][] getExcelData() {
		Object data[][]=TestUtil.getTestData(sheetName);
		return data;
	}
	@Test(priority=3,dataProvider="getExcelData")
	public void validateDataFields(String Email, String OrderID) throws InterruptedException {
		test=extent.createTest("TC_03: TrackOrder Page-validate Empty DataField Title");
		trackorderpage.validateTrackOrder(Email, OrderID);
		
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
