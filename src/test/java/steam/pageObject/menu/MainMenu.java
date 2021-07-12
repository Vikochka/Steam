package steam.pageObject.menu;

import framework.elements.Label;
import org.openqa.selenium.By;

import static framework.PropertyReader.getProperty;

public class MainMenu {
    private static String lblMenuSectionTemplate = "//a[@class='pulldown_desktop'][contains(text(),'%s')]";
    private static String lblSubSectionTemplate = "//div[contains(@class,'popup_block_new flyout_tab_flyout responsive_slidedown')]//a[contains(text(),'%s')]";

    public void navigateSection(String sectionName, String navSection) {
        System.out.println("Navigate section");
        Label lblMenu = new Label(By.xpath(String.format(lblMenuSectionTemplate, getProperty(sectionName))));
        lblMenu.click();
        Label lblSection = new Label(By.xpath(String.format(lblSubSectionTemplate, getProperty(navSection))));
        lblSection.clickAndWait();
    }
}