package tests;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.time.Duration;
import base.BaseTest;
import pages.*;

public class OrderTest extends BaseTest {
    @Test(description = "User should be able to complete a full purchase flow")
    public void completeOrderFlow() {
        LoginPage loginPage       = new LoginPage(driver);
        ProductPage productPage   = new ProductPage(driver);
        CartPage cartPage         = new CartPage(driver);
        CheckoutPage checkoutPage = new CheckoutPage(driver);

        // FIX 1: Increased timeout from 15 → 30 seconds
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        // ── Step 1: Login ──────────────────────────────────────────────────
        loginPage.loginToApp("standard_user", "secret_sauce");
        wait.until(ExpectedConditions.urlContains("inventory"));
        Assert.assertTrue(productPage.isUserOnProductsPage(),
                "Login did not redirect to the products page");

        // ── Step 2: Add product to cart ────────────────────────────────────
        productPage.addProductToCart();

        // ── Step 3: Navigate to cart and verify ───────────────────────────
        productPage.openCart();
        wait.until(ExpectedConditions.urlContains("cart"));
        Assert.assertTrue(cartPage.verifyCartPage(),
                "Cart page was not displayed after clicking the cart icon");

        // ── Step 4: Proceed through checkout ──────────────────────────────
        cartPage.clickCheckout();
        wait.until(ExpectedConditions.urlContains("checkout-step-one"));
        checkoutPage.enterUserDetails("Narasimha", "QA", "500001");

        // FIX 2: Ensure we are on step-one before clicking continue
        wait.until(ExpectedConditions.urlContains("checkout-step-one"));
        checkoutPage.continueCheckout();

        wait.until(ExpectedConditions.urlContains("checkout-step-two")); // Line 56
        checkoutPage.finishOrder();
        wait.until(ExpectedConditions.urlContains("checkout-complete"));

        // ── Step 5: Validate order confirmation ───────────────────────────
        String confirmationMessage = checkoutPage.getOrderSuccessMessage();
        Assert.assertTrue(
                confirmationMessage.toLowerCase().contains("thank you"),
                "Order confirmation not shown. Actual message: ["
                        + confirmationMessage + "]");
    }
}