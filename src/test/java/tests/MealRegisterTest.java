package tests;

import constant.Common;
import core.BaseTest;
import core.SystemDefault;
import helpers.WebElementHelper;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.MealRegisterPage;

import java.io.IOException;
import java.util.List;

public class MealRegisterTest extends BaseTest<MealRegisterPage> {
    @BeforeClass
    public void setUp() throws IOException {
//        initExcelData("BookingInfo", new String[]{"booking_code", "email", "phone_number", "expected"});
        loginPage = new LoginPage();
        if(!this.loginPage.openPage().login(SystemDefault.DEFAULT_USER, SystemDefault.DEFAULT_PASSWORD)) {
            Assert.fail("Cannot login with current user");
            return;
        }
        page = new MealRegisterPage();
    }

    @BeforeMethod
    public void refreshPage() {
        driver.navigate().refresh();
        page.openPage().waitElementDisplayed(page.formContainer);
        System.out.println("[Step] Open Meal Register page");
    }

    @Test(description = "TC01", priority = 1)
    public void TC01() throws InterruptedException {
        page.selectMealType(Common.MEAL_TYPE_TABLE).selectNextDay();
        System.out.println("[Step] Open Meal Register page");
    }

    @Test(description = "Check code auto created", priority = 11)
    public void TC02() throws InterruptedException {
        try {
            String code = page.selectCurrentDay().addNewRowForEmployee().fillEmployeeName("Bảo Hân", true).fillMenu("").clickSave().getCodeValue();
            Assert.assertFalse(code.isEmpty());
        } catch (Exception e) {
            System.out.println("[Error] Have an error : " + e.getMessage());
            Assert.fail();
        }
    }

    @Test(description = "Check Logged in username and register meal username", priority = 4)
    public void TC04() throws InterruptedException {
        Assert.assertEquals(page.getRegisterUser(), page.getCurLoggedInUser());
    }

    @Test(description = "Check list value of meal", priority = 5)
    public void TC05() throws InterruptedException {
        List<String> mealTypes = List.of("Suất", "Bàn");
        Assert.assertEquals(page.getListMealType(), mealTypes);
        Assert.assertEquals(page.getSelectedMealType(), Common.MEAL_TYPE_SINGLE);
    }

    @Test(description = "Check list field when select meal type = Bàn", priority = 6)
    public void TC06() throws InterruptedException {
        page.selectMealType(Common.MEAL_TYPE_TABLE);
        Assert.assertTrue(page.checkListFieldWhenSelectMealType());
    }

    @Test(description = "Check input the day in the past", priority = 7)
    public void TC07() throws InterruptedException {
        Assert.assertEquals("Ngày Đăng Ký Phải Lớn Hơn Hoặc Bằng Ngày Hiện Tại.", page.selectDateTimeInPass().addNewRowForEmployee().getPopupMessage());
    }

    // TODO:
    @Test(description = "Check input current day", priority = 8)
    public void TC08() throws InterruptedException {
        page.selectCurrentDay().addNewRowForEmployee();
    }

    // TODO:
    @Test(description = "Check input next day", priority = 9)
    public void TC09() throws InterruptedException {
        page.selectNextDay().addNewRowForEmployee();
    }

    // TODO:
    @Test(description = "Check empty day", priority = 10)
    public void TC10() throws InterruptedException {
        Assert.assertEquals(page.clickSave().getErrorMessage(), "Ngày đăng ký");
    }

    @Test(description = "Check exist username add successfully", priority = 11)
    public void TC11() throws InterruptedException {
        try {
            String code = page.selectCurrentDay().addNewRowForEmployee().fillEmployeeName("Bảo Hân", true).fillMenu("").clickSave().getCodeValue();
            Assert.assertFalse(code.isEmpty());
        } catch (Exception e) {
            System.out.println("[Error] Have an error : " + e.getMessage());
            Assert.fail();
        }
    }

    @Test(description = "Check display dialog add new employee", priority = 12)
    public void TC12() throws InterruptedException {
        try {
            boolean haveDisplayDialog = page.selectCurrentDay().addNewRowForEmployee().fillEmployeeName("Bảo Hân 1", false).clickSave().checkDisplayedPopupDialog();
            Assert.assertTrue(haveDisplayDialog);
        } catch (Exception e) {
            System.out.println("[Error] Have an error : " + e.getMessage());
            Assert.fail();
        }
    }

    @Test(description = "Check click display dialog list employee", priority = 14)
    public void TC13() throws InterruptedException {
        try {
            boolean haveDisplayDialog = page.selectCurrentDay().addNewRowForEmployee().clickMoreItem().checkDisplayedPopupDialog();
            Assert.assertTrue(haveDisplayDialog);
        } catch (Exception e) {
            System.out.println("[Error] Have an error : " + e.getMessage());
            Assert.fail();
        }
    }

//    TODO
//    @Test(description = "Check click display dialog list employee", priority = 14)
//    public void TC14() throws InterruptedException {
//        try {
//            boolean haveDisplayDialog = page.selectCurrentDay().addNewRowForEmployee().clickMoreItem().checkDisplayedPopupDialog();
//            Assert.assertTrue(haveDisplayDialog);
//        } catch (Exception e) {
//            System.out.println("[Error] Have an error : " + e.getMessage());
//            Assert.fail();
//        }
//    }

    @Test(description = "Check empty username", priority = 15)
    public void TC15() throws InterruptedException {
        try {
            page.selectCurrentDay().clickSave();
            Assert.assertEquals(WebElementHelper.getModalDialogText(), "Bạn Chưa Đăng Ký Bữa Ăn");
        } catch (Exception e) {
            System.out.println("[Error] Have an error : " + e.getMessage());
            Assert.fail();
        }
    }

//    TODO
    @Test(description = "Check day dont have menu", priority = 16)
    public void TC16() throws InterruptedException {
        try {
        } catch (Exception e) {
            System.out.println("[Error] Have an error : " + e.getMessage());
            Assert.fail();
        }
    }

    @Test(description = "Check day have menu", priority = 17)
    public void TC17() throws InterruptedException {
        try {
            String code = page.selectCurrentDay().addNewRowForEmployee().fillEmployeeName("Bảo Hân", true).fillMenu("").clickSave().getCodeValue();
            Assert.assertFalse(code.isEmpty());
        } catch (Exception e) {
            System.out.println("[Error] Have an error : " + e.getMessage());
            Assert.fail();
        }
    }

    @Test(description = "Do not select day", priority = 18)
    public void TC18() throws InterruptedException {
        try {
           page.addNewRowForEmployee().fillEmployeeName("Bảo Hân", true).fillMenu("");
        } catch (Exception e) {
            System.out.println("[Error] Have an error : " + e.getMessage());
        }
    }

    @Test(description = "Check empty menu", priority = 19)
    public void TC19() throws InterruptedException {
        try {
            page.selectCurrentDay().addNewRowForEmployee().fillEmployeeName("Bảo Hân", false).clickSave();
        } catch (Exception e) {
            System.out.println("[Error] Have an error : " + e.getMessage());
            Assert.fail();
        }
    }
}