package steam.pages;

import framework.PropertyReader;
import framework.elements.Label;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static framework.PropertyReader.getProperty;

public class MainMenu {
    private static String lblMenuSectionTemplate = "//a[@class='pulldown_desktop'][contains(text(),'%s')]";
    private static String lblSubSectionTemplate = "//div[contains(@class,'popup_block_new flyout_tab_flyout responsive_slidedown')]//a[contains(text(),'%s')]";

    @Step("Navigate Section in tha Main Menu")
    public void navigateSection(String sectionName, String navSection) {
        Label lblMenu = new Label(By.xpath(String.format(lblMenuSectionTemplate, getProperty(sectionName))));
        lblMenu.click();
        Label lblSection = new Label(By.xpath(String.format(lblSubSectionTemplate, getProperty(navSection))));
        lblSection.clickAndWait();
    }
}