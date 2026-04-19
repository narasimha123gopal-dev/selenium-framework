package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

/**
 * CheckoutPage covers the multi-step checkout flow:
 * Step 1 — Enter personal details
 * Step 2 — Review order summary
 * Step 3 — Order confirmation
 */
public class CheckoutPage {

    private final WebDriver driver;
    private final WebDriverWait wait;
    private final JavascriptExecutor js;

    // Locators
    private final By firstName   = By.id("first-name");
    private final By lastName    = By.id("last-name");
    private final By zipCode     = By.id("postal-code");
    private final By continueBtn = By.id("continue");
    private final By finishBtn   = By.id("finish");
    private final By successText = By.className("complete-header");

    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
        this.wait   = new WebDriverWait(driver, Duration.ofSeconds(15));
        this.js     = (JavascriptExecutor) driver;
    }

    /**
     * Fills in the checkout form with the provided user details.
     * Waits for the form to be visible before interacting.
     */
    public void enterUserDetails(String fName, String lName, String zip) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(firstName));

        clearAndType(firstName, fName);
        clearAndType(lastName, lName);
        clearAndType(zipCode, zip);
    }

    /**
     * Submits the details form and proceeds to the order summary page.
     */
    public void continueCheckout() {
        jsClick(continueBtn);
    }

    /**
     * Confirms and places the order on the summary page.
     */
    public void finishOrder() {
        jsClick(finishBtn);
    }

    /**
     * Waits for and returns the order confirmation message.
     * Used to validate successful order completion.
     */
    public String getOrderSuccessMessage() {
        WebElement confirmation = wait.until(ExpectedConditions
                .visibilityOfElementLocated(successText));
        return confirmation.getText().trim();
    }

    // ── Private helpers ───────────────────────────────────────

    private void clearAndType(By locator, String text) {
        WebElement field = driver.findElement(locator);
        field.clear();
        field.sendKeys(text);
    }

    private void jsClick(By locator) {
        WebElement btn = wait.until(ExpectedConditions
                .presenceOfElementLocated(locator));
        js.executeScript("arguments[0].scrollIntoView(true);", btn);
        js.executeScript("arguments[0].click();", btn);
    }
}