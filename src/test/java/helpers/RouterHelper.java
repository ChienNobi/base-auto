package helpers;

import config.BrowserConfig;
import constant.Common;
import org.openqa.selenium.WebDriver;

public class RouterHelper {

    WebDriver driver;

    public RouterHelper() {
        this.driver = BrowserConfig.getDriver();
    }

    public void openMenuListByDay() {
        driver.get(Common.MENU_LIST_BY_DAY);
    }

    public void openMenuListByWeek() {
        driver.get(Common.MENU_LIST_BY_WEEK);
    }

    public void openMealList() {
        driver.get(Common.MEAL_LIST);
    }

    public void openMealRegister() {
        driver.get(Common.MEAL_REGISTER);
    }
}
