package core;

import config.BrowserConfig;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.Random;

public class BasePage {
    public WebDriver driver;
    public String pageUrl;

    public BasePage() {
        this.driver = BrowserConfig.getDriver();
    }

    public void openPage() {
        driver.get(pageUrl);
    }
}
