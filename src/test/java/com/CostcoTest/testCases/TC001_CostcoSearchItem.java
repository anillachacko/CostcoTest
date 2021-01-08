package com.CostcoTest.testCases;


import java.io.IOException;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.CostcoTest.pageObjects.MainPage;
import com.CostcoTest.testBase.BaseClass;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.CostcoTest.testBase.ExtentManager;


public class TC001_CostcoSearchItem extends BaseClass{
   // ExtentReports reports;
   // ExtentTest extent;
	@Test(groups={"TC001_CostcoSearchItem.searchItemTest"})
	public void searchItemTest() throws InterruptedException, IOException {
		
		//reports = ExtentManager.getReports();
		//extent = reports.createTest("searchItemTest");
		logger.info("*************************Starting execution for TC001_CostcoSearchItem************************************");	
		System.out.println("Check");
		driver.get(prop.getProperty("url")); //URL is defined inside config.propeties file
		logger.info("*************************Opened application*****************************************");
		
		
		
		MainPage mp = new MainPage(driver);
		
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		
		
		logger.info("*************************Checking if Costco Modal Dialog is opened************************************");	
		Boolean modalDisplayed = isDisplayed(mp.getCostcoModalElement(), "Costco Modal Box");
		
		if(modalDisplayed) {
			logger.info("*************************Setting region and language************************************");
			mp.setRegion();
		}

		logger.info("*************************Search Starting************************************");
		String searchString = prop.getProperty("searchString");
		mp.setTxtSearchField(searchString);
		mp.clickSearchButton();
		Thread.sleep(10000);
		String actTitleAfterSearch = driver.getTitle();
		
		
		int index = actTitleAfterSearch.indexOf(searchString);
		System.out.println("Index:"+index);
		System.out.println("actTitleAfterSearch:"+actTitleAfterSearch);
		System.out.println("searchString:"+searchString);
		if((actTitleAfterSearch.indexOf(searchString)) >=0) {
			
			Boolean statusPX760 = isDisplayed(mp.getLinkPX760(),"PX 760 link") ;
			Boolean statusPX780 = isDisplayed(mp.getLinkPX780(), "PX 780 link") ;
			System.out.println(statusPX760  +" "+statusPX780);
			
			if(isDisplayed(mp.getLinkPX760(),"PX 760 link") || isDisplayed(mp.getLinkPX780(), "PX 780 link")) {
				logger.info("*************************Search Successful************************************");
				capturescreen(driver, "searchItemTest");
				Assert.assertTrue(true,"Search Success");
				//extent.log(Status.PASS, "Search for PX-760 and PX-780 successful");
			} else {
				logger.fatal("*************************Items PX-760 and PX-780 not present************************************");
				capturescreen(driver, "searchItemTest");
				Assert.assertFalse(true, "Items PX-760 and PX-780 not present");
				//extent.log(Status.FAIL, "Search for PX-760 and PX-780 unsuccessful");
			}
		}else {
			logger.fatal("*************************Search Failed************************************");
			//extent.log(Status.FAIL, "Search unsuccessful");
			capturescreen(driver, "searchItemTest");
			Assert.assertFalse(true, "Search Fail");
		}
		logger.info("*************************Execution ended for TC001_CostcoSearchItem************************************");
		//reports.flush();
	}

}
