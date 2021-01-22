package com.qa.opencart.pages;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class AccountsPage {
    private WebDriver driver;
    ElementUtil eleUtil;
    private By accountPageHeader = By.cssSelector("div#logo a");
    private By accountSectionHeaders = By.cssSelector("div#content h2");
    private By searchText = By.cssSelector("input[name=\'search\']");
    private By searchButton= By.cssSelector("div#search button");
    private By searchItemResult = By.cssSelector("div.product-layout div.product-thumb");
    private By itemsList = By.cssSelector("div.product-thumb h4 a");

    AccountsPage(WebDriver driver){
        this.driver = driver;
        eleUtil = new ElementUtil(this.driver);
    }

    public String getAccountPageTitle(){
        return eleUtil.waitForPageTitlePresence(Constants.ACCOUNT_PAGE_TITLE,5);
    }

    public String getAccountPageHeader(){
        if (eleUtil.isElementDisplayed(accountPageHeader))
            return eleUtil.doGetText(accountPageHeader);
        else
            return null;
    }

    public int getAccountSectionCount(){
        return eleUtil.getElements(accountSectionHeaders).size();
    }

    public List<String> getAccountSectionList(){
        List<String> accList = new ArrayList<>();
        List<WebElement> accSectionList = eleUtil.getElements(accountSectionHeaders);
        for(WebElement e: accSectionList){
            accList.add(e.getText());
        }
        return accList;
    }

    public boolean doSearch(String productName){
        eleUtil.doSendKeys(productName,searchText);
        eleUtil.doClick(searchButton);
        if(eleUtil.getElements(searchItemResult).size()>0){
            return true;}
        return false;
    }

    public ProductInfoPage selectProductFromResult(String productName){
        List<WebElement> resultItemsList = eleUtil.getElements(itemsList);
        for(WebElement e : resultItemsList){
            if(e.getText().equalsIgnoreCase(productName)) {
                e.click();
                break;
            }
        }

        return new ProductInfoPage(driver);
    }
}
