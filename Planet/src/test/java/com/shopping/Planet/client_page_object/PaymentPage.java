package com.shopping.Planet.client_page_object;

import com.shopping.Planet.Domain.Payment;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PaymentPage {
    protected WebDriver driver;

    public PaymentPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void open(String url) {
        driver.get(url);
    }

    public void close() {
        driver.quit();
    }

    @FindBy(id = "number")
    private WebElement number;

    @FindBy(id = "type")
    private WebElement type;

    @FindBy(id = "validationCode")
    private WebElement validationCode;

    @FindBy(id = "submitPaymentBtn")
    private WebElement submitPaymentButton;

    public ProductListPage submitPayment(Payment payment) {
        number.sendKeys(payment.getNumber());
        type.sendKeys(payment.getType());
        validationCode.sendKeys(String.valueOf(payment.getValidationCode()));
        submitPaymentButton.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.alertIsPresent());

        Alert alert = driver.switchTo().alert();
        alert.accept();
        return new ProductListPage(driver);
    }


}
