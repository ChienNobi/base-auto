package core;

import config.BrowserConfig;
import helpers.RandomHelper;
import helpers.WebElementHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Random;

public class BasePage<T> {
    public WebDriver driver;
    public String pageUrl;
    public Actions action;
    public WebElementHelper webElementHelper;

    @FindBy(xpath = "//div[contains(@class, 'o_form_sheet')]")
    public WebElement formContainer;


    public BasePage() {
        this.driver = BrowserConfig.getDriver();
        action = new Actions(driver);
    }

    public T openPage() {
        driver.get(pageUrl);
        return (T) this;
    }

    public void waitElementDisplayed(WebElement element) {
        WebDriverWait wait = new WebDriverWait(BrowserConfig.getDriver(), Duration.ofMillis(SystemDefault.WAIT_PAGE_LOAD));
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void WaitElementVisible(WebElement element, int time) {
        WebDriverWait wait = new WebDriverWait(BrowserConfig.getDriver(), Duration.ofMillis(time));
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public T selectDateTimeInPass(WebElement element) {
        String date = RandomHelper.randomPastDate(null);
        WebElementHelper.simpleDateTimeSelect(element, date);
        return (T) this;
    }
}
