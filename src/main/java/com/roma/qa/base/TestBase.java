/*
* @author Roma
* */

package com.roma.qa.base;

import com.roma.qa.utils.TestUtil;
import com.roma.qa.utils.WebEventListener;
import org.apache.commons.math3.stat.inference.TestUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.events.WebDriverEventListener;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Time;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class TestBase {

   public static WebDriver driver;
    public static Properties prop;
    public static EventFiringWebDriver e_driver;
    public static WebDriverEventListener eventListener;

    public TestBase(){
        try{
            prop = new Properties();
            FileInputStream ip = new FileInputStream("C:\\Users\\user\\Documents\\Projects\\POMHibridSeleniumProjectFramewordClone\\src\\main\\java\\com\\roma\\qa\\config\\config.properties");
            prop.load(ip);
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public static void initialization(){
       String browserName =  prop.getProperty("browser");

       if (browserName.equals("chrome")){
            System.setProperty("webdriver.chrome.driver","C:\\webdrivers\\chromedriver.exe");
             driver = new ChromeDriver();
       }else if (browserName.equals("firefox")){
           System.setProperty("webdriver.gecko.driver", "C:\\webdrivers\\geckodriver.exe");
           driver = new FirefoxDriver();
       }

       e_driver = new EventFiringWebDriver(driver);
       // Now create object of EventListenerHandler to register it with EventFiringWebDriver
        eventListener = new WebEventListener();
        e_driver.register(eventListener);
        driver = e_driver;

       driver.manage().window().maximize();
       driver.manage().deleteAllCookies();
       driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);

       driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT, TimeUnit.SECONDS);

       driver.get(prop.getProperty("url"));
        WebDriverWait wait = new WebDriverWait(driver,15);

//        Boolean isPresent = driver.findElements(By.tagName("button")).size()>0;
//        if (isPresent){
//            WebElement cookieButton = driver.findElement(By.tagName("button"));
//            cookieButton.click();
//        }

        //wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("button")));
        //WebElement cookieButton = driver.findElement(By.tagName("button"));
//        if (cookieButton.isDisplayed()){
//            cookieButton.click();
//        }

        boolean isPresent = driver.findElement(By.tagName("button")).isDisplayed();
        if(isPresent){
            driver.findElement(By.tagName("button")).click();
        }

    }

}
