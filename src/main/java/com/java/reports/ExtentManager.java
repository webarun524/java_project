package com.java.reports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {
    private static ExtentReports extent;

    public static ExtentReports getInstance() {
        if (extent == null) {
            String reportPath = System.getProperty("user.dir") + "\\reports\\ExtentReport.html";
            ExtentSparkReporter reporter = new ExtentSparkReporter(reportPath);
            reporter.config().setDocumentTitle("Automation Test Report");
            reporter.config().setReportName("Selenium Test Results");

            extent = new ExtentReports();
            extent.attachReporter(reporter);

            // Adding environment info
            extent.setSystemInfo("Project", "Selenium POM Project");
            extent.setSystemInfo("Tester", "Arunkumar");
            extent.setSystemInfo("Browser", "Chrome");
        }
        return extent;
    }
}
