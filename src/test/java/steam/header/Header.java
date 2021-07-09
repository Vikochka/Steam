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
    private static Label lblpopup= new Label(By.xpath("//div[@id='language_dropdown']"));

    public void selectLanguage(String selectLanguage) {
        System.out.println("1");
        lblLanguage.clickViaJS();
        System.out.println("2");
        lblpopup.isDisplayed();
        System.out.println("i see you");
        TextBox currentLanguage = new TextBox(By.xpath(String.format(btnLanguage, selectLanguage)));
        currentLanguage.waitForIsElementPresent();
        System.out.println("i see lang");
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