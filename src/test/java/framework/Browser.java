package framework;

import framework.elements.Label;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.concurrent.TimeUnit;


public class Browser {

    private static final long IMPLICITLY_WAIT = 10;
    private static final String DEFAULT_CONDITION_TIMEOUT = "timeoutElement";
    private static final String DEFAULT_PAGE_LOAD_TIMEOUT = "timeout";

    static final String PROPERTIES_FILE = "config.properties";

    private static Browser instance;
    private static WebDriver driver;

    private static String timeoutForPageLoad;
    private static String timeoutForCondition;

    public static PropertyReader props;

    public boolean isBrowserAlive() {
        return instance != null;
    }


    public static Browser getInstance() {
        if (instance == null) {
            initProperties();
            try {
                driver = DriverFactory.getDriver();
                driver.manage().timeouts().implicitlyWait(IMPLICITLY_WAIT, TimeUnit.SECONDS);
            } catch (Exception e) {
                Assert.fail("Driver does not instance");
            }
            instance = new Browser();
        }
        return instance;
    }

    private static void initProperties() {
        props = new PropertyReader(PROPERTIES_FILE);
        timeoutForPageLoad = props.getProperty(DEFAULT_PAGE_LOAD_TIMEOUT);
        timeoutForCondition = props.getProperty(DEFAULT_CONDITION_TIMEOUT);
    }

    public void exit() {
        try {
            driver.quit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            instance = null;
        }
    }

    public String getTimeoutForCondition() {
        return timeoutForCondition;
    }

    public String getTimeoutForPageLoad() {
        return timeoutForPageLoad;
    }

    public void waitForPageToLoad() {
        WebDriverWait wait = new WebDriverWait(driver, Long.parseLong(getTimeoutForPageLoad()));
        try {
            wait.until((ExpectedCondition<Boolean>) new ExpectedCondition<Boolean>() {
                public Boolean apply(final WebDriver d) {
                    if (!(d instanceof JavascriptExecutor)) {
                        return true;
                    }
                    Object result = ((JavascriptExecutor) d)
                            .executeScript("return document['readyState'] ? 'complete' == document.readyState : true");
                    if (result != null && result instanceof Boolean && (Boolean) result) {
                        return true;
                    }
                    return false;
                }
            });
        } catch (Exception e) {
            Assert.fail("Page does not Load");
        }
    }

    public void windowMaximise() {
        try {
            driver.manage().window().maximize();
        } catch (Exception e) {
            //A lot of browsers crash here
        }

    }

    public void navigate(final String url) {
        driver.navigate().to(url);
    }


    public WebDriver getDriver() {
        return driver;
    }

    @Deprecated
    public void click(final By selector) {
        new Label(selector).click();
    }

    public String getLocation() {
        return driver.getCurrentUrl();
    }
}
