package org.example.tests;

import org.example.pages.CartPage;
import org.example.pages.InventoryPage;
import org.example.pages.LoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class ProductTest {
    private WebDriver driver;
    private LoginPage loginPage;
    private InventoryPage inventoryPage;

    @BeforeMethod
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        driver = new ChromeDriver(options);
        loginPage = new LoginPage(driver);
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        inventoryPage = new InventoryPage(driver);
    }

    @Test(groups = "smoke")
    public void addToCartUpdatesBadgeCount() {
        inventoryPage.addBackpackToCart();
        Assert.assertEquals(inventoryPage.getCartItemCount(), "1",
                "Cart badge should show 1 item after adding one product");
    }

    @Test(groups = "smoke")
    public void cartContainsAddedProduct() {
        inventoryPage.addBackpackToCart();
        inventoryPage.goToCart();

        CartPage cartPage = new CartPage(driver);
        List<String> itemNames = cartPage.getCartItemNames();

        Assert.assertTrue(itemNames.contains("Sauce Labs Backpack"),
                "Cart should contain the item that was added");
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}