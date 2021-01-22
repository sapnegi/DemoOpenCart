package com.qa.opencart.factory;


import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;


public class DriverFactory {
    private static final Logger LOGGER = Logger.getLogger(String.valueOf(DriverFactory.class));

    String browserName;
    WebDriver driver;
    Properties prop;
    public static String highlight;
    public static ThreadLocal<WebDriver> tldriver = new ThreadLocal<>();

    public WebDriver init_driver(Properties  prop){
        String browserName = prop.getProperty("browser");
        LOGGER.info("browser name is : "+ browserName);
        highlight = prop.getProperty("highlight");
        OptionsManager optManager = new OptionsManager(prop);

        if(browserName.equals("chrome")){
            WebDriverManager.chromedriver().setup();
           // driver = new ChromeDriver(optManager.getChromeOptions());
            tldriver.set(new ChromeDriver());
        }
        getDriver().manage().window().fullscreen();
        getDriver().manage().deleteAllCookies();
        return getDriver();
    }
    public static synchronized WebDriver getDriver(){
        return tldriver.get();
    }

    public Properties init_prop(){
        prop = new Properties();
        try {
            FileInputStream fip = new FileInputStream("./src/test/resources/config/config.properties");
            prop.load(fip);
        }
        catch (FileNotFoundException ignored) {
        }
        catch (IOException ignored) {
        }
        return prop;
    }

    public String getScreenshot(){
        File src = ((TakesScreenshot)getDriver()).getScreenshotAs(OutputType.FILE);
//        File srcFile = new File(src);
        String path = System.getProperty("user.dir")+"/screenshots/"+System.currentTimeMillis()+".jpg";
        File destination = new File(path);

        try {
            FileUtils.copyFile(src,destination);
        } catch (IOException e) {
        }

        return path;
    }
}
