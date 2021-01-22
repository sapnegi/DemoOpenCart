package com.qa.opencart.utils;

import com.qa.opencart.factory.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.List;
import java.util.Random;


public class ElementUtil {
    private WebDriver driver;
    private JavaScriptsUtil jsUtil;

    public ElementUtil(WebDriver driver){
        this.driver = driver;
        jsUtil = new JavaScriptsUtil(this.driver);
    }

    public WebElement getElement(By locator){
        WebElement element = driver.findElement(locator);
        if(DriverFactory.highlight.equals("true")){
            jsUtil.flash(element);
        }
        return element;
    }

    public List<WebElement> getElements(By locator){
        return driver.findElements(locator);
    }

    public String waitForPageTitlePresence(String titleValue, int timeOut){
        WebDriverWait wait = new WebDriverWait(driver, timeOut);
        wait.until(ExpectedConditions.titleContains(titleValue));
        return driver.getTitle();
    }

    public void doSendKeys(String value, By locator){
        WebElement element = getElement(locator);
        element.clear();
        element.sendKeys(value);
    }

    public void doClick(By locator){
        getElement(locator).click();
    }

    public String doGetText(By locator){
        return getElement(locator).getText();
    }

    public boolean isElementDisplayed(By locator){
        return getElement(locator).isDisplayed();
    }

    public int generateRandomNumber(){
        Random rand = new Random();
        return rand.nextInt(100);
    }
}
