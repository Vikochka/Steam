package framework.elements;

import framework.BaseTest;
import framework.Browser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
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
    private By by;
    private String name;
    private WebDriverWait wait;
    private static final int TIMEOUT_WAIT_0 = 0;


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

    public void waitForIsElementPresent() {

        isElementPresent(Integer.valueOf(browser.getTimeoutForCondition()));
//        isElementPresent();
//        if (!element.isDisplayed()) {
//            Assert.fail("Element do not found");
//        }
//        return false;
    }

    public List<WebElement> getElements() {
        waitForElementsArePresent();
        return elements;
    }

//    public boolean isPresent() {
//        return isPresent();
//    }

    public void waitForElementIsPresent() {
        isElementPresent(Integer.valueOf(browser.getTimeoutForCondition()));
    }

    public void waitForElementsArePresent() {
        areElementsPresent(Integer.valueOf(browser.getTimeoutForCondition()));
    }

    public boolean isElementPresent(int timeout) {
        WebDriverWait wait = new WebDriverWait(browser.getDriver(), timeout);
        try {
            browser.getDriver().manage().timeouts().implicitlyWait(Integer.valueOf(browser.getTimeoutForCondition()), TimeUnit.SECONDS);
            element = browser.getDriver().findElement(by);
            return element.isDisplayed();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Check are elements present on the page
     *
     * @return are elements present
     */
    public boolean areElementsPresent(int timeout) {
        WebDriverWait wait = new WebDriverWait(Browser.getInstance().getDriver(), timeout);
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

//    public boolean isPresent(int timeout) {
//        WebDriverWait wait = new WebDriverWait(Browser.getInstance().getDriver(), timeout);
//        browser.getDriver().manage().timeouts().implicitlyWait(Integer.valueOf(browser.getTimeoutForCondition()), TimeUnit.SECONDS);
//        try {
//            wait.until((ExpectedCondition<Boolean>) new ExpectedCondition<Boolean>() {
//                public Boolean apply(final WebDriver driver) {
//                    try {
//                        List<WebElement> list = driver.findElements(by);
//                        for (WebElement el : list) {
//                            if (el instanceof WebElement && el.isDisplayed()) {
//                                element = (WebElement) el;
//                                return element.isDisplayed();
//                            }
//                        }
//                        element = (WebElement) driver.findElement(by);
//                    } catch (Exception e) {
//                        return false;
//                    }
//                    return element.isDisplayed();
//                }
//            });
//        } catch (Exception e) {
//            return false;
//        }
//        try {
//            browser.getDriver().manage().timeouts().implicitlyWait(Integer.valueOf(browser.getTimeoutForCondition()), TimeUnit.SECONDS);
//            return element.isDisplayed();
//        } catch (Exception e) {
//            Assert.fail("Element does not found");
//        }
//        return false;
//    }


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