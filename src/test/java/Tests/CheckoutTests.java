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

public class CheckoutTests extends BaseTest {
    @BeforeMethod
    public void pageSetUp() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        homePage = new HomePage(driver, wait);
        swagsLabsSidebar = new SwagsLabsSidebar(driver, wait);
        inventoryPage = new InventoryPage(driver, wait);
        cartPage = new CartPage(driver, wait);
        checkoutStepOnePage = new CheckoutStepOnePage(driver, wait);
        checkOutStepTwoPage = new CheckOutStepTwoPage(driver, wait);
        checkoutCompletePage = new CheckoutCompletePage(driver, wait);
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com/");
        userLogin();
    }

    @Test //Test ID 5
    public void userCanBeSentToCartPageAfterAddingItemToTheCart() {
        Assert.assertTrue(inventoryPage.getInventoryContainer().isDisplayed());
        inventoryPage.getOnesieAddtoCartButton().click();
        Assert.assertEquals(swagsLabsSidebar.getCartIcon().getText(), "1");
        swagsLabsSidebar.getCartIcon().click();
        Assert.assertTrue(cartPage.getCheckoutButton().isDisplayed());
    }

    @Test //Test ID 6
    public void userCanReturnFromCartPageToInventoryPage() {
        inventoryPage.getOnesieAddtoCartButton().click();
        Assert.assertEquals(swagsLabsSidebar.getCartIcon().getText(), "1");
        swagsLabsSidebar.getCartIcon().click();
        Assert.assertTrue(cartPage.getCheckoutButton().isDisplayed());
        cartPage.getContinueShoppingButton().click();
        Assert.assertTrue(inventoryPage.getProductSortContainer().isDisplayed());
    }

    @Test //Test ID 7
    public void userCanGoToCheckoutStepOnePageAfterAddingItemToTheCart() {
        inventoryPage.getOnesieAddtoCartButton().click();
        inventoryPage.gettShirtRedAddtoCartButton().click();
        Assert.assertEquals(swagsLabsSidebar.getCartIcon().getText(), "2");
        swagsLabsSidebar.getCartIcon().click();
        Assert.assertTrue(cartPage.getCheckoutButton().isDisplayed());
        cartPage.getCheckoutButton().click();
        Assert.assertTrue(checkoutStepOnePage.getContinueButton().isDisplayed());
    }

    @Test //Test ID 8
    public void userCanInputDataOnCheckoutStepOnePageAndGoToCheckoutStepTwoPage() {
        inventoryPage.getOnesieAddtoCartButton().click();
        inventoryPage.gettShirtRedAddtoCartButton().click();
        Assert.assertEquals(swagsLabsSidebar.getCartIcon().getText(), "2");
        swagsLabsSidebar.getCartIcon().click();
        Assert.assertTrue(cartPage.getCheckoutButton().isDisplayed());
        cartPage.getCheckoutButton().click();
        Assert.assertTrue(checkoutStepOnePage.getContinueButton().isDisplayed());
        checkoutStepOnePage.inputFirstName("Nikola");
        checkoutStepOnePage.inputLastName("Stojanovic");
        checkoutStepOnePage.inputZip("18106");
    }

    @Test //Test ID 9
    public void userCanFinishOrderAndGoToCheckoutCompletePage() {
        inventoryPage.getOnesieAddtoCartButton().click();
        inventoryPage.gettShirtRedAddtoCartButton().click();
        Assert.assertEquals(swagsLabsSidebar.getCartIcon().getText(), "2");
        swagsLabsSidebar.getCartIcon().click();
        Assert.assertTrue(cartPage.getCheckoutButton().isDisplayed());
        cartPage.getCheckoutButton().click();
        Assert.assertTrue(checkoutStepOnePage.getContinueButton().isDisplayed());
        checkoutStepOnePage.inputFirstName("Nikola");
        checkoutStepOnePage.inputLastName("Stojanovic");
        checkoutStepOnePage.inputZip("18106");
        checkoutStepOnePage.getContinueButton().click();
        Assert.assertEquals(checkOutStepTwoPage.getTitleCheckoutOverview().getText(), "Checkout: Overview");
        Assert.assertTrue(checkOutStepTwoPage.getFinishButton().isDisplayed());
    }

    @Test //Test ID 10
    public void userAfterSuccessfulOrderCanGoToInventoryPage() {
        inventoryPage.getOnesieAddtoCartButton().click();
        inventoryPage.gettShirtRedAddtoCartButton().click();
        Assert.assertEquals(swagsLabsSidebar.getCartIcon().getText(), "2");
        swagsLabsSidebar.getCartIcon().click();
        Assert.assertTrue(cartPage.getCheckoutButton().isDisplayed());
        cartPage.getCheckoutButton().click();
        Assert.assertTrue(checkoutStepOnePage.getContinueButton().isDisplayed());
        checkoutStepOnePage.inputFirstName("Nikola");
        checkoutStepOnePage.inputLastName("Stojanovic");
        checkoutStepOnePage.inputZip("18106");
        checkoutStepOnePage.getContinueButton().click();
        Assert.assertEquals(checkOutStepTwoPage.getTitleCheckoutOverview().getText(), "Checkout: Overview");
        Assert.assertTrue(checkOutStepTwoPage.getFinishButton().isDisplayed());
        checkOutStepTwoPage.getCancelButton().click();
        Assert.assertEquals(inventoryPage.getTitleProducts().getText(), "Products");
        Assert.assertTrue(inventoryPage.getProductSortContainer().isDisplayed());
    }

    @Test //Test ID 11
    public void userCanGoBackFromCheckoutStepOnePageToCartPage() {
        inventoryPage.getOnesieAddtoCartButton().click();
        inventoryPage.gettShirtRedAddtoCartButton().click();
        Assert.assertEquals(swagsLabsSidebar.getCartIcon().getText(), "2");
        swagsLabsSidebar.getCartIcon().click();
        Assert.assertTrue(cartPage.getCheckoutButton().isDisplayed());
        cartPage.getCheckoutButton().click();
        checkoutStepOnePage.getCancelButton().click();
        Assert.assertEquals(cartPage.getTitleYourCart().getText(), "Your Cart");
        Assert.assertTrue(cartPage.getCheckoutButton().isDisplayed());
    }

    @Test //Test ID 12
    public void userCanGoBackFromCheckoutStepTwoPageToInventoryPage() {
        inventoryPage.getOnesieAddtoCartButton().click();
        inventoryPage.gettShirtRedAddtoCartButton().click();
        Assert.assertEquals(swagsLabsSidebar.getCartIcon().getText(), "2");
        swagsLabsSidebar.getCartIcon().click();
        Assert.assertTrue(cartPage.getCheckoutButton().isDisplayed());
        cartPage.getCheckoutButton().click();
        Assert.assertTrue(checkoutStepOnePage.getContinueButton().isDisplayed());
        checkoutStepOnePage.inputFirstName("Nikola");
        checkoutStepOnePage.inputLastName("Stojanovic");
        checkoutStepOnePage.inputZip("18106");
        checkOutStepTwoPage.getCancelButton().click();
        cartPage.getContinueShoppingButton().click();
        Assert.assertEquals(inventoryPage.getTitleProducts().getText(), "Products");
        Assert.assertTrue(inventoryPage.getProductSortContainer().isDisplayed());
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
