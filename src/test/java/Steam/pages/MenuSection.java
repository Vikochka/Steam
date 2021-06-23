package Steam.pages;

import framework.BasePage;
import framework.elements.Label;
import org.openqa.selenium.By;

import static framework.PropertyReader.getProperty;

public class MenuSection extends BasePage {
    private static By menuLocator = By.xpath("//div[@class='store_nav']");
    private static String btnSection = "//a[@class='pulldown_desktop'][contains(text(),'%s')]";
    private static String btnNavigateSection = "//div[contains(@class,'popup_block_new flyout_tab_flyout responsive_slidedown')]//a[contains(text(),'%s')]";

    public MenuSection() {
        super(menuLocator, "Main menu");
    }

    public void navigateSection(String sectionName, String navSection) {
        Label section = new Label(By.xpath(String.format(btnSection, getProperty(sectionName))));
        section.click();
        Label navigateSection = new Label(By.xpath(String.format(btnNavigateSection, getProperty(navSection))));
        navigateSection.clickAndWait();
    }
}