package core;

import config.BrowserConfig;
import helpers.ExcelReader;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import pages.LoginPage;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class BaseTest<T> {
    public WebDriver driver;
    public LoginPage loginPage;
    public ExcelReader excelReader;
    public List<Map<String, String>> testData;
    public T page;

    @BeforeSuite
    @Parameters({"browser"})
    public void initDriver(@Optional String browser) throws IOException, InterruptedException {
        driver = BrowserConfig.setDriver(browser);
        BrowserConfig.setWaitImplicit(SystemDefault.WAIT_IMPLICIT);
    }

    @AfterClass()
    public void afterClass() {
//        BrowserConfig.quit();
    }

    public void initExcelData(String sheetName, String[] headers) throws IOException {
        excelReader = new ExcelReader(SystemDefault.TEST_DATA_FILE, sheetName);
        testData = excelReader.parseData(headers, true);
    }
}
