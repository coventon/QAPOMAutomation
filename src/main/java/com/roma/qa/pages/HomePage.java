package com.roma.qa.pages;

import com.roma.qa.base.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends TestBase {

    @FindBy(xpath = "//div[@class='right menu']//span[@class='user-display']")
    @CacheLookup
    WebElement userNameLabel;

    @FindBy(xpath = "//a//span[contains(text(),'Contacts')]")
    @CacheLookup
    WebElement contactsLink;

    @FindBy(xpath = "//a//span[contains(text(),'Contacts')]//parent:: a//following-sibling::button")
    @CacheLookup
    WebElement createNewContactLink;

    @FindBy(xpath = "//a//span[contains(text(),'Deals')]")
    @CacheLookup
    WebElement dealsLink;

    @FindBy(xpath = "//a//span[contains(text(),'Tasks')]")
    @CacheLookup
    WebElement tasksLink;

    public HomePage(){
        PageFactory.initElements(driver,this);
    }

    public String verifyHomePageTitle(){
        return driver.getTitle();
    }

    public ContactsPage clickOnContactsLink(){
        contactsLink.click();
        return new ContactsPage();
    }

    public DealsPage clickOnDealsLink(){
        dealsLink.click();
        return new DealsPage();
    }

    public TaskPage clickOnTasksLink(){
        tasksLink.click();
        return new TaskPage();
    }

    public boolean verifyUserName(){
        return userNameLabel.isDisplayed();
    }

  public CreateNewContactPage clickOnNewContactLink(){
        Actions actions = new Actions(driver);
        actions.moveToElement(contactsLink).build().perform();
        //clickOnSideBarItem("Contacts");
     createNewContactLink.click();
      return new CreateNewContactPage();
  }

    private void clickOnSideBarItem(String sideNavItem) {
        WebElement element = driver.findElement(By.xpath(
                "//a//span[contains(text(),'"+sideNavItem+"')]//parent:: a//following-sibling::button"
                //a//span[contains(text(),'"+sideNavItem+"')]//parent:: a//following-sibling::button
        ));
        Actions actions = new Actions(driver);
        actions.moveToElement(element).click().perform();
    }

}
