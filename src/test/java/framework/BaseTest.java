package framework;

import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class BaseTest {

    protected static Browser browser = Browser.getInstance();
    protected ITestContext context;

    @BeforeClass
    public void before(ITestContext context) {
        this.context = context;
        browser = Browser.getInstance();
        browser.windowMaximise();
        browser.navigate(PropertyReader.getProperty("URL"));
        context.setAttribute("driver", browser);
    }

    @AfterClass(alwaysRun = true, description = "Closing browser")
    public void after() {
        if (browser.isBrowserAlive()) {
            browser.exit();
        }
    }
}