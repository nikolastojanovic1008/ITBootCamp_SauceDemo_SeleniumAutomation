package Tests;

import Base.BaseTest;
import Pages.HomePage;
import Pages.InventoryPage;
import Pages.SwagsLabsSidebar;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class CartFunctionalityTests extends BaseTest {
    @BeforeMethod
    public void pageSetUp() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        homePage = new HomePage(driver, wait);
        swagsLabsSidebar = new SwagsLabsSidebar(driver, wait);
        inventoryPage = new InventoryPage(driver, wait);
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com/");
        userLogin();
    }

    @Test //Test ID 3
    public void userCanAddOneItemToTheCart() {
        inventoryPage.getBackpackAddToCartButton().click();
        Assert.assertTrue(inventoryPage.getBackpackRemoveButton().isDisplayed());
        Assert.assertEquals(swagsLabsSidebar.getCartIcon().getText(), "1");
    }

    @Test //Test ID 13
    public void testAddMultipleItemsToCart() {
        inventoryPage.getBackpackAddToCartButton().click();
        inventoryPage.getBikeLightAddToCartButton().click();

        Assert.assertTrue(inventoryPage.getBackpackRemoveButton().isDisplayed());
        Assert.assertTrue(inventoryPage.getBikeLightRemoveFromCartButton().isDisplayed());
        Assert.assertEquals(swagsLabsSidebar.getCartIcon().getText(), "2");
    }

    @Test //Test ID 4
    public void userCanRemoveOneItemFromTheCart() {
        inventoryPage.getBackpackAddToCartButton().click();
        inventoryPage.getBackpackRemoveButton().click();

        Assert.assertTrue(inventoryPage.getBackpackAddToCartButton().isDisplayed());
        Assert.assertTrue(inventoryPage.getOnesieAddtoCartButton().isDisplayed());
    }

    @Test
    public void testAddAllItemsToCart() {
        inventoryPage.getBackpackAddToCartButton().click();
        inventoryPage.getBikeLightAddToCartButton().click();
        inventoryPage.getBoltTSHirtAddToCartButton().click();
        inventoryPage.getFleeceJacketAddtoCartButton().click();
        inventoryPage.getOnesieAddtoCartButton().click();
        inventoryPage.gettShirtRedAddtoCartButton().click();

        Assert.assertTrue(inventoryPage.getBackpackRemoveButton().isDisplayed());
        Assert.assertTrue(inventoryPage.getBikeLightRemoveFromCartButton().isDisplayed());
        Assert.assertEquals(swagsLabsSidebar.getCartIcon().getText(), "6");
    }

    @Test
    public void testAddAllThenRemoveThreeItemsFromCart() {
        inventoryPage.getBackpackAddToCartButton().click();
        inventoryPage.getBikeLightAddToCartButton().click();
        inventoryPage.getBoltTSHirtAddToCartButton().click();
        inventoryPage.getFleeceJacketAddtoCartButton().click();
        inventoryPage.getOnesieAddtoCartButton().click();
        inventoryPage.gettShirtRedAddtoCartButton().click();
        Assert.assertEquals(swagsLabsSidebar.getCartIcon().getText(), "6");
        // Remove three items
        inventoryPage.getBackpackRemoveButton().click();
        inventoryPage.getBikeLightRemoveFromCartButton().click();
        inventoryPage.getBoltTShirtRemoveFromCartButton().click();
        Assert.assertEquals(swagsLabsSidebar.getCartIcon().getText(), "3");
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
