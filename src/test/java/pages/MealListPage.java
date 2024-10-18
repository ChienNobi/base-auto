package pages;

import constant.Common;
import core.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.sql.SQLOutput;

public class MealListPage extends BasePage<MealListPage> {
    @FindBy(xpath = "//button[@accesskey='c']")
    private WebElement addBtn;

    public MealListPage() {
        super();
        this.pageUrl = Common.MEAL_LIST;
        PageFactory.initElements(driver, this);
    }

    public MealListPage clickBtnAdd() {
        System.out.println("[Step] MealListPage click button Add");
        addBtn.click();
        waitElementDisplayed(formContainer);
        return this;
    }
}

