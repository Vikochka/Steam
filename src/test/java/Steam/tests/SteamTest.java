package Steam.tests;

import Steam.pages.GamePage;
import framework.BaseTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import Steam.pages.ActionPage;
import Steam.pages.MainPage;
import Steam.pages.MenuSection;


public class SteamTest extends BaseTest {

    @Parameters({"language", "year"})
    @Test
    public void steamTest(String language, String year) {

        MainPage mainPage = new MainPage();
        mainPage.selectLanguage(language);

        MenuSection menuSection = new MenuSection();
        menuSection.navigateSection("categories_key", "action_key");

        ActionPage actionPage = new ActionPage();
        actionPage.selectGameWithMaxDiscount();
      //  actionPage.checkAge(year);
//
//        GamePage gamePage = new GamePage();
//        gamePage.clickOnInstallSteam();
//        gamePage.downloadSteam();
    }
}
