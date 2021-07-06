package steam.pages;

import framework.PropertyReader;
import framework.elements.Label;
import org.openqa.selenium.By;

import static framework.PropertyReader.getProperty;

public class MainMenu {
    PropertyReader prop = new PropertyReader();
    private static String lblMenuSectionTemplate = "//a[@class='pulldown_desktop'][contains(text(),'%s')]";
    private static String lblSubSectionTemplate = "//div[contains(@class,'popup_block_new flyout_tab_flyout responsive_slidedown')]//a[contains(text(),'%s')]";

    public void navigateSection(String sectionName, String navSection) {
        Label lblMenu = new Label(By.xpath(String.format(lblMenuSectionTemplate, prop.getProperty(sectionName))));
        lblMenu.click();
        Label lblSection = new Label(By.xpath(String.format(lblSubSectionTemplate, prop.getProperty(navSection))));
        lblSection.clickAndWait();
    }
}