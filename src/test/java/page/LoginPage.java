package page;

import core.BasePage;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends BasePage {
    @FindBy(id = "button_login_home")
    private WebElement loginButton;

    @FindBy(id = "txtUserName")
    private WebElement usernameField;

    @FindBy(id = "txtPassword")
    private WebElement passwordField;

    @FindBy(id = "btnSignIn")
    private WebElement signInButton;

    @FindBy(id = "android:id/button2")
    private WebElement cancelBtn;

    @FindBy(id = "android:id/message")
    private WebElement errorMessage;

    public LoginPage() {
        super();
        this.pageUrl = "";
        PageFactory.initElements(driver, this);
    }

    public void login(String username, String password) throws InterruptedException {
        System.out.println("Login with info: " + username + "/" + password);
        loginButton.click();
        usernameField.sendKeys(username);
        passwordField.sendKeys(password);
        signInButton.click();
    }

    public String isLoginSuccessfully() {
        try {
            String message = errorMessage.getText();
            cancelBtn.click();
            return message;
        } catch (NoSuchElementException e) {
            return "Pass";
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return e.getMessage();
        }
    }
}
