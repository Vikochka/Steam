package Steam.pages;

import framework.BasePage;
import framework.elements.Button;
import framework.elements.ComboBox;
import org.openqa.selenium.By;

import static framework.PropertyReader.getProperty;

public class AgePage extends BasePage {
    private static By pageLocator = By.xpath("//div[contains(text(),'Please enter your birth date to continue:')]");
    private static String viewPage = "//a[@class='btnv6_blue_hoverfade btn_medium']/span[text()='%s']";

    private ComboBox dropdownYear = new ComboBox(By.id("ageYear"));

    public AgePage() {
        super(pageLocator, "Age page");
    }

    public void ageCheck(String interYear) {
        dropdownYear.selectComboBox(interYear);
        Button btnViewPage = new Button(By.xpath(String.format(viewPage,getProperty("view_page_key"))));
        btnViewPage.clickAndWait();
    }
}