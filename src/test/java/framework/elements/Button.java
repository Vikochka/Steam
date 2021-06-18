package framework.elements;

import org.openqa.selenium.By;

public class Button extends BaseElement {


    public Button(By by) {
        super(by);
    }

    public Button(By by, String name) {
        super(by, name);
    }

    public String getElementType() {
        return getElementType();
    }

    public String[] split(String value) {
        return new String[0];
    }

    public int size() {
        return 0;
    }

    public void clickButton() {
        click();
    }
}