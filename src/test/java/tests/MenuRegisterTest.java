package tests;

import constant.Common;
import core.BaseTest;
import core.SystemDefault;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.MenuRegisterPage;
import java.io.IOException;

public class MenuRegisterTest extends BaseTest<MenuRegisterPage> {
    @BeforeClass
    public void setUp() throws IOException {
        loginPage = new LoginPage();
        if(!this.loginPage.openPage().login(SystemDefault.DEFAULT_USER, SystemDefault.DEFAULT_PASSWORD)) {
            Assert.fail("Cannot login with current user");
            return;
        }
        page = new MenuRegisterPage();
    }

    @BeforeMethod
    public void refreshPage() {
        driver.navigate().refresh();
        page.openPage().waitElementDisplayed(page.formContainer);
        System.out.println("[Step] Open Menu Register page");
    }

    @Test(description = "Check code auto created", priority = 1)
    public void TC01() throws InterruptedException {
        try {
            page.selectCurrentDay().fillMenuItem("").selectMealType(Common.MEAL_TYPE_SINGLE).clickSave();
        } catch (Exception e) {
            System.out.println("[Error] Have an error : " + e.getMessage());
            Assert.fail();
        }
    }

}
