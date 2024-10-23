package Tests;

import Base.BaseTest;
import Pages.*;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class SortingProductTests extends BaseTest {

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

    @Test(priority = 10) //Test ID 16 (user can sort items on inventory page using option "Name (A to Z)")
    public void testSortByNameAsc() {
        inventoryPage.selectSortOptionByValue("az");
        Assert.assertTrue(inventoryPage.isSortedByNameAsc());
        Assert.assertEquals(driver.findElement(By.className("active_option")).getText(), "Name (A to Z)");
    }

    @Test(priority = 20) // Test ID 17 (user can sort items on inventory page using option "Name (Z to A)")
    public void testSortByNameDesc() {
        inventoryPage.selectSortOptionByValue("za");
        Assert.assertTrue(inventoryPage.isSortedByNameDesc());
        Assert.assertEquals(driver.findElement(By.className("active_option")).getText(), "Name (Z to A)");
    }

    @Test(priority = 30) // Test ID 18 (user can sort items on inventory page using option "Price (low to high)")
    public void testSortByPriceAsc() {
        inventoryPage.selectSortOptionByValue("lohi");
        Assert.assertTrue(inventoryPage.isSortedByPriceAsc());
        Assert.assertEquals(driver.findElement(By.className("active_option")).getText(), "Price (low to high)");
    }

    @Test(priority = 40) // Test ID 19 (can sort items on inventory page using option "Price (high to low)")
    public void testSortByPriceDesc() {
        inventoryPage.selectSortOptionByValue("hilo");
        Assert.assertTrue(inventoryPage.isSortedByPriceDesc());
        Assert.assertEquals(driver.findElement(By.className("active_option")).getText(), "Price (high to low)");
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

}
