package pages;

import constant.Common;
import core.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MenuRegisterPage extends BasePage {
    @FindBy(xpath = "//button[@accesskey='s']")
    private WebElement saveBtn;

    @FindBy(xpath = "//button[@accesskey='j']")
    private WebElement cancelBtn;

    @FindBy(xpath = "//button[@name='action_register']")
    private WebElement registerBtn;

    @FindBy(xpath = "//select[@name='meal_type']")
    private WebElement mealTypeSelect;

    @FindBy(xpath = "//input[@name='date']")
    private WebElement dateField;

    public MenuRegisterPage() {
        super();
        this.pageUrl = Common.MENU_ADD_BY_DAY;
        PageFactory.initElements(driver, this);
    }
}

