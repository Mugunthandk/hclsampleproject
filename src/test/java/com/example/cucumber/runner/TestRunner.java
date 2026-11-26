package com.example.cucumber.runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
    features = "src/test/resources/features",
    glue = "com.example.cucumber.steps",
    plugin = {
        "pretty",
        "html:test-output/cucumber-report.html",
        "com.example.cucumber.steps.CucumberExtentListener"
    },
    monochrome = true
)
public class TestRunner extends AbstractTestNGCucumberTests {
}