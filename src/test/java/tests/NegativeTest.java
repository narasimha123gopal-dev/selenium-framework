package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.time.Duration;
import base.BaseTest;
import pages.*;

/**
 * NegativeTest covers error handling and boundary scenarios on SauceDemo.
 * These tests verify the application behaves correctly under invalid conditions.
 */
public class NegativeTest extends BaseTest {

    @Test(description = "Invalid credentials should display an error message")
    public void invalidLoginShouldShowError() {
        LoginPage loginPage = new LoginPage(driver);

        loginPage.loginToApp("invalid_user", "wrong_pass");

        String errorMessage = loginPage.getErrorMessage();

        Assert.assertTrue(
                errorMessage.toLowerCase().contains("do not match"),
                "Expected credential mismatch error but got: " + errorMessage);
    }

    @Test(description = "Locked account should be blocked from logging in")
    public void lockedUserShouldNotLogin() {
        LoginPage loginPage = new LoginPage(driver);

        loginPage.loginToApp("locked_out_user", "secret_sauce");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement errorElement = wait.until(ExpectedConditions
                .visibilityOfElementLocated(
                        By.cssSelector("[data-test='error']")));

        String errorMessage = errorElement.getText().trim();

        Assert.assertTrue(
                errorMessage.toLowerCase().contains("locked"),
                "Expected account locked error but got: " + errorMessage);
    }

    @Test(description = "Empty cart should display the checkout button")
    public void emptyCartShouldShowCheckoutButton() {
        LoginPage loginPage     = new LoginPage(driver);
        ProductPage productPage = new ProductPage(driver);
        CartPage cartPage       = new CartPage(driver);

        loginPage.loginToApp("standard_user", "secret_sauce");
        productPage.openCart();

        // Validation 1: User is on the cart page
        Assert.assertTrue(cartPage.verifyCartPage(),
                "Expected cart page but URL was: " + driver.getCurrentUrl());

        // Validation 2: Cart is empty and checkout button is still accessible
        Assert.assertTrue(cartPage.isCartEmpty(),
                "Cart should be empty but contains items");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        boolean checkoutVisible = wait.until(ExpectedConditions
                .visibilityOfElementLocated(By.id("checkout"))) != null;

        Assert.assertTrue(checkoutVisible,
                "Checkout button should be visible even on an empty cart");
    }
}