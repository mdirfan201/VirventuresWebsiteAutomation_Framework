package com.qa.VirventureWebsite.TestCases;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
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
import com.qa.VirventureWebsite.page.ContactUsPage;
import com.qa.VirventureWebsite.page.HomePage;
import com.qa.util.JavaScriptUtil;
import com.qa.util.TestUtil;

public class ContactUsTest extends TestBase {

	static HomePage homepage;
	static ContactUsPage contactuspage;
	static ExtentReports extent;
	static ExtentSparkReporter spark;
	static ExtentTest test;

	String sheetName="Register";
	public ContactUsTest() {
		super();
	}

	@BeforeSuite
	public void setExtentReport() {
		extent= new ExtentReports();
		spark= new ExtentSparkReporter("D:\\IRFAN---\\java program\\VirventuresWebsiteAutomation_Framework\\Extent-Report\\VirventureWebsite-ContactUSPage.html");
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
		homepage = new HomePage();
		homepage.headerContactUsBtn.click();
		contactuspage = new ContactUsPage();
	}

	@Test(priority=1)
	public void validateContactUsPageTitleTest() {
		test=extent.createTest("TC_01 : CONTACT-US Page Validate Title Test");
		String ExpectedTitle="Contact Us";
		String ActualTitle=contactuspage.validateTitle();
		Assert.assertEquals(ActualTitle, ExpectedTitle, "Title not Matched");
		test.info("The Actual and Expected Title Matched---->"+ driver.getTitle());
	}
	@Test(priority=2)
	public void verifyContactUsPageLableTest() {
		test=extent.createTest("TC_02 : CONTACT-US Page Verify Lable Test");
		boolean flag=contactuspage.verifyLable();
		Assert.assertTrue(flag);
		test.info("Contact-Us Page Lable is Displayed");
	}
	@Test(priority=3)
	public void verifyContactUsPageEmptyDataTest() {
		test=extent.createTest("TC_03 : CONTACT-US Page EmptyData Test");
		contactuspage.validateEmptyContactUsdata();
		WebElement element= driver.findElement(By.xpath("//b[normalize-space()='Telephone:']"));
		JavaScriptUtil.scrolluptoanelementByJS(element, driver);
		test.info("The warning under Name is==>"+ contactuspage.nameWarning.getText());
		test.info("The warning under Email is-->"+ contactuspage.EmailWarning.getText());
		test.info("The warning under Enquiry is-->"+ contactuspage.EnquiryWarning.getText());
		test.info("The warning under Captcha is-->"+ contactuspage.CaptchaWarning.getText());
	}
	
	@DataProvider
	public Object[][] getTestData() {
		Object data[][]=TestUtil.getTestData(sheetName);
		return data;
	}
	@Test(priority=4, dataProvider="getTestData")
	public void validateValidContactUsPageData(String Name, String Email,String Enquiry) {
		test=extent.createTest("TC_04 : CONTACT-US Page validdata Test");
		contactuspage.setContactUsData(Name, Email, Enquiry);
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
