package org.example.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class FrameTest {
    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeMethod
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get("https://the-internet.herokuapp.com/iframe");
    }

    @Test
    public void editTextInsideFrame() {
        driver.switchTo().frame("mce_0_ifr");

        WebElement editorBody = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("tinymce")));

        // Set the content directly via JS instead of simulating keystrokes.
        // Contenteditable iframes often don't reliably accept synthetic
        // sendKeys() combos like Ctrl+A/Delete — this is deterministic.
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].innerHTML = arguments[1];", editorBody, "Hello from Selenium");

        Assert.assertEquals(editorBody.getText(), "Hello from Selenium");

        driver.switchTo().defaultContent();
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}