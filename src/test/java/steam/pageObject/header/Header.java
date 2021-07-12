package steam.pageObject.header;

import framework.PropertyReader;
import framework.elements.Button;
import framework.elements.Label;
import framework.elements.TextBox;
import org.openqa.selenium.By;

import static framework.PropertyReader.getProperty;

public class Header {
    PropertyReader prop;
    private Label lblLanguage = new Label(By.xpath("//span[@id='language_pulldown']"));
    private Button btnInstallSteam = new Button(By.xpath("//a[@class='header_installsteam_btn_content']"));
    private static String btnLanguage = "//a[@class='popup_menu_item tight'][contains(text(),'%s')]";
    private static Label lblpopup = new Label(By.xpath("//div[@id='language_dropdown']"));

    public void selectLanguage(String selectLanguage) {
        String language = lblLanguage.getText();
        if (language.equals("язык")){
            System.out.println("Site opening on russian");
            lblLanguage.click();
            TextBox currentLanguage = new TextBox(By.xpath(String.format(btnLanguage, selectLanguage)));
            currentLanguage.click();
            System.out.println("Click on the language selected");
            prop = new PropertyReader("localisation/loc_en.properties");
        }else{
            System.out.println(("Site opening on english"));
            prop = new PropertyReader("localisation/loc_en.properties");
        }
    }

    public void clickOnInstallSteam() {
        btnInstallSteam.clickAndWait();
    }
}