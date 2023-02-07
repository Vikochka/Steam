package steam.pageObject.pages;

import framework.elements.Button;
import framework.elements.ComboBox;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;

import static framework.PropertyReader.getProperty;

@Log4j2
public class AgePage extends BaseSteamPage {

    private static String pageLocator = "//div[contains(text(),'%s')]";
    private static String btnViewPageTemplate = "//a[@class='btnv6_blue_hoverfade btn_medium']/span[text()='%s']";

    private static ComboBox comboBoxYear = new ComboBox(By.id("ageYear"));

    public AgePage() {
        super(By.xpath(String.format(pageLocator, getProperty("age_check"))), "Age page");
    }

    public BaseSteamPage checkAge(String interYear) {
        log.info("Check age:");
        comboBoxYear.selectComboBox(interYear);
        log.info("Enter year of birth: " + interYear);
        Button btnViewPage = new Button(By.xpath(String.format(btnViewPageTemplate, getProperty("view_page_key"))));
        btnViewPage.clickAndWait();
        log.info(getProperty("log.click") + " " + getProperty("view_page_key") + " button");
        return new GamePage();
    }
}