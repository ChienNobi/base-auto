package core;

import config.BrowserConfig;
import helpers.ExcelReader;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import page.LoginPage;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class BaseTest {
    public WebDriver driver;
    public LoginPage loginPage;
    public ExcelReader excelReader;
    public List<Map<String, String>> testData;

    private void initChrome() {
        driver = BrowserConfig.setDriver("en");
        BrowserConfig.setWaitImplicit(SystemDefault.WAIT_IMPLICIT);
    }

    @BeforeSuite
    public void beforeSuit() throws IOException, InterruptedException {
        initChrome();
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
