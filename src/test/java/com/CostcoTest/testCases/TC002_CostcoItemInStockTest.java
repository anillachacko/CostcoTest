package com.CostcoTest.testCases;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.CostcoTest.pageObjects.MainPage;
import com.CostcoTest.pageObjects.SearchedPage;
import com.CostcoTest.testBase.BaseClass;
import com.CostcoTest.testBase.ExtentManager;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class TC002_CostcoItemInStockTest extends BaseClass{
	
	MainPage mp;
	
	@BeforeMethod
	public void setupSearchPage() {
		
		driver.get(prop.getProperty("url")); //URL is defined inside config.propeties file
		logger.info("*************************Opened application*****************************************");
		
		
		mp = new MainPage(driver);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		
		Boolean modalDisplayed = isDisplayed(mp.getCostcoModalElement(), "Costco Modal Box");	
		if(modalDisplayed) {
			mp.setRegion();
		}

		String searchString = prop.getProperty("searchString");
		mp.setTxtSearchField(searchString);
		mp.clickSearchButton();
	}
		
	
	@Test(dependsOnGroups={"TC001_CostcoSearchItem.searchItemTest"})
	public void itemPX760InStockTest() throws IOException {
		
		
		logger.info("*************************Starting execution for PX760 Search************************************");		
		
		SearchedPage sp = new SearchedPage(driver);

		if(isDisplayed(mp.getLinkPX760(),"PX 760 link")) {
			
			logger.info("*************************Looking for PX-760************************************");
			mp.getLinkPX760().click();
			String strAddToCartBtnValue = sp.getBtnAddToCart().getAttribute("value");
				
				if(strAddToCartBtnValue.equalsIgnoreCase("Add to Cart")) {
					logger.info("*************************Found  PX-760************************************");
					Assert.assertTrue(true,"Found PX-760: Item Available");
					capturescreen(driver, "itemPX760InStockTest");
				}
				else if(strAddToCartBtnValue.equalsIgnoreCase("Out of Stock")){
					logger.info("*************************Not Found for PX-760************************************");
					capturescreen(driver, "itemPX760InStockTest");
					Assert.assertFalse(true,"PX-760 not found : Item Out of Stock");
					
				}
		} 
		logger.info("*************************Execution ended for PX760 Search************************************");
			
	}
	
	@Test(dependsOnGroups={"TC001_CostcoSearchItem.searchItemTest"})
	public void itemPX780InStockTest() throws IOException {
		
		
		logger.info("*************************Starting execution for PX 780 Search************************************");		

		
		SearchedPage sp = new SearchedPage(driver);

		if(isDisplayed(mp.getLinkPX780(),"PX 780 link")) {
			
			logger.info("*************************Looking for PX-780************************************");
			mp.getLinkPX780().click();
			String strAddToCartBtnValue = sp.getBtnAddToCart().getAttribute("value");
				
				if(strAddToCartBtnValue.equalsIgnoreCase("Add to Cart")) {
					logger.info("*************************Found  PX-780************************************");
					Assert.assertTrue(true,"Found PX-780: Item Available");
					capturescreen(driver, "itemPX780InStockTest");
				}
				else if(strAddToCartBtnValue.equalsIgnoreCase("Out of Stock")){
					logger.info("*************************Not Found for PX-780************************************");
					capturescreen(driver, "itemPX780InStockTest");
					Assert.assertFalse(true,"PX-780 not found : Item Out of Stock");
					
				}
		} 
		logger.info("*************************Execution ended for PX780 Search************************************");
	}	

}
