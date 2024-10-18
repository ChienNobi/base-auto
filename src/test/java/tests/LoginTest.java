package tests;

import core.BaseTest;
import core.SystemDefault;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.MealListPage;
import pages.LoginPage;
import pages.MealRegisterPage;

import java.io.IOException;

public class LoginTest extends BaseTest {

    @BeforeClass
    public void setUp() throws IOException {
//        initExcelData("BookingInfo", new String[]{"booking_code", "email", "phone_number", "expected"});
        loginPage = new LoginPage();
        if(!this.loginPage.openPage().login(SystemDefault.DEFAULT_USER, SystemDefault.DEFAULT_PASSWORD)) {
            Assert.fail("Cannot login with current user");
            return;
        }
    }

    @Test(description = "TC01")
    public void TC01() throws InterruptedException {
        MealRegisterPage page = new MealRegisterPage();
        page.openPage().waitElementDisplayed(page.formContainer);
        System.out.println("[Step] Open Meal Register page");
    }
}
