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

        String projectId = browser.getLocation().split("/")[3];
        if (projectId == "agecheck") {
            System.out.println("Age page was opened");
            AgePage agePage = new AgePage();
            agePage.checkAge(year);
        } else {
            System.out.println("Game page was opened");
            GamePage gamePage = new GamePage();
            gamePage.checkCurrentGame();
            gamePage.getHeader().clickOnInstallSteam();
        }

            DownloadPage downloadPage = new DownloadPage();
            downloadPage.downloadSteam();
            downloadPage.deleteFile();
        }
    }