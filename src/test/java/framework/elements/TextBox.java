package framework.elements;

import org.openqa.selenium.By;

public class TextBox extends BaseElement {

    public TextBox(By by) {
        super(by);
    }

    public TextBox(By by, String name) {
        super(by, name);
    }

    public String getElementType() {
        return getLoc("log.text.box");
    }

    public void type(final String value) {
        waitForIsElementPresent();
        element.sendKeys(value);
    }

    public String getText() {
        waitForIsElementPresent();
        element.getText();
        return null;
    }
}