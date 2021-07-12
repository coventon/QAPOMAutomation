package com.roma.qa.pages;

import com.roma.qa.base.TestBase;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreateNewContactPage extends TestBase {

    @FindBy(xpath = "//div[@id='dashboard-toolbar']//div[contains(string(),'Create New Contact')]")
    WebElement createNewContactPageLabel;

    @FindBy(name = "first_name")
    @CacheLookup
    WebElement firstName;

    @FindBy(name = "last_name")
    @CacheLookup
    WebElement lastName;

    @FindBy(name = "middle_name")
    @CacheLookup
    WebElement middleName;

    @FindBy(xpath = "//div[@name = 'company']//input[@type='text']")
    @CacheLookup
    WebElement company;

    @FindBy(xpath = "//label[@for='tags']//input")
    @CacheLookup
    WebElement tags;

    @FindBy(xpath = "//button[contains(text(),'Public')]")
    @CacheLookup
    WebElement publicBtn;


    @FindBy(xpath = "//input[@name='value' and @placeholder='Email address']")
    @CacheLookup
    WebElement emailAddress;

    //input[@name='name' and @placeholder='Personal email, Business, Alt...']
    @FindBy(xpath = "//input[@name='name' and @placeholder='Personal email, Business, Alt...']")
    @CacheLookup
    WebElement emailType;

    @FindBy(xpath = "//input[@name='address' ]")
    @CacheLookup
    WebElement addressStreet;

    @FindBy(name = "city")
    @CacheLookup
    WebElement city;

    @FindBy(name = "state")
    @CacheLookup
    WebElement state;

    @FindBy(name = "zip")
    @CacheLookup
    WebElement zip;

    @FindBy(name = "country")
    @CacheLookup
    WebElement country;

    @FindBy(xpath = "//button[text()='Save']")
    @CacheLookup
    WebElement saveBtn;

    public CreateNewContactPage(){
        PageFactory.initElements(driver,this);
    }

    public ContactPage createNewContact(String firstname, String lastName,String middleName, String company,  String emailAddress, String emailTYpe,String tags){
        this.firstName.sendKeys(firstname);
        this.lastName.sendKeys(lastName);
        this.middleName.sendKeys((middleName));
        this.company.sendKeys(company);
        this.tags.sendKeys(tags);
        this.emailAddress.sendKeys(emailAddress);
        this.emailType.sendKeys(emailTYpe);
        saveBtn.click();
        return new ContactPage();
    }

    public boolean verifyCreateNewContactLabel(){

        return createNewContactPageLabel.getText().equals("Create New Contact");
    }

}
