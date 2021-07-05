package framework;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;

import java.util.HashMap;


public class DriverFactory {

    public static WebDriver getDriver() {
        WebDriver driver = null;
        String browser = PropertyReader.getProperty("browser");

        switch (browser) {
            case "chrome":
                WebDriverManager.chromedriver().setup();

                HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
                chromePrefs.put("profile.default_content_settings.popups", 0);
                chromePrefs.put("download.default_directory", System.getProperty("user.dir") + PropertyReader.getProperty("filePath"));
                chromePrefs.put("safebrowsing.enabled", "true");

                ChromeOptions options = new ChromeOptions();
                options.setExperimentalOption("prefs", chromePrefs);

                driver = new ChromeDriver(options);
                break;

            case "firefox":
                WebDriverManager.firefoxdriver().setup();

                FirefoxOptions firefoxOptions = new FirefoxOptions();

                FirefoxProfile profile = new FirefoxProfile();
                profile.setPreference("browser.download.folderList", 2);
                profile.setPreference("browser.download.dir", System.getProperty("user.dir") + "\\src\\test\\resources\\downloads");
                profile.setPreference("browser.download.useDownloadDir", true);
                profile.setPreference("browser.helperApps.neverAsk.saveToDisk", "application/octet-stream");
                firefoxOptions.setProfile(profile);

                driver = new FirefoxDriver(firefoxOptions);
                break;

            default:
                System.out.println("Invalid browser name");
        }
        return driver;
    }
}