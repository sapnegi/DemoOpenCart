package com.qa.opencart.pages;

import com.qa.opencart.utils.ElementUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.*;

public class ProductInfoPage {
    private WebDriver driver;
    private ElementUtil eleUtil;

    private By productNameHeader = By.cssSelector("#content h1");
    private By productMetaData = By.cssSelector("#content .list-unstyled:nth-of-type(1) li");
    private By priceMetaData = By.cssSelector("#content .list-unstyled:nth-of-type(2) li");
    private By quantityText = By.cssSelector("#input-quantity");
    private By addToCartButton = By.cssSelector("#button-cart");
    private By productImages = By.cssSelector(".thumbnails img");

     public ProductInfoPage(WebDriver driver){
         this.driver = driver;
         eleUtil = new ElementUtil(this.driver);
     }

     public Map<String, String> getProductInformation(){
         Map<String,String> productInfoMap = new HashMap<String, String>();
         productInfoMap.put("Product",eleUtil.doGetText(productNameHeader).trim());
         List<WebElement> productMetaDataList = eleUtil.getElements(productMetaData);
         for (WebElement e : productMetaDataList){
             String meta[] = e.getText().split(":");
             String metaKey = meta[0].trim();
             String metaValue = meta[1].trim();
             productInfoMap.put(metaKey,metaValue);
         }

         List<WebElement> productPriceList = eleUtil.getElements(priceMetaData);
         productInfoMap.put("Price",productPriceList.get(0).getText().trim());
         productInfoMap.put("ExTaxPrice",productPriceList.get(1).getText().split(":")[1].trim());
         return productInfoMap;
     }

     public void selectQuantity(String quantity){
         eleUtil.doSendKeys(quantity,quantityText);
     }

     public void addToCart(){
         eleUtil.doClick(addToCartButton);
     }

     public int getProductimages(){
         return eleUtil.getElements(productImages).size();
     }

     public String getProductInfoPageTitle(String productName){
         return eleUtil.waitForPageTitlePresence(productName,5);
     }
}
