package Tests;

import Base.BaseTest;
import Pages.HomePage;
import Pages.InventoryPage;
import Pages.SwagsLabsSidebar;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class SwagsLabsSidebarTests extends BaseTest {
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

    @Test //Test ID 14 (Bug - korpa se resetuje/isprazni ali kod itema koji su bili u korpi stoji i dalje Remove
    //button umesto da se vrati na Add to Cart
    public void userCanResetCart() {
        inventoryPage.getBackpackAddToCartButton().click();
        inventoryPage.getBikeLightAddToCartButton().click();
        Assert.assertEquals(swagsLabsSidebar.getCartIcon().getText(), "2");
        swagsLabsSidebar.clickOnHamburgerMenu();
        wait.until(ExpectedConditions.visibilityOf(swagsLabsSidebar.getResetAppStateLink()));
        swagsLabsSidebar.getResetAppStateLink().click();
        Assert.assertTrue(inventoryPage.getInventoryContainer().isDisplayed());
    }

    @Test // Test ID 15
    public void userCanGoToSauceLabsPage() {
        swagsLabsSidebar.clickOnHamburgerMenu();
        wait.until(ExpectedConditions.visibilityOf(swagsLabsSidebar.getAboutLink()));
        swagsLabsSidebar.getAboutLink().click();
        Assert.assertEquals(driver.getCurrentUrl(), "https://saucelabs.com/");
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
