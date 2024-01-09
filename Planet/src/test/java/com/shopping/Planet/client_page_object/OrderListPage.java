package com.shopping.Planet.client_page_object;

import com.shopping.Planet.DTO.ProductDTO;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class OrderListPage {
    protected WebDriver driver;

    public OrderListPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void open(String url) {
        driver.get(url);
    }

    public void close() {
        driver.quit();
    }

    private List<WebElement> nameList;

    private List<WebElement> statusList;

    private List<WebElement> totalList;

    public void verifyOrder(ProductDTO productDTO) {
        String name = productDTO.getName();
        int idxLocation = -1;
        boolean nameContains = false;
        boolean statusContains = false;
        boolean priceContains = false;
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.name("nameInOrders")));
        nameList = driver.findElements(By.name("nameInOrders"));
        for (int i = 0; i < nameList.size(); i++) {
            if (nameList.get(i).getText().equals(name)) {
                nameContains = true;
                idxLocation = i;
            }
        }

        statusList = driver.findElements(By.name("statusInOrders"));
        totalList = driver.findElements(By.name("totalInOrders"));
        if (idxLocation != -1) {
            statusContains = statusList.get(idxLocation).getText().equals("PLACED");
            priceContains = totalList.get(idxLocation).getText().equals(productDTO.getPrice().toString());

        }

        assertThat(nameContains, is(true));
        assertThat(statusContains, is(true));
        assertThat(priceContains, is(true));
    }


}
