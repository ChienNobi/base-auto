package pages;

import constant.Common;
import core.BasePage;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends BasePage<LoginPage> {
    @FindBy(id = "login")
    private WebElement usernameField;

    @FindBy(id = "password")
    private WebElement passwordField;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement loginBtn;

    @FindBy(xpath = "//p[@class='alert alert-danger']")
    private WebElement errorMessage;

    public LoginPage() {
        super();
        this.pageUrl = Common.LOGIN_URL;
        PageFactory.initElements(driver, this);
    }

    public boolean login(String username, String password) {
        System.out.println("Login with info: " + username + "/" + password);
        usernameField.sendKeys(username);
        passwordField.sendKeys(password);
        action.moveToElement(loginBtn).click(loginBtn).build().perform();

        return isLoginSuccess();
    }

    public boolean isLoginSuccess() {
        try {
            if(errorMessage.isDisplayed()) {
                System.out.println("Has error message: " + errorMessage.getText());
                return false;
            }
        } catch (NoSuchElementException e) {}
        System.out.println("Login successfully");
        return true;
    }
}

