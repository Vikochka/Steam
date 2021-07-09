package steam.header;

import framework.PropertyReader;
import framework.elements.Button;
import framework.elements.Label;
import framework.elements.TextBox;
import org.openqa.selenium.By;

public class Header {
    PropertyReader prop = new PropertyReader("localisation/loc_en.properties");

    private Label lblLanguage = new Label(By.xpath("//span[@id='language_pulldown']"));
    private Button btnInstallSteam = new Button(By.xpath("//a[@class='header_installsteam_btn_content']"));
    private static String btnLanguage = "//a[@class='popup_menu_item tight'][contains(text(),'%s')]";

    public void selectLanguage(String selectLanguage) {
        System.out.println("1");
        lblLanguage.clickViaJS();
        System.out.println("2");
        TextBox currentLanguage = new TextBox(By.xpath(String.format(btnLanguage, selectLanguage)));
        currentLanguage.isDisplayed();
        System.out.println("6");
        if (currentLanguage.isDisplayed()) {
            System.out.println("3");
            currentLanguage.click();
            System.out.println("4");
        } else {
            System.out.println("5");
            lblLanguage.click();
        }
    }

    public void clickOnInstallSteam() {
        btnInstallSteam.clickAndWait();
    }
}