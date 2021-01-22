package com.qa.opencart.tests;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.utils.Constants;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

public class AccountTest extends BaseTest {

    @BeforeClass
    public void accountPageSetup(){
        accountsPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password") );
    }
    @Test(priority=1)
    public void accountPageTitleTest(){
        Assert.assertEquals(accountsPage.getAccountPageTitle(), Constants.ACCOUNT_PAGE_TITLE);
    }

    @Test(priority=2)
    public void verifyAccountPageHeaderTest(){
        Assert.assertEquals(accountsPage.getAccountPageHeader(),Constants.ACCOUNT_PAGE_HEADER);
    }

    @Test(priority=3)
    public void verifyAccountSectionCountTest(){
        Assert.assertTrue(accountsPage.getAccountSectionCount()==Constants.ACCOUNT_SECTION_COUNT);
    }

    @Test(priority=4)
    public void verifyAccountPageSectionListTest(){
        List<String> accSecList = accountsPage.getAccountSectionList();
        System.out.println("the account list is : " + accSecList);
        Assert.assertEquals(accSecList,Constants.getAccSecList());
    }

    @DataProvider
    public Object[][] searchData(){
        return  new Object[][] {{"imac"},
                {"iphone"},
                {"macbook"}};
    }

    @Test(priority = 5, dataProvider = "searchData")
    public void searchProductTest(String productName){
        Assert.assertTrue(accountsPage.doSearch(productName));
    }

    @Test(priority = 6)
    public void verifyProductResultTest(){
        accountsPage.doSearch("macBook");
        accountsPage.selectProductFromResult("imac");
    }
}
