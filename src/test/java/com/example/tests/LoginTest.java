
package com.example.tests;

import com.example.BaseTest;
import com.example.pages.LoginPage;

import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    @Test
    public void verifySuccessfulLogin() {

        LoginPage loginPage = new LoginPage(getDriver());

        loginPage.login("standard_user", "secret_sauce");

        String currentUrl = getDriver().getCurrentUrl();

        Assert.assertTrue(
            currentUrl.contains("inventory.html"),
            "Login failed!"
        );

        System.out.println("SUCCESS: User logged in successfully!");
    }

    @Test
    public void verifyInvalidLogin() {

        LoginPage loginPage = new LoginPage(getDriver());

        loginPage.login("invalid_user", "wrong_password");

        String error = loginPage.getErrorMessage();

        Assert.assertTrue(
            error.contains("Username and password do not match"),
            "Invalid login error not displayed!"
        );

        System.out.println("SUCCESS: Invalid login verified!");
    }
}
