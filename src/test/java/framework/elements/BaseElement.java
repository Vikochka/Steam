package framework.elements;

import framework.BaseTest;
import framework.Browser;
import framework.PropertyReader;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static framework.PropertyReader.getProperty;

@Log4j2
public abstract class BaseElement extends BaseTest {
    PropertyReader propertyReader = new PropertyReader("localisation/loc_en.properties");
    protected WebElement element;
    protected List<WebElement> elements;
    private By by;
    private String name;
    private WebDriverWait wait;

    public static String getLoc(final String key) {
        return getProperty(key);
    }

    public WebElement getElement() {
        waitForIsElementPresent();
        return element;
    }

    public BaseElement(By by) {
        this.by = by;
    }

    public BaseElement(By by, String name) {
        this.by = by;
        this.name = name;
    }

    protected abstract String getElementType();

    public boolean waitForIsElementPresent() {
        isElementPresent(Integer.valueOf(browser.getTimeoutForCondition()));
        return true;
    }

    public List<WebElement> getElements() {
        waitForElementsArePresent();
        return elements;
    }

    public void waitForElementsArePresent() {
        areElementsPresent(Integer.valueOf(browser.getTimeoutForCondition()));
    }

    public boolean isElementPresent(int timeout) {
        wait = new WebDriverWait(browser.getDriver(), timeout);
        try {
            browser.getDriver().manage().timeouts().implicitlyWait(Integer.valueOf(browser.getTimeoutForCondition()), TimeUnit.SECONDS);
            element = browser.getDriver().findElement(by);
            return element.isDisplayed();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean areElementsPresent(int timeout) {
        wait = new WebDriverWait(Browser.getInstance().getDriver(), timeout);
        browser.getDriver().manage().timeouts().implicitlyWait(Integer.valueOf(browser.getTimeoutForCondition()), TimeUnit.SECONDS);
        try {
            wait.until((ExpectedCondition<Boolean>) new ExpectedCondition<Boolean>() {
                public Boolean apply(final WebDriver driver) {
                    try {
                        elements = driver.findElements(by);
                        for (WebElement element : elements) {
                            if (element instanceof WebElement && element.isDisplayed()) {
                                element = (WebElement) element;
                                return element.isDisplayed();
                            }
                        }
                        element = (WebElement) driver.findElement(by);
                    } catch (Exception e) {
                        return false;
                    }
                    return element.isDisplayed();
                }
            });
        } catch (Exception e) {
            return false;
        }
        return false;
    }

    public void sendKeys(String sendKeys) {
        getElement().sendKeys(sendKeys);
    }

    public boolean isSelected() {
        waitForIsElementPresent();
        log.info(getProperty("log.select") + element.getText());
        return element.isSelected();
    }

    public boolean isDisplayed() {
        waitForIsElementPresent();
        return element.isDisplayed();
    }

    public void click() {
        waitForIsElementPresent();
        element.click();
        log.info(getProperty("log.click") + ": " + getElementType() + ": " + element.getText());
    }

    public void clickAndWait() {
        waitForIsElementPresent();
        element.click();
        browser.waitForPageToLoad();
        log.info(getProperty("log.click") + ": " + getElementType() + " = " + by + ". And waits for the page to load ");
    }

    public void clickViaJS() {
        waitForIsElementPresent();
        if (browser.getDriver() instanceof JavascriptExecutor) {
            ((JavascriptExecutor) browser.getDriver()).executeScript("arguments[0].click()", element);
        }
    }

    public String getText() {
        waitForIsElementPresent();
        log.info(getLoc("log.get.text") + ": " + element.getText());
        return element.getText();
    }

    public void moveAndClickByAction() {
        waitForIsElementPresent();
        Actions actions = new Actions(Browser.getInstance().getDriver());
        actions.moveToElement(element).perform();
        actions.click().perform();
    }

    public void moveToElement() {
        waitForIsElementPresent();
        Actions actions = new Actions(Browser.getInstance().getDriver());
        actions.moveToElement(element).perform();
    }

    public void selectComboBox(String value) {
        waitForIsElementPresent();
        Select select = new Select(element);
        select.selectByVisibleText(value);
        log.info(getLoc("log.select") + ": " + value);
    }
}