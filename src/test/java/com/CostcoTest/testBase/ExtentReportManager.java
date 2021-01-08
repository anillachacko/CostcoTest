package com.CostcoTest.testBase;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.ImageHtmlEmail;
import org.apache.commons.mail.resolver.DataSourceUrlResolver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportManager implements ITestListener
{
	public ExtentSparkReporter htmlReporter;
	public ExtentReports extent;
	public ExtentTest test;
	
	String repName;
	
	public void onStart(ITestContext testContext)
	{		
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());//time stamp
		repName="Test-Report-"+timeStamp+".html";
				
		htmlReporter=new ExtentSparkReporter(".\\reports\\"+repName);//specify location of the report
				
		htmlReporter.config().setDocumentTitle("Costco Item Search Test"); // Tile of report
		htmlReporter.config().setReportName("Item Search Test Report"); // name of the report
		htmlReporter.config().setTheme(Theme.DARK);
		
		extent=new ExtentReports();
		extent.attachReporter(htmlReporter);
		extent.setSystemInfo("Host name","localhost");
		extent.setSystemInfo("Environemnt","QA");
		extent.setSystemInfo("user","Anilla");
		
	}
	
	public void onTestSuccess(ITestResult result)
	{
		test=extent.createTest(result.getName()); // create new entry in the report
		test.log(Status.PASS, "Test Passed");
		String screenshotPath=System.getProperty("user.dir")+"\\screenshots\\"+result.getName()+".png";
		System.out.println(screenshotPath);
		try {
			test.addScreenCaptureFromPath(screenshotPath);// adding screen shot	
		} catch (IOException e) {
				e.printStackTrace();
		} 
	}
	
	public void onTestFailure(ITestResult result)
	{
		
		test=extent.createTest(result.getName()); // create new entry in th report
		test.log(Status.FAIL, "Test Failed");
		test.log(Status.FAIL, result.getThrowable().getMessage());
					
		String screenshotPath=System.getProperty("user.dir")+"\\screenshots\\"+result.getName()+".png";
		System.out.println(screenshotPath);
		try {
			test.addScreenCaptureFromPath(screenshotPath);// adding screen shot	
		} catch (IOException e) {
				e.printStackTrace();
		} 
	}
	
	public void onTestSkipped(ITestResult result)
	{
		
		test=extent.createTest(result.getName()); // create new entry in th report
		test.log(Status.SKIP, "Test Skip");
		test.log(Status.SKIP, result.getThrowable().getMessage());
	}
	
	public void onFinish(ITestContext testContext)
	{
		extent.flush(); //To save the reports

		/*try {
		URL url = new URL("file:///"+System.getProperty("user.dir")+"\\reports\\"+repName);
		 // Create the email message
		  ImageHtmlEmail email =  cnew ImageHtmlEmail();
		  email.setDataSourceResolver(new DataSourceUrlResolver(url));
		  email.setHostName("pop.gmail.com");
		  email.setSmtpPort(465);
		  email.setAuthenticator(new DefaultAuthenticator("tekinspireinfotech@gmail.com", "password"));
		  email.setSSLOnConnect(true);
		  email.setFrom("tekinspireinfotech@gmail.com");   //Sender
		  email.setSubject("Test Results");
		  email.setMsg("Please find Attached Report....");
		  email.addTo("nareshkumarg14@gmail.com");   //Receiver
		  email.attach(url, "extent report", "please check report...");
		  email.send();   // send the email
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}*/
		}

	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}
	
	
}
