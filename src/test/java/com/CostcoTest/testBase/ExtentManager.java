package com.CostcoTest.testBase;
import java.io.File;
import java.util.Date;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentManager {
	
	
	static ExtentReports reports;  //defining it static means 1 per class   not 1 per instance
	public static ExtentReports getReports() {
		if (reports == null) {

			String parentDir = System.getProperty("user.dir");
			reports = new ExtentReports();
			
			Date date = new Date();
			String dateStr = String.format("%1$tb-%1$te-%1$tY %1$tH-%1$tM-%1tS", date);
			

			String reportsFolderPath = parentDir + "//reports//"+dateStr+"//";
			String screenshotFolderPath = parentDir+"//reports//"+dateStr+"//screenshots";
			File f = new File(screenshotFolderPath);
			f.mkdirs(); 
		
			ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportsFolderPath);
			sparkReporter.config().setReportName("Costco Testing");
			sparkReporter.config().setDocumentTitle("Selenium Automation Report");
			sparkReporter.config().setTheme(Theme.DARK);
			
			reports.attachReporter(sparkReporter);
		}
		
		return reports;
		
	}

}
