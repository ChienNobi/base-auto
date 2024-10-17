package page;

import constant.Common;
import core.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MealListPage extends BasePage {
    @FindBy(xpath = "//button[@accesskey='c']")
    private WebElement addBtn;

    @FindBy(xpath = "//div[contains(@class, 'o_form_sheet')]")
    private WebElement formContainer;

    public MealListPage() {
        super();
        this.pageUrl = Common.LOGIN_URL;
        PageFactory.initElements(driver, this);
    }

    public MealListPage clickBtnAdd() {
        addBtn.click();
        waitElementDisplayed(formContainer);
        return this;
    }
}

