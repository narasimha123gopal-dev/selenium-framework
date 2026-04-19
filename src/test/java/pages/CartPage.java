package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

/**
 * CartPage represents the shopping cart screen.
 * Provides actions and verifications for cart-related test scenarios.
 */
public class CartPage {

    private final WebDriver driver;
    private final WebDriverWait wait;
    private final JavascriptExecutor js;

    // Locators
    private final By checkoutBtn = By.id("checkout");
    private final By cartItem    = By.className("cart_item");

    public CartPage(WebDriver driver) {
        this.driver = driver;
        this.wait   = new WebDriverWait(driver, Duration.ofSeconds(15));
        this.js     = (JavascriptExecutor) driver;
    }

    /**
     * Clicks the Checkout button to proceed to the checkout flow.
     */
    public void clickCheckout() {
        WebElement btn = wait.until(ExpectedConditions
                .presenceOfElementLocated(checkoutBtn));
        js.executeScript("arguments[0].scrollIntoView(true);", btn);
        js.executeScript("arguments[0].click();", btn);
    }

    /**
     * Confirms the user is on the cart page by checking the current URL.
     */
    public boolean verifyCartPage() {
        return driver.getCurrentUrl().contains("cart");
    }

    /**
     * Returns true if no items are present in the cart.
     */
    public boolean isCartEmpty() {
        return driver.findElements(cartItem).isEmpty();
    }
}