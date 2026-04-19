package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

/**
 * LoginPage encapsulates all interactions with the SauceDemo login screen.
 * Follows the Page Object Model (POM) pattern for maintainability.
 */
public class LoginPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    // Locators
    private final By usernameField = By.id("user-name");
    private final By passwordField = By.id("password");
    private final By loginButton   = By.id("login-button");
    private final By errorMessage  = By.cssSelector("[data-test='error']");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait   = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    /**
     * Enters credentials and submits the login form.
     * Clears fields first to prevent stale input from previous interactions.
     */
    public void loginToApp(String username, String password) {
        WebElement userField = wait.until(ExpectedConditions
                .visibilityOfElementLocated(usernameField));
        userField.clear();
        userField.sendKeys(username);

        WebElement passField = driver.findElement(passwordField);
        passField.clear();
        passField.sendKeys(password);

        driver.findElement(loginButton).click();
    }

    /**
     * Waits for and returns the error message text after a failed login attempt.
     */
    public String getErrorMessage() {
        WebElement error = wait.until(ExpectedConditions
                .visibilityOfElementLocated(errorMessage));
        return error.getText().trim();
    }
}