package com.qa.opencart.tests;

import com.qa.opencart.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import javax.print.DocFlavor;
import java.util.Map;

public class ProductInfoTest extends BaseTest {
    @BeforeClass
    public void accountPageSetup(){
        accountsPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password") );
    }

    public void goToProductInfo(String productName){
        accountsPage.doSearch(productName);
        productInfoPage = accountsPage.selectProductFromResult(productName);
    }
    @Test
    public void productInfoPageTitleTest(){
        goToProductInfo("iMac");
        Assert.assertEquals(productInfoPage.getProductInfoPageTitle("iMac"), "iMac");
    }
    @Test
    public void verifyProductImageTest(){
        goToProductInfo("iMac");
        Assert.assertTrue(productInfoPage.getProductimages()==3);
    }
    @Test
    public void verifyProductInfoTest(){
        String productName = "iMac";
        goToProductInfo(productName);
        Map<String,String> productInfoMap = productInfoPage.getProductInformation();

        productInfoMap.forEach((k,v)-> System.out.println(k+" "+v));
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(productInfoMap.get("Product"),productName);
        softAssert.assertEquals(productInfoMap.get("Brand"),"Apple");
        softAssert.assertEquals(productInfoMap.get("Product Code"),"Product 14");
        softAssert.assertEquals(productInfoMap.get("Availability"),"Out Of Stock");
        softAssert.assertEquals(productInfoMap.get("Price"),"$100.00");
        softAssert.assertEquals(productInfoMap.get("ExTaxPrice"),"$100.00");

        softAssert.assertAll();
    }
}
