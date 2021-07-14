package steam.pageObject.header;

import framework.PropertyReader;
import framework.elements.Button;
import framework.elements.Label;
import framework.elements.TextBox;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;

import static framework.PropertyReader.getProperty;

@Log4j2
public class Header {
    PropertyReader prop = new PropertyReader("localisation/loc_en.properties");
    private Label lblLanguage = new Label(By.xpath("//span[@id='language_pulldown']"));
    private Button btnInstallSteam = new Button(By.xpath("//a[@class='header_installsteam_btn_content']"));
    private static String btnLanguage = "//a[@class='popup_menu_item tight'][contains(text(),'%s')]";

    public void selectLanguage(String selectLanguage) {
        String language = lblLanguage.getText();

        if (language.equals(getProperty("lang_site"))) {
            log.info("Site opening on russian");
            lblLanguage.click();
            TextBox currentLanguage = new TextBox(By.xpath(String.format(btnLanguage, selectLanguage)));
            currentLanguage.click();
        } else {
            log.info("Site opening on english");
        }
    }

    public void clickOnInstallSteam() {
        btnInstallSteam.clickAndWait();
    }
}