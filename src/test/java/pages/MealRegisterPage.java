package pages;

import constant.Common;
import core.BasePage;
import helpers.WebElementHelper;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MealRegisterPage extends BasePage<MealRegisterPage> {
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

    public MealRegisterPage() {
        super();
        this.pageUrl = Common.MEAL_REGISTER;
        PageFactory.initElements(driver, this);
    }

    public MealRegisterPage selectMealType(String mealType) {
        WebElementHelper.selectAnOption(mealTypeSelect, mealType);
        return this;
    }

    public MealRegisterPage selectDate(String date) {
        WebElementHelper.simpleDateTimeSelect(dateField, date);
        return this;
    }
}

