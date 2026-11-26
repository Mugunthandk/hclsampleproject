
package com.example.utilities;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ScreenshotUtils {

    public static String captureScreenshot(
            WebDriver driver, String testName) throws IOException {

        String timestamp = LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));

        Path directory = Paths.get("test-output", "screenshots");

        Files.createDirectories(directory);

        File source = ((TakesScreenshot) driver)
                .getScreenshotAs(OutputType.FILE);

        Path destination = directory.resolve(
                testName + "_" + timestamp + ".png"
        );

        Files.copy(source.toPath(), destination);

        return destination.toAbsolutePath().toString();
    }
}
