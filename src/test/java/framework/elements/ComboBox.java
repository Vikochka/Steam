package framework.elements;

import org.openqa.selenium.By;

public class ComboBox extends BaseElement {
    public ComboBox(By by) {
        super(by);
    }

    public ComboBox(By by, String name) {
        super(by, name);
    }

    protected String getElementType() {
        return null;
    }

    public String[] split(String value) {
        return new String[0];
    }

    public int size() {
        return 0;
    }


}
