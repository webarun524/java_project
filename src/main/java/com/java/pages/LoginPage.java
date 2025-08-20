package com.java.pages;
import com.java.base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class LoginPage {
    WebDriver driver;
    WebDriverWait wait;

    // Locators
    // private By username = By.xpath("//input[@placeholder='Username']");
    // private By password = By.xpath("//input[@placeholder='Password']");
    // private By loginBtn = By.xpath("//button[normalize-space()='Login']");

    // Constructor
    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void Loginmethod(String user, String pass) {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement username = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.name("username")));
        username.sendKeys(user);

        driver.findElement(By.name("password")).sendKeys(pass);

        driver.findElement(By.cssSelector(".orangehrm-login-button")).click();

        // expect login to be successful
        WebElement successMsg = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".oxd-text.oxd-text--h6.oxd-topbar-header-breadcrumb-module")));
        if (successMsg.isDisplayed()) {
            System.out.println("Login Successful!");
        }

        BaseTest base = new BaseTest();
        base.takeScreenshot("testimage");

    }

}