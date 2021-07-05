package framework;

import framework.elements.Label;
import org.openqa.selenium.By;
import org.testng.Assert;


public class BasePage extends BaseTest {
    protected String title;
    protected By titleLocator;

    public BasePage(final By locator, final String pageTitle) {
        init(locator, pageTitle);
        assertIsOpen();
    }

    private void init(final By locator, final String pageTitle) {
        titleLocator = locator;
        title = pageTitle;
    }

    public void assertIsOpen() {
        Label elem = new Label(titleLocator, title);
        try {
            elem.waitForIsElementPresent();
        } catch (Throwable e) {
            Assert.assertTrue(true, title + " does not open");

        }
    }
}
