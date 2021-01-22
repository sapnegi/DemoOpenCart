package com.qa.opencart.base;

import com.qa.opencart.factory.DriverFactory;
import com.qa.opencart.pages.AccountsPage;
import com.qa.opencart.pages.LoginPage;
import com.qa.opencart.pages.ProductInfoPage;
import com.qa.opencart.pages.RegisterPage;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.util.Properties;

public class BaseTest {
    WebDriver driver;
    DriverFactory df;
    public Properties prop;
    public LoginPage loginPage;
    public AccountsPage accountsPage;
    public ProductInfoPage productInfoPage;
    public RegisterPage registerPage;
    @BeforeTest
    public void setUp(){
        df = new DriverFactory();
        prop = df.init_prop();
        driver = df.init_driver(prop);
        driver.get(prop.getProperty("url"));
        loginPage = new LoginPage(driver);
    }

    @AfterTest
    public void closeBrowser(){
        driver.quit();
    }
}
