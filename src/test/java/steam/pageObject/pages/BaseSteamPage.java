package steam.pageObject.pages;

import framework.BasePage;
import lombok.Getter;
import org.openqa.selenium.By;
import steam.pageObject.header.Header;
import steam.pageObject.menu.MainMenu;

@Getter
public abstract class BaseSteamPage extends BasePage {

    private final Header header = new Header();
    private final MainMenu mainMenu = new MainMenu();

    public BaseSteamPage(By by, String titlePage) {
        super(by, titlePage);
    }
}