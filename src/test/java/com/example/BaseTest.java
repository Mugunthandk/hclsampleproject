
package com.example;

import com.example.utilities.Log;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;

import java.time.Duration;

public class BaseTest {

    private static ThreadLocal<WebDriver> driver =
            new ThreadLocal<>();

    @BeforeMethod
    public void setUp() {

        Log.info("Starting Chrome Browser - Thread: "
                + Thread.currentThread().getId());

        try {

            ChromeOptions options = new ChromeOptions();
            options.addArguments("--headless=new");
            options.addArguments("--disable-gpu");
            options.addArguments("--window-size=1920,1080");

            WebDriver browser = new ChromeDriver(options);

            driver.set(browser);

            getDriver().manage().timeouts()
                    .implicitlyWait(Duration.ofSeconds(10));

            getDriver().get("https://www.saucedemo.com/");

            Log.info("Website Opened Successfully");

        } catch (Exception e) {

            Log.error("Browser Setup Failed: " + e.getMessage());
            throw e;
        }
    }

    @AfterMethod
    public void tearDown() {

        if (getDriver() != null) {

            getDriver().quit();

            Log.info("Browser Closed Successfully - Thread: "
                    + Thread.currentThread().getId());

            driver.remove();
        }
    }

    public WebDriver getDriver() {
        return driver.get();
    }
}
