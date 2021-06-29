package steam.pages;

import framework.BasePage;
import lombok.Getter;
import org.openqa.selenium.By;

@Getter
public class MainPage extends BaseSteamPage{

    private static String pageLocator = "logo_holder";

    public MainPage() {
        super(By.id(pageLocator), "Main page");
    }
}