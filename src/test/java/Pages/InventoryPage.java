package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class InventoryPage {
    public WebDriver driver;
    public WebDriverWait wait;
    WebElement inventoryContainer;
    WebElement backpackAddToCartButton;
    WebElement backpackRemoveButton;
    WebElement bikeLightAddToCartButton;
    WebElement bikeLightRemoveFromCartButton;
    WebElement boltTSHirtAddToCartButton;
    WebElement boltTShirtRemoveFromCartButton;
    WebElement fleeceJacketAddtoCartButton;
    WebElement fleeceJacketRemoveFromCartButton;
    WebElement onesieAddtoCartButton;
    WebElement onesieRemoveFromCartButton;
    WebElement tShirtRedAddtoCartButton;
    WebElement redTShirtRemoveFromCartButton;
    WebElement titleProducts;
    WebElement productSortContainer;
    WebElement twitterLink;
    WebElement facebookLink;
    WebElement linkedinLink;

    public InventoryPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public WebElement getInventoryContainer() {
        return driver.findElement(By.id("inventory_container"));
    }

    public WebElement gettShirtRedAddtoCartButton() {
        return driver.findElement(By.id("add-to-cart-test.allthethings()-t-shirt-(red)"));
    }

    public WebElement getOnesieAddtoCartButton() {
        return driver.findElement(By.id("add-to-cart-sauce-labs-onesie"));
    }

    public WebElement getFleeceJacketAddtoCartButton() {
        return driver.findElement(By.id("add-to-cart-sauce-labs-fleece-jacket"));
    }

    public WebElement getBoltTSHirtAddToCartButton() {
        return driver.findElement(By.id("add-to-cart-sauce-labs-bolt-t-shirt"));
    }

    public WebElement getBikeLightAddToCartButton() {
        return driver.findElement(By.id("add-to-cart-sauce-labs-bike-light"));
    }

    public WebElement getBackpackAddToCartButton() {
        return driver.findElement(By.id("add-to-cart-sauce-labs-backpack"));
    }

    public WebElement getTitleProducts() {
        return driver.findElement(By.className("title"));
    }

    public WebElement getProductSortContainer() {
        return driver.findElement(By.className("product_sort_container"));
    }

    // Geteri za Footer buttons
    public WebElement getTwitterLink() {
        return driver.findElement(By.className("social_twitter"));
    }

    public WebElement getFacebookLink() {
        return driver.findElement(By.className("social_facebook"));
    }

    public WebElement getLinkedinLink() {
        return driver.findElement(By.className("social_linkedin"));
    }

    // Geteri za REMOVE buttons
    public WebElement getBackpackRemoveButton() {
        return driver.findElement(By.id("remove-sauce-labs-backpack"));
    }

    public WebElement getBikeLightRemoveFromCartButton() {
        return driver.findElement(By.id("remove-sauce-labs-bike-light"));
    }

    public WebElement getBoltTShirtRemoveFromCartButton() {
        return driver.findElement(By.id("remove-sauce-labs-bolt-t-shirt"));
    }

    public WebElement getFleeceJacketRemoveFromCartButton() {
        return driver.findElement(By.id("remove-sauce-labs-fleece-jacket"));
    }

    public WebElement getOnesieRemoveFromCartButton() {
        return driver.findElement(By.id("remove-sauce-labs-onesie"));
    }

    public WebElement getRedTShirtRemoveFromCartButton() {
        return driver.findElement(By.id("remove-test.allthethings()-t-shirt-(red)"));
    }
    //-------------------------------------------------
    // Sorting Products
    public WebElement getSortDropdown() {
        return driver.findElement(By.className("product_sort_container"));
    }

    public List<WebElement> getItemNames() {
        return driver.findElements(By.className("inventory_item_name"));
    }

    public List<WebElement> getItemPrices() {
        return driver.findElements(By.className("inventory_item_price"));
    }

    public void selectSortOptionByValue(String value) {
        WebElement dropdown = getSortDropdown();
        dropdown.click();
        dropdown.findElement(By.cssSelector("option[value='" + value + "']")).click();
    }

    public boolean isSortedByNameAsc() {
        List<WebElement> items = getItemNames();
        for (int i = 0; i < items.size() - 1; i++) {
            if (items.get(i).getText().compareTo(items.get(i + 1).getText()) > 0) {
                return false;
            }
        }
        return true;
    }

    public boolean isSortedByNameDesc() {
        List<WebElement> items = getItemNames();
        for (int i = 0; i < items.size() - 1; i++) {
            if (items.get(i).getText().compareTo(items.get(i + 1).getText()) < 0) {
                return false;
            }
        }
        return true;
    }

    public boolean isSortedByPriceAsc() {
        List<WebElement> items = getItemPrices();
        for (int i = 0; i < items.size() - 1; i++) {
            double price1 = Double.parseDouble(items.get(i).getText().replace("$", ""));
            double price2 = Double.parseDouble(items.get(i + 1).getText().replace("$", ""));
            if (price1 > price2) {
                return false;
            }
        }
        return true;
    }

    public boolean isSortedByPriceDesc() {
        List<WebElement> items = getItemPrices();
        for (int i = 0; i < items.size() - 1; i++) {
            double price1 = Double.parseDouble(items.get(i).getText().replace("$", ""));
            double price2 = Double.parseDouble(items.get(i + 1).getText().replace("$", ""));
            if (price1 < price2) {
                return false;
            }
        }
        return true;
    }
}

