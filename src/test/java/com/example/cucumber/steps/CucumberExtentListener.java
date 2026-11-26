package com.example.cucumber.steps;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import io.cucumber.plugin.ConcurrentEventListener;
import io.cucumber.plugin.event.EventPublisher;
import io.cucumber.plugin.event.TestCaseStarted;
import io.cucumber.plugin.event.TestCaseFinished;
import io.cucumber.plugin.event.TestStepFinished;
import io.cucumber.plugin.event.Result;
import io.cucumber.plugin.event.Status;

import java.io.File;
import java.util.concurrent.ConcurrentHashMap;
import java.util.Map;

public class CucumberExtentListener implements ConcurrentEventListener {

    private static ExtentReports extent;
    private static Map<String, ExtentTest> tests = new ConcurrentHashMap<>();

    public CucumberExtentListener() {

        new File("test-output").mkdirs();

        ExtentSparkReporter reporter =
                new ExtentSparkReporter("test-output/CucumberExtentReport.html");

        reporter.config().setDocumentTitle("Cucumber Automation Report");
        reporter.config().setReportName("HCL Cucumber Test Results");

        extent = new ExtentReports();
        extent.attachReporter(reporter);
    }

    @Override
    public void setEventPublisher(EventPublisher publisher) {

        publisher.registerHandlerFor(
                TestCaseStarted.class,
                this::testCaseStarted
        );

        publisher.registerHandlerFor(
                TestStepFinished.class,
                this::testStepFinished
        );

        publisher.registerHandlerFor(
                TestCaseFinished.class,
                this::testCaseFinished
        );
    }

    private void testCaseStarted(TestCaseStarted event) {

        String id = event.getTestCase().getId().toString();

        ExtentTest test = extent.createTest(
                event.getTestCase().getName()
        );

        tests.put(id, test);
    }

    private void testStepFinished(TestStepFinished event) {

        String id = event.getTestCase().getId().toString();
        ExtentTest test = tests.get(id);

        if (test == null) return;

        Result result = event.getResult();

        if (result.getStatus() == Status.PASSED) {
            test.pass("Step Passed");
        } else if (result.getStatus() == Status.FAILED) {
            test.fail(result.getError());
        } else if (result.getStatus() == Status.SKIPPED) {
            test.skip("Step Skipped");
        }
    }

    private void testCaseFinished(TestCaseFinished event) {

        String id = event.getTestCase().getId().toString();
        ExtentTest test = tests.remove(id);

        if (test == null) return;

        if (event.getResult().getStatus() == Status.PASSED) {
            test.pass("Scenario Passed");
        } else {
            test.fail("Scenario Failed");
        }

        synchronized (extent) {
            extent.flush();
        }
    }
}