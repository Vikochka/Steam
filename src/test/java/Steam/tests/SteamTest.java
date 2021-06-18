package Steam.tests;

import framework.BaseTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import Steam.pages.ActionPage;
import Steam.pages.MainPage;
import Steam.pages.MenuSection;


public class SteamTest extends BaseTest {

    @Parameters({"language"})
    @Test
    public void steamTest(String language) {

        MainPage mainPage = new MainPage();
        mainPage.selectLanguage(language);

        MenuSection menuSection = new MenuSection();
        menuSection.navigateSection("categories_key", "action_key");

        ActionPage actionPage = new ActionPage();
        actionPage.selectGameWithMaxDiscount();
    }
}
