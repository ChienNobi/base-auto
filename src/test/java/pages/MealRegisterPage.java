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

import static java.lang.Thread.sleep;

public class MealRegisterPage extends BasePage<MealRegisterPage> {
    @FindBy(xpath = "//button[@data-hotkey='j']")
    private WebElement cancelBtn;

    @FindBy(xpath = "//button[@name='action_register']")
    private WebElement registerBtn;

    @FindBy(xpath = "//select[@name='meal_type']")
    private WebElement mealTypeSelect;

    @FindBy(xpath = "//a[@name='register']")
    private WebElement registerUsername;

    @FindBy(xpath = "//span[@name='name']")
    private WebElement codeField;

    @FindBy(xpath = "//span[@class='oe_topbar_name text-truncate']")
    private WebElement currentUsername;

    @FindBy(xpath = "//input[@name='date']")
    public WebElement dateField;

    @FindBy(xpath = "//select[@name='number']")
    public WebElement numberOfPeopleField;

    @FindBy(xpath = "//div[contains(@class, 'tab-pane active')]//tbody[@class='ui-sortable']/tr[1]//td[1]//input")
    public WebElement employeeNameField;
    @FindBy(xpath = "//div[@name='menu_id']//input")
    public WebElement menuFieldTypeTable;

    @FindBy(xpath = "//div[contains(@class, 'tab-pane active')]//tbody[@class='ui-sortable']/tr[1]//td[4]//input")
    public WebElement menuField;

    @FindBy(xpath = "//div[contains(@class, 'tab-pane active')]//tbody[@class='ui-sortable']/tr[1]//td[5]//input")
    public WebElement menuFieldCustomer;

    @FindBy(xpath = "//a[@role='button' and text()='Thêm một dòng']")
    public List<WebElement> addRowBtn;


    @FindBy(xpath = "//ul[@class='nav nav-tabs']/li[2]//a[@role='tab']")
    private WebElement tabCustomer;

    public MealRegisterPage() {
        super();
        this.pageUrl = Common.MEAL_REGISTER;
        PageFactory.initElements(driver, this);
    }

    public MealRegisterPage selectMealType(String mealType) {
        System.out.println("[Step] select meal type");
        WebElementHelper.selectAnOption(mealTypeSelect, mealType);
        return this;
    }

    public MealRegisterPage selectDate(String date) {
        WebElementHelper.simpleDateTimeSelect(dateField, date);
        return this;
    }

    public String getRegisterUser() {
        String username = registerUsername.getText();
        System.out.println("[Step] get register username: " + username);

        return username;
    }

    public String getCurLoggedInUser() {
        String curUserLoggedIn = currentUsername.getText();
        System.out.println("[Step] get current logged in username: " + curUserLoggedIn);

        return curUserLoggedIn;
    }

    public List<String> getListMealType() {
        System.out.println("[Step] get list value of meal type");
        return WebElementHelper.getListValueOfSelect(mealTypeSelect);
    }

    public String getSelectedMealType() {
        String selectedVal = WebElementHelper.getSelectedValue(mealTypeSelect);
        System.out.println("[Step] selected meal type " + selectedVal);
        return selectedVal;
    }

    public boolean checkListFieldWhenSelectMealType() {
        System.out.println("[Step] check list field when select meal type");
        return WebElementHelper.hasElement(menuFieldTypeTable);
    }

    public MealRegisterPage addNewRowForEmployee() {
        System.out.println("[Step] add new row for employee");
        addRowBtn.get(0).click();
        return this;
    }

    public MealRegisterPage addNewRowForCustomer() {
        System.out.println("[Step] add new row for customer");
        addRowBtn.get(1).click();
        return this;
    }

    public MealRegisterPage fillEmployeeName(String name, @Optional boolean throwException) {
        System.out.println("[Step] fill employee name: " + name);
        boolean isNameValid = RemoteSearchHelper.isTextExist(employeeNameField, name);
        if(!isNameValid && throwException) {
            throw new NotFoundException("Cannot find employee name");
        }
        return this;
    }

    public MealRegisterPage fillMenu(@Optional String menu) {
        if(menu == null || menu.isEmpty()) {
            if (!RemoteSearchHelper.selectFirstOption(menuField)) {
                throw new NotFoundException("Cannot select menu");
            }
            return this;
        }
        System.out.println("[Step] fill menu: " + menu);
        boolean isValidMenu = RemoteSearchHelper.isTextExist(menuField, menu);
        if(!isValidMenu) {
            throw new NotFoundException("Cannot find menu");
        }
        return this;
    }

    public MealRegisterPage fillMenuCustomer(@Optional String menu) {
        if(menu == null || menu.isEmpty()) {
            if (!RemoteSearchHelper.selectFirstOption(menuFieldCustomer)) {
                throw new NotFoundException("Cannot select menu");
            }
            return this;
        }
        System.out.println("[Step] fill menu: " + menu);
        boolean isValidMenu = RemoteSearchHelper.isTextExist(menuFieldCustomer, menu);
        if(!isValidMenu) {
            throw new NotFoundException("Cannot find menu");
        }
        return this;
    }

    public String getCodeValue() throws InterruptedException {
        sleep(5000);
        System.out.println("[Step] get code value: " + codeField.getText());
        return codeField.getText();
    }


    public boolean checkDisplayedPopupDialog() {
        String msg = WebElementHelper.getModalDialogText();
        return !msg.isEmpty();
    }

    public MealRegisterPage clickMoreItem() {
         RemoteSearchHelper.selectMoreItem(employeeNameField);
         return this;
    }

    public MealRegisterPage switchTabCustomer() {
        tabCustomer.click();
        return this;
    }
}

