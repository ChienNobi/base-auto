package pages;

import constant.Common;
import core.BasePage;
import helpers.WebElementHelper;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DishCreationPage extends BasePage<DishCreationPage> {
    @FindBy(xpath = "//span[@name='name']")
    private WebElement nameField;

    @FindBy(xpath = "//select[@name='type_service']")
    private WebElement typeField;

    @FindBy(xpath = "//select[@name='type_food']")
    private WebElement typeFoodField;

    @FindBy(xpath = "//input[@name='price_total']")
    private WebElement priceField;

    @FindBy(xpath = "//input[@name='wage']")
    private WebElement otherPriceField;

    public DishCreationPage() {
        super();
        this.pageUrl = Common.DISH_CREATION;
        PageFactory.initElements(driver, this);
    }

    public DishCreationPage fillName(String name) {
        System.out.println("[Step] fill dish name: " + name);
        typeField.sendKeys(name);
        return this;
    }

    public DishCreationPage fillType(String type) {
        System.out.println("[Step] select dish type: " + type);
        WebElementHelper.selectAnOption(typeField, type);
        return this;
    }

    public DishCreationPage fillFoodType(String type) {
        System.out.println("[Step] select food type: " + type);
        WebElementHelper.selectAnOption(typeFoodField, type);
        return this;
    }

    public DishCreationPage fillPrice(String price) {
        System.out.println("[Step] enter price: " + price);

        return this;
    }
}
