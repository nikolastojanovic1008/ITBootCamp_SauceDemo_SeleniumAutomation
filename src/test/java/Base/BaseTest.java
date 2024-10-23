package Base;

import Pages.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.io.IOException;
import java.time.Duration;

public class BaseTest {
    public WebDriver driver;
    public WebDriverWait wait;
    public HomePage homePage;
    public SwagsLabsSidebar swagsLabsSidebar;
    public InventoryPage inventoryPage;
    public CartPage cartPage;
    public CheckoutStepOnePage checkoutStepOnePage;
    public CheckOutStepTwoPage checkOutStepTwoPage;
    public CheckoutCompletePage checkoutCompletePage;
    public ExcelReader excelReader;


    @BeforeClass
    public void setUp() throws IOException {
        WebDriverManager.chromedriver().setup();
        excelReader = new ExcelReader("Credentials.xlsx");
    }


    @AfterClass
    public void end() {
    }

    public void userLogin() {
        homePage.inputUsername("standard_user");
        homePage.inputPassword("secret_sauce");
        homePage.clickOnLoginButton();
    }

}






