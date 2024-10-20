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

public class PriceRegisterPage extends BasePage<PriceRegisterPage> {
    @FindBy(xpath = "//span[@name='name']")
    private WebElement codeField;

    @FindBy(xpath = "//select[@name='type']")
    private WebElement priceTypeSelect;

    @FindBy(xpath = "//div[@name='name_id']//input")
    private WebElement peopleRegisterField;
    @FindBy(xpath = "//div[@name='room_id']//input")
    private WebElement roomField;

    @FindBy(xpath = "//input[@name='start_day']")
    private WebElement startDayField;

    @FindBy(xpath = "//input[@name='end_day']")
    public WebElement endDayField;

    @FindBy(xpath = "//a[@role='button' and text()='Thêm một dòng']")
    public WebElement addRowBtn;

    @FindBy(xpath = "//tbody[@class='ui-sortable']/tr[1]//td[1]//input")
    public WebElement menuField;

    @FindBy(xpath = "//input[@name='deposit']")
    public WebElement depositField;

    public PriceRegisterPage() {
        super();
        this.pageUrl = Common.PRICE_REGISTER;
        PageFactory.initElements(driver, this);
    }

    public PriceRegisterPage selectPriceType(String mealType) {
        System.out.println("[Step] select price type");
        WebElementHelper.selectAnOption(priceTypeSelect, mealType);
        return this;
    }

    public PriceRegisterPage selectStartDate(String date) {
        WebElementHelper.simpleDateTimeSelect(startDayField, date);
        return this;
    }

    public PriceRegisterPage selectEndDate(String date) {
        WebElementHelper.simpleDateTimeSelect(endDayField, date);
        return this;
    }

    public List<String> getLisPriceType() {
        System.out.println("[Step] get list price type value");
        return WebElementHelper.getListValueOfSelect(priceTypeSelect);
    }

    public PriceRegisterPage addNewRow() {
        System.out.println("[Step] add new row");
        addRowBtn.click();
        return this;
    }

    public PriceRegisterPage fillOrderPeople(@Optional String name, @Optional boolean throwException) {
        System.out.println("[Step] fill employee name: " + name);
        if(name == null || name.isEmpty()) {
            if (!RemoteSearchHelper.selectFirstOption(peopleRegisterField)) {
                throw new NotFoundException("Cannot select employee name");
            }
            return this;
        }

        boolean isNameValid = RemoteSearchHelper.isTextExist(peopleRegisterField, name);
        if(!isNameValid && throwException) {
            throw new NotFoundException("Cannot find employee name");
        }
        return this;
    }

    public PriceRegisterPage fillRoom(@Optional String name, @Optional boolean throwException) {
        System.out.println("[Step] fill room name: " + name);
        if(name == null || name.isEmpty()) {
            if (!RemoteSearchHelper.selectFirstOption(roomField)) {
                throw new NotFoundException("Cannot select room name");
            }
            return this;
        }

        boolean isNameValid = RemoteSearchHelper.isTextExist(roomField, name);
        if(!isNameValid && throwException) {
            throw new NotFoundException("Cannot find room name");
        }
        return this;
    }

    public PriceRegisterPage fillMenu(@Optional String name, @Optional boolean throwException) {
        System.out.println("[Step] fill menu: " + name);
        if(name == null || name.isEmpty()) {
            if (!RemoteSearchHelper.selectFirstOption(menuField)) {
                throw new NotFoundException("Cannot select menu");
            }
            return this;
        }

        boolean isNameValid = RemoteSearchHelper.isTextExist(menuField, name);
        if(!isNameValid && throwException) {
            throw new NotFoundException("Cannot find menu");
        }
        return this;
    }

    public PriceRegisterPage fillDeposit(String deposit) {
        System.out.println("[Step] fill deposit: " + deposit);
        WebElementHelper.sendKeys(depositField, deposit);
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

    public PriceRegisterPage clickMoreItem() {
         RemoteSearchHelper.selectMoreItem(menuField);
         return this;
    }
}

