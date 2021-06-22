package Steam.pages;

import framework.BasePage;
import framework.PropertyReader;
import framework.elements.ComboBox;
import framework.elements.TextBox;
import org.openqa.selenium.By;

public class MainPage extends BasePage {

    private ComboBox lblLanguage = new ComboBox(By.id("language_pulldown"));

    private static String btnLanguage = "//a[@class='popup_menu_item tight'][contains(text(),'%s')]";
    private static By pageLocator = By.id("logo_holder");

    public MainPage() {
        super(pageLocator, "Main page");
    }

    public void selectLanguage(String selectLanguage) {
        lblLanguage.click();
        TextBox currentLanguage = new TextBox(By.xpath(String.format(btnLanguage, selectLanguage)));
        if (currentLanguage.isDisplayed()) {
            PropertyReader prop = new PropertyReader("localisation/loc_en.properties");
        } else {
            PropertyReader prop = new PropertyReader("localisation/loc_ru.properties");
        }
        currentLanguage.click();
    }
}