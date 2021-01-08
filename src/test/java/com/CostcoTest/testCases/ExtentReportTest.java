package com.CostcoTest.testCases;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import com.CostcoTest.testBase.ExtentManager;

public class ExtentReportTest {
	
	ExtentReports reports;
    ExtentTest extent;
    
    @BeforeMethod
	public void init() {
		
		reports = ExtentManager.getReports();
		extent = reports.createTest("ExtentReportTest");
		
	}
	
	@AfterMethod
	public void quit() {
			reports.flush();
	}
	@Test
	public void loginTestCase() {
		  extent.log(Status.INFO, "Started Login Test");
		  extent.log(Status.INFO, "Browser Started");
		  extent.log(Status.PASS, "Logged in successfully");
		  extent.log(Status.WARNING, "Issue While loogin in");
	}
    

}
