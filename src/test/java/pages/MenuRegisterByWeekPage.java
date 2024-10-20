package pages;

import constant.Common;
import core.BasePage;
import helpers.RemoteSearchHelper;
import helpers.WebElementHelper;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Optional;

import java.util.List;

public class MenuRegisterByWeekPage extends BasePage<MenuRegisterByWeekPage> {
    @FindBy(xpath = "//div[@name='week']//input")
    private WebElement weekField;

    @FindBy(xpath = "//select[@name='type_menu']")
    private WebElement menuTypeSelect;

    @FindBy(xpath = "//select[@name='number_of_people']")
    private WebElement numberOfPeopleSelect;

    @FindBy(xpath = "//span[@name='name']")
    private WebElement codeField;

    @FindBy(xpath = "//div[@name='menu_ids']//input")
    private WebElement menuItemField;

    public MenuRegisterByWeekPage() {
        super();
        this.pageUrl = Common.MENU_ADD_BY_WEEK;
        PageFactory.initElements(driver, this);
    }

    public MenuRegisterByWeekPage selectFirstWeek() {
        System.out.println("[Step] select first week");
        if (!RemoteSearchHelper.selectFirstOption(weekField)) {
            throw new NotFoundException("Cannot select week");
        }
        return this;
    }

    public MenuRegisterByWeekPage selectMealType(String menuType) {
        System.out.println("[Step] select menu type");
        WebElementHelper.selectAnOption(menuTypeSelect, menuType);
        return this;
    }

    public MenuRegisterByWeekPage fillMenuItem(@Optional String menu, @Optional boolean throwException) {
        if(menu == null || menu.isEmpty()) {
            if (!RemoteSearchHelper.selectFirstOption(menuItemField)) {
                throw new NotFoundException("Cannot select menu");
            }
            return this;
        }
        System.out.println("[Step] fill menu: " + menu);
        boolean isValidMenu = RemoteSearchHelper.isTextExist(menuItemField, menu);
        if(!isValidMenu && throwException) {
            throw new NotFoundException("Cannot find menu");
        }
        return this;
    }

    public String getCode() {
        WebElementHelper.waitForElementHasText(codeField);
        return codeField.getText();
    }

    public List<String> getListMealType() {
        System.out.println("[Step] get list value of meal type");
        return WebElementHelper.getListValueOfSelect(menuTypeSelect);
    }

    public List<String> getListNumberOfPeople() {
        WebElementHelper.hasElement(numberOfPeopleSelect);
        System.out.println("[Step] get list value of number of people");
        return WebElementHelper.getListValueOfSelect(numberOfPeopleSelect);
    }
}

