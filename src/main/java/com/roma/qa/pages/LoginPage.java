package com.roma.qa.pages;

import com.roma.qa.base.TestBase;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends TestBase {
    // Page Factory - Object Repository OR:
    // This is the same as: driver.findElementByName("name");

    @FindBy(name = "email")
    WebElement username;

    // The same as driver.findELement(By.name("name"))

    @FindBy(name = "password")
    WebElement password;

    @FindBy(xpath = "//form//div//div[3]")
    WebElement loginBtn;

    @FindBy(xpath = "//a[contains(text(),'Sign Up')]")
    WebElement sighUpBtn;

    @FindBy(xpath = "//div[@class = 'rd-navbar-panel']//div[@class='rd-navbar-brand']//a[1]")
    WebElement crmLogo;

    @FindBy(xpath = "//div[@class = 'ui message']//a[contains(text(),'Forgot')]")
    WebElement forgotPasswordLink;

    @FindBy(xpath = "//a//span[text()='Log In']")
    WebElement logInMain;

    // Initialization
    public LoginPage(){
        PageFactory.initElements(driver,this);
    }

    // Actions
    public String validateLoginPageTitle(){
        logInMain.click();
        return driver.getTitle();
    }

    public boolean validateForgotPasswordLink(){
        logInMain.click();
        return forgotPasswordLink.isDisplayed();
    }

    public boolean validateCRMImage(){
        return crmLogo.isDisplayed();
    }

    public HomePage login(String uName, String pwd){
        logInMain.click();
        username.sendKeys(uName);
        password.sendKeys(pwd);
        loginBtn.click();

        return new HomePage();
    }

    public LoginPage clickLoginBtnMain(){
        logInMain.isDisplayed();
        logInMain.click();
        return new LoginPage();
    }

}
