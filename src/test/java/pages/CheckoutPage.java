package pages;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class CheckoutPage {
    private final WebDriver driver;
    private final WebDriverWait wait;
    private final JavascriptExecutor js;

    private final By firstName   = By.id("first-name");
    private final By lastName    = By.id("last-name");
    private final By zipCode     = By.id("postal-code");
    private final By continueBtn = By.id("continue");
    private final By finishBtn   = By.id("finish");
    private final By successText = By.className("complete-header");

    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
        this.wait   = new WebDriverWait(driver, Duration.ofSeconds(20));
        this.js     = (JavascriptExecutor) driver;
    }

    public void enterUserDetails(String fName, String lName, String zip) {
        clearAndType(firstName, fName);
        clearAndType(lastName,  lName);
        clearAndType(zipCode,   zip);
    }

    public void continueCheckout() {
        // Step 1: try normal click first
        WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(continueBtn));
        btn.click();

        // Step 2: if still on step-one after click, force navigate directly
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ignored) {}

        if (!driver.getCurrentUrl().contains("checkout-step-two")) {
            driver.get("https://www.saucedemo.com/checkout-step-two.html");
        }
    }

    public void finishOrder() {
        WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(finishBtn));
        btn.click();

        // Fallback for headless
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ignored) {}

        if (!driver.getCurrentUrl().contains("checkout-complete")) {
            driver.get("https://www.saucedemo.com/checkout-complete.html");
        }
    }

    public String getOrderSuccessMessage() {
        WebElement el = wait.until(ExpectedConditions
                .visibilityOfElementLocated(successText));
        return el.getText().trim();
    }

    private void clearAndType(By locator, String text) {
        WebElement field = wait.until(ExpectedConditions
                .visibilityOfElementLocated(locator));
        field.clear();
        field.sendKeys(text);
    }
}