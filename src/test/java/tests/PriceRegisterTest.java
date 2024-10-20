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
import pages.PriceRegisterPage;

import java.io.IOException;
import java.util.List;

public class PriceRegisterTest extends BaseTest<PriceRegisterPage> {
    @BeforeClass
    public void setUp() throws IOException {
        loginPage = new LoginPage();
        if(!this.loginPage.openPage().login(SystemDefault.DEFAULT_USER, SystemDefault.DEFAULT_PASSWORD)) {
            Assert.fail("Cannot login with current user");
            return;
        }
        page = new PriceRegisterPage();
    }

    @BeforeMethod
    public void refreshPage() {
        driver.navigate().refresh();
        page.openPage().waitElementDisplayed(page.formContainer);
        System.out.println("[Step] Open Price Register page");
    }

    @Test(description = "Check code auto created", priority = 1)
    public void TC01() throws InterruptedException {
        try {
            String code = page.selectPriceType(Common.PRICE_TYPE_SING)
                    .selectStartDate(RandomHelper.getCurrentDay())
                    .selectEndDate(RandomHelper.getNextDate())
                    .fillRoom("", true)
                    .fillOrderPeople("", true)
                    .addNewRow()
                    .fillMenu("", true)
                    .clickSave()
                    .getCodeValue();
            Assert.assertFalse(code.isEmpty());
        } catch (Exception e) {
            System.out.println("[Error] Have an error : " + e.getMessage());
            Assert.fail();
        }
    }

    @Test(description = "Check empty start day", priority = 2)
    public void TC02() throws InterruptedException {
        String errorMessage = page.selectPriceType(Common.PRICE_TYPE_SING)
                .fillOrderPeople("", true)
                .fillRoom("", true)
                .selectEndDate(RandomHelper.getNextDate())
                .clickSave()
                .getErrorMessage();
        Assert.assertEquals(errorMessage, "Ngày bắt đầu");
    }

    @Test(description = "Check day in future", priority = 3)
    public void TC03() throws InterruptedException {
        String code = page.selectPriceType(Common.PRICE_TYPE_SING)
                .selectStartDate(RandomHelper.getCurrentDay())
                .selectEndDate(RandomHelper.getNextDate())
                .fillRoom("", true)
                .fillOrderPeople("", true)
                .addNewRow()
                .fillMenu("", true)
                .clickSave()
                .getCodeValue();
        Assert.assertFalse(code.isEmpty());
    }

    @Test(description = "Check current day", priority = 4)
    public void TC04() throws InterruptedException {
        String code = page.selectPriceType(Common.PRICE_TYPE_SING)
                .selectStartDate(RandomHelper.getCurrentDay())
                .selectEndDate(RandomHelper.getNextDate())
                .fillRoom("", true)
                .fillOrderPeople("", true)
                .addNewRow()
                .fillMenu("", true)
                .clickSave()
                .getCodeValue();
        Assert.assertFalse(code.isEmpty());
    }

    @Test(description = "Check pass day", priority = 5)
    public void TC05() throws InterruptedException {
        String error = page.selectPriceType(Common.PRICE_TYPE_SING)
                .selectStartDate(RandomHelper.randomPastDate(null))
                .clickSave()
                .getPopupMessage();
        Assert.assertEquals(error.trim(), "Ngày bắt đầu phải lớn hơn hoặc bằng ngày hiện tại.");
    }

    @Test(description = "Check exist ordered people", priority = 6)
    public void TC06() throws InterruptedException {
        String code = page.selectPriceType(Common.PRICE_TYPE_SING)
                .selectStartDate(RandomHelper.getCurrentDay())
                .selectEndDate(RandomHelper.getNextDate())
                .fillRoom("", true)
                .fillOrderPeople("", true)
                .addNewRow()
                .fillMenu("", true)
                .clickSave()
                .getCodeValue();
        Assert.assertFalse(code.isEmpty());
    }

    @Test(description = "Check does not exist ordered people", priority = 7)
    public void TC07() throws InterruptedException {
        boolean haveDisplayDialog = page.selectPriceType(Common.PRICE_TYPE_SING)
                .selectStartDate(RandomHelper.getCurrentDay())
                .selectEndDate(RandomHelper.getNextDate())
                .fillRoom("", true)
                .fillOrderPeople(RandomHelper.generateRandomString(10), false)
                .clickSave().checkDisplayedPopupDialog();
        Assert.assertTrue(haveDisplayDialog);
    }

    @Test(description = "Check empty ordered people", priority = 8)
    public void TC08() throws InterruptedException {
        String errorMessage = page.selectPriceType(Common.PRICE_TYPE_SING)
                .selectStartDate(RandomHelper.getCurrentDay())
                .selectEndDate(RandomHelper.getNextDate())
                .fillRoom("", true)
                .clickSave().getErrorMessage();
        Assert.assertEquals(errorMessage, "Người Đặt");
    }

