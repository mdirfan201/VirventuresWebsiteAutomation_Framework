package com.qa.VirventureWebsite.TestCases;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
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
import com.qa.VirventureWebsite.page.ForgotPasswordPage;
import com.qa.VirventureWebsite.page.HomePage;
import com.qa.util.TestUtil;

@Listeners(CustomeListner.class)
public class ForgotPasswordTest extends TestBase{
	static HomePage homepage;
	static ForgotPasswordPage forgotpasswordpage;

	static ExtentReports extent;
	static ExtentSparkReporter spark;
	static ExtentTest test;
	String sheetName="ForgotPage";

	public ForgotPasswordTest() {
		super();
	}

	@BeforeSuite
	public void setupExtentReport() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yy-MM-dd");  
		LocalDateTime now = LocalDateTime.now();  
		System.out.println(dtf.format(now));
		extent= new ExtentReports();
		spark= new ExtentSparkReporter("D:\\IRFAN---\\java program\\VirventuresWebsiteAutomation_Framework\\Extent-Report\\VirventureWebsite-ForgotPage.html");
		spark.config().setTheme(Theme.DARK);
		spark.config().setDocumentTitle("Virventure-Website Automation");
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
		homepage= new HomePage();
		try {
			homepage.validateForgotPage();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		forgotpasswordpage= new ForgotPasswordPage();
	}


	@Test(priority=1)
	public void validateForgotTitleTest() {
		test=extent.createTest("TC_01 : FORGOT-PAGE  Validate Title Test");
		String ExpectedTitle="Forgot Your Password?";
		String ActualTitle=forgotpasswordpage.validateTitle();
		Assert.assertEquals(ActualTitle, ExpectedTitle, "Title not Matched");
		test.info("The Actual and Expected Title Matched---->"+ driver.getTitle());
	}
	@Test(priority=2)
	public void validateForgotPageBackBtnTest() throws InterruptedException {
		test=extent.createTest("TC_02 : FORGOT-PAGE  Validate ForgotPageBackBtn Test");
		forgotpasswordpage.validateBackBtnLink();
	}
	@DataProvider
	public Object[][] getTestData() {
		Object data[][]=TestUtil.getTestData(sheetName);
		return data;
	}

	@Test(priority=3)
	public void ForgotPageEmailIdEmptyTest() {
		test=extent.createTest("TC_03 : FORGOT-PAGE  Validate ForgotPageEmailIdEmpty Test");
		forgotpasswordpage.clickSubmitBtn.click();
	}
	@Test(priority=4)
	public void ForgotPageInvalidEmailIdTest() {
		test=extent.createTest("TC_04 : FORGOT-PAGE  Validate ForgotPageEmailIdEmpty Test");
		forgotpasswordpage.txtEmail.sendKeys("mdirfan201@gmail.com",Keys.TAB);
		forgotpasswordpage.clickSubmitBtn.click();
		//test.info("Warning Invalid EmailID---> "+ driver.findElement(By.xpath("//div[@class='warning']")).getText());
	}
	@Test(priority=5,dataProvider="getTestData")
	public void validateForgotPageEmailIdTest(String Email) throws IOException {
		test=extent.createTest("TC_05 : FORGOT-PAGE  Validate ForgotPageEmailId Test");
		forgotpasswordpage.setForgotPwdEmail(Email);
		test.info("SuccessFully Send new Password---> "+ driver.findElement(By.xpath("//div[@class='success']")).getText());
	}
	@AfterMethod
	public void tearDown(ITestResult result) {
		if(result.getStatus()==ITestResult.FAILURE) {
			test.log(Status.FAIL, "Test cases MethodName Failed ==>" + result.getName());
			test.log(Status.FAIL, "Test cases MethodName Failed and throws Exception==>" + result.getThrowable());
			test.log(Status.FAIL, MediaEntityBuilder.createScreenCaptureFromBase64String(getBase64ScreenShot()).build());
		}else if(result.getStatus()==ITestResult.SKIP) {
			test.log(Status.SKIP, "Test cases MethodName Skiped ==>" + result.getName());
			test.log(Status.SKIP, "Test cases MethodName Skiped and throws Exception ==>" + result.getThrowable());	
		}else if(result.getStatus()==ITestResult.SUCCESS) {
			test.log(Status.PASS, "Test cases MethodName Pass ==>" + result.getName());
			test.log(Status.PASS, MediaEntityBuilder.createScreenCaptureFromBase64String(getBase64ScreenShot()).build());
		}

		driver.close();

	}

	public String getBase64ScreenShot() {
		return((TakesScreenshot)driver).getScreenshotAs(OutputType.BASE64);
	}

}
