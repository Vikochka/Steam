package steam.tests;

import io.qameta.allure.Step;
import framework.BaseTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import steam.pageObject.pages.*;


public class SteamTest extends BaseTest {

    @Parameters({"language", "year"})
    @Step("Chose game with max discount and download SteamSetup file")
    @Test(description = "Chose game with max discount and download SteamSetup file")
    public void steamTest(String language, String year) {

        MainPage mainPage = new MainPage();
        mainPage.getHeader().selectLanguage(language);
        mainPage.getMainMenu().navigateSection("categories_key", "action_key");

        ActionPage actionPage = new ActionPage();
        actionPage.selectGameWithMaxDiscount();

        if (AgePage.waitForPageToLoad()) {
            System.out.println("popa");
            AgePage agePage = new AgePage();
            agePage.checkAge(year);
        } else {
            GamePage gamePage = new GamePage();
            gamePage.checkCurrentGame();
        }

        DownloadPage downloadPage = new DownloadPage();
        downloadPage.getHeader().clickOnInstallSteam();
        downloadPage.downloadSteam();
    }
}