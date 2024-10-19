package core;

import config.BrowserConfig;
import helpers.RandomHelper;
import helpers.WebElementHelper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage<T> {
    public WebDriver driver;
    public String pageUrl;
    public Actions action;
    public WebElementHelper webElementHelper;

    @FindBy(xpath = "//input[@name='date']")
    public WebElement dateField;
    @FindBy(xpath = "//div[contains(@class, 'o_notification_body')]//ul/li")
    public WebElement errorPopup;

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

    public T waitElementDisplayed(WebElement element) {
        WebDriverWait wait = new WebDriverWait(BrowserConfig.getDriver(), Duration.ofMillis(SystemDefault.WAIT_PAGE_LOAD));
        wait.until(ExpectedConditions.visibilityOf(element));

        return (T) this;
    }

    public T WaitElementVisible(WebElement element, int time) {
        WebDriverWait wait = new WebDriverWait(BrowserConfig.getDriver(), Duration.ofMillis(time));
        wait.until(ExpectedConditions.visibilityOf(element));
        return (T) this;
    }

    public T selectDateTimeInPass() {
        String date = RandomHelper.randomPastDate(null);
        System.out.println("[Step] fill past date: " + date);
        WebElementHelper.simpleDateTimeSelect(dateField, date);
        return (T) this;
    }

    public T selectNextDay() {
        String date = RandomHelper.getNextDate();
        System.out.println("[Step] fill next day: " + date);
        WebElementHelper.simpleDateTimeSelect(dateField, date);
        return (T) this;
    }

    public T selectCurrentDay() {
        String date = RandomHelper.getCurrentDay();
        System.out.println("[Step] fill current date: " + date);
        WebElementHelper.simpleDateTimeSelect(dateField, date);
        return (T) this;
    }

    public String getPopupMessage() {
        return WebElementHelper.getModalDialogText();
    }

    public String getErrorMessage() {
        String error = WebElementHelper.hasElement(errorPopup) ? errorPopup.getText() : "";
        System.out.println("[Result] Error message: " + error);
        return error;
    }
}