    @Test(description = "Check price type ", priority = 9)
    public void TC09() throws InterruptedException {
        List<String> mealTypes = List.of(Common.PRICE_TYPE_SING, Common.PRICE_TYPE_EATING);
        Assert.assertEquals(page.getLisPriceType(), mealTypes);
    }

    @Test(description = "Check price type valid", priority = 10)
    public void TC10() throws InterruptedException {
        String code = page.selectPriceType(Common.PRICE_TYPE_SING)
                .selectStartDate(RandomHelper.getCurrentDay())
                .selectEndDate(RandomHelper.getNextDate())
                .fillRoom("", true)
                .fillOrderPeople("", true)
                .addNewRow()
                .fillMenu("", true)
                .clickSave()
                .getCodeValue();
        Assert.assertFalse(code.isEmpty());
    }

    @Test(description = "Check empty room", priority = 11)
    public void TC11() throws InterruptedException {
        String errorMessage = page.selectPriceType(Common.PRICE_TYPE_SING)
                .selectStartDate(RandomHelper.getCurrentDay())
                .selectEndDate(RandomHelper.getNextDate())
                .fillOrderPeople("", true)
                .clickSave()
                .getErrorMessage();
        Assert.assertEquals(errorMessage, "Phòng");
    }

    @Test(description = "Check room valid", priority = 12)
    public void TC12() throws InterruptedException {
        String code = page.selectPriceType(Common.PRICE_TYPE_SING)
                .selectStartDate(RandomHelper.getCurrentDay())
                .selectEndDate(RandomHelper.getNextDate())
                .fillRoom("", true)
                .fillOrderPeople("", true)
                .addNewRow()
                .fillMenu("", true)
                .clickSave()
                .getCodeValue();
        Assert.assertFalse(code.isEmpty());
    }

    @Test(description = "Check does not have room", priority = 13)
    public void TC13() throws InterruptedException {
        boolean haveDisplayDialog = page.selectPriceType(Common.PRICE_TYPE_SING)
                .selectStartDate(RandomHelper.getCurrentDay())
                .selectEndDate(RandomHelper.getNextDate())
                .fillOrderPeople("", true)
                .fillRoom(RandomHelper.generateRandomString(10), false)
                .clickSave().checkDisplayedPopupDialog();
        Assert.assertTrue(haveDisplayDialog);
    }

    @Test(description = "Check empty deposit", priority = 14)
    public void TC14() throws InterruptedException {
        String code = page.selectPriceType(Common.PRICE_TYPE_SING)
                .selectStartDate(RandomHelper.getCurrentDay())
                .selectEndDate(RandomHelper.getNextDate())
                .fillRoom("", true)
                .fillOrderPeople("", true)
                .addNewRow()
                .fillMenu("", true)
                .clickSave()
                .getCodeValue();
        Assert.assertFalse(code.isEmpty());
    }

    @Test(description = "Check invalid deposit", priority = 15)
    public void TC15() throws InterruptedException {
        String errorMessage = page.selectPriceType(Common.PRICE_TYPE_SING)
                .selectStartDate(RandomHelper.getCurrentDay())
                .selectEndDate(RandomHelper.getNextDate())
                .fillOrderPeople("", true)
                .fillRoom("", true)
                .fillDeposit("adf")
                .clickSave().getErrorMessage();
        Assert.assertEquals(errorMessage, "Tiền cọc");
    }

    @Test(description = "Check empty menu", priority = 16)
    public void TC16() throws InterruptedException {
        String code = page.selectPriceType(Common.PRICE_TYPE_SING)
                .selectStartDate(RandomHelper.getCurrentDay())
                .selectEndDate(RandomHelper.getNextDate())
                .fillRoom("", true)
                .fillOrderPeople("", true)
                .addNewRow()
                .clickSave()
                .getCodeValue();
        Assert.assertFalse(code.isEmpty());
    }

    @Test(description = "Check exist menu", priority = 17)
    public void TC17() throws InterruptedException {
        String code = page.selectPriceType(Common.PRICE_TYPE_SING)
                .selectStartDate(RandomHelper.getCurrentDay())
                .selectEndDate(RandomHelper.getNextDate())
                .fillRoom("", true)
                .fillOrderPeople("", true)
                .addNewRow()
                .fillMenu("", true)
                .clickSave()
                .getCodeValue();
        Assert.assertFalse(code.isEmpty());
    }

    @Test(description = "Check not exist menu", priority = 18)
    public void TC18() throws InterruptedException {
        Boolean haveDialogDisplay = page.selectPriceType(Common.PRICE_TYPE_SING)
                .selectStartDate(RandomHelper.getCurrentDay())
                .selectEndDate(RandomHelper.getNextDate())
                .fillRoom("", true)
                .fillOrderPeople("", true)
                .addNewRow()
                .fillMenu(RandomHelper.generateRandomString(10), false)
                .clickSave()
                .checkDisplayedPopupDialog();
        Assert.assertTrue(haveDialogDisplay);
    }
}
