package helpers;

import config.BrowserConfig;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Optional;

import java.util.List;

import static java.lang.Thread.sleep;

public class RemoteSearchHelper {
    public static boolean isTextExist(WebElement inputElement, String text) {
        List<WebElement> children = getChildren(inputElement, text);
        if(children == null || children.size() <= 1) {
            return false;
        }
        for (WebElement element : children) {
            System.out.println("[LOG] Menu list item " + element.getText());
            if (element.getText().equals(text)) {
                element.click();
                return true;
            }
            if (element.getText().equals("Tạo \"" + text + "\"")) {
                return false;
            }
        }
        return false;
    }

    public static boolean selectMoreItem(WebElement inputElement) {
        List<WebElement> children = getChildren(inputElement, "");
        if(children == null || children.size() <= 1) {
            return false;
        }
        children.get(children.size() - 2).click();
        return true;
    }

    public static List<WebElement> getChildren(WebElement inputElement, @Optional String text) {
        if (text != null && !text.isEmpty()) {
            inputElement.clear();
            inputElement.sendKeys(text);

        }
        inputElement.click();

        WebDriver driver = BrowserConfig.getDriver();
        try {
            WebElement parent = driver.findElement(By.xpath("//ul[contains(@class, 'ui-menu ui-widget ') and not(contains(@style, 'display: none'))]"));
            sleep(2000);

            return parent.findElements(By.tagName("li"));
        } catch (NoSuchElementException | InterruptedException e) {
            return null;
        }
    }

    public static boolean selectFirstOption(WebElement inputElement) {
        List<WebElement> children = getChildren(inputElement, "");
        if(children == null || children.size() <= 1) {
            return false;
        }

        children.get(0).click();
        return true;
    }
}
