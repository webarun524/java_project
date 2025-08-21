package com.java.base;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import io.github.bonigarcia.wdm.WebDriverManager;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.lang.reflect.Method;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.java.reports.ExtentManager;
import org.testng.ITestResult;

public class BaseTest {

    protected static WebDriver driver;
    protected static ExtentReports extent;
    protected static ExtentTest test;

    // ✅ Setup browser and Extent Report
    @BeforeMethod
    public void setUp(Method method) throws IOException {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");

        extent = ExtentManager.getInstance();
        test = extent.createTest(method.getName());
    }

    public void takeScreenshot(String fileName) {
        try {
            if (driver == null) {
                throw new RuntimeException("Driver is not initialized!");
            }

            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            File dest = new File(".\\uploads\\" + fileName + ".png");
            dest.getParentFile().mkdirs();  // Ensure folder exists
            Files.copy(src.toPath(), dest.toPath());
            System.out.println("Screenshot saved at: " + dest.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String captureScreenshot(String fileName) {
        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String destPath = System.getProperty("user.dir") + "\\reports\\screenshots\\" + fileName + ".png";
        File dest = new File(destPath);
        dest.getParentFile().mkdirs();
        try {
            Files.copy(src.toPath(), dest.toPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return destPath;
    }

    // ✅ Correct AfterMethod for TestNG + ExtentReports
    @AfterMethod
    public void tearDown(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            test.fail("Test Failed: " + result.getThrowable());
        } else if (result.getStatus() == ITestResult.SUCCESS) {
            test.pass("Test Passed");
        } else {
            test.skip("Test Skipped");
        }

        extent.flush();  // ✅ Save report after each test

        if (driver != null) {
            driver.quit();
        }
    }
}
