package com.shopping.Planet.client_page_object;

import com.shopping.Planet.DTO.ProductDTO;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class ProductListPage {
    protected WebDriver driver;

    public ProductListPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void open(String url) {
        driver.get(url);
    }

    public void close() {
        driver.quit();
    }

    @FindBy(id = "productNumber")
    private WebElement productNumber;

    @FindBy(id = "name")
    private WebElement name;

    @FindBy(id = "price")
    private WebElement price;

    @FindBy(id = "description")
    private WebElement description;

    @FindBy(id = "stock")
    private WebElement stock;

    @FindBy(id = "addProductBtn")
    private WebElement addProductBtn;

    private WebElement numberInList;

    private WebElement nameInList;

    private WebElement priceInList;

    @FindBy(id = "backHomeBtnUserLanding")
    private WebElement backHomeButton;

    @FindBy(id = "backHomeProductsBtn")
    private WebElement backHomeProductsButton;

    public void submitAndVerifyProduct(ProductDTO productDTO) {
        productNumber.sendKeys(productDTO.getProductNumber());
        name.sendKeys(productDTO.getName());
        price.sendKeys(productDTO.getPrice().toString());
        description.sendKeys(productDTO.getDescription());
        stock.sendKeys(String.valueOf(productDTO.getStock()));
        addProductBtn.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.alertIsPresent());
        Alert alert = driver.switchTo().alert();
        alert.accept();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("numberInList" + productDTO.getProductNumber())));
        numberInList = driver.findElement(By.id("numberInList" + productDTO.getProductNumber()));
        nameInList = driver.findElement(By.id("nameInList" + productDTO.getProductNumber()));
        priceInList = driver.findElement(By.id("priceInList" + productDTO.getProductNumber()));

        assertThat(numberInList.getText(), is(productDTO.getProductNumber()));
        assertThat(nameInList.getText(), is(productDTO.getName()));
        assertThat(priceInList.getText(), is(productDTO.getPrice().toString()));
    }

    public HomePage clickBackToHome() {
        backHomeButton.click();
        return new HomePage(driver);
    }

    public HomePage clickBackHomeInEmployee() {
        backHomeProductsButton.click();
        return new HomePage(driver);
    }
}
