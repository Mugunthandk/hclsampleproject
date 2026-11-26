
package com.example.cucumber.steps;

import com.example.pages.LoginPage;
import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;

public class LoginSteps {

    private LoginPage loginPage;

    @Given("user opens the login page")
    public void user_opens_the_login_page() {

        loginPage = new LoginPage(
            Hooks.baseTest.getDriver()
        );

        System.out.println("Login page opened successfully");
    }

    @When("user enters valid credentials")
    public void user_enters_valid_credentials() {

        loginPage.enterUsername("standard_user");
        loginPage.enterPassword("secret_sauce");
    }

    @When("user enters invalid credentials")
    public void user_enters_invalid_credentials() {

        loginPage.enterUsername("invalid_user");
        loginPage.enterPassword("wrong_password");
    }

    @When("clicks the login button")
    public void clicks_the_login_button() {

        loginPage.clickLogin();
    }

    @Then("user should reach the products page")
    public void user_should_reach_the_products_page() {

        Assert.assertTrue(
            Hooks.baseTest.getDriver()
                .getCurrentUrl()
                .contains("inventory.html"),
            "Login failed!"
        );

        System.out.println("Cucumber: Valid login passed");
    }

    @Then("an error message should be displayed")
    public void an_error_message_should_be_displayed() {

        String error = loginPage.getErrorMessage();

        Assert.assertFalse(
            error.isEmpty(),
            "Error message was not displayed!"
        );

        System.out.println("Cucumber: Invalid login passed");
    }
}
