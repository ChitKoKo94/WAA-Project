package com.shopping.Planet.client_page_object;

import org.openqa.selenium.WebDriver;

public class UserLandingPage {
    protected WebDriver driver;

    public UserLandingPage(WebDriver driver) {
        this.driver = driver;
    }

    public void open(String url) {
        driver.get(url);
    }

    public void close() {
        driver.quit();
    }
}
