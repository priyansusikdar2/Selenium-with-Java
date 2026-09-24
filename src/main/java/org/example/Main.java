package org.example;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Main {
    public static void main(String[] args) {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");

        WebDriver driver = new ChromeDriver(options);

        try {
            driver.get("https://www.saucedemo.com/");

            System.out.println("Title : " + driver.getTitle());
            System.out.println("URL   : " + driver.getCurrentUrl());

            if (!"Swag Labs".equals(driver.getTitle())) {
                System.out.println("FAIL: unexpected page title");
            } else {
                System.out.println("PASS: landed on the expected page");
            }

            Thread.sleep(2000); // just so you can see it — never do this in real tests
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            driver.quit();
        }
    }
}