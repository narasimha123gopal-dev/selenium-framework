package pages;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class ProductPage {
    private final WebDriver driver;
    private final WebDriverWait wait;
    private final JavascriptExecutor js;

    private final By addBackpackBtn = By.id("add-to-cart-sauce-labs-backpack");
    private final By cartBadge      = By.className("shopping_cart_badge");
    private final By cartIcon       = By.className("shopping_cart_link");

    public ProductPage(WebDriver driver) {
        this.driver = driver;
        this.wait   = new WebDriverWait(driver, Duration.ofSeconds(15));
        this.js     = (JavascriptExecutor) driver;
    }

    public void addProductToCart() {
        WebElement addBtn = wait.until(ExpectedConditions
                .presenceOfElementLocated(addBackpackBtn));
        jsClick(addBtn);
        wait.until(ExpectedConditions.presenceOfElementLocated(cartBadge));
    }

    /** FIX: was jsClick on presenceOfElementLocated — changed to normal click on elementToBeClickable */
    public void openCart() {
        wait.until(ExpectedConditions.elementToBeClickable(cartIcon)).click();
    }

    public boolean isUserOnProductsPage() {
        return driver.getCurrentUrl().contains("inventory");
    }

    private void jsClick(WebElement element) {
        js.executeScript("arguments[0].scrollIntoView(true);", element);
        js.executeScript("arguments[0].click();", element);
    }
}