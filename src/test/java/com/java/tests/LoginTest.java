package com.java.tests;

import com.java.base.BaseTest;
import com.java.pages.LoginPage;
import org.testng.annotations.Test;
import java.io.IOException;


public class LoginTest extends BaseTest {

    @Test
    public void testValidLogin() throws IOException , InterruptedException{

        //Login to the application
        LoginPage login = new LoginPage(driver);
        login.Loginmethod("Admin","admin123");
        System.out.println("Login Successful!");


        Thread.sleep(5000); // Wait for 2 seconds to observe the result
    }
}


