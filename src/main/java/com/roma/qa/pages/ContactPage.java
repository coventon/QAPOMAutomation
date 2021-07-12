package com.roma.qa.pages;

import com.roma.qa.base.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.nio.channels.WritableByteChannel;

public class ContactPage extends TestBase {

  //  WebElement contactLabel = driver.findElement(By.xpath("//*[@id='dashboard-toolbar']/div[1]"));
   // WebElement contactsLink =driver.findElement(By.xpath("//a//span[contains(text(),'Contacts')]"));
  // WebElement contactsLink =  driver.findElement(By.xpath("//*[@id='main-nav']/div[3]/a"));

   @FindBy(xpath = "//*[@id='dashboard-toolbar']/div[1]")
   WebElement contactLabel;

   @FindBy(xpath = "//*[@id='main-nav']/div[3]/a")
   WebElement contactsLink;

   @FindBy(xpath = "//*[@id='dashboard-toolbar']/div[1][contains(string(),'Tom Hudson')]")
    WebElement contactLabelTop;

   public ContactPage(){
       PageFactory.initElements(driver,this);
   }


    public boolean verifyContactLabel(String contact){
        System.out.println("Contact Label "+contactLabel.getText());
        return contactLabel.getText().equals(contact);
    }

    public String getLabel(){
        String label = contactLabel.getText();

        return label;
    }

    public boolean verifyContactLabelMain(){
        return contactLabelTop.isDisplayed();
    }

    public boolean verifyLabelOfContact(String contactLabel){
       return driver.findElement(By.xpath("//*[@id='dashboard-toolbar']/div[1][contains(string(),'"+contactLabel+"')]")).isDisplayed();
    }

    public String verifyName(String name){
       String contactName = driver.findElement(By.xpath("//*[@id='dashboard-toolbar']/div[1][contains(string(),'"+name+"')]")).getText();
       return contactName;
    }

    public String verifyName2(){
       String name2 = contactLabel.getText();
       return name2;
    }

    public ContactsPage clickOnContactsLink(){

        Actions actions = new Actions(driver);
        actions.moveToElement(contactsLink).build().perform();
        contactsLink.click();
        return new ContactsPage();
    }
}
