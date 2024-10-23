package Tests;

import Base.BaseTest;
import Base.ExcelReader;
import Pages.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.time.Duration;

public class LoginAndLogoutTests extends BaseTest {

    @BeforeMethod
    public void pageSetUp() throws IOException {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        homePage = new HomePage(driver, wait);
        swagsLabsSidebar = new SwagsLabsSidebar(driver, wait);
        inventoryPage = new InventoryPage(driver, wait);
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com/");
    }

    @Test(priority = 10) //Test ID 1
    public void userCanLogInWithValidCredentials() {
        String validUsername = excelReader.getStringData("Credentials", 1, 0);
        String validPassword = excelReader.getStringData("Credentials", 1, 1);
        homePage.inputUsername(validUsername);
        homePage.inputPassword(validPassword);
        homePage.clickOnLoginButton();
        Assert.assertTrue(swagsLabsSidebar.getHamburgerMenu().isDisplayed());
        Assert.assertTrue(swagsLabsSidebar.getCartIcon().isDisplayed());
        Assert.assertTrue(inventoryPage.getInventoryContainer().isDisplayed());
    }

    @Test(priority = 20) //Test ID 2
    public void userCanLogout() {
        userLogin();
        swagsLabsSidebar.clickOnHamburgerMenu();
        wait.until(ExpectedConditions.visibilityOf(swagsLabsSidebar.getLogoutLink()));
        swagsLabsSidebar.getLogoutLink().click();
        Assert.assertTrue(homePage.getUsernameField().isDisplayed());
        Assert.assertTrue(homePage.getLoginButton().isDisplayed());
    }

    @Test(priority = 30) //Test ID 1.1
    public void userCanNotLoginWithInvalidUsername() {
        homePage.inputUsername("invalid username");
        homePage.inputPassword("secret_sauce");
        homePage.clickOnLoginButton();
        Assert.assertTrue(homePage.getErrorMessage().isDisplayed());
        Assert.assertTrue(homePage.getLoginButton().isDisplayed());
        Assert.assertEquals(homePage.getErrorMessage().getText(), "Epic sadface: Username and password do not match any user in this service");
    }

    @Test(priority = 40) //Test ID 1.2
    public void userCanNotLoginWithInvalidPassword() {
        homePage.inputUsername("standard_user");
        homePage.inputPassword("invalid password");
        homePage.clickOnLoginButton();
        Assert.assertTrue(homePage.getErrorMessage().isDisplayed());
        Assert.assertTrue(homePage.getLoginButton().isDisplayed());
        Assert.assertEquals(homePage.getErrorMessage().getText(), "Epic sadface: Username and password do not match any user in this service");
    }

    @Test(priority = 50) //Test 1.3
    public void userCanNotLoginWithEmptyUsernameField() {
        homePage.inputUsername("");
        homePage.inputPassword("secret_sauce");
        homePage.clickOnLoginButton();
        Assert.assertTrue(homePage.getErrorMessage().isDisplayed());
        Assert.assertTrue(homePage.getLoginButton().isDisplayed());
        Assert.assertEquals(homePage.getErrorMessage().getText(), "Epic sadface: Username is required");
    }

    @Test(priority = 60) //Test ID 1.4
    public void userCanNotLoginWithEmptyPasswordField() {
        homePage.inputUsername("standard_user");
        homePage.inputPassword("");
        homePage.clickOnLoginButton();
        Assert.assertTrue(homePage.getErrorMessage().isDisplayed());
        Assert.assertTrue(homePage.getLoginButton().isDisplayed());
        Assert.assertEquals(homePage.getErrorMessage().getText(), "Epic sadface: Password is required");
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
