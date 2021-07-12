package com.roma.qa.tastcases;

import com.roma.qa.base.TestBase;
import com.roma.qa.pages.ContactsPage;
import com.roma.qa.pages.CreateNewContactPage;
import com.roma.qa.pages.HomePage;
import com.roma.qa.pages.LoginPage;
import com.roma.qa.utils.TestUtil;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class HomePageTest extends TestBase {
    LoginPage loginPage;
    HomePage homePage;
    ContactsPage contactsPage;
    TestUtil testUtil;
    CreateNewContactPage createNewContactPage;

    public HomePageTest(){
        super();
    }

    // Test cases should be separated -- independent from each other
    //Before each test case launch browser and login
    // After each test case close the browser

    @BeforeMethod
    public void setUp(){
        initialization();
        testUtil = new TestUtil();
        loginPage = new LoginPage();
        homePage =  loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
        contactsPage = new ContactsPage();

    }

    @Test(priority = 1)
    public void verifyHomePageTitleTest(){
       String title =  homePage.verifyHomePageTitle();
        Assert.assertEquals(title,"Cogmento CRM","Homepage title did not match: ");
    }

    @Test(priority = 2)
    public void verifyUseNameTest(){
        Assert.assertTrue(homePage.verifyUserName());
    }

    @Test(priority = 3)
    public void verifyContactsLink(){
        contactsPage = homePage.clickOnContactsLink();
        Assert.assertTrue(contactsPage.verifyContactsLabel());
    }

    @Test
    public void clickOnCreateNewContactButton(){
       createNewContactPage =  homePage.clickOnNewContactLink();
        Assert.assertTrue(createNewContactPage.verifyCreateNewContactLabel(),"Element can not be found");
    }

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }
}
