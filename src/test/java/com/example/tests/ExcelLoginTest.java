
package com.example.tests;

import com.example.BaseTest;
import com.example.pages.LoginPage;
import com.example.utilities.ExcelUtils;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class ExcelLoginTest extends BaseTest {

    @DataProvider(name = "loginData", parallel = true)
    public Object[][] getLoginData() throws Exception {

        String filePath =
                "src/test/resources/TestData.xlsx";

        return ExcelUtils.getExcelData(filePath);
    }

    @Test(dataProvider = "loginData")
    public void verifyLoginWithExcelData(
            String username,
            String password,
            String expectedResult) {

        LoginPage loginPage = new LoginPage(getDriver());

        loginPage.login(username, password);

        if (expectedResult.equalsIgnoreCase("Success")) {

            Assert.assertTrue(
                    getDriver().getCurrentUrl().contains("inventory.html"),
                    "Valid login failed for: " + username
            );

            System.out.println(
                    "PASSED: Valid login - " + username
            );

        } else {

            String error = loginPage.getErrorMessage();

            Assert.assertFalse(
                    error.isEmpty(),
                    "Expected login error was not displayed!"
            );

            System.out.println(
                    "PASSED: Invalid login verified - " + username
            );
        }
    }
}
