package com.CostcoTest.testBase;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;


import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {
	//Step1
	public WebDriver driver;
	public Properties prop;
	//String br= "Chrome";
	//Log4J2Concept
	public Logger logger = LogManager.getLogger(this.getClass());
	
	//Step2
	
	@BeforeClass
	@Parameters("browser")
	public void setup(String br) throws IOException {
		
		System.out.println("Inside setup");
		//**********To Load the properties file***************************************//
		prop = new Properties();
		FileInputStream fis = new FileInputStream(".\\resources\\config.properties");
		prop.load(fis);
		//**********To Load the properties file***************************************//

		//To execute scripts from specific browser passed from testng.xml
		if (br.equalsIgnoreCase("chrome")) {
			
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		} else if(br.equalsIgnoreCase("firefox")){
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		}
		driver.manage().window().maximize();
	}
	
	//Capturing Screenshots of the application
	public void capturescreen(WebDriver driver, String testName) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File target = new File(System.getProperty("user.dir")+ "\\screenshots\\" + testName + ".png");
		System.out.println(target);
		FileUtils.copyFile(source, target);
		System.out.println("Screenshot Captured for : " + testName);
	}
	
	
	public Boolean isDisplayed(WebElement element, String locator) {

	    Boolean passFail = false;

	    try {
	        if (element.isDisplayed())
	            passFail = true;
	    } catch (NoSuchElementException noElementExp) {
	        System.err.println("Unable to locate element '" + locator + "'");
	        
	    } catch (Exception e) {
	        System.err.println("Unable to check display status of element '" + locator + "'");
	        e.printStackTrace();
	    }

	    return passFail;

	}
	
	//Step4
	@AfterClass
	public void tearDown() {
		driver.quit();
	}
}
