package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

public class CartPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By cartItemNames = By.className("inventory_item_name");

    public CartPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public List<String> getCartItemNames() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(cartItemNames));
        return driver.findElements(cartItemNames)
                .stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }
}