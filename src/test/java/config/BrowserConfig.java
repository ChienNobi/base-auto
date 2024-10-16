package config;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ThreadGuard;

import java.time.Duration;
import java.util.HashMap;

public class BrowserConfig {
    private HashMap<String, Object> prefs =  new HashMap<String, Object>();

    private static final ThreadLocal<WebDriver> driverThread = new ThreadLocal<>();
    private static WebDriver driver;

    private BrowserConfig(String language) {
        prefs.put("intl.accept_languages", language);
        prefs.put("download.prompt_for_download", false);
        prefs.put("download.directory_upgrade", true);
        prefs.put("safebrowsing.enabled", true);
        prefs.put("profile.default_content_setting_values.automatic_downloads", 1);

        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("prefs", prefs);
        options.addArguments("--window-size=1920,1080");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");
        options.addArguments("--verbose");
        options.addArguments("--remote-allow-origins=*");
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
    }

    public static WebDriver getDriver() {
        return driverThread.get();
    }

    public static WebDriver setDriver(String language) {
        new BrowserConfig(language);
        BrowserConfig.driverThread.set(ThreadGuard.protect(driver));
        return driver;
    }

    /**
     * set wait implicit
     *
     * @param timeout in millis second
     */
    public static void setWaitImplicit(int timeout) {
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(timeout));
    }

    public static void quit() {
        BrowserConfig.getDriver().quit();
        BrowserConfig.driverThread.remove();
    }
}
