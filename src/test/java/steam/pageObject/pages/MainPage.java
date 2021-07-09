package steam.pageObject.pages;

import org.openqa.selenium.By;


public class MainPage extends BaseSteamPage {

    private static String pageLocator = "logo_holder";

    public MainPage() {
        super(By.id(pageLocator), "Main page");
    }
}