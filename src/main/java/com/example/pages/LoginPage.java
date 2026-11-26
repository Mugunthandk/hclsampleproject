
package com.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {

    private WebDriver driver;

    // Web Elements
    private By usernameField = By.id("user-name");
    private By passwordField = By.id("password");
    private By loginButton = By.id("login-button");
    private By errorMessage = By.cssSelector("h3[data-test='error']");

    // Constructor
    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    // Enter Username
    public void enterUsername(String username) {
        driver.findElement(usernameField).sendKeys(username);
    }

    // Enter Password
    public void enterPassword(String password) {
        driver.findElement(passwordField).sendKeys(password);
    }

    // Click Login
    public void clickLogin() {
        driver.findElement(loginButton).click();
    }

    // Login Action
    public void login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLogin();
    }

    // Get Error Message
    public String getErrorMessage() {
        return driver.findElement(errorMessage).getText();
    }
}
