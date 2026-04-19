package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import base.BaseTest;
import pages.LoginPage;

/**
 * LoginTest validates the core authentication flow on SauceDemo.
 */
public class LoginTest extends BaseTest {

    @Test(description = "Valid credentials should redirect to the inventory page")
    public void userShouldLoginSuccessfullyWithValidCredentials() {
        LoginPage loginPage = new LoginPage(driver);

        loginPage.loginToApp("standard_user", "secret_sauce");

        Assert.assertTrue(driver.getCurrentUrl().contains("inventory"),
                "Login failed — expected inventory page but got: "
                        + driver.getCurrentUrl());
    }
}