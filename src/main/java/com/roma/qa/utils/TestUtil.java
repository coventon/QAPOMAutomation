package com.roma.qa.utils;

import com.roma.qa.base.TestBase;
import org.apache.commons.compress.archivers.dump.InvalidFormatException;
import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.usermodel.Workbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class TestUtil extends TestBase {

   public static long PAGE_LOAD_TIMEOUT = 20;
    public static long IMPLICIT_WAIT = 10;

    public static String TEST_DATA_PATH = "C:\\Users\\user\\Documents\\Projects\\POMHibridSeleniumProjectFramewordClone\\src\\main\\java\\com\\roma\\qa\\testdata\\test_data_selen.xlsx";

    static Workbook book;
    static Sheet sheet;

    public void switchToframe(){
        driver.switchTo().frame("mainpanel");
    }

    public static Object[][] getTestData(String sheetName) throws IOException {
        FileInputStream file = null;
        try {
            file = new FileInputStream(TEST_DATA_PATH);
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }
        try {
            book = WorkbookFactory.create(file);
        }catch (InvalidFormatException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }

        sheet = book.getSheet(sheetName);
        Object[][] data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
       // System.out.println(sheet.getLastRowNum());
        for (int i = 0; i < sheet.getLastRowNum() ; i++) {
            for (int j = 0; j <sheet.getRow(0).getLastCellNum() ; j++) {
                data[i][j] = sheet.getRow(i+1).getCell(j).toString();
               // System.out.println(data[i][j]);
            }
        }
        return data;
    }

    public static void takeScreenShotAtEndOfTest() throws IOException {
        File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        String currentDir = System.getProperty("user.dir");
        FileUtils.copyFile(scrFile, new File(currentDir+"\\screenshots\\"+System.currentTimeMillis()+".png"));
        System.out.println("Screenshot taken and saved in directory: " + currentDir+"/screenshots");
    }
}
