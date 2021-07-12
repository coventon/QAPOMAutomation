package com.roma.qa.tastcases;

import com.roma.qa.base.TestBase;
import com.roma.qa.pages.ContactsPage;
import com.roma.qa.pages.HomePage;
import com.roma.qa.pages.LoginPage;
import com.roma.qa.utils.TestUtil;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ContactsPageTest  extends TestBase {

    LoginPage loginPage;
    HomePage homePage;
    TestUtil testUtil;
    ContactsPage contactsPage;

    public ContactsPageTest(){
        super();
    }

    @BeforeMethod
    public void setUp(){
        initialization();
        testUtil = new TestUtil();
        contactsPage = new ContactsPage();
        loginPage = new LoginPage();
        homePage = loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
        homePage.clickOnContactsLink();
    }

    @Test(priority = 1)
    public void verifyContactsPageLabel(){

        Assert.assertTrue(contactsPage.verifyContactsLabel(),"Contacts label is missing on the page");
    }

    @Test(priority = 2)
    public void selectContactsTest(){
        contactsPage.selectContacts("Alex Herman Bovich");
    }

    @Test(priority = 3)
    public void selectMultipleContacts(){
        contactsPage.selectContacts("Alex Herman Bovich");
        contactsPage.selectContacts("Tomas Henbo Vah");
    }

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }
}
