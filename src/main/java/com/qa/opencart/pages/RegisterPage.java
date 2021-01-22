package com.qa.opencart.pages;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegisterPage {

    private WebDriver driver;
    private ElementUtil eleUtil;

    private By firstNameText = By.cssSelector("#input-firstname");
    private By lastNameText = By.cssSelector("#input-lastname");
    private By eMailText = By.cssSelector("#input-email");
    private By telephoneText = By.cssSelector("#input-telephone");
    private By passwordText = By.cssSelector("#input-password");
    private By confirmPasswordText = By.cssSelector("#input-confirm");
    private By subscribeYesOption = By.cssSelector(".radio-inline input[value='1']");
    private By subscribeNoOption = By.cssSelector(".radio-inline input[value='0']");
    private By privatePolicyCheckBox = By.name("agree");
    private By continueButton = By.xpath("//input[@value='Continue']");
    private By registerButton = By.linkText("Register");
    private By logOutButton = By.linkText("Logout");
    private By successMsgLabel = By.cssSelector("#content h1");

     RegisterPage(WebDriver driver){
        this.driver = driver;
        eleUtil = new ElementUtil(this.driver);
    }

    public boolean accountRegistration(String firstNameText,String lastNameText,String telephoneText,
                                       String passwordText,String subscribe){
        String eMailText = "PETE"+eleUtil.generateRandomNumber()+"@gmail.com";
        eleUtil.doSendKeys(firstNameText,this.firstNameText);
        eleUtil.doSendKeys(lastNameText,this.lastNameText);
        eleUtil.doSendKeys(eMailText,this.eMailText);
        eleUtil.doSendKeys(telephoneText,this.telephoneText);
        eleUtil.doSendKeys(passwordText,this.passwordText);
        eleUtil.doSendKeys(passwordText,confirmPasswordText);

        if(subscribe.equalsIgnoreCase("Yes"))
            eleUtil.doClick(subscribeYesOption);
        else
            eleUtil.doClick(subscribeNoOption);

        eleUtil.doClick(privatePolicyCheckBox);
        eleUtil.doClick(continueButton);

        if(eleUtil.doGetText(successMsgLabel).contains(Constants.ACCOUNT_SUCCESS_MSG)){
            eleUtil.doClick(logOutButton);
            eleUtil.doClick(registerButton);

            return true;
        }

        return false;
    }

}
