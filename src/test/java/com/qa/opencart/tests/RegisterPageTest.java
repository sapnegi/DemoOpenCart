package com.qa.opencart.tests;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ExcelUtil;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class RegisterPageTest extends BaseTest {
    @BeforeClass
    public void registerPageSetUp(){
        registerPage= loginPage.navigateToRegisterPage();
    }

    @DataProvider
    public Object[][] getRegistrationData(){
        Object data[][] = ExcelUtil.getTestData(Constants.REGISTER_SHEET_NAME);
        return data;
    }
    @Test(dataProvider = "getRegistrationData")
    public void userRegistrationTest(String firstName, String LastName,String phone,
                                     String password,String subscribe){
        Assert.assertTrue(registerPage.accountRegistration(firstName,LastName,phone,
                password,subscribe));
    }
}
