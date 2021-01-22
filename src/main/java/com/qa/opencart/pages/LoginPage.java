package com.qa.opencart.pages;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage{
    private WebDriver driver;
    private ElementUtil eleUtil;

    private By emailId = By.id("input-email");
    private By password = By.id("input-password");
    private By loginBtn = By.cssSelector("input[value='Login']");
    private By forgotPswdLink =  By.cssSelector("div.form-group a");
    private By registerButton = By.linkText("Register");

    public LoginPage(WebDriver driver){
        this.driver = driver;
        eleUtil = new ElementUtil(this.driver);
    }

    public String getLoginPageTitle(){
        return eleUtil.waitForPageTitlePresence(Constants.LOGIN_PAGE_TITLE,5);
    }

    public Boolean isForgotPaswdLinkDisplayed(){

        return eleUtil.isElementDisplayed(forgotPswdLink);
    }

    public AccountsPage doLogin(String un, String pswd){
        eleUtil.doSendKeys(un,emailId);
        eleUtil.doSendKeys(pswd,password);
        eleUtil.doClick(loginBtn);

        return new AccountsPage(driver);
    }

    public RegisterPage navigateToRegisterPage(){
        eleUtil.doClick(registerButton);
        return new RegisterPage(driver);
    }


}
