package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {
    public WebDriver driver;
    public WebDriverWait wait;
    WebElement usernameField;
    WebElement passwordField;
    WebElement loginButton;
    WebElement errorMessage;

    public HomePage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public WebElement getUsernameField() {
        return driver.findElement(By.id("user-name"));
    }

    public WebElement getPasswordField() {
        return driver.findElement(By.id("password"));
    }

    public WebElement getLoginButton() {
        return driver.findElement(By.id("login-button"));
    }

    public WebElement getErrorMessage() {
        return driver.findElement(By.cssSelector(".error-message-container.error"));
    }


//---------------

    public void inputUsername(String username) {
        getUsernameField().clear();
        getUsernameField().sendKeys(username);
    }

    public void inputPassword(String password) {
        getUsernameField().clear();
        getPasswordField().sendKeys(password);
    }

    public void clickOnLoginButton() {
        getLoginButton().click();
    }


}




