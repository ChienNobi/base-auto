package test;

import core.BaseTest;
import core.SystemDefault;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import page.MealListPage;
import page.LoginPage;

import java.io.IOException;

public class LoginTest extends BaseTest {

    @BeforeClass
    public void setUp() throws IOException {
//        initExcelData("BookingInfo", new String[]{"booking_code", "email", "phone_number", "expected"});
        loginPage = new LoginPage();
        loginPage.openPage();
    }

    @Test
    public void loginTest() throws InterruptedException {
        if(!this.loginPage.login(SystemDefault.DEFAULT_USER, SystemDefault.DEFAULT_PASSWORD)) {
            Assert.fail("Cannot login with current user");
            return;
        }

        MealListPage home = new MealListPage();
        home.clickBtnAdd();
    }
}
