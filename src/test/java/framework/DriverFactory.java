package framework;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.HashMap;


public class DriverFactory {

    public static WebDriver getDriver() {
        WebDriver driver = null;
        String browser = PropertyReader.getProperty("browser");

        switch (browser) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                //установить каталог автоматической загрузки в ChromeOptions, а затем создать драйвер с этой опцией
                HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
                chromePrefs.put("profile.default_content_settings.popups", 0);
                chromePrefs.put("download.default_directory", System.getProperty("user.dir") + PropertyReader.getProperty("filePath"));
                chromePrefs.put("safebrowsing.enabled", "true");

                ChromeOptions options = new ChromeOptions();
                options.setExperimentalOption("prefs", chromePrefs);
                driver = new ChromeDriver(options);
//                driver = new ChromeDriver();
                break;
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                break;
            default:
                System.out.println("Invalid browser name");
        }
        return driver;
    }
}