package steam.pages;

import framework.PropertyReader;
import framework.elements.Button;
import framework.elements.Label;
import framework.elements.TextBox;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class Header {
    PropertyReader prop;

    private Label lblLanguage = new Label(By.id("language_pulldown"));
    private Button btnInstallSteam = new Button(By.xpath("//a[@class='header_installsteam_btn_content']"));
    private static String btnLanguage = "//a[@class='popup_menu_item tight'][contains(text(),'%s')]";

    @Step("Chose Language ")
    public void selectLanguage(String selectLanguage) {
        lblLanguage.click();
        TextBox currentLanguage = new TextBox(By.xpath(String.format(btnLanguage, selectLanguage)));
        if (currentLanguage.isDisplayed()) {
            prop = new PropertyReader("localisation/loc_en.properties");
        } else {
            lblLanguage.click();
            prop = new PropertyReader("localisation/loc_ru.properties");

        }
        currentLanguage.click();
    }

    @Step("Click on Install Steam button on the Header")
    public void clickOnInstallSteam() {
        btnInstallSteam.clickAndWait();
    }
}