package com.shopping.Planet.client_page_object;

import com.shopping.Planet.Domain.User;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class UserInfoPage {
    protected WebDriver driver;

    public UserInfoPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void open(String url) {
        driver.get(url);
    }

    public void close() {
        driver.quit();
    }

    @FindBy(id = "username")
    private WebElement username;

    @FindBy(id = "email")
    private WebElement email;

    @FindBy(id = "phone")
    private WebElement phone;

    @FindBy(id = "city")
    private WebElement city;

    @FindBy(id = "street")
    private WebElement street;

    @FindBy(id = "zip")
    private WebElement zip;

    @FindBy(id = "submit")
    private WebElement submit;

    public PaymentPage FillAndSubmitUserData(User user) {
        username.sendKeys(user.getUsername());
        email.sendKeys(user.getEmail());
        phone.sendKeys(user.getPhone());
        city.sendKeys(user.getCity());
        street.sendKeys(user.getStreet());
        zip.sendKeys(user.getZip());
        submit.click();
        return new PaymentPage(driver);
    }
}
