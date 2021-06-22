package framework.elements;

import framework.BaseTest;
import framework.Browser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.List;
import java.util.concurrent.TimeUnit;

public abstract class BaseElement extends BaseTest {
    protected WebElement element;
    protected List<WebElement> elements;
    protected WebDriver driver;
    private By by;
    private String name;
    private WebDriverWait wait;
    private static final int TIMEOUT_WAIT_0 = 0;


    public WebElement getElement() {
        waitForIsElementPresent();
        return element;
    }

    public List<WebElement> getElements() {
        waitForIsElementPresent();
        return elements;
    }

    public void sort() {
        waitForIsElementPresent();
        getElements();

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
        isPresent();
        if (!element.isDisplayed()) {
            Assert.fail("Element do not found");
        }
        return false;
    }

    public boolean waitForInvisibility() {
        isPresent();
        return wait.until(ExpectedConditions.invisibilityOf(element));
    }

    public WebElement waitForClickable(By by) {
        isPresent();
        wait.until(ExpectedConditions.elementToBeClickable(by));
        return element;
    }

    public boolean isPresent() {
        return isPresent(TIMEOUT_WAIT_0);
    }

    public boolean isPresent(int timeout) {
        WebDriverWait wait = new WebDriverWait(Browser.getInstance().getDriver(), timeout);
        browser.getDriver().manage().timeouts().implicitlyWait(Integer.valueOf(browser.getTimeoutForCondition()), TimeUnit.SECONDS);
        try {
            wait.until((ExpectedCondition<Boolean>) new ExpectedCondition<Boolean>() {
                public Boolean apply(final WebDriver driver) {
                    try {
                        List<WebElement> list = driver.findElements(by);
                        for (WebElement el : list) {
                            if (el instanceof RemoteWebElement && el.isDisplayed()) {
                                element = (RemoteWebElement) el;
                                return element.isDisplayed();
                            }
                        }
                        element = (RemoteWebElement) driver.findElement(by);
                    } catch (Exception e) {
                        return false;
                    }
                    return element.isDisplayed();
                }
            });
        } catch (Exception e) {
            return false;
        }
        try {
            browser.getDriver().manage().timeouts().implicitlyWait(Integer.valueOf(browser.getTimeoutForCondition()), TimeUnit.SECONDS);
            return element.isDisplayed();
        } catch (Exception e) {
            Assert.fail("Element does not found");
        }
        return false;
    }


    public void sendKeys(String sendKeys) {
        getElement().sendKeys(sendKeys);
    }

    public boolean isSelected() {
        waitForIsElementPresent();
        return element.isSelected();
    }

    public boolean isDisplayed() {
        waitForIsElementPresent();
        return element.isDisplayed();
    }

    public void click() {
        waitForIsElementPresent();
        element.click();
    }

    public void clickAndWait() {
        waitForIsElementPresent();
        element.click();
        browser.waitForPageToLoad();
    }

    public String getText() {
        waitForIsElementPresent();
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


    }

    public abstract String[] split(String velue);


    public abstract int size();
}