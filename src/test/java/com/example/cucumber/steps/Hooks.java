
package com.example.cucumber.steps;

import com.example.BaseTest;
import io.cucumber.java.Before;
import io.cucumber.java.After;

public class Hooks {

    public static BaseTest baseTest;

    @Before
    public void setUp() {

        baseTest = new BaseTest();
        baseTest.setUp();

        System.out.println("Cucumber Hook: Browser Started");
    }

    @After
    public void tearDown() {

        if (baseTest != null) {
            baseTest.tearDown();
            System.out.println("Cucumber Hook: Browser Closed");
            baseTest = null;
        }
    }
}
