
package com.example.utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import com.example.BaseTest;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class TestListener implements ITestListener {

    private static ExtentReports extent;

    private static ThreadLocal<ExtentTest> test =
            new ThreadLocal<>();

    @Override
    public synchronized void onStart(ITestContext context) {

        try {
            Files.createDirectories(Paths.get("test-output"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ExtentSparkReporter sparkReporter =
                new ExtentSparkReporter(
                        "test-output/ExtentReport.html"
                );

        sparkReporter.config().setDocumentTitle(
                "E-Commerce Automation Report"
        );

        sparkReporter.config().setReportName(
                "HCL Automation Testing Results"
        );

        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);

        extent.setSystemInfo("Project", "E-Commerce Automation");
        extent.setSystemInfo("Framework", "Selenium + TestNG");
        extent.setSystemInfo("Language", "Java");
    }

    @Override
    public void onTestStart(ITestResult result) {

        ExtentTest extentTest =
                extent.createTest(result.getMethod().getMethodName());

        test.set(extentTest);

        test.get().info(
                "Test started on Thread: "
                + Thread.currentThread().getId()
        );
    }

    @Override
    public void onTestSuccess(ITestResult result) {

        ExtentTest extentTest = test.get();

        if (extentTest != null) {
            extentTest.pass("TEST PASSED");
        }

        test.remove();
    }

    @Override
    public void onTestFailure(ITestResult result) {

        ExtentTest extentTest = test.get();

        if (extentTest != null) {

            extentTest.fail(result.getThrowable());

            Object instance = result.getInstance();

            if (instance instanceof BaseTest) {

                BaseTest base = (BaseTest) instance;

                if (base.getDriver() != null) {

                    try {
                        String screenshot =
                                ScreenshotUtils.captureScreenshot(
                                        base.getDriver(),
                                        result.getMethod().getMethodName()
                                );

                        extentTest.addScreenCaptureFromPath(screenshot);

                    } catch (Exception e) {

                        extentTest.warning(
                                "Screenshot capture failed: "
                                + e.getMessage()
                        );
                    }
                }
            }
        }

        test.remove();
    }

    @Override
    public void onTestSkipped(ITestResult result) {

        ExtentTest extentTest = test.get();

        if (extentTest != null) {
            extentTest.skip("TEST SKIPPED");
        }

        test.remove();
    }

    @Override
    public synchronized void onFinish(ITestContext context) {

        if (extent != null) {
            extent.flush();
        }
    }
}
