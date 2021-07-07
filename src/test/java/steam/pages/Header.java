package steam.pages;

import framework.PropertyReader;
import framework.elements.Button;
import framework.elements.Label;
import framework.elements.TextBox;
import org.openqa.selenium.By;


public class Header {
    PropertyReader prop;

    private Label lblLanguage = new Label(By.id("language_pulldown"));
    private Button btnInstallSteam = new Button(By.xpath("//a[@class='header_installsteam_btn_content']"));
    private static String btnLanguage = "//div[@class='popup_body popup_menu']//a[contains(text(),'%s')]";

    public void selectLanguage(String selectLanguage) {
        lblLanguage.click();
        TextBox currentLanguage = new TextBox(By.xpath(String.format(btnLanguage, selectLanguage)));
        prop = new PropertyReader("localisation/loc_en.properties");
        if (!currentLanguage.waitForIsElementPresent()) {
            lblLanguage.click();
        } else {
            currentLanguage.click();
        }
    }

    public void clickOnInstallSteam() {
        btnInstallSteam.clickAndWait();
    }
}