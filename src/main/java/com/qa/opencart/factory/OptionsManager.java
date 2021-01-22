package com.qa.opencart.factory;

import org.openqa.selenium.chrome.ChromeOptions;

import java.util.Properties;

public class OptionsManager {
    private Properties prop;
    private ChromeOptions co;

    OptionsManager(Properties prop){
        this.prop = prop;
    }

    public ChromeOptions getChromeOptions(){
        co = new ChromeOptions();
        if(Boolean.parseBoolean(prop.getProperty("headless"))){
            co.addArguments("--headless");
        }
        return co;
    }
}
