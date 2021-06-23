package Steam.pages;

import framework.BasePage;
import framework.PropertyReader;
import framework.elements.Label;
import framework.elements.TextBox;
import org.openqa.selenium.By;


public class MainPage extends BasePage {
    HeaderPage headerPage;

    private Label lblLanguage = new Label(By.id("language_pulldown"));

    private static By pageLocator = By.id("logo_holder");

    public MainPage() {
        super(pageLocator, "Main page");
        headerPage = new HeaderPage();
    }

    public void selectLanguage(String selectLanguage) {
        headerPage.selectLanguage(selectLanguage);
    }
}