package helpers;

import config.BrowserConfig;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

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

    public static boolean enterInputRemoteSearch(WebElement inputElement, String text) {
        inputElement.click();
        inputElement.sendKeys(text);

        WebDriver driver = BrowserConfig.getDriver();
        try {
            WebElement parent = driver.findElement(By.xpath("//ul[contains(@class, 'ui-menu ui-widget ') and not(contains(@style, 'display: none'))]"));
            List<WebElement> children = parent.findElements(By.tagName("li"));

            children.forEach(element -> {
                System.out.println(element.getText());
            });
        } catch (NoSuchElementException e) {
            return false;
        }

        try {
            WebElement dialog = driver.findElement(By.xpath("//div[@class='modal-body']"));
            if(dialog.isDisplayed()) {
                String msg = dialog.getText();
                System.out.println("Popup dialog with msg: " + msg);
                return false;
            }
        } catch (NoSuchElementException e) {
            System.out.println("Found " + text);
        }

        return true;
    }

    public static void sendKeys(WebElement element, String value) {
        element.clear();
        element.sendKeys(value);
    };
}
