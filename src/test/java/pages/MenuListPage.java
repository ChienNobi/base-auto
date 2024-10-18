package pages;

import constant.Common;
import core.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MenuListPage extends BasePage<MealListPage> {
    @FindBy(xpath = "//button[@accesskey='c']")
    private WebElement addBtn;

    @FindBy(xpath = "//div[contains(@class, 'o_form_sheet')]")
    private WebElement formContainer;

    public MenuListPage() {
        super();
        this.pageUrl = Common.MENU_LIST_BY_DAY;
        PageFactory.initElements(driver, this);
    }

    public MenuListPage clickBtnAdd() {
        addBtn.click();
        waitElementDisplayed(formContainer);
        return this;
    }
}

