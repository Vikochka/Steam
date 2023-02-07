package steam.pageObject.menu;

import framework.elements.Label;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;

import static framework.PropertyReader.getProperty;

public class MainMenu {
    private static String lblMenuSectionTemplate = "//a[@class='pulldown_desktop'][contains(text(),'%s')]";
    private static String lblSubSectionTemplate = "//div[contains(@class,'popup_block_new flyout_tab_flyout responsive_slidedown')]//a[contains(text(),'%s')]";
    private final Logger log = LogManager.getLogger(MainMenu.class);

    public void navigateSection(String sectionName, String navSection) {
        log.info("Navigate section:");
        Label lblMenu = new Label(By.xpath(String.format(lblMenuSectionTemplate, getProperty(sectionName))));
        lblMenu.click();
        Label lblSection = new Label(By.xpath(String.format(lblSubSectionTemplate, getProperty(navSection))));
        lblSection.clickAndWait();
    }
}