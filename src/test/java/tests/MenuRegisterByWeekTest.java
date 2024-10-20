package tests;

import constant.Common;
import core.BaseTest;
import core.SystemDefault;
import helpers.RandomHelper;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.MenuRegisterByWeekPage;
import pages.MenuRegisterPage;

import java.io.IOException;
import java.util.List;

public class MenuRegisterByWeekTest extends BaseTest<MenuRegisterByWeekPage> {
    @BeforeClass
    public void setUp() throws IOException {
        loginPage = new LoginPage();
        if(!this.loginPage.openPage().login(SystemDefault.DEFAULT_USER, SystemDefault.DEFAULT_PASSWORD)) {
            Assert.fail("Cannot login with current user");
            return;
        }
        page = new MenuRegisterByWeekPage();
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
            String code = page.selectFirstWeek().fillMenuItem("", true).selectMealType(Common.MEAL_TYPE_SINGLE).clickSave().getCode();
            System.out.println("[Result] Check code auto created: " + code);
        } catch (Exception e) {
            System.out.println("[Error] Have an error : " + e.getMessage());
            Assert.fail();
        }
    }

    @Test(description = "Empty day", priority = 2)
    public void TC02() throws InterruptedException {
        try {
            String error = page.clickSave().getErrorMessage();
            Assert.assertEquals(error, "Từ Ngày");
        } catch (Exception e) {
            System.out.println("[Error] Have an error : " + e.getMessage());
            Assert.fail();
        }
    }

    @Test(description = "Empty meal type", priority = 3)
    public void TC03() throws InterruptedException {
        try {
            String errorMessage = page.selectDateTimeInPass().fillMenuItem("", true).clickSave().getErrorMessage();
            Assert.assertEquals(errorMessage, "Kiểu Thực Đơn");
        } catch (Exception e) {
            System.out.println("[Error] Have an error : " + e.getMessage());
            Assert.fail();
        }
    }

    @Test(description = "Check list value of meal", priority = 4)
    public void TC04() throws InterruptedException {
        List<String> mealTypes = List.of("Suất", "Bàn");
        Assert.assertEquals(page.getListMealType(), mealTypes);
    }

    @Test(description = "Check list value of numberOfPeople", priority = 5)
    public void TC05() throws InterruptedException {
        List<String> numberOfPeople = List.of("4", "6");
        Assert.assertEquals(page.selectMealType(Common.MEAL_TYPE_TABLE).getListNumberOfPeople(), numberOfPeople);
    }

    @Test(description = "Check error message when empty menu", priority = 6)
    public void TC09() throws InterruptedException {
        String errorMessage = page.selectDateTimeInPass().selectMealType(Common.MEAL_TYPE_SINGLE).clickSave().getErrorMessage();
        Assert.assertEquals(errorMessage, "Thực Đơn");
    }

    @Test(description = "Check exist menu", priority = 7)
    public void TC7() throws InterruptedException {
        try {
            String code = page.selectCurrentDay().fillMenuItem("", true).selectMealType(Common.MEAL_TYPE_SINGLE).clickSave().getCode();
            System.out.println("[Result] Check code auto created: " + code);
        } catch (Exception e) {
            System.out.println("[Error] Have an error : " + e.getMessage());
            Assert.fail();
        }
    }

    @Test(description = "Check exist menu", priority = 8)
    public void TC11() throws InterruptedException {
        try {
            String code = page.selectCurrentDay().selectMealType(Common.MEAL_TYPE_SINGLE).fillMenuItem(RandomHelper.generateRandomString(20), false).clickSave().getPopupMessage();
        } catch (Exception e) {
            System.out.println("[Error] Have an error : " + e.getMessage());
            Assert.fail();
        }
    }
}
