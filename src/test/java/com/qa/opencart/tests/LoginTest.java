package com.qa.opencart.tests;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.pages.AccountsPage;
import com.qa.opencart.utils.Constants;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    @Test
    public void loginPageTitleTest(){
        Assert.assertEquals(loginPage.getLoginPageTitle(), Constants.LOGIN_PAGE_TITLE); }
    @Test
    public void forgotPswdLinkTest(){
        Assert.assertTrue(loginPage.isForgotPaswdLinkDisplayed());
    }
    @Test
    public void loginTest(){
        accountsPage = loginPage.doLogin(prop.getProperty("username"),prop.getProperty("password"));
        Assert.assertEquals(accountsPage.getAccountPageTitle(),Constants.ACCOUNT_PAGE_TITLE);
    }


}
