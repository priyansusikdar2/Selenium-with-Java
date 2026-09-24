package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginTest {
    public static void main(String[] args) {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        WebDriver driver = new ChromeDriver(options);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            driver.get("https://www.saucedemo.com/");

            WebElement username = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(By.id("user-name")));
            WebElement password = driver.findElement(By.id("password"));
            WebElement loginBtn = driver.findElement(By.id("login-button"));

            username.sendKeys("standard_user");
            password.sendKeys("secret_sauce");
            loginBtn.click();

            // Confirm login worked by checking we landed on the inventory page
            WebElement pageTitle = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(By.className("title")));

            if (pageTitle.getText().equals("Products")) {
                System.out.println("PASS: login succeeded, landed on Products page");
            } else {
                System.out.println("FAIL: unexpected page after login — " + pageTitle.getText());
            }

        } finally {
            driver.quit();
        }
    }
}