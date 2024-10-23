package Tests;

import Base.BaseTest;
import Pages.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class ShoppingTests extends BaseTest {

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
    }

    @Test(priority = 10)
    public void shoppingNoBacksTest() {
        userLogin();
        Assert.assertEquals(inventoryPage.getTitleProducts().getText(), "Products");
        Assert.assertTrue(inventoryPage.getProductSortContainer().isDisplayed());
        Assert.assertTrue(inventoryPage.getInventoryContainer().isDisplayed());
        //1. Adding 5 products to the cart:
        inventoryPage.getBackpackAddToCartButton().click();
        inventoryPage.getBikeLightAddToCartButton().click();
        inventoryPage.getBoltTSHirtAddToCartButton().click();
        inventoryPage.getFleeceJacketAddtoCartButton().click();
        inventoryPage.getOnesieAddtoCartButton().click();
        Assert.assertEquals(swagsLabsSidebar.getCartIcon().getText(), "5");
        //2. Removing 1 product from the cart:
        inventoryPage.getBackpackRemoveButton().click();
        Assert.assertEquals(swagsLabsSidebar.getCartIcon().getText(), "4");
        //3. Going to the CartPage:
        swagsLabsSidebar.getCartIcon().click();
        //4. Removing 2 product from the cart on the CartPage:
        Assert.assertEquals(cartPage.getTitleYourCart().getText(), "Your Cart");
        Assert.assertTrue(cartPage.getCheckoutButton().isDisplayed());
        cartPage.getBikeLightRemoveCartButton().click();
        cartPage.getFleeceJacketRemoveCartButton().click();
        Assert.assertEquals(swagsLabsSidebar.getCartIcon().getText(), "2");
        //5. Going to the CheckoutStepOnePage:
        cartPage.getCheckoutButton().click();
        //6. Entering data in the fields on the CheckoutStepOnePage:
        Assert.assertTrue(checkoutStepOnePage.getContinueButton().isDisplayed());
        Assert.assertEquals(checkoutStepOnePage.getTitleCheckoutYourInformation().getText(), "Checkout: Your Information");
        checkoutStepOnePage.inputFirstName("John");
        checkoutStepOnePage.inputLastName("Doe");
        checkoutStepOnePage.inputZip("18106");
        //7. Going to the CheckoutStepTwoPage:
        checkoutStepOnePage.getContinueButton().click();
        //8. Going to the CheckoutCompletePage:
        Assert.assertEquals(checkOutStepTwoPage.getTitleCheckoutOverview().getText(), "Checkout: Overview");
        Assert.assertTrue(checkOutStepTwoPage.getFinishButton().isDisplayed());
        checkOutStepTwoPage.getFinishButton().click();
        //9. Returning to the InventoryPage:
        Assert.assertTrue(checkoutCompletePage.getGetHomeButton().isDisplayed());
        Assert.assertEquals(checkoutCompletePage.getTitleCheckoutComplete().getText(), "Checkout: Complete!");
        checkoutCompletePage.getGetHomeButton().click();
        Assert.assertTrue(inventoryPage.getProductSortContainer().isDisplayed());
        Assert.assertTrue(inventoryPage.getInventoryContainer().isDisplayed());
    }

    @Test(priority = 20)
    public void shoppingWithBacksTest() {
        userLogin();
        Assert.assertEquals(inventoryPage.getTitleProducts().getText(), "Products");

//1. Adding 6 products to the cart:
        Assert.assertEquals(inventoryPage.getTitleProducts().getText(), "Products");
        Assert.assertTrue(inventoryPage.getProductSortContainer().isDisplayed());
        inventoryPage.getBackpackAddToCartButton().click();
        inventoryPage.getBikeLightAddToCartButton().click();
        inventoryPage.getBoltTSHirtAddToCartButton().click();
        inventoryPage.getFleeceJacketAddtoCartButton().click();
        inventoryPage.getOnesieAddtoCartButton().click();
        inventoryPage.gettShirtRedAddtoCartButton().click();
        Assert.assertEquals(swagsLabsSidebar.getCartIcon().getText(), "6");
//2. Removing 1 product from the cart:
        inventoryPage.getBackpackRemoveButton().click();
        Assert.assertEquals(swagsLabsSidebar.getCartIcon().getText(), "5");
//3. Going to the Cart Page:
        swagsLabsSidebar.getCartIcon().click();
        Assert.assertEquals(cartPage.getTitleYourCart().getText(), "Your Cart");
        Assert.assertTrue(cartPage.getCheckoutButton().isDisplayed());
//4. Removing 1 product from the cart on the CartPage:
        cartPage.getBikeLightRemoveCartButton().click();
        Assert.assertEquals(swagsLabsSidebar.getCartIcon().getText(), "4");
//5. Returning from the CartPage to the InventoryPage (clicking the Continue Shopping button):
        cartPage.getContinueShoppingButton().click();
//6. Adding 1 product to the cart on the InventoryPage:
        Assert.assertEquals(inventoryPage.getTitleProducts().getText(), "Products");
        Assert.assertTrue(inventoryPage.getProductSortContainer().isDisplayed());
        inventoryPage.getBackpackAddToCartButton().click();
        Assert.assertEquals(swagsLabsSidebar.getCartIcon().getText(), "5");
//7. Going to the CartPage:
        swagsLabsSidebar.getCartIcon().click();
//8. Going to the CheckoutStepOnePage (clicking the Checkout button):
        Assert.assertEquals(cartPage.getTitleYourCart().getText(), "Your Cart");
        Assert.assertTrue(cartPage.getCheckoutButton().isDisplayed());
        cartPage.getCheckoutButton().click();
        Assert.assertTrue(checkoutStepOnePage.getContinueButton().isDisplayed());
        Assert.assertEquals(checkoutStepOnePage.getTitleCheckoutYourInformation().getText(), "Checkout: Your Information");
//9. Going to the CartPage (clicking the Cancel button):
        checkoutStepOnePage.getCancelButton().click();
//10. Going to the CheckoutStepOne Page (clicking the Checkout button):
        Assert.assertEquals(cartPage.getTitleYourCart().getText(), "Your Cart");
        Assert.assertTrue(cartPage.getCheckoutButton().isDisplayed());
        cartPage.getCheckoutButton().click();
//11. Entering data in the fields on the CheckoutStepOnePage:
        Assert.assertTrue(checkoutStepOnePage.getContinueButton().isDisplayed());
        Assert.assertEquals(checkoutStepOnePage.getTitleCheckoutYourInformation().getText(), "Checkout: Your Information");
        checkoutStepOnePage.inputFirstName("Nikola");
        checkoutStepOnePage.inputLastName("Stojanovic");
        checkoutStepOnePage.inputZip("18106");
//12. Going to the CheckoutStepTwoPage (clicking the Continue button):
        checkoutStepOnePage.getContinueButton().click();
        Assert.assertEquals(checkOutStepTwoPage.getTitleCheckoutOverview().getText(), "Checkout: Overview");
        Assert.assertTrue(checkOutStepTwoPage.getFinishButton().isDisplayed());
//13. Going to the InventoryPage (clicking the Cancel button):
        checkOutStepTwoPage.getCancelButton().click();
        Assert.assertEquals(inventoryPage.getTitleProducts().getText(), "Products");
        Assert.assertTrue(inventoryPage.getProductSortContainer().isDisplayed());
//14. Going to the CartPage:
        swagsLabsSidebar.getCartIcon().click();
        Assert.assertEquals(cartPage.getTitleYourCart().getText(), "Your Cart");
        Assert.assertTrue(cartPage.getCheckoutButton().isDisplayed());
//15. Going to the CheckoutStepOnePage (clicking the Checkout button):
        cartPage.getCheckoutButton().click();
        Assert.assertTrue(checkoutStepOnePage.getContinueButton().isDisplayed());
        Assert.assertEquals(checkoutStepOnePage.getTitleCheckoutYourInformation().getText(), "Checkout: Your Information");
//16. Entering data in the fields on the CheckoutStepOnePage:
        checkoutStepOnePage.inputFirstName("Nikola");
        checkoutStepOnePage.inputLastName("Stojanovic");
        checkoutStepOnePage.inputZip("18106");
//17. Going to the CheckoutStepTwo Page (clicking the Continue button):
        checkoutStepOnePage.getContinueButton().click();
        Assert.assertEquals(checkOutStepTwoPage.getTitleCheckoutOverview().getText(), "Checkout: Overview");
        Assert.assertTrue(checkOutStepTwoPage.getFinishButton().isDisplayed());
//18. Going to the CheckoutCompletePage (clicking the Finish button):
        checkOutStepTwoPage.getFinishButton().click();
        Assert.assertTrue(checkoutCompletePage.getGetHomeButton().isDisplayed());
        Assert.assertEquals(checkoutCompletePage.getTitleCheckoutComplete().getText(), "Checkout: Complete!");
//19. Going to the InventoryPage (clicking the Back Home button):
        checkoutCompletePage.getGetHomeButton().click();
        Assert.assertTrue(inventoryPage.getProductSortContainer().isDisplayed());
        Assert.assertTrue(inventoryPage.getInventoryContainer().isDisplayed());
    }

    @AfterMethod
    public void tearDown() {
        swagsLabsSidebar.clickOnHamburgerMenu();
        wait.until(ExpectedConditions.visibilityOf(swagsLabsSidebar.getResetAppStateLink()));
        swagsLabsSidebar.getResetAppStateLink().click();
        wait.until(ExpectedConditions.visibilityOf(swagsLabsSidebar.getLogoutLink()));
        swagsLabsSidebar.getLogoutLink().click();
        driver.quit();
    }

}
