package helpers;

import config.BrowserConfig;
import constant.Common;
import core.SystemDefault;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

import static java.lang.Thread.sleep;

public class WebElementHelper {

    public static void selectAnOption(WebElement element, String option) {
        if(!element.isDisplayed() || !element.isEnabled() || option.isEmpty()) {
            System.out.println("Cannot find option " + option);
            return;
        }
        Select select = new Select(element);
        select.selectByVisibleText(option);
    }

    public static void simpleDateTimeSelect(WebElement inputElement, String time) {
        inputElement.sendKeys(time);
    }

    public static void sendKeys(WebElement element, String value) {
        element.clear();
        element.click();
        element.sendKeys(value);
    };


    public static String getModalDialogText() {
        try {
            WebDriver driver = BrowserConfig.getDriver();
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(SystemDefault.WAIT_IMPLICIT));
            WebElement dialog = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(@class, 'modal-body')]")));

            String msg = dialog.getText();
            System.out.println("[Result] Popup dialog with msg: " + msg);
            return msg;
        } catch (NoSuchElementException e) {
            System.out.println("[Result] No popup dialog");
            return "";
        }
    }

    public static List<String> getListValueOfSelect(WebElement selectionElement) {
        Select select = new Select(selectionElement);
        List<WebElement> options = select.getOptions();
        return select.getOptions().stream()
                .map(WebElement::getText).filter(text -> !text.trim().isEmpty())
                .collect(Collectors.toList());
    }

    public static String getSelectedValue(WebElement selectElement) {
        Select select = new Select(selectElement);
        return select.getFirstSelectedOption().getText();
    }

    public static boolean hasElement(WebElement element) {
        try {
            WebDriverWait wait = new WebDriverWait(BrowserConfig.getDriver(), Duration.ofMillis(SystemDefault.WAIT_IMPLICIT));
            wait.until(ExpectedConditions.visibilityOf(element));
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }
}
