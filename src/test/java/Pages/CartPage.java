package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CartPage {
    public WebDriver driver;
    public WebDriverWait wait;
    WebElement bikeLightRemoveCartButton;
    WebElement backpackRemoveCartButton;
    WebElement boltTShirtRemoveCartButton;
    WebElement fleeceJacketRemoveCartButton;
    WebElement onesieRemoveCartButton;
    WebElement redTShirtRemoveCartButton;
    WebElement continueShoppingButton;
    WebElement checkoutButton;
    WebElement titleYourCart;

    public CartPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public WebElement getBikeLightRemoveCartButton() {
        return driver.findElement(By.id("remove-sauce-labs-bike-light"));
    }

    public WebElement getBackpackRemoveCartButton() {
        return driver.findElement(By.id("remove-sauce-labs-backpack"));
    }

    public WebElement getBoltTShirtRemoveCartButton() {
        return driver.findElement(By.id("remove-sauce-labs-bolt-t-shirt"));
    }

    public WebElement getFleeceJacketRemoveCartButton() {
        return driver.findElement(By.id("remove-sauce-labs-fleece-jacket"));
    }

    public WebElement getOnesieRemoveCartButton() {
        return driver.findElement(By.id("remove-sauce-labs-onesie"));
    }

    public WebElement getRedTShirtRemoveCartButton() {
        return driver.findElement(By.id("remove-test.allthethings()-t-shirt-(red)"));
    }
//-------------------------------------------------------------------------

    public WebElement getContinueShoppingButton() {
        return driver.findElement(By.id("continue-shopping"));
    }

    public WebElement getCheckoutButton() {
        return driver.findElement(By.id("checkout"));
    }

    public WebElement getTitleYourCart() {
        return driver.findElement(By.className("title"));
    }
}
