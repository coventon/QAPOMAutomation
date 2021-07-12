package com.roma.qa.pages;

import com.roma.qa.base.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactsPage extends TestBase {

 @FindBy(xpath = "//div[@id='dashboard-toolbar']//div[contains(string(),'Contacts')]")
    WebElement contactsLabel;



    // Initializing page object
    public ContactsPage(){
        PageFactory.initElements(driver,this);
    }

    public boolean verifyContactsLabel(){
        return contactsLabel.isDisplayed();
    }

    public void selectContacts(String name){
       WebElement element =  driver.findElement(By.xpath("//a[text() = '"+name+"']//parent::td[@class]//preceding-sibling::td//input[@name='id']"));
       Actions actions =new Actions(driver);
       actions.moveByOffset(150,40).perform();
       actions.moveToElement(element).click().perform();

    }


}
