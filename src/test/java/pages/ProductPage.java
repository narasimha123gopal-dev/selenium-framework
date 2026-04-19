package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

/**
 * ProductPage handles interactions on the product inventory screen.
 * Uses JavaScript click to reliably interact with React-rendered buttons.
 */
public class ProductPage {

    private final WebDriver driver;
    private final WebDriverWait wait;
    private final JavascriptExecutor js;

    // Locators
    private final By addBackpackBtn = By.id("add-to-cart-sauce-labs-backpack");
    private final By cartBadge      = By.className("shopping_cart_badge");
    private final By cartIcon       = By.className("shopping_cart_link");

    public ProductPage(WebDriver driver) {
        this.driver = driver;
        this.wait   = new WebDriverWait(driver, Duration.ofSeconds(15));
        this.js     = (JavascriptExecutor) driver;
    }

    /**
     * Adds the Sauce Labs Backpack to the cart.
     * Confirms success by waiting for the cart badge to appear.
     */
    public void addProductToCart() {
        WebElement addBtn = wait.until(ExpectedConditions
                .presenceOfElementLocated(addBackpackBtn));
        jsClick(addBtn);

        // Cart badge appearing confirms the item was successfully added
        wait.until(ExpectedConditions
                .presenceOfElementLocated(cartBadge));
    }

    /**
     * Navigates to the shopping cart.
     */
    public void openCart() {
        WebElement cartLink = wait.until(ExpectedConditions
                .presenceOfElementLocated(cartIcon));
        jsClick(cartLink);
    }

    /**
     * Verifies the user has landed on the inventory/products page.
     */
    public boolean isUserOnProductsPage() {
        return driver.getCurrentUrl().contains("inventory");
    }

    /**
     * Scrolls element into view and fires a JavaScript click.
     * Required for React-rendered components where native Selenium clicks
     * may not register reliably.
     */
    private void jsClick(WebElement element) {
        js.executeScript("arguments[0].scrollIntoView(true);", element);
        js.executeScript("arguments[0].click();", element);
    }
}