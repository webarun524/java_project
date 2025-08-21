package com.java.reports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentManager {
    private static ExtentReports extent;

    public static ExtentReports getInstance() {
        if (extent == null) {
            createInstance();
        }
        return extent;
    }

    public static ExtentReports createInstance() {
        // Set report save path
        String reportPath = System.getProperty("user.dir") + "/test-output/ExtentReport.html";

        ExtentSparkReporter reporter = new ExtentSparkReporter(reportPath);
        reporter.config().setDocumentTitle("Automation Test Report");
        reporter.config().setReportName("Selenium TestNG Automation Report");
        reporter.config().setTheme(Theme.DARK);

        extent = new ExtentReports();
        extent.attachReporter(reporter);

        //Optional info
        extent.setSystemInfo("Tester", "Arun Kumar");
        extent.setSystemInfo("Environment", "QA");
        extent.setSystemInfo("Framework", "Selenium + TestNG");

        return extent;
    }
}