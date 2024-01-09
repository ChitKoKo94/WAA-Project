package com.shopping.Planet.client_page_object;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
    protected WebDriver driver;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void open(String url) {
        driver.get(url);
    }

    public void close() {
        driver.quit();
    }

    @FindBy(id = "employeeBtn")
    private WebElement employeeButton;

    @FindBy(id = "userBtn")
    private WebElement userButton;

    public void clickEmployee() {
        employeeButton.click();
    }

    public void clickUser() {
        userButton.click();
    }

    public ProductListPage clickManageProducts() {
        driver.findElement(By.id("productsBtn")).click();
        return new ProductListPage(driver);
    }

    public ProductDetailPage clickGoToProduct(String id) {
        driver.findElement(By.id(id)).click();
        return new ProductDetailPage(driver);
    }

    public OrderListPage clickManageOrders() {
        driver.findElement(By.id("ordersBtn")).click();
        return new OrderListPage(driver);
    }
}
