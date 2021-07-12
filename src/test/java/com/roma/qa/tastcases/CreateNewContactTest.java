package com.roma.qa.tastcases;

import com.roma.qa.base.TestBase;
import com.roma.qa.pages.*;
import com.roma.qa.utils.TestUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class CreateNewContactTest extends TestBase {
    LoginPage loginPage;
    HomePage homePage;
    TestUtil testUtil;
    ContactsPage contactsPage;
    CreateNewContactPage createNewContactPage;
    String sheetName = "contacts";
    ContactPage contactPage;
    public CreateNewContactTest(){
        super();
    }

    @BeforeMethod
    public void setUp(){
        initialization();
        testUtil = new TestUtil();
        contactsPage = new ContactsPage();
        loginPage = new LoginPage();

        homePage = loginPage.login(prop.getProperty("username"), prop.getProperty("password"));

    }

    @DataProvider
    public Object[][] getCRMTestData() throws IOException {
       Object[][] data =   TestUtil.getTestData(sheetName);
        return data;
    }

//    @Test(priority = 1, dataProvider = "getCRMTestData")
//    public void validateCreateNewContact(){
//       createNewContactPage =  homePage.clickOnNewContactLink();
//       createNewContactPage.createNewContact("Tom","Hudson", "V","NewLink","tag","tom@main.com","business");
//        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
//        String testElement = driver.findElement(By.xpath("//div[@class='ui header item mb5 light-black']")).getText();
//        System.out.println(testElement);
//        WebElement newUserPageName = driver.findElement(By.xpath("//div[@class='ui header item mb5 light-black'][contains(string(),'Tom Hudson')]"));
//        String testName = newUserPageName.getText();
//        System.out.println(testName);
//        Assert.assertTrue( newUserPageName.isDisplayed());
//        Assert.assertEquals(newUserPageName.getText(),"Tom Hudson","User not matched");
//
////       boolean contactLabel = contactPage.verifyContactLabel("Tom Hudson");
////        Assert.assertTrue(contactLabel);
//    }

    @Test(priority = 1, dataProvider = "getCRMTestData")
    public void validateCreateNewContact( String first_name, String last_name,String middle_name,String company, String email_address, String email_type, String tags ){
        createNewContactPage =  homePage.clickOnNewContactLink();
        createNewContactPage.createNewContact(first_name,last_name,middle_name,company,email_address,email_type,tags );

        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

        String testElement = driver.findElement(By.xpath("//div[@class='ui header item mb5 light-black']")).getText();
        System.out.println(testElement);

        WebElement newUserPageName = driver.findElement(By.xpath("//div[@class='ui header item mb5 light-black'][contains(string(),'"+first_name+" " +last_name+"')]"));
        String testName = newUserPageName.getText();
        System.out.println("Test Name: "+testName);

        Assert.assertTrue( newUserPageName.isDisplayed());
        Assert.assertEquals(newUserPageName.getText(),first_name+" "+last_name,"User not matched");

    }

    @Test(priority = 2)
    public void validateCreateNewContact2() throws InterruptedException {

        createNewContactPage = homePage.clickOnNewContactLink();
        driver.manage().timeouts().pageLoadTimeout(20,TimeUnit.SECONDS);
       // driver.navigate().refresh();
        Assert.assertTrue(createNewContactPage.verifyCreateNewContactLabel());

       contactPage =createNewContactPage.createNewContact("Tom","Hudson", "V","NewLink","tag","tom@main.com","business");

       driver.manage().timeouts().pageLoadTimeout(10,TimeUnit.SECONDS);
        WebElement nameLabel = driver.findElement(By.xpath("//*[@id='dashboard-toolbar']/div[1]"));
        String nameToCompare = nameLabel.getText();
        System.out.println(nameToCompare);
        System.out.println(nameToCompare.compareTo("Tom Hudson"));
        String label = contactPage.getLabel();
        System.out.println(label);
        //Assert.assertTrue(nameLabel.equals("Tom Hudson"));

       // driver.getCurrentUrl();
        driver.manage().timeouts().pageLoadTimeout(10,TimeUnit.SECONDS);
        WebDriverWait wait = new WebDriverWait(driver, 10);

        //contactPage.verifyContactLabelMain();
        Assert.assertTrue(contactPage.verifyContactLabelMain());
        Assert.assertTrue(contactPage.verifyLabelOfContact("Tom Hudson"));
        Assert.assertEquals(contactPage.verifyName("Tom Hudson"),"Tom Hudson");
        Assert.assertEquals(contactPage.verifyName2(),"Tom Hudson");

        //WebElement el = driver.findElement(By.xpath("//*[@id='dashboard-toolbar']/div[1]"));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='dashboard-toolbar']/div[1]")));
        WebElement el1 = driver.findElement(By.xpath("//*[@id='dashboard-toolbar']/div[1]"));

        //driver.navigate().refresh();
        //System.out.println(el.getAttribute("innerText"));
        System.out.println("Contact Label: "+el1.getAttribute("innerText"));

        //Assert.assertTrue(contactPage.verifyContactLabel("Tom Hudson"));

//        WebElement contactsLink =  driver.findElement(By.xpath("//*[@id='main-nav']/div[3]/a"));
//        Thread.sleep(3000);
//        Actions actions = new Actions(driver);
//        actions.moveToElement(contactsLink).build().perform();
//        contactsLink.click();
       // contactPage.clickOnContactsLink();
        //*[@id="main-nav"]/div[3]/a
//        String name = driver.findElement(By.xpath("//*[@id=\"main-content\"]/div/div[2]/div/table/tbody/tr[1]/td[2]/a")).getText();
//        System.out.println(name);
    }

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }
}
