package steam.pageObject.pages;

import framework.elements.Button;
import framework.elements.ComboBox;
import framework.elements.Label;
import org.openqa.selenium.By;

import static framework.PropertyReader.getProperty;

public class AgePage extends BaseSteamPage {
    private static String pageLocator = "//div[contains(text(),'%s')]";
    private static String btnViewPageTemplate = "//a[@class='btnv6_blue_hoverfade btn_medium']/span[text()='%s']";

    private static ComboBox comboBoxYear = new ComboBox(By.id("ageYear"));

    public AgePage() {
        super(By.xpath(String.format(pageLocator, getProperty("age_check"))), "Age page");
    }

    public static boolean waitForPageToLoad() {
        Label lblAgePage = new Label(By.xpath(String.format(pageLocator, getProperty("age_check"))), "Age page");
        if (!lblAgePage.waitForIsElementPresent()) {
            System.out.println("Age page appeared");
            return true;
        } else {
            System.out.println("Age page did not appear");
            return false;
        }
    }

    public BaseSteamPage checkAge(String interYear) {
        System.out.println("Check age");

        comboBoxYear.selectComboBox(interYear);
        Button btnViewPage = new Button(By.xpath(String.format(btnViewPageTemplate, getProperty("view_page_key"))));
        btnViewPage.clickAndWait();
        return new GamePage();
    }
}