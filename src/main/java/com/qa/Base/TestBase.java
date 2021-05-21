package com.qa.Base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.qa.util.TestUtil;
import com.qa.util.WebEventListener;

import io.github.bonigarcia.wdm.WebDriverManager;
import javafx.scene.web.WebEvent;



public class TestBase {
	public static WebDriver driver;
	static EventFiringWebDriver e_driver;
	public static Properties prop;
	public TestBase() {
		
		try {
			FileInputStream ip= new FileInputStream("D:\\IRFAN---\\java program\\VirventuresWebsiteAutomation_Framework\\src\\main\\java\\com\\qa\\config\\config.properties");
			prop= new Properties();
			prop.load(ip);
		} catch (FileNotFoundException e) {
			System.out.println("The config file not found Or config file location is missing");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void initialization() {
		String browserName=prop.getProperty("browser");
		if(browserName.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver= new ChromeDriver();
		}else if(browserName.equalsIgnoreCase("FireFox")) {
			WebDriverManager.firefoxdriver().setup();
			driver= new FirefoxDriver();
		}else if(browserName.equalsIgnoreCase("IE")) {
			WebDriverManager.edgedriver().setup();
			driver= new EdgeDriver();
		}else {
			
			System.out.println("browser key not available in properties file");
		}
		
		e_driver= new EventFiringWebDriver(driver);
		WebEventListener eventListner=new WebEventListener(); 
		e_driver.register(eventListner);
		driver= e_driver;
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LODE_TIMEOUT, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT_TIMEOUT, TimeUnit.SECONDS);
		driver.get(prop.getProperty("url"));
		
	}

	public  static void getScreenshot(String testMethodNmae) throws IOException {
		TakesScreenshot ts= (TakesScreenshot) driver;
		File src=ts.getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(src, new File("D:\\IRFAN---\\java program\\VirventuresWebsiteAutomation_Framework\\ScreenShot\\"+testMethodNmae+"_"+ ".png"));
			
		} catch (Exception e) {
			System.out.println("Unable to take screensot");
			e.printStackTrace();
		}
		
	}
}
