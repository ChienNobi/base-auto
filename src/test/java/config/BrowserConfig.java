package config;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.support.ThreadGuard;

import java.time.Duration;
import java.util.HashMap;

public class BrowserConfig {

    private static final ThreadLocal<WebDriver> driverThread = new ThreadLocal<>();

    public static WebDriver getDriver() {
        return driverThread.get();
    }

    public static WebDriver setDriver(String browser) {
        WebDriver driver = createDriver(browser);
        BrowserConfig.driverThread.set(ThreadGuard.protect(driver));
        driver.manage().window().maximize();
        return driver;
    }

    private static WebDriver createDriver(String browserName) {
        return switch (browserName.trim().toLowerCase()) {
            case "chrome" -> initChromeDriver();
            case "firefox" -> initFirefoxDriver();
            case "edge" -> initEdgeDriver();
            default -> {
                System.out.println("Browser: " + browserName + " is invalid, Launching Chrome as browser of choice...");
                yield initChromeDriver();
            }
        };
    }

    private static WebDriver initChromeDriver() {
        System.out.println("Launching Chrome driver...");
        HashMap<String, Object> prefs =  new HashMap<String, Object>();

        prefs.put("intl.accept_languages", "en");
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
        return new ChromeDriver(options);
    }

    private static WebDriver initFirefoxDriver() {
        System.out.println("Launching Firefox driver...");
        WebDriverManager.firefoxdriver().setup();
        FirefoxOptions options = new FirefoxOptions();
        options.setProfile(new FirefoxProfile());
        options.addArguments("--window-size=1920,1080");
        options.addArguments("--disable-dev-shm-usage");
        return new FirefoxDriver(options);
    }

    private static WebDriver initEdgeDriver() {
        System.out.println("Launching Edge driver...");
        WebDriverManager.edgedriver().setup();
        return new EdgeDriver();
    }

    /**
     * set wait implicit
     *
     * @param timeout in millis second
     */
    public static void setWaitImplicit(int timeout) {
        getDriver().manage().timeouts().implicitlyWait(Duration.ofMillis(timeout));
    }

    public static void quit() {
        BrowserConfig.getDriver().quit();
        BrowserConfig.driverThread.remove();
    }
}
