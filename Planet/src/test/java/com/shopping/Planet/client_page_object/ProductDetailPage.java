package com.shopping.Planet.client_page_object;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProductDetailPage {
    protected WebDriver driver;

    public ProductDetailPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void open(String url) {
        driver.get(url);
    }

    public void close() {
        driver.quit();
    }

    @FindBy(id = "addItemBtn")
    private WebElement addItemButton;

    @FindBy(id = "gotoCartBtn")
    private WebElement gotoCartButton;

    @FindBy(id = "gotoCheckoutBtn")
    private WebElement gotoCheckoutButton;

    public UserInfoPage addItemAndCheckout() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("addItemBtn")));
        addItemButton.click();
        gotoCartButton.click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("gotoCheckoutBtn")));
        gotoCheckoutButton.click();
        return new UserInfoPage(driver);
    }
}
