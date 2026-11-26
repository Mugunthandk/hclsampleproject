
package com.example.tests;

import com.example.BaseTest;

import org.testng.Assert;
import org.testng.annotations.Test;

public class BrowserTest extends BaseTest {

    @Test
    public void verifyWebsiteLaunch() {

        String title = getDriver().getTitle();

        System.out.println("Website Title: " + title);

        Assert.assertTrue(
                title.contains("Swag Labs"),
                "Website title verification failed!"
        );
    }
}
