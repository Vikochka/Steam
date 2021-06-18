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
        return getElementType();
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

    public String[] split(String value) {
        return new String[0];
    }


    public int size() {
        return 0;
    }
}
