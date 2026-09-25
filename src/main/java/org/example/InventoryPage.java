package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class InventoryPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By pageTitle = By.className("title");
    private final By addToCartBackpackBtn = By.id("add-to-cart-sauce-labs-backpack");
    private final By cartBadge = By.className("shopping_cart_badge");
    private final By cartLink = By.className("shopping_cart_link");

    public InventoryPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public String getTitle() {
        WebElement title = wait.until(ExpectedConditions.visibilityOfElementLocated(pageTitle));
        return title.getText();
    }

    public void addBackpackToCart() {
        WebElement addBtn = wait.until(ExpectedConditions.elementToBeClickable(addToCartBackpackBtn));
        addBtn.click();
    }

    public String getCartItemCount() {
        WebElement badge = wait.until(ExpectedConditions.visibilityOfElementLocated(cartBadge));
        return badge.getText();
    }

    public void goToCart() {
        driver.findElement(cartLink).click();
    }
}