package framework.elements;

import org.openqa.selenium.By;

public class Label extends BaseElement {

    public Label(By by) {
        super(by);
    }

    public Label(By by, String name) {
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
}