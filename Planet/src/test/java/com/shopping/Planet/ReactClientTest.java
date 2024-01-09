package com.shopping.Planet;

import com.shopping.Planet.DTO.ProductDTO;
import com.shopping.Planet.Domain.Payment;
import com.shopping.Planet.Domain.User;
import com.shopping.Planet.client_page_object.*;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.PageFactory;

import java.math.BigDecimal;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class ReactClientTest {
    private static ProductListPage productListPage;
    private static UserLandingPage userLandingPage;
    private static ProductDetailPage productDetailPage;
    private static UserInfoPage userInfoPage;
    private static PaymentPage paymentPage;
    private static OrderListPage orderPage;
    private static HomePage homePage;

    @Before
    public void createWebDriver() {
        // set path to chromedriver.exe
        System.setProperty("webdriver.chrome.driver",
                "/Users/zlatan/Desktop/Testing/chromedriver-mac-x64/chromedriver");
        ChromeOptions options = new ChromeOptions();
        options.setBinary(
                "/Users/zlatan/Desktop/Testing/chrome-headless-shell-mac-x64/chrome-headless-shell");
        options.addArguments("--remote-allow-origins=*");
        // create chrome instance
        WebDriver driver = new ChromeDriver(options);
        homePage = PageFactory.initElements(driver, HomePage.class);
        homePage.open("http://localhost:3000");
    }

    @AfterClass
    public static void closeTheBrowser() {
        homePage.close();
        if (productListPage != null)
            productListPage.close();
        if (userLandingPage != null)
            userLandingPage.close();
        if (productDetailPage != null)
            productDetailPage.close();
        if (userInfoPage != null)
            userInfoPage.close();
        if (paymentPage != null)
            paymentPage.close();
        if (orderPage != null)
            orderPage.close();
    }

    @Test
    public void testCreateProduct() {
        homePage.clickEmployee();
        productListPage = homePage.clickManageProducts();
        ProductDTO productDTO = new ProductDTO(
                "",
                "P00005",
                "Phone",
                BigDecimal.valueOf(1200),
                "Iphone",
                2
        );
        productListPage.submitAndVerifyProduct(productDTO);

    }

    @Test
    public void testCreateCheckout() {
        homePage.clickEmployee();
        productListPage = homePage.clickManageProducts();
        ProductDTO productDTO = new ProductDTO(
                "",
                "P00025",
                "Chair",
                BigDecimal.valueOf(200),
                "gaming chair",
                3
        );
        productListPage.submitAndVerifyProduct(productDTO);
        homePage = productListPage.clickBackHomeInEmployee();
        homePage.clickUser();
        productDetailPage = homePage.clickGoToProduct("gotoProductBtn" + productDTO.getProductNumber());
        userInfoPage = productDetailPage.addItemAndCheckout();
        paymentPage = userInfoPage.FillAndSubmitUserData(new User(
                "Jon", "t@test.com", "202020999", "FF", "4", "43334"
        ));
        productListPage = paymentPage.submitPayment(new Payment(
                "credit", "1234321234444455", null, 123
        ));
        homePage = productListPage.clickBackToHome();
        homePage.clickEmployee();
        orderPage = homePage.clickManageOrders();
        orderPage.verifyOrder(productDTO);
    }
}
