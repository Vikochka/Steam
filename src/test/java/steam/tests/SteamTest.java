package steam.tests;

import io.qameta.allure.Step;
import steam.pages.*;
import framework.BaseTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;


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
            AgePage agePage = new AgePage();
            agePage.checkAge(year);
        }else {
            GamePage gamePage = new GamePage();
            gamePage.checkCurrentGame();
        }

        DownloadPage downloadPage = new DownloadPage();
        downloadPage.clickOnInstallSteam();
        try {
            downloadPage.downloadSteam();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}