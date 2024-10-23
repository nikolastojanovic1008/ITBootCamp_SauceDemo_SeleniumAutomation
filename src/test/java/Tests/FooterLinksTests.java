package Tests;

import Base.BaseTest;
import Pages.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class FooterLinksTests extends BaseTest {
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

    @Test (priority = 10) //Test ID 21.1
    public void userCanGoToSaucedemoTwitterPage() {
        Assert.assertTrue(inventoryPage.getTwitterLink().isDisplayed(), "Twitter link should be visible.");
        Assert.assertTrue(inventoryPage.getTwitterLink().isEnabled(), "Twitter link should be enabled.");
        inventoryPage.getTwitterLink().click();
        String originalWindow = driver.getWindowHandle();
        for (String windowHandle : driver.getWindowHandles()) {
            driver.switchTo().window(windowHandle);
        }
        Assert.assertTrue(driver.getCurrentUrl().contains("x.com"), "URL should contain 'x.com'.");
        driver.switchTo().window(originalWindow);
    }

    @Test (priority = 20) // Test ID 21.2
    public void userCanGoToSaucedemoFacebookPage() {
        Assert.assertTrue(inventoryPage.getFacebookLink().isDisplayed(), "Facebook link should be visible.");
        Assert.assertTrue(inventoryPage.getFacebookLink().isEnabled(), "Facebook link should be enabled.");
        inventoryPage.getFacebookLink().click();
        String originalWindow = driver.getWindowHandle();
        for (String windowHandle : driver.getWindowHandles()) {
            driver.switchTo().window(windowHandle);
        }
        Assert.assertTrue(driver.getCurrentUrl().contains("facebook"), "URL should contain 'facebook'.");
        driver.switchTo().window(originalWindow);
    }


    @Test (priority = 20) // Test ID 21.3
    public void userCanGoToSaucedemoLinkedinPage() {
        Assert.assertTrue(inventoryPage.getLinkedinLink().isDisplayed(), "LinkedIn link should be visible.");
        Assert.assertTrue(inventoryPage.getLinkedinLink().isEnabled(), "LinkedIn link should be enabled.");
        inventoryPage.getLinkedinLink().click();
        String originalWindow = driver.getWindowHandle();
        for (String windowHandle : driver.getWindowHandles()) {
            driver.switchTo().window(windowHandle);
        }
        Assert.assertTrue(driver.getCurrentUrl().contains("linkedin"), "URL should contain 'linkedin'.");
        driver.switchTo().window(originalWindow);
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
